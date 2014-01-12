<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//process
	var process = $("#flow_conditionedit_form input[name=processId]").combobox({
		url:"${pageContext.request.contextPath}/flow/process!all.action",
		valueField:"id",
		textField:"name",
		required: true,
		onSelect:function(record){
			flow_condition_edit_load(record.id,null);
		}
	});
	//transition
	var trans,fromParam;
	flow_condition_edit_load = function(processId,transitionId){
		var trans_url = "${pageContext.request.contextPath}/flow/transition!all.action?processId=" + processId;
		if(trans){
			trans.combobox("reload", trans_url);
		}else{
			trans = $("#flow_conditionedit_form input[name=transitionId]").combobox({
				url:trans_url,
				valueField:"id",
				textField:"name",
				required: true,
				onLoadSuccess:function(){
					if(transitionId){
						if(process)process.combobox("readonly",true);
						$(this).combobox("select",transitionId);
						$(this).combobox("readonly",true);
						if(fromParam)fromParam.combobox("readonly",true);
					}
				},
				onSelect:function(record){
					loadParams(record.fromStepId);
				}
			});	
		}
	};
	//
	function loadParams(fromStepId){
		var f_url = "${pageContext.request.contextPath}/flow/parameter!all.action?stepId=" + fromStepId;
		if(fromParam){
			fromParam.combobox("reload", f_url);
		}else{
			fromParam = $("#flow_conditionedit_form input[name=fromParameterId]").combobox({
				url:f_url,
				valueField:"id",
				textField:"name",
				required: true
			});	
		}
	};
});
//-->
</script>
<form id="flow_conditionedit_form" class="edit" method="POST">
	<dd>
		<label style="width:88px;">所属流程：</label>
		<input type="hidden" name="id"/>
		<input type="text" name="processId" style="width:198px;"/>
	</dd>
	<dd>
		<label style="width:88px;">所属变迁名称：</label>
		<input type="text" name="transitionId" style="width:198px;"/>
	</dd>
	<dd>
		<label style="width:88px;">前驱变迁参数：</label>
		<input type="text" name="fromParameterId" style="width:198px;"/>
	</dd>
	<dd>
		<label style="width:88px;">变迁条件：</label>
		<input type="text" name="compareValue" class="easyui-validatebox" data-options="required:true" style="width:198px;"/>
	</dd>
</form>