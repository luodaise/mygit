<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<jsp:include page="easyui.jsp"></jsp:include>
    <style type="">
	    .input_readonly{
	    	border:0;
	    	pointer-events: none;
	    }
    </style>
    <script>
    	$(function(){
    
    		$("#user_datagrid").datagrid({
   				url : '/student/student_info/list',
   				pagination : true,//分页菜单
   				pageSize : 15,
   				pageList : [ 15, 30, 45 ],
   				fit : true,//自适应大小
   				fitColumns : true,//列多的时候改为false 出现滚动条
   				nowrap : true,//折行，单元格内数据很多时，改为false，能显示单元格全部
   				border : false,
   				idField : 'autoid',//主键标识
   				striped : true, //隔行换色
   				singleSelect : true, //单选
   				rownumbers : true,
   				columns : [ [
   						{
   							field : 'name',
   							title : '姓名',
   							width : 2,
   							align : 'left',
   							halign : 'center'
   						},
   						{
   							field : 'age',
   							title : '年龄',
   							width : 2,
   							align : 'left',
   							halign : 'center'
   						},
   						{
   							field : 'num',
   							title : '学号',
   							width : 2,
   							align : 'left',
   							halign : 'center'
   						},
   						{
   							field : 'autoID',
   							title : '操作',
   							width : 2,
   							align : 'center',
   							formatter : function(value, row, index) {
   								return 	'<a class="detail_btn" href="#" onclick="detail('+ index+ ')"></a>'
   									   +'<a class="edit_btn" href="#" onclick="update('+ index+ ')"></a>'
   									   +'<a class="remove_btn" href="#" onclick="del('+ index + ')"></a>';
   							}
   						} ] ],
   						toolbar : '#user_toolbar',
   						onLoadSuccess : function(data) {
   							$('.edit_btn').linkbutton({
   								iconCls : 'icon-edit',
   								plain : true
   							});
   							$('.remove_btn').linkbutton({
   								iconCls : 'icon-cancel',
   								plain : true
   							});
   							$('.detail_btn').linkbutton({
   								iconCls : 'icon-search',
   								plain : true
   							});
   							$(this).datagrid("resize");
   						}
   			});
    	});
    	
    	var form_url;//全局变量，存储表单url
    	</script>
  </head>
  
  <body>
   <div id="user_datagrid"></div>
  </body>
</html>
