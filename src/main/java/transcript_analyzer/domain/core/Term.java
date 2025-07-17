package transcript_analyzer.domain.core;

import java.util.ArrayList;
import java.util.List;

public class Term {
	
	private final String term;
	private final int year;
	private final List<Course> courses;
	
	public Term(String term, int year) {
		this.term = term;
		this.year = year;
		this.courses = new ArrayList<>();
	}
	
	public Term(String term, int year, Course c) {
		this.term = term;
		this.year = year;
		this.courses = new ArrayList<Course>();
		courses.add(c);
	}
	
	public Term(String term, int year, List<Course> courses) {
		this.term = term;
		this.year = year;
		this.courses = courses;
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
