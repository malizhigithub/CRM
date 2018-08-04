<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>

<div class="layui-container">
    <div class="layui-row">
    
    	<!-- 近n天公司的客户增加量/损失量 -->
        <div class="layui-col-md12">
            <form class="layui-form" action="" style="margin-top:30px;">
		        <div class="layui-form-item" style="text-align:center;">
		        	<div class="layui-inline">
		               <label class="layui-form-label">统计对象</label>
			            <div class="layui-input-inline" style="width:100px;">
			                <select name="selectType" lay-filter="report-object">
			                    <option value="1010" selected>新增客户</option>
			                    <option value="1020" >新增跟进记录</option>
			                </select>
			            </div>
			        </div>
	                <div class="layui-inline">
		               <label class="layui-form-label">统计粒度</label>
			            <div class="layui-input-inline" style="width:100px;">
			                <select name="action" lay-filter="select-action">
			                    <option value="year" >年</option>
			                    <option value="month" selected>月</option>
			                    <option value="day">日</option>
			                </select>
			            </div>
			        </div>
			        <div class="layui-inline">
                       <label class="layui-form-label">时间</label>
                        <div class="layui-input-inline" style="width:100px;">
	                        <select name="year" id="date-year" class="layui-input">
				                    
				            </select>
				            
                        </div>
                        <div id="date-month-div" class="layui-input-inline layui-hide" style="width:100px;">
				            <select name="month" id="date-month">
			                    <option value="01" selected>1月</option>
			                    <option value="02">2月</option>
			                    <option value="03">3月</option>
			                    <option value="04">4月</option>
			                    <option value="05">5月</option>
			                    <option value="06">6月</option>
			                    <option value="07">7月</option>
			                    <option value="08">8月</option>
			                    <option value="09">9月</option>
			                    <option value="10">10月</option>
			                    <option value="11">11月</option>
			                    <option value="12">12月</option>
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
</div>

</body>
<script type="text/javascript">
//请求数据
layui.use(['layer','form'], function(){
	var $ = layui.$;
	var form = layui.form;
    

	var customerIncrease = echarts.init(document.getElementById("customer"));
    var  option = {
            title : {
                text: '客户新增趋势'
            },
            tooltip:{  //鼠标经过显示提示框
            	show : true,
            	trigger: "axis"
            },
            legend:{  //不同的线的类型
            	data: ["客户新增量"]
            },
            xAxis: {
                name: "时间",
                type: 'category'
            },
            yAxis: {
                name: "新增客户量",
                type: 'value'
            },
            toolbox:{  //工具栏
                show: true,
                feature:{
                    dataView: {readOnly: false},  
                    magicType: {type: ['line', 'bar']},  //图形切换
                    restore: {},
                    saveAsImage: {}
                }
            },
            dataZoom: [  //滚动条
                {
                    type: 'slider',
                    show: true,
                    start: 0,
                    end: 100
                }
            ],
            series: [
            	{
            		name: "客户新增量",
                    data: [], 
                    type: 'line'
                }
            ] 
        };
	
	
    
    function init(){
    	var year = new Date().getFullYear();
    	
    	var str = '';
    	for(var i=year-10;i<=year+10;i++){
    		if(i == year){
    			str += '<option value="' + i + '" selected>' + i + '</option>';
    		}else{
    			str += '<option value="' + i + '">' + i + '</option>';
    		}
    	}
    	$('#date-year').html(str);
    	form.render("select");
    	
    }
    
    
    form.render("select");
    init();
    
    
    //监听查询方式选择
    form.on('select(select-action)',function(data){
    		if(data.value == 'day'){
        		$('#date-month-div').removeClass('layui-hide');
        	}else{
        		$('#date-month-div').addClass('layui-hide');
        	}
    });
    
    //监听查询对象选择
    form.on('select(report-object)',function(data){
    	if(data.value == '1010'){
    		option.title = {text: '客户新增趋势'};
    		option.legend = {data: ["客户新增量"]};
    		option.yAxis= {name: "新增客户量",type: 'value'};
    		option.series = [{name: "客户新增量",data: [],type: 'line'}];
            
    	}else{
    		option.title = {text: '跟进记录新增趋势'};
    		option.legend = {data: ["跟进记录新增量"]};
    		option.yAxis= {name: "新增跟进记录量",type: 'value'};
    		option.series = [{name: "跟进记录新增量",data: [],type: 'line'}];
    	}
    });
    
    
	//构建公司近n天的新增/损失客户量
	createCustomerEcharts();
	
	$("#customerButton").click(function(){
		createCustomerEcharts();
	});
	
	/**
	   构建新增客户图表统计
	**/
	function createCustomerEcharts(){
		//构建图表data数
		var dataArray = [];  
		console.log('start');
		
		var dateStr = '';
		var action = $('select[name=action]').val();
		if(action == 'year'){
			dateStr = $('#date-year').val();
		}else if(action == 'month'){
			dateStr = $('#date-year').val() + '-' + $("#date-month").val();
		}else{
			dateStr = $('#date-year').val() + '-' + $("#date-month").val() + '-11';
		}
		
		$.ajax({
	        url:"${pageContext.request.contextPath}/report/countCustomerByUser",
	        type:"post",
	        data:{
	            "action": $('select[name=action]').val(),
	            "selectType":$('select[name=selectType]').val(),
	            "date":dateStr
	        },
	        asnyc:false,
	        dataType:"json",
	        beforeSend:function(){
	            loading = layer.load();
	        },
	        success:function(res){
	        	
	        	if(res.code != 0){
	        		layer.closeAll('loading');
	        		layer.msg(res.msg);
	        		return;
	        	}
	        	
	        	
	            var res_data = res.data;
	            
	            //构建json对象
	            var data = {};
	            
	            if(action == 'year'){
	            	var myDate = new Date(dateStr);
	            	console.log(myDate);
	            	var y = myDate.getFullYear();
	            	data[y] = 0;
	            }else if(action == 'month'){ 
	            	var myDate = new Date(dateStr);
	            	console.log(myDate);
	            	var y = myDate.getFullYear();
	            	
	            	for(var i=1;i<=12;i++){
	            		var datename = '' + y;
	            		datename += i>=10? '-' + i : '-0' + i; 
	            		console.log(datename);
	            		data[datename] = 0;
	            	}
	            	
	            }else if(action == 'day'){
	            	var myDate = new Date($('#date-year').val(),$('#date-month').val(),0);
	            	var days = myDate.getDate();
	            	var datename = '';
	            	for(var i=1;i<=days;i++){
	            		datename = $('#date-month').val();
	            		datename += i>=10 ? '-' + i : '-0' + i;
	            		data[datename] = 0;
	            	}
	            }else{
	            	return;
	            }
	            
	            
	            var dataJson = {};  //构建json对象，日期为key 统计数值为value
	            for(var i=0; i < res_data.length;i++ ){
	            	dataJson[res_data[i].date] = res_data[i].count;
	            }

	           
	            //构建图表data数组
	            for(var key in data){
	            	if(dataJson[key] != null){
	            		dataArray.push([key, dataJson[key]]);
	            	}else{
	            		dataArray.push([key, data[key]]);
	            	}
	            }
	        },
	        error:function(){
	            layer.msg("服务器开小差了!");
	        }
	         ,
	        complete:function(){
	            layer.close(loading);
	            console.log(option);
	            option.series[0].data = dataArray;
	            customerIncrease.setOption(option);
	        } 
	    });
	}
});


</script>

</html>