<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>员工管理</title>
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

	<div class="layui-upload">
		<button type="button" class="layui-btn layui-btn-normal" id="test8">导入员工</button>
		<button type="button" class="layui-btn" id="test9">确认导入</button>
	</div>
	<table class="layui-hide" id="test" lay-filter="test"></table>

	<script type="text/html" id="toolbarDemo"> 
  <div class="layui-btn-container" style="padding-left:20px;">
    <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="getCheckData"><i class="layui-icon layui-icon-add-1"></i>新增员工 </button>
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
  
  
  //选完文件后不自动上传
  upload.render({
    elem: '#test8'
    ,url: '${APP_PATH}/addMoreEmp.do'
    ,auto: false
    ,accept:'file'
    //,multiple: true
    ,bindAction: '#test9'
    ,done: function(res){
    	if(res==false){
			layer.msg("添加失败", {time:2000, icon:5, shift:6});
		}else{
			layer.msg("添加成功", {time:2000, icon:1, shift:3},function(){
				table.reload('test', {
					  url:'${APP_PATH}/queryAllEmp.do'
				});
			});
		}
    }
  });
  
  
  table.render({
    elem: '#test'
    ,url:'${APP_PATH}/queryAllEmp.do'
    ,toolbar: '#toolbarDemo'
    ,title: '员工单'
    ,cols: [[
      {type: 'checkbox', fixed: 'left'}
      ,{field:'headUrl', title:'头像', unresize:true,templet: function(d){
        return '<img style="width: 200px;height: 400px"  src="/pic/'+d.headUrl+'">';
      }
      }
      ,{field:'empId', title:'员工Id', unresize:true}
      ,{field:'empName', title:'姓名',unresize:true}
      ,{field:'empPwd', title:'密码', unresize:true}
      ,{field:'empAge', title:'年龄', unresize:true}
      ,{field:'empSex', title:'性别', unresize:true}
      ,{field:'empTel', title:'电话', unresize:true}
      ,{field:'studyUndergo', title:'学历', unresize:true}
      ,{field:'roleId', title:'角色id', unresize:true}
      ,{field:'field1', title:'角色名', unresize:false}
      ,{field:'joinTime', title:'入职时间', unresize:false}
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
      layer.confirm('确认删除该员工吗？', function(index){
    	  $.ajax({
				type:"post",
				url:"${APP_PATH}/deletEmpById.do",
				data:{
					"empid":data.empId,
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
    	
    	$.ajax({
			type:"post",
			url:"${APP_PATH}/queryRoleIdName.do",
			success:function(result){
				var content='<option value="">请分配角色</option>';
				console.log(result);
				var d=result.data;
				$.each(d,function(index,item){
					if(item.roleId==data.roleId){
						content+='<option id="iot'+item.roleId+'" selected value="'+item.roleId+'">'+item.roleName+'</option>';
    				}else{
    					content+='<option id="iot'+item.roleId+'" value="'+item.roleId+'">'+item.roleName+'</option>';
    				}
				});
				$("#roleId").html(content);
				form.render('select'); //重新渲染
			}
		});
    	$("#demo1").prop("src","/pic/"+data.headUrl);
    	//console.log(data);
    	//formTest 即 class="layui-form" 所在元素对应的 lay-filter="" 对应的值
    	form.val("formAuthority", {
    	  "empName": data.empName // "name": "value"
    	  ,"empPwd":data.empPwd
    	  ,"empAge": data.empAge
    	  ,"empSex": data.empSex
    	  ,"empTel":data.empTel
    	  ,"joinTime":data.joinTime
    	  ,"studyUndergo":data.studyUndergo
    	  ,"empId":data.empId
    	})
    	
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
		            url: "${APP_PATH}/updateEmployeeById.do",
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
									  url: '${APP_PATH}/queryAllEmp.do'
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
  
  
  form.on('select(deptCheck)', function(data){
	 /*  console.log(data.elem); //得到select原始DOM对象
	  console.log(data.value); //得到被选中的值
	  console.log(data.othis); //得到美化后的DOM对象 */
	  var deptId=data.value;
	  $.ajax({
			type:"post",
			url:"${APP_PATH}/queryRoleIdName.do",
			
			success:function(data){
				var content='<option value="">请分配角色</option>';
				$.each(data,function(index,item){
					content+='<option id="iot'+item.roleId+'" value="'+item.roleId+'">'+item.roleName+'</option>';
				});
				$("#roleId").html(content);
				form.render('select'); //重新渲染
			}
		});
	}); 
  
//工具栏事件
	table.on('toolbar(test)', function(obj) {
		var checkStatus = table.checkStatus(obj.config.id);
		switch (obj.event) {
		case 'getCheckData':
            $("#formIdOne")[0].reset();
            
			var index = layer.open({
				title : '新增员工',//标题
				type : 1,//样式
				shade: 0,
				offset: ['15%', '35%'],//设置位移
			    btn: ['确认', '取消'],
				yes: function(index, layero){
					var formSatellite = document.getElementById("formIdOne");//获取所要提交form的id
			        var fs1 = new FormData(formSatellite);  //用所要提交form做参数建立一个formdata对象
					$.ajax({
			            url: "${APP_PATH}/addEmployee.do",
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
										  url: '${APP_PATH}/queryAllEmp.do'
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
						url: '${APP_PATH}/queryAllEmp.do'
					  	,where: { //设定异步数据接口的额外参数，任意设
					    	"empName": searchName
					  	}
					}); //只重载数据
				break;
		};
	});
	
	//普通图片上传
	  var uploadInst = upload.render({
	    elem: '#test1'
	    ,url: '/upload/'
	    ,accept: 'images'//指定允许上传时校验的文件类型，可选值有：images（图片）、file（所有文件）、video（视频）、audio（音频）
	    ,multiple: true//是否允许多文件上传。设置 true即可开启。不支持ie8/9
	    ,auto:false//是否选完文件后自动上传。如果设定 false，那么需要设置 bindAction 参数来指向一个其它按钮提交上传
	    ,size: 10000 //限制文件大小，单位 KB
	    ,choose: function(obj){
	      //预读本地文件示例，不支持ie8
	      obj.preview(function(index, file, result){
	        $('#demo1').attr('src', result); //图片链接（base64）
	      });
	    }
	  });
	//日期
	  laydate.render({
	    elem: '#date'
	  });
  
  
  
  
  
  
  
  
  
  
  
});
</script>


	<div class="site-text" style="margin: 5%; display: none" id="branch"
		target="test123">
		<form class="layui-form" lay-filter="formAuthority" id="formIdOne">
			<input type="hidden" name="empId" autocomplete="off"
				class="layui-input">
			<div class="layui-input-inline" style="margin-top: 10px;">
				<label style="margin: 0 10px 0 20px; font-size: 13px;">员工姓名：</label>
				<div class="layui-input-inline">
					<input type="text" name="empName" lay-verify="required"
						placeholder="请输入姓名" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-input-inline" style="margin-top: 10px;">
				<label style="margin: 0 10px 0 20px; font-size: 13px;">员工密码：</label>
				<div class="layui-input-inline">
					<input type="text" name="empPwd" lay-verify="required"
						placeholder="请输入密码" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-input-inline" style="margin-top: 10px;">
				<label style="margin: 0 10px 0 20px; font-size: 13px;">员工年龄：</label>
				<div class="layui-input-inline">
					<input type="text" name="empAge" lay-verify="required"
						placeholder="请输入年龄" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-input-inline" style="margin-top: 10px;">
				<label style="margin: 0 10px 0 20px; font-size: 13px;">员工性别：</label>
				<div class="layui-input-inline" style="width: 193px">
					<input type="radio" name="empSex" value="男" title="男"> <input
						type="radio" name="empSex" value="女" title="女" checked>
				</div>
			</div>
			
			<div class="layui-input-inline" style="margin-top: 10px;">
				<label style="margin: 0 10px 0 20px; font-size: 13px;">选择角色：</label>
				<div class="layui-input-inline" style="width: 193px">
					<select name="roleId" id="roleId" lay-verify="">
						<option value="">请分配角色</option>
					</select>
				</div>
			</div>
			<div class="layui-input-inline" style="margin-top: 10px;">
				<label style="margin: 0 10px 0 20px; font-size: 13px;">员工电话：</label>
				<div class="layui-input-inline">
					<input type="tel" name="empTel" lay-verify="required|phone"
						placeholder="请输入电话" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-input-inline" style="margin-top: 10px;">
				<label style="margin: 0 10px 0 20px; font-size: 13px;">员工学历：</label>
				<div class="layui-input-inline" style="width: 193px">
					<select name="studyUndergo" lay-verify="">
						<option value="">请选择学历</option>
						<option value="高中">高中</option>
						<option value="专科">专科</option>
						<option value="本科">本科</option>
						<option value="硕士">硕士</option>
						<option value="研究生">研究生</option>
					</select>
				</div>
			</div>
			<div class="layui-input-inline" style="margin-top: 10px;">
				<label style="margin: 0 10px 0 20px; font-size: 13px;">入职时间：</label>
				<div class="layui-input-inline">
					<input type="text" name="joinTime" id="date" lay-verify="date"
						placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-input-inline" style="margin-top: 10px;">
				<label style="margin: 0 10px 0 20px; font-size: 13px;">上传头像：</label>
				<div class="layui-input-inline">
					<div class="layui-upload">
						<button type="button" class="layui-btn" id="test1">上传图片</button>
						<div class="layui-upload-list">
							<img src="" style="width: 100px; height: 100px"
								class="layui-upload-img" id="demo1">
						</div>
					</div>

				</div>
			</div>
		</form>
	</div>

</body>
</html>