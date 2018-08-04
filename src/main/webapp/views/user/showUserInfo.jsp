<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="../../layui/css/layui.css">
<script src="../../layui/layui.js"></script>
<script src="../../js/myutil.js"></script>
<style type="text/css">
.layui-form-item{
    margin-top: 16px;
    margin-left:60px;
    font-size: 16px;
}
.layui-input{
    border: none;
}
</style>
</head>
<body>

        
        <form class="layui-form"  lay-filter="myform">
            <div style="width: 99.8%;height:35px;line-height:35px;border: 1px #f1f1f1 solid;background-color: #f5f5f5;" ><span style="margin-left: 28px;">基本资料</span></div>
                    
            <div class="layui-form-item" style="margin-top: 15px;">  
                <label class="layui-form-label">用户名：</label>
                <div class="layui-input-inline" style="width: 320px">
                    <input type="text" class="layui-input" name="account" readonly="readonly"  />
                </div>
            </div>
            
            <hr>
            
            <div class="layui-form-item">  
                <label class="layui-form-label">真实姓名：</label>
                <div class="layui-input-inline" style="width: 250px;">
                    <input type="text" class="layui-input" name="realName" readonly="readonly" />
                </div>
                <label class="layui-form-label" style="margin-left: 150px;">手机号码：</label>
                <div class="layui-input-inline" style="width: 250px;">
                    <input type="text" name="phoneNumber" class="layui-input" readonly="readonly" />
                </div>
            </div>
            
            <hr>
            
            <div class="layui-form-item">  
                <label class="layui-form-label">电子邮箱：</label>
                <div class="layui-input-inline" style="width: 250px;">
                    <input type="text" name="email" class="layui-input" readonly="readonly"  />
                </div>
                <label class="layui-form-label" style="margin-left: 150px;">职位：</label>
                <div class="layui-input-inline" style="width: 250px;">
                    <input type="text" name="roleName" class="layui-input" readonly="readonly" />
                </div>
            </div>         
      
            <hr>
            
            <div class="layui-form-item">  
                <label class="layui-form-label">创建时间：</label>
                <div class="layui-input-inline" style="width: 250px;">
                    <input type="text" name="createTime" class="layui-input" readonly="readonly"  />
                </div>
                <label class="layui-form-label" style="width:130px;margin-left: 100px;">上一次登陆时间：</label>
                <div class="layui-input-inline" style="width: 250px;">
                    <input type="text" name="lastLoginTime" class="layui-input" readonly="readonly" />
                </div>
            </div>
                    
            
		</form>
</body>

<script>
	layui.use([ 'form' ], function() {
		var form = layui.form;
		var layer = layui.layer;
		var $ = layui.jquery;	
		
		//获取通过连接传过来的用户id值
		var id = getParm().id;
		
		if(id != null){
	      //如果id不为空，则加载相应的用户信息
		  loadUserInfo();
		}else{
		  layer.msg("用户编号为空，读取用户信息失败！",{icon: 5,time:1000,end:function(){}});
		}
		
		function loadUserInfo(){
			var load = layer.load(2);
	        $.ajax({
	          url : '${pageContext.request.contextPath}/user/findUserById',
	          type : 'POST',
	          data : {
	              'id' : id
	          },
	          dataType : 'json',
	          success : function(data){
	              console.log(data);
	              if(data.success){
	                  form.val('myform',data.user);
	                  $("input[name='roleName']").val(data.user.role.name)
	              }
	              layer.close(load);
	          }
	        });
		}
		
		
		
		
		
		
	});
</script>
</html>