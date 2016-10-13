package com.cfs.ems.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;




@Component
public class ProjectAllocationDTO {
	
	private String projectId;
	private String projectAllocationId;
	
	@NotEmpty(message ="Employee name cannot be empty")
	private String employeeName;
	
	@NotEmpty(message ="project name cannot be empty")
	private String projectName;
	
	@NotEmpty(message ="designation cannot be empty")
	private String designation;
	
	@NotEmpty(message ="start date cannot be empty")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String startDate;
	
	@NotEmpty(message ="end date cannot be empty")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String endDate;
	
	private String employeeId;
	private String status;
	
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
