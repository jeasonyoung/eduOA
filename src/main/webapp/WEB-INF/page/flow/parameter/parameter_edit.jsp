<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<script type="text/javascript">
<!--
$(function(){
	//process
	var process = $("#flow_parameteredit_form input[name=processId]").combobox({
		url:"${pageContext.request.contextPath}/flow/process!all.action",
		valueField:"id",
		textField:"name",
		required: true,
		onSelect:function(record){
			flow_parameteredit_loadSteps(record.id);
		}
	});
	//step
	var step;
	flow_parameteredit_loadSteps = function(processId){
		if(step){
			step.combobox("reload","${pageContext.request.contextPath}/flow/step!all.action?processId="+processId);
		}else{
			step = $("#flow_parameteredit_form input[name=stepId]").combobox({
				url:"${pageContext.request.contextPath}/flow/step!all.action?processId="+processId,
				valueField:"id",
				textField:"name",
				required: true
			});	
		}
	};
});
//-->
</script>
<form id="flow_parameteredit_form" class="edit" method="POST">
	<dd>
		<div style="float:left;">
			<label style="width:88px;">所属流程：</label>
			<input type="text" name="processId" style="width:168px;"/>
	 	</div>
	 	<div style="float:left;">
			<label style="width:88px;">所属步骤：</label>
			<input type="text" name="stepId" style="width:168px;"/>
		</div>
	</dd>
	<dd>
		<label style="width:88px;">参数名称：</label>
		<input type="hidden" name="id"/>
		<input type="text" name="name" class="easyui-validatebox" data-options="required:true" style="width:428px;"/>
	</dd>
	<dd>
		<label style="width:88px;">默认值：</label>
		<input type="text" name="value" style="width:428px;"/>
	</dd>
	<dd>
		<label style="width:88px;">流程描述：</label>
		<textarea name="description" rows="2" cols="5" style="width:428px;height:60px;"></textarea>
	</dd>
</form>