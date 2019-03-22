<!-- 添加试卷 -->
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
	<%-- <%@ include file="../../../../taglibs.jsp" %> --%>
	<%-- <%@ include file="../common/commonDia.jsp" %> --%>
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="san-row">
					<div class="san-col-9">
						<div class="section-a-title h5">试卷管理 > 手工新增试卷 > 添加题目</div>
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
					<span class="headline-a-text">查询条件</span>
				</div>
				<div class="section-b">
					<div class="section-b-body section-b-161011">
						<div class="section-b-161011-left">
							<div class="section-b-161011-inner">
							<form id="queryForm" >
								<div class="san-row">
									<div class="san-col-2">
										<div>类别</div>
										<input class="easyui-combobox" name="categoryId" id="categoryId" style="width: 100%;">
									</div>
									<div class="san-col-2">
										<div>群组</div>
										<input class="easyui-combobox" name="groupId" id="groupId" style="width: 100%;">
									</div>
									<div class="san-col-2">
										<div>难度</div>
										<input class="easyui-combobox" name="levels" id="levels" style="width: 100%;">
									</div>
									<div class="san-col-2">
										<div>题型</div>
										<input class="easyui-combobox" name="questionType" id="questionType" style="width: 100%;">
									</div>
								</div>
								<!-- san-row end -->
							</form>
							</div>
						</div>
						<div class="section-b-161011-right">
							<div class="section-b-161011-inner">
								<a href="#" onclick="searchQuestion();"
									class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">查询</a>
							</div>
						</div>
					</div>
					<!-- section-b-body end -->
				</div>
				<!-- section-b end -->

				<div class="headline-a">
					<span class="headline-a-text">查询结果</span>
				</div>
				<div class="easyui-layout" style="width:100%;height:400px;">
					<div data-options="region:'west',split:true" title="题库" style="width:45%;">
						<table id="questionTable" style="width: 100%; height: 400px;"></table>
					</div>
					<div data-options="region:'east',split:true" title="试卷题目" style="width:45%;">
						<table id="examinatioTable" style="width: 100%; height: 400px;"></table>
					</div>
					<div data-options="region:'center'" class="AC AM CELL">
						总分100分<br>当前分数:<span id="totalScore">0</span>分<br>
						<a href="#" onclick="leftToRight()"
									class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">>></a>
						
					</div>
				</div>
			</div>
			<!-- section-a-body end -->
			<div class="section-f-foot AR">
							<a id="toExaminationInfo"
							class="easyui-linkbutton mw-70 m-r-10 z-easyui-bg-primary">下一步</a>
			</div>
		</div>
		<!-- section-a end -->
	</div>
	<!-- sub-page end -->
	<!-- <div id="subpage-list-ecc" class="easyui-calendar"></div> -->

	<script type="text/javascript">
		$(function() {
			$.parser.parse("#main-page-body-content");
			//初始化状态下拉框
 			$("#categoryId").combobox({
				valueField:'id', //值字段
				textField:'text', //显示的字段
				url:'question/categoryList?page=all',
				panelHeight:200,
				required:false,
				editable:false
			}); 
			
 			//初始化状态下拉框
 			$("#groupId").combobox({
				valueField:'id', //值字段
				textField:'text', //显示的字段
				url:'question/groupList?page=all',
				panelHeight:200,
				required:false,
				editable:false
			});
 			
 			//初始化状态下拉框
 			$("#levels").combobox({
				valueField:'id', //值字段
				textField:'text', //显示的字段
				url:'question/levelsList?page=all',
				panelHeight:'auto',
				required:false,
				editable:false
			});
			
			//初始化状态下拉框
 			$("#questionType").combobox({
				valueField:'id', //值字段
				textField:'text', //显示的字段
				url:'question/queTypeList',
				panelHeight:'auto',
				required:false,
				editable:false
			});
			
 			//列宽设置为%，并完美100%，必须用js方式
			$("#questionTable").datagrid({
								method : "POST",
								rownumbers : true,
								pagination : false,
								striped : true,
								sortName: 'QUESTION_ID', //排序的列
								sortOrder: 'asc', //升序
								singleSelect : false,
								columns : [ [
										{
											field : "questionId",
											title : "",
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
													return '<a href="#" onclick="showDetail('+row.questionId+')" >'+row.subjectImgName+'</a>'
												}else{
													return '<a href="#" onclick="showDetail('+row.questionId+')" >'+value+'</a>'
												}
											}
										},
										{
											field : "score",
											title : "分数",
											halign : "center",
											width : "10%",
											align:'center',
											resizable : false
										} ] ],
								onLoadSuccess : function(data) {
									$("#questionTable").datagrid("hideColumn", "questionId");
								}
							});
			//列宽设置为%，并完美100%，必须用js方式
			$("#examinatioTable").datagrid({
								rownumbers : true,
								pagination : false,
								striped : true,
								sortName: 'QUESTION_ID', //排序的列
								sortOrder: 'asc', //升序
								singleSelect : false,
								toolbar:[{
									text:'删除',
									iconCls:'icon-cut',
									handler:function(){
										var exrows = $('#examinatioTable').datagrid('getSelections');
										for(var i in exrows)
										{
											var index = $('#examinatioTable').datagrid('getRowIndex',exrows[i]);
											$('#examinatioTable').datagrid('deleteRow',index);
										}
										var totalScore = 0;
										var allRows = $('#examinatioTable').datagrid('getRows');
										for(var i in allRows){
											totalScore+=Number(allRows[i].score);
										}	
										$("#totalScore").html(totalScore);
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
										},
										{
											field : "score",
											title : "分数",
											halign : "center",
											width : "10%",
											align:'center',
											resizable : false
										}] ],
								onBeforeLoad : function(data) {
									$("#examinatioTable").datagrid("hideColumn", "questionId");
								}
							});
			$('#toExaminationInfo').click(function(){
				var exrows = $('#examinatioTable').datagrid('getRows');
				var totalScore = 0;
				if(exrows.length==0){
					showFailureMsgDia("请选择试题!");
					return;
				}
				var url;
				if("${examinationId}"==null||"${examinationId}"==''){
					url='examination/examinationInfo';
				}else{
					url='examination/getExaminationDetail?examinationId=${examinationId}&action=update'
				}
				$('#main-page-body-content').sanJumpto({
					url:url,
					onBefore:function(){
						$('#examinatioInfoTable').datagrid({url:""});
					}, 
					onDone:function(){
						for(var i in exrows)
						{
							$('#examinatioInfoTable').datagrid('appendRow',exrows[i]);	
							totalScore+=Number(exrows[i].score);
						}
						$("#totalScore").textbox("setValue",totalScore);
					}
				});
			});


		});
		function searchQuestion(){
			var params = $('#questionTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
			var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
			$.each( fields, function(i, field){
				params[field.name] = field.value; //设置查询参数
			}); 
			$('#questionTable').datagrid({url:"examination/allQuestionList"});
			$('#questionTable').datagrid('reload'); //设置查询参数 reload  
		}
		
		function leftToRight()
		{
			var exrows = $('#examinatioTable').datagrid('getRows');
			var length = exrows.length;
			var rows = $('#questionTable').datagrid('getSelections');
			var tag = true;
			var totalScore = 0 ;
			for(var i in rows)
			{
				var tag = true;
				for(var j=0;j<length;j++)
				{
					if(exrows[j].questionId==rows[i].questionId)
					{
						tag = false;
						break;
					}else{
						tag = true;
					}
				}
				if(tag)
				{
					$('#examinatioTable').datagrid('appendRow',rows[i]);	
				}
			}
			var allRows = $('#examinatioTable').datagrid('getRows');
			for(var i in allRows){
				totalScore+=Number(allRows[i].score);
			}	
			$("#totalScore").html(totalScore);
		}
		
		function showDetail(questionId){
			showWindow({
				title:'试题明细',
				href:'question/getQuestionDetail?action=select&questionId='+questionId,
				width:1200,
				height:'auto'
			});
		}
		
	</script>
</body>
</html>