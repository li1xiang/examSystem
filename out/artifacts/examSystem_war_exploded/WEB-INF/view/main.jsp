<%@ page import="saptacims.model.TbUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- <%@ include file="../../taglibs.jsp"%> --%>
<%@ include file="../../session.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- 修改表格的行高 -->
<style>
/* .datagrid-cell {
	line-height: 35px
} */
</style>
<meta http-equiv="Expires" CONTENT="0">
<meta http-equiv="Cache-Control" CONTENT="no-cache">
<meta http-equiv="Pragma" CONTENT="no-cache">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<title>考试系统</title>
<script>
	$(function() {
		//$.parser.parse('#main-page-body-content');
		
		$.post('menulist',function(data){
			var menulist = data.menulist;
			$("#main-menu").html("");
			var menuHtml = '';
			console.log(menulist.length);
			for(var i=0;i<menulist.length;i++)
			{
				var menu = menulist[i];
				menuHtml += "<li><a class='level-1' href='javascript:void(0);'>"+menu['topMenu']+"</a>";
				var secondmenu = menu['secondMenu'];
				for(var j=0;j<secondmenu.length;j++)
				{
					if(j==0)
					{
						menuHtml +="<ul class='sub-menu'>";
					}
					menuHtml += "<li><a class='level-2' href='javascript:void(0);' data-url='${ctx}"+secondmenu[j].menuUrl+"'>"+secondmenu[j].menuName+"</a></li>";
					if(j+1 == secondmenu.length)
					{
						menuHtml += "</ul>";
					}
				}
				menuHtml+= "</li>";
			}
			$("#main-menu").html(menuHtml);
			$('#main-menu').sanMainMenu({
				'default' : false
			});
		},'json');
		
		
		fixLayout();
		$(window).on('resize', function() {
			setTimeout(fixLayout, 300);
		});
		$('#main-page-body-content').sanJumpto({
			url : 'subpageDefault'
		});
		
	});

	//自定义表单认证规则
	$.extend(
					$.fn.validatebox.defaults.rules,
					{
						dateVerified : {
							validator : function(value, param) {

								return /^([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))$/
										.test(value);
							},
							message : '格式不正确,如：2000-05-10'
						},
						phone : {// 验证电话号码 
							validator : function(value) {
								/* return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i
										.test(value); */
								return /^(^(\d{3,4}-)?\d{7,8})$|(1[34578]\d{9})$/i
										.test(value);
							},
							message : '格式不正确,如：手机号/电话：(区号)-85802212'
						},
						// 验证传真
						faxno : {
							validator : function(value) {

								return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i
										.test(value);
							},
							message : '格式不正确，如：(区号)-85802212'
						},
						// 验证邮政编码 
						zip : {
							validator : function(value) {
								return /^[1-9]\d{5}$/i.test(value);
							},
							message : '格式不正确,如：858022'
						},
						//手机号码格式认证

						mobilePhone : {
							validator : function(value) {
								return /^(?:13\d|15\d|18\d)-?\d{5}(\d{3}|\*{3})$/.test(value);

							},
							message : '手机号码格式不正确'
						},
						//登录名只能是英文字母
						
						english : {
							validator : function(value) {
								//return /^[A-Za-z]+$/i.test(value);
								return /^[a-zA-Z][a-zA-Z0-9]*$/.test(value);
							},
							message : '请输入英文'
						},
						chs : {
							validator : function(value) {
								//return /^[A-Za-z]+$/i.test(value);
								return /^[\u0391-\uFFE5]+$/.test(value);
							},
							message : '请输入中文'
						},
					});
	//登出
	function logout() {
		$("#confirmCloseDia").dialog("open");
	}

	$.extend($.messager.defaults, {
		ok : "确定",
		cancel : "取消"
	});

	function postSanJumpTo(_jumpUrl, _poParam) {
		if (_poParam == null)
			_poParam = {};
		$('#main-page-body-content').sanJumpto({
			url : _jumpUrl,
			type : 'POST',
			param : _poParam
		});
	}
	
	
	//弹出窗口
	function showWindow(options){
		jQuery("#MyPopWindow").window(options);
	}
	//关闭弹出窗口
	function closeWindow(){
		$("#MyPopWindow").window('close');
	}
</script>



</head>

<body>

	<%
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		TbUser user = (TbUser) session.getAttribute("currentUser");
		if (user == null)
			response.sendRedirect(request.getContextPath());
	%>
	<%@ include file="common/commonDia.jsp" %> 
	<div id="main-page">
		<div id="main-page-head">
			<div id="system-name"></div>

			<ul id="main-page-head-btn-list">
				<li class="active"><a class="p-l-25 p-r-25"
					href="javascript:void(0);" title="当前账号">${currentUser.userCname }</a></li>
				<li><a href="javascript:void(0);" onclick="logout();" title="退出系统"><span class="san-icon san-icon-close"></span></a></li>
			</ul>
		</div>
		<!-- main-page-head end -->

		<div id="main-page-body">
			<div id="main-page-body-left">
				<div id="main-menu-slide-handle">
					<a class="san-icon-32 san-icon-32-context"
						href="javascript:void(0);"></a>
				</div>
				<ul id="main-menu">
				</ul>
			</div>
			<!-- main-page-body-left end -->

			<div id="main-page-body-content" style="overflow:auto"></div>
			<!-- main-page-body-content end -->
		</div>
		<!-- main-page-body end -->

		<div id="main-page-foot">
			<div class="AR m-r-10">&copy;&nbsp;All Right Reserved.</div>
		</div>
		<!-- main-page-foot end -->

	</div>
	<!-- main-page end -->



<div id="MyPopWindow" modal="true" shadow="false" minimizable="false" cache="false" maximizable="false" collapsible="false" resizable="false" style="margin: 0px;padding: 0px;overflow: auto;left:window.screen.availWidth/2;top:100px;"></div>
</body>


</html>
