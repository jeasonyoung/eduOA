<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//list
	var dg = $("#org_userrole_dg").datagrid({
		url:"${pageContext.request.contextPath}/org/userrole!datagrid.action",
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
			title:"用户名称",
			field:"name",
			width:40,
			sortable:true
		},{
			title:"赋予角色",
			field:"roleNames",
			width:256,
			sortable:true
		}]],
		toolbar:"#org_userrole_dg_toolbar",
		onDblClickRow:function(rowIndex,rowData){
			org_userrole_edit_window("编辑用户角色",rowIndex,rowData);
		}
	});
	//edit
	function org_userrole_edit_window(title,index,row){
		var d = $("<div/>").dialog({
			title:title,
			width:600,
			height:400,
			href:"${pageContext.request.contextPath}/org/userrole!edit.action",
			modal:true,
			buttons:[{
				text:"保存",
				iconCls:"icon-save",
				handler:function(){
					$.messager.progress();
					$("#org_userroleedit_form").form("submit",{
						url:"${pageContext.request.contextPath}/org/userrole!update.action",
						onSubmit: function(param){
							param.roleIds = org_userrole_edit_getcheckeds();
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
				org_userrole_edit_load(row);
			}
		});
	};
	//search
	org_userrole_dg_search = function(){
		dg.datagrid("load",{
			name:$("#org_userrole_dg_toolbar input[type=text]").val()
		});
	};
	//add
	org_userrole_dg_add = function(){
		org_userrole_edit_window("新增用户角色",0,null);
	};
	//delete
	org_userrole_dg_delete = function(){
		var rows = dg.datagrid("getChecked");
		if(rows && rows.length > 0){
			$.messager.confirm("确认","您是否确认删除选中的数据?",function(r){
				if(!r)return;
				var ids = [];
				for(var i = 0; i < rows.length; i++){
					ids.push(rows[i].id);
				}
				$.ajax({
					url:"${pageContext.request.contextPath}/org/userrole!delete.action",
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
<table id="org_userrole_dg"></table>
<div id="org_userrole_dg_toolbar" style="padding:2px;height:auto;">
	<a href="#" class="easyui-linkbutton" onclick="org_userrole_dg_add()" data-options="iconCls:'icon-add',plain:true" style="float:left;">新增</a>
	<span>|</span>
	<a href="#" class="easyui-linkbutton" onclick="org_userrole_dg_delete()" data-options="iconCls:'icon-remove',plain:true">删除</a>
	<span style="margin-left:20px;">用户名称:</span>
	<input type="text" style="width:268px;"/>
	<a href="#" class="easyui-linkbutton" onclick="org_userrole_dg_search()" data-options="iconCls:'icon-search',plain:true">查询</a>
</div>