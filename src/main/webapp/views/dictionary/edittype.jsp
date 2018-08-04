<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>字典添加</title>
	<link rel="stylesheet" href="../../layui/css/layui.css">
	<script src="../../layui/layui.js"></script>
</head>
<body>
	<!-- 编辑添加框 -->
	<form  class="layui-form" lay-filter="dictionary-form" style="width: 90%">
	
	  <div class="layui-form-item layui-hide">
	    <label class="layui-form-label">ID</label>
	    <div class="layui-input-inline">
	      <input type="text" name="id" value="0" readonly="readonly"  lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  
	  <div class="layui-form-item" style="margin-top: 20px">
	    <label class="layui-form-label">字典名称</label>
	    <div class="layui-input-inline">
	      <input type="text" name="name"  lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button type="button" class="layui-btn" lay-submit lay-filter="type-submit">保存</button>
	    </div>
	  </div> 
	</form>

	
<script type="text/javascript">
	layui.use('form',function(){
	var form = layui.form;
	var layer = layui.layer;
	var $ = layui.$;
	// 截取url 参数值（这里默认一个参数id）
	var id = location.search.substr(1).split("=")[1];
	var url = '${pageContext.request.contextPath}/dictionary/admin/add';
	
	if(id > 0){
		//更新操作
		//检测字典是否存在
		$.ajax({
			type: "POST",
			url: '${pageContext.request.contextPath}/dictionary/find',
			data: {'id':id},
			dataType: "json",
			success: function(data){
				if(data.success){//查找成功
					//更改ajax提交地址
					url = '${pageContext.request.contextPath}/dictionary/admin/update';
					//填写form表单内容
					form.val('dictionary-form',{
						'id':data.data.id,
						'name':data.data.name
					});
					form.render();
		 		}else{
		 			//查找失败,
		 			top.layer.msg('字典不存在...');
		 		}
			},
			error:function(){
				top.layer.msg("服务器开小差了，请稍后再试...");
			}
		});
	}
	
	
	
	
			
	//监听表单提交按钮
	form.on('submit(type-submit)', function(data){
		var btn = data.elem;         //获取点击的按钮
		btn.className += " layui-btn-disabled"; //给按钮添加禁用样式
		
		var formdata = data.field;//读取form表单中的数据 ,键值对形式
		
		//显示加载动画
		var index = top.layer.msg('数据提交中，请稍候', {
			icon : 16,
			time : false,
			shade : 0.8
		});
		
		$.ajax({
			type: "POST",
			url: url,
			data: formdata,
			dataType: "json",
			success: function(data){
				top.layer.msg(data.msg);	//使用top显示
				if(data.success){//成功
					//关闭当前弹出层
					var thisindex = parent.layer.getFrameIndex(window.name);
					parent.layer.close(thisindex);
		 		}
			},
			error:function(){
				top.layer.msg("服务器开小差了，请稍后再试...");
			},
			complete:function(){//请求完成执行，无论请求是否成功
				btn.className = btn.className.replace("layui-btn-disabled","");  //去除禁用属性
				top.layer.close(index);		//关闭加载动画
			}
	   });
	   return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
 	});
	
});
</script>
	
</body>
</html>