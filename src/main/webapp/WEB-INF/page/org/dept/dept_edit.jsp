<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//
	org_deptedit_parentdept = function(row){
		var tree = $("#org_deptedit_form input[name=parentDepartmentId]").combotree({
			url:"${pageContext.request.contextPath}/org/dept!treeOfNoChilds.action?departmentId=" + (row ? row.departmentId : ""),
			onLoadSuccess:function(){
				if(row)$("#org_deptedit_form").form("load",row);
			}
		});
	}
});
//-->
</script>
<form id="org_deptedit_form" class="edit" method="POST">
	<dd>
		<label style="width:88px;">上级组织部门：</label>
		<input type="text" name="parentDepartmentId" style="width:168px;"/>
	</dd>
	<dd style="marign-top:2px;">
		<label style="width:88px;">组织部门名称：</label>
		<input type="hidden" name="departmentId"/>
		<input type="text" name="departmentName" class="easyui-validatebox" data-options="required:true" style="width:168px;"/>
	</dd>
	<dd>
		<label style="width:88px;">排序号：</label>
		<input type="text" name="departmentOrder" value="1" class="easyui-numberspinner" data-options="required:true,min:0,max:100,editable:false" style="width:68px;"/>
	</dd>
</form>