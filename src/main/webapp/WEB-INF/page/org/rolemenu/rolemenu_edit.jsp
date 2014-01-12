<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	var menu_tree;
	//load
	org_rolemenu_edit_load = function(row){
		//role
		$("#org_rolemenuedit_form input[name=id]").combobox({
			url:"${pageContext.request.contextPath}/org/role!all.action",
			valueField:"id",
			textField:"name",
			required:true,
			width:198,
			onSelect:function(record){
				menu_tree.tree("reload");
				$.ajax({
					type:"get",
					url:"${pageContext.request.contextPath}/org/rolemenu!menus.action?id="+record.id,
					dataType:"json",
					success:function(data){
						if(data && menu_tree){
							$.each(data,function(index,id){
								var node = menu_tree.tree("find",id);
								if(node) menu_tree.tree("check",node.target);
							});
						}
					}
				});
			},
			onLoadSuccess:function(){
				if(row)$(this).combobox("setValue",row.id);
			}
		});
		//menu
		menu_tree = $("#org_rolemenuedit_form ul").tree({
			url:"${pageContext.request.contextPath}/org/menu.action?systemId",
			animate:true,
			checkbox:true,
			lines:true,
			onLoadSuccess:function(){
				if(row && row.menuIds){
					$.each(row.menuIds,function(index,id){
						var node = menu_tree.tree("find",id);
						if(node) menu_tree.tree("check",node.target);
					});
				}
			}
		});
	}	
	//getcheckeds
	org_rolemenu_edit_getcheckeds = function(){
		var menus = [];
		var objArray = menu_tree.tree("getChecked","checked");
		if(objArray){
			$.each(objArray,function(index,obj){
				menus.push(obj.id);
			});
		}
		return menus;
	};
});
//-->
</script>
<form id="org_rolemenuedit_form" class="edit" method="POST">
	<dd style="marign-top:2px;">
		<label>角色名称：</label>
		<input type="text" name="id"/>
	</dd>
	<dd>
		<div title="菜单" class="easyui-panel" style="width:570px; height:295px;fit:true;">
			<ul/>
		</div>
	</dd>
</form>