<!-- 试卷列表管理 -->
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
	<%-- <%@ include file="../../../../taglibs.jsp" %> --%>
	<%-- <%@ include file="../common/commonDia.jsp" %> --%>
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="san-row">
					<div class="san-col-9">
						<div class="section-a-title h5">试卷管理 >试卷列表维护 > 试卷列表管理</div>
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
							<div class="san-row section-f-row">
								<div class="san-col-2">
									<div>试卷名称</div>
									<select id="examinationName" name="examinationName" class="easyui-textbox"
										style="width: 100%;" data-options="validType:'length[0,100]'">										
									</select>
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-2">
								<div>创建人</div>
									<select id="createUser" name="createUser" class="easyui-combobox"
										style="width: 100%;" data-options="required:true,editable:false">										
									</select>								
								</div>
								<div class="san-col-1"></div>
								<div class="san-col-2">
									<div>状态</div>
									<select id="examinationStatus" name="examinationStatus" class="easyui-combobox"
										style="width: 100%;" data-options="required:true,editable:false">										
									</select>
								</div>
								<div class="san-col-1"></div>								
							</div>
							<!-- san-row end -->
							</form>
							</div>
						</div>
						<div class="section-b-161011-right">
							<div class="section-b-161011-inner">
								<a href="#" onclick="searchExamination();"
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
				<table id="examinationTable" style="width: 100%; height: 300px"></table>
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
			
 			//初始化禁用下拉框
 			$("#examinationStatus").combobox({
				valueField:'id', //值字段
				textField:'text', //显示的字段
				url:'question/activeList?page=all',
				panelHeight:'auto',
				required:false,
				editable:false//不可编辑，只能选择
			}); 
 			
 			//初始化创建人下拉框
 			$("#createUser").combobox({
				valueField:'id', //值字段
				textField:'text', //显示的字段
				url:'question/createUserList?page=all',
				panelHeight:200,
				required:false,
				editable:false//不可编辑，只能选择
			});
			//初始化查询条件--------------------end			
			
			//列宽设置为%，并完美100%，必须用js方式
			$("#examinationTable").datagrid({
								url : "examination/examinationList",
								method : "POST",
								rownumbers : true,
								pagination : true,
								striped : true,
								sortName: 'EXAMINATION_ID', //排序的列
								sortOrder: 'asc', //升序
								checkOnSelect: false,
								selectOnCheck: false,
								onClickRow: function (rowIndex, rowData) {
		                            $("#examinationTable").datagrid('unselectRow', rowIndex);
		 						},
		 						toolbar:[{
		 							text:'批量禁用',
		 							iconCls:'icon-cancel',
		 							handler:function(){
		 								lockExamination(0);
		 							}
		 						},'-',{
		 							text:'批量启用',
		 							iconCls:'icon-ok',
		 							handler:function(){
		 								lockExamination(1);
		 							}
		 						}],
								columns : [ [
										{
											field : "ck",
											title : "ck",
											halign : "center",
											checkbox:true,
											align:'center',
											resizable : false
										},{
											field : "examinationId",
											title : "试卷ID",
											halign : "center",
											width : "10%",
											align:'center',
											resizable : false
										},
										
							   			{
											field : "examinationName",
											title : "试卷名称",
											halign : "center",
											width : "15%",
											align:'center',
											resizable : false
							   			},
							   			{
											field : "examinationRemark",
											title : "备注",
											halign : "center",
											width : "30%",
											align:'left',
											resizable : false,
											formatter: function(value,row,index){ 
												//单元格文本溢出显示省略号,悬浮框
												   return '<span title='+value+'>'+value+'</span>'    
												}  
							   			},
							   			{
											field : "createTime",
											title : "创建时间",
											halign : "center",
											width : "15%",
											align:'center',
											resizable : false,
											formatter:function(value,row,index){
												return getDate(value);
											}
							   			},
							   			{
											field : "examinationStatus",
											title : "状态",
											halign : "center",
											width : "5%",
											align:'center',
											resizable : false,
											formatter:function(value,row,index){
												return row.examinationStatus==0?'禁用':'启用';  
											}
							   			} ,
							   			{field:'operate',title:'操作',width:"35%",sortable:false,align:'center',halign:'center',
											formatter:function formatOper(val,row,index){  
												var s = '<a href="#" onclick="editRow('+index+')" class="editcls"></a>';
												s+= '<a href="#" onclick="deleteRow('+index+')" class="disabledcls"></a>';
												/* if(row.examinationStatus ==1)
												{
													s+= '<a href="#" onclick="deleteRow('+index+')" class="disabledcls"></a>';
												}else{
													s+= '<a href="#" onclick="deleteRow('+index+')" class="enablecls"></a>';
												} */
												var downloadExamBtn = '<a href="#" onclick="downloadExamination('+ row.examinationId + ')" class="downExamcls"></a>';
												var downloadAnswerBtn = '<a href="#" onclick="downloadAnswer('+ row.examinationId + ')" class="downAnswermcls"></a>';
											    return s + downloadExamBtn + downloadAnswerBtn;  
											} 
										} ] ],
								onLoadSuccess : function(data) {
									//$("#examinationTable").datagrid("hideColumn", "examinationId");
									$(".disabledcls").linkbutton({
										text:'删除',
										plain : true,
										iconCls : 'icon-cancel',
										height : '18px'
									});
									$(".enablecls").linkbutton({
										text:'启用',
										plain : true,
										iconCls : 'icon-ok',
										height : '18px'
									});
									//修改行按钮
									$(".editcls").linkbutton({
										text:'更新',
										plain : true,
										iconCls : 'icon-edit',
										height:'18px'
									});
									$(".downExamcls").linkbutton({
										text:'下载试卷',
										plain : true,
										iconCls : 'icon-more',
										height:'18px'
									});
									$(".downAnswermcls").linkbutton({
										text:'下载答案',
										plain : true,
										iconCls : 'icon-more',
										height:'18px'
									});
								}
							});
		});
		
		
		function searchExamination(){
			var params = $('#examinationTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
			var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
			$.each( fields, function(i, field){
				params[field.name] = field.value; //设置查询参数
			}); 
			$('#examinationTable').datagrid('reload'); //设置查询参数 reload  
		}				
		
		//批量禁用/启用
		function lockExamination(status){ 
			var rows = $('#examinationTable').datagrid('getChecked');
			var examinationIds = new Array();
			for(i=0;i<rows.length;i++){
				if(rows[i].active==status){
					continue;
				}
				examinationIds[i]=rows[i].examinationId;
			}
			if(examinationIds.length==0){
				showSuccessMsgDia(status==0?'禁用试题成功!':'启用试题成功!');
				return;
			}
			//确认框取消按钮点击事件
			showConfirmDia(status==0?'确定禁用?':'确定启用?',function(){
		    	$.post('examination/updateStatus',{examinationIds:examinationIds,status:status},function(data){
		    		if(data.saveFlag){
		    			showSuccessMsgDia(data.msg);
						$('#examinationTable').datagrid('reload'); 
		    		}else{
		    			showFailureMsgDia(data.msg);
		    		}
				});
		    });
		}
		//单行禁用/启用
		function deleteRow(index){
			 $('#examinationTable').datagrid('selectRow',index);
			 var row = $('#examinationTable').datagrid('getSelected');
			 showConfirmDia('确定要删除吗?', function(){
					$.post('examination/deleteExamination', {
							examinationId : row.examinationId
						}, function(data) {
							if (data.saveFlag) {
								showSuccessMsgDia(data.msg);
								$('#examinationTable').datagrid('reload');
							} else {
								showFailureMsgDia(data.msg);
							}
						});
			 			});
			/* $('#examinationTable').datagrid('selectRow',index);// 关键在这里
			var row = $('#examinationTable').datagrid('getSelected');
			var examinationIds = new Array();
			examinationIds[0]=row.examinationId;
			var status = row.examinationStatus==0?1:0;
			//确认框取消按钮点击事件
			showConfirmDia(status==0?'确定禁用?':'确定启用?', function(){
			$.post('examination/updateStatus', {
					examinationIds : examinationIds,
					status : status
				}, function(data) {
					if (data.saveFlag) {
						showSuccessMsgDia(data.msg);
						$('#examinationTable').datagrid('reload');
					} else {
						showFailureMsgDia(data.msg);
					}
				});
			}); */
		}

		//行修改
		function editRow(index) {
			$('#examinationTable').datagrid('unselectAll');//先清除所有选中的行
			$('#examinationTable').datagrid('selectRow', index);// 关键在这里  
			var row = $('#examinationTable').datagrid('getSelected');
			//检查试卷是否有关联的面试者，若有已关联面试者则不能
			$.ajax({
				url: "examination/hasRelatedInterviewer",
				data: {
					examinationId : row.examinationId
				},
				success: function(data){
					
					if(data.flag){
						//有关联的面试者
						showFailureMsgDia("已绑定面试者的试卷无法更改！");
						return;
					}else{
						//没有关联面试者则跳转到修改页面
						postSanJumpTo('examination/getExaminationDetail?examinationId='
								+ row.examinationId);
					}
				}
			});
			/* $('#main-page-body-content').sanJumpto(
					{
						url : 'examination/getExaminationDetail?examinationId='
								+ row.examinationId
					}); */
		}
		//下载试卷
		function downloadExamination(examinationId){
			window.open("paper/downloadExamination?examinationId=" + examinationId+"&timeStamp=" + new Date(),"_blank");
			/* $.ajax({
				url:'paper/downloadExamination',
				data:{examinationId:examinationId}
			}); */
		}
		//下载答案
		function downloadAnswer(examinationId){
			window.open("paper/downloadAnswerPage?examinationId=" + examinationId+"&timeStamp=" + new Date(),"_blank");
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
	</script>
</body>
</html>