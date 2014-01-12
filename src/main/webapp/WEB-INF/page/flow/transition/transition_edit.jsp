<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//process
	var process = $("#flow_transitionedit_form input[name=processId]").combobox({
		url:"${pageContext.request.contextPath}/flow/process!all.action",
		valueField:"id",
		textField:"name",
		required: true,
		onSelect:function(record){
			flow_transition_edit_load(record.id);
		}
	});
	//fromStep
	var fromStep,toStep;
	flow_transition_edit_load = function(processId){
		loadFromStep(processId,"");
		loadToStep(processId,"");
	};
	//load step
	function loadFromStep(processId,ignoreStepId){
		var step_url = "${pageContext.request.contextPath}/flow/step!all.action?processId="+processId + "&id=" + ignoreStepId;
		if(fromStep){
			fromStep.combobox("reload", step_url);
		}else{
			fromStep = $("#flow_transitionedit_form input[name=fromStepId]").combobox({
				url:step_url,
				valueField:"id",
				textField:"name",
				required: true,
				onSelect:function(record){
					loadToStep(processId,record.id);
				}
			});	
		}
	};
	function loadToStep(processId,ignoreStepId){
		var step_url = "${pageContext.request.contextPath}/flow/step!all.action?processId="+processId + "&id=" + ignoreStepId;
		if(toStep){
			toStep.combobox("reload", step_url);
		}else{
			toStep = $("#flow_transitionedit_form input[name=toStepId]").combobox({
				url:step_url,
				valueField:"id",
				textField:"name",
				required: true,
				onSelect:function(record){
					loadFromStep(processId,record.id);
				}
			});	
		}
	};
});
//-->
</script>
<form id="flow_transitionedit_form" class="edit" method="POST">
	<dd>
		<label style="width:88px;">所属流程：</label>
		<input type="text" name="processId" style="width:198px;"/>
	</dd>
	<dd>
		<label style="width:88px;">变迁名称：</label>
		<input type="hidden" name="id"/>
		<input type="text" name="name" class="easyui-validatebox" data-options="required:true" style="width:198px;"/>
	</dd>
	<dd>
		<label style="width:88px;">前驱步骤：</label>
		<input type="text" name="fromStepId" style="width:198px;"/>
	</dd>
	<dd>
		<label style="width:88px;">后驱步骤：</label>
		<input type="text" name="toStepId" style="width:198px;"/>
	</dd>
	<dd>
		<label style="width:88px;">变迁条件逻辑：</label>
		<input type="radio" name="rule" value="1" checked="checked">并运算 </input>
		<input type="radio" name="rule" value="2">或运算 </input>
	</dd>
</form>