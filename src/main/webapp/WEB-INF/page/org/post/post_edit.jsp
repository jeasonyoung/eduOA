<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="org_postedit_form" class="edit" method="POST">
	<dd style="marign-top:2px;">
		<label style="width:88px;">职务岗位名称：</label>
		<input type="hidden" name="id"/>
		<input type="text" name="name" class="easyui-validatebox" data-options="required:true" style="width:168px;"/>
	</dd>
	<dd>
		<label style="width:88px;">职务岗位级别：</label>
		<input type="text" name="level" value="1" class="easyui-numberspinner" data-options="required:true,min:0,max:100,editable:false" style="width:68px;"/>
	</dd>
</form>