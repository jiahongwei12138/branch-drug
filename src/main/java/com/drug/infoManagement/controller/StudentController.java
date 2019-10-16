package com.drug.infoManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.drug.entity.Student;
import com.drug.infoManagement.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/queryAll")
	public void queryAll() {
		Student student=studentService.queryAll();
		System.out.println(student);
	}
	 
	@RequestMapping("/tranferTo")
	public void tranferTo() {
		studentService.tranferTo(2, 3, 200);
	}
}
