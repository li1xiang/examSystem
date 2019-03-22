<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%-- 
<%@ include file="../../../../taglibs.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增用户信息</title>
</head>
<body>
	<%-- <jsp:include page="../common/commonDia.jsp" /> --%>
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="section-a-title h5">账户管理 > 新增用户 </div>
			</div>
			<!-- section-a-head end -->

			<div class="section-a-body">
				<div class="section-f section-f-fixed-800 m-t-10">
					<div class="section-f-head">
						<div class="h4">用户信息录入</div>
					</div>
					<!-- section-f-head end -->
					<form id="saveUser" method="POST" action="">
						<div class="section-f-body">
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">登录名</div>
								<div class="san-col-3">
									<input id="account" name="account" maxlength="10" class="easyui-textbox"  style="width: 100%;" data-options="required:true,validType:['english','length[1,100]']"  >
									<!-- ,validType:['english','length[1,100]'] -->
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-2" align="right">登录密码</div>
								<div class="san-col-3">
									<input id="userPassword" name="userPassword" class="easyui-passwordbox"
										prompt="密码" iconWidth="28" value="123456" data-options="required:'true',missingMessage:'该输入项为必输项'"
										style="width: 100%; height: 24px; padding: 10px">
								</div>
								<div class="san-col-1"></div>
							</div>
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">姓名</div>
								<div class="san-col-3">
									<input id="userCname" name="userCname" class="easyui-textbox" style="width: 100%" data-options="required:true,validType:['chs','length[1,100]']">
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-2" align="right">性别</div>
								<div class="san-col-3">
									<select id="sex" name="sex" class="easyui-combobox"
											style="width: 100%;" data-options="required:true,editable:false"  >
									</select>
								</div>
								<div class="san-col-1"></div>
								
							</div>
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">角色</div>
								<div class="san-col-3">
									<select id="roleName" name="roleId" class="easyui-combobox"
											style="width: 100%;" data-options="required:true,editable:false">
									</select>
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-2" align="right">手机</div>
								<div class="san-col-3">
									<input id="phone" name="phone" class="easyui-numberbox" data-options="required:true,prompt:'请输入正确的手机格式',validType:['mobilePhone','length[0,100]']"
										style="width: 100%;">
								</div>
								<div class="san-col-1"></div>
							</div>
							<div class="san-row section-f-row" id="StuentInfo">
								<div class="san-col-2" align="right">学号</div>
								<div class="san-col-3">
									<input id="stuNo" name="stuNo" class="easyui-textbox" style="width: 100%" data-options="required:true,validType:['number','length[1,100]']">
								</div>
								<div class="san-col-1"></div>
									<div class="san-col-2" align="right">班级</div>
									<div class="san-col-3">
										<select id="stuclass" name="stuclass" class="easyui-combobox"
												style="width: 100%;" data-options="required:true,editable:false">
										</select>
									</div>
								<div class="san-col-1"></div>
							</div>
						</div>
						<!-- section-f-body end -->
					</form>
					<div class="section-f-foot AR">
							<a id="saveInNewUser" href="#"
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
	<!-- <div id="subpage-list-ecc" class="easyui-calendar"></div> -->
	<script>
		$(function() {
			$.parser.parse('#main-page-body-content');
            $("#roleName").combobox({
                valueField:'id', //值字段
                textField:'text', //显示的字段
                url:'user/roleNameList',
                panelHeight:'auto',
                required:false,
                editable:false,//不可编辑，只能选择
				onChange: function(){
                  if($("#roleName").combobox('getText')!="学生"){
                      $("#StuentInfo").hide();
				  }else{
                      $("#StuentInfo").show();
				  }
				}
            });
            //初始化新增表单
			$("#saveUser").form({
				url : "user/save",
				type : "POST",
				resetForm: true,
				success : function(data) {
					var result = eval("(" + data + ")");
					if (result.saveFlag) {
						showSuccessMsgDia(result.msg);
						postSanJumpTo("user/newUser");
					}else {
						showFailureMsgDia(result.msg);
					}
				}
			});
			
			//初始化性别下拉框
 			$("#sex").combobox({
				valueField:'id', //值字段
				textField:'text', //显示的字段
				url:'user/sexList',
				panelHeight:'auto',
				required:true,
				editable:false//不可编辑，只能选择
			});
            //初始化班级下拉框
            $("#stuclass").combobox({
                valueField:'id', //值字段
                textField:'text', //显示的字段
                url:'user/classList',
                panelHeight:'auto',
                required:true,
                editable:false//不可编辑，只能选择
            });


            //新增页面保存按钮
			$("#saveInNewUser").bind("click", function() {
				$("#saveUser").form("submit");
			});
		});
	</script>



</body>
</html>