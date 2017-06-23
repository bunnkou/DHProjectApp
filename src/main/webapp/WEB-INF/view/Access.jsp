<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    HttpSession s = request.getSession(); 
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>"></base>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="bs-plugins/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="bs-plugins/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="bs-plugins/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="bs-plugins/css/plugins/iCheck/custom.css" rel="stylesheet">
  </head>
 
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
    	
    	<div class="row">
    		<div class="col-sm-12">
    			<div class="ibox float-e-margins">
    				<div class="ibox-title">
    					<h5>群组信息</h5>
    				</div>
    				<div class="ibox-content">
    					<form class="form-horizontal" name="access_form_c" role="form" action="access/store" method="post">
    						<div style="display:none;"><input type="hidden" name="id" value="${ access.id }" /></div>
    						<!-- 编辑权限 -->
    						<shiro:hasPermission name="fdbk:edit">
								<div class="form-group">
								  <label class="col-sm-2 control-label" for="inputUserName">用户姓名</label>
								  <div class="col-sm-8">
								  	<input class="form-control" id="inputUserName" name="inputUserName" placeholder="姓名" value="${ access.userName }" />
								  </div>
								</div>
								<div class="hr-line-dashed"></div>
								<div class="form-group">
								  	<label class="col-sm-2 control-label">权限</label>
	                                <div class="col-sm-8">
	                                	<c:forEach items="${ roleLst }" var="role">
	                                	<label class="checkbox-inline i-checks" style="min-width:61;">
	                                		<input type="checkbox" value="${ role.id }" id="i_cb_${ role.id }" name="inputRoleMember">${ role.roleTitle }
                                        </label>
                                        </c:forEach>
	                                </div>
								</div>
							</shiro:hasPermission>
							
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-2">
									<button type="button" class="btn btn-primary" onclick="submitFB();">保存</button>
									<button type="button" class="btn btn-default" onclick="returnLst();">返回</button>
								</div>
							</div>
    					</form>
    				</div>
    			</div>
    		</div>
    	</div>
    	
    </div>
	
	<script type="text/javascript" src="JS/tagsinput/jquery-2.2.4.min.js"></script>
	<script type="text/javascript" src="Bootstrap/js/bootstrap.min.js"></script>
	<!-- iCheck -->
    <script src="JS/iCheck/icheck.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green'
            });
            
            setChecks();
        });
        
        function setChecks(){
        	var member = "${group.groupMember}";
        	if (member!=""){
 				var ret = member.split(",");
 				for(var i=0; i<ret.length; i++){
 					$('#i_cb_'+ret[i]).iCheck('check');
 				}	
        	}
        }
        
        function submitFB(para){
			document.access_form_c.submit();
		}
		
		function returnLst(){
			var url = "<%=request.getContextPath()%>/access/index";
			$("#J_iframe", window.parent.document).attr('src',url);
			return false;
		}
    </script>
	
  </body>
</html>
