<!-- 面试者列表管理 -->
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
						<div class="section-a-title h5">试卷管理 > 面试者管理 > 面试者列表管理</div>
					</div>
					<div class="san-col-3 AR">
						<a id="addInterviewerBtn" href="#" class="easyui-linkbutton m-t-8 mw-70 z-easyui-bg-primary">面试者录入</a>
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
							<form id="queryForm" >
								<div class="san-row">
									<div class="san-col-2">
										<div>姓名</div>
										<input class="easyui-textbox" name="name" style="width: 100%;" data-options="validType:'length[0,128]'">
									</div>
									<div class="san-col-2">
										<div>手机号</div>
										<input class="easyui-textbox" name="phone" style="width: 100%;" data-options="validType:['mobilePhone','length[0,15]']">
									</div>
									<div class="san-col-2">
										<div>面试者来源</div>
										<select id="source" name="source" class="easyui-combobox"
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
								<a href="#" onclick="searchInterviewer();"
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
				<table id="interviewerTable" style="width: 100%; height: 300px"></table>
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
			$("#source").combobox({
				url:"interviewer/sourceList",
				valueField:"id",
				textField:"text",
				panelHeight:'auto',
				required:false,
				editable:false//不可编辑，只能选择
			});
			//初始化查询条件--------------------end

			$("#addInterviewerBtn").click(function(){
				/* $('#main-page-body-content').sanJumpto({
					url : 'interviewer/newInterviewer'
				}); */
				showWindow({
					title:'新增面试者信息',
					href:'interviewer/newInterviewer',
					width:1000,
					height:'auto'
				});
				
			});
			
			
			//列宽设置为%，并完美100%，必须用js方式
			$("#interviewerTable").datagrid({
								url : "interviewer/interviewerList",
								method : "POST",
								rownumbers : true,
								pagination : true,
								striped : true,
								sortName: 'name', //排序的列
								sortOrder: 'asc', //升序
								singleSelect : true,
								columns : [ [
										{
											field : "interviewerId",
											hidden : true
										},
										{
											field : "name",
											title : "姓名",
											halign : "center",
											width : "15%",
											align:'center',
											resizable : false
										},
										{
											field : "phone",
											title : "手机号",
											halign : "center",
											width : "15%",
											align:'center',
											resizable : false
							   			},
							   			{
											field : "education",
											title : "学历",
											halign : "center",
											width : "10%",
											align:'center',
											resizable : false
							   			},
							   			{
											field : "major",
											title : "专业",
											halign : "center",
											width : "15%",
											align:'center',
											resizable : false
							   			},
							   			{
											field : "workingYears",
											title : "工作年限",
											halign : "center",
											width : "10%",
											align:'center',
											resizable : false
							   			},
							   			{
											field : "active",
											title : "状态",
											halign : "center",
											width : "10%",
											align:'center',
											resizable : false,
											formatter:function(value,row,index){
												return row.active==0?'禁用':'启用';  
											}
							   			},
										{field:'operate',title:'操作',width:"25%",sortable:false,align:'center',halign:'center',
											formatter:function formatOper(val,row,index){  
												var s = '<a href="#" onclick="editRow('+index+')" class="editcls"></a>';
												s+= '<a href="#" onclick="relatedExamination('+index+')" class="relatedCls"></a>';
												if(row.active ==1)
												{
													s+= '<a href="#" onclick="deleteRow('+index+')" class="disabledcls"></a>';
												}else{
													s+= '<a href="#" onclick="deleteRow('+index+')" class="enablecls"></a>';
												}
											    return s;  
											} 
										} ] ],
								onLoadSuccess : function(data) {
									 $(".relatedCls").linkbutton({
										text:'关联试卷',
										plain : true,
										iconCls : 'icon-lock',
										height : '18px'
									});
									//修改行按钮
									$(".editcls").linkbutton({
										text:'修改',
										plain : true,
										iconCls : 'icon-edit',
										height:'18px'
									});
									
									$(".disabledcls").linkbutton({
										text:'禁用',
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
								}
							});


		});
		function searchInterviewer(){
			var params = $('#interviewerTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
			var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
			//debugger;
			$.each( fields, function(i, field){
				params[field.name] = field.value; //设置查询参数
			}); 
			$('#interviewerTable').datagrid('reload'); //设置查询参数 reload  
		}
		
		//试卷关联
		function relatedExamination(index){ 
			$('#interviewerTable').datagrid('selectRow',index);// 关键在这里  
			var row = $('#interviewerTable').datagrid('getSelected');
			showWindow({
				title:'关联试卷',
				href:'interviewer/relatedExamination?interviewerId='+row.interviewerId,
				width:1000,
				height:'auto'
			});
		}
		
		//行修改
		function editRow(index){ 
			$('#interviewerTable').datagrid('unselectAll');//先清除所有选中的行
			$('#interviewerTable').datagrid('selectRow',index);// 关键在这里  
			var row = $('#interviewerTable').datagrid('getSelected'); 
			showWindow({
				title:'修改面试者信息',
				href:'interviewer/updateInterviewer?interviewerId='+row.interviewerId,
				width:1000,
				height:'auto'
			});
		}
		
		function deleteRow(index){ 
			$('#interviewerTable').datagrid('selectRow',index);// 关键在这里  
			var row = $('#interviewerTable').datagrid('getSelected');
			var alertmsg = "";
			var status;
			if(row.active == 0)
			{
				alertmsg = "确定要启用\""+row.name+"\"吗?";
				status = 1;
			}else{
				alertmsg = "确定要禁用\""+row.name+"\"吗?";
				status = 0;
			} 
			$.messager.confirm('提示',alertmsg,function(result){
				if (result){
					$.post('interviewer/deleteInterviewer',{interviewerId:row.interviewerId,active:status},function(data){
						$('#interviewerTable').datagrid('reload'); 
						var msg = "";
						if(status==1)
						{
							msg="启用";
						}else{
							msg="禁用";
						}
						$.messager.alert('提示',msg+data.msg,'info');
					});
				}
			});
		}
	</script>
</body>
</html>