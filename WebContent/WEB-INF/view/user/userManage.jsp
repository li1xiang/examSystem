<!-- 用户列表管理 -->
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
						<div class="section-a-title h5">账户管理 > 用户管理 > 用户列表管理</div>
					</div>
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
										<div>学号</div>
										<input class="easyui-textbox" name="stuNo" style="width: 100%;">
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
								url : "user/userList",
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
											width : "10%",
											align:'center',
											resizable : false
										},
										{
											field : "account",
											title : "登录名",
											halign : "center",
											width : "10%",
											align:'center',
											resizable : false
										},
										{
											field : "userCname",
											title : "姓名",
											halign : "center",
											width : "15%",
											align:'center',
											resizable : false
							   			},
							   			{
											field : "phone",
											title : "电话",
											halign : "center",
											width : "15%",
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
										{field:'operate',title:'操作',width:"30%",sortable:false,align:'center',halign:'center',
											formatter:function formatOper(val,row,index){  
												var s = '<a href="#" onclick="editRow('+index+')" class="editcls"></a>';
												s+= '<a href="#" onclick="rsetRow('+index+')" class="editpswd"></a>';
												if(row.userStatus ==1)
												{
													s+= '<a href="#" onclick="deleteRow('+index+')" class="disabledcls"></a>';
												}else{
													s+= '<a href="#" onclick="deleteRow('+index+')" class="enablecls"></a>';
												}
											    return s;  
											} 
										} ] ],
								onLoadSuccess : function(data) {
									$("#userTable").datagrid("hideColumn", "userId");
									$(".disabledcls").linkbutton({
										text:'禁用',
										plain : true,
										iconCls : 'icon-cancel',
										height : '18px'
									});
									$(".enablecls").linkbutton({
										text:'启用',
										plain : true,
										iconCls : 'icon-ok',
										height : '18px'
									});
									//修改行按钮
									$(".editcls").linkbutton({
										text:'修改',
										plain : true,
										iconCls : 'icon-edit',
										height:'18px'
									});
									$('.editpswd').linkbutton({text:'重置密码',
										plain:true,
										iconCls:'icon-edit',
										height:'18px',
										width:'78px'});  //按钮样式
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
		
		//行删除
		function deleteRow(index){ 
			$('#userTable').datagrid('selectRow',index);// 关键在这里  
			var row = $('#userTable').datagrid('getSelected');
			var alertmsg = "";
			var status;
			if(row.userStatus == 0)
			{
				alertmsg = "确定要启用\""+row.userCname+"\"吗?";
				status = 1;
			}else{
				alertmsg = "确定要禁用\""+row.userCname+"\"吗?";
				status = 0;
			} 
			$.messager.confirm('提示',alertmsg,function(result){
				if (result){
					$.post('user/deleteUser',{userId:row.userId,userStatus:status},function(data){
						$('#userTable').datagrid('reload'); 
						var msg = "";
						if(status==1)
						{
							msg="启用";
						}else{
							msg="禁用";
						}
						$.messager.alert('提示',msg+data.msg,'info');
					});
				}
			});
		}
		
		//行修改
		function editRow(index){ 
			$('#userTable').datagrid('unselectAll');//先清除所有选中的行
			$('#userTable').datagrid('selectRow',index);// 关键在这里  
			var row = $('#userTable').datagrid('getSelected'); 
			showWindow({
				title:'修改用户信息',
				href:'user/updateUser?userId='+row.userId,
				width:1000,
				height:'auto'
			});
		}
		
		//重置密码
		function rsetRow(index){ 
			$('#userTable').datagrid('selectRow',index);// 关键在这里  
			var row = $('#userTable').datagrid('getSelected');
			$.messager.confirm('提示','确认重置\"'+row.userCname+'\"的密码吗?',function(result){
				if (result){
					$.post('user/rsetPasswd',{userId:row.userId},function(data){
						$('#userTable').datagrid('reload'); 
						$.messager.alert('提示',data.msg,'info');
					});
				}
			});
		}
	</script>
</body>
</html>