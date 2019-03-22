
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>关联试卷</title>
</head>
<body>
	<%-- <jsp:include page="../common/commonDia.jsp" /> --%>
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="section-a-title h5">试卷关联</div>
			</div>
			<!-- section-a-head end -->

			<form id="relatedForm" method="POST">
				<div class="section-a-body">
					<div class="section-f section-f-fixed-800 m-t-10">
						<!-- section-f-head end -->
						<div class="san-row section-f-row">
							<input type="hidden" name="interviewerId"
								value="${interviewerId }">
							<!-- <input id="examinationId" name="examinationIds"
									class="easyui-combobox" style="width: 100%"
									data-options="required:true,limitToList:true"> -->

							<div class="easyui-layout" style="width: 100%; height: 300px;">
								<div data-options="region:'west',split:true" title="试卷列表"
									style="width: 45%;">
									<table id="examinationList" style="width: 100%; height: 400px;"></table>
								</div>
								<div data-options="region:'east',split:true" title="关联试卷"
									style="width: 45%;">
									<table id="relatedTable" style="width: 100%; height: 300px;"></table>
								</div>
								<div data-options="region:'center'" class="AC AM CELL">
									<a href="#" onclick="leftToRight()"
										class="easyui-linkbutton m-t-10 mw-70 m-r-10 z-easyui-bg-primary">>></a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- section-f-body end -->
			</form>
			<div class="section-f-foot AR">
				<a id="cancelInInterviewerManage" href="#"
					class="easyui-linkbutton mw-70 m-r-10">取消</a> <a
					id="saveInRelatedExamination" href="#"
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
			//初始化试卷表格
			$("#examinationList").datagrid({
				url : "interviewer/examinationList",
				method : "POST",
				columns : [ [ {
					field : "examinationId",
					title : "试卷ID",
					halign : "center",
					width : "20%",
					align : 'center',
					resizable : false
				}, {
					field : "examinationName",
					title : "试卷名",
					halign : "center",
					width : "80%",
					align : 'center',
					resizable : false
				} ] ]
			});

			$("#relatedTable").datagrid({
				method : "POST",
				columns : [ [ {
					field : "examinationId",
					title : "试卷ID",
					halign : "center",
					width : "20%",
					align : 'center',
					resizable : false
				}, {
					field : "examinationName",
					title : "试卷名",
					halign : "center",
					width : "100%",
					align : 'center',
					resizable : false
				} ] ],
				toolbar:[{
					text:'删除',
					iconCls:'icon-cut',
					handler:function(){
						var exrows = $('#relatedTable').datagrid('getSelections');
						for(var i in exrows)
						{
							var index = $('#relatedTable').datagrid('getRowIndex',exrows[i]);
							$('#relatedTable').datagrid('deleteRow',index);
						}
					}
				}]
			});
			var relatedExaminations = ${oldExaminations};
			if(relatedExaminations!="-1")
				$("#relatedTable").datagrid('loadData',relatedExaminations);

			//初始化修改表单
			$("#relatedForm").form({
				url : "interviewer/doRelate",
				type : "POST",
				success : function(data) {
					var result = eval("(" + data + ")")
					if (result.relatedFalg) {
						showSuccessMsgDia(result.msg);
						closeWindow();
					} else {
						showFailureMsgDia(result.msg);
					}
				}
			});

			$("#saveInRelatedExamination").bind("click", function() {
				var myform=$('#relatedForm'); //得到form对象
       		 	var tmpInput=$("<input type='text' name='relatedTable'/>");
	        	tmpInput.attr("value", JSON.stringify($('#relatedTable').datagrid("getRows")));
	        	myform.append(tmpInput);
				//新增表单提交
				$("#relatedForm").form("submit");
				console.info($("input[name=relatedTable]").val());
				$("input[name=relatedTable]").remove();
			});

			//取消
			$("#cancelInInterviewerManage").bind("click", function() {
				closeWindow();
			})
		});
		
		function leftToRight()
		{
			var exrows = $('#relatedTable').datagrid('getRows');
			var length = exrows.length;
			var rows = $('#examinationList').datagrid('getSelections');
			var tag = true;
			for(var i in rows)
			{
				var tag = true;
				for(var j=0;j<length;j++)
				{
					if(exrows[j].examinationId==rows[i].examinationId)
					{
						tag = false;
						break;
					}else{
						tag = true;
					}
				}
				if(tag)
				{
					$('#relatedTable').datagrid('appendRow',rows[i]);
				}
			}
		}
	</script>
</body>
</html>