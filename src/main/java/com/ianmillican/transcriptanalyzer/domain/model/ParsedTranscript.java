package com.ianmillican.transcriptanalyzer.domain.model;

import java.util.List;

public class ParsedTranscript {
	
	private final Student student;
	private final List<Term> terms;
	private final String DOI;
	
	public ParsedTranscript(List<Term> terms, Student student, String DOI) {
		this.terms = terms;
		this.student = student;
		this.DOI = DOI;
	}
	
	public List<Term> getTerms() {
		return terms;
	}
	
	public String getStudentName() {
		return student.getName();
	}
	
	public int getStudentID() {
		return student.getStudentID();
	}
	
	public String getDOI() {
		return DOI;
	}
	
}
