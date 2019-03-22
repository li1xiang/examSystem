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
						<div class="section-a-title h5">账户管理 > 权限管理 > 权限列表管理</div>
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
										<div>权限名称</div>
										<input class="easyui-textbox" name="roleName" style="width: 100%;" data-options="validType:'length[1,100]'">
									</div>
									<div class="san-col-2 AR"></div>
								</div>
								<!-- san-row end -->
							</form>
							</div>
						</div>
						<div class="section-b-161011-right">
							<div class="section-b-161011-inner">
								<a href="#" onclick="searchRole();"
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
				<table id="roleTable" style="width: 100%; height: 300px"></table>
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
			
			
			//列宽设置为%，并完美100%，必须用js方式
			$("#roleTable").datagrid({
								url : "role/roleList",
								method : "POST",
								rownumbers : true,
								pagination : true,
								striped : true,
								sortName: 'ROLE_ID', //排序的列
								sortOrder: 'asc', //升序
								singleSelect : true,
								columns : [ [
										{
											field : "roleId",
											title : "权限ID",
											halign : "center",
											width : "10%",
											align:'center',
											resizable : false
										},
										{
											field : "roleName",
											title : "权限名称",
											halign : "center",
											width : "15%",
											align:'center',
											resizable : false
										},
										{field:'operate',title:'操作',width:"15%",sortable:false,align:'center',halign:'center',
											formatter:function formatOper(val,row,index){  
                                                var s= '<a href="#" onclick="menuRow('+index+')" class="editmenu" ></a>';
											    return s;
											} 
										} ] ],
								onLoadSuccess : function(data) {
									$("#roleTable").datagrid("hideColumn", "roleId");
									$('.editmenu').linkbutton({text:'菜单权限',plain:true,iconCls:'icon-edit',width:'78px',height:'18px'});  //按钮样式
								}
							});

		});
		function searchRole(){
			var params = $('#roleTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
			var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
			$.each( fields, function(i, field){
				params[field.name] = field.value; //设置查询参数
			}); 
			$('#roleTable').datagrid('reload'); //设置查询参数 reload  
		}
		

		//行修改
		function editRow(index){ 
			$('#roleTable').datagrid('unselectAll');//先清除所有选中的行
			$('#roleTable').datagrid('selectRow',index);// 关键在这里  
			var row = $('#roleTable').datagrid('getSelected'); 
			showWindow({
				title:'修改权限信息',
				href:'role/updateRole?roleId='+row.roleId,
				width:1000,
				height:'auto'
			});
		}
		
		//角色菜单设置
		function menuRow(index){ 
			$('#roleTable').datagrid('selectRow',index);// 关键在这里  
			var row = $('#roleTable').datagrid('getSelected');
			showWindow({
	  			title:'编辑菜单权限',
	  			href:'role/saveOrUpdateRoleMenu?roleId='+row.roleId,
	  			width:1000,
	  			onLoad: function(){
	  				/* $('#roleForm').form('clear');
	  				$('#updateUserId').val('${userSessionInfo.userId}');
	  				$('#roleId').val(row.roleId); */
	  			}
	  			
	  		});
		}
		
	</script>
</body>
</html>