<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>退货审核</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="../layui/css/layui.css" >
  <script type="text/javascript" src="../layui/layui.js" charset="utf-8"></script> 
</head>
<body>
 <div id="returnAudit" style="display: none;">
 	<table>
 		<tr>
 			<td>
 			  	<label class="layui-form-label" style="font-size:13px;">审核时间</label>
	   			<div class="layui-input-block">
	      			<input name="checkTime" class="layui-input" id="date1" type="text" placeholder="yyyy-MM-dd" autocomplete="off" lay-verify="date" 
	      			onkeypress="javascript:if(event.keyCode == 32)event.returnValue = false;"/>
	    		</div>
    		</td>
    		<td>
	  			<label class="layui-form-label" style="font-size:13px;">审核员</label>
	   			<%-- <div class="layui-input-block">
	      			<input name="checkName" class="layui-input" type="text" autocomplete="off" lay-verify="title" value="${name }" readonly="readonly">
	    		</div> --%>	    	
			</td>
			<td>
				<div class="layui-input-inline">
					<select name="name" lay-verify="" lay-search="" id="name">
						<option value="">制定人</option>
						<option value="1">张三</option>
						<option value="2">李四</option>
						<option value="3">王五</option>
					</select>  
				</div>
			</td>
 		</tr>
 		<tr>
 			<td> <label class="layui-form-label"></label></td>
 			<td> <label class="layui-form-label"></label></td>
 			<td> <label class="layui-form-label"></label></td>
 		</tr>
 		<tr>
 			<td>
	 			<label class="layui-form-label"></label>
	 			<div class="layui-input-block" >
	 				<button name="agree" class="layui-btn" type="button" id="agreeCheck" value="批准">审核批准</button>
	 			</div>
 			</td>
 			<td><label class="layui-form-label"></label></td>
 			<td>
 				<button name="agree"  class="layui-btn" type="button" id="failCheck" value="未批准">审核不批准</button>
 			</td>
 		</tr> 
 	</table>
 	
   
 </div>
 
<table class="layui-hida" id="test" lay-filter="test"></table>
 
<script id="barDemo" type="text/html">
  <a class="layui-btn layui-btn-xs" lay-event="edit">审核</a>
</script>
              
<script id="toolbarDemo" type="text/html">
	<div class="layui-inline">
		<input type="text" placeholder="请输入审核状态" class="layui-text"  id="search" style="width:150px;height:30px;">
		<button class="layui-btn layui-btn-sm" lay-event="search"><i class="layui-icon layui-icon-search"></i>搜索</button>
	<div>	
</script>         

 
<script>
layui.use(['table','layer','form','jquery','laydate'], function(){
	  var table = layui.table;
	  var $=layui.jquery;
	  var layer=layui.layer;
	  var form=layui.form;
	  var laydate = layui.laydate;
	  var tanIndex;
	  var updateId;
	  
	  table.render({
		  elem: '#test'
		  ,url:'${APP_PATH}/purchase/selectNotCheckReturn.do'
		  ,toolbar: '#toolbarDemo'
		  ,title: '退货数据表'
		  ,cols: [[
		      {field:'bprId', title:'采购退货编号', fixed: 'left', unresize: true, sort: true,width:140}
		      ,{field:'bprTime', title:'退单时间',width:120}
		      ,{field:'returnName', title:'退货员',width:80}
		      ,{field:'braId', title:'分店编号',width:100}
		      ,{field:'bprMoney', title:'退货金额',width:130}
		      ,{field:'checkStatus', title:'审核状态',width:90}
		      ,{field:'checkName', title:'审核人',width:80}
		      ,{field:'checkTime', title:'审核日期 ',width:120}
		      ,{field:'payStatus', title:'付款状态',width:90}
		      ,{field:'bprReason', title:'退货原因',width:100}
		      ,{fixed: 'right', title:'操作', toolbar: '#barDemo',width:60}
		    ]],
		    initSort: {
			    field: 'bpoId' //排序字段，对应 cols 设定的各字段名
			    ,type: 'desc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
			  }
		  ,page: true
		});
	
//头工具栏事件
  table.on('toolbar(test)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id);
    switch(obj.event){
		case 'search':
			var searchName=$("#search").val();
				table.reload('test', {
					url: '${APP_PATH}/purchase/selectNotCheckReturn.do'
				  	,where: { //设定异步数据接口的额外参数，任意设
				  		searchName: searchName
				  	}
					,method:'post'//不加这个就为get方式提交，会出现乱码
				}); //只重载数据
			break;
    };
  });
   
  
  //监听行工具事件
  table.on('tool(test)', function(obj){
  var da = obj.data; //获得当前行数据
  var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
  var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
  updateId=da.bprId;
   if(obj.event === 'edit'){
	   tanIndex=layer.open({
	    	type: 1, 
	    	area: ['500px', '150px'],
	    	content: $('#returnAudit') //这里content是一个普通的String
	    });
	    //$("#cause").prop("value","");
    }
  });
  
  
  
  
  $("button[name='agree']").click(function(){
	  var  val= $(this).attr("value");
	  var checkStatus;
	  if(val=='批准'){
		  checkStatus='已批准';
	  }else{
		  checkStatus='未批准';
	  }
	  var checkName=$('#name').find("option:selected").text();
	  var time=$('#date1').val();
	  if(time!=''){
		  $.ajax({
			  url:"${APP_PATH}/purchase/updateReturn.do",
			  type:'post',
		      data:{
		    	  checkTime:time,
		    	  checkName:checkName,
		    	  checkStatus:checkStatus,
		    	  bprId:updateId
		      },
		      error: function(xhr, err){
						layer.alert(err);
					}, 
			  success: function (back) {
					if (back==1){
				    	table.reload('test', {
							method:'post',
							url:'${APP_PATH}/purchase/selectNotCheckReturn.do'
						});	
						layer.close(tanIndex);
					}else{
						layer.msg("审核失败，请重新审核",{  icon: 2,
			    			  time: 1000});
					}
				}
		    
		  });
	  }else{
			layer.msg("不能提交空值，请输入审核时间",{  icon: 2,
    			  time: 1000});
	  }	 
	  
  });
	 
  	
	  
			 /*   $('#failCheck').click(function(){
		  alert($("input[name='checkTime']").val());
		  alert($("button[name='agree']").val());
		  var checkStatus='未批准';
		  var checkName=$('#name').find("option:selected").text();
		  var time=$('#date1').val();
		  if(time!=''){
			  $.ajax({
				  url:"${APP_PATH}/purchase/updateOrder.do",
				  type:'post',
			      data:{
			    	  checkTime:time,
			    	  checkName:checkName,
			    	  checkStatus:checkStatus,
			    	  bpoId:updateId
			      },
			      error: function(xhr, err){
							layer.alert(err);
						}, 
				  success: function (back) {
						if (back==1){
							table.reload('test', {
								url:'${APP_PATH}/purchase/selectNotCheckOrder.do'
							});
					        layer.close(tanIndex);
						}else{
							layer.msg("审核失败，请重新审核",{  icon: 2,
				    			  time: 1000});
						}
					}
			    
			  });
		  }else{
				layer.msg("不能提交空值，请输入审核时间",{  icon: 2,
	    			  time: 1000});
		  }	 
	  }); */
	  
	  
	  
	  
  //日期
  laydate.render({
    elem: '#date1'
  });
});

</script>

</body>
</html>