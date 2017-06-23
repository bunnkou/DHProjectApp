<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<head>
	<base href="<%=basePath%>"></base>
	<link href="<%=basePath%>bs-plugins/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="<%=basePath%>bs-plugins/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="<%=basePath%>bs-plugins/css/animate.css" rel="stylesheet">
    <link href="<%=basePath%>bs-plugins/css/style.css?v=4.1.0" rel="stylesheet">
    
    <title>登录</title>
</head>

<body class="gray-bg">
	<div class="middle-box text-center loginscreen  animated fadeInDown">
       <div>
			<div style="margin-top:40px;">&nbsp;</div>
           	<h3>欢迎使用动画部反馈系统</h3>
			<form:form class="m-t" role="form" action="login" commandName="user">
				<div class="form-group">
				    <form:input path="name" class="form-control" placeholder="用户名" autofocus="autofocus" />
				</div>
				<div class="form-group">
				    <form:password path="password" class="form-control" placeholder="密码" />
				</div>
				<form:button name="submit" class="btn btn-primary block full-width m-b">登录</form:button>
			</form:form>
    	</div>
	</div>
	<!-- 全局js -->
    <script type="text/javascript" src="<%=basePath%>JS/jquery-1.11.3/jquery.min.js"></script>
    <script src="<%=basePath%>bs-plugins/js/bootstrap.min.js?v=3.3.6"></script>
</body>