<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>年级管理</title>
</head>
<body>
	<%-- <%@ include file="../common/commonDia.jsp" %> --%>
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="san-row">
					<div class="san-col-9">
						<div class="section-a-title h5">题库管理 > 科目管理 </div>
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
								<div class="san-row">
								<form id="queryForm" >
									<div class="san-col-2">
										<div>科目名称</div>
										<input class="easyui-textbox" name="groupName" style="width: 100%;">
									</div>
									
								</form>
									<div class="san-col-2 AR"></div>
								</div>
								<!-- san-row end -->
							</div>
						</div>
						<div class="section-b-161011-right">
							<div class="section-b-161011-inner">
								<a href="#" onclick="searchGroup();"
									class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">查询</a>
								<!-- <a href="#" class="easyui-linkbutton m-t-10 mw-70">Excel导出</a> -->
								<!--  
								<span><a href="#"
									class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary" id="addGroup" onclick="$('#dlgg').dialog('open')">新增</a></span>
								-->
								<span><a href="#"
									class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary" id="addGroup" onclick="addGroup()">新增</a></span>
							</div>
						</div>
					</div>
					<!-- section-b-body end -->
				</div>
				<!-- section-b end -->

				<div class="headline-a">
					<span class="headline-a-text">查询结果</span>
				</div>
				<table id="groupTable" style="width: 100%; height: 300px"></table>
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
		
			//初始化查询条件--------------------end

			//列宽设置为%，并完美100%，必须用js方式
			$("#groupTable").datagrid({
								url : "group/groupList",
								method : "POST",
								rownumbers : true,
								pagination : true,
								striped : true,
								sortName: 'GROUP_ID', //排序的列
								sortOrder: 'asc', //升序
								singleSelect : true,
								columns : [ [
										{
											field : "groupId",
											title : "科目ID",
											halign : "center",
											width : "40%",
											align:'center',
											resizable : false
										},
										{
											field : "groupName",
											title : "科目名称",
											halign : "center",
											width : "40%",
											align:'center',
											resizable : false
										},
										{
											field : "groupStatus",
											title : "科目状态",
											halign : "center",
											width : "40%",
											align:'center',
											resizable : false,
											formatter:function(value,row,index){
												return row.groupStatus==0?'禁用':'启用';  
											}
							   			},
										{field:'operate',title:'操作',width:"21%",sortable:false,align:'center',halign:'center',
											formatter:function formatOper(val,row,index){  
												var s = '<a href="#" onclick="editRow('+index+')" class="editcls"></a>';
												s+= '<a href="#" onclick="rsetRow('+index+')" class="editpswd"></a>';
												if(row.groupStatus ==1)
												{
													s+= '<a href="#" onclick="deleteRow('+index+')" class="disabledcls"></a>';
												}else{
													s+= '<a href="#" onclick="deleteRow('+index+')" class="enablecls"></a>';
												}
											    return s;  
											} 
										}
							   			] ],
								onLoadSuccess : function(data) {
									$("#groupTable").datagrid("hideColumn", "groupId");
									$(".disabledcls").linkbutton({
										plain : true,
										iconCls : 'icon-cancel',
										height : '18px'
									});
									$(".enablecls").linkbutton({
										plain : true,
										iconCls : 'icon-ok',
										height : '18px'
									});
									
//									修改行按钮
									$(".editcls").linkbutton({
										plain : true,
										iconCls : 'icon-edit',
										height:'18px'
									});
								}
							});
								

});
		
		function searchGroup(){
			var params = $('#groupTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
			var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
			$.each( fields, function(i, field){
				params[field.name] = field.value; //设置查询参数
			}); 
			$('#groupTable').datagrid('reload'); //设置查询参数 reload  
		}
		
		//行删除
		function deleteRow(index){ 
			$('#groupTable').datagrid('selectRow',index);// 关键在这里  
			var row = $('#groupTable').datagrid('getSelected');
			var alertmsg = "";
			var status;
			if(row.groupStatus == 0)
			{
				alertmsg = "确定要启用\""+row.groupName+"\"吗?";
				status = 1;
			}else{
				alertmsg = "确定要禁用\""+row.groupName+"\"吗?";
				status = 0;
			} 
			$.messager.confirm('提示',alertmsg,function(result){
				if (result){
					$.post('group/deleteGroup',{groupId:row.groupId,groupStatus:status},function(data){
						$('#groupTable').datagrid('reload'); 
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
		
		//行修改
		function editRow(index){ 
			$('#groupTable').datagrid('unselectAll');//先清除所有选中的行
			$('#groupTable').datagrid('selectRow',index);// 关键在这里  
			var row = $('#groupTable').datagrid('getSelected'); 
			showWindow({
				title:'修改群组信息',
				href:'group/updateGroup?groupId='+row.groupId,
				width:1000,
				height:'auto'
			});
		}
		
		//新增群组
		function addGroup(){
			showWindow({
				title:'群组信息录入',
				href:'group/addGroup',
				width:1000,
				height:'auto'
			});
		}
	</script>
</body>
</html>