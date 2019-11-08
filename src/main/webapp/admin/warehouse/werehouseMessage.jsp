<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>仓库信息</title>
<link rel="stylesheet" href="${APP_PATH }/layui/css/layui.css"  media="all">
<script src="${APP_PATH }/layui/layui.js"></script>
<script type="text/javascript" src="${APP_PATH }/js/jquery-3.4.1.min.js"></script>
<style type="text/css">
.layui-inline{
	margin-top: 10px;
}
</style>

</head>
<body>
	<table class="layui-hide" id="store" lay-filter="store"></table>
		
	<div id="but"  class="layui-btn-group layui-hide" >
		<button type="button" class="layui-btn layui-btn-normal" lay-event="add" id="add"><i class="layui-icon"></i>新建仓库</button>
	</div>
	
	<div id="but2"  class="layui-hide" >
		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</div>
	
	<div id="addStoreDiv" style="display: none;">
		<form class="layui-form" action="javaScript:void(0)"  lay-filter="addStore" id="addStore" method="post">
			<div class="layui-form-item">
				<div class="layui-inline" >
					<label class="layui-form-label" >仓库名称:</label>
					<div class="layui-input-inline">
						<input type="text" name="storeName" id="storeName"
							placeholder="请输入仓库名称" autocomplete=”off” class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">仓库容量:</label>
					<div class="layui-input-inline">
						<select name="capacity" lay-filter="capacity">
							<option value="1000000">大型仓库(1000000)</option>
							<option value="500000">中型仓库(500000)</option>
							<option value="200000" selected>小型仓库(200000)</option>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">仓库位置:</label>
					<div class="layui-input-inline">
						<input type="text" name="address" id="address"
							placeholder="请输入仓库位置" autocomplete=”off” class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">创建时间:</label>
					<div class="layui-input-inline">
						<input type="text" name="creationTime" id="creationTime"
							lay-verify="creationTime" placeholder="yyyy-MM-dd"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="hidden" name="state" id="state"
							value="0%" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<div class="layui-input-block">
						<button id="storagesubmit" type="submit" class="layui-btn" lay-submit
							lay-filter="storagesubmit">提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	
	<script>
	layui.use(['table','jquery','layer','form','laydate'], function(){
		var table = layui.table;
		var $=layui.jquery;
		var layer = layui.layer;
		var form = layui.form;
		var laydate=layui.laydate;

		//日期
		laydate.render({
			elem : '#creationTime'
		});
		
		
		var store=table.render({
			elem: '#store', //指定原始表格元素选择器（推荐id选择器）
			url: '${APP_PATH }/warehouse/selectAllStore.do',
			toolbar:  '#but',
			height: 'full-20',
			defaultToolbar: [],
			page:true,
			limits:[10,20],
			cols: [[
			      {field:'storeId', width:'14%', title: '仓库ID' , unresize:true}
			      ,{field:'storeName', width:'14%', title: '仓库名称', unresize:true}
			      ,{field:'capacity', width:'14%', title: '仓库容量', unresize:true}
			      ,{field:'address', width:'14%', title: '仓库位置', unresize:true}
			      ,{field:'creationTime', width:'14%', title: '创建时间' , unresize:true}
			      ,{field:'state', width:'14%', title: '容量状态', unresize:true}
			      ,{fixed: 'right', title:'操作', toolbar: '#but2', width:'16%',align:'center'}
			]]
		});

		var ss;
		//监听表头工具栏
		table.on('toolbar(store)',function(obj){
			switch(obj.event){
			case 'add':
				ss=layer.open({
					title: '商品详情'			//标题
					,type:1					//样式
					,area:['350px','340px']	//设置宽高
					,content:$('#addStoreDiv')	
					,shade: 0
					,skin: 'layui-layer-molv'
				});    
			}
		})  

		//监听提交按钮
		$("#storagesubmit").click(function(){
			$.ajax({
				url: '${APP_PATH }/warehouse/addStore.do',
				type: "POST",
				data: $("#addStore").serializeArray(),//这个是传给后台的值   序列化 */
				success: function(back){
					console.log(back);
					 if(back == 1){ 
						layer.close(ss);
						//更新前台显示的值
						store.reload();
						layer.msg("添加成功", {icon: 6});
					}else{
						layer.msg("添加失败", {icon: 5});
					} 
				}
	
			});
		})

		//监听行内工具栏
		table.on('tool(store)',function(obj){
			var data=obj.data;
			
			switch(obj.event){
			case 'del':
				layer.confirm('真的删除行么', function(index){
					delStore(data.storeId);
			         
			     });
			}
				
		});

		//删除仓库方法
		function delStore(storeId){
			$.ajax({
				url: '${APP_PATH }/warehouse/deleteStoreById.do',
				data: {"storeId" : storeId},
				success: function(back){
					 if(back == 1){
						layer.close(ss);
						//更新前台显示的值
						store.reload();
						layer.msg("删除成功", {icon: 6});
					}else{
						layer.msg("删除失败", {icon: 5});
					}
				}
				
			});
		};	
	
	});
	</script>
</body>
</html>