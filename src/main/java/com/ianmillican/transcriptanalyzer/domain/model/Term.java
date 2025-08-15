package com.ianmillican.transcriptanalyzer.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Term {
	
	private final String location;
	private final String term;
	private final int year;
	private final List<Course> courses;
	private final String degree;
	
	public Term(String term, int year, String degree, List<Course> courses, String location) {
		this.term = term;
		this.year = year;
		this.courses = courses;
		this.degree = degree;
		this.location = location;
	}
	
	public String getTerm() {
		return term;
	}
	
	public int getYear() {
		return year;
	}
	
	public List<Course> getCourses() {
		return new ArrayList<>(courses);
	}
	
	public void addCourse(Course c) {
		courses.add(c);
	}
	
	public String getDegree() {
		return degree;
	}
	
	public String getLocation() {
		return location;
	}
	
	@Override
	public String toString() {
		return year+"/"+term + "\t" + degree + "\t" + location + "\n" + courses;
	}
	
}
