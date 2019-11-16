package com.mbc.hr.hrSys.dao.documents;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "department")
public class Department {

	@Id
	private ObjectId _id;

	public Department() {
		this._id = ObjectId.get();
	}

	private String departmentName;

	@PersistenceConstructor
	public Department(String departmentName) {
		this.departmentName = departmentName;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		return "Department [_id=" + _id + ", departmentName=" + departmentName + "]";
	}
}