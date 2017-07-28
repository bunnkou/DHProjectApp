<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<head>
	<base href="<%=basePath%>"></base>
	<%@ include file="subform/_css.jsp" %>
	<!-- <link href="JS/datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen"> -->
	<link href="JS/daterangepicker/daterangepicker.min.css" rel="stylesheet" media="screen">
	<link href="JS/select2-4.0.3/css/select2.min.css" rel="stylesheet">
	<style>
		#unselected_div a{ margin-right:5px; }
		.timecard-persons a{ margin:0 5px 5px 0; }
		.timecard-btn{margin:3px 5px 0 0;}
		.footer{
			background:none repeat scroll 0 0 white;;
			border-top:1px solid #e7eaec;
			padding:0 !important;
			margin:0;
			height:0px;
		}
	</style>
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
            <div class="ibox-title"><h5>工时情况</h5></div>
            <div class="ibox-content">
            	
            	<div>
            		<label>选择时间：</label>
            		<div id="div_DateRangePicker" style="margin-bottom:10px;"></div>
            	</div>
            	
           		<div style="
           			height:40px;
           			display:flex;
           			flex-flow: row wrap;
           			justify-content: flex-start;
           			align-content: center;
           			align-items: center;
           		">
           			<!-- <div style="display: inline-flex; margin-right:10px;">
           				
           			</div>
           			<div style="display:inline-flex; margin-right:10px;">
           				<input type="text" id="inputDatetime" name="inputDatetime" class="form_date form-control" readonly>
           			</div> -->
           			<button type="button" class="btn btn-outline btn-default timecard-btn" onclick="addRow();">增加</button>
           			<button type="button" class="btn btn-outline btn-danger timecard-btn" onclick="removeRow();">删除</button>
           			<button type="button" class="btn btn-outline btn-success timecard-btn" onclick="accept();">确认</button>
        		</div>
        		
        		<hr>
        		
        		<div class="project-list">
        			<h5>项目列表：</h5>
        			<table id="project-table" class="table table-hover"></table>
        		</div>
        		
        		<h5>未进项目人员：</h5>
        		<div id="unselected_div"></div>
        		  		
	        </div>	<!-- ibox-content -->
        </div>	<!-- ibox float-e-margins -->
	</div>	<!-- wrapper wrapper-content animated fadeInRight -->
	
	<input type="hidden" id="inputStartDate" value="" />
	<input type="hidden" id="inputEndDate" value="" />
	
	<%@ include file="subform/_js.jsp" %>
	<!-- <script type="text/javascript" src="JS/datetimepicker/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="JS/datetimepicker/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script> -->
	<script type="text/javascript" src="JS/moment.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="JS/daterangepicker/jquery.daterangepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="JS/select2-4.0.3/js/select2.min.js"></script>
	<script type="text/javascript" src="JS/timecard.js"></script>
	
	<script type="text/javascript">
		var pData = ${ persons };
		var sData = JSON.parse('${ sData }'),
			pjTable = $('#project-table');
				
		$(document).ready(function(){
			
			$('#div_DateRangePicker').dateRangePicker({
				format: 'YYYY-MM-DD',
				language: 'cn',
				inline: true,
				container: '#div_DateRangePicker',
				alwaysOpen: true
			}).bind('datepicker-change', function(event, obj){
				var date1 = moment(obj.date1).format('YYYY-MM-DD'),
					date2 = moment(obj.date2).format('YYYY-MM-DD');
				$('#inputStartDate').val(date1);
				$('#inputEndDate').val(date2);
				getProjectsByDate('reload');
			});
			
			var sDate = new Date(),
				eDate = new Date();
			eDate.setMonth(eDate.getMonth()+1);
			fmt_sDate = moment(sDate).format('YYYY-MM-DD');
			fmt_eDate = moment(eDate).format('YYYY-MM-DD');
			$('#div_DateRangePicker').data('dateRangePicker').setDateRange(fmt_sDate, fmt_eDate);
			$('#inputStartDate').val(fmt_sDate);
			$('#inputEndDate').val(fmt_eDate);
						
			pjTable.timecard({
				data:getProjectsByDate(),
				columns:[[
					{field:'id', hidden:true},
					{field:'date1', hidden:true},
					{field:'date2', hidden:true},
					{field:'title', title:'项目', width:'50%', editor:{type:'select', options:{data:sData, required:true}}},
					{field:'dateRange', title:'年份', width:'30%'},
					{field:'persons', title:'成员', width:'20%', editor:{type:'label'}}
				]],
				sourcePool: "unselected_div",
				multiLabel: true,
				success:function(o){
					o.OperatePerson(pData, "remove");
					changeLabelStatus(o);
				}
			}).bind('label-click', function(event, obj){
				changeLabelStatus();
			});
			
		});
		
		function changeLabelStatus(obj){
			var ret = null,
				tmpStr = "",
				tmpArr = [],
				pool = $('#unselected_div');
			if (obj) {
				ret = [];
				for (var i=0; i<obj.data.length; i++){
					var opt = {};
					opt.persons = obj.data[i].persons;
					ret.push(opt);
				}
			}else{
				ret = pjTable.timecard('getData', 'uncheck');
			}
			for (var i=0; i<ret.length; i++){
				if (tmpStr) tmpStr += ",";
				tmpStr += ret[i].persons;
			}
			tmpArr = tmpStr.split(",");
			ret = pool.find('a');
			for (var i=0; i<ret.length; i++){	//改变列表中人员状态
				if (tmpArr.indexOf($(ret[i]).text())!=-1){
					$(ret[i]).removeClass().addClass('btn btn-outline btn-danger btn-sm').appendTo(pool);
				}else{
					if ( $(ret[i]).hasClass('btn-default') ) continue;
					$(ret[i]).removeClass().addClass('btn btn-default btn-sm').prependTo(pool);
				}
			}
		}
		
		function getProjectsByDate(param){
			var date1 = $('#inputStartDate').val(),
				date2 = $('#inputEndDate').val(), 
				url = "timecard/show", json;
			$.ajax({
				url:url,
				type:"POST",
				data:{date1: date1, date2: date2},
				async:false,
				success:function(data){
					json = data;
					if(param=="reload") pjTable.timecard(param, data);
				},
				dataType:"json"
			});
			if(!param) return json;
		}
		
		function RemoveElementFromArray(subArr, tarArr){
			for(var i=0, index; i<subArr.length; i++){
				index = tarArr.indexOf(subArr[i]);
				if (index!==-1) tarArr.splice(index,1);
			}
		}
		
		function addRow(){
			pjTable.timecard('addRow');
		}
		
		function removeRow(){
			var c = confirm("确认删除此项目");
			if (!c) return;
			var rowData = pjTable.timecard('getRowData');			
			pjTable.timecard('removeRow');
			$.post("timecard/delete", {id:rowData.id});
			changeLabelStatus();
		}
		
		function accept(){
			var date1 = $('#inputStartDate').val(),
				date2 = $('#inputEndDate').val();
			
			if(date2=="") {
				alert("请先选择日期");
				return false;
			}
			
			var ret = pjTable.timecard('getData'),
				url = "timecard/store",
				json = tmp = "";
			
			for(var i=0; i<ret.length; i++){
				if (tmp!=="") tmp += ",";
				tmp += '{';
				tmp += '"id":"'+ret[i].id+'",';
				tmp += '"date1":"'+ret[i].date1+'",';
				tmp += '"date2":"'+ret[i].date2+'",';
				tmp += '"title":"'+ret[i].title+'",';
				tmp += '"persons":"'+ret[i].persons+'"';
				tmp += '}';
			}	
				
			json = '"date1":"'+date1+'",';
			json += '"date2":"'+date2+'",';
			json += '"rows":['+tmp+']';
			json = '{'+json+'}';
			
			$.ajax({
				url: url,
				type: 'POST',
				contentType: "application/json;charset=UTF-8",
				traditional: true,
				data: JSON.stringify(json),				
				success: function(data){
					pjTable.timecard('reload', data);
				}
			});
			
		}
	</script>
</body>