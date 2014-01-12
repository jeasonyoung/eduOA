<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>我的办公室</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui/portal.css" type="text/css"/>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jquery-easyui/jquery.portal.js"></script>
	</head>
	<body> 
		<div id="desk_layout" class="easyui-layout" data-options="fit:true,border:false">
			<!-- left -->
			<div data-options="region:'west',split:true,border:false,title:'我的办公室',href:'desk!left.action'" style="width:168px;overflow:hidden;">
			</div>
			<!-- workspace -->
			<div data-options="region:'center',border:false,href:'desk!workspace.action'" style="overflow:hidden;">
			</div>
		</div>
	</body>
</html>