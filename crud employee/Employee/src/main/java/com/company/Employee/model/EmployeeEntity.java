package com.company.Employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class EmployeeEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;

	@Column(name ="emp_name")
	private String empName;
	
	@Column(name ="dept_id")
	private int deptId;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	@Override
	public String toString() {
		return "EmployeeEntity [Id=" + Id + ", empName=" + empName + ", deptId=" + deptId + "]";
	}

	
}
