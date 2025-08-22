package com.ianmillican.transcriptanalyzer.domain.model;

import java.time.LocalDate;
import java.util.List;

public class Transcript {
	
	private final Student student;
	private final List<Term> terms;
	private final LocalDate DOI;
	
	public Transcript(List<Term> terms, Student student, LocalDate DOI) {
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
	
	public LocalDate getDOI() {
		return DOI;
	}
	
	@Override
	public String toString() {
		return student + "\n" +
				printTerms();
	}
	
	private String printTerms() {
		StringBuilder builder = new StringBuilder();
		for(Term t : terms) {
			builder.append(t + "\n");
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
	
}
