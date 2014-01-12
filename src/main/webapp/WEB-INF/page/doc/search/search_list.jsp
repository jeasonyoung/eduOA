<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
$(function(){
	var search = $("#doc_search_dg_search").searchbox({
		menu:"#doc_search_dg_searchoptions",
		prompt:"请输入查询值！",
		searcher:function(value,name){
			if(value == "")return;
			dg.datagrid("load",{
				searchName:name,
				searchValue:value
			});
		}
	});
	//list
	var dg = $("#doc_search_dg").datagrid({
		url:"${pageContext.request.contextPath}/doc/search!datagrid.action",
		fit:true,
		fitColumns:true,
		pagination:true,
		rownumbers:true,
		pagePosition:"bottom",
		pageSize:20,
		pageList:[20,30,40],
		border:true,
		striped:true,
		idField:"id",
		sortName:"title",
		sortOrder:"asc",
		columns:[[{
			field:"id",
			checkbox:true
		},{
			title:"登记编号",
			field:"receiveCode",
			width:50,
			sortable:true
		},{
			title:"公文标题",
			field:"title",
			width:198,
			sortable:true
		},{
			title:"来/发文单位",
			field:"fromUnit",
			width:128,
			sortable:true
		},{
			title:"来/发文日期",
			field:"receiveDate",
			width:50,
			sortable:true
		}]],
		toolbar:"#doc_search_dg_toolbar",
		onDblClickRow:function(rowIndex,rowData){
			//org_dept_edit_window("编辑组织部门",rowIndex,rowData);
		}
	});
});
//-->
</script>
<table id="doc_search_dg"></table>
<div id="doc_search_dg_toolbar" style="padding:2px;height:auto;">
	<input id="doc_search_dg_search" style="width:368px;"/>
	<div id="doc_search_dg_searchoptions" style="width:128px;">
		<div name="title">公文标题</div>
		<div name="receiveCode">登记编号</div>
		<div name="code">文件字号</div>
		<div name="issueCode">发文号</div>
		<div name="regCode">行文登记号</div>
		<div name="regEmployee">登记人</div>
		<div name="fromUnit">来/发文单位</div>
		<div name="fromLinkEmployee">来/发文联系人</div>
		<div name="fromLinkTel">电话</div>
	</div>
</div>