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
                    <h1>上海医药（集团）有限公司</h1>
                  </div>
                  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;welcome to background system.</p>
                </div>
              </div>
            </div>
            <!-- Form Panel    -->
            <div class="col-lg-6 bg-white">
              <div class="form d-flex align-items-center">
                <div class="content" >
                	<table id="tab" style="height: 250px">
                		
                	</table>
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
    <script type="text/javascript">
    	$(function(){
    		$.ajax({
    			type:"post",
    			url:"${APP_PATH}/queryStorefactsheet.do",
    			dateType:"json",
    			success:function(result){
    				var data=result.data;
    				var content="";
    				content+='<tr align="right">';
            		content+='	<td width="110px">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</td>';
					content+='	<td width="170px" align="left">'+data.sfsname+'</td>';
            		content+='	<td width="110px">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</td>';
					content+='	<td width="170px" align="left">'+data.sfslocation+'</td>';
            		content+='</tr>';
            		content+='<tr align="right">';
            		content+='	<td width="110px">联系电话：</td>';
					content+='	<td width="170px" align="left">'+data.sfscontact+'</td>';
            		content+='	<td width="110px">法人代表：</td>';
					content+='	<td width="170px" align="left">'+data.empId+'</td>';
            		content+='</tr>';
            		content+='<tr align="right">';
            		content+='	<td width="110px">占地面积：</td>';
					content+='	<td width="170px" align="left">'+data.sfsnetarea+'</td>';
            		content+='	<td width="110px">注册资金：</td>';
					content+='	<td width="170px" align="left">'+data.sfsregisiteredamount+'$</td>';
            		content+='</tr>';
            		content+='<tr align="right">';
            		content+='	<td width="110px">员工人数：</td>';
					content+='	<td width="170px" align="left">'+data.sfscrewSize+'</td>';
            		content+='	<td width="110px">注册时间：</td>';
					content+='	<td width="170px" align="left">'+data.sfsopendate+'</td>';
            		content+='</tr>';
    				
    				$("#tab").html(content);
    			}
    		});
    	});
    </script>
  </body>
</html>