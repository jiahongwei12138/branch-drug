 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>库存信息</title>
<link rel="stylesheet" href="${APP_PATH }/layui/css/layui.css"  media="all">
<script src="${APP_PATH }/layui/layui.js"></script>
<script type="text/javascript" src="${APP_PATH }/js/jquery-3.4.1.min.js"></script>

<style>
#barDemo.layui-form-label {
	width: 100px;
}

#barDemo..layui-input-block {
	margin-left: 130px;
	min-height: 36px;
	
}
#barDemo..layui-textarea{
	border: none; 
	resize: none;
}
#barDemo..layui-input{
	border: none;
}
</style>

</head> 
<body >
 	
 	<div id="but"  class="layui-form  layui-form-item" style="margin: 10px;">
		<div class="layui-input-inline">
			<form class="layui-form" action="" lay-filter="prg">
				<div class="layui-input-inline">
					<select id="storeName" name="storeName" lay-filter="aihao">
					</select>
				</div>
			</form>
		</div>
		
		<div class="layui-input-block"  >
			<div class="layui-input-inline"   >
				<input id="condition" type="text" name="title" lay-verify="title"
					autocomplete="off" placeholder="请输入药品名称" class="layui-input"
					style="margin-right: 5px;">
			</div>
			<button type="button"
				class="layui-btn layui-btn-normal layui-btn-radius layui-clear"
				lay-event="search"  id="see" >搜索</button>
		</div>
		
	</div>
 	
	<table class="layui-hide" id="inventory" lay-filter="inventory"></table>
	
	<!-- 表头工具栏-->
	
	
	<!-- 表内工具栏-->
	<div id="barDemo" class="layui-hide">
  		<a class="layui-btn layui-btn-xs" lay-event="seeDrug" id="seeDrug">查看药品详情</a>
	</div>
	
	<!-- 商品详情界面 -->
	<div id="div" style="display: none;">
		<form class="layui-form" lay-filter="details" id="details" method="post">
			<div class="layui-form-item">
				<div class="layui-inline" >
					<label class="layui-form-label" >用法用量:</label>
					<div class="layui-input-inline">
						<input type="text" name="methodOfApplication" id="methodOfApplication" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">保质期:</label>
					<div class="layui-input-inline">
						<input type="text" name="expirationdate" id="expirationdate"
						 class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">功效描述:</label>
					<div class="layui-input-inline">
						<textarea name="proCure" lay-verify="proCure" required
						readonly="readonly" class="layui-textarea" ></textarea>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">禁用描述:</label>
					<div class="layui-input-inline">
						<textarea name="proForbidden" lay-verify="proForbidden" required
						readonly="readonly" class="layui-textarea" ></textarea>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">不良反应描述:</label>
					<div class="layui-input-inline">
						<textarea name="proBadness" lay-verify="proBadness" required
						readonly="readonly" class="layui-textarea" ></textarea>
					</div>
				</div>
			</div>
		</form>
	</div>
	
	

	<script>
		layui.use(['table','jquery','layer','form'], function(){
			var table = layui.table;
			var $=layui.jquery;
			var layer = layui.layer;
			var form = layui.form;

			//渲染下拉列表框
			$.ajax({
				url : '${APP_PATH}/warehouse/getStore.do',
				dataType : 'json',
				type : 'post',
				success : function(data) {
					$.each(data.data, function(index, item) {
						$('#storeName').append(
								new Option(item.storeName, item.storeId));// 下拉菜单里添加元素
					});
					form.render("select");
				}
			});
			
			
			table.render({
				elem: '#inventory', //指定原始表格元素选择器（推荐id选择器）
				url: '${APP_PATH }/warehouse/selectAllProduct.do',
				
				height: 'full-20',
				defaultToolbar: [],
				page:true,
				limits:[10,20],
				cols: [[
				      {field:'proid', title: '药品ID' , unresize:true}
				      ,{field:'proName', title: '药品名称', unresize:true}
				      ,{field:'enlishName', title: '英文名称', unresize:true}
				      ,{field:'proBatchNumber', width:350, title: '药品编号' , unresize:true}
				      ,{field:'expirationdate', title: '保质期', unresize:true}
				      ,{field:'number', title: '数量', unresize:true}
				      ,{field:'costPice', title: '成本价', unresize:true} 
				      ,{field:'retailPrice' , title: '售价',sort: true, unresize:true}
				      ,{fixed: 'right', title:'操作', toolbar: '#barDemo',align:'center'}
				]]
			});

			/* ,{field:'', width:140, title: '总数量', unresize:true, templet: function(d){
				return d.branchStore.storeId;
		      }} */


			//监听行内工具栏
			table.on('tool(inventory)',function(obj){
				var data=obj.data;
				
				if(obj.event === 'seeDrug'){ //查看
					layer.open({
						title: '药品详情'			//标题
						,type:1					//样式
						,area:['350px','480px']	//设置宽高
						,content:$('#div')	
						,shade: 0
						,skin: 'layui-layer-molv'
						,success:function(index){
							form.val('details',data);
							
						}
					}); 
				}
			})

			//监听表头工具栏
			$("#see").click(function() {
				//获取输入框的值
				var condition=$("#condition").val()
				
				
				table.reload('inventory', {
					url: '${APP_PATH}/warehouse/selectByProName.do'
				  	,where: { //设定异步数据接口的额外参数，任意设
				  		condition: condition
				  	}
					,method:'post'//不加这个就为get方式提交，会出现乱码
				}); //只重载数据

			})
			

			form.on("select(aihao)", function(data) {

				var storeId = data.value;

				table.reload('inventory', {
					url : '${APP_PATH}/warehouse/selectProductByStoreId.do',
					where : { //设定异步数据接口的额外参数，任意设
						storeId : storeId
					},
					method : 'post'//不加这个就为get方式提交，会出现乱码
				}); //只重载数据 
			});

			/* $(document).ready(function() {       
				    // select下拉框选中触发事件
			    form.on("select(aihao)", function(data){
			    	 var message=$("select[name=student_Level").val(); 
			        alert(data.value); // 获取选中的值
			    });
				 
			}); */

		});
	</script>
</body>
</html>