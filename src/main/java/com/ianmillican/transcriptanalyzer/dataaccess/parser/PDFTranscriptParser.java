package com.ianmillican.transcriptanalyzer.dataaccess.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.ianmillican.transcriptanalyzer.domain.interfaces.TranscriptParser;
import com.ianmillican.transcriptanalyzer.domain.model.ParsedTranscript;
import com.ianmillican.transcriptanalyzer.domain.model.Student;
import com.ianmillican.transcriptanalyzer.domain.model.Term;
import com.ianmillican.transcriptanalyzer.errors.ParsingException;

public class PDFTranscriptParser implements TranscriptParser {
	
	public PDFTranscriptParser() {}

	@Override
	public ParsedTranscript parse(File input) throws ParsingException {
		
		ParsedTranscript transcript = null;
		//File type validation
		if(!input.getAbsolutePath().endsWith(".pdf") ) {
			throw new ParsingException("Invalid file type");
		}
		
		try(PDDocument document = PDDocument.load(input)) {
			PDFTextStripper stripper = new PDFTextStripper();
			String fullText = stripper.getText(document);
			ArrayList<String> lines = new ArrayList<>(Arrays.asList(fullText.split("\\R")));
			lines.replaceAll(s -> s == null ? "" : s.trim());
			
			int index = 0;

			//PDF validation
			if(lines.size() <= 0 || !lines.get(index).trim().equals("UNOFFICIAL TRANSCRIPT")) {
				throw new ParsingException("This doesn't appear to be an unofficial transcript");
			}
			index++;
			
			//Name and ID
			String nameAndIDLine = lines.get(index);
			Matcher m = Pattern.compile("^(\\d{6,})\\s+(.+)$").matcher(nameAndIDLine);
			if(!m.find()) {
				throw new ParsingException("Could not parse student ID and name");
			}
			int ID = Integer.parseInt(m.group(1));
			String name = m.group(2).trim();
			index+=2; //Skip the Header line for DOB and DOI
			//DOB and DOI
			String DOBandDOILine = lines.get(index);
			m = Pattern.compile(
					"^(\\d{1,2}/\\d{1,2})\\s+(\\d{1,2}\\s[A-Za-z]{3,4}\\s\\d{4})$",
					Pattern.CASE_INSENSITIVE
				).matcher(DOBandDOILine);
			if(!m.find()) {
				throw new ParsingException("Could not parse DOB/DOI");
			}
			String DOB = m.group(1);
			String DOI = m.group(2);
			index++;
			
			
			Student s = null;
			if(ID != 0 && name != null && DOB != null && DOI != null) {
				s = new Student(name, ID, DOB, DOI);
			} else {
				throw new ParsingException("Issue parsing Student information");
			}
			
			PDFTermParser termParser = new PDFTermParser();
			List<Term> terms = termParser.parse(lines.subList(index, lines.size()));
			
			transcript = new ParsedTranscript(terms, s);
			
			
		} catch (IOException e) {
			throw new ParsingException("IOException thrown, failed to load the document");
		}
		
		return transcript;
	}

}
