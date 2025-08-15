package com.ianmillican.transcriptanalyzer.domain.model;

public class Student {

	private final String name;
	private final int studentID;
	private final String DOB;
	private final String DOI;
	
	public Student(String name, int studentID, String DOB, String DOI) {
		this.name = name;
		this.studentID = studentID;
		this.DOB = DOB;
		this.DOI = DOI;
	}
	
	public String getName() {
		return name;
	}
	
	public int getStudentID() {
		return studentID;
	}
	
	public String getDOB() {
		return DOB;
	}
	
	public String getDOI() {
		return DOI;
	}
	
}
