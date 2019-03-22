<!-- 阅卷页面 -->
<%@page import="saptacims.cst.Status"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../../taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.ql-left-td{ 
BORDER-RIGHT: #000000 0px solid; /* 显示右边框为1px，如果不想显示就为0px */   
BORDER-TOP: #000000 1px solid; /* 显示上边框为1px，如果不想显示就为0px */   
BORDER-LEFT: #000000 1px solid;/* 显示左边框为1px，如果不想显示就为0px */   
BORDER-BOTTOM: #000000 1px solid;/* 显下右边框为1px，如果不想显示就为0px */   
} 
.ql-right-td{ 
BORDER-RIGHT: #000000 1px solid; /* 显示右边框为1px，如果不想显示就为0px */   
BORDER-TOP: #000000 1px solid; /* 显示上边框为1px，如果不想显示就为0px */   
BORDER-LEFT: #000000 0px solid;/* 显示左边框为1px，如果不想显示就为0px */   
BORDER-BOTTOM: #000000 1px solid;/* 显下右边框为1px，如果不想显示就为0px */   
} 
.ql-td-gray{
	background-color:gray;
}
.ql-td-yellow{
	background-color:yellow;
}
.ql-td-blank{
	background-color:white;
}
</style>
<title>阅卷评分</title>
</head>
<body>
	<%@ include file="../common/commonDia.jsp"%>
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="san-row">
					<div class="san-col-9">
						<div class="section-a-title h5">阅卷评分</div>
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
						试卷名:${paper.examinationName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;答卷人:${paper.paperUserName }
					</div>
					<div class="section-b">
						<div class="section-b-body section-b-161011">
							<span class="headline-a-text">题号</span>
							<table border="0" width="80%">
								<c:forEach items="${paper.questionList }" var="question"
									varStatus="status">
									<!-- 默认主观题已打分 -->
									<c:set var="clsName" value="ql-td-blank"></c:set>
									<c:if test="${(status.index) mod 10 == 0}"><tr></c:if>
									<c:if test="${question.examinationQuestionId ==-1 }"><td></td><td></td></c:if>
									<c:if test="${question.examinationQuestionId >=0 }">
										<!-- 客观题灰色 -->
										<c:if test="${question.questionType == 1}">
											<c:set var="clsName" value="ql-td-gray"></c:set>
										</c:if>
										<!-- 主观题,并未打分,亮黄 -->
										<c:if test="${question.questionType == 0 and empty question.paperDetail.score}">
											<c:set var="clsName" value="ql-td-yellow"></c:set>
										</c:if>
											<td class="ql-left-td ${clsName }" style="text-align:right;">${status.index+1 }.<input type="radio" id="question_${status.index }"
												onClick="showQuestion(${question.examinationQuestionId})" name="questionId"
												value="${question.examinationQuestionId}"></td>
										
										<td id="score_${status.index }" style="text-align:center" class="ql-right-td ${clsName }">${question.paperDetail.score}</td>
									</c:if>
									<c:if test="${(status.index) mod 10 == 9}"></tr></c:if>
								</c:forEach>
							</table>
						</div>
						<!-- section-b-body end -->
					</div>
					<!-- section-b end -->
					<div class="headline-a">
						<span class="headline-a-text" id="dajuanText">答卷题目</span>
					</div>
					<div id="questionText" style="width: 900px; height: 'auto'">

					</div>
					<!-- section-b end -->
					<!-- <a href="#" id="backToChoosePaper"  class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">返回</a> -->
					<a href="#" onclick="nextQuestion();"
						class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">评分并到下一题</a>
					<a href="#" onclick="saveAnswer();"
						class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">保存</a>
					<a href="#" onclick="submitPaper();"
						class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">评分提交</a>
					<a href="#" onclick="showPaper();"
						class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">预览答卷</a>
					<div style="margin-top:5px">
						<%-- <span class="headline-a-text">评分总说明</span><br>
						<input class="easyui-textbox" id="scoreRemarks" name="scoreRemarks" data-options="multiline:true" style="width:700px;height:200px" value="${paper.scoreRemarks }"><br>
						<a href="#" onclick="submitPaper();"
							class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">评分提交</a> --%>
					</div>
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
			//$.parser.parse("#main-page-body-content");
			$("input[name='questionId']").eq(0).prop("checked",true);
			selectQuestionId = $("input[name='questionId']:checked").val();
		 	showQuestion(selectQuestionId);
			
			//返回按钮点击事件
			$("#backToChoosePaper").click(function() {
				//closeWindow();
			});
			
			$("#paperDetailForm").form({
				url:"nextQuestion",
				type:"POST",
				success : function(data){
					var result = eval("("+data+")");
					if (result.saveFlag) {
						showSuccessMsgDia(result.msg);
						refreshNewScore(result.score);
						
						//$("#totalScore").textbox("disable");
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
				}
			});
		});
		function refreshNewScore(score){
			var currentRadioId = $("input[name='questionId']:checked").attr("id");
			var index = currentRadioId.split("_")[1];
			var currentScoreTdId = "score_" + index;
			$("#score_" + index).text(score);
		}

		//点击题号显示题目信息
		function showQuestion(questionId) {
			if(questionId==null)
				return;
			$.ajax({
				url:"showQuestion",
				type:"POST",
				data:{
						questionId:questionId,
						paperId:$("#paperId").val()
					},
				success : function(data){
						$("#questionText").html();
						$("input[name='questionId']").prop("checked",false);
						if(questionId!=null)
							$("input[value='"+questionId+"']").prop("checked",true);
						var questionHtml = "";//题目Html
						var answerHtml = "";//答案Html
						var markingHtml = "";//评分
						var question = data['question'];//题目信息
						var selectedAnswer = data['selected'];
						console.info(selectedAnswer);
						var score = data['score'];
						$("#dajuanText").text("答卷题目(本题总分值为"+question.totalScore+")");
						var totalScore = "<input type='hidden' id='allScore' value='"+question.totalScore+"'>";
						if(question.questionType==1)
							markingHtml = "<div style='margin-right:300px'> 得分:<input class='easyui-numberbox' id='totalScore' name='score' style='width:50px' value="+(score == null?"":score)+"  disabled='disabled'></div>";
						else{
							if(score != null)
								markingHtml = "<div style='margin-right:300px'> 得分:<input class='easyui-numberbox' data-options='required:true' id='totalScore' name='score' style='width:50px' value="+score+" ></div>";
							else
								markingHtml = "<div style='margin-right:300px'> 得分:<input class='easyui-numberbox' data-options='required:true' id='totalScore' name='score' style='width:50px' value='' ></div>";
							
						}
						questionHtml += "<div style='border:0'>";
						questionHtml += totalScore + question.questionTitle + markingHtml;
						questionHtml += "<input type='hidden' name='questionType' value='"+question.questionType+"'>";
						questionHtml += "<br/>";
						if(question.questionImgName != null && question.questionImgName != ""){
							questionHtml += "<img src='${ctx}"+ question.questionImgPath + "'/> <br/>";
						}
						//如果是客观题
						if(question.questionType == '1'){
							var answers = data['answers'];//答案列表
							for (var j = 0; j < answers.length; j++) {
								var answer = answers[j];
								console.info(answer);
								if(answer.answerId == selectedAnswer){
									if(answer.isright == 1){
										//正确答案
										answerHtml += "&nbsp;&nbsp;<div style='width:10;float:left'><img src='${ctx }/css/sge/icons/ok.png' ></div><label style='width:800;'><input type='radio' name='answerId' value='"+ answer.answerId +"' disabled='disabled' checked='checked'/>"+answer.answerText+"</label><br>";
									}else{
										answerHtml += "&nbsp;&nbsp;<div style='width:10;float:left'>&nbsp;</div><label style='width:800;'><input type='radio' name='answerId' value='"+ answer.answerId +"' disabled='disabled' checked='checked'/>"+answer.answerText+"</label><br>";
									}
								}
								else{
									if(answer.isright == 1){
										answerHtml += "&nbsp;&nbsp;<div style='width:10;float:left'><img src='${ctx }/css/sge/icons/ok.png' ></div><label style='width:800;'><input type='radio' name='answerId' value='"+ answer.answerId +"' disabled='disabled'/>"+answer.answerText+"</label><br>";
									}else {
										answerHtml += "&nbsp;&nbsp;<div style='width:10;float:left'>&nbsp;</div><label style='width:800;'><input type='radio' name='answerId' value='"+ answer.answerId +"' disabled='disabled'/>"+answer.answerText+"</label><br>";
									}	
								}
							}
						}else{
							var answers = data['answers'];//主观题答案
							console.info(answers);
							if(selectedAnswer != null){
								answerHtml += "<input id='subjectiveAnswer' name='subjectiveAnswer' class='easyui-textbox' data-options='multiline:true' disabled='disabled' value='"+selectedAnswer+"' style='width:700px;height:150px'><br>";
							}
							else{
								answerHtml += "<input id='subjectiveAnswer' name='subjectiveAnswer' class='easyui-textbox' data-options='multiline:true' disabled='disabled' value='' style='width:700px;height:150px'><br>";
							}
							answerHtml += "参考答案：<br><input class='easyui-textbox' data-options='multiline:true' disabled='disabled' value='"+answers[0].answerText+"' style='width:700px;height:150px'><br>";
						}
						answerHtml += "</div>"; 
						$("#questionText").html(questionHtml + answerHtml);
						$.parser.parse();
				}
				
			})
		}

 		//检验是否有得分
		function checkScore(){
 			var score = $("#totalScore").val();
 			if(score==null || score=="")
 				return false;
			return true;
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
			//判断分数是否小于0
			var score = $("#totalScore").val();
			if(parseInt(score) < 0){
				showFailureMsgDia("得分不能小于0");
				return;
			}
			var totalScore = $("#allScore").val();
			if(checkScoreOutOfExpore(totalScore)){
				showFailureMsgDia("得分不能超过总分");
				return;				
			}
			if(checkScore())
		 		$("#paperDetailForm").submit();
			else{
				//提示未做题
				showFailureMsgDia("请先评分后再保存!");
				return;
			}
		}
		//选中下一个radio
		function checkNextRadio(){
			var questionIds = $("input[name='questionId']");
			var checkedRadio = $("input[name='questionId']:checked");
			var radioId = checkedRadio.attr("id");
			var nextRadioId = "question_" + (parseInt(radioId.split("_")[1])+1);
			if(parseInt(radioId.split("_")[1])+1 >= questionIds.length){
				showFailureMsgDia("已经是最后一题");
			}
		    $("#" + nextRadioId).attr("checked","checked");
		}
		//检查分数是否超过总分 超过则返回true
		function checkScoreOutOfExpore(totalScore){
			//当前得分
			var score = $("#totalScore").val();
			//若得分大于总分,返回false
			if(parseInt(score) > parseInt(totalScore))
				return true;
			return false;
		}
		
		//下一题按钮点击事件 
		function nextQuestion() {
			$("#totalScore").textbox("enable");
			var totalScore = $("#allScore").val();
			/* console.info(totalScore);
			if(!checkScoreOutOfExpore(totalScore)){
				showFailureMsgDia("得分不能超过这道题目的总分!");
				return;
			} */
			save(true);
		}
		
		//保存按钮点击事件
		function saveAnswer(){
			$("#totalScore").textbox("enable");
			/* var totalScore = $("#allScore").val();
			if(!checkScoreOutOfExpore(totalScore)){
				showFailureMsgDia("得分不能超过这道题目的总分!");
				return;
			} */
			save(false);
			
		}
		
		//交卷
		function submitPaper(){
			$.ajax({
				url:"markPaper",
				type:"POST",
				data:{paperId:$("#paperId").val()},
				success:function(data){
					if (data.markingFlag) {
						showSuccessMsgDia(data.msg);
						showPaper();
						window.close();				
					}else {
						showFailureMsgDia(data.msg);
					}
				}
			})
		}
		
		function showPaper(){
			window.open('showPaper?paperId='+$("#paperId").val(), "_blank");
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