<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@include file="../../../taglibs.jsp"%> --%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<div id="confirmCloseDia" style="display:none;">
    <div class="section-f section-f-161012 m-b-0">
        <div class="section-f-head section-f-head-l">
            <div class="TB BHC">
                <div class="CELL AT p-r-20"><span class="san-icon-48 san-icon-48-attention"></span></div>
                <div class="CELL AM h4 m-t-0 m-b-0 N">是否确认退出？</div>
            </div>
        </div><!-- section-f-head end -->
        <div class="section-f-foot AC">
            <a id="makeSureInConfirmCloseDia" href="#" class="easyui-linkbutton mw-70 m-r-50">确定</a>
            <a id="cancelInConfirmCloseDia" href="#" class="easyui-linkbutton mw-70">取消</a>
        </div><!-- section-f-foot end -->
    </div><!-- section-f end -->
</div>

<div id="confirmDia" style="display:none;">
    <div class="section-f section-f-161012 m-b-0">
        <div class="section-f-head section-f-head-l">
            <div class="TB BHC">
                <div class="CELL AT p-r-20"><span class="san-icon-48 san-icon-48-attention"></span></div>
                <div class="CELL AM h4 m-t-0 m-b-0 N"><span id="wordInConfirmDia" style="display:block; white-space:pre-wrap;word-wrap : break-word ;overflow: hidden ;"></span></div>
            </div>
        </div><!-- section-f-head end -->
        <div class="section-f-foot AC">
            <a id="makeSureInConfirmDia" href="#" class="easyui-linkbutton mw-70 m-r-50">确定</a>
            <a id="cancelInConfirmDia" href="#" class="easyui-linkbutton mw-70">取消</a>
        </div><!-- section-f-foot end -->
    </div><!-- section-f end -->
</div>

<div id="successDia" style="display:none;">
    <div class="section-f m-b-0">
        <div class="section-f-head section-f-head-l bg-success">
            <div class="TB BHC">
                <div class="CELL AT p-r-20"><span class="san-icon-48 san-icon-48-check"></span></div>
                <div class="CELL AM h4 m-t-0 m-b-0 N"><span id="wordInSuccessDia" style="display:block; white-space:pre-wrap;word-wrap : break-word ;overflow: hidden "></span></div>
            </div>
        </div><!-- section-f-head end -->
        <div class="section-f-foot AC">
            <a id="makeSureInSuccessDia" href="#" class="easyui-linkbutton mw-70">确定</a>
        </div><!-- section-f-foot end -->
    </div><!-- section-f end -->
</div>

<div id="failureDia" style="display:none;">
    <div class="section-f m-b-0">
        <div class="section-f-head section-f-head-l bg-fail AC">
            <div class="TB BHC">
                <div class="CELL AT p-r-20"><span class="san-icon-48 san-icon-48-error"></span></div>
                <div class="CELL AM h4 m-t-0 m-b-0 N"><span id="wordInFailureDia" style="display:block; white-space:pre-wrap;word-wrap : break-word ;overflow: hidden ;"></span></div>
            </div>
        </div><!-- section-f-head end -->
        <div class="section-f-foot AC">
            <a id="makeSureInFailureDia" href="#" class="easyui-linkbutton mw-70">确定</a>
        </div><!-- section-f-foot end -->
    </div><!-- section-f end -->
</div>

<script type="text/javascript">
	$(function(){
		$.parser.parse("#confirmDia");
		$.parser.parse("#confirmCloseDia");
		$.parser.parse("#successDia");
		$.parser.parse("#failureDia");
	    $('#confirmDia').dialog({
	        title: false,
	        width: 400,
	        height: 'auto',
	        bodyCls: 'z-easyui-dialog',
	        closed: true,
	        closable: false,
	        cache: false,
	        modal: true,
	        onBeforeClose: function(){
	        	console.info("onBeforeClose-confirm");
	        	$("#makeSureInConfirmDia").unbind("click");
	        	//console.info("解绑!");
	        }
	    });
	    $('#confirmCloseDia').dialog({
	        title: false,
	        width: 400,
	        height: 'auto',
	        bodyCls: 'z-easyui-dialog',
	        closed: true,
	        closable: false,
	        cache: false,
	        modal: true,
	        onBeforeClose: function(){
	        	//$("#makeSureInConfirmCloseDia").unbind("click");
	        }
	    });

	    $('#successDia').dialog({
	        title: false,
	        width: 400,
	        height: 'auto',
	        bodyCls: 'z-easyui-dialog',
	        closed: true,
	        closable: false,
	        cache: false,
	        modal: true,
	        onBeforeClose: function(){
	        	$("#makeSureInSuccessDia").unbind("click");
	        }
	    });

		$('#failureDia').dialog({
	        title: false,
	        width: 400,
	        height: 'auto',
	        bodyCls: 'z-easyui-dialog',
	        closed: true,
	        closable: false,
	        cache: false,
	        modal: true,
	        onBeforeClose: function(){
	        	$("#makeSureInFailureDia").unbind("click");
	        }
	    });

	    //成功框确认按钮点击事件
	    $("#makeSureInSuccessDia").bind("click",function(){
	    	$("#successDia").dialog("close");
	    });

	    //失败框确认按钮点击事件
	    $("#makeSureInFailureDia").bind("click",function(){
	    	$("#failureDia").dialog("close");
	    });
	    
	    //确认框取消按钮点击事件
	    $("#cancelInConfirmDia").bind("click",function(){
	    	//console.info("click:close");
	    	$("#confirmDia").dialog("close");
	    });
	    
	    //退出系统确认框取消按钮点击事件
	    $("#cancelInConfirmCloseDia").bind("click",function(){
	    	$("#confirmCloseDia").dialog("close");
	    });
	    
	    //退出系统确认框确认按钮
	    $("#makeSureInConfirmCloseDia").bind("click",function(){
	    	window.location.href="${pageContext.request.contextPath}/logout";
	    })
	});
    function showSuccessMsgDia(_msg, _url){
    	$("#wordInSuccessDia").text(_msg);
    	$("#makeSureInSuccessDia").click(function(){
	    	$("#successDia").dialog("close");
    		if(_url){
    			$('#main-page-body-content').sanJumpto({
					url:_url
				});
    		}
	    	//$("#makeSureInSuccessDia").unbind("click");
    	});
    	$("#successDia").dialog("open");
    }
    function showFailureMsgDia(_msg){
    	$("#wordInFailureDia").text(_msg);
    	
    	$("#makeSureInFailureDia").click(function(){
	    	$("#failureDia").dialog("close");
	    	//$("#makeSureInFailureDia").unbind("click");
    	});
    	$("#failureDia").dialog("open");
    }
    function showConfirmDia(_msg, _func){
    	$("#wordInConfirmDia").text(_msg);
    	
    	$("#makeSureInConfirmDia").click(function(){
	    	$("#confirmDia").dialog("close");
	    	if(_func)
    			_func();
	    	//$("#makeSureInConfirmDia").unbind("click");
    	});
    	$("#confirmDia").dialog("open");
    }
</script>
</body>
</html>