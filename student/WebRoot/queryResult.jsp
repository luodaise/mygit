<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path%>/jquery-1.8.2.min.js"></script> 

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生管理信息</title>
<style>
table {
border:1px solid #606060;
}
table td{
border-left:1px solid #606060;
border-top:1px solid #606060;
text-align:center;
}
table th{
border-left:1px solid #606060;
border-top:1px solid #606060;
}

.addStudent a{
color:#000000;
}
.addStudent a:hover{
color:#2828FF;
font-weight:bold;
}
</style>
<script type="text/javascript">
       function del(id,name){
        var msg="删除后将不能恢复，确认删除【"+name+"】吗?";
        if(confirm(msg)==true){
           $.get("/student/delStudent?id=" +id,function(data){
             if("success"==data.result){
              alert("删除成功");
              window.location.reload();
             }else{
              alert("删除失败");
             }
           });
        }else{
         return false;
        }
       }
</script>

</head>
<body>
    <div style="width:90%;height:auto;margin:0 auto;">
        <h3 class="addStudent">
         <a href="/student/toAddStudent">添加学生</a>
           <a href="/student/toQueryStudent">查找学生</a>
           <a href="/student/allStudent?page=1">返回列表</a>
        </h3>
        
        <table style="width:89%;">
            <tbody>
                  <tr>
                     <th>姓名</th>
                     <th>年龄</th>
                     <th>性别</th>
                     <th>学号</th>
                     <th>操作</th>
                  </tr>
                  <c:if test="${!empty studentList }">
                  <c:forEach  items="${studentList }" var="student">
                  <tr>
                    <td >${student.name}</td> 
					<td>${student.age}</td> 
					<td>${student.sex}</td> 
					<td>${student.num}</td> 
					<td> 
					<a href="/student/editStudent?id=${student.id}">编辑</a> 
					<a href="javascript:del('${student.id}','${student.name }')">删除</a> 
					</td> 
                    
                  </tr>
                  </c:forEach> 
                  </c:if> 
                  
            </tbody>
        </table>
    </div>
</body>
</html>