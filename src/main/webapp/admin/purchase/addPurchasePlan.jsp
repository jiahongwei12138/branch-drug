<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>采购计划基本信息</title>
  <link rel="stylesheet" href="../layui/css/layui.css">
  <script src="../layui/layui.js"></script>
</head>
<body>
          
<p>&nbsp;</p>
<p>&nbsp;</p>
<h2 align="center"><b>新增采购订单</b></h2>
<form class="layui-form" lay-filter="dataform" id="dataform" method="post">
	<!-- 这是对话框里面的LayUI的Form，新增或修改表记录用，一般insert或update需要
			 新增/修改几个列，在这里就要准备几个控件，每个控件肯定都有 class="layui-input",
			 需要数据验证的肯定有lay-verify属性  -->
  <p>基本信息</p>
  <table class="layui-table">
  	<tr>
  		<td>
  			<label class="layui-form-label" style="font-size:13px;">采购员</label>
   			<div class="layui-input-block">
      			<input name="bpurchaseName" class="layui-input" type="text" autocomplete="off" lay-verify="title" value="${name }" readonly="readonly">
    		</div>
		</td>
  		<td>
  			<label class="layui-form-label" style="font-size:13px;">分店编号</label>
   			<div class="layui-input-block">
     				<input name="braId" class="layui-input" type="text" autocomplete="off" lay-verify="title" value="${braId }" readonly="readonly">
   			</div>
    	</td>
  	</tr>
  	<tr>
  		<td>
  			<label class="layui-form-label" style="font-size:13px;">申请部门</label>
   			<div class="layui-input-block">
      			<select name="department" lay-filter="aihao">       			
        			<option value="">采购部</option>
        			<option value="0"></option>
      			</select>
    		</div>
  		</td>
  		<td>
  			<label class="layui-form-label" style="font-size:13px;">下单时间</label>
   			<div class="layui-input-block">
      			<input name="bpoTime" class="layui-input" id="date1" type="text" placeholder="yyyy-MM-dd" autocomplete="off" lay-verify="date"/>
    		</div>
  		</td>
  	</tr>
  </table>
  
  <table class="layui-hida" id="test" lay-filter="test"></table>
  
<script id="barDemo" type="text/html">
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
  <div class="layui-input-block" align="center">
      <button type="submit" class="layui-btn" lay-submit=""
						lay-filter="dosubmit">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
  </div>

</form>
 
 
          
<script>

layui.use(['table','jquery'], function(){
  var table = layui.table;
  var $=layui.jquery;
  
  //展示已知数据
  table.render({
    elem: '#test'
    ,url:'${APP_PATH}/purchase/selectDetails.do'
    ,toolbar: '#toolbarDemo'
    ,totalRow : true
    ,cols: [[ //标题栏
        {field:'proId', title:'商品id', fixed: 'left', unresize: true}
        ,{field:'proName', title:'商品名称'}
        ,{field:'enlishName', title:'英文名称'}
        ,{field:'proCure', title:'作用'}
        ,{field:'hqtName', title:'出厂商'}
        ,{field:'proPrice', title:'商品价格'}
        ,{field:'bpodQuantity', title:'商品数量'}
        ,{field:'bpodSubtotal', title:'商品小计',totalRow : true}
        ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
    ]]
    ,data: [{
      "proId": ""
      ,"proName": ""
      ,"proPrice": ""
      ,"bpodQuantity": ""
      ,"bpodSubtotal": ""
    }]
  });
  
  
/*   table.on('edit(test)', function(obj){
	  console.log(obj.value); //得到修改后的值
	  console.log(obj.field); //当前编辑的字段名
	  console.log(obj.data); //所在行的所有相关数据  
	  
	}); */
  
 /*  //头工具栏事件
  table.on('toolbar(test)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id);
    switch(obj.event){
      case 'add':
        var dataBak = [];   
        var tableBak = table.cache.test; 
        for (var i = 0; i < tableBak.length; i++) {
            dataBak.push(tableBak[i]);      //将之前的数组备份
        }
        dataBak.push({   
            "proId": ""
            ,"proName": ""
            ,"proPrice": ""
            ,"bpodQuantity": ""
            ,"bpodSubtotal": ""	
        });
        table.reload("test",{
            data:dataBak   // 将新数据重新载入表格
        });
      break;
    };
  }); */

//监听行工具事件
  table.on('tool(test)', function(obj){
    var da = obj.data;
    if(obj.event === 'del'){
      layer.confirm('真的删除行么', function(index){
        $.ajax({
        	url:"${APP_PATH}/purchase/deleteDetails.do"
				, data: {id: da.proId}
				, type: "post"
				, dataType: "json"
				, error: function(xhr, err){
					layer.alert(err);
				}
				, success: function (back) {
					if (back==1){
						table.reload('test', {
							url:'${APP_PATH}/purchase/selectDetails.do'
						});
				        layer.close(index);
					}
				}
        });  
        
        
      });
    }
  });

});
	

</script>

<script>
layui.use(['form', 'layedit', 'laydate','jquery','table'], function(){
  var form = layui.form
  ,$=layui.jquery
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate
  , table = layui.table;
  
  //日期
  laydate.render({
    elem: '#date1'
  });
  laydate.render({
	    elem: '#date2'
	  });
  
//下面是一个一个的初始化
//表单的初始化  submit是事件类型，dosubmit是提交按钮的lay-filter属性
		form.on("submit(dosubmit)", function(obj){
			//查看表格里面所有的数据
			//alert(debugObj(layui.table.cache.test));
			var length=layui.table.cache.test.length;
			/* $.each(layui.table.cache.test, function(index, row){
				alert(debugObj(row));
			}); */
			//表格对象的属性名
			//alert(debugObj(layui.table.cache.test[0].proName));	
			if(length>0){
				//序列化表单数据
				var params=$("#dataform").serialize();
				$.ajax({
					url: "${APP_PATH}/purchase/insertOrder.do"
					, data: params
					, type: "post"
					, dataType: "json"
					, error: function (xhr, err){
						alert(err);
					}
					, success: function (back) {
						if(back==1){
							parent.location.href="purchase.jsp";					
						}else{
							layer.msg("新增采购订单失败，请重新操作",{  icon: 2,
				    			  time: 1000});
							parent.location.href="purchasePlan.jsp";
						}
					}
				});
			}else{
				layer.msg("请保证要采购的商品详情至少有一条",{  icon: 2,
	    			  time: 1000});
			}
			//调用ajax请求时防止再次跳回新增表单页面
			return false;
		});
 
  
  
});

function debugObj(obj) {
	var s = "";
	for (var i in obj) {
		if ((obj[i]+"").substr(0,8) == "function")
			continue;
		s += i;
		s += " = ";
		s += obj[i];
		s += "\n";
	}
	return s;
}
		
		
		
/* 	function checkForm(frm){

		 alert(frm.a.value);
		return false;
	} */
</script>
</body>
</html>