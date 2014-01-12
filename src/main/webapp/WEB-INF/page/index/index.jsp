<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><s:property value="systemName"/></title>
		<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui/portal.css" type="text/css"/> --%>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/uploadify/uploadify.css" type="text/css"/>
		<%-- <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jquery-easyui/jquery.portal.js"></script> --%>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/uploadify/jquery.uploadify.min.js"></script>
	</head>
	<body>
		<div class="easyui-layout" data-options="fit:true,border:false">
			<!-- left menu -->
			<div title="<s:property value='systemName'/>" data-options="region:'west',split:true,href:'index!leftmenu.action'" style="width:200px;overflow:hidden;">
			</div>
			<!-- workspace -->
			<div data-options="region:'center',border:false,href:'index!workspace.action'" style="overflow:hidden;">
			</div>
		</div>
	</body>
</html>