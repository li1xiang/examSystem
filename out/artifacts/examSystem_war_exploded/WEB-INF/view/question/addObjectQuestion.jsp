<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增考题</title>
</head>
<body>
	<div class="sub-page">
		<div class="section-a">
			<div class="section-a-head section-a-head-fixed">
				<div class="section-a-title h5">题库管理 > 客观题录入 </div>
			</div>
			<!-- section-a-head end -->

			<div class="section-a-body">
				<div class="section-f  m-t-10">
					<form id="saveQuestion" method="POST" action=""
						enctype="multipart/form-data">
						<div class="section-f-head">
							<div class="h4">信息录入</div>
						</div>
						<div class="easyui-tabs" data-options="tabWidth:112">
							<div title="题目录入" style="padding: 10px" class="section-f-body">
								<div class="san-row section-f-row">
									<input id="questionType" name="questionType" type="hidden"
										value=1>
									<div class="san-col-1" align="right">类别</div>
									<div class="san-col-2">
										<select id="categoryId" name="categoryId"
											class="easyui-combobox" style="width: 100%;"
											data-options="required:true,editable:false">
										</select>
									</div>
									<div class="san-col-1"></div>
									<div class="san-col-1" align="right">群组</div>
									<div class="san-col-2">
										<select id="groupId" name="groupId" class="easyui-combobox"
											style="width: 100%;"
											data-options="required:true,editable:false">
										</select>
									</div>
									<div class="san-col-1"></div>
									<div class="san-col-1" align="right">分数</div>
									<div class="san-col-2">
										<input id="score" name="score" class="easyui-numberbox"
											data-options="required:true" style="width: 100%" >
									</div>
									<div class="san-col-1"></div>
								</div>
								<!-- san-row end -->
								<div class="san-row section-f-row">
									<div class="san-col-1" align="right">难度</div>
									<div class="san-col-2">
										<label style="float:left;font-size:12px;"> <input type="radio" style="vertical-align:middle; margin-top:-2px; margin-bottom:1px;" value=3 name="levels" id="levels" >初级  &nbsp;</label>
										<label style="float:left;font-size:12px;"> <input type="radio" style="vertical-align:middle; margin-top:-2px; margin-bottom:1px;" value=2 name="levels" id="levels" >中级  &nbsp;</label>
										<label style="float:left;font-size:12px;"> <input type="radio" style="vertical-align:middle; margin-top:-2px; margin-bottom:1px;" value=1 name="levels" id="levels" >高级  &nbsp;</label>
									</div>
									<div class="san-col-1"></div>
									<div class="san-col-1"></div>
									<div class="san-col-2"></div>
									<div class="san-col-1"></div>
								</div>
								<!-- san-row end -->
								<div class="san-row section-f-row">
									<div class="san-col-1" align="right">题目</div>
									<div class="san-col-10">
										<input id="subject" name="subject" class="easyui-textbox"
											style="width: 100%;"
											data-options="multiline:true,height:'100px',validType:'length[0,500]'">
									</div>
									<div class="san-col-1"></div>
								</div>
								<div class="san-row section-f-row">
									<div class="san-col-1" align="right">附件</div>
									<div class="san-col-3">
										<input id="questionFile" name="questionFile"
											class="easyui-filebox" style="width: 100%;"
											data-options="prompt:'上传图片',accept:'image/jpeg,image/png'"
											buttonText="选择文件">
									</div>
									<div class="san-col-8"></div>
								</div>
							</div>
							<div title="答案录入" style="padding: 10px" class="section-f-body">
									<table id="answerTable" style="width: 100%; height: 200px"
										class="easyui-datagrid"
										data-options="rownumbers:false,singleSelect:true,url:'',method:'get'">

									</table>
							</div>
						</div>
					</form>
					<div class="section-f-foot AR">
						<a id="saveNewQuestion" href="#"
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
			$.parser.parse('#main-page-body-content');

			//初始化新增表单
			$("#saveQuestion").form(
					{
						url : "question/addObjecQuestion",
						type : "POST",
						resetForm : true,
						success : function(data) {
							var result = eval("(" + data + ")");
							if (result.saveFlag) {
								//成功框确认按钮点击事件
								showSuccessMsgDia(result.msg,
										'question/getAddObjectQuestion');
							} else {
								showFailureMsgDia(result.msg);
							}
						}
					});

			$("input[name='levels'][value=3]").attr("checked",true);
			
			//初始化类别下拉框
			$("#categoryId").combobox({
				valueField : 'id', //值字段
				textField : 'text', //显示的字段
				url : 'question/categoryList?page=add',
				panelHeight : 200,
				required : false,
				editable : false
			//不可编辑，只能选择
			});
			//初始化群组下拉框
			$("#groupId").combobox({
				valueField : 'id', //值字段
				textField : 'text', //显示的字段
				url : 'question/groupList?page=add',
				panelHeight : 200,
				required : false,
				editable : false
			//不可编辑，只能选择
			});

			
			$("#answerTable").datagrid({
				url : "",
				method : "POST",
				striped : true,
				singleSelect:false,
				rownumbers : true,
				onDblClickCell: onClickCell, 
				toolbar: [{
		            text:'添加',
		            iconCls:'icon-add',
		            handler:function(){
		            	append();
		            }
		        },{
		            text:'移除',
		            iconCls:'icon-remove',
		            handler:function(){
		            	removeit()
		            }
		        }],
				columns : [ [
						{
							field : "questionId",
							halign : "center",
							width : "10%",
							align:'center',
							resizable : false,
							hidden : true
						},
						{
							field : "answer",
							title : "答案内容",
							halign : "center",
							width : "90%",
							editor:'text',
							align:'left',
							resizable : false
			   			},
			   			{
							field : "isright",
							title : "选择正确答案",
							halign : "center",
							editor:{type:'checkbox',options:{on:1,off:0}},
							width : "10%",
							align:'center',
							resizable : false,
							formatter: function(value, rowData, rowIndex){
				                return value==1?'是':'否';
				            } 
						}] ],
				onLoadSuccess : function(data) {
				}
			});
			
			var editIndex = undefined;  
			function endEditing() {//该方法用于关闭上一个焦点的editing状态  
			    if (editIndex == undefined) {  
			        return true  
			    }  
			    if ($('#answerTable').datagrid('validateRow', editIndex)) {  
			        $('#answerTable').datagrid('endEdit', editIndex);  
			        editIndex = undefined;  
			        return true;  
			    } else {  
			        return false;  
			    }  
			};  
			
			function onClickCell(index, field){
	            if (editIndex != index){
	                if (endEditing()){
	                    $('#answerTable').datagrid('selectRow', index)
	                            .datagrid('beginEdit', index);
	                    var ed = $('#answerTable').datagrid('getEditor', {index:index,field:field});
	          /*           $(ed.target).focus().bind('blur', function () {
	                         $("#answerTable").datagrid('endEdit', index); 
	                   }); */
	                    if (ed){
	                       $(ed.target).focus();
	                    }
	                    editIndex = index;
	                } else {
	                    setTimeout(function(){
	                        $('#answerTable').datagrid('selectRow', editIndex);
	                    },0);
	                }
	            }
	        } 
			
			function removeit(){
	            if (editIndex != undefined){
		            $('#answerTable').datagrid('cancelEdit', editIndex);
		        } 
	            var rows = $('#answerTable').datagrid('getSelections');
	            for(i=0;i<rows.length;i++){
	            	$('#answerTable').datagrid('deleteRow',$('#answerTable').datagrid('getRowIndex',rows[i]));
	            }
	            editIndex = undefined;
	        }
			
			function append(){
	            if (endEditing()){
	                $('#answerTable').datagrid('appendRow',{questionId:'${question.questionId}',isright:0});
	                editIndex = $('#answerTable').datagrid('getRows').length-1;
	                $('#answerTable').datagrid('beginEdit', editIndex);
	            }
	        }
			
			function judge(row,str){
				debugger;
				for(var i= 0;i<row.length;i++){
					if(row[i]==str){
						return true;
					}
				}
			}
			
			//新增页面保存按钮
			$("#saveNewQuestion").bind("click",function() {
				endEditing();
				//校验题目
				var v = $('#score').numberbox('getValue');
				if(Number(v)<1||Number(v)>100){
					showFailureMsgDia("分数取值为1~100之间!");
					return;
				}
				
				if ($("#subject").val().trim()==''&&$("#questionFile").filebox('getValue')=='') {
					showFailureMsgDia("请填写题目或上传一个附件!");
					return;
				}
				
				
				
				//校验答案
				var arr=$('#answerTable').datagrid('getData');
				var num = 0;
				if(arr.rows.length<2){
					showFailureMsgDia("客观题答案不能少于两个!");
					return;
				}
				var dataArr = new Array();;
				for(i=0;i<arr.rows.length;i++){	
					if(arr.rows[i].answer.trim().length==0){
						showFailureMsgDia("答案内容不能为空!");
						return;
					}
					
					if(arr.rows[i].answer.trim().length > 500){
						showFailureMsgDia("字段不能超过500！");
						return;
					}
					if(judge(dataArr,arr.rows[i].answer.trim())){
						showFailureMsgDia("答案内容不能相同!");
						return;
					}
					dataArr.push(arr.rows[i].answer.trim());
					num+=Number(arr.rows[i].isright);
				}
				if(num==0){
					showFailureMsgDia("请选择一个正确答案!");
					return;
				}else if(num>1){
					showFailureMsgDia("只能选择一个正确答案!");
					return;
				}
				
				var myform=$('#saveQuestion'); //得到form对象
       		 	var tmpInput=$("<input type='hidden' name='answerTable' />");
	        	tmpInput.attr("value", JSON.stringify($('#answerTable').datagrid("getRows")));
	        	myform.append(tmpInput);
				
				//新增表单提交
				$("#saveQuestion").form("submit");
				$("input[name=answerTable]").remove();
			});
		});
	</script>



</body>
</html>