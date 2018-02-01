<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String path = request.getContextPath();
%> 
<script type="text/javascript" src="<%=path %>/jquery-1.8.2.min.js"></script> 

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生管理信息</title>
</head>
<body>
   <body> 
<div style="width:50%;height:auto;margin:0 auto;">
<table width="89%">
<tr>
<td style="text-align:center;">
<h3>查找学生</h3> 
</td>
   </tr>
<tr>
<td style="text-align:center;">
<form action="" name="userForm" method="post"> 
姓名：<input type="text" id="name" name="name"/> <br/><br/>
年龄：<input type="text" id="age" name="age"/> <br/><br/>
学号：<input type="text" id="num" name="num"/> <br/><br/>
性别：<select name="sex" style="width:154px;">
<option value="男">男</option>
<option value="女">女</option>
   </select>
<br/><br/>
<input type="button" value="查找" onclick="queryStudent();"> 
<input type="button" value="返回" onclick="goBack();"/> 
</form> 
</td>
</tr>
</table>
</div>
   
   
</body>

<script type="text/javascript">
function queryStudent(){
var form = document.forms[0]; 
form.action = "/student/queryStudent"; 
form.method="post"; 
form.submit(); 
}

function goBack(){
history.go(-1);
}
</script>

</html>