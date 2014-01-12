<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//process
	var process = $("#flow_parameter_dg_toolbar input[name=processId]").combobox({
		url:"${pageContext.request.contextPath}/flow/process!all.action",
		valueField:"id",
		textField:"name",
		onSelect:function(record){
			loadSteps(record.id);
		}
	});
	//step
	var step = $("#flow_parameter_dg_toolbar input[name=stepId]").combobox({
		url:"${pageContext.request.contextPath}/flow/step!all.action?processId=",
		valueField:"id",
		textField:"name"
	});	
	function loadSteps(processId){
		if(step){
			step.combobox("reload","${pageContext.request.contextPath}/flow/step!all.action?processId="+processId);
		}
	};
	//list
	var dg = $("#flow_parameter_dg").datagrid({
		url:"${pageContext.request.contextPath}/flow/parameter!datagrid.action",
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
			title:"所属流程",
			field:"processName",
			width:70
		},{
			title:"所属步骤",
			field:"stepName",
			width:60
		},{
			title:"参数名称",
			field:"name",
			width:50,
			sortable:true
		},{
			title:"默认值",
			field:"value",
			width:80,
			sortable:true
		},{
			title:"参数描述",
			field:"description",
			width:80,
			sortable:true
		}]],
		toolbar:"#flow_parameter_dg_toolbar",
		onDblClickRow:function(rowIndex,rowData){
			flow_parameter_edit_window("编辑流程步骤参数",rowIndex,rowData);
		}
	});
	//编辑
	function flow_parameter_edit_window(title,index,row){
		var d = $("<div/>").dialog({
			title:title,
			width:600,
			height:260,
			href:"${pageContext.request.contextPath}/flow/parameter!edit.action",
			modal:true,
			buttons:[{
				text:"保存",
				iconCls:"icon-save",
				handler:function(){
					$.messager.progress();
					$("#flow_parameteredit_form").form("submit",{
						url:"${pageContext.request.contextPath}/flow/parameter!update.action",
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
					flow_parameteredit_loadSteps(row.processId);
					$("#flow_parameteredit_form").form("load",row);
				}
			}
		});
	};
	//search
	flow_parameter_dg_search = function(){
		dg.datagrid("load",{
			processId:$("#flow_parameter_dg_toolbar input[name=processId]").val(),
			stepId:$("#flow_parameter_dg_toolbar input[name=stepId]").val(),
			name:$("#flow_parameter_dg_toolbar input[name=name]").val()
		});
	};
	//add
	flow_parameter_dg_add = function(){
		flow_parameter_edit_window("新增流程步骤参数",0,null);
	};
	//delete
	flow_parameter_dg_delete = function(){
		var rows = dg.datagrid("getChecked");
		if(rows && rows.length > 0){
			$.messager.confirm("确认","您是否确认删除选中的数据?",function(r){
				if(!r)return;
				var ids = [];
				for(var i = 0; i < rows.length; i++){
					ids.push(rows[i].id);
				}
				$.ajax({
					url:"${pageContext.request.contextPath}/flow/parameter!delete.action",
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
<table id="flow_parameter_dg"></table>
<div id="flow_parameter_dg_toolbar" style="padding:2px;height:auto;">
	<a href="#" class="easyui-linkbutton" onclick="flow_parameter_dg_add()" data-options="iconCls:'icon-add',plain:true" style="float:left;">新增</a>
	<span>|</span>
	<a href="#" class="easyui-linkbutton" onclick="flow_parameter_dg_delete()" data-options="iconCls:'icon-remove',plain:true">删除</a>
	<span style="margin-left:20px;">流程名称:</span>
	<input type="text" name="processId" style="width:168px;"/>
	<span style="margin-left:20px;">步骤名称:</span>
	<input type="text" name="stepId" style="width:168px;"/>
	<span style="margin-left:20px;">参数名称:</span>
	<input type="text" name="name" style="width:168px;"/>
	<a href="#" class="easyui-linkbutton" onclick="flow_parameter_dg_search()" data-options="iconCls:'icon-search',plain:true">查询</a>
</div>