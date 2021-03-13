package com.myapp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.models.Student;
import com.myapp.models.StudentPage;
import com.myapp.repositories.StudentRepository;

@RestController
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepo;
	
	@PostMapping("/add")
	public String addStudent(@RequestBody Student s) {
		List<Student> list = new ArrayList<Student>();
		list.add(new Student(1, "Punit", 21));
		list.add(new Student(2, "Raj", 22));
		list.add(new Student(3, "Mayank", 23));
		list.add(new Student(4, "Nitish", 20));
		for(int i=0;i<list.size();i++) {
			studentRepo.save(list.get(i));
		}
		return "Students Added Successfully";
	}
	
	@GetMapping("/get")
	public Student getStudent(@RequestParam(value = "id") int id) {
		return studentRepo.findById(id).get();
	}
	
	@GetMapping("/getAll")
	public List<Student> getStudents() {
		return studentRepo.findAll();
	}

	@GetMapping("/students")
	public Page<Student> getStudents(StudentPage studentPage) {
		Sort sort = Sort.by(studentPage.getSortDirection(), studentPage.getSortBy());
		Pageable pageable = PageRequest.of(studentPage.getPageNumber(), studentPage.getPageSize(),sort);
		return studentRepo.findAll(pageable);
	}

}
