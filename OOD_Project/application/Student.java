package application;

import java.util.Date;

public class Student {

	private Integer studentNum;
	private String year;
	private String course;
	private String firstName;
	private String lastName;
	private String gender;
	private Date birth;
	private String status;
	private String image;
	private double firstSem;
	private double secondSem;
	private double finals;


	public Student(Integer studentNum, String year, String course, String firstName, String lastName, String gender,
			Date birth, String status, String image) {
		this.studentNum = studentNum;
		this.year = year;
		this.course = course;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birth = birth;
		this.status = status;
		this.image = image;
	}
	
    public Student(Integer studentNum, String year, String course, Double firstSem, Double secondSem, Double finals) {
        this.studentNum = studentNum;
        this.year = year;
        this.course = course;
        this.firstSem = firstSem;
        this.secondSem = secondSem;
        this.finals = finals;
    }

	public Integer getStudentNum() {
		return studentNum;
	}

	public String getYear() {
		return year;
	}

	public String getCourse() {
		return course;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getGender() {
		return gender;
	}

	public Date getBirth() {
		return birth;
	}

	public String getStatus() {
		return status;
	}

	public String getImage() {
		return image;
	}

	public double getFirstSem() {
		return firstSem;
	}

	public void setFirstSem(double firstSem) {
		this.firstSem = firstSem;
	}

	public double getSecondSem() {
		return secondSem;
	}

	public void setSecondSem(double secondSem) {
		this.secondSem = secondSem;
	}

	public double getFinals() {
		return finals;
	}

	public void setFinals(double finals) {
		this.finals = finals;
	}


}
