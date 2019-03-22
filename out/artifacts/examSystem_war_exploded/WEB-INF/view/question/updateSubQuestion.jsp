<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改题目</title>
</head>
<body>
	<%-- <jsp:include page="../common/commonDia.jsp" / --%>
	<script type="text/javascript">	
	
		function onChangeQue(n, o) {
			if (n == '') {
				$("#imgDivQue").show();
				$("#deleteButtonQue").show();
			} else {
				$("#imgDivQue").hide();
				$("#deleteButtonQue").hide();
			}
		};

		function onChangeAn(n, o) {
			if (n == '') {
				$("#imgDivAn").show();
				$("#deleteButtonAn").show();
			} else {
				$("#imgDivAn").hide();
				$("#deleteButtonAn").hide();
			}
		};
	</script>
	<div class="sub-page">
		<div class="section-a">
			<!-- section-a-head end -->

			<div class="section-a-body">
				<div class="section-f  m-t-10">
					<div class="section-f-head">
						<div class="h4">主观题更新</div>
					</div>
					<!-- section-f-head end -->
					<form id="updateQuestion" method="POST" action="" enctype="multipart/form-data">
						<div class="easyui-tabs" data-options="tabWidth:112" >
							<div title="修改题目" style="padding: 10px" class="section-f-body">
								<input type="hidden" name="questionId" id="questionIdMod" /> 
								<input type="hidden" name="questionType" id="questionType" /> 
								<input type="hidden" name="subjectImg" id="subjectImg" value="${tbQuestion.subjectImg}" />
								<input type="hidden" name="delSubjectImg" id="delSubjectImg" value="N"/>
								<div class="san-row section-f-row">
									<div class="san-col-1" align="right">类别</div>
									<div class="san-col-2">
										<select id="categoryId" name="categoryId"
											class="easyui-combobox" style="width: 100%;"
											data-options="required:true,editable:false">
										</select>
									</div>
									<div class="san-col-1"></div>
									<div class="san-col-1" align="right">群组</div>
									<div class="san-col-2">
										<select id="groupId" name="groupId" class="easyui-combobox"
											style="width: 100%;"
											data-options="required:true,editable:false">
										</select>
									</div>
									<div class="san-col-1"></div>
									<div class="san-col-1" align="right">分数</div>
									<div class="san-col-2">
										<input id="score" name="score" class="easyui-numberbox"
											data-options="required:true" style="width: 100%"
											value="${tbQuestion.score}">
									</div>
									<div class="san-col-1"></div>
								</div>
								<!-- san-row end -->
								<div class="san-row section-f-row">
									<div class="san-col-1" align="right">难度</div>
									<div class="san-col-2">
										<label style="float:left;font-size:12px;"> <input type="radio" style="vertical-align:middle; margin-top:-2px; margin-bottom:1px;" value=3 name="levels" id="levels" >初级  &nbsp;</label>
										<label style="float:left;font-size:12px;"> <input type="radio" style="vertical-align:middle; margin-top:-2px; margin-bottom:1px;" value=2 name="levels" id="levels" >中级  &nbsp;</label>
										<label style="float:left;font-size:12px;"> <input type="radio" style="vertical-align:middle; margin-top:-2px; margin-bottom:1px;" value=1 name="levels" id="levels" >高级  &nbsp;</label>
									</div>
									<div class="san-col-1"></div>
									<div class="san-col-1"></div>
									<div class="san-col-2"></div>
									<div class="san-col-1"></div>
								</div>
								<!-- san-row end -->
								<div class="san-row section-f-row">
									<div class="san-col-1" align="right">题目</div>
									<div class="san-col-10">
										<input id="subject" name="subject" class="easyui-textbox"
											style="width: 100%;" 
											data-options="multiline:true,height:'100px',validType:'length[0,500]'"
											value="${tbQuestion.subject}">
									</div>
									<div class="san-col-1"></div>
								</div>
								<div class="san-row section-f-row">
									<div class="san-col-1" align="right">附件</div>
									<div class="san-col-3">
										<input id="questionFile" name="questionFile"
											class="easyui-filebox" style="width: 100%;"
											data-options="onChange:function(n,o){onChangeQue(n,o);},prompt:'上传图片',accept:'image/jpeg,image/png'" buttonText="选择文件"
											" >
									</div>
									<c:if test="${not empty tbQuestion.subjectImg}">
										<div class="san-col-8" id="deleteButtonQue">
											<a id="deleteQueImg" href="#"
												class="easyui-linkbutton mw-70 m-r-10">删除图片</a>
										</div>
									</c:if>
								</div>
								<div class="san-row section-f-row">
									<div class="san-col-1" align="right"></div>
									<c:if test="${not empty tbQuestion.subjectImg}">
										<div class="san-col-11" id="imgDivQue">
											<img src="${ctx}${tbQuestion.subjectImg}">
										</div>
									</c:if>
								</div>
							</div>
							<div title="修改答案" style="padding: 10px" class="section-f-body">
								<input id="answerId" name="answerId" type="hidden" value="${answerDetail.answerId}">
								<input id="answerImg" name="answerImg" type="hidden" value="${answerDetail.answerImg}">
								<input id="delAnswerImg" name="delAnswerImg" type="hidden" value="N">
								<div class="san-row section-f-row">
								<div class="san-col-1" align="right">答案</div>
								<div class="san-col-10">
									<input id="answer" name="answer" class="easyui-textbox"
										style="width: 100%;" data-options="multiline:true,height:'100px',validType:'length[0,500]'" value="${answerDetail.answer}">
								</div>
								<div class="san-col-1"></div>
							</div>
							<div class="san-row section-f-row">
								<div class="san-col-1" align="right">附件</div>
								<div class="san-col-3" >
									<input id="answerFile" name="answerFile" class="easyui-filebox"
										style="width: 100%;" data-options="onChange:function(n,o){onChangeAn(n,o);},prompt:'上传图片',accept:'image/jpeg,image/png'" buttonText="选择文件">
								</div>								
								<c:if test="${not empty answerDetail.answerImg}">
								<div class="san-col-8" id="deleteButtonAn">
								<a id="deleteAnImg" href="#"
									class="easyui-linkbutton mw-70 m-r-10">删除附件</a>
								</div>
								</c:if>
							</div>
							<div class="san-row section-f-row">
								<div class="san-col-1" align="right"></div>
								<c:if test="${not empty answerDetail.answerImg}">
									<div class="san-col-11" id="imgDivAn">
										<img src="${ctx}${answerDetail.answerImg}">
									</div>
								</c:if>
							</div>
							</div>
						</div>
					</form>
					<div class="section-f-foot AR">
						<a id="cancelUpdateQuestion" href="#"
							class="easyui-linkbutton mw-70 m-r-10">取消</a> 
							<a id="updateQuestionBtn" href="#"
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
			$("#updateQuestion").form({
				url : "question/updateSubQuestion",
				type : "POST",
				resetForm: true,
				success : function(data) {
					var result = eval("(" + data + ")");
					if (result.saveFlag) {
						//成功框确认按钮点击事件
						closeWindow();
						showSuccessMsgDia(result.msg, 'question/getMyQuestionDetail?action=update&questionId='+result.questionId);
					}else {
						showFailureMsgDia(result.msg);
					}
				}
			}); 
			
			$("#questionIdMod").val("${tbQuestion.questionId}");
			$("#oldQuestionType").val("${tbQuestion.questionType}");
			$("input[name='levels'][value=${tbQuestion.levels}]").attr("checked",true); 
 			//初始化类别下拉框
 			$("#categoryId").combobox({
				valueField:'id', //值字段
				textField:'text', //显示的字段
				url:'question/categoryList?page=add',
				panelHeight:200,
				required:false,
				editable:false,//不可编辑，只能选择
				onLoadSuccess:function(){
					$("#categoryId").combobox("setValue", "${tbQuestion.categoryId}");
				}//不可编辑，只能选择
			}); 
 			//初始化群组下拉框
 			$("#groupId").combobox({
				valueField:'id', //值字段
				textField:'text', //显示的字段
				url:'question/groupList?page=add',
				panelHeight:200,
				required:false,
				editable:false,//不可编辑，只能选择
				onLoadSuccess:function(){
					$("#groupId").combobox("setValue", "${tbQuestion.groupId}");
				}//不可编辑，只能选择
			}); 			

			//删除图片题目
			$("#deleteQueImg").bind("click", function() {
				//确认框取消按钮点击事件
				showConfirmDia("确定删除附件?",function(){
					$("#imgDivQue").remove();
					$("#deleteButtonQue").remove();	
					$("#subjectImg").val("");
					$("#delSubjectImg").val("Y");
					
					
			    	/* $.ajax({ 
			            type:"POST", 
			            url:"question/deleteImg",                  
			            data:{
			            	questionId:'${tbQuestion.questionId}'
			            }, 
			            success:function(data){
							if (data.saveFlag) {
								$("#imgDivQue").remove();
								$("#deleteButtonQue").remove();	
								$("#subjectImg").val("");								
								//成功框确认按钮点击事件
								showSuccessMsgDia(data.msg, 'question/getMyQuestionDetail?action=update&questionId=${tbQuestion.questionId}')
							}else {
								showFailureMsgDia(data.msg);
							}		                                       
			            } 
			         });  */
			    })
			});

			//删除图片答案
			$("#deleteAnImg").bind("click", function() {
				
				//确认框取消按钮点击事件
				showConfirmDia("确定删除附件?", function(){
					$("#answerImg").val("");
					$("#imgDivAn").remove();
					$("#deleteButtonAn").remove();
					$("#delAnswerImg").val("Y");
					
			    	/* $.ajax({ 
			            type:"POST", 
			            url:"answer/deleteImg",                  
			            data:{
			            	questionId:'${tbQuestion.questionId}',
			            	answerId:'${answerDetail.answerId}'
			            }, 
			            success:function(data){
							if (data.saveFlag) {							
								//成功框确认按钮点击事件
								showSuccessMsgDia(data.msg, 'question/getMyQuestionDetail?action=update&questionId=${tbQuestion.questionId}');
							}else {
								showFailureMsgDia(data.msg);
							}		                                       
			            } 
			         });  */
			    })
			});
			
 			//新增成交单页面取消按钮
			$("#cancelUpdateQuestion").bind("click", function() {
				closeWindow();
			});
			
			//新增页面保存按钮
			$("#updateQuestionBtn").bind("click", function() {
				//题目校验
				var v = $('#score').numberbox('getValue');
				if(Number(v)<1||Number(v)>100){
					showFailureMsgDia("分数取值为1~100之间!");
					return;
				}
				if($("#subject").val().trim()==''&&$("#questionFile").filebox('getValue')==''&&$("#answerImg").val()==''){
					showFailureMsgDia("请填写题目或上传一个附件!");
					return;
				}
				//答案校验
				if($("#answer").val().trim()==''&&$("#answerFile").filebox('getValue')==''&&$("#answerImg").val()==''){
					showFailureMsgDia("请填写答案或上传一个附件!");
					return;
				}
				showConfirmDia("确定保存修改内容?",function(){
					$("#updateQuestion").form("submit");
				})
			});
			
		});
	</script>



</body>
</html>