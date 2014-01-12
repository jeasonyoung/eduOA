<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript" charset="utf-8">
<!--
$(function(){
	//左边菜单树
	$("#index_left_menu").tree({
		url:"${pageContext.request.contextPath}/menu.action?systemId=<s:property value='systemId'/>",
		lines:true,
		fit:true,
		animate:true,
		onClick:function(node){
			index_workspace_addWorkspaceTab(node);
		},
		onDbClick:function(node){
			if(node.state == "closed"){
				$(this).tree("expand",node.target);
			}else{
				$(this).tree("collapse",node.target);
			}
		}
	});
});
//-->
</script>
<div class="easyui-accordion" data-options="fit:true,border:false">
	<div title="功能导航" data-options="border:false" style="overflow:auto;padding:1px;">
		<ul id="index_left_menu"/>
	</div>
	<div title="在线人员">
		ttt
	</div>
</div>