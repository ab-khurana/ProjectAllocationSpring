package com.cfs.ems.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class ProjectAllocation {
	private String projectId;
	private String projectAllocationId;
	private String employeeName;
	private String projectName;
	private String designation;  
	private Date startDate;
	private Date endDate;
	
	@NotEmpty(message = "Cannot be empty")
	private String employeeId;
	private String status;
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getProjectAllocationId() {
		return projectAllocationId;
	}
	public void setProjectAllocationId(String projectAllocationId) {
		this.projectAllocationId = projectAllocationId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	} 

}
