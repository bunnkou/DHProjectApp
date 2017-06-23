<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<head>
	<base href="<%=basePath%>"></base>
	<link href="bs-plugins/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="bs-plugins/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="JS/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="bs-plugins/css/style.css?v=4.1.0" rel="stylesheet">
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
            <div class="ibox-title"><h5>权限管理</h5></div>
            <div class="ibox-content">
            	<div class="btn-group hidden-xs" id="Toolbar" role="group">
                    <button type="button" class="btn btn-outline btn-default" onclick="openDoc();">
                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                    </button>
                    <button id="remove" type="button" class="btn btn-outline btn-default" disabled>
                        <i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
                    </button>
                </div>
	        	<table id="todoTBL" data-icon-size="outline" data-pagination="true" data-search="true" style="word-break:break-all; word-wrap:break-all;"></table>
	        </div>
        </div>
	</div>
	
	<script type="text/javascript" src="JS/tagsinput/jquery-2.2.4.min.js"></script>
	<script type="text/javascript" src="Bootstrap/js/bootstrap.min.js"></script>
	<!-- Bootstrap table -->
    <script src="JS/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="JS/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script type="text/javascript">
    var $table = $('#todoTBL'),
    	$remove = $('#remove'),
    	selections = [];
    
    $(document).ready(function(){
	    $table.bootstrapTable({
	    	height: getHeight(),
	    	toolbar: '#Toolbar',
	    	url: "<%=request.getContextPath()%>/access/getLst.do",
		    columns: [
		    	/* {field: 'state', checkbox: true, align: 'center', valign: 'middle'}, */
		    	{field: 'userName',title: '姓名'},
		    	{field: 'roleName',title: '权限'},
		    	{field: 'id',title: '操作',align: 'center',events: operateEvents,formatter: operateFormatter}
		    ],
		    search: true
		});
	});
	
	function operateFormatter(value, row, index) {
        return [
            '<a class="like" href="javascript:void(0)" title="Like">',
            '<i class="glyphicon glyphicon-zoom-in"></i>',
            '</a>  '
        ].join('');
    }
    
    window.operateEvents = {
        'click .like': function (e, value, row, index) {
        	openDoc(row.id);
        }
    };
    
    $(window).resize(function () {
        $table.bootstrapTable('resetView', {
            height: getHeight()
        });
    });
   	
	function getHeight() {
        return $(window).height() - $('.ibox-title').outerHeight(true);
    }
    
    $table.on('check.bs.table uncheck.bs.table ' +
	        'check-all.bs.table uncheck-all.bs.table', function () {
	    $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);
	    selections = getIdSelections();
	});
    
    $remove.click(function () {
    	 var ids = getIdSelections();
         $table.bootstrapTable('remove', { field: 'id', values: ids });
         var url = "<%=request.getContextPath()%>/admin/delGroups.do";
         $.post(url,{ids:ids.join(",")});
         $remove.prop('disabled', true);
    });
    
    function getIdSelections() {
        return $.map($table.bootstrapTable('getSelections'), function (row) {
            return row.id;
        });
    }
    
    function openDoc(id){
		var url = "<%=request.getContextPath()%>/access/0";
		if(id) url = "<%=request.getContextPath()%>/access/" + id;
		$("#J_iframe", window.parent.document).attr('src',url);
       	return false;
	}
	</script>
</body>