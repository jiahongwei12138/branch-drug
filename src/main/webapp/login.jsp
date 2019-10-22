<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>登录-医药ERP</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="robots" content="all,follow">
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="${APP_PATH }/css/bootstrap.min.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
    <!-- Fontastic Custom icon font-->
    <link rel="stylesheet" href="${APP_PATH }/css/fontastic.css">
    <!-- Google fonts - Poppins -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:300,400,700">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="${APP_PATH }/css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="${APP_PATH }/css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="">
  </head>
  <body>
    <div class="page login-page">
      <div class="container d-flex align-items-center">
        <div class="form-holder has-shadow">
          <div class="row">
            <!-- Logo & Information Panel-->
            <div class="col-lg-6">
              <div class="info d-flex align-items-center">
                <div class="content">
                  <div class="logo">
                    <h1>医药ERP后台登录系统</h1>
                  </div>
                  <p>Medical ERP background login system.</p>
                </div>
              </div>
            </div>
            <!-- Form Panel    -->
            <div class="col-lg-6 bg-white">
              <div class="form d-flex align-items-center">
                <div class="content">
                  <form method="post" class="form-validate">
                    <div class="form-group">
                      <input id="empName" type="text" name="empName" required data-msg="Please enter your username" class="input-material">
                      <label for="login-username" class="label-material">User Name</label>
                    </div>
                    <div class="form-group">
                      <input id="empPwd" type="password" name="empPwd" required data-msg="Please enter your password" class="input-material">
                      <label for="login-password" class="label-material">Password</label>
                    </div><a id="login" href="javaScript:void(0)" class="btn btn-primary">Login</a>
                    <!-- This should be submit button but I replaced it with <a> for demo purposes-->
                  </form><a href="" class="forgot-pass">Forgot Password?</a><br><small>Do not have an account? </small><a href="" class="signup">Signup</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="copyrights text-center">
        <p><a href="#" class="external">医药erp系统</a>
          <!-- Please do not remove the backlink to us unless you support further theme's development at https://bootstrapious.com/donate. It is part of the license conditions. Thank you for understanding :)-->
        </p>
      </div>
    </div>
    <!-- JavaScript files-->
    <script src="${APP_PATH }/js/jquery-3.4.1.min.js"></script>
    <script src="${APP_PATH }/js/popper.min.js"> </script>
    <script src="${APP_PATH }/js/bootstrap.min.js"></script>
    <script src="${APP_PATH }/js/jquery.cookie.js"> </script>
    <script src="${APP_PATH }/js/Chart.min.js"></script>
    <script src="${APP_PATH }/js/jquery.validate.min.js"></script>
    <!-- Main File-->
    <script src="${APP_PATH }/js/front.js"></script>
    <script src="${APP_PATH }/layui/layui.js"></script>
    <script type="text/javascript">
    	layui.use(['form','jquery'],function(){
    		var form = layui.form;
    		var $ = layui.jquery;
    		var layer = layui.layer;
    		$("#login").click(function(){
    			var empName=$("#empName").val();
    			var empPwd=$("#empPwd").val();
    			if(empName.trim()==""){
    				layer.msg("请输入账号！",{time:1000, icon:5, shift:6});
    			}else if(empPwd==""){
    				layer.msg("请输入密码！",{time:1000, icon:5, shift:6});
    			}else{
    				$.ajax({
    					type:"post",
    					url:"${APP_PATH}/login.do",
    					data:{
    						"empName":empName,
    						"empPwd":empPwd
    					},
    					success:function(result){
    						if(result==true){
    							window.location.href="${APP_PATH}/admin/adminindex.jsp";
    						}else{
    							layer.msg("登录失败，账号或密码错误！",{time:1000, icon:5, shift:6});
    						}
    					}
    				});
    			}
    		});
    		
    		
    	});
    	
    </script>
  </body>
</html>