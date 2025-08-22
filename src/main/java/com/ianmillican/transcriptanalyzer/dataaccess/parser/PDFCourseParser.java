package com.ianmillican.transcriptanalyzer.dataaccess.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ianmillican.transcriptanalyzer.domain.interfaces.CourseParser;
import com.ianmillican.transcriptanalyzer.domain.model.Course;
import com.ianmillican.transcriptanalyzer.errors.ParsingException;

public class PDFCourseParser implements CourseParser {

	@Override
	public List<Course> parse(List<String> term) throws ParsingException {
		List<Course> courses = new ArrayList<>();
		Pattern p = Pattern.compile(
			    "^([A-Z]{2,10})\\*([A-Z0-9]{2,5})\\s+(.+?)\\s+(?:(WF|INC|NCR|CR|[A-D][+-]?|F|W)\\s+)?"
			  + "(\\d{1,3}\\.\\d{2})(?:\\s+\\d{1,3}\\.\\d{2})?(?:\\s+([A-Z]{2,4}(?:\\s+[A-Z]{2,4})*))?\\s*$"
			);

		for(String line: term) {
			Matcher m = p.matcher(line);
			if(m.matches()) {
				String subject = m.group(1); // Required
				String code = m.group(2); // Required
				String title = m.group(3); // Required
				String grade = m.group(4); // Not Required
				if(grade == null) {
					grade = "";
				}
				String creditHoursString = m.group(5); // Required
				double creditHours = Double.parseDouble(creditHoursString);
				String transfers = m.group(6);
				List<String> transfersList = 
						(transfers == null || transfers.isBlank())
						? new ArrayList<>() : Arrays.asList(transfers.trim().split("\\s+"));
				if(isNumeric(code)) {
					int num = Integer.parseInt(code);
					Course newCourse = new Course(subject, num, title, creditHours, grade, transfersList);
					courses.add(newCourse);
				} else {
					Course newCourse = new Course(subject, 0, title, creditHours, grade, transfersList);
					courses.add(newCourse);
				}
			}
		}
		return courses;
	}
	
	private boolean isNumeric(String code) {
		return code.matches("\\d{4}");
	}

}
