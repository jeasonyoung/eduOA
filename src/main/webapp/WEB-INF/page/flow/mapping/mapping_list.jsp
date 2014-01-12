<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//process
	var process = $("#flow_mapping_dg_toolbar input[name=processId]").combobox({
		url:"${pageContext.request.contextPath}/flow/process!all.action",
		valueField:"id",
		textField:"name"
	});
	//list
	var dg = $("#flow_mapping_dg").datagrid({
		url:"${pageContext.request.contextPath}/flow/mapping!datagrid.action",
		fit:true,
		fitColumns:true,
		pagination:true,
		pagePosition:"bottom",
		pageSize:20,
		pageList:[20,30,40],
		border:true,
		striped:true,
		idField:"id",
		sortName:"processName",
		sortOrder:"asc",
		columns:[[{
			field:"id",
			checkbox:true
		},{
			title:"所属流程",
			field:"processName",
			width:50,
			sortable:true
		},{
			title:"步骤变迁名称",
			field:"transitionName",
			width:70,
			sortable:true
		},{
			title:"前驱步骤参数",
			field:"fromParameterName",
			width:60,
			sortable:true
		},{
			title:"映射后驱驱步骤参数",
			field:"toParameterName",
			width:60,
			sortable:true
		}]],
		toolbar:"#flow_mapping_dg_toolbar",
		onDblClickRow:function(rowIndex,rowData){
			flow_mapping_edit_window("编辑流程步骤变迁参数映射",rowIndex,rowData);
		}
	});
	//编辑
	function flow_mapping_edit_window(title,index,row){
		var d = $("<div/>").dialog({
			title:title,
			width:380,
			height:200,
			href:"${pageContext.request.contextPath}/flow/mapping!edit.action",
			modal:true,
			buttons:[{
				text:"保存",
				iconCls:"icon-save",
				handler:function(){
					$.messager.progress();
					$("#flow_mappingedit_form").form("submit",{
						url:"${pageContext.request.contextPath}/flow/mapping!update.action",
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
					flow_mapping_edit_load(row.processId,row.transitionId);
					$("#flow_mappingedit_form").form("load",row);
				}
			}
		});
	};
	//search
	flow_mapping_dg_search = function(){
		dg.datagrid("load",{
			processId:$("#flow_mapping_dg_toolbar input[name=processId]").val(),
			transitionName:$("#flow_mapping_dg_toolbar input[name=transitionName]").val()
		});
	};
	//add
	flow_mapping_dg_add = function(){
		flow_mapping_edit_window("新增流程步骤变迁参数映射",0,null);
	};
	//delete
	flow_mapping_dg_delete = function(){
		var rows = dg.datagrid("getChecked");
		if(rows && rows.length > 0){
			$.messager.confirm("确认","您是否确认删除选中的数据?",function(r){
				if(!r)return;
				var ids = [];
				for(var i = 0; i < rows.length; i++){
					ids.push(rows[i].id);
				}
				$.ajax({
					url:"${pageContext.request.contextPath}/flow/mapping!delete.action",
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
<table id="flow_mapping_dg"></table>
<div id="flow_mapping_dg_toolbar" style="padding:2px;height:auto;">
	<a href="#" class="easyui-linkbutton" onclick="flow_mapping_dg_add()" data-options="iconCls:'icon-add',plain:true" style="float:left;">新增</a>
	<span>|</span>
	<a href="#" class="easyui-linkbutton" onclick="flow_mapping_dg_delete()" data-options="iconCls:'icon-remove',plain:true">删除</a>
	<span style="margin-left:20px;">所属流程:</span>
	<input type="text" name="processId" style="width:168px;"/>
	<span style="margin-left:20px;">流程步骤变迁名称:</span>
	<input type="text" name="transitionName" style="width:168px;"/>
	<a href="#" class="easyui-linkbutton" onclick="flow_mapping_dg_search()" data-options="iconCls:'icon-search',plain:true">查询</a>
</div>