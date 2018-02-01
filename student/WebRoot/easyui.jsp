<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

 
<%
   //设置无缓存
   response.setHeader("progma","no-cache");   
   response.setHeader("Cache-Control","no-cache");   
%> 
<c:set var="ctx" value="<%=request.getContextPath() %>"/>

<script type="text/javascript">
var xtlh = xtlh || {};
</script>
<script type="text/javascript" src="${ctx }/js/jquery-easyui-1.4/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-easyui-1.4/detail-datagrid.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-easyui-1.4/jquery.edatagrid.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-easyui-1.4/util.js"></script>


<link rel="stylesheet" href="${ctx }/js/jquery-easyui-1.4/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="${ctx }/js/jquery-easyui-1.4/themes/icon.css" type="text/css"></link>

