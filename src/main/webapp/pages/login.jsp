<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" href="layui/css/layui.css">

  </head>
  <style>
  #verifyCodeImg{
    display: block;
    height:90%;
  }

  .cr-header {
    width: 100%;
    height: 65px;
    background-color: #393D49;
  }
  .cr-header .main {
    position: relative;
    margin: 0 auto;
    min-height: 0;
  }
  .main {
    max-width: 1079px;
    margin: 0 auto 15px;
  }
  .logo {
    display: block;
    position: absolute;
    top: 13px;
    left: 15px;
    /* width: 135px; */
    height: 37px;
    color: #009688;
    font-size: 24px;
  }
  .nav-user {
    position: absolute;
    top: 24px;
    right: 15px;
  }
  .nav-user span, .nav-user .unlogin, .out-login, .avatar, .avatar *, .nav-user .nav {
    display: inline-block;
    vertical-align: middle;
  }
  .nav-user span a {
    padding: 0 10px;
    color: #A9B7B7;
  }
  .cr-body {
    margin: 15px auto 48px auto;
    max-width: 780px;
  }
  .cr-body h1 {
    font-size: 22px;
    margin-bottom: 10px;
    line-height: 30px;
    padding: 10px;
    color: #393D49;
    border-bottom: 1px solid #009688;
  } 
  .cr-form {
    margin: 15px auto;
    max-width: 480px;
    background-color: #fff;
    padding: 30px;
    border: 1px solid #f0f0f0;
  }
  .cr-form a {
    color: #999;
    text-decoration: underline;
  }
  .cr-footer {
    position: fixed;
    bottom: 0;
    background-color: #fcfcfc;
    margin-top: 10px;
    border-top: 1px solid #f0f0f0;
    width: 100%;
    height: 38px;
    padding: 10px 0;
    text-align: center;
    color: #d2d2d2;
    line-height: 38px;
  }
  </style>
  <body>
  
  <div class="layui-layout">
    <!-- 头部区域 -->
    <div class="layui-header cr-header">
        <div class="main">
            <a href="#" class="logo">东软CRM</a>
            <div class="nav-user">     
                <span><a href="#">登录</a></span>
            </div>
        </div>
    </div>
    <div class="cr-body">

    <h1><i class="layui-icon"></i> 用户登录</h1>
    <div class="cr-form">
        <form class="layui-form" style="margin-left: 30px;" lay-filter="loginForm" action="<%=basePath%>user/login" id="submitForm"  method="post">
        <!--<i class="layui-icon">&#xe613;</i>
          <hr>-->
          <div class="layui-form-item">
			<label class="layui-form-label" for="account">登录帐号</label>
			<div class="layui-input-block" style="width: 250px;">
			  <input name="account" type="text" lay-verify="required" placeholder="登录帐号(必填)" autocomplete="off" class="layui-input">
			     
			</div>
          </div>
          
          <div class="layui-form-item">
			<label class="layui-form-label" for="password">登录密码</label>
			<div class="layui-input-inline" style="width: 250px;">
			  <input name="password"  type="password" lay-verify="required" placeholder="登录密码(必填)" autocomplete="off" class="layui-input">
			     
			</div>
          </div>
          
          <div class="layui-form-item">
              <label class="layui-form-label">验证码</label>
              <div class="layui-input-block"  >
                  <input type="text" name="verifyCode" lay-verify="required" placeholder="验证码(必填)" autocomplete="off" class="layui-input" style="width: 100px; float: left; margin-right: 14px">
               
                  <img style="padding:0 !important;height:38px;width:130px" id="verifyCodeImg" src="<%=basePath %>verify/getVerifyCode" onclick="refleshVerifyCode();" />
              </div>
          </div>
          
          <div class="layui-form-item">
            <div class="layui-input-block">
                <button style="margin-top: 10px; type="submit" class="layui-btn layui-btn-default" id="loginButton" lay-submit lay-filter="loginForm">
                    <i class="layui-icon">စ</i>&nbsp;登录系统
                </button>
                
            </div>
          </div>   
             
          <div class="layui-form-item">
            <label class="layui-form-label" ></label>
            <div>
                <a  href="<%=basePath %>pages/forgotPasswd.jsp">忘记密码</a> 
            </div>
          </div>
        </form> 
    </div>


    </div>
    <div class="cr-footer"> 
    © 东软CRM － 以极简方式管理客户和业务过程
    </div>
</div>
  
    <script src="layui/layui.all.js"></script>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script> 
    <script type="text/javascript">
    layui.form.on('submit(loginForm)', function(data){
        login(data.field);
        console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
    $("#loginButton").click(function(){
    	$("#loginForm").submit();
    });
    $("input").keyup(function(e){
    	if(e.keyCode == 13){
    		$("#loginForm").submit();
    	}
    });
    /**
    	验证码刷新
    **/
    function refleshVerifyCode(){
    	$("#verifyCodeImg").attr("src", "${pageContext.request.contextPath}/verify/getVerifyCode?time="+Math.random());
    }
    function login(data){
    	$.ajax({
    		url:"${pageContext.request.contextPath}/user/login",
    		type:"post",
    		data:data,
    		dataType:"json",
    		beforeSend:function(){
    			loading = layer.load();
    		},
    		success:function(data){
    			if(data && data.code == 0){
    				//如果为首次登陆
    				if(data.firstLogin){
    					layer.msg("这是你的首次登陆，请先修改默认密码", {
                            icon : 6,
                            time : 2000,
                            end : function() {
                            	location.href = "${pageContext.request.contextPath}/#/userPasswd"
                            }
                        })
    				}else{
    					layer.msg("登录成功");
    					location.href="${pageContext.request.contextPath}";//跳转主页
    				}
    			}else{
    				layer.msg(data.msg);
    				
    				//刷新验证码
    				refleshVerifyCode();
    			}
    		},
    		error:function(){
    			layer.msg("服务器开销差了!");
    			refleshVerifyCode();
    		},
    		complete:function(){
    			layer.close(loading);
    			
    		}
    	});
    }
    </script>
  </body>
</html>
