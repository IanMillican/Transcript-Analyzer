package transcript_analyzer.domain.core;

import java.util.ArrayList;
import java.util.List;

public class Term {
	
	private final String term;
	private final int year;
	private final List<Course> courses;
	private final String degree;
	
	public Term(String term, int year, String degree) {
		this.term = term;
		this.year = year;
		this.courses = new ArrayList<>();
		this.degree = degree;
	}
	
	public Term(String term, int year, String degree, Course c) {
		this.term = term;
		this.year = year;
		this.courses = new ArrayList<Course>();
		courses.add(c);
		this.degree = degree;
	}
	
	public Term(String term, int year, String degree, List<Course> courses) {
		this.term = term;
		this.year = year;
		this.courses = courses;
		this.degree = degree;
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
	
}
