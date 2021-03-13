package com.myapp.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.myapp.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>,PagingAndSortingRepository<Student, Integer>{
	
	Page<Student> findAll(Pageable pageable);

}
