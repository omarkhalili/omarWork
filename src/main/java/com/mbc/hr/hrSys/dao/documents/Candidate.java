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
	private Date regestrationDate;
	private Integer yearsOfExperience;
	private Department department;

	public Candidate() {
		setRegestrationDate(new Date());
	}

	@PersistenceConstructor
	public Candidate(String fullName, Date dateOfBirth, Date regestrationDate, Integer yearsOfExperience,
			Department department) {
		super();
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.regestrationDate = regestrationDate;
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

	public Date getRegestrationDate() {
		return regestrationDate;
	}

	public void setRegestrationDate(Date regestrationDate) {
		this.regestrationDate = regestrationDate;
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
		return "Candidate [id=" + id + ", fullName=" + fullName + ", dateOfBirth=" + dateOfBirth + ", regestrationDate="
				+ regestrationDate + ", yearsOfExperience=" + yearsOfExperience + ", department=" + department + "]";
	}
}