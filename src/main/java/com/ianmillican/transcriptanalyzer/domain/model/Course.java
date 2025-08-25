package com.ianmillican.transcriptanalyzer.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Course {

	private final String subject;
	private final int num;
	private final boolean coop;
	private final String name;
	private final int creditHours;
	private final String grade;
	private final List<String> transfers;
	
	public Course(String subject, int num, String name, int creditHours, String grade, List<String> transfers) {
		this.subject = subject;
		this.num = num;
		if(num == 0) {
			this.coop = true;
		} else {
			this.coop = false;
		}
		this.name = name;
		this.creditHours = creditHours;
		this.grade = grade;
		this.transfers = transfers;
	}

	public Course(String subject, int num, String name, int creditHours, String grade) {
		this.subject = subject;
		this.num = num;
		if(num == 0) {
			this.coop = true;
		} else {
			this.coop = false;
		}
		this.name = name;
		this.creditHours = creditHours;
		this.grade = grade;
		this.transfers = new ArrayList<>();
	}
	
	public String getsubject() {
		return subject;
	}
	
	public int getNum() {
		return num;
	}
	
	public boolean isCoop() {
		return coop;
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
	
	@Override
	public String toString() {
		String initialString = subject + "\t" + name + "\t" + grade + "\t" + String.format("%.2f", creditHours) + "\t" + String.format("%.2f", pointsConverter()) + "\t";
		StringBuilder builder = new StringBuilder(initialString);
		for(String t : transfers) {
			builder.append(t + ", ");
		}
		builder.deleteCharAt(builder.length()-1);
		builder.deleteCharAt(builder.length()-1);
		return builder.toString();
	}
	
	private double pointsConverter() {
		switch(grade) {
			case "A+":
				return 4.3*creditHours;
			case "A":
				return 4*creditHours;
			case "A-":
				return 3.7*creditHours;
			case "B+":
				return 3.3*creditHours;
			case "B":
				return 3*creditHours;
			case "B-":
				return 2.7*creditHours;
			case "C+":
				return 2.3*creditHours;
			case "C":
				return 2*creditHours;
			case "D":
				return 1*creditHours;
			default:
				return 0;
		}
	}
	
}
