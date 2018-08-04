<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户经理业绩排行榜</title>
</head>
<body>

<div class="layui-container">
    <div class="layui-row">
    
        <!-- 近n天公司的客户增加量/损失量 -->
        <div class="layui-col-md12">
            <form class="layui-form" action="" style="margin-top:30px;">
                <div class="layui-form-item" style="text-align:center;">
                    <div class="layui-inline">
                       <label class="layui-form-label">范围</label>
                        <div class="layui-input-inline" style="width:100px;">
							 <input type="text" class="layui-input" id="start_Time">
                        </div>
                        <div class="layui-form-mid">-</div>
                        <div class="layui-input-inline" style="width:100px;">
                        	<input type="text" class="layui-input" id="end_Time">
                        </div>
                        <button id="rankButton" class="layui-btn layui-btn-primary" type="button">确定</button>
                    </div>
                </div>
            </form>
            <div id="rankEchart" style="width:100%;height:500px">
            </div>
        </div>
        
    </div>
</div>
</body>
<script type="text/javascript">
layui.use(['layer', 'form', 'laydate'], function(){
    var $ = layui.$;
    layui.form.render("select");
    
    layui.laydate.render({
        elem: '#start_Time' //指定元素
    });
    
    layui.laydate.render({
        elem: '#end_Time' //指定元素
    });
    
    
    var rankEcharts = echarts.init(document.getElementById("rankEchart"));
    
    createEcharts();
    
    $("#rankButton").click(function(){
    	createEcharts();
    })
    
    function createEcharts(){
        var data = [];
    	$.ajax({
    		url: "${pageContext.request.contextPath}/report/countManagerCustomerRank",
    		type:"post",
    		data:{
    			startTime: $("#start_Time").val(),
    			endTime: $("#end_Time").val(),
    		},
    		dataType:"json",
    		asnyc:false,  //同步加载
    		beforeSend:function(){
    			loading = layer.load();
    		},
    		success:function(res){
    			if(res.code == 0){
    				var resData = res.data;
        			for(var i=0;i<resData.length;i++){
        				var arr = [];
        				arr.push(resData[i].count);
        				arr.push(resData[i].name);
        				data.unshift(arr);
        			}
    			}else{
    				layer.msg(res.msg);
    			}
    			
    			console.log(data);
    		},
    		error:function(){
    			layer.msg("服务器出小差了");
    		},
    		complete:function(){
    			layer.close(loading);
    			
    			var option = {
   			        title: {
   			            text: '客户经理业绩前十',
   			            subtext: '数据来自东软客户关系管理系统'
   			        },
   			        tooltip: {
   			            trigger: 'axis',
   			            axisPointer: {
   			                type: 'shadow'
   			            }
   			        },
   			     	toolbox:{  //工具栏
	                    show: true,
	                    feature:{
	                        dataView: {readOnly: false},  
	                        magicType: {type: [ 'bar']},  //图形切换
	                        restore: {},
	                        saveAsImage: {},
	                    },
	                },
   			        grid: {
   			            left: '3%',
   			            right: '4%',
   			            bottom: '3%',
   			            containLabel: true
   			        },
   			        xAxis: {
   			            type: 'value',
   			            boundaryGap: [0, 0.01]
   			        },
   			        yAxis: {
   			            type: 'category',
   			           
   			        },
   			        series: [
   			            {
   			                name: '客户量',
   			                type: 'bar',
   			                data: data,  //数据
   			            },
   			            
   			        ]
   			    };
    			rankEcharts.setOption(option);
    		}
    		
    	});
    	
    }
})
</script>
</html>