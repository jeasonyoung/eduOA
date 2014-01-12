<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	
});
//-->
</script>
<form id="flow_processedit_form" class="edit" method="POST">
	<dd>
		<label style="width:88px;">流程名称：</label>
		<input type="hidden" name="id"/>
		<input type="text" name="name" class="easyui-validatebox" data-options="required:true" style="width:268px;"/>
	</dd>
	<dd style="marign-top:2px;">
		<label style="width:88px;">流程状态：</label>
		<input type="radio" name="status" value="1" checked="checked">启用 </input>
		<input type="radio" name="status" value="0">禁用 </input>
	</dd>
	<dd>
		<label style="width:88px;">流程描述：</label>
		<textarea name="description" rows="3" cols="5" style="width:268px;height:60px;"></textarea>
	</dd>
</form>