<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>采购订单</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="../layui/css/layui.css" >
  <script type="text/javascript" src="../layui/layui.js"></script>
</head>
<body>
 <div style="display: none;" id="purchaseOrderDetails">
 	<table class="layui-hide" id="detail" lay-filter="detail"></table>
 </div>

<table class="layui-hida" id="test" lay-filter="test"></table>


<iframe id="updatePurchase" src="updatePurchase.jsp" style="display: none;" frameborder="0" width="1000px;" height="500px"></iframe>
 
 
 
 <div id="toolbarDemo" style="display: none;">
	<div class="layui-inline">
		<select name="checkStatus" lay-verify="" lay-search="" id="checkStatus">
				<option value=""></option>
				<option value="1">已批准</option>
				<option value="2">已通过</option>
				<option value="3">未审核</option>
				<option value="4">未批准</option>
		</select>
	</div>
		<button class="layui-btn layui-btn-sm" lay-event="search"><i class="layui-icon layui-icon-search"></i>搜索</button>	
</div>     

<script id="barDemo" type="text/html">
  <a class="layui-btn layui-btn-xs" lay-event="edit">查看详情</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>             
 
<script>
layui.use(['table','layer','form','jquery','laydate'], function(){
	  var table = layui.table;
	  var $=layui.jquery;
	  var layer=layui.layer;
	  var form=layui.form;
	  var laydate = layui.laydate;
  
  table.render({
    elem: '#test'
    ,url:'${APP_PATH}/purchase/selectAll.do'
    ,toolbar: '#toolbarDemo'
    ,title: '用户数据表'
    ,cols: [[
        {field:'bpoId', title:'订单编号', fixed: 'left', unresize: true, sort: true,width:150}
        ,{field:'bpoTime', title:'下单时间',width:120}
        ,{field:'bpurchaseName', title:'采购员',width:80}
        ,{field:'braId', title:'分店编号',width:100}
        ,{field:'bpoTotalPrices', title:'采购订单总金额',width:130}
        ,{field:'checkStatus', title:'审核状态',width:90}
        ,{field:'checkName', title:'审核人',width:80}
        ,{field:'checkTime', title:'审核日期 ',width:120}
        ,{field:'payStatus', title:'付款状态',width:90}
        ,{field:'storeStatus', title:'入库状态',width:90}
        ,{field:'qualityStatus', title:'质检状态',width:90}
        ,{fixed: 'right', title:'操作', toolbar: '#barDemo',width:140}
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
    //var count=0;
    switch(obj.event){
		case 'search':
			var   searchName=$("#checkStatus").find("option:selected").text();
				table.reload('test', {
					url: '${APP_PATH}/purchase/selectAll.do'
				  	,where: { //设定异步数据接口的额外参数，任意设
				  		searchName: searchName
				  	}
					,method:'post'//不加这个就为get方式提交，会出现乱码
				}); //只重载数据
			break;
    };
  });
  
/*   table.on('edit(test)', function(obj){
	  console.log(obj.value); 
	  alert(obj.value);
  });
  table.on('rowDouble(test)', function(obj){
	    var layer = layui.layer;
	    layer.open({
	    	type: 1, 
	    	title:'详细信息',
	    	area: ['1000px', '500px'],
	    	offset: ['00px', '50px'],
	    	content: $('#purchaseOrderDetails') //这里content是一个普通的String
	    });

	}); */

  //监听行工具事件
  table.on('tool(test)', function(obj){
      var da = obj.data;
      if(obj.event === 'del'){
          layer.confirm('真的删除行么', function(index){
              $.ajax({
              	url:"${APP_PATH}/purchase/deleteOrder.do"
      				, data: {id: da.bpoId}
      				, type: "post"
      				//, dataType: "json"
      				, error: function(xhr, err){
      					layer.alert(err);
      				}
      				, success: function (back) {
      					if (back==1){
      						table.reload('test', {
      							url:'${APP_PATH}/purchase/selectAll.do'
      						});
      				        layer.close(index);
      					}
      				}
              }); 	    
          });
    }else if(obj.event==='edit'){
    	var id=da.bpoId;
	    layer.open({
	    	type: 1, 
	    	title:'详细信息',
	 		//shadeClose : true,
	  		area : [ '700px', '460px' ],//大小
	    	//offset: ['00px', '50px'],
	    	content: $('#purchaseOrderDetails') //这里content是一个普通的String
	    /* 	,end : function() {
					$('[lay-id="detail"]').css("display", "none");
			  } */
	    });
	    
	    table.render({
	        elem: '#detail'
	        ,url:'${APP_PATH}/purchase/selectOrderDetail.do?id='+id
	        ,totalRow : true
	        ,cols: [[ //标题栏
	            {field:'proId', title:'商品id', fixed: 'left', unresize: true}
	            ,{field:'proName', title:'商品名称'}
	            ,{field:'proPrice', title:'商品价格'}
	            ,{field:'bpodQuantity', title:'商品数量'}
	            ,{field:'bpodSubtotal', title:'商品小计',totalRow : true}
	        ]]
	      });
    }
  });
	  
	});




</script>




<script>
layui.use('laydate', function(){
  var laydate = layui.laydate;
  
  //常规用法
  laydate.render({
    elem: '#test1'
  });
  
  //国际版
  laydate.render({
    elem: '#test1-1'
    ,lang: 'en'
  });
  
  //年选择器
  laydate.render({
    elem: '#test2'
    ,type: 'year'
  });
  
  //年月选择器
  laydate.render({
    elem: '#test3'
    ,type: 'month'
  });
  
  //时间选择器
  laydate.render({
    elem: '#test4'
    ,type: 'time'
  });
  
  //日期时间选择器
  laydate.render({
    elem: '#test5'
    ,type: 'datetime'
  });
  
  //日期范围
  laydate.render({
    elem: '#test6'
    ,range: true
  });
  
  //年范围
  laydate.render({
    elem: '#test7'
    ,type: 'year'
    ,range: true
  });
  
  //年月范围
  laydate.render({
    elem: '#test8'
    ,type: 'month'
    ,range: true
  });
  
  //时间范围
  laydate.render({
    elem: '#test9'
    ,type: 'time'
    ,range: true
  });
  
  //日期时间范围
  laydate.render({
    elem: '#test10'
    ,type: 'datetime'
    ,range: true
  });
  
  //自定义格式
  laydate.render({
    elem: '#test11'
    ,format: 'yyyy年MM月dd日'
  });
  laydate.render({
    elem: '#test12'
    ,format: 'dd/MM/yyyy'
  });
  laydate.render({
    elem: '#test13'
    ,format: 'yyyyMMdd'
  });
  laydate.render({
    elem: '#test14'
    ,type: 'time'
    ,format: 'H点m分'
  });
  laydate.render({
    elem: '#test15'
    ,type: 'month'
    ,range: '~'
    ,format: 'yyyy-MM'
  });
  laydate.render({
    elem: '#test16'
    ,type: 'datetime'
    ,range: '到'
    ,format: 'yyyy年M月d日H时m分s秒'
  });
  
  //开启公历节日
  laydate.render({
    elem: '#test17'
    ,calendar: true
  });
  
  //自定义重要日
  laydate.render({
    elem: '#test18'
    ,mark: {
      '0-10-14': '生日'
      ,'0-12-31': '跨年' //每年的日期
      ,'0-0-10': '工资' //每月某天
      ,'0-0-15': '月中'
      ,'2017-8-15': '' //如果为空字符，则默认显示数字+徽章
      ,'2099-10-14': '呵呵'
    }
    ,done: function(value, date){
      if(date.year === 2017 && date.month === 8 && date.date === 15){ //点击2017年8月15日，弹出提示语
        layer.msg('这一天是：中国人民抗日战争胜利72周年');
      }
    }
  });
  
  //限定可选日期
  var ins22 = laydate.render({
    elem: '#test-limit1'
    ,min: '2016-10-14'
    ,max: '2080-10-14'
    ,ready: function(){
      ins22.hint('日期可选值设定在 <br> 2016-10-14 到 2080-10-14');
    }
  });
  
  //前后若干天可选，这里以7天为例
  laydate.render({
    elem: '#test-limit2'
    ,min: -7
    ,max: 7
  });
  
  //限定可选时间
  laydate.render({
    elem: '#test-limit3'
    ,type: 'time'
    ,min: '09:30:00'
    ,max: '17:30:00'
    ,btns: ['clear', 'confirm']
  });
  
  //同时绑定多个
  lay('.test-item').each(function(){
    laydate.render({
      elem: this
      ,trigger: 'click'
    });
  });
  
  //初始赋值
  laydate.render({
    elem: '#test19'
    ,value: '1989-10-14'
    ,isInitValue: true
  });
  
  //选中后的回调
  laydate.render({
    elem: '#test20'
    ,done: function(value, date){
      layer.alert('你选择的日期是：' + value + '<br>获得的对象是' + JSON.stringify(date));
    }
  });
  
  //日期切换的回调
  laydate.render({
    elem: '#test21'
    ,change: function(value, date){
      layer.msg('你选择的日期是：' + value + '<br><br>获得的对象是' + JSON.stringify(date));
    }
  });
  //不出现底部栏
  laydate.render({
    elem: '#test22'
    ,showBottom: false
  });
  
  //只出现确定按钮
  laydate.render({
    elem: '#test23'
    ,btns: ['confirm']
  });
  
  //自定义事件
  laydate.render({
    elem: '#test24'
    ,trigger: 'mousedown'
  });
  
  //点我触发
  laydate.render({
    elem: '#test25'
    ,eventElem: '#test25-1'
    ,trigger: 'click'
  });
  
  //双击我触发
  lay('#test26-1').on('dblclick', function(){
    laydate.render({
      elem: '#test26'
      ,show: true
      ,closeStop: '#test26-1'
    });
  });
  
  //日期只读
  laydate.render({
    elem: '#test27'
    ,trigger: 'click'
  });
  
  //非input元素
  laydate.render({
    elem: '#test28'
  });
  
  //墨绿主题
  laydate.render({
    elem: '#test29'
    ,theme: 'molv'
  });
  
  //自定义颜色
  laydate.render({
    elem: '#test30'
    ,theme: '#393D49'
  });
  
  //格子主题
  laydate.render({
    elem: '#test31'
    ,theme: 'grid'
  });
  
  
  //直接嵌套显示
  laydate.render({
    elem: '#test-n1'
    ,position: 'static'
  });
  laydate.render({
    elem: '#test-n2'
    ,position: 'static'
    ,lang: 'en'
  });
  laydate.render({
    elem: '#test-n3'
    ,type: 'month'
    ,position: 'static'
  });
  laydate.render({
    elem: '#test-n4'
    ,type: 'time'
    ,position: 'static'
  });
});
</script>

</body>
</html>