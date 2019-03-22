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
				<div class="section-a-title h5">试卷内容编辑</div>
			</div>
			<!-- section-a-head end -->

			<div class="section-a-body">
				<div class="section-f section-f-fixed-800 m-t-10">
					<div class="section-f-head">
						<div class="h4">试卷内容编辑</div>
					</div>
					<!-- section-f-head end -->
					<form id="saveForm" method="POST" action="">
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
								<div class="san-col-2" align="right">试卷名称</div>
								<div class="san-col-3">
									<input id="examinationName" name="examinationName" class="easyui-textbox" style="width: 100%;" data-options="required:true,validType:'length[0,100]'">
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-2" align="right">试卷状态</div>
								<div class="san-col-3">
									<select id="examinationStatus" name="examinationStatus" class="easyui-combobox"
										style="width: 100%;">
										<%-- <option value="">请选择</option>
										<option value="<%=ActiveStatus.VALID%>">有效</option>
										<option value="<%=ActiveStatus.CANCEL%>">注销</option> --%>
									</select>
								</div>
								<div class="san-col-1"></div>
							</div>
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">分数</div>
								<div class="san-col-3">				
									<input id="totalScore" name="totalScore" disabled="disabled" class="easyui-textbox" style="width: 100%;">
								</div>
								<div class="san-col-7"></div>
							</div>
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">试卷备注</div>
								<div class="san-col-3" style="width:75%">
									<input id="examinationRemark" name="examinationRemark" class="easyui-textbox"
										style="width: 100%;" data-options="multiline:true,height:'100px',validType:'length[0,500]'">
								</div>
								<div class="san-col-1"></div>
							</div>
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<table id="examinatioInfoTable" style="width: 100%; height: 300px;"></table>
							</div>
						</div>
						<!-- section-f-body end -->
					</form>
					<div class="section-f-foot AR">
							<a id="lastPage" href="#"
							class="easyui-linkbutton mw-70 m-r-10 z-easyui-bg-primary">上一步</a>
							<a id="saveExamination" href="#"
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
			$("#saveForm").form({
				url : "examination/saveExaminationInfo",
				type : "POST",
				resetForm: true,
				success : function(data) {
					var result = eval("(" + data + ")");
					if (result.saveFlag) {
						//成功框确认按钮点击事件
						showSuccessMsgDia(result.msg, 'examination/addExamination');
					}else {
						$("#totalScore").textbox("disable");
						showFailureMsgDia(result.msg);
					}
				}
			});
			

			
			//列宽设置为%，并完美100%，必须用js方式
			$("#examinatioInfoTable").datagrid({
								rownumbers : true,
								pagination : false,
								striped : true,
								sortName: 'QUESTION_ID', //排序的列
								sortOrder: 'asc', //升序
								singleSelect : false,
								onDblClickCell: onClickCell,
								toolbar:[{
									text:'删除',
									iconCls:'icon-cut',
									handler:function(){
										var exrows = $('#examinatioInfoTable').datagrid('getSelections');
										for(var i in exrows)
										{
											var index = $('#examinatioInfoTable').datagrid('getRowIndex',exrows[i]);
											$('#examinatioInfoTable').datagrid('deleteRow',index);
											countScore();
										}
									}
								}],
								columns : [ [
										{
											field : "questionId",
											title : "试题ID",
											halign : "center",
											width : "10%",
											align:'center',
											resizable : false
										},
										{
											field : "subject",
											title : "题目",
											halign : "center",
											width : "90%",
											align:'left',
											resizable : false,
											formatter:function(value,row,index){
												if(row.subject == null||row.subject.length==0)
												{
													return row.subjectImgName
												}else{
													return value
												}
											}
										} ,
										{
											field : "score",
											title : "分数",
											halign : "center",
											width : "10%",
											align:'center',
											editor:'text',
											resizable : false
										}] ],
								onBeforeLoad : function(data) {
									$("#examinatioInfoTable").datagrid("hideColumn", "questionId");
									
								}
							});
			
 			//初始化状态下拉框
 			$("#examinationStatus").combobox({
				valueField:'id', //值字段
				textField:'text', //显示的字段
				url:'user/statusList',
				panelHeight:'auto',
				required:false,
				editable:false
			}); 
			
			//新增页面保存按钮
			$("#saveExamination").bind("click", function() {
				endEditing();
				if(!$("#saveForm").form("validate"))
				{
					return;	
				}
				
				var arr=$('#examinatioInfoTable').datagrid('getData');
				var dataArr = new Array();;
				for(i=0;i<arr.rows.length;i++){	
					var score = arr.rows[i].score.toString().trim();
					if(score.length==0){
						showFailureMsgDia("分数不能为空!");
						return;
					}
					if(!/^[0-9]*[1-9][0-9]*$/.test(score)){
						showFailureMsgDia("分数必须是正整数!");
						return;
					}
				}
				
				var totalScore = $("#totalScore").textbox("getValue");
				if(Number(totalScore)!=100){
					showFailureMsgDia("分数不等于100分!");
					return;
				}
								
				var myform=$('#saveForm'); //得到form对象
       		 	var tmpInput=$("<input type='hidden' name='examinatioInfoTable' />");
	        	tmpInput.attr("value", JSON.stringify($('#examinatioInfoTable').datagrid("getRows")));
	        	myform.append(tmpInput);
				//新增表单提交
				$("#totalScore").textbox("enable");
				$("#saveForm").form("submit");
				$("input[name=examinatioInfoTable]").remove();
			});
			
			$("#lastPage").bind("click",function(){
				endEditing();
				var exrows ;
				var totalScore = 0;
				$('#main-page-body-content').sanJumpto({
					url:'examination/addExamination',
				    onBefore:function(){
						exrows = $('#examinatioInfoTable').datagrid('getRows');
					}, 
					onDone:function(){
						for(var i in exrows)
						{
							$('#examinatioTable').datagrid('appendRow',exrows[i]);	
							totalScore+=Number(exrows[i].score);
						}
						$("#totalScore").html(totalScore);
					}
				});
			});
			
			var editIndex = undefined;  
			function endEditing() {//该方法用于关闭上一个焦点的editing状态  
			    if (editIndex == undefined) {  
			        countScore();
			        return true  
			    }  
			    if ($('#examinatioInfoTable').datagrid('validateRow', editIndex)) {  
			        $('#examinatioInfoTable').datagrid('endEdit', editIndex);  
			        editIndex = undefined;  
			        countScore();
			        return true;  
			    } else {  
			        return false;  
			    }  
			};  
			
			function onClickCell(index, field){
	            if (editIndex != index){
	                if (endEditing()&&field=="score"){
	                    $('#examinatioInfoTable').datagrid('selectRow', index)
	                            .datagrid('beginEdit', index);
	                    var ed = $('#examinatioInfoTable').datagrid('getEditor', {index:index,field:field});
	          /*           $(ed.target).focus().bind('blur', function () {
	                         $("#answerTable").datagrid('endEdit', index); 
	                   }); */
	                    if (ed){
	                       $(ed.target).focus();
	                    }
	                    editIndex = index;
	                } else {
	                    setTimeout(function(){
	                        $('#examinatioInfoTable').datagrid('selectRow', editIndex);
	                    },0);
	                }
	            }
	        } 
			
			function countScore(){
				var totalScore = 0;
				var allRows = $('#examinatioInfoTable').datagrid('getRows');
				for(var i in allRows){
					if(/^[0-9]*[1-9][0-9]*$/.test(allRows[i].score)){
						totalScore+=Number(allRows[i].score);
					}
				}	
				$("#totalScore").textbox("setValue",totalScore);
			}
			
		});
	</script>



</body>
</html>