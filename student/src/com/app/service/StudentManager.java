package com.app.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.entity.Student;

@SuppressWarnings("unused")
public interface StudentManager 
{
	public List<Student> allStudent(String page);
	public Student findById(String id);
	public void addStudent(Student student);
	public boolean deleteStudent(String id);
	public boolean updateStudent(Student student);
	public List<Student> queryStudent(Student student);
	public int getPageNum();
}

