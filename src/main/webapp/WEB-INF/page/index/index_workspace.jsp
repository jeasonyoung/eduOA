<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
<!--
$(function(){
	//构建Tabs.
	var workspaceTabs = $("#layout_workspace_tabs").tabs({
		fit:true,
		border:false,
		onContextMenu:function(e,title,index){
			e.preventDefault();
			if(tabsMenu){
				tabsMenu.menu("show",{
					left:e.pageX,
					top:e.pageY
				}).data("tabTitle",title);
			}
		}
	});
	//tabs右键菜单
	var tabsMenu = $("#layout_workspace_tabsMenu").menu({
		onClick:function(item){
			var currentTabTitle = $(this).data("tabTitle");
			var type = $(item.target).attr("type");
			if(type == "refresh"){
				if(workspaceTabs){
					var tab = workspaceTabs.tabs("getTab", currentTabTitle);
					if(tab){
						var opts = tab.panel("options");
						workspaceTabs.tabs("update",{
							tab:tab,
							options:opts
						});
					}
				}
				return;
			}
			if(type == "close"){
				var t = workspaceTabs.tabs("getTab",currentTabTitle);
				if(t && t.panel("options").closable){
					workspaceTabs.tabs("close",currentTabTitle);
				}
				return;
			}
			var allTabs = workspaceTabs.tabs("tabs");
			var closeTabsTitle = [];
			$.each(allTabs,function(){
				var opts = $(this).panel("options");
				if(opts.closable && opts.title != currentTabTitle && type == "closeOther"){
					closeTabsTitle.push(opts.title);
				}else if(opts.closable && type == "closeAll"){
					closeTabsTitle.push(opts.title);
				}
			});
			for(var i = 0; i < closeTabsTitle.length;i++){
				workspaceTabs.tabs("close",closeTabsTitle[i]);
			}
		}
	});
	//添加tab页。
	index_workspace_addWorkspaceTab = function(node){
		if(node && workspaceTabs){
			var text = node.text||'';
			var url = node.attributes.url||'';
			//alert(url);
			if(workspaceTabs.tabs("exists",text)){
				workspaceTabs.tabs("select",text);
			}else if(url && url.length > 0){
				//添加Tab页面。
				workspaceTabs.tabs("add",{
					title:text,
					closable:true,
					href:"${pageContext.request.contextPath}"+url
				});
			}
		}
	}
});
//-->
</script>
<div id="layout_workspace_tabs">
	<div class="easyui-layout" data-options="fit:true,border:false" title="首页">
		<div class="easyui-layout" data-options="region:'center',border:false">
			<div data-options="region:'center',title:'待办事项(0)'"></div>
			<div data-options="region:'east',title:'待阅事项(0)',split:true" style="width:500px;"></div>
		</div>
		<div class="easyui-layout" data-options="region:'south',border:false,split:true" style="height:200px;">
			<div data-options="region:'west',title:'未读邮件(0)',split:true" style="width:350px;"></div>
			<div data-options="region:'center',title:'工作备忘(0)'"></div>
			<div data-options="region:'east',title:'未读公告(0)',split:true" style="width:350px;"></div>
		</div>
	</div>
</div>
<div id="layout_workspace_tabsMenu" style="width:120px;display:none;">
	<div type="refresh">刷新</div>
	<div class="menu-sep"></div>
	<div type="close">关闭</div>
	<div type="closeOther">关闭其他</div>
	<div type="closeAll">关闭所有</div>
</div>