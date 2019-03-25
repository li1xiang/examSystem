<%@page import="saptacims.cst.ActiveStatus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改群组信息</title>
</head>
<body>
	<%-- <jsp:include page="../common/commonDia.jsp" /> --%>
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="section-a-title h5">群组信息修改</div>
			</div><!-- section-a-head end -->
			<div class="section-a-body">
				<div class="section-f section-f-fixed-800 m-t-10">
					<div class="section-f-head">
						<div class="h4">群组信息修改</div>
					</div>
					<!-- section-f-head end -->
					<form id="modGroup" method="POST" action="">
						<input type="hidden" name="groupId" id="groupIdMod" />
						<div class="section-f-body">
							<!-- san-row end -->
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">群组名称</div>
								<div class="san-col-3">
									<input id="accountMod" name="groupName" class="easyui-textbox"
										style="width: 100%;" data-options="required:true,validType:'length[1,100]'" value="${group.groupName}"> 
								</div>
								<div class="san-col-1"></div>
								
								<div class="san-col-2" align="right">群组状态</div>
								<div class="san-col-3">
									<select id="groupStatusMod" name="groupStatus" class="easyui-combobox"
										style="width: 100%;">
									</select>
								</div>
							</div>
							
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">创建人</div>
								<div class="san-col-3">
									<input name="groupName" class="easyui-textbox"
										style="width: 100%;" disabled="false"  value="${group.createrName}"> 
								</div>
								<div class="san-col-1"></div>
								
								<div class="san-col-2" align="right">创建时间</div>
								<div class="san-col-3">
									<input id="createTime" name="groupName" class="easyui-textbox"
										style="width: 100%;" disabled="false" value="${group.createTimeStr}"> 
								</div>
							</div>
							
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">更新人</div>
								<div class="san-col-3">
									<input  name="groupName" class="easyui-textbox"
										style="width: 100%;" disabled="false" value="${group.updaterName}"> 
								</div>
								<div class="san-col-1"></div>
								
								<div class="san-col-2" align="right">更新时间</div>
								<div class="san-col-3">
									<input id="updateTime" name="groupName" class="easyui-textbox"
										style="width: 100%;" disabled="false" value="${group.updateTimeStr}"> 
								</div>
							</div>
							
							<div class="san-row section-f-row">
								<div class="san-col-2" align="right">备注</div>
								<div class="san-col-3">
									<input class="easyui-textbox" name="remark" data-options="multiline:true,validType:'length[0,255]'" value="${group.remark}" style="width:550px;height:100px">
								</div>
							</div>
							<!-- san-row end -->
						</div>
						<!-- section-f-body end -->
					</form>
					<div class="section-f-foot AR">
						<a id="cancelInUpdateGroup" href="#"
							class="easyui-linkbutton mw-70 m-r-10">取消</a> <a
							id="saveInUpdateGroup" href="#"
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
 			$("#groupStatusMod").combobox({
				valueField:'id', //值字段
				textField:'text', //显示的字段
				url:'group/statusList',
				panelHeight:'auto',
				required:false,
				editable:false,//不可编辑，只能选择
				onLoadSuccess:function(){
					$("#groupStatusMod").combobox("setValue", "${group.groupStatus}");
				}
			}); 
			
 			 $("#groupIdMod").val("${group.groupId}");
 			 
				
 			//初始化新增表单
 			$("#modGroup").form({
 				url : "group/doUpdateGroup",
 				type : "POST",
 				success : function(data) {
 					var result = eval("(" + data + ")");
 					console.info(result.modFlag);
 					if (result.modFlag) {
 						showSuccessMsgDia(result.msg);
 						$('#groupTable').datagrid("reload");
						closeWindow();
 					}else {
 						showFailureMsgDia(result.msg);
 					}
 				}
 			});
 			
 			
 			//新增成交单页面取消按钮
			$("#cancelInUpdateGroup").bind("click", function() {
				closeWindow();
			});
 			
			//新增页面保存按钮
			$("#saveInUpdateGroup").bind("click", function() {
				showConfirmDia("您确定要更新吗？", function(){
					$("#modGroup").form("submit");
					$("#confirmDia").dialog("close");
				});
			});
			
		});
		
	
	</script>
	
</body>
</html>