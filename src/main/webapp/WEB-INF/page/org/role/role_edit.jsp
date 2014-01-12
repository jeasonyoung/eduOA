<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="org_roleedit_form" class="edit" method="POST">
	<dd style="marign-top:2px;">
		<label style="width:88px;">角色名称：</label>
		<input type="hidden" name="id"/>
		<input type="text" name="name" class="easyui-validatebox" data-options="required:true" style="width:168px;"/>
	</dd>
	<dd>
		<label style="width:88px;">角色描述：</label>
		<textarea name="description" rows="3" style="width:168px;"/>
	</dd>
</form>