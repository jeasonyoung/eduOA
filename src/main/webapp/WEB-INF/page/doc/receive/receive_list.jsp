<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	var search = $("#doc_receive_dg_search").searchbox({
		menu:"#doc_receive_dg_searchoptions",
		prompt:"请输入查询值！",
		searcher:function(value,name){
			if(value == "")return;
			dg.datagrid("load",{
				searchName:name,
				searchValue:value
			});
		}
	});
	//list
	var dg = $("#doc_receive_dg").datagrid({
		url:"${pageContext.request.contextPath}/doc/receive!datagrid.action",
		fit:true,
		fitColumns:true,
		pagination:true,
		rownumbers:true,
		pagePosition:"bottom",
		pageSize:20,
		pageList:[20,30,40],
		border:true,
		striped:true,
		idField:"id",
		sortName:"title",
		sortOrder:"asc",
		columns:[[{
			field:"id",
			checkbox:true
		},{
			title:"登记编号",
			field:"receiveCode",
			width:50,
			sortable:true
		},{
			title:"公文标题",
			field:"title",
			width:198,
			sortable:true
		},{
			title:"来/发文单位",
			field:"fromUnit",
			width:128,
			sortable:true
		},{
			title:"来/发文日期",
			field:"receiveDate",
			formatter: function(value,row,index){
				if(value && value.length >= 10) return value.substring(0,10);
				return value;
			},
			width:50,
			sortable:true
		}]],
		toolbar:"#doc_receive_dg_toolbar",
		onDblClickRow:function(rowIndex,rowData){
			doc_receive_edit_window("编辑收发公文",rowIndex,rowData);
		}
	});
	//edit
	function doc_receive_edit_window(title,index,row){
		var d = $("<div/>").dialog({
			title:title,
			width:600,
			height:440,
			href:"${pageContext.request.contextPath}/doc/receive!edit.action",
			modal:true,
			buttons:[{
				text:"保存",
				iconCls:"icon-save",
				handler:function(){
					$.messager.progress();
					$("#doc_receiveedit_form").form("submit",{
						url:"${pageContext.request.contextPath}/doc/receive!update.action",
						onSubmit: function(){
							var isValid = $(this).form("validate");
							if (!isValid)$.messager.progress("close");
							return isValid;
						},
						success:function(data){
							$.messager.progress("close");
							var data = jQuery.parseJSON(data);
							if(data.success){
								dg.datagrid(row ? "updateRow" : "insertRow",{
									index:index,
									row:data.data
								});
								d.dialog("close");
							}else{
								$.messager.show({
									title:"保存异常",
									msg:data.msg
								});
							}
						}
					});
				}
			},{
				text:"关闭",
				iconCls:"icon-cancel",
				handler:function(){
					d.dialog("close");
				}
			}],
			onClose:function(){
				$(this).dialog("destroy");
			},
			onLoad:function(){
				if(row){
					doc_receive_load(row.attachmentId,row.attachmentName,row.attachmentPath);
					if(row.receiveDate && row.receiveDate.length >= 10) 
						row.receiveDate = row.receiveDate.substring(0,10);
					$("#doc_receiveedit_form").form("load",row);
				}
			}
		});
	};
	//add
	doc_receive_dg_add = function(){
		doc_receive_edit_window("新增收发公文",0,null);
	};
	//delete
	doc_receive_dg_delete = function(){
		var rows = dg.datagrid("getChecked");
		if(rows && rows.length > 0){
			$.messager.confirm("确认","您是否确认删除选中的数据?",function(r){
				if(!r)return;
				var ids = [];
				for(var i = 0; i < rows.length; i++){
					ids.push(rows[i].id);
				}
				$.ajax({
					url:"${pageContext.request.contextPath}/doc/receive!delete.action",
					type:"POST",
					data:{
						id:ids.join("|")
					},
					dataType:"json",
					success:function(data,textStatus){
						if(data.success){
							dg.datagrid("load");
							dg.datagrid("unselectAll");
						}else{
							$.messager.show({
								title:"提示",
								msg:data.msg
							});
						}
					}
				});
			});
		}else{
			$.messager.alert("提示","未选中须删除的数据！");
		}
	};
});
//-->
</script>
<table id="doc_receive_dg"></table>
<div id="doc_receive_dg_toolbar" style="padding:2px;height:auto;">
	<div style="float:left;">
		<a href="#" class="easyui-linkbutton" onclick="doc_receive_dg_add()" data-options="iconCls:'icon-add',plain:true" style="float:left;">新增</a>
	</div>
	<div style="float:left;">|</div>
	<div style="float:left;">
		<a href="#" class="easyui-linkbutton" onclick="doc_receive_dg_delete()" data-options="iconCls:'icon-remove',plain:true">删除</a>
 	</div>
 	 
	<input id="doc_receive_dg_search" style="width:368px;"/>
	<div id="doc_receive_dg_searchoptions" style="width:128px;">
		<div name="title">公文标题</div>
		<div name="receiveCode">登记编号</div>
		<div name="code">文件字号</div>
		<div name="issueCode">发文号</div>
		<div name="regCode">行文登记号</div>
		<div name="regEmployee">登记人</div>
		<div name="fromUnit">来/发文单位</div>
		<div name="fromLinkEmployee">来/发文联系人</div>
		<div name="fromLinkTel">电话</div>
	</div> 
</div>