<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
				
		<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui/themes/icon.css" type="text/css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui/themes/default/easyui.css" type="text/css"/>
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css" type="text/css"/>
		
		<!--[if lt IE 9]>
			<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jquery-easyui/jquery-1.9.1.min.js"></script>
		<![endif]-->
		<!--[if gte IE 9]><!-->
			<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jquery-easyui/jquery-2.0.min.js"></script>
		<!--<![endif]-->
		
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
		
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/easyui-ext.js"></script>
		
		<decorator:head/>
		<base target="_self"/>
		<title>
			<decorator:title default="教育办公协同系统"/>
		</title>
	</head>
	
	<body class="easyui-layout" data-options="border:false">
		<!--banner-->
		<div data-options="region:'north',href:'index!top.action',border:false"  style="height:40px;overflow:hidden;">
		</div>
		<!-- workspace -->
		<div data-options="region:'center',border:false" style="overflow:hidden;">
			<decorator:body/>
		</div>
		<!-- footer -->
		<div data-options="region:'south',border:false,href:'index!footer.action'" style="height:22px;overflow:hidden;">
		</div> 
	</body>
</html>