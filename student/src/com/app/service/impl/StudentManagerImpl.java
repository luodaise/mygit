package com.app.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.app.dao.StudentDAO;
import com.app.entity.Student;
import com.app.service.StudentManager;
@Service("studentManager")
public class StudentManagerImpl implements StudentManager{

			private StudentDAO studentDAO;
			
			public StudentDAO getStudentDAO()
			{
			   return studentDAO;
			}
			
			public void setStudentDAO(StudentDAO studentDAO)
			{
			   this.studentDAO = studentDAO;
			}
			@Override
			public void addStudent(Student student)
			{
			   studentDAO.addStudent(student);
			}
			@Override
			public List<Student> allStudent(String page)
			{
			   return studentDAO.allStudent(page);
			}
			@Override
			public boolean deleteStudent(String id)
			{
			   return studentDAO.deleteStudent(id);
			}
			@Override
			public Student findById(String id)
			{
			    return studentDAO.findById(id);
			}
			@Override
			public boolean updateStudent(Student student)
			{
			   return studentDAO.updateStudent(student);
			}
			@Override
			public List<Student> queryStudent(Student student) 
			{
			    return studentDAO.queryStudent(student);
			}
			@Override
			public int getPageNum() 
			{
			   return studentDAO.getPageNum();
			}
}