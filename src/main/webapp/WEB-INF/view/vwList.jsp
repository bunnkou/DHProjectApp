<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<head>
	<base href="<%=basePath%>"></base>
	<link rel="stylesheet" type="text/css" href="easyui/themes/bootstrap/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link href="bs-plugins/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="bs-plugins/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="bs-plugins/css/style.css?v=4.1.0" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                	<div class="ibox-title">
                        <h5>基本 <small>分类，查找</small></h5>
                    </div>
                    <div class="ibox-content">
	                	<table id="TBL_vwFbList"></table>
						<div style="display: none;">
							<div id="tb" style="height:auto">
								<shiro:hasPermission name="fdbk:create"><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="openDoc()">增加</a></shiro:hasPermission>
								<shiro:hasPermission name="fdbk:delete"><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a></shiro:hasPermission>
								<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" onclick="reloadTbl()">刷新</a>
								<input id="Fld_DataGridSearch" style="height:20px; border:1px solid #ccc; width:150px;">
								<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search', plain:true" onclick="doSearch();return false;">查询</a>
							</div>
						</div>
                	</div>
              	</div>
            </div>
        </div>
    </div>
	
	<script type="text/javascript" src="JS/jquery-1.11.3/jquery.min.js"></script>
	<script type="text/javascript" src="Bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/datagrid-groupview.js"></script>
	
	<script type="text/javascript">
		var $table = $('#TBL_vwFbList');
		var page = 1;
		var pageSize = 30;
		
		$().ready(function(){
			vwFbList();
		});
		
		function openDoc(id){
			var url = "<%=request.getContextPath()%>/fdbk/0";
			if(id) url = "<%=request.getContextPath()%>/fdbk/"+id;
			$("#J_iframe", window.parent.document).attr('src',url);
        	return false;
		}
		
		function reloadTbl(){
			$table.datagrid("reload");
		}
		
		function removeit(){
			var row = $table.datagrid("getSelected");
			if (row==null){
				alert("请选中行后，再点击删除按钮");
				return false;
			}
			var url = "<%=request.getContextPath()%>/fdbk/delFb.do";
			$.post(url,{pjName:row.pjName,roleName:row.roleName},function(){
				$table.datagrid('reload');
			});
		}
		
		function doSearch(){
			var sQuery = $('#Fld_DataGridSearch').val();
			//	if(sQuery.trim()==""){
			//		alert("请输入需要查询的关键字");
			//		return false;
			//	}
			var queryParams = $table.datagrid("options").queryParams;
         	queryParams.sQuery = sQuery;
         	$table.datagrid("options").queryParams=queryParams;
         	$table.datagrid("reload");
		}
		
		function vwFbList(){
			$table.datagrid({
				height:700,
				type: "POST",
				url: "<%=request.getContextPath()%>/fdbk/showFbList.do",
				contentType: "application/json",
				dataType: "json",
				singleSelect:true,
				toolbar: '#tb',
				pagination:true,
				pageSize: pageSize,
				pageList: [15,30,50],
				loadMsg: "数据加载中，请稍后...",
				fitCloumns: true,
				columns:[[
			 		{field:'pjName',title:'项目名称',width:"250"},
			 		{field:'roleName',title:'角色名称',width:"150",align:'center'},
					{field:'batch',title:'批次',width:"150",align:'center'},	 		
					{field:'createDate',title:'创建时间',width:"160",align:'center'},
					{field:'id',width:"150",align:'center',
						formatter: function(value, row, index){
							return '<a href="javascript:void(0)" class="sz_button" onclick="openDoc(\''+row.id+'\');return false;"></a>'
						}
					}
			 	]],
			 	groupField:'pjName',
			 	view: groupview,
					groupFormatter:function(value, rows){
					return value + ' - ' + rows.length + ' Item(s)';
        		},
				onLoadSuccess:function(data){
					$(".sz_button").linkbutton({	//添加按钮样式 
			            text:'查看',
			            plain:true,
			            iconCls:'icon-search'
			        });
					
					//$('#TBL_vwFbList').datagrid('collapseGroup');	//默认折叠
					
					if(data.rows.length==0){		//无数据提示
						var body = $(this).data().datagrid.dc.body2;
						body.find('table tbody').append('<tr><td width="' + body.width() + '" style="height: 25px; text-align: center;">没有相关记录</td></tr>');
					}
				}
			});
		}
	</script>
</body>