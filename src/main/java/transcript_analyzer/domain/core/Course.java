package transcript_analyzer.domain.core;

public class Course {

	private final String code;
	private final String name;
	private final int creditHours;
	private final String grade;
	
	public Course(String code, String name, int creditHours, String grade) {
		this.code = code;
		this.name = name;
		this.creditHours = creditHours;
		this.grade = grade;
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
	
}
