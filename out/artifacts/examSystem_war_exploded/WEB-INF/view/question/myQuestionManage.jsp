<!-- 试题列表管理 -->
<%@page import="saptacims.cst.Status"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<%-- <%@ include file="../../../../taglibs.jsp" %> --%>
	<%-- <%@ include file="../common/commonDia.jsp" %> --%>
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="san-row">
					<div class="san-col-9">
						<div class="section-a-title h5">题库管理>我的试题 > 我的试题管理</div>
					</div>
					<!-- <div class="san-col-3 AR">
						<a id="addUserBtn" href="#" class="easyui-linkbutton m-t-8 mw-70 z-easyui-bg-primary">用户录入</a>
					</div> -->
				</div>
				<!-- san-row end -->
			</div>
			<!-- section-a-head end -->

			<div class="section-a-body p-t-10">
				<div class="headline-a">
					<span class="headline-a-text">查询条件</span>
				</div>
				<div class="section-b">
					<div class="section-b-body section-b-161011">
						<div class="section-b-161011-left">
							<div class="section-b-161011-inner">
							<form id="queryForm" >
								<input id="createUser" name="createUser" type="hidden" value="${createUser}">
								<div class="san-row section-f-row">
									<div class="san-col-1" align="right">题型</div>
								<div class="san-col-2">
									<select id="questionType" name="questionType" class="easyui-combobox"
										style="width: 100%;" data-options="required:true,editable:false">										
									</select>
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-1" align="right">类别</div>
								<div class="san-col-2">
									<select id="categoryId" name="categoryId" class="easyui-combobox"
										style="width: 100%;" data-options="required:true,editable:false">										
									</select>
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-1" align="right">群组</div>
								<div class="san-col-2">
									<select id="groupId" name="groupId" class="easyui-combobox"
										style="width: 100%;" data-options="required:true,editable:false">										
									</select>
								</div>
								<div class="san-col-1"></div>
							</div>
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-1" align="right">难度</div>
								<div class="san-col-2">
									<select id="levels" name="levels" class="easyui-combobox"
										style="width: 100%;" data-options="required:true,editable:false">										
									</select>
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-1" align="right">状态</div>
								<div class="san-col-2">
									<select id="active" name="active" class="easyui-combobox"
										style="width: 100%;" data-options="required:true,editable:false">										
									</select>
								</div>
								<div class="san-col-1"></div>								
							</div>
							<!-- san-row end -->
							</form>
							</div>
						</div>
						<div class="section-b-161011-right">
							<div class="section-b-161011-inner">
								<a href="#" onclick="searchQuestion();"
									class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">查询</a>
								<!-- <a href="#" class="easyui-linkbutton m-t-10 mw-70">Excel导出</a> -->
							</div>
						</div>
					</div>
					<!-- section-b-body end -->
				</div>
				<!-- section-b end -->

				<div class="headline-a">
					<span class="headline-a-text">查询结果</span>
				</div>
				<table id="questionTable" style="width: 100%; height: 300px"></table>
			</div>
			<!-- section-a-body end -->
		</div>
		<!-- section-a end -->
	</div>
	<!-- sub-page end -->
	<!-- <div id="subpage-list-ecc" class="easyui-calendar"></div> -->

	<script type="text/javascript">
		$(function() {
			$.parser.parse("#main-page-body-content");
			//初始化查询条件--------------------start
			//初始化题型下拉框
 			$("#questionType").combobox({
				valueField:'id', //值字段
				textField:'text', //显示的字段
				url:'question/queTypeList?page=all',
				panelHeight:'auto',
				required:false,
				editable:false//不可编辑，只能选择
			}); 
			//初始化难度下拉框
 			$("#levels").combobox({
				valueField:'id', //值字段
				textField:'text', //显示的字段
				url:'question/levelsList?page=all',
				panelHeight:'auto',
				required:false,
				editable:false//不可编辑，只能选择
			}); 
 			//初始化禁用下拉框
 			$("#active").combobox({
				valueField:'id', //值字段
				textField:'text', //显示的字段
				url:'question/activeList?page=all',
				panelHeight:'auto',
				required:false,
				editable:false//不可编辑，只能选择
			}); 
 			//初始化类别下拉框
 			$("#categoryId").combobox({
				valueField:'id', //值字段
				textField:'text', //显示的字段
				url:'question/categoryList?page=all',
				panelHeight:200,
				required:false,
				editable:false//不可编辑，只能选择
			}); 
 			//初始化群组下拉框
 			$("#groupId").combobox({
				valueField:'id', //值字段
				textField:'text', //显示的字段
				url:'question/groupList?page=all',
				panelHeight:200,
				required:false,
				editable:false//不可编辑，只能选择
			}); 
			//初始化查询条件--------------------end			
			
			//列宽设置为%，并完美100%，必须用js方式
			$("#questionTable").datagrid({
								url : "question/questionList?createUser=${createUser}",
								method : "POST",
								rownumbers : true,
								pagination : true,
								striped : true,
								sortName: 'QUESTION_ID', //排序的列
								sortOrder: 'asc', //升序
								checkOnSelect: false,
								selectOnCheck: false,
								onClickRow: function (rowIndex, rowData) {
		                            $("#questionTable").datagrid('unselectRow', rowIndex);
		 						},
								columns : [ [
										{
											field : "questionId",
											title : "题目ID",
											halign : "center",
											width : "10%",
											align:'center',
											resizable : false
										},
							   			{
											field : "subject",
											title : "题目内容",
											halign : "center",
											width : "25%",
											align:'left',
											resizable : false,
											formatter: function(value,row,index){ 
												//单元格文本溢出显示省略号,悬浮框
												   return '<span title='+value+'>'+value+'</span>'    
												}  
							   			},
							   			{
											field : "subjectImgName",
											title : "附件",
											halign : "center",
											width : "15%",
											align:'center',
											resizable : false,
											formatter: function(value,row,index){
												return '<a href="${ctx}'+row.subjectImg+'" rel="lightbox" >'+row.subjectImgName+'</a>';
											}
							   			},
							   			{
											field : "subjectImg",
											title : "附件地址",
											halign : "center",
											width : "15%",
											align:'center',
											resizable : false
							   			},
										{
											field : "questionType",
											title : "题型",
											halign : "center",
											width : "10%",
											align:'center',
											resizable : false,
											formatter:function(value,row,index){
												return row.questionType==0?'主观题':'客观题';  
											}
										},
										{
											field : "categoryId",
											title : "类别ID",
											halign : "center",
											width : "10%",
											align:'center',
											resizable : false
							   			},
										{
											field : "categoryName",
											title : "类别",
											halign : "center",
											width : "10%",
											align:'center',
											resizable : false
							   			},
							   			{
											field : "groupId",
											title : "群组ID",
											halign : "center",
											width : "10%",
											align:'center',
											resizable : false
							   			},
							   			{
											field : "groupName",
											title : "群组",
											halign : "center",
											width : "10%",
											align:'center',
											resizable : false
							   			},
							   			{
											field : "score",
											title : "分数",
											halign : "center",
											width : "5%",
											align:'center',
											resizable : false
							   			},
							   			{
											field : "levels",
											title : "难度",
											halign : "center",
											width : "5%",
											align:'center',
											resizable : false,
											formatter:function(value,row,index){
												return row.levels==1?'高级':(row.levels==2?'中级':'初级');  
											}
							   			},
							   			{
											field : "active",
											title : "状态",
											halign : "center",
											width : "5%",
											align:'center',
											resizable : false,
											formatter:function(value,row,index){
												return row.active==0?'禁用':'启用';  
											}
							   			} ,
										{field:'operate',title:'操作',width:"15%",sortable:false,align:'center',halign:'center',
											formatter:function formatOper(val,row,index){  
												var s = '<a href="#" onclick="editRow('+index+')" class="editcls"></a>';												
											    return s;  
											} 
										} ] ],
								onLoadSuccess : function(data) {
									$("#questionTable").datagrid("hideColumn", "questionId");
									$("#questionTable").datagrid("hideColumn", "subjectImg");
									$("#questionTable").datagrid("hideColumn", "categoryId");
									$("#questionTable").datagrid("hideColumn", "groupId");
									//修改行按钮
									$(".editcls").linkbutton({
										text:'明细',
										plain : true,
										iconCls : 'icon-edit',
										height:'18px'
									});
								}
							});


		});
		
		
		function searchQuestion(){
			var params = $('#questionTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
			var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
			$.each( fields, function(i, field){
				params[field.name] = field.value; //设置查询参数
			}); 
			$('#questionTable').datagrid('reload'); //设置查询参数 reload  
		}				
		
		//行修改
		function editRow(index){ 
			$('#questionTable').datagrid('unselectAll');//先清除所有选中的行
			$('#questionTable').datagrid('selectRow',index);// 关键在这里  
			var row = $('#questionTable').datagrid('getSelected'); 
			var urls;
			if(row.active==0){
				urls='question/getMyQuestionDetail?action=update&questionId='+row.questionId
			}else{
				urls='question/getMyQuestionDetail?action=select&questionId='+row.questionId
			}	
			$('#main-page-body-content').sanJumpto({
				url:urls					
			});
		}
		
	</script>
</body>
</html>