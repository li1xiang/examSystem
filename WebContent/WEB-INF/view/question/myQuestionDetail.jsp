<!-- 试题明细 -->
<%@page import="saptacims.cst.Status"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<%-- <%@ include file="../common/commonDia.jsp" %> --%>
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="san-row">
					<div class="san-col-9">
						<div class="section-a-title h5">试题明细</div>
					</div>
					<!-- <div class="san-col-3 AR">
						<a id="addUserBtn" href="#" class="easyui-linkbutton m-t-8 mw-70 z-easyui-bg-primary">用户录入</a>
					</div> -->
				</div>
				<!-- san-row end -->
			</div>
			<!-- section-a-head end -->

			<div class="section-a-body p-t-10">
				<div class="headline-a">
					<span class="headline-a-text">题目明细</span>
				</div>
				<div class="section-f">
					<div class="section-f-body">
								<div class="san-row section-f-row">
									<div class="san-col-1" align="right">题型：</div>
									<div class="san-col-2">
										<p>${questionDetail.questionType==1?'客观题':'主观题'}</p>
									</div>
									<div class="san-col-1"></div>
									<div class="san-col-1" align="right">科目：</div>
									<div class="san-col-2">
										<p>${questionDetail.categoryName}</p>								
									</div>
									<div class="san-col-1"></div>
									<div class="san-col-1" align="right">年级：</div>
									<div class="san-col-2">
										<p>${questionDetail.groupName}</p>
									</div>
									<div class="san-col-1"></div>
								</div>
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-1" align="right">难度：</div>
								<div class="san-col-2">
									<p>${questionDetail.levels==1?'高级':(questionDetail.levels==2?'中级':'初级')}</p>
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-1" align="right">分数：</div>
								<div class="san-col-2">
									<p>${questionDetail.score}</p>							
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-1" align="right">状态：</div>
								<div class="san-col-2">
									<p>${questionDetail.active==0?'禁用':'启用'}</p>
								</div>
								<div class="san-col-1"></div>								
							</div>
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-1" align="right">创建人：</div>
								<div class="san-col-2">
									<p>${questionDetail.createUserName}</p>
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-1" align="right">创建时间：</div>
								<div class="san-col-2">
									<p>${questionDetail.createTimestr}</p>							
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-1" align="right"></div>
								<div class="san-col-2">
								</div>
								<div class="san-col-1"></div>								
							</div>
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-1" align="right">题目：</div>
								<div class="san-col-10" style="word-wrap:break-word">
									${questionDetail.subject}
								</div>
								<div class="san-col-1"></div>
							</div>
							<c:if test="${not empty questionDetail.subjectImg}">
								<div class="san-row section-f-row">
									<div class="san-col-1" align="right">附件：</div>
									<div class="san-col-3" >
									<img src="${ctx}${questionDetail.subjectImg}">
									</div>
									<div class="san-col-8"></div>
								</div>
							</c:if>
							<!-- san-row end -->
						</div>
					<!-- section-b-body end -->
				</div>
				<!-- section-b end -->

				<div class="headline-a">
					<span class="headline-a-text">答案明细</span>
				</div>
				<div class="section-f" >
				<c:if test="${questionDetail.questionType==0}">
					<div class="section-f-body" id="subjectDiv">								
							<!-- san-row end -->
							<div class="san-row section-f-row">							
								<div class="san-col-1" align="right">参考答案：</div>
								<div class="san-col-10" style="word-wrap:break-word">
									${answerDetail.answer}
								</div>
								<div class="san-col-1"></div>
							</div>
							<c:if test="${not empty answerDetail.answerImg}">
								<div class="san-row section-f-row">
									<div class="san-col-1" align="right">附件：</div>
									<div class="san-col-3" >
									<img src="${ctx}${answerDetail.answerImg}">
									</div>									
									<div class="san-col-8"></div>
								</div>
							</c:if>
					</div>
					</c:if>
					<c:if test="${questionDetail.questionType==1}">
					<div class="section-b-body section-b-161011" id="objectDiv">
						<div class="section-b-161011-inner">															
							<table id="answerTable" style="width: 100%; height: 150px"></table>
						</div>	
					</div>										
					</c:if>
					<!-- section-b-body end -->
				</div>
				<!-- section-b end -->
				<c:if test="${action eq 'update'}">
				<div class="section-f-foot">
					<a href="#" onclick="updateQuestion();"
						class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">编辑题目</a>
					<a href="#" onclick="lastPage();"
						class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">返回</a>
				</div>
				</c:if>
				<c:if test="${action eq 'select'}">
				<div class="section-f-foot">
					<a href="#" onclick="lastPage();"
						class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">返回</a>
				</div>
				</c:if>
			</div>
			<!-- section-a-body end -->
		</div>
		<!-- section-a end -->
	</div>
	<!-- sub-page end -->
	<!-- <div id="subpage-list-ecc" class="easyui-calendar"></div> -->

	<script type="text/javascript">
		$(function() {
			$.parser.parse("#main-page-body-content");	
						
			//列宽设置为%，并完美100%，必须用js方式
			$("#answerTable").datagrid({
								url : "answer/answerList?questionId=${questionDetail.questionId}",
								method : "POST",
								rownumbers : true,
								striped : true,					
								columns : [ [
							   			{
							   				field : "isright",
											title : "",
											halign : "center",
											width : "5%",
											align:'center',
											resizable : false,
											formatter:function(val,row,index)
											{
												var s;
												if(row.isright == 1)
												{
													return '<a href="#" class="enablecls"></a>';
												}
											}
							   			},
							   			{
											field : "answer",
											title : "答案内容",
											halign : "center",
											width : "95%",
											align:'left',
											resizable : false
							   			} ] ],
								onLoadSuccess : function(data) {
									$(".disabledcls").linkbutton({
										text:'',
										plain : true,
										iconCls : 'icon-cancel',
										height : '18px'
									});
									$(".enablecls").linkbutton({
										text:'',
										plain : true,
										iconCls : 'icon-ok',
										height : '18px'
									});
									
								}
							});
		});	
		//返回
		function lastPage(){
			$('#main-page-body-content').sanJumpto({
				url:'question/getMyQuestionManage'
			});
		}
		//题目修改
		function updateQuestion(){ 
			showWindow({
				title:'修改题目信息',
				href:'question/getMyUpdateQuestion?questionId=${questionDetail.questionId}',
				width:1000,
				height:'auto'
			});
		}
		
	</script>
</body>
</html>