<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@taglib prefix="eShiro" tagdir="/WEB-INF/tags" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  	
</head>

<style>
#queryForm{
	margin-top:10px;  
}
</style>

<body>
<div class="layui-container">  
	<div class="layui-row">
		<div class="layui-col-md12">
      
			<!-- 搜索表单 -->
			<div class="layui-form-item" id="queryForm">
			  <div class="layui-inline">
			    <eShiro:hasAnyPermissions name="2001,2002">
				    <label class="layui-form-label">日期范围</label>
	                <div class="layui-input-inline" style="width: 200px;">
	                  <input type="date" name="timeMin" placeholder="" autocomplete="off" class="layui-input">
	                </div>
	                <div class="layui-form-mid">-</div>
	                <div class="layui-input-inline" style="width: 200px;">
	                  <input type="date" name="timeMax" placeholder="" autocomplete="off" class="layui-input">
	                </div>
			    </eShiro:hasAnyPermissions>
			    
			    
			    <div class="layui-input-inline" style="width: 500px;">
			        <shiro:hasPermission name="2001">
			             <button class="layui-btn" lay-submit lay-filter="formDemo" id="querySubmit">搜索</button>
			        </shiro:hasPermission>
			    	<button class="layui-btn" class="layui-btn layui-btn-primary" id="reset">重置</button>
			    	<shiro:hasPermission name="2002">
			    	    <button class="layui-btn" class="layui-btn layui-btn-danger" id="delete">删除日志(删除指定时间)</button>
                        <button class="layui-btn" class="layui-btn layui-btn-danger" id="trash">清空日志</button>
			    	</shiro:hasPermission>
			    	
                    
			    </div>
			  </div>
			</div><!-- 搜索表单 -->
    	</div>
	</div>
</div>

<!-- 数据表格 -->
<table id="logging"></table>

<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script> 
</body>
<script>
layui.use(['table','layer'], function(){
   var loading = layer.load(); //动画index
   
   var logtable = layui.table.render({
  	  elem: '#logging', //指定原始表格元素选择器（推荐id选择器）
	  url: '${pageContext.request.contextPath}/log/findLogging',
	 
	  cols:[[
		  {field: "eventId", title: "日志ID"},
		  {field: "timestamp", title: "操作时间"},
		  {field: "formattedMessage" , title: "操作内容", width: "70%"},
	  ]],
	  edit:"text",  //单元格编辑类型（默认不开启）目前只支持：text（输入框）
	  limit: 20,
	  limits: [10, 20, 30, 40, 50, 60, 70, 80, 90],
	  loading: true, //分页加载样式
	  page: true, //是否开启分页
	  id : "logTable",
	  done: function(res, curr, count){
		//如果是异步请求数据方式，res即为你接口返回的信息。
	    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
	    console.log(res);
	    
	    //得到当前页码
	    console.log(curr); 
	    
	    //得到数据总量
	    console.log(count);
	    layer.close(loading);//关闭动画
	  }
  });
    
  $("#querySubmit").click(function(){
  		logtable.reload({
  			page: {
  	          curr: 1 //重新从第 1 页开始
  	        },
  			 where: {
			  	"timeMax" : $("input[name=timeMax]").val(),
			  	"timeMin" : $("input[name=timeMin]").val(),
			  },
  		});
  });
  
  /**
    删除指定时间内的日志
  */
  $("button#delete").click(function(){
	  var timeMin = $("input[name=timeMin]").val();
	  var timeMax = $("input[name=timeMax]").val();
	  if(timeMin == '' && timeMax == ''){
		  layer.msg("至少选择一天的日期");
		  return false;
	  }
	  if(timeMin != '' && timeMax != ''){
		  layui.layer.confirm("确认删除 "+timeMin+" - "+timeMax+" 之间的日志信息吗？", function(index){
                deleteLogging(timeMin, timeMax);
				layer.close(index);  //取消  
		  })
	  }
	  if(timeMin != ''){
		  layui.layer.confirm("确认删除 "+timeMin+" 之后的日志信息吗？", function(index){
              deleteLogging(timeMin, timeMax);
              layer.close(index);  //取消  
		  })
	  }
	  if(timeMax != ''){
		  layui.layer.confirm("确认删除 "+ timeMax + " 之前的日志信息吗?", function(index){
              deleteLogging(timeMin, timeMax);
              layer.close(index);  //取消  
		  })
	  }
  });
  
  /**
        删除日志
  */
  function deleteLogging( timeMin, timeMax){
	  loading = layer.load();
	   //确认删除
	  var data = {
		   "timeMin" : timeMin,
		   "timeMax" : timeMax,
	   };
      $.ajax({
          url:"${pageContext.request.contextPath}/log/delLogging",
          type:"post",
          data:data,
          dataType:"json",
          success:function(data){
              if(data && data.code == 0){
                  //删除成功 重新加载表格数据
                  layui.layer.open({
                      content : '清空完成!',
                      btn: ['确认'],
                      yes: function(index, layero){
                          logtable.reload();
                          layer.closeAll();
                      },
                      cancel:function(){
                          logtable.reload();
                          layer.closeAll();
                      },
                  })
                  
              }else{
            	  layer.closeAll();
                  layer.msg(data.msg);
              }
          },
          error:function(){}
      });
  }
  
  /**
   * 清空日志
   */
  $("button#trash").click(function(){
      layui.layer.confirm("确认要清空日志吗？",function(index){
    	  deleteLogging('', '');
          layer.close(index);  //取消  
      });
	  
  });
  
  //重置
  $("#reset").click(function(){
	    $("input").val("");
	});
});




</script>
</html>