package com.cfs.ems.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cfs.ems.model.ProjectAllocation;
import com.cfs.ems.model.ProjectAllocationDTO;
//import com.cfs.ems.service.ListOfProjectAllocationService;
import com.cfs.ems.service.LoginService;

/**
 * @author Priyanka Uppalwar
 * 
 */
@Controller
public class ListOfProjectAllocationController {

	private LoginService loginService;

	public ListOfProjectAllocationController(LoginService loginService){

		this.loginService = loginService;

	}

	@RequestMapping(value = "/projectAllocateform", method = RequestMethod.GET)
	public ModelAndView projectAllocation(ModelMap m) throws Exception{

		ModelAndView view = new ModelAndView();

		List list=loginService.fetchProjectName();
		System.out.println(list);

		List list1=loginService.fetchEmployeeName();

		System.out.println(list1);
		view.setViewName("projectAllocation");
		view.addObject("projectName", list);
		view.addObject("employeeName", list1);
		return view;
	}

	@RequestMapping(value="/allocateEmployee",method=RequestMethod.POST)
	public String AllocateEmployee(ModelMap model,@Valid @ModelAttribute("allocateForm")ProjectAllocationDTO projectAllocationDto,BindingResult result) throws Exception {
		System.out.println("inside registering data for createEmployee");

		if(result.hasErrors()){
			model.addAttribute(projectAllocationDto);
			List projectName=loginService.fetchProjectName();
//			System.out.println(projectName);

			List employeeName=loginService.fetchEmployeeName();
			model.addAttribute("projectName", projectName);
			model.addAttribute("employeeName", employeeName);
			return "projectAllocation";
		}
		System.out.println("start date is "+projectAllocationDto.getStartDate());


		int e = loginService.allocateEmployee(projectAllocationDto);
		ModelAndView view = new ModelAndView();
		if(e==1){
			return "message";
		}
		else if(e==2 || e==5){
			return "error";
		}else if(e==6){
			return "errorDates";
		}
		else if(e==4){
			return "dateprocess";
		}
		else {
			//        	   model.addAttribute("message", "Employee already working in the project so edit it");
			return "already";
		}

	}

	@RequestMapping(value = "/listOfProjectAllocation",method=RequestMethod.GET)
	public ModelAndView welcome(ProjectAllocation projectAllocation) throws Exception{

		List<ProjectAllocation> list = loginService.fetchAll();

		System.out.println("in test"+list);

		for(ProjectAllocation pa:list){

			System.out.println("inside test"+pa.getProjectAllocationId());
			System.out.println(pa.getEmployeeId());
		}

		ModelAndView view = new ModelAndView();

		view.setViewName("listOfProjectAllocation");
		view.addObject("list", list);

		return view;

	}

	/*@RequestMapping(value = "/listOfProjectAllocation")
	public ModelAndView welcome(ProjectAllocation projectAllocation, HttpSession session) throws Exception{

		List<ProjectAllocation> list = listOfProjectAllocationService
				.fetchAll();
		session.setAttribute("empId", status.getEmpId());

	 */



}
