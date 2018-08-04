<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../../layui/css/layui.css">
    <script src="../../js/myutil.js"></script>
    <script src="../../layui/layui.js"></script>
<title>订单项详情页</title>
</head>
<body>

<table id="itemInfo" lay-filter="itemInfo" ></table>

</body>

<script type="text/javascript">
layui.use(['table', 'layer'], function(){
	var $ = layui.$;
	
	var param = getParm(); //获取get请求的参数
	
	itemInfo = layui.table.render({
        elem: "#itemInfo",
        url: "${pageContext.request.contextPath}/orders/getOrderItem?id="+param.orderId,
        page: true, //开启分页
        cols: [[ //表头
          {
        	field: "id",
        	title: "订单项编号",
          },{
        	field: "product",
        	title: "产品",
        	templet:function(d){
        		return d.product.name;
        	}
          },{
        	field: "unitPrice",
        	title: "单价",
        	templet: function(d){
        		return d.unitPrice+""+d.unit;
        	}
          },{
        	field: "num",
        	title: "数量",
          },{
        	field: "price",
        	title: "总额",
          }
        ]],
        limit: 10,
        limits: [10,20,30,40,50,60,70],
        loading: true,
        done:function(res, curr, count){
            //如果是异步请求数据方式，res即为你接口返回的信息。
            //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
            console.log(res);
            
            //得到当前页码
            console.log(curr); 
            
            //得到数据总量
            console.log(count);
        }
    });
	
})
</script>

</html>