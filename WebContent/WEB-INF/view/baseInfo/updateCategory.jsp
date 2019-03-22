<%@page import="saptacims.cst.ActiveStatus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改类别信息</title>
</head>
<body>
	<%-- <jsp:include page="../common/commonDia.jsp" /> --%>
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="section-a-title h5">类别信息修改</div>
			</div><!-- section-a-head end -->
			<div class="section-a-body">
				<div class="section-f section-f-fixed-800 m-t-10">
					<div class="section-f-head">
						<div class="h4">类别信息修改</div>
					</div>
					<!-- section-f-head end -->
					<form id="modCategory" method="POST" action="">
						<input type="hidden" name="categoryId" id="categoryIdMod" />
						<div class="section-f-body">
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">类别名称</div>
								<div class="san-col-3">
									<input name="categoryName" class="easyui-textbox"
										style="width: 100%;" data-options="required:true,validType:'length[1,100]'" value="${category.categoryName}">
										 
								</div>
								<div class="san-col-1"></div>
								
								<div class="san-col-2" align="right">类别状态</div>
								<div class="san-col-3">
									<select id="categoryStatusMod" name="categoryStatus" class="easyui-combobox"
										style="width: 100%;">
									</select>
								</div>
							</div>
							
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">创建人</div>
								<div class="san-col-3">
									<input name="categoryName" class="easyui-textbox"
										style="width: 100%;" disabled="false"  value="${category.createUsername}"> 
								</div>
								<div class="san-col-1"></div>
								
								<div class="san-col-2" align="right">创建时间</div>
								<div class="san-col-3">
									<input id="createTime" name="categoryName" class="easyui-textbox"
										style="width: 100%;" disabled="false"  value="${category.createTimeStr}"> 
								</div>
							</div>
							
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">更新人</div>
								<div class="san-col-3">
									<input name="categoryName" class="easyui-textbox"
										style="width: 100%;" disabled="false"  value="${category.updateUsername}"> 
								</div>
								<div class="san-col-1"></div>
								
								<div class="san-col-2" align="right">更新时间</div>
								<div class="san-col-3">
									<input id="updateTime" name="categoryName" class="easyui-textbox"
										style="width: 100%;" disabled="false"  value="${category.updateTimeStr}"> 
								</div>
							</div>
							<!-- san-row end -->
						</div>
						<!-- section-f-body end -->
					</form>
					<div class="section-f-foot AR">
						<a id="cancelInUpdateCategory" href="#"
							class="easyui-linkbutton mw-70 m-r-10">取消</a> 
							<a id="saveInUpdateCategory" href="#"
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
	
	<script>
	
		$(function(){
			//初始化状态下拉框
 			$("#categoryStatusMod").combobox({
				valueField:'id', //值字段
				textField:'text', //显示的字段
				url:'category/statusList',
				panelHeight:'auto',
				required:false,
				editable:false,//不可编辑，只能选择
				onLoadSuccess:function(){
					$("#categoryStatusMod").combobox("setValue", "${category.categoryStatus}");
				}
			}); 
			
 			 $("#categoryIdMod").val("${category.categoryId}");
 			 
 			 
 			 
 			//初始化新增表单
 			$("#modCategory").form({
 				url : "category/doUpdateCategory",
 				type : "POST",
 				success : function(data) {
 					var result = eval("(" + data + ")");
 					console.info(result.modFlag);
 					if (result.modFlag) {
 						showSuccessMsgDia(result.msg);
 						$('#categoryTable').datagrid("reload");
						closeWindow();
 					}else {
 						showFailureMsgDia(result.msg);
 					}
 				}
 			});
 			
 			$("#cancelInUpdateCategory").bind("click", function() {
				closeWindow();
			});
 			
			$("#saveInUpdateCategory").bind("click", function() {
				//$.messager.confirm("操作提示", "您确定要更新吗？", function (data) {  
		        //    if (data) {  
		            	//新增表单提交
				//		$("#modCategory").form("submit");
		        //    }  
		        //});  
				showConfirmDia("您确定要更新吗？", function(){
					$("#modCategory").form("submit");
					$("#confirmDia").dialog("close");
				})
			});
			
		});
	
				
				
	</script>
</body>
</html>