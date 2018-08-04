<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>东软客户关系管理系统</title>
	<!-- build:css -->
	<link rel="stylesheet" href="layui/css/layui.css">
	<link rel="stylesheet" href="layui/css/kitadmin.css">
	<link rel="stylesheet" href="layui/css/nprogress.css">

	<!-- endbuild -->
	
</head>

<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<!-- header -->
		<div class="layui-header">
			<div class="layui-logo">
				<div class="layui-logo-toggle" kit-toggle="side" data-toggle="on">
					<i class="layui-icon">&#xe65a;</i>
				</div>
				<div class="layui-logo-brand" style="width: 174px;">
					<a href="#/">客户关系管理系统</a>
				</div>
			</div>
			<!-- <div class="layui-layout-left">
				<div class="kit-search">
					<form action="/">
						<input type="text" name="keyword" class="kit-search-input" placeholder="关键字..." />
						<button class="kit-search-btn" title="搜索" type="submit">
							<i class="layui-icon">&#xe615;</i>
						</button>
					</form>
				</div>
			</div> -->
			<div class="layui-layout-right">
				<ul class="kit-nav" lay-filter="header_right">
					<li class="kit-item">
						<a href="javascript:;">
							<i class="layui-icon">&#xe607;</i>
							<span>帮助</span>
						</a>
					</li>
					<li class="kit-item" id="ccleft">
						<a href="javascript:;">
							<i class="layui-icon">&#xe60e;</i>
						</a>
					</li>
					<li class="kit-item" id="cc">
						<a href="javascript:;">
							<i class="layui-icon">&#xe64c;</i>
						</a>
					</li>
					<li class="kit-item">
						<a href="javascript:;">
							<span>
								${user.account }
							</span>
						</a>
						<ul class="kit-nav-child kit-nav-right">
							<li class="kit-item">
								<a href="#/userInfo">
									<i class="layui-icon">&#xe612;</i>
									<span>个人中心</span>
								</a>
							</li>
							<li class="kit-item" kit-target="setting">
								<a href="javascript:;">
									<i class="layui-icon">&#xe614;</i>
									<span>设置</span>
								</a>
							</li>
							<li class="kit-nav-line"></li>
							<li class="kit-item">
								<a href="user/logout">
									<i class="layui-icon">&#x1006;</i>
									<span>注销</span>
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		<!-- silds -->
		<div class="layui-side" kit-side="true">
			<div class="layui-side-scroll">
				<div id="menu-box">
					<ul class="kit-menu">
				
					</ul>
				</div>
			</div>
		</div>
		<!-- main -->
		<div class="layui-body" kit-body="true">
			<router-view></router-view>
		</div>
		<!-- footer -->
		<div class="layui-footer" kit-footer="true">
			2017 © kit.zhengjinfan.cn MIT license
		</div>
	</div>
	
	
	<!-- build:js -->
	<script src="layui/polyfill.min.js"></script>
	<script src="layui/layui.js"></script>
	
	<script type="text/javascript" src="https://cdn.bootcss.com/echarts/4.1.0.rc2/echarts.js"></script>
	
	<!-- <script src="layui/kitadmin.js"></script> -->
	<!-- endbuild -->
	<!-- build:use -->
	<script>
		layui.config({
			base: 'js/'
			
		}).use('index');
	</script>
	<script type="text/javascript">
	   layui.use([ 'element'], function() {
		   var element = layui.element;
		   var $ = layui.jquery;
		   $.ajax({
			  url : '${pageContext.request.contextPath}/role/getRolePermissionMenu',
			  type : 'POST',
			  async : false,
			  dataType : 'json',
			  success : function(data){
				  var html = '';
				  routeOpts.routes = [];
				  $("#menu-box").html('');
				  //构建树形菜单
				  if(data.permission.length > 0){
					  html += addMenu(data.permission,"<ul class='kit-menu'>");
				  }
				  $("#menu-box").html(html);
				  console.log(routeOpts);
				  element.render('nav');
			  }
		   });
		   
		   
		   //添加菜单
		   function addMenu(menuArray,ulClass){
			   var html = '';
			   html += ulClass;
			   //用于添加首页菜单
			   var flag = 0;
			   //根据class来判断是否为顶级菜单
			   if(ulClass == "<ul class='kit-menu'>"){
				   flag = 1;
			   }
			   for(var i = 0 ; i < menuArray.length ; i++){
				   
				   if(flag == 1){
                       var index = {'path': '/', 'component':'views/app.jsp','name':'首页'};
                       routeOpts.routes.push(index);
                       html += "<li class='kit-menu-item'>";
                       html += "<a href='#/'>";
                       html += "<span>首页</span>";
                       html += "</a>";
                       html += "</li>";
                       flag = 0;
                   }
				   
				   var url_split = '';
				   
				   if(menuArray[i].childPermission.length <= 0 ){
					   var url_str =  menuArray[i].url.split('/');
					   url_split = url_str[url_str.length-1].split('.jsp')[0];
					   var route = {'path': '/'+ url_split , 'component':menuArray[i].url ,'name':menuArray[i].title};
					   routeOpts.routes.push(route);
				   }
				    
				   html += "<li class='kit-menu-item'>";
				   if(menuArray[i].childPermission.length > 0 ){
					   html += "<a href='javascript:;'>";
				   }else{
					   html += "<a href='#/"+url_split+"'>";
				   }
				   html += "<span>"+menuArray[i].title+"</span>";
				   html += "</a>";
				   
				   if(menuArray[i].childPermission.length > 0 ){
					   html += addMenu(menuArray[i].childPermission,"<ul class='kit-menu-child kit-menu-child layui-anim'>");
				   }
				   html += "</li>";
			   }
			   html += "</ul>";
			   return html;
		   }
		   
	   });
	</script>
	<!-- endbuild -->
</body>

</html>