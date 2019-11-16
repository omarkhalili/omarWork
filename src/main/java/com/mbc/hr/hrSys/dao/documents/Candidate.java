package com.mbc.hr.hrSys.dao.documents;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "candidate")
public class Candidate {

	@Id
	private String id;
	@Indexed(unique = true)
	private String fullName;
	private Date dateOfBirth;
	private Date registrationDate;
	private Integer yearsOfExperience;
	private Department department;

	public Candidate() {
		setRegistrationDate(new Date());
	}

	@PersistenceConstructor
	public Candidate(String fullName, Date dateOfBirth, Date registrationDate, Integer yearsOfExperience,
			Department department) {
		super();
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.registrationDate = registrationDate;
		this.yearsOfExperience = yearsOfExperience;
		this.department = department;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Integer getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(Integer yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", fullName=" + fullName + ", dateOfBirth=" + dateOfBirth + ", registrationDate="
				+ registrationDate + ", yearsOfExperience=" + yearsOfExperience + ", department=" + department + "]";
	}
}