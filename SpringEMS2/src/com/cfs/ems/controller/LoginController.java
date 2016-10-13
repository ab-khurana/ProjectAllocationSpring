package com.cfs.ems.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HttpSessionMutexListener;

import com.cfs.ems.model.*;
import com.cfs.ems.service.LoginService;


@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	public LoginController(LoginService loginService){

		this.loginService = loginService;

	}


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(){

		return "login";
	}

	@RequestMapping(value="/loginUser",method=RequestMethod.POST)
	public ModelAndView welcome(Login login, HttpSession session) throws Exception{

		Status status = loginService.login(login);
		session.setAttribute("empId", status.getEmpId());
		session.setAttribute("empName", status.getEmpName());
		session.setAttribute("empLastName", status.getEmpLastName());
		
		System.out.println(status.getEmpName());
		ModelAndView view = new ModelAndView();

		if(status.getStatus()==1){

			view.setViewName("dashboard");
			view.addObject("status", status);
//			return view;

		}
		else if (status.getStatus()==2){

			view.setViewName("ManagerHome");
			view.addObject("status", status);

		}
		else if (status.getStatus()==3){

			view.setViewName("EmployeeHome");
			view.addObject("status", status);

		}
		else{
			view.setViewName("login");	
		}
		return view;


	}
	
	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		session.invalidate();
		return "login";
	
	}
}
