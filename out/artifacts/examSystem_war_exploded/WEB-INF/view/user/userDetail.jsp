<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息明细</title>
</head>
<body>
	<%-- <jsp:include page="../common/commonDia.jsp" /> --%>
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="section-a-title h5">个人信息明细</div>
			</div><!-- section-a-head end -->
			<div class="section-a-body">
				<div class="section-f section-f-fixed-800 m-t-10">
					<div class="section-f-head">
						<div class="h4">个人信息明细</div>
					</div>
					<!-- section-f-head end -->
					<form id="modUser" method="POST" action="">
						<input type="hidden" name="userId" id="userIdMod" />
						<div class="section-f-body">
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">登录名:</div>
								<div class="san-col-3">
									<p>${tbUser.account}</p>
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-2" align="right">英文名:</div>
								<div class="san-col-3">
									<p>${tbUser.userEname}</p>
								</div>
								<div class="san-col-1"></div>
							</div>
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">中文姓名:</div>
								<div class="san-col-3">
									<p>${tbUser.userCname}</p>
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-2" align="right">性别:</div>
								<div class="san-col-3">
									<p>${tbUser.sex == 0?'男':'女'}</p>
								</div>
								<div class="san-col-1"></div>
							</div>
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">用户状态:</div>
								<div class="san-col-3">
									<p>${tbUser.userStatus == 0?'禁用':'启用'}</p>
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-2" align="right">手机:</div>
								<div class="san-col-3">
									<p>${tbUser.phone}</p>
								</div>
								<div class="san-col-1"></div>
							</div>
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">电子邮件:</div>
								<div class="san-col-3">
									${tbUser.stuNo}
								</div>
								<div class="san-col-1"></div>
								
							</div>
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">备注:</div>
								<div class="san-col-3" style="width:75%">
									<p>${tbUser.stuclass}</p>
								</div>
								<div class="san-col-1"></div>
							</div>
						</div>
						<!-- section-f-body end -->
					</form>
					<div class="section-f-foot">
					<a href="#" onclick="lastPage();"
						class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">返回</a>
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
			$.parser.parse('#main-page-body-content');
 			 
		});
		
		//返回
		function lastPage(){
			$('#main-page-body-content').sanJumpto({
				url:'user/getQueryUser'
			});
		}
	</script>



</body>
</html>