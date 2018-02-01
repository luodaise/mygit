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
   <div style="width:50%;height:auto;margin:0 auto;">
       <table style="width:89%">
          <tr>
             <td style="text-align:center;">
             <h3>编辑学生</h3>
             <td/>
          <tr/>
          <tr>
              <td style="text-align:center;">
                 <form action="" name="userForm" method="post">
                 <input type="hidden" name="id" value="${student.id }"/>
                 姓名:<input type="text" id="name" name="name" value="${student.name }"/><br/><br/>
                 年龄:<input type="text" id="age" name="age" value="${student.age }"/><br/><br/>
                 学号:<input type="text" id="num" name="num" value="${student.num }"/><br/><br/>
                 性别:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" id="man" value="男"/> 男
                         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" id="women" value="女"/>女<br/><br/>
                         <input type="button" value="保存" onclick="addStudent();"/>
                         <input type="button" value="返回" onclick="goBack()"/>

                 </form>
              <td/>
          <tr/>
       </table>
   </div>
</body>

 <script  type="text/javascript">
 
       $(document).ready(function(){
         var sex="${student.sex}"; 
         if(sex=="女"){
          $("women").attr("checked","checked");
         }else if (sex=="男"){
          $("#man").attr("checked","checked");
         }
        });
 </script>
<script type="text/javascript">
    function validator () {
     var name = $("#name").val();
     var age =$("#age").val();
     var num=$("#num").val();
     if(name==null||name==""){
     alert("请输入姓名");
     return false;
     }
     if(name.length>5){
      alert("姓名太长");
      return false;
     }
     if(age==null||age==""){
      alert("请输入年龄");
      return false;
     }
     if(!(age>0&&age<100)){
      alert("请输入正确的年龄");
      return false;
     }
     if(num==null||num==""){
     alert("请输入学号");
     return false;
     }
     if(num.length>5){
      alert("学号太长");
      return false;
     }
     return true;
    }
    
    function addStudent(){
     if(validator()){
       var form =document.forms[0];
       form.action="/student/addaStudent";
       form.method="post";
       form.submit();
     }
    }
    
    function goBack(){
     history.go(-1);
    }
</script>









</html>