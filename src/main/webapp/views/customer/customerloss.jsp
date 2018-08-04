<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@taglib prefix="eShiro" tagdir="/WEB-INF/tags" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户流失</title>
</head>

<body>

<script type="text/html" id="lossToolbar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">详情</a>
    <shiro:hasPermission name="8004"><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a></shiro:hasPermission>
</script>

<form class="layui-form"  style="margin-top:10px;padding:15px;border-left:5px solid #009688">
    <div class="layui-inline">
      <label class="layui-form-label">客户名称</label>
      <div class="layui-input-inline" >
        <input type="text" name="queryCustomerName"  class="layui-input" >
      </div>
    </div>
    
    <div class="layui-inline">
      <label class="layui-form-label">客户状态</label>
      <div class="layui-input-inline" >
        <select name="queryCustomerStatus"  >
          <option value="" selected>全部</option>
          <option value="0">将要流失</option>
          <option value="1">挽回客户</option>
          <option value="2">确认流失</option>
        </select>
      </div>
    </div>
    
    <button class="layui-btn" type="button" id="queryButton" >搜索</button>
</form>

<table id="lossInfo" lay-filter="lossInfo"></table>


<div id="lossDetail" hidden>
	<form class="layui-form" action="" lay-filter="lossForm" style="padding:10px;">
		  <input type="hidden" name="id" value="" />
		  <input type="hidden" name="customerId" value="" />
		  <div class="layui-form-item">
		    <label class="layui-form-label">客户名称</label>
		    <div class="layui-input-inline" >
		      <input type="text" name="name"  class="layui-input" readonly>
		    </div>
		    <button class="layui-btn" type="button" id="customerInfoButton" >客户信息</button>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label">最后一次下单时间</label>
		    <div class="layui-input-inline" >
		      <input type="text" name="lastOrderTime" class="layui-input" readonly>
		    </div>
		    <button class="layui-btn" type="button" id="historyOrdersButton" >查看历史订单</button>
		  </div>
		  
		  <!-- 采取的措施 -->
		  <div class="layui-form-item">
		    <label class="layui-form-label">采取的措施</label>
		    <div class="layui-input-block">
		      <textarea name="measure" placeholder="请输入采取的措施" class="layui-textarea"></textarea>
		    </div>
		  </div>
		  
		  <!-- 追加措施 -->
		  
		  <!-- 流失时间 -->
		  <div class="layui-form-item">
		    <label class="layui-form-label">流失时间</label>
		    <div class="layui-input-inline" >
		      <input type="text" name="lossDate" class="layui-input" readonly>
		    </div>
		  </div>
		  
		  <!-- 流失原因 -->
		  <div class="layui-form-item">
		    <label class="layui-form-label">流失原因</label>
		    <div class="layui-input-block">
		      <textarea name="reason" placeholder="请输入流失原因，未流失可不填写" class="layui-textarea"></textarea>
		    </div>
		  </div>
		  
		  <!-- 客户状态 -->
		  <div class="layui-inline">
		    <label class="layui-form-label">客户状态</label>
		    <div class="layui-input-inline">
		      <select name="status"  lay-verify="required">
		        <option value="0">将要流失</option>
		        <option value="1">挽回客户</option>
		        <option value="2">确认流失</option>
		      </select>
		    </div>
		  </div>
		  
		  <!-- 是否有更新客户流失的权限 -->
		  <shiro:hasPermission name="8003">
		  <div class="layui-form-item" style="margin-top:20px;">
		    <div class="layui-input-block">
		      <button class="layui-btn" type="button" lay-submit lay-filter="lossForm">更新</button>
		      
		    </div>
		  </div>
		 </shiro:hasPermission>
	</form>
</div>

</body>


<script type="text/javascript">
layui.use(['table', 'layer'], function(){
	var $ = layui.$;
	var currentPage = 1;
	var lossInfoTable = layui.table.render({
		elem: '#lossInfo',
	    url: '${pageContext.request.contextPath}/loss/findCustomerLoss', //数据接口
	    page: true, //开启分页
	    cols: [[ //表头
	      {
	    		field: "customer",
		    	title: "客户名称",
		    	templet: function(d){
		    		if(d.customer != null){
		    			return d.customer.name;
		    		}
		    		return "";
		    	},
	      },{
		    	field: "lastOrderTime",
		    	title: "上一次下单时间",
	      },{
	    	  	field: "lossDate",
	    	  	title: "流失日期",
	    	  	templet: function(d){
	    	  		if(d.lossDate == null){
	    	  			return "未流失";
	    	  		}else{
	    	  			return d.lossDate;
	    	  		}
	    	  	}
	      },{
	    	  	field: "status",
	    	  	title: "流失状态",
	    	  	templet:function(d){
	    	  		if(d.status == "0"){
	    	  			return "将要流失";
	    	  		}else if(d.status == 1){
	    	  			return "<span style='color:green'>挽回客户</span>";
	    	  		}else if(d.status == 2){
	    	  			return "<span style='color:red;'>确认流失</span>";
	    	  		}
	    	  	}
	      },{
	    	  	title: "操作",
	    	  	align: "center",
	    	  	toolbar: "#lossToolbar",
	      },
	    ]],
	    limit: 10,
	    limits: [10,20,30,40,50,60,70],
	    loading: true,
	    done:function(res, curr, count){
	    	//如果是异步请求数据方式，res即为你接口返回的信息。
	        //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
	        //console.log(res);
	        
	        //得到当前页码
	        //console.log(curr); 
	        
	        //得到数据总量
	        //console.log(count);
	        
	        currentPage = curr;
	    }
	});
	
	//监听工具栏
	layui.table.on("tool(lossInfo)", function(obj){
		  var data = obj.data; //获得当前行数据
		  var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		  var tr = obj.tr; //获得当前行 tr 的DOM对象
		  
		  var html = "";
		  if(layEvent == "edit"){
			  //详情
			  layer.open({
				  type:1,
				  content: $("#lossDetail").html(),
				  area: ["600px", "600px"],
				  title: "详情",
				  success:function(layero, index){
					  html = $("#lossDetail").html();
					  $("#lossDetail").html("");
					  
					 // console.log(data);
					  
					  //渲染表单
					  layui.form.val("lossForm",{
						  "id"				: data.id,
						  "name"			: data.customer.name,
						  "lastOrderTime"	: data.lastOrderTime,
						  "measure"			: data.measure,
						  "reason"			: data.reason,
						  "lossDate"		: data.lossDate,
						  "customerId"		: data.customer.id,
					  });
					  $("select[name=status]").val(data.status);
					  //重新加载
					  layui.form.render(null, "lossForm");
					  
					  //设置按钮事件
					  //点击客户详情按钮
					  $("button#customerInfoButton").click(function(){
						  //弹出详情页
						  layer.open({
				    			type:2,
				    			title:'客户详情',
				    			area:['80%','100%'],
				    			clostBtn:1,
				    			shadeClose: true,
				    			maxmin:true,
				    			offset:'r',
				    			content:'views/customer/customerInfomation.jsp?id='+ data.customer.id,
				    	   }); 
					  });
					  
					  //点击历史订单查看客户的历史订单信息
					  $("button#historyOrdersButton").click(function(){
						 //弹出对应的客户的历史订单页
						  layer.open({
                              type:2,
                              title:'客户   '+data.customer.name+'  的历史订单',
                              area:['90%','100%'],
                              clostBtn:1,
                              shadeClose: true,
                              maxmin:true,
                              offset:'r',
                              content:'views/customer/customerOrders.jsp?customerId='+ data.customer.id,
                         }); 
						 
					  });
				  },
				  end:function(){
					  $("#lossDetail").html(html);
				  }
			  })
			  
		  }else if(layEvent == "del"){
			  //删除
			  layer.confirm("确定要删除这条数据吗？", function(index){
				 
				  $.ajax({
					  url:"${pageContext.request.contextPath}/loss/deleteCustomerLoss",
					  type:"post",
					  data:{
						  id : data.id,
					  },
					  dataType:"json",
					  beforeSend:function(){
						  loading = layer.load();
					  },
					  success:function(data){
						  if( data != null && data.code == 0){
							  layer.closeAll();
							  layer.msg("删除成功!");
							  reload();
						  }else{
							  layer.msg("删除失败!");
							  
						  }
					  },
					  error:function(){
						  layer.msg("服务器开小差了!");
					  },
					  complete:function(){
						  layer.close(loading);
					  }
				  });
				  
				  layer.close(index);
			  });
			 
		  }
	})
	
	//form表单的提交
	layui.form.on("submit(lossForm)", function(data){
		  //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
		  //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
		  //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
		  
		  $.ajax({
			 url:"${pageContext.request.contextPath}/loss/updateCustomerLoss",
			 type:"post",
			 data:{
				 "id"     : $("input[name=id]").val(),
				 "measure": $("textarea[name=measure]").val(),
				 "reason" : $("textarea[name=reason]").val(),
				 "status" : $("select[name=status]").val(),
				 "customer.id"	: $("input[name=customerId]").val(),
			 },
			 dataType:"json",
			 beforeSend:function(){
				 loading = layer.load();
			 },
			 success:function(res){
				 if(res != null && res.code == 0){
					 layer.closeAll();
					 layer.msg("更新成功!");
					 //表格重载
					 reload();
				 }else{
					 layer.msg("更新失败!");
				 }
			 },
			 error:function(){
				layer.msg("服务器开小差了!"); 
			 },
			 complete:function(){
				 layer.close(loading);
			 }
		  });
		  
		  
		  
	});
	
	/**
		表格的重新加载
	*/
	function reload(){
		lossInfoTable.reload({
			page: {
		       curr: 1 //重新从第 1 页开始
		    },
			where : {
				//搜索条件
				"customer.name": $("input[name=queryCustomerName]").val(),
				"status": $("select[name=queryCustomerStatus]").val(),
			},
		});
	}
	
	/**
	   搜索
	**/
	$("button#queryButton").click(function(){
		currentPage = 1;
        reload();
    })
		
	
});


</script>

</html>