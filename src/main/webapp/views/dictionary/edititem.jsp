<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>字典子项</title>
	<link rel="stylesheet" href="../../layui/css/layui.css">
	<script src="../../js/myutil.js"></script>
	<script src="../../layui/layui.js"></script>
</head>
<body>


<form class="layui-form" style="width: 90%" lay-filter="item-form"> 
	
	<input type="hidden" value="0" name="id">
	<input type="hidden" value="0" name="typeId">

  <div class="layui-form-item"  style="margin-top: 15px;" >
    <label class="layui-form-label">名称</label>
    <div class="layui-input-inline">
      <input type="text" name="name" placeholder="请输入名称" lay-verify="required" autocomplete="off" class="layui-input">
    </div>
    
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">状态</label>
    <div class="layui-input-inline">
      <select name="status" lay-filter="status">
        <option value="0">锁定</option>
        <option value="1">解锁</option>
      </select>
    </div>	
    
	<div class="layui-form-item" style="margin-top: 40px;">
		<div class="bottom-btn" align="center">
			<button class="layui-btn" lay-submit lay-filter="item-submit">立即提交</button>
		</div>
	</div>    
    
</form>

	
<script type="text/javascript">
layui.use('form',function(){
	var form = layui.form;
	var layer = layui.layer;
	var $ = layui.$;
	
	var parm = getParm();


	//监听表单提交按钮
	form.on('submit(item-submit)', function(data){
		var btn = data.elem;         //获取点击的按钮
		btn.className += " layui-btn-disabled"; //给按钮添加禁用样式
		
		var formdata = data.field;//读取form表单中的数据 ,键值对形式
		formdata.typeId = parm.typeId;
		//显示加载动画
		var index = top.layer.msg('数据提交中，请稍候', {
			icon : 16,
			time : false,
			shade : 0.8
		});
		
		$.ajax({
			type: "POST",
			url: '${pageContext.request.contextPath}/dictionary/item/admin/add',
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