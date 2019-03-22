<!-- 答卷预览 -->
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
</style>
<title>答卷预览</title>
</head>
<body>
	<%@ include file="../common/commonDia.jsp"%>
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="san-row">
					<div class="san-col-9">
						<div class="section-a-title h5">答卷</div>
					</div>
				</div>
				<!-- san-row end -->
			</div>
			<form id="paperDetailForm" method="post">
				<input type="hidden" id="paperId" name="paperId" value="${paperId }">
				<input type="hidden" id="operType" name="operType" value="">
				<!-- section-a-head end -->
				<div class="section-a-body p-t-10" >
					<div class="headline-a">
						<span class="headline-a-text">答卷</span>
					</div>
					<div align="center" style="width: 900px; height: 'auto'">
						<div><span style="width:100%;font-size:200%;text-align:center;" >${paper.examinationName}</span></div>
						<div style="display:inline;font-size:15">
							<div style="float:left;margin-left:15">答卷人:&nbsp;${paper.paperUserName}</div>
							<div style="float:right;margin-right:30">总分：&nbsp;${paper.score }</div>
						</div>
					</div>
					<br>
					<br>
					<div id="questionText" style="width: 900px; height: 'auto';">
						<c:forEach items="${paper.questionList }" var="question" varStatus="s">
							<div style="display:inline">	
								<div style="float:left;margin-left:0">${s.index+1 }.&nbsp;${question.questionTitle } </div>
								<div style="margin-right:15;float:right">得分：&nbsp;${question.paperDetail.score }</div><br>
							</div>
							<c:if test="${not empty question.questionImgPath}">
								<img src='${ctx}${question.questionImgPath}'/> <br/>
							</c:if>
							<c:if test="${question.questionType == 1 }">
								<c:forEach items="${question.options }" var="option">
									<div style="display:inline">
										<div style="width:10;float:left"><c:if test="${option.isright ==1 }"><img src="${ctx }/css/sge/icons/ok.png" ></c:if></div>
										<div style="width:10;float:left"><c:if test="${option.isright ==0 }"><div style="width:16">&nbsp;</div></c:if></div>
										<c:if test="${option.answerId ==question.paperDetail.answerId }"><div style="margin-left:15;float:left"><label><input type="radio" value="${option.answerId }" checked="checked" disabled="disabled"> ${option.answerText }</label></div><br></c:if>
										<c:if test="${option.answerId !=question.paperDetail.answerId }"><div style="margin-left:15;float:left"><label><input type="radio" value="${option.answerId }" disabled="disabled"> ${option.answerText }</label></div><br></c:if>
									</div>
								</c:forEach>
							</c:if>
							<c:if test="${question.questionType == 0 }">
								<div style="display:inline">
									<div style="width:16;float:left">&nbsp;</div>
									<input id='subjectiveAnswer' name='subjectiveAnswer' class='easyui-textbox' data-options='multiline:true' disabled='disabled' value='${question.paperDetail.subjectiveAnswer }' style='width:700px;height:150px'><br>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
				<!-- section-a-body end -->
			</form>
		</div>
		<!-- section-a end -->
	</div>
	<!-- sub-page end -->
</body>
</html>