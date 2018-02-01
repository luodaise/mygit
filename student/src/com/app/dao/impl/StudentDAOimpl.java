package com.app.dao.impl;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.app.dao.StudentDAO;
import com.app.entity.Student;
@Scope("prototype")
@Repository("studentDao")
public class StudentDAOimpl implements StudentDAO {
			private SessionFactory sessionFactory;
			private int pageSize = 10;
			
			public void setSessionFactory(SessionFactory sessionFactory) { 
			this.sessionFactory = sessionFactory; 
			}
			public void addStudent(Student student) {
			sessionFactory.getCurrentSession().save(student);
}

			/**
			* 获取所有学生
			*/
			@SuppressWarnings("unchecked")
			public List<Student> allStudent(String page) 
			{
				String hql="from Student";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				query.setFirstResult((Integer.parseInt(page)-1)*pageSize); 
				query.setMaxResults(pageSize); 
				return query.list();
			}

			/**
			* 根据id删除学生
			*/
			public boolean deleteStudent(String id)
			{
				String hql ="delete Student s where s.id = ? ";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				query.setString(0, id);
				return (query.executeUpdate()>0);
			}

			/**
			* 根据id查找学生
			*/
			public Student findById(String id)
		 {
				String hql = "from Student s where s.id=? "; 
				Query query = sessionFactory.getCurrentSession().createQuery(hql); 
				query.setString(0, id); 
				return (Student)query.uniqueResult(); 
			}

			/**
			* 编辑学生信息
			*/
			public boolean updateStudent(Student student)
			{
				String hql="update Student s set s.name =?,s.age=?,s.sex=?,s.num=? where s.id =?";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				query.setString(0, student.getName());
				query.setInteger(1, student.getAge());
				query.setString(2, student.getSex());
				query.setString(3, student.getNum());
				query.setString(4, student.getId());
				return (query.executeUpdate() >0);
			}
			/**
			* 条件查找学生
			*/
			@SuppressWarnings("unchecked")
			public List<Student> queryStudent(Student student)
         {
				StringBuffer hql = new StringBuffer();
				hql.append("from Student s where 1=1");
				if(!student.getName().equals("") && student.getName()!=null){
					hql.append(" and s.name like '%"+student.getName()+"%'");
				}
				if(student.getAge() != null){
				hql.append(" and s.age like 三'%"+student.getAge()+"%'");
				}
				if(!student.getNum().equals("") && student.getNum()!=null){
				hql.append(" and s.num like '%"+student.getNum()+"%'");
				}
				hql.append(" and s.sex = '"+student.getSex()+"'");
				Query query = 
						sessionFactory.getCurrentSession().createQuery(hql.toString());
				return query.list();
			}
			public int getPageNum()
			{
				String hql="from Student";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				int temp = query.list().size()/pageSize;
				if(query.list().size()%pageSize !=0){
				temp++;
			}
			return temp;
			}
			}
