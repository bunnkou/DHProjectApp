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
	<link href="JS/tagsinput/bootstrap-tagsinput.css" rel="stylesheet">
	<link href="JS/tagsinput/app.css" rel="stylesheet">
	
	<link href="JS/fancybox/jquery.fancybox.css" rel="stylesheet">
  </head>
 
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
    
    	<div class="row">
    		<div class="col-sm-12">
    			<div class="ibox float-e-margins">
    				<div class="ibox-title">
    					<h5>反馈信息</h5>
    					<div class="ibox-tools">
							<a class="collapse-link">
								<i class="fa fa-chevron-up"></i>
							</a>
						</div>
    				</div>
    				<div class="ibox-content">
    					<form:form name="fb_form_c" role="form" commandName="feedback" action="fdbk/saveFdbk.do" method="post">
    						<div style="display:none;"><form:hidden path="id"/><input type="text" id="Fld_SaveAsNewFdbk" name="SaveAsNewFdbk" /></div>
    						<!-- 编辑权限 -->
    						<shiro:hasPermission name="fdbk:edit">
								<div class="form-group">
								  <label for="exampleInputEmail1">项目名称</label>
								  <form:input path="PjName" class="form-control" id="Fld_PjName" placeholder="项目名称" />
								</div>
								<div class="form-group">
								  <label for="exampleInputPassword1">批次</label>
								  <form:input path="Batch" class="form-control" id="Fld_Batch" placeholder="批次" />
								</div>
								<div class="form-group">
								  <label for="exampleInputPassword1">角色名称</label>
								  <form:input path="RoleName" class="form-control" id="Fld_RoleName" placeholder="角色名称" />
								</div>
								<div class="form-group">
								  <label for="exampleInputPassword1">修改人员</label>
								  <form:input path="ModifyPerson" class="form-control" id="Fld_ModifyPerson" placeholder="修改人员" />
								</div>
								<div class="form-group">
								  <label for="exampleInputPassword1">反馈内容</label>
								  <form:textarea path="Feedback" id="Fld_Feedback" style="width:100%;height:150px;"></form:textarea>
								</div>
							</shiro:hasPermission>
							<!-- 只读权限 -->
							<shiro:lacksPermission name="fdbk:edit">
								<div class="form-group">
								  <label for="exampleInputEmail1">项目名称</label>
								  <label class="form-control">${feedback.pjName}</label>
								</div>
								<div class="form-group">
								  <label for="exampleInputPassword1">批次</label>
								  <label class="form-control">${feedback.batch}</label>
								</div>
								<div class="form-group">
								  <label for="exampleInputPassword1">角色名称</label>
								  <label class="form-control">${feedback.roleName}</label>
								</div>
								<!--  
								<div class="form-group">
								  <label for="exampleInputPassword1">修改人员</label>
								  <label class="form-control" id="labels_mp"></label>
								</div>
								<div class="form-group">
								  <label for="exampleInputPassword1">反馈内容</label>
								  <p>${feedback.feedback}</p>
								</div>
								-->
							</shiro:lacksPermission>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<shiro:hasPermission name="fdbk:edit">
									<button type="button" class="btn btn-primary" onclick="submitFB();">保存</button>
									<c:if test="${feedback.id ne 0 }">
										<button type="button" class="btn btn-info" onclick="submitFB(1);">另存为新反馈</button>
									</c:if>
								</shiro:hasPermission>
								<button type="button" class="btn btn-default" onclick="returnLst();">返回</button>
							</div>
    					</form:form>
    				</div>
    			</div>
    		</div>
    	</div>
    	
    	<c:if test="${not empty fbHist}">
    	<div class="row">
    		<div class="col-sm-12">
    			<div class="ibox float-e-margins">
    				<div class="ibox-title">
    					<h5>历史反馈</h5>
						<div class="ibox-tools">
							<a class="collapse-link">
								<i class="fa fa-chevron-up"></i>
							</a>
						</div>
					</div>
					
					<c:forEach items="${fbHist}" var="fbh">
					<div id="div_fb_${fbh.id}" class="ibox-content timeline">
						<div class="timeline-item">
                            <div class="row">
                                <div class="col-xs-3 date">
                                    <i class="fa fa-file-text"></i> ${fbh.lastUpdate}
                                    <shiro:hasPermission name="fdbk:delete">
                                    	<p><a href="#" class="btn btn-xs btn-danger" onclick="delFb('${fbh.id}');return false;"> 删除 </a></p>
                                    </shiro:hasPermission>
                                </div>
                                <div class="col-xs-10 content">
                                    <p class="m-b-xs"><strong>反馈信息</strong>
                                    </p>
                                    <p>${fbh.feedback}</p>
                                </div>
                            </div>
                        </div>
					</div>
					</c:forEach>
					
    			</div>
    		</div>
    	</div>
    	</c:if>
    </div>
	
	<div id="fancyboxBuffer" style="display:none">
		<a id="fancyboxBigPic" />
	</div>
	
	<script type="text/javascript" src="JS/tagsinput/jquery-2.2.4.min.js"></script>
	<script type="text/javascript" src="Bootstrap/js/bootstrap.min.js"></script>
	<!-- Tags Input -->
	<script type="text/javascript" src="JS/tagsinput/typeahead.jquery.min.js"></script>
	<script type="text/javascript" src="JS/tagsinput/bootstrap-tagsinput.min.js"></script>
	<script type="text/javascript" src="JS/tagsinput/bloodhound.min.js"></script>
	<!-- UEditor -->
	<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.parse.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
	<!-- Fancy box -->
    <script src="JS/fancybox/jquery.fancybox.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('#fancyboxBigPic').fancybox({
				width:1024,
				height:768
			});
			
			$('img').click(function(){
				$('#fancyboxBigPic').attr('title', this.title);
				$('#fancyboxBigPic').attr('href', this.src.replace(/c_/,""));
				$('#fancyboxBigPic').click();
			});
			
			$(".fa-chevron-up").click(function(){
				var obj = $(this).parents(".ibox").find(".ibox-content");
				if (obj.is(":visible")) obj.slideUp();
				else obj.slideDown();
			});
			
			//===Tags Input Start===
			if( $('#Fld_ModifyPerson').length > 0 ){
				var names = new Bloodhound({
					datumTokenizer: Bloodhound.tokenizers.obj.whitespace('name'),
				  	queryTokenizer: Bloodhound.tokenizers.whitespace,
				  	local:${nameLst}
				});
				names.initialize();
				$('#Fld_ModifyPerson').tagsinput({
					itemValue: 'name',
					typeaheadjs: {
						name: 'names',
						displayKey: 'name',
						valuekey: 'name',
						source: names.ttAdapter()
					}
				});
				$('.tt-menu').css("z-index","1000");
				var s_mp = "${feedback.modifyPerson}";
				if (s_mp!=""){
					var mp_ret = s_mp.split(",");
					for(var i=0;i<mp_ret.length;i++){
						$('#Fld_ModifyPerson').tagsinput('add',{"name":mp_ret[i]});
					}
				}
			}
			//===Tags Input END===
			if( $('#Fld_Feedback').length > 0 ){
				var ue = UE.getEditor('Fld_Feedback');
			}
			
			recordRdr();		//记录当前阅读者
		});
		
		function delFb(id){
			var divID = "#div_fb_"+id;
			$(divID).remove();
			var url = "<%=request.getContextPath()%>/fdbk/delFb.do";
			$.post(url,{id:id});
		}
		
		function recordRdr(){
			var html = "", label = "";
			var url = "<%=request.getContextPath()%>/fdbk/recordRdr.do";
			var id = "${feedback.id}";
			var flag = false;
			var s_mp = '${mpJSON}';
			var curUser = "${curUser}";
			if (s_mp!="") {
				$.each(JSON.parse(s_mp),function(i,obj){
					label = Number(obj.label)>=0?"label-success":"label-warning";
					if ( obj.name == curUser ){
						flag = true;
						label = "label-success";
					}
					html += "<p style=\"display:inline-block; margin:2px 2px 0 2px;\"><span class=\"label "+label+"\">"+obj.name+"</span></p>";
				});
				if( flag ) $.post(url,{id:id});
				//if( $('#labels_mp').length > 0 ) $('#labels_mp').append(html);
			}
		}
		
		function returnLst(){
			var url = "<%=request.getContextPath()%>/fdbk/<%=s.getAttribute("vwPage")%>";
			$("#J_iframe", window.parent.document).attr('src',url);
			return false;
		}
		
		function submitFB(para){
			if(para) $('#Fld_SaveAsNewFdbk').val(para);
			document.fb_form_c.submit();
		}
		
	</script>
  </body>
</html>
