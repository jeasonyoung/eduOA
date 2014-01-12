<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	//dept tree
	$("#org_user_left_department").tree({
		url:"${pageContext.request.contextPath}/org/dept!tree.action",
		lines:true,
		fit:true,
		animate:true,
		onClick:function(node){
			org_user_dg_search(node.id);
		},
		onDbClick:function(node){
			if(node.state == "closed"){
				$(this).tree("expand",node.target);
			}else{
				$(this).tree("collapse",node.target);
			}
		}
	});
	//user list
	var dg = $("#org_user_dg").datagrid({
		url:"${pageContext.request.contextPath}/org/user!datagrid.action",
		fit:true,
		fitColumns:true,
		pagination:true,
		pagePosition:"bottom",
		pageSize:20,
		pageList:[20,30,40],
		border:false,
		striped:true,
		idField:"id",
		sortName:"name",
		sortOrder:"asc",
		columns:[[{
			field:"id",
			checkbox:true
		},{
			title:"组织部门",
			field:"departmentName",
			width:80,
			sortable:false
		},{
			title:"用户姓名",
			field:"name",
			width:50,
			sortable:true
		},{
			title:"性别",
			field:"gender",
			formatter:function(value,row,index){
				if(value == 1)return "男";
				if(value == 2)return "女";
			},
			width:20,
			align:"center",
			sortable:true
		},{
			title:"职务岗位",
			field:"postName",
			width:64,
			sortable:false
		},{
			title:"用户账号",
			field:"account",
			width:50,
			sortable:true
		},{
			title:"状态",
			field:"status",
			formatter:function(value,row,index){
				if(value == 0)return "<font color='red'>禁用</font>";
				if(value == 1)return "启用";
			},
			width:30,
			align:"center",
			sortable:true
		},{
			title:"电子邮箱",
			field:"email",
			width:80,
			sortable:true
		}]],
		toolbar:"#org_user_dg_toolbar",
		onDblClickRow:function(rowIndex,rowData){
			org_user_edit_window("编辑用户",rowIndex,rowData);
		}
	});
	//edit
	function org_user_edit_window(title,index,row){
		var d = $("<div/>").dialog({
			title:title,
			width:500,
			height:230,
			href:"${pageContext.request.contextPath}/user/user!edit.action",
			modal:true,
			buttons:[{
				text:"保存",
				iconCls:"icon-save",
				handler:function(){
					$.messager.progress();
					$("#org_useredit_form").form("submit",{
						url:"${pageContext.request.contextPath}/user/user!update.action",
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
					row.password = "";
					$("#org_useredit_form").form("load",row);
				}
				else{
					$("#org_useredit_form input[name=password]").validatebox({
						required: true
					});
					$("#org_useredit_form input[name=repassword]").validatebox({
						required: true
					});
				}
			}
		});
	};
	//search
	org_user_dg_search = function(deptId){
		dg.datagrid("load",{
			name:$("#org_user_dg_toolbar input[type=text]").val(),
			departmentId:deptId
		});
	};
	//add
	org_user_dg_add = function(){
		org_user_edit_window("新增用户",0,null);
	};
	//delete
	org_user_dg_delete = function(){
		var rows = dg.datagrid("getChecked");
		if(rows && rows.length > 0){
			$.messager.confirm("确认","您是否确认删除选中的数据?",function(r){
				if(!r)return;
				var ids = [];
				for(var i = 0; i < rows.length; i++){
					ids.push(rows[i].id);
				}
				$.ajax({
					url:"${pageContext.request.contextPath}/org/user!delete.action",
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
	}
});
//-->
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div title="组织部门" data-options="region:'west',split:true" style="width:200px;overflow:hidden;">
		<ul id="org_user_left_department"/>
	</div>
	<div data-options="region:'center',border:false" style="overflow:hidden;">
		<table id="org_user_dg"></table>
		<div id="org_user_dg_toolbar" style="padding:2px;height:auto;">
			<a href="#" class="easyui-linkbutton" onclick="org_user_dg_add()" data-options="iconCls:'icon-add',plain:true" style="float:left;">新增</a>
			<span>|</span>
			<a href="#" class="easyui-linkbutton" onclick="org_user_dg_delete()" data-options="iconCls:'icon-remove',plain:true">删除</a>
		 
			<span style="margin-left:20px;">用户姓名/账号:</span>
			<input type="text" style="width:268px;"/>
			<a href="#" class="easyui-linkbutton" onclick="org_user_dg_search()" data-options="iconCls:'icon-search',plain:true">查询</a>
		</div>
	</div>
</div>