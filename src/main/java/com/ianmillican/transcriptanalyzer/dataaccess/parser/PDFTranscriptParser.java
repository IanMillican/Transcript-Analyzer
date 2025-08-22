package com.ianmillican.transcriptanalyzer.dataaccess.parser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.ianmillican.transcriptanalyzer.domain.interfaces.TranscriptParser;
import com.ianmillican.transcriptanalyzer.domain.model.Transcript;
import com.ianmillican.transcriptanalyzer.domain.model.Student;
import com.ianmillican.transcriptanalyzer.domain.model.Term;
import com.ianmillican.transcriptanalyzer.errors.ParsingException;

public class PDFTranscriptParser implements TranscriptParser {
	
	public PDFTranscriptParser() {}

	@Override
	public Transcript parse(File input) throws ParsingException {
		
		Transcript transcript = null;
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
					"^(\\d{1,2})/(\\d{1,2})\\s+(\\d{1,2})\\s([A-Za-z]{3,4})\\s(\\d{4})$",
					Pattern.CASE_INSENSITIVE
				).matcher(DOBandDOILine);
			if(!m.find()) {
				throw new ParsingException("Could not parse DOB/DOI");
			}
			int DOBMonth = Integer.parseInt(m.group(1));
			int DOBDay = Integer.parseInt(m.group(2));
			int day = Integer.parseInt(m.group(3));
			String monthString = m.group(4);
			if(monthString == null) {
				throw new ParsingException("Issue extracting the month of the DOI as a string");
			}
			int monthInt = monthToInt(monthString);
			int year = Integer.parseInt(m.group(5));
			index++;
			
			
			Student s = null;
			if(ID != 0 && name != null) {
				s = new Student(name, ID, MonthDay.of(DOBMonth, DOBDay));
			} else {
				throw new ParsingException("Issue parsing Student information");
			}
			
			PDFTermParser termParser = new PDFTermParser();
			List<Term> terms = termParser.parse(lines.subList(index, lines.size()));
			transcript = new Transcript(terms, s, LocalDate.of(year, monthInt, day));
			
		} catch (IOException e) {
			throw new ParsingException("IOException thrown, failed to load the document");
		}
		
		return transcript;
	}
	
	private int monthToInt(String month) throws ParsingException{
		int monthInt = 0;
		switch (month) {
			case "Jan":
				monthInt = 1;
				break;
			case "Feb":
				monthInt = 2;
				break;
			case "Mar":
				monthInt = 3;
				break;
			case "Apr":
				monthInt = 4;
				break;
			case "May":
				monthInt = 5;
				break;
			case "Jun":
				monthInt = 6;
				break;
			case "Jul":
				monthInt = 7;
				break;
			case "Aug":
				monthInt = 8;
				break;
			case "Sep":
				monthInt = 9;
				break;
			case "Oct":
				monthInt = 10;
				break;
			case "Nov":
				monthInt = 11;
				break;
			case "Dec":
				monthInt = 12;
				break;
			default:
				throw new ParsingException("Error converting the DOI month from a string to an int");
		}
		return monthInt;
	}

}
