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
				<div class="section-a-title h5">权限菜单信息</div>
			</div><!-- section-a-head end -->
			<div class="section-a-body">
				<div class="section-f section-f-fixed-800 m-t-10">
					<div class="section-f-head">
						<div class="h4">权限菜单信息编辑</div>
					</div>
					<!-- section-f-head end -->
					<form id="modRoleMenu" method="POST" action="">
						<input type="hidden" name="roleId" id="roleIdMod" />
						<input type="hidden" name="menuId" id="nodestr"/>
 						<div class="section-f-body">
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<ul class="easyui-tree" data-options="url:'role/allMenuList',animate:true,checkbox:true,
									onLoadSuccess:function(){
										$.post('role/roleMenu',{roleId:$('#roleIdMod').val()},function(data){
											for(var i=0;i<data.length;i++)
											{
												var node =$('#tt').tree('find',data[i].menuId);
												$('#tt').tree('check', node.target);
											}
										});
									}
									" id="tt"></ul>
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

			
			 $("#roleIdMod").val("${roleId}"); 
			
			//初始化新增表单
			$("#modRoleMenu").form({
				url : "role/addOrUpdateRoleMenu",
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
				var nodes = $('#tt').tree('getChecked');
				var nodestr= '';
				for(var i=0;i<nodes.length;i++)
				{
					nodestr+=nodes[i].id+',';
				}
				$('#nodestr').val(nodestr);
				//新增表单提交
				$("#modRoleMenu").form("submit");
			});
		});
	</script>



</body>
</html>