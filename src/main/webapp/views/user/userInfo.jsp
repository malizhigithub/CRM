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
            <h2 style="display: inline-block;">基本资料</h2><span style="font-size:10px; margin-left:10px; color:#999999">（<span style="color: red;">*</span>为可编辑项）</span>
        </div>
        <div style="font-size:10px;margin-left: 50px;margin-top: 20px;">
            <span style="color:#999999">为了让公司更好地进行管理，以下信息将公开地显示在个人资料页，请确保信息的准确性</span>
        </div>
		<form class="layui-form" style="margin-left: 80px;margin-top: 40px;" lay-filter="myform">
			<input type="hidden" name="id" value="${user.id }" style="width: 250px;" />
			<div class="layui-form-item" style="margin-top: 15px;">
				<label class="layui-form-label">账号：</label>
				<div class="layui-input-inline">
					<input type="text" name="account" 
						readonly="readonly" class="layui-input" />
				</div>	
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label"><span style="color: red;">*</span>真实姓名：</label>
				<div class="layui-input-inline">
					<input type="text" name="realName" lay-verify="required" 
						placeholder="请输入真实姓名" autocomplete="off" class="layui-input" />
				</div>
			</div>
			<div class="layui-form-item">
                <label class="layui-form-label"><span style="color: red;">*</span>手机号码：</label>
                <div class="layui-input-inline">
                    <input type="text" name="phoneNumber" lay-verify="required|phone" 
                        placeholder="请输入手机号码"  class="layui-input" />
                </div>
            </div>
            
            <div class="layui-form-item">
                <label class="layui-form-label"><span style="color: red;">*</span>电子邮箱：</label>
                <div class="layui-input-inline">
                    <input type="text" name="email" lay-verify="required|email|checkEmail" 
                        placeholder="请输入电子邮箱" class="layui-input" />
                </div>
            </div>
            
            
			<div class="layui-form-item">
                <label class="layui-form-label">职位：</label>
                <div class="layui-input-inline">
                    <input type="text" name="roleName" 
                        readonly="readonly" class="layui-input" />
                </div>
            </div>
            
            <div class="layui-form-item">
                <label class="layui-form-label">创建时间：</label>
                <div class="layui-input-inline">
                    <input type="text" name="createTime" 
                        readonly="readonly" class="layui-input" />
                </div>
            </div>
            
            <div class="layui-form-item">
                <label class="layui-form-label">上一次登陆时间：</label>
                <div class="layui-input-inline">
                    <input type="text" name="lastLoginTime" 
                        readonly="readonly" class="layui-input" />
                </div>
            </div>
            
			<div class="layui-form-item">
			    <label class="layui-form-label"></label>
                <div class="layui-input-block">
                    <shiro:hasPermission name="13002">
                        <button id="submit-Btn" class="layui-btn" style="width:100px;" lay-submit lay-filter="formSubmit">保存</button>
                    </shiro:hasPermission>
                    <button id="reset-Btn" class="layui-btn layui-btn-primary">重置</button>
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
		var load = layer.load(2);
		$.ajax({
		  url : '${pageContext.request.contextPath}/user/findCurrentUser',
		  type : 'POST',
		  data : {
		      id : $("input[name='id']").val()
		  },
		  dataType : 'json',
		  success : function(data){
		      console.log(data);
		      if(data.success){
		          user = data.data;
		          form.val('myform',user);
		          $("input[name='roleName']").val(user.role.name);
		      }
		      else{
		          layer.msg(data.msg);
		      }
		      layer.close(load);
		  }
		});
		
		$("#reset-Btn").click(function(){
            $("input[name='realName']").val(user.realName);
            $("input[name='email']").val(user.email);
            $("input[name='phoneNumber']").val(user.phoneNumber);
        });
        
		form.on('submit(formSubmit)', function(data) {
			var loadanima = null;
			$.ajax({
				type : 'POST',
				url : "${pageContext.request.contextPath}/user/editUser",
			    data : {
			    	id : $("input[name='id']").val(),
			    	readName : $("input[name='realName']").val(),
			    	email : $("input[name='email']").val(),
			    	phoneNumber : $("input[name='phoneNumber']").val()
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
			    		layer.msg(data.msg,{icon: 6,time:1000,end:function(){}});
			    	}else{
			    		layer.msg(data.msg,{icon: 5,time:1000,end:function(){}});
			    		$("#reset-Btn").click();
			    	}
			    }				
			});
			return false;
		});
		
		
	});
</script>
</html>