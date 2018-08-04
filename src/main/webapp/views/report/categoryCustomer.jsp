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
		               <label class="layui-form-label">分类</label>
			            <div class="layui-input-inline" style="width:150px;">
			                <select name="selectType" lay-filter="report-object">
			                    <option value="2010" selected>客户信用度</option>
			                    <option value="2020" >客户等级</option>
			                    <option value="2030" >客户成熟度</option>
			                    <option value="2040" >客户来源</option>
			                    <option value="2050" >客户状态</option>
			                    <option value="2060" >客户类别</option>
			                </select>
			            </div>
			        </div>
	                <div class="layui-inline">
		               <label class="layui-form-label">统计粒度</label>
			            <div class="layui-input-inline" style="width:100px;">
			                <select name="action" lay-filter="select-action">
			                    <option value="year" selected>年</option>
			                    <option value="month" >月</option>
			                    <option value="day">日</option>
			                </select>
			            </div>
			        </div>
			        <div class="layui-inline">
                       <label class="layui-form-label">时间</label>
                        <div class="layui-input-inline" id="select-year-div" style="width:80px;">
	                        <!-- <input name="date" id="time" class="layui-input"> -->
	                        <select name="year" lay-filter="select-year">
                                <option value="2018" selected>2018</option>
                                <option value="2018" selected>2018</option>
                                <option value="2018" selected>2018</option>
                            </select>
                        </div>
                        <div class="layui-input-inline layui-hide" id="select-month-div" style="width:80px;">
                            <select name="month" lay-filter="select-month">
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
                        <div class="layui-input-inline  layui-hide" id="select-day-div" style="width:80px;">
                            <select name="day" lay-filter="select-day">
                                <option value="01" selected>01</option>
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
layui.use(['layer','form','laydate'], function(){
	var $ = layui.$;
	var form = layui.form;
	var laydate = layui.laydate;
    

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
                name: "数量",
                type: 'value'
            },
            yAxis: {
                name: "新增客户量",
                type: 'category',
                data:[]
            },
            toolbox:{  //工具栏
                show: true,
                feature:{
                    //dataView: {readOnly: false},  
                    //magicType: {type: ['line', 'bar']},  //图形切换
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
                    type: 'bar'
                }
            ] 
        };

    
    form.render("select");

   
    //监听查询方式选择
    form.on('select(select-action)',function(data){
    		if(data.value == 'day'){
    			
    			changeDays();
    			$('#select-day-div').removeClass('layui-hide');
    			$('#select-month-div').removeClass('layui-hide');
    			
        	}else if(data.value == 'month'){
        		$('#select-day-div').addClass('layui-hide');
        		$('#select-month-div').removeClass('layui-hide');
        	} else{
        		$('#select-day-div').addClass('layui-hide');
                $('#select-month-div').addClass('layui-hide');
        	}
    		
    });
    
   
    form.on('select(select-year)',function(data){
    	changeDays();
    });
    
    form.on('select(select-month)',function(data){
        changeDays();
    });
    
    function changeDays(){
    	var days = laydate.getEndDate($('select[name=month]').val(),$('select[name=year]').val());
        var str = '<option value="01" selected>01</option>';
        for(var i=2;i<=days;i++){
            var j = i >= 10 ? '' + i : '0' + i;
            str += '<option value="' + j + '">' + j + '</option>';
        }
        $('select[name=day]').html(str);
        form.render('select');
    }
    
    //初始化年下拉框
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
        $('select[name=year]').html(str);
        form.render("select");
        
    }
    init();
    
    //监听查询对象选择
    form.on('select(report-object)',function(data){
    	if(data.value == '1010'){
    		option.title = {text: '客户新增趋势'};
    		option.legend = {data: ["客户新增量"]};
    		option.yAxis= {name: "新增客户量",type: 'value'};
    		option.series = [{name: "客户新增量",data: [],type: 'line'}];
            
    	}
    });
    
    
	//构建
	showEcharts();
	
	$("#customerButton").click(function(){
		
		showEcharts();
	});
	
	/**
	   构建新增客户图表统计
	**/
	function showEcharts(){
		//构建图表data数
		var dataArray = [];
		var yArray = [];
		//console.log('start');
        
		var dateStr = '' + $('select[name=year').val() + '-' +  $('select[name=month').val()+ '-' +  $('select[name=day').val();
		
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

	           
	            for(var i=0;i<res_data.length;i++){
	            	dataArray.push(res_data[i].count);
	            	if(res_data[i].category == ''){
	            		yArray.push('未分类');
	            	}
	            	else{
	            		yArray.push(res_data[i].category);
	            	}
	            	
	            }
	            
	        },
	        error:function(){
	            layer.msg("服务器开小差了!");
	        }
	         ,
	        complete:function(){
	            layer.close(loading);
	            //console.log(option);
	            
	            option.yAxis.data = yArray;

	            option.series[0].data = dataArray;
	            customerIncrease.setOption(option);
	        } 
	    });
	}
});


</script>

</html>