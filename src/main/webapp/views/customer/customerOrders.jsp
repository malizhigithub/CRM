<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../../layui/css/layui.css">
    <script src="../../js/myutil.js"></script>
    <script src="../../layui/layui.js"></script>
<title>客户历史订单详情页</title>
</head>
<body>

<table id="customerOrders" lay-filter="customerOrders" ></table>

<script type="text/html" id="ordersToolsBar" >
    <a class="layui-btn layui-btn-xs" lay-event="detail">详情</a>
</script>

</body>

<script type="text/javascript">
layui.use(['table', 'layer', 'form'], function(){
	var $ = layui.$;
	var param = getParm();//获取get请求中的参数
	
	customerOrders = layui.table.render({
		elem: "#customerOrders",
		url: "${pageContext.request.contextPath}/orders/getHistoryOrdersByCustomer?id="+param.customerId,
		page: true, //开启分页
        cols: [[ //表头
          {
        	field: "id",
        	title: "订单编号",
        	align: "center",
          },{
        	field: "date",
        	title: "时间",
          },{
        	field: "address",
        	title: "地址",
          },{
        	field: "price",
        	title: "订单总额",
          },{
        	field: "status",
        	title: "状态",
        	templet:function(data){
        		if(data.status == 1){
        			return "已完成";
        		}
        	}
          },{
        	align: "center",
        	toolbar: "#ordersToolsBar",
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
            
        }
	});
	
	//工具栏监听
	layui.table.on("tool(customerOrders)", function(obj){
		  var data = obj.data; //获得当前行数据
		  var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		  var tr = obj.tr; //获得当前行 tr 的DOM对象
		  
		  if(layEvent == "detail"){
			  //弹出详情页面
			  
			  layer.open({
                  type:2,
                  title:'订单   '+data.id+'  的订单项',
                  area:['100%','100%'],
                  clostBtn:1,
                  shadeClose: true,
                  maxmin:true,
                  offset:'r',
                  content:'orderItem.jsp?orderId=' + data.id,
             }); 
			  
		  }
		  
	});
	
})
</script>
</html>