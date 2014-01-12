<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	
});
//-->
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" style="padding-top:15px;padding-left:20px;">
		<span>协同办公管理系统</span>
	</div>
	<div data-options="region:'east',border:false" style="width:400px;padding-top:5px;">
		<a href="index.action?systemId=ipower_edu_oa_desk_0000" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">我的办公室</a>
		<a href="index.action?systemId=ipower_edu_oa_doc_0000" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">公文管理</a>
		<!-- <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">日常事务</a> -->
		<a href="index.action?systemId=ipower_edu_flow_0000" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">流程管理</a>
		<span>|</span>
		<a href="#" class="easyui-linkbutton" data-options="plain:true">退出</a>
	</div>
</div>