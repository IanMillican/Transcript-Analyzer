package com.ianmillican.transcriptanalyzer.domain.model;

import java.util.List;

public class ParsedTranscript {
	
	private final Student student;
	private final List<Term> terms;
	
	public ParsedTranscript(List<Term> terms, Student student) {
		this.terms = terms;
		this.student = student;
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
	
}
