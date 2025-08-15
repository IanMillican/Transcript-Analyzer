package com.ianmillican.transcriptanalyzer.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Course {

	private final String code;
	private final String name;
	private final int creditHours;
	private final String grade;
	private final List<String> transfers;
	
	public Course(String code, String name, int creditHours, String grade, List<String> transfers) {
		this.code = code;
		this.name = name;
		this.creditHours = creditHours;
		this.grade = grade;
		this.transfers = transfers;
	}

	public Course(String code, String name, int creditHours, String grade) {
		this.code = code;
		this.name = name;
		this.creditHours = creditHours;
		this.grade = grade;
		this.transfers = new ArrayList<>();
	}
	
	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	public int getCreditHours() {
		return creditHours;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public List<String> getTransfers() {
		return transfers;
	}
	
}
