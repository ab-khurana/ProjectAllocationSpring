package com.cfs.ems.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cfs.ems.model.ProjectAllocation;
import com.cfs.ems.model.ProjectAllocationDTO;
import com.cfs.ems.service.LoginService;

@Controller
public class ProjectAllController {

	@Autowired
	private LoginService loginService;

	public ProjectAllController(LoginService loginService){

		this.loginService = loginService;

	}


	@RequestMapping(value = "/updateProject", method = RequestMethod.GET)
	public String updateForm(){

		return "updateProjectForm";

	}

	@RequestMapping(value =  "/editallocate/{id}", method = RequestMethod.GET)
	public String projectAllform(@PathVariable("id")Integer id, @ModelAttribute("projectAll") ProjectAllocationDTO allocationDto, ModelMap model) throws Exception{

		//		System.out.println(allocation.getProjectAllocationId());
		String idv = Integer.toString(id);
		allocationDto.setProjectAllocationId(idv);
		ProjectAllocation projectAllocation = loginService.updateprojectAll(allocationDto);
//		System.out.println(e.getEmployeeId());
//		ModelAndView model = new ModelAndView();
		List list=loginService.fetchProjectName();
		System.out.println(list);

//		model.setViewName("EditProjectAllocation");
		model.addAttribute("editProjectAllocation",projectAllocation);
		model.addAttribute("projectName", list);
		return "EditProjectAllocation";
	}


	@RequestMapping(value="/editallocate/updateprojectAllocation", method=RequestMethod.POST)
	public String AllocateEmployee(@Valid @ModelAttribute("projectAll")ProjectAllocationDTO projectAllocationDto, BindingResult result, ModelMap view) throws Exception {
		System.out.println("inside registering data for createEmployee");

		System.out.println("DTO Name is "+projectAllocationDto.getStartDate());
		
		/*if(result.hasErrors()){
			view.addAttribute(projectAllocationDto);
			List projectName=loginService.fetchProjectName();
			String idv = Integer.toString(id);
			projectAllocationDto.setProjectAllocationId(idv);
			ProjectAllocation projectAllocation = loginService.updateprojectAll(projectAllocationDto);
			view.addAttribute("projectName", projectName);
			view.addAttribute("editProjectAllocation", projectAllocation);
			return "EditProjectAllocation";
		}*/
		
		Boolean e = loginService.updateAllocation(projectAllocationDto);
		if(e){
			
			return "editSuccess";
		}
		else{
			view.addAttribute("projectAllocateObj", projectAllocationDto);
			return "error2";
		}


	}
	
	
	@RequestMapping(value = "/deletes/{id}", method = RequestMethod.POST)
	public ModelAndView deleteController(@PathVariable("id")int id) throws Exception{
		System.out.println("This is id " + id);
		Boolean status = loginService.deleteService(id);
		ModelAndView model = new ModelAndView();
		if(status){

			model.setViewName("deleteSuccess");

		}
		else
			model.setViewName("deleteFailure");

		return model;


	}

	@RequestMapping(value = "/searchbyemployee", method = RequestMethod.GET)
	public String searchByID(){

		return "searchEmployeeProject";

	}
	
	@RequestMapping(value = "/searchproject", method = RequestMethod.POST)
	public String searchEmployeeProject(@Valid @ModelAttribute("searchAllocateForm")  ProjectAllocation allocation, BindingResult result, ModelMap view) throws Exception{
		
		if(result.hasErrors()){
			view.addAttribute(allocation);
			return "searchEmployeeProject";
			
		}
		System.out.println(allocation.getEmployeeId());
		List<ProjectAllocation> list = loginService.searchEmployeeProject(allocation.getEmployeeId());
		
		System.out.println("in test"+list);
				
//		ModelAndView view = new ModelAndView();
		
//		view.setViewName("listOfEmpProject");
		view.addAttribute("list", list);
		
		return "listOfEmpProject";
	
	}
	
	
	@RequestMapping(value = "/searchbyproject", method = RequestMethod.GET)
	public ModelAndView searchByProjectName() throws Exception{
		
		ModelAndView view = new ModelAndView();
		List list=loginService.fetchProjectName();
		view.setViewName("searchEmployeeProjectName");
		view.addObject("projectName", list);
		return view;

	}
	
	@RequestMapping(value = "/searchprojectname", method = RequestMethod.POST)
	public ModelAndView searchEmployeeProjectName(@ModelAttribute("searchAllocateFormProject")  ProjectAllocation allocation) throws Exception{
		ModelAndView view = new ModelAndView();
		
		System.out.println(allocation.getProjectName());
		List<ProjectAllocation> list = loginService.searchEmployeeProjectName(allocation.getProjectName());
		
		System.out.println("in test"+list);
				
		
		
		view.setViewName("listOfEmpProject");
		view.addObject("list", list);
		
		return view;
	
	}
}
