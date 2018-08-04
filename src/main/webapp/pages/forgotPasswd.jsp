<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>找回密码</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="layui/css/layui.css">

</head>
<style>
div#myForm {
	width: 400px;
	min-width: 400px;
	margin: 0 auto;
}

.layui-form-item input {
	width: 250px;
}

.layui-form h1 {
	text-align: center;
	padding: 10px 80px;
}

.layui-form-mid {
	padding: 0px 0 !important;
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
    <!-- 头部区域 -->
    <div class="layui-header cr-header">
        <div class="main">
            <a href="#" class="logo">东软CRM</a>
            <div class="nav-user">     
                <span><a href="#">登录</a>
            </div>
        </div>
    </div>
    <div class="cr-body">
        <h1><i class="layui-icon"></i> 找回密码</h1>
        
        <div class="cr-form">
        <form lay-filter="myForm" class="layui-form" id="submitForm">
            <div class="layui-form-item">
                <label class="layui-form-label">邮箱：</label>
                <div class="layui-input-inline">
                    <input id="email-input" type="text" name="email" required
                        lay-verify="required" placeholder="请输入邮箱" autocomplete="off"
                        class="layui-input">
                </div>
            </div>

            <div class="layui-form-item" style="height:38px;">
                <label class="layui-form-label">验证码：</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input type="text" name="code" required lay-verify="required"
                        placeholder="请输入验证码" autocomplete="off" class="layui-input"
                        style="width:100%">

                </div>
                <div class="layui-form-mid layui-word-aux" style="height: 38px;">
                    <button id="code-Btn" type="button" class="layui-btn" 
                        style="width: 140px">获取验证码</button>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">新密码：</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" placeholder="请输入密码" lay-verify="required|password|checkPasswd"
                        autocomplete="off" class="layui-input" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">确认密码：</label>
                <div class="layui-input-inline">
                    <input type="password" name="rePassword" placeholder="请再次输入密码" lay-verify="required|password|checkPasswd"
                        autocomplete="off" class="layui-input" />
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button id="submit-Btn" class="layui-btn" lay-submit
                        lay-filter="formSubmit">确定</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>
    </div>
    <div class="cr-footer"> 
        © 东软CRM － 以极简方式管理客户和业务过程
    </div>
	


	<script src="layui/layui.js"></script>
	<script type="text/javascript">
		layui.use([ 'form' ], function() {
			var form = layui.form;
			var layer = layui.layer;
			var $ = layui.jquery;
	
			$("#code-Btn").click(function() {
				//检验邮箱的正则表达式
				var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
				var email = $("#email-input").val();
				//先检验数据的合法性
				if (email == null || email == '') {
					layer.msg("邮箱不可以为空！", {
						icon : 5,
						time : 1000,
						end : function() {}
					});
				} else if (re.test(email) == false) {
					layer.msg("邮箱格式不合法！", {
						icon : 5,
						time : 1000,
						end : function() {}
					});
				} else {
					getCode(email);
				}
			});
	
			//获取验证码
			function getCode(email) {
				var load = null;
				$.ajax({
					url : '${pageContext.request.contextPath}/user/getForgotPasswdCode',
					type : 'POST',
					aysnc : false,
					data : {
						'email' : email
					},
					dataType : 'json',
					beforeSend : function() {
						load = layer.load(2);
					},
					success : function(data) {
						if (data.success) {
							layer.msg(data.msg, {
								icon : 6,
								time : 1000,
								end : function() {}
							});
	
							var seconds = 60;
							$("#email-input").attr('readonly', 'readonly');
							$("#code-Btn").attr('disabled', 'true');
							$("#code-Btn").addClass('layui-btn-disabled');
							$("#code-Btn").text("");
							$("#code-Btn").text(seconds + 's后可重新获取');
							var time = setInterval(function() {
								seconds--;
								$("#code-Btn").text(seconds + 's后可重新获取');
								if (seconds == 0) {
									clearInterval(time);
									$("#code-Btn").removeAttr('disabled');
									$("#code-Btn").removeClass('layui-btn-disabled');
									$("#code-Btn").text('获取验证码');
									$("#email-input").removeAttr('readonly');
								}
							}, 1000);
	
						} else {
							layer.msg(data.msg, {
								icon : 5,
								time : 1000,
								end : function() {}
							})
						}
					},
					error : function(textStatus) {
						console.log(textStatus);
						layer.msg('服务器出小差了，请稍等...', {
							icon : 5,
							time : 1000,
							end : function() {}
						})
					},
					complete : function() {
						layer.close(load);
					}
				});
			}
	
	
			form.on('submit(formSubmit)', function(data) {
	            var formdata = data.field; 
	            console.log(formdata);
				var loadanima = null;
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/user/resetPasswd",
					data : formdata,
					async : false,
					//请求前执行
					beforeSend : function() {
						//显示加载动画
						loadanima = layer.load(2);
					},
					//请求完成执行，无论请求是否成功
					complete : function() {
						//关闭加载动画
						layer.close(loadanima);
					},
					success : function(data) {
						if (data.success) {
							layer.msg(data.msg, {
								icon : 6,
								time : 1000,
								end : function() {
									location.href = 'pages/login.jsp';
								}
							})
						} else {
							layer.msg(data.msg, {
								icon : 5,
								time : 1000,
								end : function() {}
							})
						}
					}
				});
				return false;
			});
	
			form.verify({
				//自定义验证
				checkPasswd : function(value, item) {
					if (item.name == 'password' && $("input[name='rePassword']").val() != value) {
						return '两次输入密码不一致'
					}
					if (item.name == 'rePassword' && $("input[name='password']").val() != value) {
						return '两次输入密码不一致'
					}
				},
				//我们既支持上述函数式的方式，也支持下述数组的形式
				//数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
				password : [
					/^[\S]{6,12}$/
					, '密码必须6到12位，且不能出现空格'
				]
			})
	
		});
	</script>
</body>
</html>
