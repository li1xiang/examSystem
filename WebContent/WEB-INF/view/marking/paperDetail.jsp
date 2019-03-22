
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>试卷</title>
</head>
<body>
	<jsp:include page="../common/commonDia.jsp" />
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="section-a-title h5">试卷</div>
			</div>
			<!-- section-a-head end -->

			<div class="section-a-body">
				<div class="section-f section-f-fixed-800 m-t-10">
					<!-- section-f-head end -->
					<form id="interviewerForm" method="POST"
						enctype="multipart/form-data">
						<input type="hidden" name="paperId" id="paperId" value="${paperId }"/>
						<div id="questions"></div>
						<!-- section-f-body end -->
					</form>
					<div class="section-f-foot AR">
						<a id="cancel" href="#"
							class="easyui-linkbutton mw-70 m-r-10">取消</a> <a
							id="saveInNewInterviewer" href="#"
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
			//$.parser.parse('#main-page-body-content');
			var paper = ${paper};
			var paperHtml = "";
			for(var i=0 ;i<paper.length; i++){
				var questions = paper[i];
				var question = questions['question'];
				var answers = questions['answers'];//答案数组
				var selectedAnswer = questions['selectedAnswer'];
				
				paperHtml += "<div style='border:0'>";
				paperHtml += question.questionTitle;
				paperHtml += "<br/>";
				if(question.questionImgName != null && question.questionImgName != ""){
					paperHtml += "<img src='${ctx}"+ question.questionImgPath + "'/> <br/>";
				}
				//如果是客观题
				if(question.questionType == '1'){
					for (var j = 0; j < answers.length; j++) {
						var answer = answers[j];
						if(selectedAnswer != undefined && answer.answerId == selectedAnswer.answerId)
							paperHtml += "&nbsp;&nbsp;<label><input type='radio' name='answerId' value='"+ answer.answerId +"' checked='checked'/>"+answer.answerText+"</label><br>";
						else
							paperHtml += "&nbsp;&nbsp;<label><input type='radio' name='answerId' value='"+ answer.answerId +"'/>"+answer.answerText+"</label><br>";
					}
				}else{
					paperHtml += "<input name='subjectiveAnswer' class='easyui-textbox' data-options='multiline:true' style='width:700px;height:150px' value='"+selectedAnswer+"'>"
				}
				paperHtml += "</div>"; 
			}
			$("#questions").html(paperHtml);
			//初始化修改表单
			$("#interviewerForm").form({
				url : "interviewer/doUpdateInterviewer",
				type : "POST",
				resetForm : true,
				success : function(data) {
					var result = eval("(" + data + ")");
					if (result.modFlag) {
						showSuccessMsgDia(result.msg);
						$('#interviewerTable').datagrid("reload");
						closeWindow();
					} else {
						showFailureMsgDia(result.msg);
					}
				}
			});

			//修改页面页面保存按钮
			$("#saveInNewInterviewer").bind("click", function() {
				//新增表单提交
				$("#interviewerForm").form("submit");
			});

			//修改面试者页面取消按钮
			$("#cancel").bind("click", function() {
				closeWindow();
			});

		});
	</script>
</body>
</html>