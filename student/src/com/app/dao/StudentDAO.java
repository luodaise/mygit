package com.app.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.entity.Student;
@SuppressWarnings("unused")
public interface StudentDAO 
{
		public List<Student> allStudent(String page);
		public Student findById(String id);
		public void addStudent(Student student);
		public boolean deleteStudent(String id);
		public boolean updateStudent(Student student);
		public List<Student> queryStudent(Student student);
		public int getPageNum();
}
