<!-- 用户查询 -->
<%@page import="saptacims.cst.Status"%>
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
						<div class="section-a-title h5">账户管理 > 用户查询</div>
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
								<div class="san-row">
									<div class="san-col-2">
										<div>登录名</div>
										<input class="easyui-textbox" name="account" style="width: 100%;" data-options="validType:['english','length[1,100]']">
									</div>
									<div class="san-col-2">
										<div>中文姓名</div>
										<input class="easyui-textbox" name="userCname" style="width: 100%;" data-options="validType:['chs','length[1,100]']">
									</div>
									
									<div class="san-col-2 AR"></div>
								</div>
								<!-- san-row end -->
							</form>
							</div>
						</div>
						<div class="section-b-161011-right">
							<div class="section-b-161011-inner">
								<a href="#" onclick="searchUser();"
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
				<table id="userTable" style="width: 100%; height: 300px"></table>
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
			//初始化查询条件--------------------end

			
			
			
			//列宽设置为%，并完美100%，必须用js方式
			$("#userTable").datagrid({
								url : "user/queryUserList",
								method : "POST",
								rownumbers : true,
								pagination : true,
								striped : true,
								sortName: 'USER_ID', //排序的列
								sortOrder: 'asc', //升序
								singleSelect : true,
								columns : [ [
										{
											field : "userId",
											title : "用户ID",
											halign : "center",
											width : "15%",
											align:'center',
											resizable : false
										},
										{
											field : "account",
											title : "登录名",
											halign : "center",
											width : "15%",
											align:'center',
											resizable : false
										},
										{
											field : "userCname",
											title : "中文姓名",
											halign : "center",
											width : "15%",
											align:'center',
											resizable : false
							   			},
							   			{
											field : "phone",
											title : "电话",
											halign : "center",
											width : "20%",
											align:'center',
											resizable : false
							   			},
							   			{
											field : "stuNo",
											title : "stuNo",
											halign : "center",
											width : "30%",
											align:'center',
											resizable : false
							   			},
							   			{
											field : "userStatus",
											title : "用户状态",
											halign : "center",
											width : "10%",
											align:'center',
											resizable : false,
											formatter:function(value,row,index){
												return row.userStatus==0?'禁用':'启用';  
											}
							   			},
										{field:'operate',title:'操作',width:"15%",sortable:false,align:'center',halign:'center',
											formatter:function formatOper(val,row,index){  
												var s = '<a href="#" onclick="editRow('+index+')" class="editcls"></a>';
											    return s;  
											} 
										} ] ],
								onLoadSuccess : function(data) {
									$("#userTable").datagrid("hideColumn", "userId");
									//明细按钮
									$(".editcls").linkbutton({
										text:'明细',
										plain : true,
										iconCls : 'icon-edit',
										height:'18px'
									});
								}
							});


		});
		function searchUser(){
			var params = $('#userTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
			var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
			$.each( fields, function(i, field){
				params[field.name] = field.value; //设置查询参数
			}); 
			$('#userTable').datagrid('reload'); //设置查询参数 reload  
		}
		
		//查看明细
		function editRow(index){ 
			$('#userTable').datagrid('unselectAll');//先清除所有选中的行
			$('#userTable').datagrid('selectRow',index);// 关键在这里  
			var row = $('#userTable').datagrid('getSelected'); 
			$('#main-page-body-content').sanJumpto({
				//url:'question/getQuestionDetail?action=update&questionId='+row.questionId
				url:'user/getUserDetail?userId=' + row.userId
			});
		}
	</script>
</body>
</html>