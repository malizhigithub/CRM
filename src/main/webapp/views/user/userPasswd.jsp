<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<style type="text/css">
.layui-form-item{
    margin-top: 30px;
}
.layui-form-item input{
    width:300px;
}
.layui-form-label {
    width:130px;
}


</style>
</head>
<body>

        <div style="margin-left: 50px;margin-top: 25px;">
            <h2 style="display: inline-block;">修改密码</h2><span style="font-size:10px; margin-left:10px; color:#999999">（<span style="color: red;">*</span>为必须填写项）</span>
        </div>
        <div style="font-size:10px;margin-left: 50px;margin-top: 20px;">
            <span style="color:#999999">请妥善保管好您的密码</span>
        </div>
		<form class="layui-form" style="margin-left: 80px;margin-top: 40px;" lay-filter="myform">
			<input type="hidden" name="id" value="${user.id }" style="width: 250px;" />
			<div class="layui-form-item" style="margin-top: 15px;">
				<label class="layui-form-label"><span style="color: red;">*</span>原密码：</label>
				<div class="layui-input-inline">
					<input type="password" name="oldPassword" placeholder="请输入原密码" lay-verify="required" 
						 class="layui-input" />
				</div>	
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label"><span style="color: red;">*</span>新密码：</label>
				<div class="layui-input-inline">
					<input type="password" name="password" lay-verify="checkPasswd1|password" 
						placeholder="请输入新密码" autocomplete="off" class="layui-input" />
				</div>
			</div>
			<div class="layui-form-item">
                <label class="layui-form-label"><span style="color: red;">*</span>确认密码：</label>
                <div class="layui-input-inline">
                    <input type="password" name="rePassword" lay-verify="checkPasswd1|password" 
                        placeholder="请再次输入密码"  class="layui-input" />
                </div>
            </div>
                       
			<div class="layui-form-item">
			    <label class="layui-form-label"></label>
                <div class="layui-input-block">
                    <shiro:hasPermission name="14001">
                        <button id="submit-Btn" class="layui-btn" style="width:100px;" lay-submit lay-filter="formSubmit">保存</button>
                    </shiro:hasPermission>
                    <button id="reset-Btn" type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
		</form>
</body>

<script>
	layui.use([ 'form' ], function() {
		var form = layui.form;
		var layer = layui.layer;
		var $ = layui.jquery;	
		var user;
		
		

        
		form.on('submit(formSubmit)', function(data) {
			var loadanima = null;
			
			$.ajax({
				type : 'POST',
				url : "${pageContext.request.contextPath}/user/editUserPasswd",
			    data : {
			    	oldPassword : $("input[name='oldPassword']").val(),
			    	password : $("input[name='password']").val(),
			    	id : $("input[name='id']").val()
			    },
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
			    success : function(data){
			    	if(data.success){
			    		layer.msg(data.msg,{icon: 6,time:1000,end:function(){}})
			    		$("#reset-Btn").click();
			    	}else{
			    		layer.msg(data.msg,{icon: 5,time:1000,end:function(){}})
			    	}
			    }				
			});
			return false;
		});
		
		form.verify({
            //自定义验证
            checkPasswd1 : function(value, item) {
                if (value == '') {
                    return '必填项不能为空';
                }
                if (item.name == 'password' && $("input[name='rePassword']").val() != value) {
                    return '两次输入密码不一致';
                }
                if (item.name == 'rePassword' && $("input[name='password']").val() != value) {
                    return '两次输入密码不一致';
                }if(item.name == 'password' && $("input[name='oldPassword']").val() == value) {
			        return '新密码不可以与旧密码一致！';
			    }
            },
            //自定义验证
            checkPasswd2 : function(value, item) {
                if (item.name == 'password' && $("input[name='rePassword']").val() != value) {
                    return '两次输入密码不一致'
                }
                if (item.name == 'rePassword' && $("input[name='password']").val() != value) {
                    return '两次输入密码不一致'
                }
            },
            //用户名验证
            username: function(value, item){ //value：表单的值、item：表单的DOM对象
                if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                  return '用户名不能有特殊字符';
                }
                if(/(^\_)|(\__)|(\_+$)/.test(value)){
                  return '用户名首尾不能出现下划线\'_\'';
                }
                if(/^\d+\d+\d$/.test(value)){
                  return '用户名不能全为数字';
                }
              },    
            //我们既支持上述函数式的方式，也支持下述数组的形式
            //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
            password: [
            /^[\S]{6,12}$/
              ,'密码必须6到12位，且不能出现空格'
            ] 
        })
		
		
	});
</script>
</html>