package com.ianmillican.transcriptanalyzer.ui.viewmodel;

import java.util.ArrayList;
import java.util.List;

import com.ianmillican.transcriptanalyzer.domain.model.Course;
import com.ianmillican.transcriptanalyzer.domain.model.Term;
import com.ianmillican.transcriptanalyzer.domain.model.Transcript;
import com.ianmillican.transcriptanalyzer.domain.service.TranscriptService;

public class AnalysisViewModel {
	
	private final Transcript transcript;
	private final TranscriptService service;
	
	public AnalysisViewModel(Transcript transcript, TranscriptService service) {
		this.transcript = transcript;
		this.service = service;
	}
	
	public List<Course> getAllCourses() {
		List<Course> courseList = new ArrayList<>();
		for(Term t : transcript.getTerms()) {
			courseList.addAll(t.getCourses());
		}
		return courseList;
	}
	
	public String codeAndName(Course course) {
		String codeAndName = "";
		if(course.isCoop()) {
			codeAndName = "" + course.getsubject() + " PEP: " + course.getName();
		} else {
			codeAndName = "" + course.getsubject() + " " +course.getNum() + ": " + course.getName();
		}
		return codeAndName;
	}
	
	public Integer creditHour(Course course) {
		return course.getCreditHours();
	}
	
}
