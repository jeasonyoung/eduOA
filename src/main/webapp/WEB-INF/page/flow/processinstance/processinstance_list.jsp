<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//process
	var process = $("#flow_processinstance_dg_toolbar input[name=processId]").combobox({
		url:"${pageContext.request.contextPath}/flow/process!all.action",
		valueField:"id",
		textField:"name"
	});
	//list
	var dg = $("#flow_processinstance_dg").datagrid({
		url:"${pageContext.request.contextPath}/flow/processinstance!datagrid.action",
		fit:true,
		fitColumns:true,
		pagination:true,
		pagePosition:"bottom",
		pageSize:20,
		pageList:[20,30,40],
		border:true,
		striped:true,
		idField:"id",
		sortName:"name",
		sortOrder:"asc",
		columns:[[{
			field:"id",
			checkbox:true
		},{
			title:"流程实例名称",
			field:"name",
			width:70,
			sortable:true
		},{
			title:"所属流程",
			field:"processName",
			width:50,
			sortable:true
		},{
			title:"实例状态",
			field:"status",
			width:30,
			align:"center",
			formatter: function(value,row,index){
				if(value == 1) return "<font color='green'>运行</font>";
				if(value == 2) return "<font color='red'>错误</font>";
				if(value == 3) return "<font color='darkblue'>结束 </font>";
				return value;
			},
			sortable:true
		},{
			title:"实例开始时间",
			field:"begin",
			width:40,
			sortable:true
		},{
			title:"实例结束时间",
			field:"end",
			width:40,
			sortable:true
		}]],
		toolbar:"#flow_processinstance_dg_toolbar",
		onDblClickRow:function(rowIndex,rowData){
			flow_transition_edit_window("编辑流程实例",rowIndex,rowData);
		}
	});
	//编辑
	function flow_processinstance_edit_window(title,index,row){
		var d = $("<div/>").dialog({
			title:title,
			width:350,
			height:220,
			href:"${pageContext.request.contextPath}/flow/processinstance!edit.action",
			modal:true,
			buttons:[{
				text:"保存",
				iconCls:"icon-save",
				handler:function(){
					$.messager.progress();
					$("#flow_transitionedit_form").form("submit",{
						url:"${pageContext.request.contextPath}/flow/processinstance!update.action",
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
					$("#flow_processinstanceedit_form").form("load",row);
				}
			}
		});
	};
	//search
	flow_processinstance_dg_search = function(){
		dg.datagrid("load",{
			processId:$("#flow_processinstance_dg_toolbar input[name=processId]").val(),
			name:$("#flow_processinstance_dg_toolbar input[name=name]").val()
		});
	};
	//delete
	flow_processinstance_dg_delete = function(){
		var rows = dg.datagrid("getChecked");
		if(rows && rows.length > 0){
			$.messager.confirm("确认","您是否确认删除选中的数据?",function(r){
				if(!r)return;
				var ids = [];
				for(var i = 0; i < rows.length; i++){
					ids.push(rows[i].id);
				}
				$.ajax({
					url:"${pageContext.request.contextPath}/flow/processinstance!delete.action",
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
<table id="flow_processinstance_dg"></table>
<div id="flow_processinstance_dg_toolbar" style="padding:2px;height:auto;">
	<a href="#" class="easyui-linkbutton" onclick="flow_processinstance_dg_delete()" data-options="iconCls:'icon-remove',plain:true">删除</a>
	<span style="margin-left:20px;">所属流程:</span>
	<input type="text" name="processId" style="width:168px;"/>
	<span style="margin-left:20px;">流程实例名称:</span>
	<input type="text" name="name" style="width:168px;"/>
	<a href="#" class="easyui-linkbutton" onclick="flow_processinstance_dg_search()" data-options="iconCls:'icon-search',plain:true">查询</a>
</div>