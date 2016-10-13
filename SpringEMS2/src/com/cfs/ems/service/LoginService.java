package com.cfs.ems.service;

import java.sql.SQLException;
import java.util.List;

import com.cfs.ems.model.*;

public interface LoginService {

	public Status login(Login login) throws Exception;
	public ProjectAllocation updateprojectAll (ProjectAllocationDTO allocationDto) throws Exception;
	public List<ProjectAllocation> fetchAll(/*ProjectAllocation projectAllocation*/) throws SQLException;
	public Boolean deleteService( Integer id) throws Exception;
	public List fetchProjectName() throws Exception;
	public List fetchEmployeeName() throws Exception;
	public int allocateEmployee(ProjectAllocationDTO projectAllocation) throws Exception;
	public Boolean updateAllocation(ProjectAllocationDTO projectAllocationDto) throws Exception;
	public List<ProjectAllocation> searchEmployeeProject(String empId) throws Exception;
	public List<ProjectAllocation> searchEmployeeProjectName(String projectName) throws Exception;
}
