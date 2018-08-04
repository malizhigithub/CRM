<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品选择</title>
	<link rel="stylesheet" href="../../layui/css/layui.css">
	<script src="../../js/myutil.js"></script>
	<script src="../../layui/layui.js"></script>
</head>
<body >
<div style="width: 96%;margin-left: 2%;">
  
  <form class="layui-form">
    <div class="layui-form-item">
        <label class="layui-form-label">商品类别：</label>
        <div class="layui-input-block">
            <select name="categoryId" lay-filter="categoryId">
                <option value="">--正在加载数据--</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">商品类别：</label>
        <div class="layui-input-block">
            <select name="product" lay-filter="product">
                <option value="">--请选择产品分类--</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" lay-submit class="layui-btn">确定</button>
        </div>
    </div>  
  </form>
    
</div>
	
<script type="text/javascript">

layui.use(['form'],function(){
	var form = layui.form;
	var layer = layui.layer;
	var $ = layui.$;
	//设置父窗口的jQuery
	var parent$ = window.parent.layui.jquery;
	
	
	$.post("${pageContext.request.contextPath}/findCategory",{},function(data){
		var categorys = data.categorys;
		var str = '<option value="">--请选择产品分类--</option>';
		for(var i=0;i<categorys.length;i++){
			str += '<option value="' + categorys[i].id + '">' + categorys[i].name + '</option>';
		}
		$('select[name=categoryId]').html(str);
		form.render('select');
	})
	
	form.on('select(categoryId)',function(data){
		if(data.value == ''){
			$('select[name=product]').html('<option value="">--请选择产品分类--</option>');
			return;
		}
		
		$('select[name=product]').html('<option value="">--正在加载数据--</option>');
		
		$.post('${pageContext.request.contextPath}/findProduct',{'limit':10000,'categoryId':data.value},function(data){
			var products = data.data;
			var str = '<option value="">--请选择产品--</option>';
			for(var i=0;i<products.length;i++){
	            str += '<option value="' + products[i].id + '">' + products[i].name + '</option>';
	        }
	        $('select[name=product]').html(str);
	        form.render('select');
		});
	});
	
	form.on('submit',function(data){
		
		if($('select[name=categoryId]').val() == ''){
			layer.msg('请选择商品分类');
			return false;
		}
		if($('select[name=product]').val()==''){
			layer.msg('请选择产品');
			return false;
		}
		
		var id = $('select[name=product]').val();
		var name = $('select[name=product] option[value=' + id + ']').text();
		
		parent$('#choose-product-name').text(name);
		parent$('input[name=productId]').val(id);
		
		//关闭当前弹出层
        var thisindex = parent.layer.getFrameIndex(window.name);
        parent.layer.close(thisindex);
		
        return false;
	});
	
	
	
});
</script>
		
</body>
</html>