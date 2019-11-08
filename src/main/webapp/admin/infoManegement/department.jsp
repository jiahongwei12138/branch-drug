<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>客户管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="${APP_PATH }/layui/css/layui.css"
	media="all">
<script src="${APP_PATH }/layui/layui.js"></script>
<script type="text/javascript" src="${APP_PATH }/js/jquery-3.4.1.min.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>

	<table class="layui-hide" id="test" lay-filter="test"></table>

	<script type="text/html" id="toolbarDemo"> 
  <div class="layui-btn-container" style="padding-left:20px;">
    <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="getCheckData"><i class="layui-icon layui-icon-add-1"></i>新增客户 </button>
	<input type="text" placeholder="请输入用户" class="layui-text"  id="search" style="width:150px;height:30px;">
	<button class="layui-btn layui-btn-sm  layui-btn-normal" lay-event="search"><i class="layui-icon layui-icon-search"></i>搜索</button>
 	
 </div>
	
</script>
	<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

	<script>
layui.use(['table','laydate','form','tree','jquery', 'util','upload','laydate'], function(){
  var table = layui.table;
  var laydate = layui.laydate;
  var form = layui.form;
  var tree = layui.tree
  ,layer = layui.layer
  ,util = layui.util
  ,upload = layui.upload
  ,laydate = layui.laydate
  ,$ = layui.jquery;
  
  

  
  
  table.render({
    elem: '#test'
    ,url:'${APP_PATH}/queryAllClient.do'
    ,toolbar: '#toolbarDemo'
    ,title: '员工单'
    ,cols: [[
      {type: 'checkbox', fixed: 'left'}
      ,{field:'clientId', title:'客户Id', unresize:true}
      ,{field:'clientName', title:'客户姓名',unresize:true}
      ,{field:'clientAge', title:'客户年龄', unresize:true}
      ,{field:'clientSex', title:'客户性别', unresize:true}
      ,{field:'clientTel', title:'联系电话', unresize:true}
      ,{
		fixed: 'right', title:'操作',align:'center', toolbar: '#barDemo',unresize:false
      }
    ]]
    ,page: true
    ,limit:5
    ,limits:[5,10,20,30,40,50,60,70,80,90]
  });
  
  
//监听工具条
  table.on('tool(test)', function(obj){
    var data = obj.data;
   if(obj.event === 'del'){
      layer.confirm('确认删除该客户吗？', function(index){
    	  $.ajax({
				type:"post",
				url:"${APP_PATH}/deleteCliById.do",
				data:{
					"clientId":data.clientId,
				},
				success:function(result){
					if(result==false){
						layer.msg("删除失败", {time:3000, icon:5, shift:6});
					}else{
						layer.msg("删除成功", {time:3000, icon:1, shift:3});
						obj.del();
					}
					layer.close(index);
				}
			});
      });
    } else if(obj.event === 'edit'){
    	
    	form.val("formAuthority", {
    		"clientId": data.clientId
    	 , "clientName": data.clientName // "name": "value"
    	  ,"clientAge":data.clientAge
    	  ,"clientSex": data.clientSex
    	  ,"clientTel": data.clientTel
    	});
    	
    	var index = layer.open({
			title : '编辑员工',//标题
			type : 1,//样式
			shade: 0,
			offset: ['15%', '35%'],//设置位移
			btn: ['确认', '取消'],
			yes: function(index, layero){
				var formSatellite = document.getElementById("formIdOne");//获取所要提交form的id
		        var fs1 = new FormData(formSatellite);  //用所要提交form做参数建立一个formdata对象
				$.ajax({
		            url: "${APP_PATH}/updateCliById.do",
		            type: "POST",
		            data: fs1,
		            async : false,
		            contentType: false,   //ajax 中 contentType 设置为 false 是为了避免 JQuery 对其操作，从而失去分界符，而使服务器不能正常解析文件
		            processData: false,   //当设置为true的时候,jquery ajax 提交的时候不会序列化 data，而是直接使用data
		            success: function (result) {
		            	if(result==false){
							layer.msg("修改失败", {time:2000, icon:5, shift:6});
						}else{
							layer.msg("修改成功", {time:2000, icon:1, shift:3},function(){
								layer.close(index);
								table.reload('test', {
									  url: '${APP_PATH}/queryAllClient.do'
								});
							});
						}
		            }
		        });
			}
			,btn2: function(index, layero){
				  layer.close(index);
			},
			content :$("#branch"),
		});
    }
  });
  
  
  
  
//工具栏事件
	table.on('toolbar(test)', function(obj) {
		var checkStatus = table.checkStatus(obj.config.id);
		switch (obj.event) {
		case 'getCheckData':
			
			//给clientId赋值0是数据完整
			$("#formIdOne").find('input[name="clientId"]').attr('value',0);
			
            $("#formIdOne")[0].reset();
            
			var index = layer.open({
				title : '新增客户',//标题
				type : 1,//样式
				shade: 0,
				offset: ['15%', '35%'],//设置位移
			    btn: ['确认', '取消'],
				yes: function(index, layero){
					var formSatellite = document.getElementById("formIdOne");//获取所要提交form的id
			        var fs1 = new FormData(formSatellite);  //用所要提交form做参数建立一个formdata对象
					$.ajax({
			            url: "${APP_PATH}/addClient.do",
			            type: "POST",
			            data: fs1,
			            async : false,
			            contentType: false,   //ajax 中 contentType 设置为 false 是为了避免 JQuery 对其操作，从而失去分界符，而使服务器不能正常解析文件
			            processData: false,   //当设置为true的时候,jquery ajax 提交的时候不会序列化 data，而是直接使用data
			            success: function (result) {
			            	if(result==false){
								layer.msg("添加失败", {time:2000, icon:5, shift:6});
							}else{
								layer.msg("添加成功", {time:2000, icon:1, shift:3},function(){
									layer.close(index);
									table.reload('test', {
										  url: '${APP_PATH}/queryAllClient.do'
									});
								});
							}
			            }
			        });
					//layer.close(index);
				}
				,btn2: function(index, layero){
					  layer.close(index);
				},
				
				content :$("#branch"),
			});
			break;
			case 'search':
				var searchName=$("#search").val();
					table.reload('test', {
						url: '${APP_PATH}/queryAllClient.do'
					  	,where: { //设定异步数据接口的额外参数，任意设
					    	"clientName": searchName
					  	}
					}); //只重载数据
				break;
		};
	});
	
	
  
  
  
  
  
  
  
  
  
  
  
});
</script>


	<div class="site-text" style="margin: 5%; display: none" id="branch"
		target="test123">
		<form class="layui-form" lay-filter="formAuthority" id="formIdOne">
			<input type="hidden" name="clientId" autocomplete="off"
				class="layui-input">
			<div class="layui-input-inline" style="margin-top: 10px;">
				<label style="margin: 0 10px 0 20px; font-size: 13px;">客户姓名：</label>
				<div class="layui-input-inline">
					<input type="text" name="clientName" lay-verify="required"
						placeholder="请输入姓名" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-input-inline" style="margin-top: 10px;">
				<label style="margin: 0 10px 0 20px; font-size: 13px;">客户年龄：</label>
				<div class="layui-input-inline">
					<input type="text" name="clientAge" lay-verify="required"
						placeholder="请输入年龄" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-input-inline" style="margin-top: 10px;">
				<label style="margin: 0 10px 0 20px; font-size: 13px;">客户性别：</label>
				<div class="layui-input-inline" style="width: 193px">
					<input type="radio" name="clientSex" value="男" title="男"> <input
						type="radio" name="clientSex" value="女" title="女" checked>
				</div>
			</div>
			<div class="layui-input-inline" style="margin-top: 10px;">
				<label style="margin: 0 10px 0 20px; font-size: 13px;">联系电话：</label>
				<div class="layui-input-inline">
					<input type="tel" name="clientTel" lay-verify="required|phone"
						placeholder="请输入电话" autocomplete="off" class="layui-input">
				</div>
			</div>
			
		</form>
	</div>

</body>
</html>