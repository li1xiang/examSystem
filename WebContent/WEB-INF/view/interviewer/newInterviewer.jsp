
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%-- 
<%@ include file="../../../../taglibs.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增面试者信息</title>
</head>
<body>
	<%-- <jsp:include page="../common/commonDia.jsp" /> --%>
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="section-a-title h5">面试者信息录入</div>
			</div>
			<!-- section-a-head end -->

			<div class="section-a-body">
				<div class="section-f section-f-fixed-800 m-t-10">
					<div class="section-f-head">
						<div class="h4">面试者信息录入</div>
					</div>
					<!-- section-f-head end -->
					<form id="interviewerForm" method="POST" enctype="multipart/form-data">
						<div class="section-f-body">
							<!-- <div class="san-row section-f-row">
								<div class="san-col-2" align="right">编号</div>
								<div class="san-col-3">
									<input id="id" name="id" class="easyui-textbox"
										style="width: 100%;">
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-2" align="right"></div>
								<div class="san-col-3"></div>
								<div class="san-col-1"></div>
							</div> -->
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">姓名</div>
								<div class="san-col-3">
									<input id="name" name="name" class="easyui-textbox" style="width: 100%;" data-options="required:true,validType:'length[0,128]'">
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-2" align="right">出生日期</div>
								<div class="san-col-3">
									<input name="birth" type="text" class="easyui-datebox" style="width: 100%;" data-options="editable:false">
								</div>
								<div class="san-col-1"></div>
							</div>
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">手机号</div>
								<div class="san-col-3">
									<input id="phone" name="phone" class="easyui-textbox" style="width: 100%" data-options="required:true,prompt:'请输入正确的手机格式',validType:['mobilePhone','length[0,100]']">
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-2" align="right">学历</div>
								<div class="san-col-3">
									<input id="education" name="education" class="easyui-textbox"
										style="width: 100%" data-options="validType:'length[0,32]'">
								</div>
								<div class="san-col-1"></div>
								
							</div>
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">专业</div>
								<div class="san-col-3">
									<input id="major" name="major" class="easyui-textbox" style="width: 100%" data-options="validType:'length[0,128]'">
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-2" align="right">工作年限</div>
								<div class="san-col-3">
									<input id="workingYears" name="workingYears" class="easyui-numberbox"
										style="width: 100%" data-options="validType:'length[0,2]'">
									<div class="san-col-1"></div>
								</div>
							</div>
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">面试者来源</div>
								<div class="san-col-3">
									<select id="sourceInNew" name="source" class="easyui-combobox"
										style="width: 100%;">
									</select>
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-2" align="right">公司</div>
								<div class="san-col-3">
									<input id="company" name="company" class="easyui-textbox" 
										style="width: 100%" data-options="validType:'length[0,255]'">
								</div>
								<div class="san-col-1"></div>
							</div>
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">简历附件</div>
								<div class="san-col-3" style="width:75%">
									<input name="attachFile" class="easyui-filebox" style="width: 100%" data-options="buttonText:'选择文件'">
								</div>
								<div class="san-col-1"></div>
							</div>
						</div>
						<!-- section-f-body end -->
					</form>
					<div class="section-f-foot AR">
						<a id="backToInterviewerManage" href="#"
							class="easyui-linkbutton mw-70 m-r-10">返回</a> 
						<a id="saveInNewInterviewer" href="#"
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

			//初始化新增表单
			$("#interviewerForm").form({
				url : "interviewer/newInterviewer/save",
				type : "POST",
				resetForm: true,
				success : function(data) {
					var result = eval("(" + data + ")");
					if (result.saveFlag) {
						showSuccessMsgDia(result.msg);
						$('#interviewerTable').datagrid("reload");
						$("#interviewerForm").form('reset');
					}else {
						showFailureMsgDia(result.msg);
					}
				}
			});
			
			//初始化性别下拉框
 			$("#sourceInNew").combobox({
				url:"interviewer/sourceListInNew",
				valueField:"id",
				textField:"text",
				panelHeight:'auto',
				required:false,
				editable:false//不可编辑，只能选择
			});

			//新增页面保存按钮
			$("#saveInNewInterviewer").bind("click", function() {
				console.info($("#interviewerForm").form().serialize());
				//新增表单提交
				$("#interviewerForm").form("submit");
			});
			
			$("#backToInterviewerManage").bind("click",function(){
				closeWindow();
			})
		});
	</script>



</body>
</html>