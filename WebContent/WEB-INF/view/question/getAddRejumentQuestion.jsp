<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增考题</title>
</head>
<body>
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="section-a-title h5">题库管理 > 问答题录入 </div>
			</div>
			<!-- section-a-head end -->

			<div class="section-a-body">
				<div class="section-f  m-t-10">
					<form id="saveQuestion" method="POST" action="" enctype="multipart/form-data">
						<div class="section-f-head">
							<div class="h4">信息录入</div>
						</div>
						<div class="easyui-tabs" data-options="tabWidth:112">
							<div title="题目录入" style="padding: 10px" class="section-f-body">
								<div class="san-row section-f-row">
								<input id="questionType" name="questionType" type="hidden" value=0 >
									<div class="san-col-1" align="right">年级</div>
									<div class="san-col-2">
										<select id="categoryId" name="categoryId"
											class="easyui-combobox" style="width: 100%;"
											data-options="required:true,editable:false">
										</select>
									</div>
									<div class="san-col-1"></div>
									<div class="san-col-1" align="right">科目</div>
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
											data-options="required:true" style="width: 100%">
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
											data-options="multiline:true,height:'100px',validType:'length[0,500]'">
									</div>
									<div class="san-col-1"></div>
								</div>
								<div class="san-row section-f-row">
									<div class="san-col-1" align="right">附件</div>
									<div class="san-col-3">
										<input id="questionFile" name="questionFile"
											class="easyui-filebox" style="width: 100%;"
											data-options="prompt:'上传图片',accept:'image/jpeg,image/png'"
											buttonText="选择文件">
									</div>
									<div class="san-col-8"></div>
								</div>
							</div>
							<div title="答案录入" style="padding: 10px" class="section-f-body">
									<div class="san-row section-f-row">
										<div class="san-col-1" align="right">答案</div>
										<div class="san-col-10">
											<input id="answer" name="answer" class="easyui-textbox"
												style="width: 100%;"
												data-options="multiline:true,height:'100px',validType:'length[0,500]'">
										</div>
										<div class="san-col-1"></div>
									</div>
									<div class="san-row section-f-row">
										<div class="san-col-1" align="right">附件</div>
										<div class="san-col-3">
											<input id="answerFile" name="answerFile"
												class="easyui-filebox" style="width: 100%;"
												data-options="prompt:'上传图片',accept:'image/jpeg,image/png'"
												buttonText="选择文件">
										</div>
										<div class="san-col-8"></div>
									</div>
							</div>
						</div>
					</form>
					<div class="section-f-foot AR">
						<a id="saveNewQuestion" href="#"
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
			$("#saveQuestion").form(
					{
						url : "question/addSubjecQuestion",
						type : "POST",
						resetForm : true,
						success : function(data) {
							var result = eval("(" + data + ")");
							if (result.saveFlag) {
								//成功框确认按钮点击事件
								showSuccessMsgDia(result.msg,
										'question/getAddSubjectQuestion');
							} else {
								showFailureMsgDia(result.msg);
							}
						}
					});

			$("input[name='levels'][value=3]").attr("checked",true);
			
			//初始化类别下拉框
			$("#categoryId").combobox({
				valueField : 'id', //值字段
				textField : 'text', //显示的字段
				url : 'question/categoryList?page=add',
				panelHeight : 200,
				required : false,
				editable : false
			//不可编辑，只能选择
			});
			//初始化群组下拉框
			$("#groupId").combobox({
				valueField : 'id', //值字段
				textField : 'text', //显示的字段
				url : 'question/groupList?page=add',
				panelHeight : 200,
				required : false,
				editable : false
			//不可编辑，只能选择
			});

			//新增页面保存按钮
			$("#saveNewQuestion").bind("click",function() {
				//校验题目
				var v = $('#score').numberbox('getValue');
				if(Number(v)<1||Number(v)>100){
					showFailureMsgDia("分数取值为1~100之间!");
					return;
				}
				/*if ($("#subject").val().trim()==''&&$("#questionFile").filebox('getValue')=='') {
					showFailureMsgDia("请填写题目或上传一个附件!");
					return;
				}*/
				//校验答案
				if($("#answer").val().trim()==''&&$("#answerFile").filebox('getValue')==''){
					showFailureMsgDia("请填写答案或上传一个附件!");
					return;
				}				//新增表单提交
				$("#saveQuestion").form("submit");
			});
		});
	</script>



</body>
</html>