<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<head>
	<base href="<%=basePath%>"></base>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>Bootstrap/css/bootstrap.min.css">
	<script type="text/javascript" src="<%=basePath%>JS/jquery-1.11.3/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>Bootstrap/js/bootstrap.min.js"></script>
</head>

<div class="navbar-wrapper">
	<div class="container" id="navcontainer">
	  <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="background-color:#e6e6e6 !important;">
	    <div class="container">
	      <div class="navbar-header">
	        <a class="navbar-brand" href="#">反馈系统</a>
	      </div>
	      <div class="navbar-form navbar-right">
	        <ul class="nav navbar-nav">
	          <li><button type="button" class="btn-xs btn-default" onclick="loginOut();">登出</button></li>
	        </ul>
	      </div>
	    </div>
	  </nav>
	</div>
</div>

<script type="text/javascript">
<!--
function loginOut(){
	location.href = "<%=request.getContextPath()%>/logout";
}
//-->
</script>
