package com.app.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.entity.Student;
import com.app.service.StudentManager;

@Controller("studentController")
@Scope("prototype")
@RequestMapping(value="/student") 
public class StudentController
{
    
    private StudentManager studentManager;
    
    @RequestMapping("/allStudent")
    public String getAllUser(String page,HttpServletRequest request)
    {
    	if(page==null)
    	{
    		page=1+"";
    	}
    	request.setAttribute("page", page);
    	request.setAttribute("page", page);
    	request.setAttribute("pageNum",studentManager.getPageNum());
    	request.setAttribute("studentList", studentManager.allStudent(page));
    	return "/allStudent";

    }
    
    @RequestMapping("/delStudent")
    public void delStudent(String id,HttpServletResponse response)
    {
    	String result="{\"result\":\"error\"}";
    	if(studentManager.deleteStudent(id))
    	{
    		result="{\"result\":\"success\"}";
    	}
    	response.setContentType("application/jason");
    	try
		{
			PrintWriter out=response.getWriter();
			out.write(result);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
    }
    @RequestMapping("/toAddStudent")
    public String getAddStudentPage(){
    return "/addStudent";
    }

    
    @RequestMapping("/addStudent")
    public String addStudent(Student student,HttpServletRequest request)throws Exception
    {
    	if(student.getId()!="" && student.getId()!=null)
    	{
    		studentManager.updateStudent(student);
    	}else{
    		studentManager.addStudent(student);
    	}
    	return "redirect:/allStudent";
    }
    
    @RequestMapping("/editStudent")
    public String editStudent(String id,HttpServletRequest request)
    {
    	Student student=studentManager.findById(id);
    	request.setAttribute("student", student);
    	return "/editStudent";
    }
    
    @RequestMapping("/toQueryStudent")
    public String getQueryStudent()
    {
    	return "/queryStudent";
    }
    
    @RequestMapping("/queryStudent")
    public String queryStudent(Student student,HttpServletRequest request)
    {
    	request.setAttribute("studentList", studentManager.queryStudent(student));
    	return "/queryResult";
    }
    
    @RequestMapping("/nextPage")
    public String nextPage(String page,HttpServletRequest request)
    {
    	int curPage=Integer.parseInt(page);
    	request.setAttribute("page", curPage+1);
    	request.setAttribute("pageNum", studentManager.getPageNum());
    	request.setAttribute("studentList", studentManager.allStudent(curPage+1+""));
    	return "/allStudent";
    }
    
    @RequestMapping("/prevPage")
    public String prevPage(String page,HttpServletRequest request)
    {
    	int curPage=Integer.parseInt(page);
    	request.setAttribute("page", curPage-1);
    	request.setAttribute("pageNum", studentManager.getPageNum());
    	request.setAttribute("studentList", studentManager.allStudent(curPage-1+""));
    	return "/allStudent";
    }
    

}
