<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>字典子项</title>
    <link rel="stylesheet" href="../layui/css/layui.css">
    <script src="../layui/layui.js"></script>
    <script type="text/javascript" src="../js/myutil.js"></script>
</head>
<body>
<div class="layui-upload">
  <button type="button" class="layui-btn layui-btn-normal" id="fileupload">选择文件</button>
  <span id="upload-filename" class="layui-inline" style="color: #999999"></span>
  <button type="button" class="layui-btn layui-btn-disabled" id="upload-btn">开始上传</button>
</div>
<script>
  layui.use(['upload'], function() {
	  var upload = layui.upload;
	  var $ = layui.$;
	  var layer = layui.layer;
	  //拖拽上传
	  var uploadindex = 0;
	  upload.render({
	    elem: '#fileupload'
	    ,url: '${pageContext.request.contextPath}/customer/upload'
	    ,accept:'file'     //允许所有类型文件上传
	    ,size:10240        //上传大小限制为10M
	    ,auto:false        //不自动上传
	    ,number:1
	    ,bindAction:'#upload-btn'
	    ,choose:function(obj){
	    	obj.preview(function(index, file, result){
	    		$('#upload-filename').text(file.name);
	    	});
	    	$('#upload-btn').removeClass('layui-btn-disabled');
	    }
	    ,before:function(){
	    	uploadindex = layer.load(2);
	    }
	    ,done: function(res){
	    	$('#upload-btn').addClass('layui-btn-disabled');
	    	layer.close(uploadindex);
	    	layer.msg("上传完成");
	    }
	  });    
  });
</script>
</body>
</html>