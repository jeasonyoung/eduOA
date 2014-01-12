<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<script type="text/javascript">
<!--
$(function(){
	//doc flag
	var flag = $("#doc_receiveedit_form input[name=flag]").combobox({
		 valueField:"id",
		 textField:"text",
		 required:true,
		 data:[{
			id:1,
			text:"普通件"
		 }]
	});
	//doc count
	var count = $("#doc_receiveedit_form input[name=receiveCount]").numberspinner({
		 value:1,
		 min: 1,
		 max: 1000,
		 editable: false,
		 required:true
	});
	//upload
	var upload = $("#doc_receiveedit_form_attachment").uploadify({
		swf:"${pageContext.request.contextPath}/uploadify/uploadify.swf",
		uploader:"${pageContext.request.contextPath}/doc/upload.action",
		fileObjName:"attachment",
		buttonText:"上传公文",
		removeTimeout:5,
		multi:false,
		uploadLimit:1024000,//10M
		auto:true,
		debug:true,
		progressData:"all",
		queueID:"doc_receiveedit_form_attachment_progress",
		onUploadError : function(file, errorCode, errorMsg){
			$.messager.show({
				title:"上传文件异常",
				msg:errorCode + "|" + errorMsg
			});
		},
		onUploadSuccess:function(file, data, response){
			if(!data){
				$.messager.show({
					title:"上传文件失败",
					msg:"服务器端发生异常，未反馈数据！"
				});
				return;
			}
			var result = jQuery.parseJSON(data);
			if(result){
				doc_receive_load(result.id,result.name,result.path);
			}
			//console.info(result);
		}
	});
	//
	doc_receive_load = function(attachmentId,attachmentName,attachmentPath){
		$("#doc_receiveedit_form input[name=attachmentId]").val(attachmentId);
		$("#doc_receiveedit_form a").html(attachmentName);
		$("#doc_receiveedit_form a").attr("href","${pageContext.request.contextPath}/" + attachmentPath);
	};
});
//-->
</script>
<form id="doc_receiveedit_form" class="edit" method="POST">
	<dd>
		<div style="float:left;">
			<label style="width:88px;">收文登记号：</label>
			<input type="hidden" name="id"/>
			<input type="text" name="receiveCode" style="width:168px;"/>
		</div>
		<div style="float:left;">
			<label style="width:88px;">文件字号：</label>
			<input type="text" name="code" style="width:168px;"/>
		</div>
	</dd>
	<dd>
		<label style="width:88px;">文件标题：</label>
		<input type="text" name="title" class="easyui-validatebox" data-options="required:true" style="width:428px;"/>
	</dd>
	<dd>
		<div style="float:left;">
			<label style="width:88px;">来文单位：</label>
			<input type="text" name="fromUnit" class="easyui-validatebox" data-options="required:true" style="width:168px;"/>
		</div>
		<div style="float:left;">
			<label style="width:88px;">文件标示：</label>
			<input type="text" name="flag" style="width:168px;"/>
		</div>
	</dd>
	<dd>
		<div style="float:left;">
			<label style="width:88px;">发&nbsp;文&nbsp;号：</label>
			<input type="text" name="issueCode" style="width:168px;"/>
		</div>
		<div style="float:left;">
			<label style="width:88px;">行文登记号：</label>
			<input type="text" name="regCode" style="width:168px;"/>
		</div>
	</dd>
	<dd>
		<label style="width:88px;">收文日期：</label>
		<input type="text" name="receiveDate" class="easyui-datebox" data-options="required:true" style="width:168px;"/>
	</dd>
	<dd>
		<div style="float:left;">
			<label style="width:88px;">收文份数：</label>
			<input type="text" name="receiveCount" style="width:168px;"/>
		</div>
		<div style="float:left;">
			<label style="width:88px;">登&nbsp;记&nbsp;人：</label>
			<input type="text" name="regEmployee" style="width:168px;"/>
		</div>
	</dd>
	<dd>
		<div style="float:left;">
			<label style="width:88px;">来文联系人：</label>
			<input type="text" name="fromLinkEmployee" style="width:168px;"/>
		</div>
		<div style="float:left;">
			<label style="width:88px;">电&nbsp;&nbsp;话：</label>
			<input type="text" name="fromLinkTel" style="width:168px;"/>
		</div>
	</dd>
	<dd>
		<label style="width:88px;">文件内容：</label>
		<textarea name="content" rows="2" cols="5" style="width:428px;height:70px;"></textarea>
	</dd>
	<dd>
		<label style="width:88px;">公文附件：</label>
		<input type="hidden" name="attachmentId"/>
		<a target="_blank"/>
	</dd>
	<dd>
		<div style="float:left;margin-left:90px;">
			<input id="doc_receiveedit_form_attachment" type="file" name="attachment"/>
		</div>
		<div id="doc_receiveedit_form_attachment_progress" style="float:left;margin-left:5px;border:solid 0px red;">
		
		</div>
	</dd>
</form>