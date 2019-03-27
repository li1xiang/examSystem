<%@page import="saptacims.cst.ActiveStatus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>科目信息录入</title>
</head>
<body>
<%-- 	<jsp:include page="../common/commonDia.jsp" /> --%>
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="section-a-title h5">年级信息录入</div>
			</div><!-- section-a-head end -->
			<div class="section-a-body">
				<div class="section-f section-f-fixed-800 m-t-10">
					<div class="section-f-head">
						<div class="h4">年级信息录入</div>
					</div>
					<!-- section-f-head end -->
					
						<div class="section-f-body">
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">年级名称</div>
								<div class="san-col-3">
									<input class="easyui-textbox"
										style="width: 100%;" id="name" data-options="required:true,validType:'length[0,100]'"> 
								</div>
							</div>
						</div>
					
					<div class="section-f-foot AR">
						<a id="cancelInAddCategory" href="#"
							class="easyui-linkbutton mw-70 m-r-10">取消</a> 
							<a id="saveInAddCategory" href="#"
							class="easyui-linkbutton mw-70 m-r-10 z-easyui-bg-primary" onclick="savaCategory()">保存</a>
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
		//新增类别
		function savaCategory(){
			var categoryName = $("#name").val();
			//console.info("类别名称长度"+categoryName.trim().length);
			if(categoryName.trim().length>100){
				showFailureMsgDia("年级长度不能超过100");
				return;
			}
			$.post('category/saveCategory',
					{'categoryName':$("#name").val()},
					function(result){
						if(result.saveFlag){
							$('#categoryTable').datagrid('reload');
							showSuccessMsgDia(result.msg);
							closeWindow();
						}else{
							showFailureMsgDia(result.msg);
						}
					},'json');
		}
	
		$(function(){
			//取消按钮
			$("#cancelInAddCategory").bind("click",function(){
				closeWindow();
			});
		});
	
		
		
	</script>
</body>
</html>