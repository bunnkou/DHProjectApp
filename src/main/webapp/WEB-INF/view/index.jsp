<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
	
    <title>主页</title>
	
    <meta name="keywords" content="">
    <meta name="description" content="">
	
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
	
	<base href="<%=basePath%>"></base>
    <link href="bs-plugins/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="bs-plugins/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="bs-plugins/css/animate.css" rel="stylesheet">
    <link href="bs-plugins/css/style.css?v=4.1.0" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg mini-navbar" style="overflow:hidden">
	<div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                    <span class="block m-t-xs" style="font-size:20px;">
                                        <i class="fa fa-area-chart"></i>
                                        <strong class="font-bold">Feedback</strong>
                                    </span>
                                </span>
                            </a>
                        </div>
                        <div class="logo-element">Fdbk
                        </div>
                    </li>
                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                        <span class="ng-scope">信息</span>
                    </li>
                    <li>
                        <a class="J_menuItem" href="<%=basePath%>fdbk/vwList">
                            <i class="fa fa-table"></i>
                            <span class="nav-label">表格</span>
                        </a>
                    </li>
                    <li>
                    	<a class="J_menuItem" href="<%=basePath%>fdbk/todoList">
                    		<i class="fa fa-file-o"></i> <span class="nav-label">待阅 </span>
                    		<c:if test="${todoCount ne 0 }">
                    			<span class="label label-warning pull-right">${todoCount}</span>
                    		</c:if>
                    	</a>
                    </li>
                    <shiro:hasRole name="admin">
	                    <li class="line dk"></li>
	                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
	                        <span class="ng-scope">管理</span>
	                    </li>
	                    <li>
	                        <a class="J_menuItem" href="<%=basePath%>admin/vwMonitor">
	                            <i class="fa fa-tasks"></i>
	                            <span class="nav-label">监控</span>
	                        </a>
	                    </li>
	                    <li>
	                        <a class="J_menuItem" href="<%=basePath%>admin/vwGroups">
	                            <i class="fa fa-users"></i>
	                            <span class="nav-label">群组</span>
	                        </a>
	                    </li>
	                    <li>
	                        <a class="J_menuItem" href="<%=basePath%>admin/vwGroups">
	                            <i class="fa fa-cog"></i>
	                            <span class="nav-label">权限</span>
	                        </a>
	                    </li>
                    </shiro:hasRole>
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
        	
        	<div class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
					<div class="navbar-header">
						<a class="navbar-minimalize minimalize-styl-2 btn btn-info " href="#"><i class="fa fa-bars"></i> </a>
						<a class="navbar-brand" href="#">
							<shiro:hasRole name="admin">管理员</shiro:hasRole>用户：${currUser}
						</a>
					</div>
					<ul class="nav navbar-nav navbar-right">
						<li>
							<a class="btn btn-default " href="#" onclick="checkOut();" style="padding:4px 2px 4px 12px; margin:10px 30px 5px 5px; font-size:14px;">
								<i class="fa fa-sign-out"></i> 
							</a>
						</li>
					</ul>
				</nav>
			</div>
        	
	        <div class="row J_mainContent" id="content-main">
	            <iframe id="J_iframe" width="100%" height="100%" src="<%=basePath%>fdbk/vwList" frameborder="0" data-id="<%=basePath%>index_v1" seamless></iframe>
	        </div>        
        </div>
        <!--右侧部分结束-->
        
        <!-- 全局js -->
	    <script src="bs-plugins/js/jquery.min.js?v=2.1.4"></script>
	    <script src="bs-plugins/js/bootstrap.min.js?v=3.3.6"></script>
	    <script src="bs-plugins/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	    <script src="bs-plugins/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	    <script src="bs-plugins/js/plugins/layer/layer.min.js"></script>
		
	    <!-- 自定义js -->
	    <script src="bs-plugins/js/hAdmin.js?v=4.1.0"></script>
	    <script type="text/javascript" src="bs-plugins/js/index.js"></script>
		
		<script type="text/javascript">
		function checkOut(){
			location.href = "<%=request.getContextPath()%>/loginOut.do";
		}
		</script>
</body>

</html>