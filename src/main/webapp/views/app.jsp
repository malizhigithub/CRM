<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<style>
body{
    background: white;
}
.layui-card-header{
    border-left: 5px solid #009688;
}
.layui-container{
    margin-top:20px;
}

</style>
<body>

<div class="layui-container">
    <div class="layui-row">
           
    	<!-- 近n天公司的客户增加量/损失量 -->
        <div class="layui-col-md12">
            <div class="layui-card">
               <div class="layui-card-header" >客户趋势</div>
               <div class="layui-card-body" >
		            <form class="layui-form" action="" style="margin-top:30px;">
				        <div class="layui-form-item" style="text-align:center;">
			                <div class="layui-inline">
				               <label class="layui-form-label">统计</label>
					            <div class="layui-input-inline" style="width:100px;">
					                <select name="day" >
					                    <option value="7" selected>7天</option>
					                    <option value="30">30天</option>
					                    <option value="60">60天</option>
					                </select>
					            </div>
					            <button id="customerButton" class="layui-btn layui-btn-primary" type="button">确定</button>
					        </div>
					    </div>
				    </form>
					<div id="customer" style="width:100%;height:500px">
					</div>
				</div>
			</div>
		</div><!-- 近n天公司的客户增加量/损失量 -->
		
		
		<!-- 客户来源 -->
        <div class="layui-col-md12" >
            <div class="layui-card">
                  <div class="layui-card-header" >客户来源</div>
                  <div class="layui-card-body" >
                    <div id="customerOrigin" style="width:100%;height:500px;"></div>
                  </div>
            </div>
        </div><!-- 客户来源 -->
		
    </div>
</div>

</body>
<script type="text/javascript">
//请求数据
layui.use(['layer','form','table'], function(){
	var $ = layui.$;
    layui.form.render("select");
	var customerIncrease = echarts.init(document.getElementById("customer"));
	
	//构建公司近n天的新增/损失客户量
	createCustomerEcharts();
	
	$("#customerButton").click(function(){
		createCustomerEcharts();
	});
	
	/**
	   构建新增客户图表统计
	**/
	function createCustomerEcharts(){
		//构建图表data数组
		var day = $("select[name=day]").val();
		var increaseArray = [];  //客户增加量数组
		var decreaseArray = [];  //客户损失量数组
		$.ajax({
	        url:"${pageContext.request.contextPath}/report/countCustomer",
	        type:"post",
	        data:{
	            "day": day,
	        },
	        asnyc:false,
	        dataType:"json",
	        beforeSend:function(){
	            loading = layer.load();
	        },
	        success:function(res){
	            var increase = res.increase; //客户增加量
	            var decrease = res.decrease; //客户损失量
	            
	           //构建近day天前的日期json对象
	            var data = {};
	            for(var i = 0; i < day; i++){ 
	                var myDate = new Date();
	                var lw = new Date(myDate - 1000 * 60 * 60 * 24 * i);//最后一个数字 i天的意思
	                var lastY = lw.getFullYear();
	                var lastM = lw.getMonth()+1;
	                var lastD = lw.getDate();
	                var startdate=(lastM<10 ? "0" + lastM : lastM)+"-"+(lastD<10 ? "0"+ lastD : lastD);
	                data[startdate] = 0 ;
	            }
	            
	            var increaseJson = {};  //构建json对象，日期为key 统计数值为value
	            for(var i=0; i < increase.length;i++ ){
	            	increaseJson[increase[i].date] = increase[i].count;
	            }
	            
	            var decreaseJson = {};//构建json对象，日期为key 统计数值为value
	            for(var i=0; i < decrease.length; i++){
	            	decreaseJson[decrease[i].date] = decrease[i].count;
	            }
	           
	            //构建图表data数组
	            for(var key in data){
	            	if(increaseJson[key] != null){
	            		increaseArray.unshift([key, increaseJson[key]]);
	            	}else{
	            		increaseArray.unshift([key, data[key]]);
	            	}
	            	
	            	
	            	if(decreaseJson[key] != null){
	            		decreaseArray.unshift([key, decreaseJson[key]]);
	            	}else{
	            		decreaseArray.unshift([key, data[key]]);
	            	}
	            	
	            }
	            console.log(increaseArray);
	            console.log(decreaseArray);
	        },
	        error:function(){
	            layer.msg("服务器开小差了!");
	        },
	        complete:function(){
	            layer.close(loading);
	            
	            var option = {
	                title : {
	                    text: '客户新增趋势',
	                },
	                tooltip:{  //鼠标经过显示提示框
	                	show : true,
	                	trigger: "axis",
	                },
	                legend:{  //不同的线的类型
	                	data: ["客户新增量", "客户损失量"],
	                },
	                xAxis: {
	                    name: "时间",
	                    type: 'category', 
	                },
	                yAxis: {
	                    name: "新增/损失 客户量",
	                    type: 'value',
	                },
	                toolbox:{  //工具栏
	                    show: true,
	                    feature:{
	                        dataView: {readOnly: false},  
	                        magicType: {type: ['line', 'bar']},  //图形切换
	                        restore: {},
	                        saveAsImage: {},
	                    },
	                },
	                dataZoom: [  //滚动条
	                    {
	                        type: 'slider',
	                        show: true,
	                        start: 0,
	                        end: 100,
	                    },
	                ],
	                series: [
	                	{
	                		name: "客户新增量",
		                    data: increaseArray, 
		                    type: 'line'
		                },
	                	{
		                	name: "客户损失量",
		                	data: decreaseArray,
		                	type: "line",
	                	}
	                ],
	            };
	            
	            customerIncrease.setOption(option);
	        }
	    });
	}
	
	
	//客户来源
	var customerOrigin = echarts.init(document.getElementById("customerOrigin"));
	createCustomerOrigin();
	function createCustomerOrigin(){
		var data = [];
		$.ajax({
			url: "${pageContext.request.contextPath}/report/countCustomerSource",
			type:"get",
			data:{
				
			},
			dataType:"json",
			asnyc:false,
			beforeSend:function(){
				loading = layer.load();
			},
			success:function(res){
				if(res.code == 0){
					var resData = res.data;
					console.log(resData);
					for(var i=0;i<resData.length;i++){
						var json = {};
						if(resData[i].source == ""){
							json.name = "不清楚";
						}else{
							json.name = resData[i].source;
						}
						json.value = resData[i].count;
						data.push(json);
					}
					console.log(data);
				}else{
					layer.msg(res.msg);
				}
			},
			error:function(){
				layer.msg("服务器出小差了!");
			},
			complete:function(){
				layer.close(loading);
				
				var option = {
				    title : {
				        text: '公司客户来源统计',
				        subtext: '东软客户关系管理系统',
				        x:'center'
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				    	type: "scroll",
				        orient: 'vertical',
				        left: 'left',
				        
				    },
				    series : [
				        {
				            name: '客户量',
				            type: 'pie',
				            radius : '55%',
				            center: ['50%', '60%'],
				            data:data,
				            itemStyle: {
				                emphasis: {
				                    shadowBlur: 10,
				                    shadowOffsetX: 0,
				                    shadowColor: 'rgba(0, 0, 0, 0.5)'
				                }
				            }
				        }
				    ]
				};
				customerOrigin.setOption(option);
			}
		});
		
		
	}
	
});


</script>

</html>