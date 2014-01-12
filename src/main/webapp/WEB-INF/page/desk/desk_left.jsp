<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	$("#desk_left_menu").tree({
		url:"${pageContext.request.contextPath}/menu.action?systemId=ipower_edu_oa_desk_0000",
		lines:true,
		fit:true,
		animate:true,
		onClick:function(node){
			if(node){
				var url = node.attributes.url||'';
				set_desk_center_url(url);	
			}
		},
		onDbClick:function(node){
			if(node.state == "closed"){
				$(this).tree("expand",node.target);
			}else{
				$(this).tree("collapse",node.target);
			}
		}
	});
	//设置桌面中间区域链接。
	function set_desk_center_url(url){
		if(!url)return;
		var p = $("#desk_layout").layout("panel","center");
		if(p){
			p.panel("refresh",url);
		}
	}
});
//-->
</script>
<div class="easyui-accordion" data-options="fit:true,border:false">
	<div title="功能导航" data-options="border:false" style="overflow:auto;padding:1px;">
		<ul id="desk_left_menu"/>
	</div>
	<div title="在线人员">
		ttt
	</div>
</div>