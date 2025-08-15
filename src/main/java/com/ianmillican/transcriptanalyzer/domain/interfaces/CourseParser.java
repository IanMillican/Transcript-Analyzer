package com.ianmillican.transcriptanalyzer.domain.interfaces;

import java.util.List;

import com.ianmillican.transcriptanalyzer.domain.model.Course;
import com.ianmillican.transcriptanalyzer.errors.ParsingException;

public interface CourseParser {

	public List<Course> parse(List<String> term) throws ParsingException;
	
}
