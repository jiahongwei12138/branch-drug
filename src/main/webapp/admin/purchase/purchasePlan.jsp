<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>采购计划列表</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="../layui/css/layui.css" >
  <script type="text/javascript" src="../layui/layui.js"></script>          
</head>
<body>
<table class="layui-table" id="test" lay-filter="test"></table>

	<!-- 值得注意的一点，所有LayUI风格的控件，都有lay-filter属性 -->

<iframe id="addPurchasePlan" src="addPurchasePlan.jsp" style="display: none;" frameborder="0" width="1000px;" height="500px" ></iframe>


<script id="toolbarDemo" type="text/html">
	<div class="layui-inline">
		<button class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon layui-icon-add-1"></i>新增</button>
		<input type="text" placeholder="请输入商品名称" class="layui-text"  id="search" style="width:150px;height:30px;">
		<button class="layui-btn layui-btn-sm" lay-event="search"><i class="layui-icon layui-icon-search"></i>搜索</button>
	<div>	
</script>


<script>
layui.use(['table','layer','form','jquery'], function(){
  var table = layui.table;
  var $=layui.jquery;
  var layer=layui.layer;
  var form=layui.form;
  table.render({
    elem: '#test'
    ,url:'${APP_PATH}/purchase/getMainProduct.do'
    ,toolbar: '#toolbarDemo'
    ,limit:5   //设置每页显示的条数 默认是10
	,limits:[5,10,15,20,25,30] 
    ,title: '采购商品表'
    	/* ,parseData:function(res){
        	console.log(res);
        	return{
        		code: 0, //解析接口状态
        		msg:"", //解析提示文本
        		count: 1000, //解析数据长度
        		data: res //解析数据列表
        	}
        } */
    ,cols: [[
      {type: 'checkbox', fixed: 'left'}
      ,{field:'proId', title:'商品id', fixed: 'left', unresize: true, sort: true,width:'20%'}
      ,{field:'proName', title:'商品名称'}
      ,{field:'enlishName', title:'英文名称'}
      ,{field:'proCure', title:'作用'}
      ,{field:'hqtName', title:'出厂商'}
      ,{field:'retailPrice', title:'商品价格'}
      ,{field:'bpodQuantity', title:'商品数量', edit: 'text'}
    ]]
    ,page: true
  });
  
  //头工具栏事件
  table.on('toolbar(test)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id);
    var count=0;
    switch(obj.event){
    case 'add':
    	  var layer = layui.layer;
    	  var json = checkStatus.data;//获取所有选中行的数据形成一个对象数组	
	     /*  var checkStatus = table.checkStatus('userTable'); //idTest 即为基础参数 id 对应的值
	      console.log(checkStatus.data) //获取选中行的数据
	      console.log(checkStatus.data.length) //获取选中行数量，可作为是否有选中行的条件 */
	      if(checkStatus.data.length>0){
	    	    for(var i=0;i<checkStatus.data.length;i++){
	    	    	if(checkStatus.data[i].bpodQuantity>0){
	    	    			count++;
	    	    	}
	    	   	}
	      		if(count==checkStatus.data.length){
	    	    	    $.ajax({
	    			    	  type:"post",
	    			    	  url:"${APP_PATH}/purchase/insertDetails.do", 
	    			    	  //traditional:true, json数组传输到后台所需的条件
	    			   /*  	  data:{
	    			    		 "json":json 
	    			    	  },  */
	    			    	  contentType : 'application/json',
	    			    	  data: JSON.stringify(json),
	    			    	  success:function(result){
	    				    		if(result==false){
	    								layer.msg("修改失败", {time:3000, icon:5, shift:6});				
	    							}else{
	    								layer.msg("修改成功", {time:3000, icon:1, shift:3});
	    								layer.open({
	    			        		  	    	type: 1, 
	    			        		  	    	title:'详细信息',
	    			        		  	    	offset: ['0px', '50px'],
	    			        		  	    	area: ['auto', 'auto'],
	    			        		  	    	content: $('#addPurchasePlan') //这里content是一个普通的String
	    			        		  	    });
	    							}
	    			    	  }	    	  
	    			      });
	    	    	}else{
	    	    		layer.msg("请保证要采购的商品数量大于0",{  icon: 2,
	  	    			  time: 1000});
	    	    	}
	    	}else{
	    		layer.msg("没有选择采购的商品,请选择至少一条采购的商品",{  icon: 2,
	    			  time: 1000});
	    	} 	    
    	  break;
		case 'search':
			var searchName=$("#search").val();
				table.reload('test', {
					url: '${APP_PATH}/purchase/queryMainGoods.do'
				  	,where: { //设定异步数据接口的额外参数，任意设
				    	"proName": searchName
				  	}
				}); //只重载数据
			break;
    };
  });
});
</script>
</body>
</html>