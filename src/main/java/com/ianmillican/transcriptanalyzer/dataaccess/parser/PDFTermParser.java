package com.ianmillican.transcriptanalyzer.dataaccess.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ianmillican.transcriptanalyzer.domain.interfaces.TermParser;
import com.ianmillican.transcriptanalyzer.domain.model.Course;
import com.ianmillican.transcriptanalyzer.domain.model.Term;
import com.ianmillican.transcriptanalyzer.errors.ParsingException;

public class PDFTermParser  implements TermParser {
	
	public PDFTermParser() {}

	@Override
	public List<Term> parse(List<String> transcript) throws ParsingException {
		if(transcript.isEmpty()) {
			throw new ParsingException("This transcript has no terms.");
		}
		List<Term> terms = new ArrayList<>();
		while(!transcript.isEmpty()) {
			int index = 0;
			//Gather term and degree info
			String line = transcript.get(index);
			System.out.println(line);
			Matcher m = Pattern.compile("^(\\d{4})/([A-Z]{2})\\h+([A-Z]{2,6}(?:\\h+[A-Z]{2,10})?)\\h+(\\S(?:.*\\S)?)\\h*$").matcher(line);
			int year = 0;
			if(m.matches()) {
				year = Integer.parseInt(m.group(1));
			} else {
				throw new ParsingException("Error parsing terms and degree info");
			}
			String term = m.group(2);
			String degree = m.group(3);
			String location = m.group(4);
			index+=2; //Skip header line
			//Group individual courses for this term
			List<String> courses = new ArrayList<>();
			String currLine = transcript.get(index);
			while(!endOfTerm(currLine)) {
				if(!skipLine(currLine)) {
					System.out.println(currLine);
					courses.add(currLine);
					index++;
					currLine = transcript.get(index);
				} else {
					index++;
					currLine = transcript.get(index);
				}
			}
			index+=2;//Skip the end of term line
			PDFCourseParser parser = new PDFCourseParser();
			List<Course> parsedCourses = parser.parse(courses);
			terms.add(new Term(term, year, degree, parsedCourses, location));
			transcript = transcript.subList(index, transcript.size());
			/*
			 * Next I need to handle the end of a page and start of the next page
			 * A new method stub to check for end of page below
			 * A new method stub to check for start of page below
			 */
		}
		
		return terms;
	}
	
	private boolean endOfPage(String line) {
		
		return false;
	}
	
	private boolean startOfPage(String line) {
		
		return false;
	}
	
	private boolean skipLine(String line) {
		String termSeparator = "^_+\\h*$";
		String pageSeperator = "^UNOFFICIAL\\h+TRANSCRIPT\\h+\\(Continued\\h+on\\h+page\\h+(\\d{1,2})\\)\\h*$";
		Matcher termSep = Pattern.compile(termSeparator).matcher(line);
		Matcher pageSep = Pattern.compile(pageSeperator).matcher(line);
		if(termSep.matches() || pageSep.matches()) {
			return true;
		}
		return false;
	}
	
	private boolean endOfTerm(String line) {
		String endOfTerm = "^Program\\h+Credit\\h+Hours:\\h+Attempted\\h+\\d{1,3}\\.\\d{2}\\h+Passed\\h+\\d{1,3}\\.\\d{2}\\h+Cumulative\\h+GPA\\.\\.\\.\\h+\\d{1}\\.\\d{1}\\h*$";
		Matcher EOT = Pattern.compile(endOfTerm).matcher(line);
		if(EOT.matches()) {
			return true;
		}
		return false;
	}

}
