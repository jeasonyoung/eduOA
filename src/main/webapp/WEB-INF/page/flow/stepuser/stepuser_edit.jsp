<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//process
	var process = $("#flow_stepuseredit_form input[name=processId]").combobox({
		url:"${pageContext.request.contextPath}/flow/process!all.action",
		valueField:"id",
		textField:"name",
		required: true,
		onSelect:function(record){
			flow_stepuseredit_loadSteps(record.id);
		}
	});
	//step
	var step;
	flow_stepuseredit_loadSteps = function(processId){
		if(step){
			step.combobox("reload","${pageContext.request.contextPath}/flow/step!all.action?processId="+processId);
		}else{
			step = $("#flow_stepuseredit_form input[name=stepId]").combobox({
				url:"${pageContext.request.contextPath}/flow/step!all.action?processId="+processId,
				valueField:"id",
				textField:"name",
				required: true
			});	
		}
	};
	//users
	var users = $("#flow_stepuseredit_form input[name=userId]").combobox({
		url:"${pageContext.request.contextPath}/org/user!all.action",
		valueField:"id",
		textField:"name",
		required: true,
		onSelect:function(record){
			$("#flow_stepuseredit_form input[name=userName]").val(record.name);
		}
	});
});
//-->
</script>
<form id="flow_stepuseredit_form" class="edit" method="POST">
	<dd>
		<label style="width:88px;">所属流程：</label>
		<input type="text" name="processId" style="width:198px;"/>
	</dd>
	<dd>
		<label style="width:88px;">所属步骤：</label>
		<input type="text" name="stepId" style="width:198px;"/>
	</dd>
	<dd>
		<label style="width:88px;">用户类型：</label>
		<input type="radio" name="type" value="1" checked="checked">待办 </input>
		<input type="radio" name="type" value="2">待阅 </input>
	</dd>
	<dd>
		<label style="width:88px;">用&nbsp;户&nbsp;名：</label>
		<input type="hidden" name="id"/>
		<input type="hidden" name="userName"/>
		<input type="text" name="userId" style="width:198px;"/>
	</dd>
</form>