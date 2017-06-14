    <style>
	.btn-file {  /*  上传按钮*/
	    position: relative;
	    overflow: hidden;
	}
	
	.btn-file input[type=file] {
	    position: absolute;
	    top: 0;
	    right: 0;
	    min-width: 100%;
	    min-height: 100%;
	    font-size: 100px;
	    text-align: right;
	    filter: alpha(opacity = 0);
	    opacity: 0;
	    outline: none;
	    background: white;
	    cursor: inherit;
	    display: block;
	}
	</style>
	
	<!--隐藏附件上传功能
	<div class="hr-line-dashed"></div>
	<div class="form-group">
		<form name="fb_form_u" role="form" action="uploadFBA.do" method="post">
			<label class="col-sm-1 control-label">附件</label>
			<shiro:hasPermission name="fdbk:edit">
			  <span class="btn btn-primary btn-file"> 选择文件
				<span class="glyphicon glyphicon-file" aria-hidden="true"></span>
				<input type="file" id="exampleInputFile" name="Fld_upload" value="" />
			  </span>
			</shiro:hasPermission>
		</form>
	</div>
	<div class="form-group">
		<div class="mail-attachment">
			<p><span><i class="fa fa-paperclip"></i> 附件 - </span></p>
			<div class="attachment">
				<div id="D_Attachs" class="clearfix"></div>
			</div>
		</div>
	</div>
	-->
	
	<script type="text/javascript" src="JS/ajaxfileupload.js"></script>
	
	<!-- 
	var json = '${FBA}';
	if ( '' != $.trim(json) ){
		var fba = JSON.parse(json);
		$(fba.rows).each(function(index, rData){
			showAttachs(rData);
		});
	}
	function showAttachs(data, is_new){
		var a_new = 0;
		if( "undefined" != typeof(is_new) ) a_new = 1;
		var id = "p_a_"+data.id;
		var href = "<%=request.getContextPath()%>"+data.webpath;
		var html ="<div class='file-box'><div class='file'><a href='"+href+"' target=_blank>";
		html += "<span class='corner'></span><div class='icon'><i class='fa fa-file'></i></div>";
		html += "<div id='"+id+"' a_wp='"+data.webpath+"' a_fn='"+data.filename+"' a_new='"+a_new+"' class='file-name'>"+data.filename+"</div>";
		html += "</a></div></div>";
		$("#D_Attachs").before(html);
	}
	
	function ajaxFileUpload(){
		var formId = 'jUploadForm' + 'exampleInputFile';  //file为input的id
		var obj = $('#'+formId);
		if ( obj.length > 0 ) obj.remove();
		$.ajaxFileUpload({
			url: '<%=request.getContextPath()%>/fdbk/uploadFBA.do', 					//用于文件上传的服务器端请求地址
			secureuri: false, 						//是否需要安全协议，一般设置为false
			fileElementId: 'exampleInputFile', 		//文件上传域的ID
			dataType: 'JSON',
			success: function (data, status){
				showAttachs(data, 1);
			},
			error: function (data, status, e){
				alert(e);
			}
		});
		return false;
	}
	
	function DelAttach(path){
		$('#p_a_'+path).remove();
	}
	
	var aObj = new Object();
	var a_wp = new Array();
	var a_fn = new Array();
	$('.file-name').each(function(index, pObj){
		if( "1" == $(pObj).attr("a_new") ){
			a_wp.push( $(pObj).attr("a_wp") );
			a_fn.push( $(pObj).attr("a_fn") );	
		}
	});
	aObj.wp = a_wp;
	aObj.fn = a_fn;
	$('#Fld_Attachs').val( JSON.stringify(aObj) );
	-->
	
	
	if ( 0 != fb_i) {	//附件操作
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("fba", (Object) request.getParameter("Fld_Attachs"));
			map.put("id", fb_i);
			map.put("username", username);
			map.put("ts", ts.toString());
			fdbkAttService.saveAttach(map);
		}