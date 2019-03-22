<%@page %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户信息</title>
</head>
<body>
	<%-- <jsp:include page="../common/commonDia.jsp" /> --%>
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="section-a-title h5">权限信息</div>
			</div><!-- section-a-head end -->
			<div class="section-a-body">
				<div class="section-f section-f-fixed-800 m-t-10">
					<div class="section-f-head">
						<div class="h4">权限信息编辑</div>
					</div>
					<!-- section-f-head end -->
					<form id="modRole" method="POST" action="">
						<input type="hidden" name="roleId" id="roleIdMod" />
						<div class="section-f-body">
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">权限名称</div>
								<div class="san-col-3">
									<input id="roleNameMod" name="roleName" class="easyui-textbox"
										style="width: 100%;" data-options="required:true,validType:'length[0,100]'" value="${tbRole.roleName}"> 
								</div>
								<div class="san-col-3">
									<div class="easyui-datalist"  title="用户列表" style="width:300px;height:250px" id="userlist" name="roleUser" data-options="
				            				url: 'role/allUserList',
				            				valueField:'userId',
				            				textField:'userCname',
				            				checkbox: true,
				           					selectOnCheck: false,
				            				onBeforeSelect: function(){return false;},
				            				onLoadSuccess:function(){
				            					if($('#roleIdMod').val() !='')
				            					{
												$.post('role/roleUserByRoleId',{roleId:$('#roleIdMod').val()},function(data){
				            							var rows = $('#userlist').datagrid('getRows');
				            							for(var i=0;i< rows.length;i++)
				            							{
				            								for(var j = 0;j<data.userRoleList.length;j++)
				            								{
				            									if(data.userRoleList[j].userId == rows[i].userId)
				            									{
				            										$('#userlist').datagrid('checkRow',i);
				            									}
				            								}
				            							}
												});
				            					}
				            				}
	            					">
									</div>
								</div>
								<div class="san-col-1"></div>
							</div>
						</div>
						<!-- section-f-body end -->
					</form>
					<div class="section-f-foot AR">
						<a id="cancelInModRole" href="#"
							class="easyui-linkbutton mw-70 m-r-10">取消</a> <a
							id="saveInModRole" href="#"
							class="easyui-linkbutton mw-70 m-r-10 z-easyui-bg-primary">保存</a>
					</div>
					<!-- section-f-foot end -->
				</div>
				<!-- section-f end -->

			</div>
			<!-- section-a-body end -->
		</div>
		<!-- section-a end -->
	</div>
	<!-- sub-page end -->
	<script>
		$(function() {
			//$.parser.parse('#main-page-body-content');

			
			 $("#roleIdMod").val("${tbRole.roleId}"); 
			
			//初始化新增表单
			$("#modRole").form({
				url : "role/saveOrUpdateRole",
				type : "POST",
				success : function(data) {
					var result = eval("(" + data + ")");
					if (result.modFlag) {
						showSuccessMsgDia(result.msg);
						$('#roleTable').datagrid("reload");
						closeWindow();
					}else {
						showFailureMsgDia(result.msg);
					}
				}
			});
			
			$("#cancelInModRole").bind("click", function() {
				closeWindow();
			});

			//新增页面保存按钮
			$("#saveInModRole").bind("click", function() {
				var row = $('#userlist').datagrid('getChecked');
				var htmlstr='';
				for(var i=0;i<row.length;i++)
				{
					htmlstr += '<input type="hidden" name="roleUsers['+i+'].userId" value="'+row[i].userId+'"/>';
				}
				$('#modRole').append(htmlstr);
				//新增表单提交
				$("#modRole").form("submit");
			});
		});
	</script>



</body>
</html>