package com.tajv.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tajv.dao.EmployeeDao;
import com.tajv.model.Employee;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeDao employeeDao;

	@RequestMapping(value = "/logingIn", method = RequestMethod.POST)
	public String logingIn(@ModelAttribute Employee employee, HttpSession session) {

		// TODO: add hash coding to password
		Employee employeeFromDatabase = employeeDao.getEmployeeByNickname(employee.getNickname());
		if (employeeFromDatabase != null) {
			if (employee.getPassword().equals(employeeFromDatabase.getPassword())) {
				session.setAttribute("Darbuotojas", employeeFromDatabase);
				return "redirect:/home";
			}
		}
		return "redirect:/login";
	}

	@RequestMapping(value = { "/login" })
	public ModelAndView loginOld(HttpServletRequest request) {

		ModelAndView model = new ModelAndView("LoginOld");
		return model;
	}

	@RequestMapping(value = { "/home" })
	public ModelAndView home(HttpServletRequest request, HttpSession session) {
		Employee emp = (Employee) session.getAttribute("Darbuotojas");
		if (emp != null) {
			ModelAndView model;
			if (emp.getType().equals("Pardavejas/konsultantas")) {
				model = new ModelAndView("Welcome");
			} else if (emp.getType().equals("Sandelininkas")) {
				model = new ModelAndView("WelcomeLogedIn");
			} else {
				model = new ModelAndView("Home");
			}

			model.addObject("employee", emp);
			return model;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = { "/logout" })
	public ModelAndView logout(HttpServletRequest request, HttpSession session) {
		if (!session.isNew()) {
			session.invalidate();
		}
		return new ModelAndView("redirect:/login");
	}

	@RequestMapping(value = { "/error" })
	public ModelAndView error(HttpServletRequest request) {

		ModelAndView model = new ModelAndView("ErrorLogedOut");
		return model;
	}

	@RequestMapping(value = { "/404" })
	public ModelAndView error404(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("ErrorLogedIn");
		return model;
	}
}