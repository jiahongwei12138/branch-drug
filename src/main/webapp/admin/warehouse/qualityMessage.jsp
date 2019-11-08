<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>采购质检</title>
<link rel="stylesheet" href="${APP_PATH }/layui/css/layui.css"  media="all">
<script src="${APP_PATH }/layui/layui.js"></script>
<script type="text/javascript" src="${APP_PATH }/js/jquery-3.4.1.min.js"></script>
</head>
<body>
	<table class="layui-hide" id="quality" lay-filter="quality"></table>
	
	<script>
		layui.use(['table','jquery','layer','form'], function(){
			$=layui.jquery;
			table=layui.table;
			
	
	
		})
	</script>
</body>
</html>