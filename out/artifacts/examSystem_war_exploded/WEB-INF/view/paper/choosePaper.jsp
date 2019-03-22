<!-- 面试者答卷选择 -->
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
						<div class="section-a-title h5">笔试答题 > 笔试答题 > 试卷选择</div>
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
								<form id="queryForm">
									<div class="san-row">
										<div class="san-col-2">
											<div>姓名</div>
											<input class="easyui-textbox" id="name" name="name"
												style="width: 100%;" data-options="required:true,validType:'length[0,128]'">
										</div>
										<div class="san-col-2">
											<div>手机号</div>
											<input class="easyui-textbox" id="phone" name="phone"
												style="width: 100%;" data-options="required:true,validType:['mobilePhone','length[0,15]']">
										</div>
										<div class="san-col-2 AR"></div>
									</div>
									<!-- san-row end -->
								</form>
							</div>
						</div>
						<div class="section-b-161011-right">
							<div class="section-b-161011-inner">
								<a href="#" onclick="searchUser();"
									class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">查询试卷</a>
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
				<table id="paperTable" style="width: 80%; height: 250px"></table>
				<a href="#" onclick="beginAnswerPaper();"
					class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">开始答卷</a>
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

			$("#paperTable").datagrid({
				url : "paper/paperList",
				method : "POST",
				rownumbers : true,
				pagination : true,
				striped : true,
				sortName : 'examination_Id', //排序的列
				sortOrder : 'asc', //升序
				singleSelect : true,
				columns : [ [ {
					field : "examinationId",
					hidden : true
				}, {
					field : "examinationName",
					title : "试卷名称",
					halign : "center",
					width : "25%",
					align : 'center',
					resizable : false
				}, {
					field : "createUserCname",
					title : "创建用户",
					halign : "center",
					width : "20%",
					align : 'center',
					resizable : false
				}, {
					field : "createTime",
					title : "创建时间",
					halign : "center",
					width : "20%",
					align : 'center',
					resizable : false,
					formatter : function(value) {
						
						return getDate(value);
					}
				}/* , {
					field : "oprt",
					title : "操作",
					halign : "center",
					width : "20%",
					align : 'center',
					resizable : false,
					formatter : function(value,row,index) {
						var downloadBtn = '<a href="#" onclick="downloadExamination('+ row.examinationId + ')" class="editcls"></a>';
						return downloadBtn;
					}
				} */ ] ]/* ,
				onLoadSuccess : function(data) {
					//评分按钮
					$(".editcls").linkbutton({
						text : '打印考卷',
						plain : true,
						iconCls : 'icon-edit',
						height : '18px'
					});
				}  */
			});
		});

		//列宽设置为%，并完美100%，必须用js方式
		function searchUser() {
			var params = $('#paperTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
			var fields = $('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
			$.each(fields, function(i, field) {
				params[field.name] = field.value; //设置查询参数
			});
			$('#paperTable').datagrid('reload'); //设置查询参数 reload
			//console.info($('#paperTable').datagrid('getData')); //设置查询参数 reload
		}

		//选择答卷后开始答卷按钮
		function beginAnswerPaper() {
			//获取选择行的数据
			var selectedRow = $("#paperTable").datagrid('getSelected');
			console.info(selectedRow.examinationId);
			if(selectedRow == undefined || selectedRow == null){
				alert("请选择一套试卷进行答题!");
				return;
			}
			//?examinationId='+selectedRow.examinationId+"&name="+$("#name").val()+"&phone=" + $("#phone").val()
			$('#main-page-body-content').sanJumpto({
				type:"GET",
				url:'paper/answerPaper?examinationId='+selectedRow.examinationId+"&name="+$("#name").val()+"&phone=" + $("#phone").val()
			});
			/* showWindow({
				title:'面试答题',
				href:'paper/answerPaper?examinationId='+selectedRow.examinationId,
				width:1000,
				height:600,
				closable : false
			}); */
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
		//下载试卷
		/* function downloadExamination(examinationId){
			window.open("paper/downloadExamination?examinationId=" + examinationId,"_blank");
			/* $.ajax({
				url:'paper/downloadExamination',
				data:{examinationId:examinationId}
			}); 
		} */
		</script>
</body>
</html>