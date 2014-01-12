<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	var role_tree;
	//load
	org_userrole_edit_load = function(row){
		//user
		$("#org_userroleedit_form input[name=id]").combobox({
			url:"${pageContext.request.contextPath}/org/user!all.action",
			valueField:"id",
			textField:"name",
			required:true,
			width:198,
			onSelect:function(record){
				role_tree.tree("reload");
				$.ajax({
					type:"get",
					url:"${pageContext.request.contextPath}/org/userrole!roles.action?id="+record.id,
					dataType:"json",
					success:function(data){
						if(data && role_tree){
							$.each(data,function(index,id){
								var node = role_tree.tree("find",id);
								if(node) role_tree.tree("check",node.target);
							});
						}
					}
				});
			},
			onLoadSuccess:function(){
				if(row)$(this).combobox("setValue",row.id);
			}
		}); 
		//role
		role_tree = $("#org_userroleedit_form ul").tree({
			url:"${pageContext.request.contextPath}/org/role!tree.action",
			animate:true,
			checkbox:true,
			lines:true,
			onLoadSuccess:function(){
				if(row && row.roleIds){
					$.each(row.roleIds,function(index,id){
						var node = role_tree.tree("find",id);
						if(node) role_tree.tree("check",node.target);
					});
				}
			}
		});
	}	
	//getcheckeds
	org_userrole_edit_getcheckeds = function(){
		var roles = [];
		var objArray = role_tree.tree("getChecked","checked");
		if(objArray){
			$.each(objArray,function(index,obj){
				roles.push(obj.id);
			});
		}
		return roles;
	};
});
//-->
</script>
<form id="org_userroleedit_form" class="edit" method="POST">
	<dd style="marign-top:2px;">
		<label>用户名称：</label>
		<input type="text" name="id"/>
	</dd>
	<dd>
		<div title="菜单" class="easyui-panel" style="width:570px; height:295px;fit:true;">
			<ul/>
		</div>
	</dd>
</form>