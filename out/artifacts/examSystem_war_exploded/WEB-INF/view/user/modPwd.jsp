<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ include file="../../../../taglibs.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户密码修改</title>
</head>
<body>
	<%-- <jsp:include page="../common/commonDia.jsp" /> --%>
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="section-a-title h5">账户管理 > 个人密码修改 </div>
			</div>
			<!-- section-a-head end -->

			<div class="section-a-body">
				<div class="section-f section-f-fixed-800 m-t-10">
					<div class="section-f-head">
						<div class="h4">用户密码修改</div>
					</div>
					<!-- section-f-head end -->
					<form id="modPwd" method="POST" action="">
						<div class="section-f-body">
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">原密码</div>
								<div class="san-col-3">
									<input id="userPwd" name="oldPassword" class="easyui-passwordbox"
										style="width: 100%;" data-options="required:true,validType:'length[0,100]'">
								</div>
								<div class="san-col-1"></div>
							</div>
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">新密码</div>
								<div class="san-col-3">
									<input id="newPwd" name="newPwd" class="easyui-passwordbox"
										style="width: 100%" data-options="required:true,validType:'length[0,100]'">
								</div>
								<div class="san-col-1"></div>
							</div>
							<!-- san-row end -->
							<div class="san-row section-f-row">
							<div class="san-col-2" align="right">新密码确认</div>
								<div class="san-col-3">
									<input id="confirmNewPwd" name="confirmNewPwd" class="easyui-passwordbox"
										style="width: 100%" data-options="required:true,validType:'length[0,100]'">
								</div>
								<div class="san-col-1"></div>
							</div>
							<!-- san-row end -->
						</div>
						<!-- section-f-body end -->
					</form>
					<div class="section-f-foot AR">
						<a id="saveInModPwd" href="#"
							class="easyui-linkbutton mw-70 m-r-10 z-easyui-bg-primary">修改</a>
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
	<!-- <div id="subpage-list-ecc" class="easyui-calendar"></div> -->
	<script>
		$(function() {
			$.parser.parse('#main-page-body-content');

			//初始化新增表单
			$("#modPwd").form({
				url : "user/doChangePwd",
				type : "POST",
				success : function(data) {
					var result = eval("(" + data + ")");
					if (result.modFlag) {
						showSuccessMsgDia("密码修改成功！");
						$("#modPwd").form("reset");
					}else {
						showFailureMsgDia(result.msg);
					}
				}
			});

			//新增页面保存按钮
			$("#saveInModPwd").bind("click", function() {
				$("#modPwd").form("submit");
			});
		});
	</script>



</body>
</html>