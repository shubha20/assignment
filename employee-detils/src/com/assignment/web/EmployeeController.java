package com.assignment.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.assignment.modals.Employee;
import com.assignment.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService empService;
	@GetMapping(value = "/")
	public String root(Model model){
		model.addAttribute("list", empService.findAll());
		return "list_view";
	}
	@PostMapping(value = "/addEmployee")
	public String addEmployee(HttpServletRequest request){
		System.out.println("name "+request.getParameter("name"));
		System.out.println("age "+request.getParameter("age"));
		System.out.println("city "+request.getParameter("city"));
		System.out.println("contactNumber "+request.getParameter("contactNumber"));
		Employee emp = new Employee();
		emp.setCity(request.getParameter("name"));
		emp.setContactNumber(request.getParameter("contactNumber"));
		emp.setName(request.getParameter("name"));
		emp.setAge(request.getParameter("age"));
		empService.save(emp);
		return "redirect:/";
	}
	@GetMapping(value = "/addEmployee")
	public String getEmployee(HttpServletRequest request){
		return "add_employee";
	}
	@GetMapping(value = "/searchEmployee")
	public String getSearchPage(){
		return "searchEmployee";
	}

	@PostMapping(value = "/searchEmployee")
	public String searchEmployee(HttpServletRequest request,Model model){
		System.out.println("search "+request.getParameter("search"));
		model.addAttribute("list", empService.findAllByName(request.getParameter("search")));
		return "searchEmployee";
	}
	@GetMapping(value = "/updateEmployee")
	public String getUpdatePage(HttpServletRequest request,Model model){
		model.addAttribute("emp", empService.findById(Long.parseLong(request.getParameter("id"))).get());
		return "updateEmployee";
	}

	@PostMapping(value = "/updateEmployee")
	public String updateEmployee(HttpServletRequest request,Model model){
		System.out.println("search "+request.getParameter("id"));
		Employee emp = empService.findById(Long.parseLong(request.getParameter("id"))).get();
		emp.setAge(request.getParameter("age"));
		emp.setName(request.getParameter("name"));
		emp.setCity(request.getParameter("city"));
		emp.setContactNumber(request.getParameter("contactNumber"));
		empService.save(emp);
		return "redirect:/";
	}
	
	@GetMapping(value = "/deleteEmployee")
	public String delete(HttpServletRequest request){
		Employee emp = empService.findById(Long.parseLong(request.getParameter("id"))).get();
		empService.delete(emp);
		return "redirect:/";
	}

}
