<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//list
	var dg = $("#flow_process_dg").datagrid({
		url:"${pageContext.request.contextPath}/flow/process!datagrid.action",
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
			field:"name",
			width:50,
			sortable:true
		},{
			title:"流程状态",
			field:"status",
			align:"center",
			width:20,
			formatter: function(value,row,index){
				if(value == 0) return "<font color='red'>禁用</font>";
				if(value == 1) return "<font color='green'>启用</font>";
				return value;
			},
			sortable:true
		},{
			title:"流程描述",
			field:"description",
			width:198,
			sortable:true
		}]],
		toolbar:"#flow_process_dg_toolbar",
		onDblClickRow:function(rowIndex,rowData){
			flow_process_edit_window("编辑流程",rowIndex,rowData);
		}
	});
	//编辑
	function flow_process_edit_window(title,index,row){
		var d = $("<div/>").dialog({
			title:title,
			width:400,
			height:200,
			href:"${pageContext.request.contextPath}/flow/process!edit.action",
			modal:true,
			buttons:[{
				text:"保存",
				iconCls:"icon-save",
				handler:function(){
					$.messager.progress();
					$("#flow_processedit_form").form("submit",{
						url:"${pageContext.request.contextPath}/flow/process!update.action",
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
				if(row) $("#flow_processedit_form").form("load",row);
			}
		});
	};
	//search
	flow_process_dg_search = function(){
		dg.datagrid("load",{
			name:$("#flow_process_dg_toolbar input[type=text]").val()
		});
	};
	//add
	flow_process_dg_add = function(){
		flow_process_edit_window("新增流程",0,null);
	};
	//delete
	flow_process_dg_delete = function(){
		var rows = dg.datagrid("getChecked");
		if(rows && rows.length > 0){
			$.messager.confirm("确认","您是否确认删除选中的数据?",function(r){
				if(!r)return;
				var ids = [];
				for(var i = 0; i < rows.length; i++){
					ids.push(rows[i].id);
				}
				$.ajax({
					url:"${pageContext.request.contextPath}/flow/process!delete.action",
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
<table id="flow_process_dg"></table>
<div id="flow_process_dg_toolbar" style="padding:2px;height:auto;">
	<a href="#" class="easyui-linkbutton" onclick="flow_process_dg_add()" data-options="iconCls:'icon-add',plain:true" style="float:left;">新增</a>
	<span>|</span>
	<a href="#" class="easyui-linkbutton" onclick="flow_process_dg_delete()" data-options="iconCls:'icon-remove',plain:true">删除</a>
 
	<span style="margin-left:20px;">流程名称:</span>
	<input type="text" style="width:268px;"/>
	<a href="#" class="easyui-linkbutton" onclick="flow_process_dg_search()" data-options="iconCls:'icon-search',plain:true">查询</a>
</div>