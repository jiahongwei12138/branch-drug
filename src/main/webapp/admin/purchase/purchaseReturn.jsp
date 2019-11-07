<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>采购退货</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="../layui/css/layui.css" >
  <script type="text/javascript" src="../layui/layui.js"></script> 
</head>
<body>
 <div style="display: none;" id="purchaseReturnDetails">
 	<table class="layui-hide" id="detail" lay-filter="detail"></table>
 </div>
 
<div id="mydiv" style="display: none;">
 	<form class="layui-form" lay-filter="dataform" id="dataform" method="post">
		<div class="layui-form-item">  
  			<label class="layui-form-label" style="font-size:13px;">采购员</label>
   			<div class="layui-input-block">
      			<input name="returnName" class="layui-input" type="text" autocomplete="off" lay-verify="title" value="荒" readonly="readonly">
    		</div>
    	</div>  
		<div class="layui-form-item">  
  			<label class="layui-form-label" style="font-size:13px;">分店编号</label>
   			<div class="layui-input-block">
     				<input name="braId" class="layui-input" type="text" autocomplete="off" lay-verify="title" value="10001" readonly="readonly">
   			</div>
    	</div>   	
 		<div class="layui-form-item">  
	 		<label class="layui-form-label" style="font-size:13px;">申请部门</label>
	   		<div class="layui-input-block">
    			<select name="department" lay-filter="aihao">       			
	      			<option value="">采购部</option>
	      			<option value="0"></option>
    			</select>
	    	</div>
    	</div>
 		<div class="layui-form-item">  
			<label class="layui-form-label" style="font-size:13px;">退单时间</label>
  		    <div class="layui-input-block">
    			<input name="bprTime" class="layui-input" id="date1" type="text" placeholder="yyyy-MM-dd" autocomplete="off" lay-verify="date"/>
   		    </div>
    	</div>     	   	
	    <div class="layui-form-item layui-form-text">
  			<label class="layui-form-label" style="font-size:13px;">退货原因</label>
   			<div class="layui-input-block">
     				<input name="bprReason" class="layui-input" type="text" autocomplete="off" lay-verify="title" value="不合格产品" readonly="readonly">
   			</div>
	    </div>  			  	  
	    <div class="layui-form-item">
		    <div class="layui-input-block">
	        	<button class="layui-btn" lay-filter="dosubmit" lay-submit="">立即提交</button>
	        	<button class="layui-btn layui-btn-primary" type="reset">重置</button>
		    </div>
	    </div>
	</form>
</div>
  
 
 
<table class="layui-hida" id="test" lay-filter="test"></table>


<iframe id="updatepurchaseReturn" src="admin/purchase/updatepurchaseReturn.jsp" style="display: none;" frameborder="0" width="1000px;" height="500px"></iframe>
 
 <script id="toolbarDemo" type="text/html">
    <button class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon layui-icon-add-1"></i>新增</button>
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
</script>
  
<script id="barDemo" type="text/html">
  <a class="layui-btn layui-btn-xs" lay-event="edit">查看详情</a>
</script> 
              
  
<script>
layui.use(['table','layer','form','jquery','laydate'], function(){
	  var table = layui.table;
	  var $=layui.jquery;
	  var layer=layui.layer;
	  var form=layui.form;
	  var laydate = layui.laydate;
	  var url;
	  var tanIndex;
	  var tableIns;
	  
tableIns=table.render({
		  elem: '#test'
		  ,url:'${APP_PATH}/purchase/selectReturnAll.do'
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
		      ,{fixed: 'right', title:'操作', toolbar: '#barDemo',width:80}
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
		  	case 'add':
		  			openAdd();
			  		break;
		  	case 'search':
				var   searchName=$("#checkStatus").find("option:selected").text();
					table.reload('test', {
						url: '${APP_PATH}/purchase/selectReturnAll.do'
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
      var da = obj.data;
      if(obj.event === 'edit'){
    		var id=da.bprId;
    	    layer.open({
    	    	type: 1, 
    	    	title:'详细信息',
    	 		shadeClose : true,
    	  		area : [ '700px', '460px' ],//大小
    	    	offset: ['00px', '50px'],
    	    	content: $('#purchaseReturnDetails') //这里content是一个普通的String
    	    });
    	    
    	    table.render({
    	        elem: '#detail'
    	        ,url:'${APP_PATH}/purchase/selectReturnDetail.do?id='+id
    	        ,totalRow : true
    		    ,title: '退货商品表'
    	        ,cols: [[ //标题栏
    	            {field:'proId', title:'商品id', fixed: 'left', unresize: true}
    	            ,{field:'proName', title:'商品名称'}
    	            ,{field:'proPrice', title:'商品价格'}
    	            ,{field:'bprdQuantity', title:'商品数量'}
    	            ,{field:'bprdSubtotal', title:'商品小计',totalRow : true}
    	        ]]
    	      });   
    }
  });
  
 
	 //打开新增页面
	 function openAdd(){
		 tanIndex= layer.open({
			 type:1,  //设置类型 默认为0 1 页面层 2 iframe层
			 title:'新增一条退货订单',  //标题
			 content:$("#mydiv"),//内容  type=0 的内容
			 skin:'layui-layer-molv',//skin - 样式类名
			 area:['340px'],  //area - 宽高
			 //offset:'lt',//offset - 坐标 默认：垂直水平居中
			 btnAlign:'c',//按钮排列
			 closeBtn:2,  //设置关闭按钮的样式 默认是1  0是没有 2
		     shade:[0.8, '#393D49'],//shade - 遮罩
		     shadeClose:true,//点击遮罩 是否关闭弹层
		     anim: 4,//设置动画
			 maxmin:true,//该参数值对type:1和type:2有效。默认不显示最大小化按钮。需要显示配置maxmin: true即可
			 success:function(index){
				
				 url='${APP_PATH}/purchase/addReturn.do';
				  //清空表单数据
				  $("#dataform")[0].reset();
				  
			  }		  
	});
}
	 //提交数据
	 form.on("submit(dosubmit)",function(obj){
		 //序列化表单数据
		 var params=$("#dataform").serialize();
	/* 	 $.ajax({
			type:"post",
			url:url,
			data:params,
			success:function(data){
				//刷新数据表格
				tableIns.reload();
				//关闭弹出层
				layer.close(tanIndex);
				
			}
		 }); */
	   $.post(url,params,function(back){
		           alert(back);
					//刷新数据表格
					tableIns.reload();
					//关闭弹出层
					layer.close(tanIndex);		
		 }); 
	 })
	
  //日期
  laydate.render({
    elem: '#date1'
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