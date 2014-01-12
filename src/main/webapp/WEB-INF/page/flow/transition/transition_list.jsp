<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//process
	var process = $("#flow_transition_dg_toolbar input[name=processId]").combobox({
		url:"${pageContext.request.contextPath}/flow/process!all.action",
		valueField:"id",
		textField:"name"
	});
	//list
	var dg = $("#flow_transition_dg").datagrid({
		url:"${pageContext.request.contextPath}/flow/transition!datagrid.action",
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
			width:50
		},{
			title:"步骤变迁名称",
			field:"name",
			width:70,
			sortable:true
		},{
			title:"变迁逻辑",
			field:"rule",
			width:30,
			align:"center",
			formatter: function(value,row,index){
				if(value == 1) return "<font color='green'>并运算</font>";
				if(value == 2) return "<font color='darkblue'>或运算 </font>";
				return value;
			},
			sortable:true
		},{
			title:"前驱步骤",
			field:"fromStepName",
			width:60,
			sortable:true
		},{
			title:"后驱驱步骤",
			field:"toStepName",
			width:60,
			sortable:true
		}]],
		toolbar:"#flow_transition_dg_toolbar",
		onDblClickRow:function(rowIndex,rowData){
			flow_transition_edit_window("编辑流程步骤变迁",rowIndex,rowData);
		}
	});
	//编辑
	function flow_transition_edit_window(title,index,row){
		var d = $("<div/>").dialog({
			title:title,
			width:350,
			height:220,
			href:"${pageContext.request.contextPath}/flow/transition!edit.action",
			modal:true,
			buttons:[{
				text:"保存",
				iconCls:"icon-save",
				handler:function(){
					$.messager.progress();
					$("#flow_transitionedit_form").form("submit",{
						url:"${pageContext.request.contextPath}/flow/transition!update.action",
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
					flow_transition_edit_load(row.processId);
					$("#flow_transitionedit_form").form("load",row);
				}
			}
		});
	};
	//search
	flow_transition_dg_search = function(){
		dg.datagrid("load",{
			processId:$("#flow_transition_dg_toolbar input[name=processId]").val(),
			name:$("#flow_transition_dg_toolbar input[name=name]").val()
		});
	};
	//add
	flow_transition_dg_add = function(){
		flow_transition_edit_window("新增流程步骤变迁",0,null);
	};
	//delete
	flow_transition_dg_delete = function(){
		var rows = dg.datagrid("getChecked");
		if(rows && rows.length > 0){
			$.messager.confirm("确认","您是否确认删除选中的数据?",function(r){
				if(!r)return;
				var ids = [];
				for(var i = 0; i < rows.length; i++){
					ids.push(rows[i].id);
				}
				$.ajax({
					url:"${pageContext.request.contextPath}/flow/transition!delete.action",
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
<table id="flow_transition_dg"></table>
<div id="flow_transition_dg_toolbar" style="padding:2px;height:auto;">
	<a href="#" class="easyui-linkbutton" onclick="flow_transition_dg_add()" data-options="iconCls:'icon-add',plain:true" style="float:left;">新增</a>
	<span>|</span>
	<a href="#" class="easyui-linkbutton" onclick="flow_transition_dg_delete()" data-options="iconCls:'icon-remove',plain:true">删除</a>
	<span style="margin-left:20px;">所属流程:</span>
	<input type="text" name="processId" style="width:168px;"/>
	<span style="margin-left:20px;">变迁名称:</span>
	<input type="text" name="name" style="width:168px;"/>
	<a href="#" class="easyui-linkbutton" onclick="flow_transition_dg_search()" data-options="iconCls:'icon-search',plain:true">查询</a>
</div>