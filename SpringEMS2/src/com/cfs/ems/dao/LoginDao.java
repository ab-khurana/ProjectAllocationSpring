package com.cfs.ems.dao;

import java.sql.SQLException;
import java.util.List;

import com.cfs.ems.model.*;

public interface LoginDao {
	public Status loginToDB(Login lpo) throws Exception;
	public ProjectAllocation updateProjectAll (ProjectAllocation e) throws Exception;
	public Boolean delete(Integer id) throws Exception;
	public List<ProjectAllocation> fetchAllToDb() throws SQLException;
	public List fetchProjectNameDao() throws Exception;
	public List fetchEmployeeNameDao() throws Exception;
	public int allocationEmployeeDoa(ProjectAllocation projectAllocation) throws Exception;
	public Boolean updateAllocationDao(ProjectAllocation e1) throws Exception;
	public List<ProjectAllocation> searchEmployeeProject(String empId) throws Exception ;
	public List<ProjectAllocation> searchEmployeeProjectName(String projectName) throws Exception;
}
