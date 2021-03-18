package com.company.Employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class DepartmentEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;

	@Column(name ="dept_name")
	private String deptName;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "DepartmentEntity [Id=" + Id + ", deptName=" + deptName + "]";
	}
	
	

}
