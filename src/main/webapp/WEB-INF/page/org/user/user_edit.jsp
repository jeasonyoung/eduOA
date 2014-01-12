<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	var dept = $("#org_useredit_form input[name=departmentId]").combotree({
		url:"${pageContext.request.contextPath}/org/dept!tree.action",
		required:true
	});
	var post = $("#org_useredit_form input[name=postId]").combobox({
		url:"${pageContext.request.contextPath}/org/post!all.action",
		required:true,
		textField:"name",
		valueField:"id",
		formatter: function(row){
			return row.name +="["+row.level+"]";
		}
	});
});
//-->
</script>
<form id="org_useredit_form" class="edit" method="POST">
	<dd>
		<div style="float:left;">
			<label>用户姓名：</label>
			<input type="hidden" name="id"/>
			<input type="text" name="name" class="easyui-validatebox" data-options="required:true" style="width:158px;"/>	
		</div>
		<div style="float:left;margin-left:15px;">
			<label>用户账号：</label>
			<input type="text" name="account" class="easyui-validatebox" data-options="required:true" style="width:158px;"/>
		</div>
	</dd>
	<dd>
		<div style="float:left;">
			<label>组织部门：</label>
			<input type="text" name="departmentId" style="width:160px;"/>
		</div>
		<div style="float:left;margin-left:15px;">
			<label>职务岗位：</label>
			<input type="text" name="postId" style="width:158px;"/>
		</div>
	</dd>
	<dd>
		<div style="float:left;">
			<label>手机号码：</label>
			<input type="text" name="mobile" style="width:158px;"/>
		</div>
		<div style="float:left;margin-left:15px;">
			<label>电子邮箱：</label>
			<input type="text" name="email" class="easyui-validatebox" data-options="validType:'email'" style="width:158px;"/>
		</div>
	</dd>
	<dd>
		<div style="float:left;">
			<label>身份证号：</label>
			<input type="text" name="idCard" style="width:158px;"/>
		</div>
		<div style="float:left;margin-left:15px;">
			<label>出生日期：</label>
			<input type="text" name="birthday" class="easyui-datebox" data-options="editable:false" style="width:158px;"/>
		</div>
	</dd>
	<dd>
		<div style="float:left;">
			<label>性&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
			<span style="float:left;display:block;width:158px;">
				<input type="radio" name="gender" value="1" checked="checked">男</input>
				<input type="radio" name="gender" value="2">女</input>
			</span>
		</div>
		<div style="float:left;margin-left:17px;">
			<label>用户状态：</label>
			<span style="float:left;display:block;width:158px;">
				<input type="radio" name="status" value="1" checked="checked">启用</input>
				<input type="radio" name="status" value="0">禁用</input>
			</span>
		</div>
	</dd>
	<dd>
		<div style="float:left;">
			<label>密码设置：</label>
			<input type="password" name="password" style="width:158px;"/>
		</div>
		<div style="float:left;margin-left:15px;">
			<label>重复密码：</label>
			<input type="password" name="repassword" class="easyui-validatebox" validType="equals['#org_useredit_form input[name=password]']" style="width:158px;"/>
		</div>
	</dd>
</form>