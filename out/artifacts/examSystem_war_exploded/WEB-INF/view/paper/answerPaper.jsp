<!-- 答卷页面 -->
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
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<%-- <%@ include file="../../../../taglibs.jsp" %> --%>
	<%-- <%@ include file="../common/commonDia.jsp"%> --%>
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="san-row">
					<div class="san-col-9">
						<div class="section-a-title h5">笔试答题</div>
					</div>
				</div>
				<!-- san-row end -->
			</div>
			<form id="paperDetailForm" method="post">
				<input type="hidden" id="paperId" name="paperId" value="${paperId }">
				<input type="hidden" id="operType" name="operType" value="">
				<!-- section-a-head end -->
				<div class="section-a-body p-t-10">
					<div class="headline-a">
						<span class="headline-a-text">题号</span>
					</div>
					<div class="section-b">
						<div class="section-b-body section-b-161011">
							<table border="0" width="80%">
								<tr align="center">
									<c:forEach items="${questions }" var="question"
										varStatus="status">
										<c:if test="${(status.index) mod 10 == 0}">
											<tr>
										</c:if>
										<c:if test="${question.examinationQuestionId ==-1 }">
											<td></td>
											<td></td>
										</c:if>
										<c:if test="${question.examinationQuestionId >=0 }">
											<td width="10px" style="text-align: right">${status.index+1 }.</td>
											<td style="text-align: left"><input type="radio"
												id="question_${status.index }"
												onClick="showQuestion(${question.examinationQuestionId})"
												name="questionId" value="${question.examinationQuestionId}"></td>
										</c:if>
										<c:if test="${(status.index) mod 10 == 9}">
								</tr>
								</c:if>
								<%--< c:if test="${(status.count) mod 10 != 1}">
											<td width="5px" align="right">${status.index+1 }.</td>
											<td align="left"><input type="radio" id="questionId"
												onClick="showQuestion(${question})" name="questionId"
												value="${question}"></td>
										</c:if>
										<c:if test="${(status.count) mod 10 == 1}">
											<tr>
												<td width="5px" align="right">${status.index+1 }.</td>
												<td><input type="radio" id="questionId"
													onClick="showQuestion(${question})" name="questionId"
													value="${question}"></td>
										</c:if> --%>
								</c:forEach>
								</tr>
							</table>
						</div>
						<!-- section-b-body end -->
					</div>
					<!-- section-b end -->

					<div class="headline-a">
						<span class="headline-a-text">答卷题目(<font color="red">交卷前请先保存当前题目</font>)
						</span>
					</div>
					<div id="questionText" style="width: 900px; height: 'auto'">

					</div>

					<!-- section-b end -->
					<!-- <a href="#" id="backToChoosePaper"  class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">返回</a> -->
					<a href="#" onclick="nextQuestion();"
						class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">保存并到下一题</a>
					<a href="#" onclick="saveAnswer();"
						class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">保存</a>
					<a href="#" onclick="submitPaper();"
						class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">交卷</a>
				</div>
				<!-- section-a-body end -->
			</form>
		</div>

		<!-- section-a end -->
	</div>
	<!-- sub-page end -->
	<!-- <div id="subpage-list-ecc" class="easyui-calendar"></div> -->

	<script type="text/javascript">
		var examinationList = "";
		var selectQuestionId = null;//选中radio的题号
		$(function() {
			$.parser.parse("#main-page-body-content");
			$("input[name='questionId']").eq(0).prop("checked",true);
			selectQuestionId = $("input[name='questionId']:checked").val();
		 	showQuestion(selectQuestionId);
			
			
			$("#paperDetailForm").form({
				url:"paper/nextQuestion",
				type:"POST",
				success : function(data){
					//if($("#operType").val()=="save"){
						var result = eval("("+data+")");
						if (result.saveFlag) {
							showSuccessMsgDia(result.msg);
							if($("#operType").val()=="next"){
								//显示下一题
								showQuestion(findNextQuestionID());
								//选中下一个radio
								//checkNextRadio();
							}
						}else {
							showFailureMsgDia(result.msg);
							return;
						}
					//}
				}
			});

		});

		//点击题号显示题目信息
		function showQuestion(questionId) {
			if(questionId == null)
				return;
			$.ajax({
				url:"paper/showQuestion",
				type:"POST",
				data:{
						questionId:questionId,
						paperId:$("#paperId").val()
					},
				success : function(data){
						$("#questionText").html();
						
						$("input[name='questionId']").prop("checked",false);
						$("input[value='"+questionId+"']").prop("checked",true);
						var questionHtml = "";//题目Html
						var answerHtml = "";//答案Html
						var question = data['question'];//题目信息
						var selectedAnswer = data['selected'];
						questionHtml += "<div style='border:0'>";
						questionHtml += question.questionTitle;
						questionHtml += "<input type='hidden' name='questionType' value='"+question.questionType+"'>";
						questionHtml += "<br/>";
						if(question.questionImgName != null && question.questionImgName != ""){
							questionHtml += "<img src='${ctx}"+ question.questionImgPath + "'/><br/>";
						}
						//如果是客观题
						if(question.questionType == '1'){
							var answers = data['answers'];//答案列表
							for (var j = 0; j < answers.length; j++) {
								var answer = answers[j];
								if(answer.answerId == selectedAnswer)
									answerHtml += "&nbsp;&nbsp;<label><input type='radio' name='answerId' value='"+ answer.answerId +"' checked='checked'/>"+answer.answerText+"</label><br>";
								else
									answerHtml += "&nbsp;&nbsp;<label><input type='radio' name='answerId' value='"+ answer.answerId +"'/>"+answer.answerText+"</label><br>";
							}
						}else{
							if(selectedAnswer != null)
								answerHtml += "<input id='subjectiveAnswer' name='subjectiveAnswer' class='easyui-textbox' data-options='multiline:true' value='"+selectedAnswer+"' style='width:700px;height:150px'>"
							else
								answerHtml += "<input id='subjectiveAnswer' name='subjectiveAnswer' class='easyui-textbox' data-options='multiline:true' value='' style='width:700px;height:150px'>"
						}
						answerHtml += "</div>"; 
						$("#questionText").html(questionHtml + answerHtml);
						$.parser.parse();
				}
			})
		}

		//检验是否答题
		function checkAnswer(){
			//主观题是否已经输入答案
		 	var subjectiveAnswer = $("#subjectiveAnswer").val();
		 	if(subjectiveAnswer != null && subjectiveAnswer != "")
		 		return true;
			//客观题是否已选择答案
			var answers = $("input[name='answerId']");
		 	for (i=0; i<answers.length; i++) {
		 		 if (answers[i].checked)
		 			return true;
		 	}
			return false;
		}
		//获取下一道题目ID
		function findNextQuestionID() {
			var checkedRadio = $("input[name='questionId']:checked");
			var radioId = checkedRadio.attr("id");
			var questionIds = $("input[name='questionId']");
			for (i=0; i<questionIds.length; i++) {
		        if (questionIds[i].checked) {
		        	//返回选中的下一个radio的值
		        	return $(questionIds[parseInt(i)+1]).val();
		        }
		    }
		}
		//保存、下一题入口
		function save(nextFlag){
			if(nextFlag)
				$("#operType").val("next");
			else
				$("#operType").val("");
			if(checkAnswer())
		 		$("#paperDetailForm").submit();
			else{
				//提示未做题
				showFailureMsgDia("请先答题后再保存!");
				return;
			}
		}
		//选中下一个radio
		function checkNextRadio(){
			console.info("选中下一个radio");
			var questionIds = $("input[name='questionId']");
			var checkedRadio = $("input[name='questionId']:checked");
			var radioId = checkedRadio.attr("id");
		    $("#" + radioId).removeAttr("checked");
			console.info(radioId);
			var nextRadioId = "question_" + (parseInt(radioId.split("_")[1])+1);
			if(parseInt(radioId.split("_")[1])+1 >= questionIds.length){
				showFailureMsgDia("已经是最后一题");
			}
			console.info(nextRadioId);
		    $("#" + nextRadioId).attr("checked","true");
		}
		
		//下一题按钮点击事件 
		function nextQuestion() {
			save(true);
		}
		
		//保存按钮点击事件
		function saveAnswer(){
			save(false);
		}
		
		
		//交卷
		function submitPaper(){
			$.ajax({
				url:"paper/submitPaper",
				type:"POST",
				data:{paperId:$("#paperId").val()},
				success:function(data){
					if (data.submitFlag) {
						showSuccessMsgDia(data.msg, "paper/choosePaper");
					}else {
						showFailureMsgDia(data.msg);
					}
				}
			})
		}

		function getDate(date) {
			var createDate = new Date(date);
			var year = createDate.getFullYear();
			var month = createDate.getMonth() + 1;
			var day = createDate.getDate();
			var hour = createDate.getHours();
			var minute = createDate.getMinutes();
			var second = createDate.getSeconds();
			if (parseInt(month) < 10)
				month = "0" + month;
			if (parseInt(day) < 10)
				day = "0" + day;
			return year + "-" + month + "-" + day + " "
					+ ((parseInt(hour) < 10) ? ("0" + hour) : hour) + ':'
					+ ((parseInt(minute) < 10) ? ("0" + minute) : minute) + ':'
					+ ((parseInt(second) < 10) ? ("0" + second) : second);
		}
	</script>
</body>
</html>