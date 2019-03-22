<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<body>
 <script type="text/javascript" src="${ctx }/js/jquery/jquery.min.js" ></script> 
<script type="text/javascript">
  $(function(){
     $("#id").click(function(){
         var url="${pageContext.request.contextPath}/searchOneRole";
         var searchId=$("#name").val();
         alert(searchId);
         var sendData={"searchId":searchId};
         $.post(url,sendData,function(backData,textStaut,ajax){
              alert(backData.roleName);
         });
     });
  });
</script>
<h2>Hello World!</h2>
用户名： ${user.userName}<br>
 密码：${user.userPassword}<br>
 <a href="search" >查询所有用户</a>
 <a href="add">增加用户</a>
 <a href="upd">修改用户</a>
 <a href="searchUser">查询所有角色</a><br/>
 
 <input type="text" id="name" name="searchId" /><input type="button" id="id"  value="编号查询"></input>

 
</body>
</html>