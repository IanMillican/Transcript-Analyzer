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
			throw new ParsingException("This transcript is empty");
		}
		List<Term> terms = new ArrayList<>();
		
		while(!transcript.isEmpty()) {
			int index = 0;
			String line = transcript.get(index);
			while(index < transcript.size() && skipLine(line)) {
				line = transcript.get(index);
				index++;
			}
			if(index >= transcript.size()) {
				break;
			}
			//Year/Term/Degree/Location
			Matcher m = Pattern.compile("^(\\d{4})/([A-Z]{2})\\s+([A-Z]{2,6}(?:\\s+[A-Z]{2,10})?)\\s+(\\S(?:.*\\S)?)\\s*$").matcher(line);
			int year = 0;
			if(m.matches()) {
				year = Integer.parseInt(m.group(1));
			} else {
				throw new ParsingException("Error parsing terms and degree info");
			}
			String term = m.group(2);
			String degree = m.group(3);
			String location = m.group(4);
			index++;
			if(index >= transcript.size()) break;
			//Parsing Courses
			List<String> rawCourses = new ArrayList<>();
			line = transcript.get(index);
			while(index < transcript.size() && !termSeparator(line)) {
				if(!skipLine(line)) {
					rawCourses.add(line);
				}
				index++;
				line = transcript.get(index);
			}
			index++;
			PDFCourseParser parser = new PDFCourseParser();
			List<Course> parsedCourses = parser.parse(rawCourses);
			terms.add(new Term(term, year, degree, parsedCourses, location));
			transcript = transcript.subList(index, transcript.size());
			
		}
		return terms;
	}
	
	private boolean skipLine(String line) {
		return creditHourLine(line) || endOfYear(line) || startOrEndOfPage(line) || endOfTerm(line) || header(line) 
				|| endOfRecord(line) || graduation(line) || deansList(line) || coop(line);
	}
	
	private boolean deansList(String line) {
		return line.startsWith("Dean's");
	}
	
	private boolean coop(String line) {
		return line.startsWith("4-months") || line.startsWith("with");
	}
	
	private boolean graduation(String line) {
		return line.startsWith("Degree conferred") || line.startsWith("Bachelor") || line.startsWith("Minor")
				|| (line.startsWith("(") && !line.startsWith("(Continued"));
	}
	
	private boolean endOfRecord(String line) {
		String award = "^Awards\\s+Granted:$";
		String awardInstance = "^\\d{4}-\\d{2}\\s+.*$";
		String EOR = "^End\\s+of\\s+record$";
		
		Matcher awardMatcher = Pattern.compile(award).matcher(line);
		Matcher awardInstanceMatcher = Pattern.compile(awardInstance).matcher(line);
		Matcher EORMatcher = Pattern.compile(EOR).matcher(line);
		
		return awardMatcher.matches() || awardInstanceMatcher.matches() || EORMatcher.matches();
	}
	
 	private boolean header(String line) {
		String headerLine = "GRADE\\s+HRS\\s+POINTS\\s+TRANSFERS";
		Matcher headerMatch = Pattern.compile(headerLine).matcher(line);
		return headerMatch.matches();
	}
	
	private boolean creditHourLine(String line) {
		String creditLine = "^Program\\s+Credit\\s+Hours:\\s+Attempted\\s+\\d{1,3}.\\d{2}\\s+Passed\\s+\\d{1,3}\\d{2}\\s+Cumulative\\s+GPA...\\s+\\d.\\d$";
		Matcher creditMatch = Pattern.compile(creditLine).matcher(line);
		
		if(creditMatch.matches()) {
			return true;
		}
		
		return false;
	}
	
	private boolean termSeparator(String line) {
		String termSeparate = "^_+\\s*$";
		Matcher termSep = Pattern.compile(termSeparate).matcher(line);
		if(termSep.matches()) {
			return true;
		}
		
		return false;
	}
	
	private boolean endOfYear(String line) {
		String gpaLine = "^\\d{4}/\\d{2}\\s+Assessment\\s+Year\\s+GPA\\.*\\s+\\d.\\d$";
		String academicStanding = "^In\\s+good\\s+academic\\s+standing$";
		
		Matcher gpaMatch = Pattern.compile(gpaLine).matcher(line);
		Matcher academicStandingMatch = Pattern.compile(academicStanding).matcher(line);
		
		if(gpaMatch.matches() || academicStandingMatch.matches()) {
			return true;
		}
		
		return false;
	}
	
	private boolean startOrEndOfPage(String line) {
		String endOfPage = "(?iu)^UNOFFICIAL\\s+TRANSCRIPT\\s*\\(Continued\\s+on\\s+page\\s+\\d{1,3}\\)\\s*$";
		String firstNewPageLine = "^UNOFFICIAL\\s+TRANSCRIPT\\s*$";
		String secondNewPageLine = "^\\(Continued\\s+from\\s+page\\s+\\d{1,3}\\)\\s*$";
		String thirdNewPageLine = "^\\d{6,}\\s+.+$";

		Matcher eop = Pattern.compile(endOfPage).matcher(line);
		Matcher firstNPL = Pattern.compile(firstNewPageLine).matcher(line);
		Matcher secondNPL = Pattern.compile(secondNewPageLine).matcher(line);
		Matcher thirdNPL = Pattern.compile(thirdNewPageLine).matcher(line);

		return eop.matches() || firstNPL.matches() || secondNPL.matches() || thirdNPL.matches();
	}
	
	private boolean endOfTerm(String line) {
		String endOfTerm = "^Program\\s+Credit\\s+Hours:\\s+Attempted\\s+\\d{1,3}\\.\\d{2}\\s+Passed\\s+\\d{1,3}\\.\\d{2}\\s+Cumulative\\s+GPA\\.\\.\\.\\s+\\d{1}\\.\\d{1}\\s*$";
		Matcher EOT = Pattern.compile(endOfTerm).matcher(line);
		if(EOT.matches()) {
			return true;
		}
		return false;
	}


}
