<!-- 我的信息 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ include file="../../../taglibs.jsp"%>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的信息</title>
</head>
<body>
	<%-- <jsp:include page="../common/commonDia.jsp" /> --%>
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="section-a-title h5">账户管理 > 我的信息 </div>
			</div><!-- section-a-head end -->
			<div class="section-a-body">
				<div class="section-f section-f-fixed-800 m-t-10">
					<div class="section-f-head">
						<div class="h4">我的信息</div>
					</div>
					<!-- section-f-head end -->
					<form id="modUser" method="POST" action="">
						<input type="hidden" name="userId" id="userIdMod" />
						<div class="section-f-body">
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">登录名</div>
								<div class="san-col-3">
									<input id="accountMod" name="account" class="easyui-textbox"
										style="width: 100%;" disabled="false" data-options="required:true,validType:['english','length[1,100]']" value="${tbUser.account}"> 
								</div>
								<div class="san-col-1"></div>
							</div>
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">中文姓名</div>
								<div class="san-col-3">
									<input id="userCnameMod" name="userCname" class="easyui-textbox"
										style="width: 100%" value="${tbUser.userCname}" data-options="required:true,validType:['chs','length[1,100]']">
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-2" align="right">英文名</div>
								<div class="san-col-3">
									<input id="userEnameMod" name="userEname" class="easyui-textbox"
										style="width: 100%"  value="${tbUser.userEname}" data-options="validType:'length[0,100]'">
								</div>
								<div class="san-col-1"></div>
							</div>
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">用户状态</div>
								<div class="san-col-3">
									<select id="userStatusMod" name="userStatus" class="easyui-combobox"
										style="width: 100%;">
									</select>
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-2" align="right">性别</div>
								<div class="san-col-3">
									<select id="sexMod" name="sex" class="easyui-combobox"
										style="width: 100%;">
									</select>
								</div>
							</div>
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">电子邮件</div>
								<div class="san-col-3">
									<input id="stuNoMod" name="stuNo" class="easyui-textbox"
										style="width: 100%" value="${tbUser.stuNo}" data-options="required:true,prompt:'请输入正确的邮箱格式',validType:['stuNo','length[0,100]']">
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-2" align="right">手机</div>
								<div class="san-col-3">
									<input id="phoneMod" name="phone" class="easyui-numberbox"
										style="width: 100%;" value="${tbUser.phone}" data-options="required:true,prompt:'请输入正确的手机格式',validType:['mobilePhone','length[0,100]']">
								</div>
								<div class="san-col-1"></div>
							</div>
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">备注</div>
								<div class="san-col-3" style="width:75%">
									<input id="remarkMod" name="stuclass" class="easyui-textbox"
										style="width: 100%;" data-options="multiline:true,height:'100px',validType:'length[0,500]'" value="${tbUser.stuclass}">
								</div>
								<div class="san-col-1"></div>
							</div>
						</div>
						<!-- section-f-body end -->
					</form>
					<div class="section-f-foot AR">
						 <a
							id="saveInModUser" href="#"
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
			$.parser.parse('#main-page-body-content');

			//初始化性别下拉框
 			$("#sexMod").combobox({
				valueField:'id', //值字段
				textField:'text', //显示的字段
				url:'user/sexList',
				panelHeight:'auto',
				required:false,
				editable:false,//不可编辑，只能选择
				onLoadSuccess:function(){
					$("#sexMod").combobox("setValue", "${tbUser.sex}");
				}
			}); 
			
 			//初始化状态下拉框
 			$("#userStatusMod").combobox({
				valueField:'id', //值字段
				textField:'text', //显示的字段
				url:'user/statusList',
				panelHeight:'auto',
				required:false,
				editable:false,//不可编辑，只能选择
				onLoadSuccess:function(){
					$("#userStatusMod").combobox("setValue", "${tbUser.userStatus}");
				}
			}); 
			 $("#userIdMod").val("${tbUser.userId}"); 
			
			//初始化新增表单
			$("#modUser").form({
				url : "user/updateMyMessage",
				type : "POST",
				success : function(data) {
					var result = eval("(" + data + ")");
					if (result.modFlag) {
						showSuccessMsgDia("      " + result.msg + "\n用户名在下次登录生效");
						
					}else {
						showFailureMsgDia(result.msg);
					}
				}
			});

			//新增页面保存按钮
			$("#saveInModUser").bind("click", function() {
				showConfirmDia("您确定要更新吗？", function(){
				//新增表单提交
				$("#modUser").form("submit");
				})
			});
		});
	</script>



</body>
</html>