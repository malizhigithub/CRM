<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	/* 两种方法解决网站相对路径问题
		一是使用base标签
	*/
	String basePath = request.getContextPath() + "/";
%>
<html>
<head>
<meta charset="utf-8">
<title>产品页</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>Insert title here</title>
<base href="<%=basePath%>">
<link rel="stylesheet" href="layui/css/layui.css">
</head>
<body>

	<form class="layui-form" lay-filter="boardFilter">
	<div  style="width:100%;margin-top: 20px;">
	    <blockquote class="layui-elem-quote quoteBox">
		<label style="margin-left: 20px;" style="" class="layui-label">ID：</label>

		<div style="width: 120px;" class="layui-inline">
			<input type="text" name="productId" placeholder="请输入ID"
				autocomplete="off" class="layui-input">
		</div>

		<label style="margin-left: 20px;" style="" class="layui-label">产品名称：</label>

		<div style="width: 120px;" class="layui-inline">
			<input type="text" name="productName" placeholder="请输入名称"
				autocomplete="off" class="layui-input">
		</div>

		<label style="margin-left: 20px;" class="layui-label">产地：</label>
		
		<div style="width: 120px;" class="layui-inline">
			<input type="text" name="productLocation" placeholder="请输入产地"
				autocomplete="off" class="layui-input">
		</div>
		
		<label style="margin-left: 20px;" class="layui-label">分类：</label>

		<div style="width: 120px;" class="layui-inline">
			<select name="categoryId" style="width:100%;" lay lay-verify="required">
				<option value=""></option>
			<!-- 	<option value="1">电脑</option>
				<option value="2">硬件</option>
				<option value="3">软件</option>
				<option value="4">电子</option> -->
			</select>
		</div>
		
		<button style="margin-left: 20px;" id="searchBtn" class="layui-btn"
			type="button" lay-filter="productTable">搜索</button>
		<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</blockquote>
	</div>
	</form>

	<table class="layui-hide" id="productTable" lay-filter="productTalbe"></table>

	<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
	<script src="layui/layui.all.js"></script>
	
	<script>
/* layui.use('table', function(){ */
  var table = layui.table;
  
  var productTable = table.render({
    elem: '#productTable'
    ,url:'findProduct'
    ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
    ,cols: [[
      {field:'id',  title: 'ID', sort: true, align: 'center'}
      ,{field:'name',  title: '产品名称', align: 'center'}
      ,{field:'location',  title: '产地', sort: true, align: 'center'}
      ,{field:'version',  title: '型号', align: 'center'}
      ,{field:'unit', title: '单位', align: 'center'} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
      ,{field:'price', title: '价格', sort: true, align: 'center'}
      ,{field:'repertory', title: '库存',sort: true, align: 'center'}
      ,{field:'remarks', title: '备注', align: 'center'}
      ,{field:'categoryName', title: '分类', sort: true, align: 'center', templet: function(data){
    	  return data.category.name;
      }}
    ]]
  ,page: true
  });
  
	//搜索功能
	var active = {
		reload : function() {
			var productId = $("input[name='productId']");
			var productName = $("input[name='productName']");
			var productLocation = $("input[name='productLocation']");
			var categoryId = $("select[name='categoryId']");
			//执行重载
			productTable.reload( {
				where : {
					"id" : productId.val(),
					"name" : productName.val(),
					"location" : productLocation.val(),
					"categoryId" : categoryId.val()
				}
			});
		}
	};
	$('#searchBtn').on('click', function() {
		active['reload'] ? active['reload'].call(this) : '';
	});
	
	$(function(){
		$.ajax({
			
			url:"findCategory",
			type:"POST",
			dataType:"json",
			beforeSend:function(){
    			loading = layer.load();
    		},
			success:function(data){
				console.log($("select[name='categoryId']"));
				var html = '';
				var c = data.categorys;
				 for(i=0;i<c.length;i++){
					 html += "<option value='"+c[i].id+"'>"+c[i].name+"</option>";
					 }
				$("select[name='categoryId']").append(html);//往下拉菜单里添加元素 
				  layui.form.render('select','boardFilter');
			},
    		error:function(){
    			layer.msg("服务器开销差了!");
    		},
    		complete:function(){
    			layer.close(loading);
    		}
			
		});
	});
  
/* }); */
</script>
</body>
</html>