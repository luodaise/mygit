package com.app.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@SuppressWarnings({ "serial", "unused" })
@Entity
@Table(name="student")
public class Student implements Serializable{
	@Id 
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name = "system-uuid",strategy="uuid") 


@Column(length=32) 
private String id;

@Column(length=5) 
private String name;

@Column(length=11) 
private Integer age;

@Column(length=5) 
private String sex;
@Column(length=10) 
private String num;

public String getId() {
return id;
}
public void setId(String id) {
this.id = id;
}
public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}
public Integer getAge() {
return age;
}
public void setAge(Integer age) 
   {
	    this.age = age;
	}
	public String getSex() 
	{
	    return sex;
	}
	public void setSex(String sex) 
	{
	   this.sex = sex;
	}
	public String getNum() 
	{
	   return num;
	}
	public void setNum(String num) 
	{
	   this.num = num;
	}
	}

