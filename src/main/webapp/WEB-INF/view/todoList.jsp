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
            <div class="ibox-title"><h5>待阅反馈</h5></div>
            <div class="ibox-content">
	        	<table id="todoTBL" data-icon-size="outline" data-pagination="true" data-search="true"></table>
	        </div>
        </div>
	</div>
	
	<script type="text/javascript" src="JS/tagsinput/jquery-2.2.4.min.js"></script>
	<script type="text/javascript" src="Bootstrap/js/bootstrap.min.js"></script>
	<!-- Bootstrap table -->
    <script src="JS/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="JS/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script type="text/javascript">
    var $table = $('#todoTBL');
    
    $(document).ready(function(){
	    $table.bootstrapTable({
	    	height: getHeight(),
	    	url: "<%=request.getContextPath()%>/fdbk/showTodoList.do",
		    columns: [
		    	{field: 'pjName',title: '项目名称'},
		    	{field: 'batch',title: '批次'},
		    	{field: 'roleName',title: '角色名称'},
		    	{field: 'createDate',title: '创建时间'},
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
    
    function openDoc(id){
		var url = "<%=request.getContextPath()%>/fdbk/0";
		if(id) url = "<%=request.getContextPath()%>/fdbk/"+id;
		$("#J_iframe", window.parent.document).attr('src',url);
       	return false;
	}
	</script>
</body>