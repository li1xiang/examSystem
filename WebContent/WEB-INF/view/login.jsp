<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../taglibs.jsp" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>考试系统</title>
    <script>
    $(function(){
    	fixLayout();
    	$(window).on('resize', function(){
        	setTimeout(fixLayout, 100);
        	setTimeout(function(){
           		$('#section-login').animate({'top': (sanView.viewport.height() - lh) * 0.3 + 'px'}, 300)
        	}, 300);
    	});
		init();
    	var lh = parseFloat($('#section-login').outerHeight());
    	$('#section-login').css({
        	'top': (sanView.viewport.height() - lh) * 0.3 + 'px'
    	}).addClass('section-login-show');

    	$('#section-login').on('focus', ':input', function(){
        	$(this).closest('.section-login-input').addClass('section-login-input-active');
    	}).on('blur', ':input', function(){
       		$(this).closest('.section-login-input').removeClass('section-login-input-active');
    	});
    	
    	//登录账号输入框获取焦点
    	$("#userId").focus();
	});

/* 	function chageCode(){
        $('#img_code').attr('src','getImg?abc='+Math.random());//链接后添加Math.random，确保每次产生新的验证码，避免缓存问题。
    } */
    function init() {
    	var FaileUserId ="<%=session.getAttribute("FaileId")%>";
    	if(FaileUserId=="null"){
    		$("#userId").val("");
    	}else{
    		$("#userId").val(FaileUserId);
    	}
    	$("#password").val("");
		var Failure =<%=session.getAttribute("LoginFailure")%>;		
		$("#codeTr").hide();
    	/* if(Failure==null||Failure<2){
    		$("#codeTr").hide();
    	}else if(Failure>=2){
    		$("#codeTr").show();
    	} */
    }
    
    function toPwd(e){
    	var ev = window.event || e
    	//回车焦点移到密码框,回车键的键值为13
		if (ev.keyCode==13)
			$("#password").focus();
    }
    
    function login(e){
    	var ev = window.event || e
    	//回车调用登录按钮的点击事件
		if (ev.keyCode==13)
			$("#loginbtn").click();
    }
    $(function(){
    	$("#loginbtn").click(function(){
			$.ajax({
				type : "post",
				url : "signin",
				data : {
					"ACCOUNT" : $("#userId").val(),
					"password" : $("#password").val(),
					"code":$("#code").val()},
				dataType : "json",
				success : function(result) {
					var sgin = result.sgin;
					var msg = result.msg;
					if (sgin!=null) {
						window.location.href="${ctx}/main";	
					} else if(msg!=null){
						$.messager.alert("警告",msg,'info',function(){
							window.location.reload();//刷新当前页面
							return;
						});
					}
				}
			});
		});
	});
	</script>
  </head>
  
  <body>
	<div id="main-page">
		<div id="main-page-head">
			<div id="system-name"></div>
		</div>
		<!-- main-page-head end -->

		<div id="main-page-body" class="bg-161014">
			<div class="section-login" id="section-login">
				<div class="section-login-head">
					<!-- <img src="images/logo-x64-00649f.png" style="height: 32px;"> -->
				</div>
				<!-- section-login-head end -->
				<div class="section-login-body">
					<div class="section-login-input section-login-input-account san-placeholder">
						<input type="text" id="userId" class="san-placeholder-input" value="" onkeydown="toPwd(event);" >
						<span class="san-placeholder-text">登录账号</span>
                    	<label for="section-login-input-account"></label>
					</div>
					<div class="section-login-input section-login-input-password">
						<input type="password" id="password" value="" class="san-placeholder-input" onkeydown="login(event);">
						<span class="san-placeholder-text">密码</span>
                  		<label for="section-login-input-password"></label>
					</div>
					<div class="san-row m-t-20 m-b-20" id="codeTr">
<!-- 						<div class="san-col-5 p-r-0">
							<div class="section-login-input san-placeholder">
								<input type="text" onkeydown="login(event);" id="code" class="san-placeholder-input" value="" >
								<span class="san-placeholder-text">验证码</span>
                           		<label for="section-login-input-code"></label>
							</div>
						</div>
						<div class="san-col-5 p-r-0">
							<div class="section-login-input section-login-input-code-img"><img id="img_code" src="getImg"></img></div>
						</div> 
						<div class="san-col-2" style="width:20px">
							<a href="javascript:void(0);" title="刷新" onclick="chageCode();"><span
								class="san-icon san-icon-refresh-grey m-t-8"></span></a>
						</div>-->
					</div>
					<!-- san-row end -->
				</div>
				<!-- section-login-body end -->
				<div class="section-login-foot AR">
					<a class="section-login-btn" id="loginbtn">登录</a>
				</div>
				<!-- section-login-foot end -->
			</div>
			<!-- section-login end -->
		</div>
		<!-- main-page-body end -->

		<div id="main-page-foot">
			<div class="AR m-r-10">&copy;&nbsp;All Right Reserved.</div>
		</div>
		<!-- main-page-foot end -->
	</div>
	<!-- main-page end -->

</body>
</html>
