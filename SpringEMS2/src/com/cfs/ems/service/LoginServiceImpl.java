package com.cfs.ems.service;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfs.ems.dao.*;
import com.cfs.ems.model.*;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;
	//	private Status status;

	public LoginServiceImpl(LoginDao loginDao){

		this.loginDao = loginDao;
		//		this.status = status;

	}

	public Status login(Login login) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("inside service");
		//		LoginDao dao = new LoginDaoImpl();
		Status status = loginDao.loginToDB(login);
		return status;

	}

	public ProjectAllocation updateprojectAll(ProjectAllocationDTO allocationDto)
			throws Exception {
		System.out.println("inside service");
		ProjectAllocation allocation = new ProjectAllocation();
		allocation.setProjectAllocationId(allocationDto.getProjectAllocationId());
		ProjectAllocation s = loginDao.updateProjectAll(allocation);
		return s;
	}

	public Boolean updateAllocation(ProjectAllocationDTO projectAllocationDto) throws Exception{

		ProjectAllocation projectAllocation = new ProjectAllocation();
		Date startDates = null;
		Date endDates = null;
		try{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		startDates = new Date(formatter.parse(projectAllocationDto.getStartDate()).getTime());
		endDates = new Date(formatter.parse(projectAllocationDto.getEndDate()).getTime());
		}catch(Exception e){
			return false;
		}
		projectAllocation.setDesignation(projectAllocationDto.getDesignation());
		projectAllocation.setEmployeeId(projectAllocationDto.getEmployeeId());
		projectAllocation.setEmployeeName(projectAllocationDto.getEmployeeName());
		projectAllocation.setProjectAllocationId(projectAllocationDto.getProjectAllocationId());
		projectAllocation.setProjectId(projectAllocationDto.getProjectId());
		projectAllocation.setProjectName(projectAllocationDto.getProjectName());
		projectAllocation.setStatus(projectAllocationDto.getStatus());

		projectAllocation.setStartDate(startDates);
		projectAllocation.setEndDate(endDates);

		Boolean status = loginDao.updateAllocationDao(projectAllocation);
		return status;
	}

	public List<ProjectAllocation> fetchAll() throws SQLException {

		List<ProjectAllocation> listOfProjects = loginDao.fetchAllToDb();
		System.out.println("ListOfProjectAllocationServiceImpl");

		System.out.println("In service:: "+listOfProjects);
		return listOfProjects;

	}

	public Boolean deleteService( Integer id) throws Exception{

		Boolean status = loginDao.delete( id);
		return status;

	}
	@Override
	public List fetchProjectName() throws Exception {

		List m=loginDao.fetchProjectNameDao();
		return m;

	}

	@Override
	public List fetchEmployeeName() throws Exception {

		List m=loginDao.fetchEmployeeNameDao();
		return m;


	}

	public List<ProjectAllocation> searchEmployeeProject(String empId) throws Exception {

		List<ProjectAllocation> list = loginDao.searchEmployeeProject(empId);
		return list;


	}
	public List<ProjectAllocation> searchEmployeeProjectName(String projectName) throws Exception {

		List<ProjectAllocation> list = loginDao.searchEmployeeProjectName(projectName);
		System.out.println("inside service "+ projectName);
		return list;


	}
	@Override
	public int allocateEmployee(ProjectAllocationDTO projectAllocationDto)
			throws Exception {
		/*System.out.println(projectAllocation.getEmployeeName());
		System.out.println(projectAllocation.getDesignation());
		System.out.println(projectAllocation.getProjectName());
		System.out.println(projectAllocation.getStartDate());
		System.out.println(projectAllocation.getEndDate());*/
		ProjectAllocation projectAllocation = new ProjectAllocation();
		Date startDates = null;
		Date endDates = null;
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			startDates = new Date(formatter.parse(projectAllocationDto.getStartDate()).getTime());
			endDates = new Date(formatter.parse(projectAllocationDto.getEndDate()).getTime());
		}catch(Exception e){
			return 5;
		}
		projectAllocation.setDesignation(projectAllocationDto.getDesignation());
		projectAllocation.setEmployeeId(projectAllocationDto.getEmployeeId());
		projectAllocation.setEmployeeName(projectAllocationDto.getEmployeeName());
		projectAllocation.setProjectAllocationId(projectAllocationDto.getProjectAllocationId());
		projectAllocation.setProjectId(projectAllocationDto.getProjectId());
		projectAllocation.setProjectName(projectAllocationDto.getProjectName());
		projectAllocation.setStatus(projectAllocationDto.getStatus());

		projectAllocation.setStartDate(startDates);
		projectAllocation.setEndDate(endDates);
		System.out.println("inside service::::::   "+projectAllocation.getStartDate());
		int s=loginDao.allocationEmployeeDoa(projectAllocation);
		return s;
	}


}
