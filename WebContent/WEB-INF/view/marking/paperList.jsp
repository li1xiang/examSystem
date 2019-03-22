<!-- 答卷列表 -->
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
	<%-- <%@ include file="../common/commonDia.jsp"%> --%>
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="san-row">
					<div class="san-col-9">
						<div class="section-a-title h5">阅卷管理 > 答卷列表</div>
					</div>
				</div>
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
							<form id="queryForm">
								<div class="san-row">
									<div class="san-col-2">
										<div>答卷人姓名</div>
										<input class="easyui-textbox" name="name" style="width: 100%;" data-options="validType:'length[0,128]'">
									</div>
									<div class="san-col-2">
										<div>答卷人手机号</div>
										<input class="easyui-textbox" name="phone"
											style="width: 100%;" data-options="validType:['mobilePhone','length[0,15]']">
									</div>
									<div class="san-col-2">
										<div>试卷名称</div>
										<input class="easyui-textbox" name="examinationName"
											style="width: 100%;" data-options="validType:'length[0,100]'">
									</div>
									<div class="san-col-2">
										<div>答卷状态</div>
										<select id="submitStatus" name="submitStatus" class="easyui-combobox"
											style="width: 100%;">
										</select>
									</div>

									<div class="san-col-2 AR"></div>
								</div>
								<!-- san-row end -->
							</form>
						</div>
					</div>
					<div class="section-b-161011-right">
						<div class="section-b-161011-inner">
							<a href="#" onclick="searchPaper();"
								class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">查询</a>
							<!-- <a href="#" class="easyui-linkbutton m-t-10 mw-70">Excel导出</a> -->
						</div>
					</div>
				</div>
				<!-- section-b-body end -->
			</div>
			<!-- section-b end -->

			<div class="headline-a">
				<span class="headline-a-text">查询结果</span>
			</div>
			<table id="paperTable" style="width: 100%; height: 300px"></table>
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

			//初始化查询条件--------------------start
			$("#submitStatus").combobox({
				url : "marking/submitStatusList",
				valueField : "id",
				textField : "text",
				panelHeight : 'auto',
				required : false,
				editable : false
			//不可编辑，只能选择
			});
			//初始化查询条件--------------------end

			$("#addInterviewerBtn").click(function() {
				/* $('#main-page-body-content').sanJumpto({
					url : 'interviewer/newInterviewer'
				}); */
				showWindow({
					title : '新增面试者信息',
					href : 'interviewer/newInterviewer',
					width : 1000,
					height : 'auto',
					closable : false
				});

			});

			//列宽设置为%，并完美100%，必须用js方式
			$("#paperTable")
					.datagrid(
							{
								url : "marking/paperList",
								method : "POST",
								rownumbers : true,
								pagination : true,
								striped : true,
								singleSelect : true,
								columns : [ [
										{
											field : "paperId",
											title : "答卷Id",
											halign : "center",
											width : "15%",
											align : 'center'
											/* formatter : function(value,row,index){
												return "<a href='#' onClick='showPaperDetails("+value+")'>"+value+"</a>"
											} */
										},
										{
											field : "name",
											title : "答卷人姓名",
											halign : "center",
											width : "15%",
											align : 'center',
											resizable : false
										},
										{
											field : "examinationName",
											title : "试卷名称",
											halign : "center",
											width : "15%",
											align : 'center',
											resizable : false
										},
										{
											field : "paperStartTime",
											title : "答卷时间",
											halign : "center",
											width : "20%",
											align : 'center',
											resizable : false,
											formatter : function (value){
												return getDate(value);
											}
										},
										{
											field : "score",
											title : "分数",
											halign : "center",
											width : "10%",
											align : 'center',
											resizable : false
										},
										{
											field : "active",
											title : "状态",
											halign : "center",
											width : "10%",
											align : 'center',
											resizable : false,
											formatter : function(value, row,
													index) {
												console.log(row);
												return row.submitStatus == 0 ? '未交卷'
														: '已交卷';
											}
										},
										{
							            	 field : "oper",
							            	 title : "操作",
							            	 halign : "center",
							            	 width : "15%",
							            	 align : 'center',
							            	 formatter : function(value,row,index){
							            		 var markingBtnHtml = '<a href="#" onclick="showPaperDetails('+ index + ')" class="editcls"></a>';
						            			 markingBtnHtml += '<a href="#" onclick="showPaper('+ row.paperId + ')" class="morecls"></a>'
							            		 
							            		 return markingBtnHtml;
							            	 }
							             }
										 ] ] ,
								onLoadSuccess : function(data) {
									//评分按钮
									$(".editcls").linkbutton({
										text : '评分',
										plain : true,
										iconCls : 'icon-edit',
										height : '18px'
									});
									//
									$(".morecls").linkbutton({
										text : '预览答卷',
										plain : true,
										iconCls : 'icon-more',
										height : '18px'
									});
								} 
							});

		});
		function searchPaper() {
			var params = $('#paperTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
			var fields = $('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
			//debugger;
			$.each(fields, function(i, field) {
				params[field.name] = field.value; //设置查询参数
			});
			$('#paperTable').datagrid('reload'); //设置查询参数 reload  
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
			return year + "-" + month + "-" + day + " " + ((parseInt(hour) < 10) ? ("0" + hour)
					: hour) + ':' + ((parseInt(minute) < 10) ? ("0" + minute)
							: minute) + ':' + ((parseInt(second) < 10) ? ("0" + second)
									: second);
		}
		
		//点击答卷序号跳转到评分功能
		function showPaperDetails(index){
			$('#paperTable').datagrid('selectRow', index);//关键在这里  
			var row = $('#paperTable').datagrid('getSelected');
			if(row.submitStatus==0){
				showFailureMsgDia("未交卷的答卷不能评分");
				return;
			}
			window.open('marking/markingPage?paperId='+row.paperId, "_blank");
		}
		
		function showPaper(paperId){
			window.open('marking/showPaper?paperId='+paperId, "_blank");
		}
	</script>
</body>
</html>