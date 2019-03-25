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
				<div class="section-a-title h5">用户信息修改</div>
			</div><!-- section-a-head end -->
			<div class="section-a-body">
				<div class="section-f section-f-fixed-800 m-t-10">
					<div class="section-f-head">
						<div class="h4">用户信息修改</div>
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
										style="width: 100%;" data-options="required:true,validType:['english','length[1,100]']" value="${tbUserVo.tbUser.account}">
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-2" align="right">姓名</div>
								<div class="san-col-3">
									<input id="userCnameMod" name="userCname" class="easyui-textbox"
										   style="width: 100%" value="${tbUserVo.tbUser.userCname}" data-options="required:true,validType:['chs','length[1,100]']">
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
								<div class="san-col-1"></div>
							</div>
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">手机</div>
								<div class="san-col-3">
									<input id="phoneMod" name="phone" class="easyui-numberbox"
										style="width: 100%;" value="${tbUserVo.tbUser.phone}" data-options="required:true,prompt:'请输入正确的手机格式',validType:['mobilePhone','length[0,100]']">
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-2" align="right">角色</div>
								<div class="san-col-3">
									<select id="roleNameMod" name="roleId" class="easyui-combobox"
											style="width: 100%;">
									</select>
								</div>
							</div>
							<div class="san-row section-f-row" id="student_id_flag">
								<div class="san-col-2" align="right">学号</div>
								<div class="san-col-3">
									<input id="stuNoMod" name="stuNo" class="easyui-textbox"
										   style="width: 100%" value="${tbUserVo.tbUser.stuNo}" disabled="true">
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-2" align="right">班级</div>
								<div class="san-col-3">
									<select id="stuClass" name="stuclass" class="easyui-combobox"
									style="width: 100%" value="${tbUserVo.tbUser.stuclass}" disabled="true">
									</select>
								</div>
							</div>
						</div>
						<!-- section-f-body end -->
					</form>
					<div class="section-f-foot AR">
						<a id="cancelInModUser" href="#"
							class="easyui-linkbutton mw-70 m-r-10">取消</a> <a
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
			//$.parser.parse('#main-page-body-content');

			//初始化性别下拉框
 			$("#sexMod").combobox({
				valueField:'id', //值字段
				textField:'text', //显示的字段
				url:'user/sexList',
				panelHeight:'auto',
				required:false,
				editable:false,//不可编辑，只能选择
				onLoadSuccess:function(){
					$("#sexMod").combobox("setValue", "${tbUserVo.tbUser.sex}");
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
					$("#userStatusMod").combobox("setValue", "${tbUserVo.tbUser.userStatus}");
				}
			});
       if("${tbUserVo.roleName}"!="学生"){
           $("#student_id_flag").hide()
            }else{
                $("#student_id_flag").show()
            }
            //初始化状态下拉框
            $("#stuClass").combobox({
                valueField:'id', //值字段
                textField:'text', //显示的字段
                url:'user/classList',
                panelHeight:'auto',
                required:false,
                editable:false,//不可编辑，只能选择
                onLoadSuccess:function(){
                    $("#stuClass").combobox("setValue", "${tbUserVo.tbUser.stuclass}");
                }
            });

            //初始化状态下拉框
            $("#roleNameMod").combobox({
                valueField:'id', //值字段
                textField:'text', //显示的字段
                url:'user/roleNameList',
                panelHeight:'auto',
                required:false,
                editable:false,//不可编辑，只能选择
                onLoadSuccess:function(){
                    $("#roleNameMod").combobox("setValue", "${tbUserVo.roleName}");
                }
            });
			 $("#userIdMod").val("${tbUserVo.tbUser.userId}");
			
			//初始化新增表单
			$("#modUser").form({
				url : "user/doUpdateUser",
				type : "POST",
				success : function(data) {
					var result = eval("(" + data + ")");
					if (result.modFlag) {
						showSuccessMsgDia(result.msg);
						$('#userTable').datagrid("reload");
						closeWindow();
					}else {
						showFailureMsgDia(result.msg);
					}
				}
			});
			
			//新增成交单页面取消按钮
			$("#cancelInModUser").bind("click", function() {
				closeWindow();
			});

			//新增页面保存按钮
			$("#saveInModUser").bind("click", function() {
				//新增表单提交
				$("#modUser").form("submit");
			});
		});
	</script>



</body>
</html>