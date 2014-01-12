<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	var process = $("#flow_stepedit_form input[name=processId]").combobox({
		url:"${pageContext.request.contextPath}/flow/process!all.action",
		valueField:"id",
		textField:"name",
		required:true
	});
	var stepType = $("#flow_stepedit_form input[name=type]").combobox({
		valueField:"id",
		textField:"name",
		data:[{
			id:1,
			name:"开始步骤"
		},{
			id:2,
			name:"中间步骤"
		},{
			id:3,
			name:"终结步骤"
		}],
		formatter:function(row){
			var s = "<span ";
			if(row.id == 1){
				s += "style='color:green;'";
			}else if(row.id == 2){
				s += "style='color:blue;'";
			}else if(row.id == 3){
				s += "style='color:darkblue;'";
			}
			s += ">" + row.name + "</span>";
			return s;
		},
		required:true
	});
});
//-->
</script>
<form id="flow_stepedit_form" class="edit" method="POST">
	<dd>
		<label style="width:88px;">所属流程：</label>
		<input type="text" name="processId" style="width:428px;"/>
	</dd>
	<dd>
		<label style="width:88px;">步骤名称：</label>
		<input type="hidden" name="id"/>
		<input type="text" name="name" class="easyui-validatebox" data-options="required:true" style="width:428px;"/>
	</dd>
	<dd>
		<label style="width:88px;">步骤标示：</label>
		<input type="text" name="sign" class="easyui-validatebox" data-options="required:true" style="width:428px;"/>
	</dd>
	<dd>
		<div style="float:left;">
			<label style="width:88px;">步骤类型：</label>
			<input type="text" name="type" style="width:168px;"/>
		</div>
		<div style="float:left;">
			<label style="width:88px;">步骤排序：</label>
			<input type="text" name="orderNo" class="easyui-numberspinner" data-options="value:1,min:0,max:100,required:true,editable:false" style="width:168px;"/>
		</div>
	</dd>
	<dd>
		<label style="width:88px;">访问Url：</label>
		<textarea name="url" rows="2" cols="5" style="width:428px;height:60px;"></textarea>
	</dd>
	<dd>
		<label style="width:88px;">流程描述：</label>
		<textarea name="description" rows="2" cols="5" style="width:428px;height:60px;"></textarea>
	</dd>
</form>