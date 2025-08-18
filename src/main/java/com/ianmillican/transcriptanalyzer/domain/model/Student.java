package com.ianmillican.transcriptanalyzer.domain.model;

public class Student {

	private final String name;
	private final int studentID;
	private final String DOB;
	
	public Student(String name, int studentID, String DOB) {
		this.name = name;
		this.studentID = studentID;
		this.DOB = DOB;
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
	
}
