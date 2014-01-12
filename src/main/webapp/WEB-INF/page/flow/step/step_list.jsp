<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//combobox
	var process = $("#flow_step_dg_toolbar input[name=processId]").combobox({
		url:"${pageContext.request.contextPath}/flow/process!all.action",
		valueField:"id",
		textField:"name"
	});
	//list
	var dg = $("#flow_step_dg").datagrid({
		url:"${pageContext.request.contextPath}/flow/step!datagrid.action",
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
			title:"流程名称",
			field:"processName",
			width:50
		},{
			title:"步骤名称",
			field:"name",
			width:50,
			sortable:true
		},{
			title:"步骤标示",
			field:"sign",
			width:110,
			sortable:true
		},{
			title:"步骤类型",
			field:"type",
			align:"center",
			width:30,
			formatter: function(value,row,index){
				if(value == 1) return "<font color='green'>开始步骤</font>";
				if(value == 2) return "<font color='blue'>中间步骤</font>";
				if(value == 3) return "<font color='darkblue'>终结步骤</font>";
				return value;
			},
			sortable:true
		},{
			title:"访问Url",
			field:"url",
			width:128,
			sortable:true
		},{
			title:"步骤描述",
			field:"description",
			width:50,
			sortable:true
		}]],
		toolbar:"#flow_step_dg_toolbar",
		onDblClickRow:function(rowIndex,rowData){
			flow_step_edit_window("编辑流程步骤",rowIndex,rowData);
		}
	});
	//编辑
	function flow_step_edit_window(title,index,row){
		var d = $("<div/>").dialog({
			title:title,
			width:600,
			height:340,
			href:"${pageContext.request.contextPath}/flow/step!edit.action",
			modal:true,
			buttons:[{
				text:"保存",
				iconCls:"icon-save",
				handler:function(){
					$.messager.progress();
					$("#flow_stepedit_form").form("submit",{
						url:"${pageContext.request.contextPath}/flow/step!update.action",
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
				if(row) $("#flow_stepedit_form").form("load",row);
			}
		});
	};
	//search
	flow_step_dg_search = function(){
		dg.datagrid("load",{
			processId:$("#flow_step_dg_toolbar input[name=processId]").val(),
			name:$("#flow_step_dg_toolbar input[name=name]").val()
		});
	};
	//add
	flow_step_dg_add = function(){
		flow_step_edit_window("新增流程步骤",0,null);
	};
	//delete
	flow_step_dg_delete = function(){
		var rows = dg.datagrid("getChecked");
		if(rows && rows.length > 0){
			$.messager.confirm("确认","您是否确认删除选中的数据?",function(r){
				if(!r)return;
				var ids = [];
				for(var i = 0; i < rows.length; i++){
					ids.push(rows[i].id);
				}
				$.ajax({
					url:"${pageContext.request.contextPath}/flow/step!delete.action",
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
<table id="flow_step_dg"></table>
<div id="flow_step_dg_toolbar" style="padding:2px;height:auto;">
	<a href="#" class="easyui-linkbutton" onclick="flow_step_dg_add()" data-options="iconCls:'icon-add',plain:true" style="float:left;">新增</a>
	<span>|</span>
	<a href="#" class="easyui-linkbutton" onclick="flow_step_dg_delete()" data-options="iconCls:'icon-remove',plain:true">删除</a>
	<span style="margin-left:20px;">流程名称:</span>
	<input type="text" name="processId" style="width:198px;"/>
	<span style="margin-left:20px;">步骤名称:</span>
	<input type="text" name="name" style="width:198px;"/>
	<a href="#" class="easyui-linkbutton" onclick="flow_step_dg_search()" data-options="iconCls:'icon-search',plain:true">查询</a>
</div>