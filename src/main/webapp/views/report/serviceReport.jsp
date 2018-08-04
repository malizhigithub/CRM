<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                <input name='userId' type="hidden" value="${user.id }" />
                <input name='account' type="hidden" value="${user.account }" />
		        <div class="layui-form-item" style="text-align:center;">
		            <div class="layui-inline" style="margin-left: -30px;">
                       <label class="layui-form-label">分类：</label>
                        <div class="layui-input-inline" style="width:110px;">
                            <select id="categorySelect">
                                <option value="status" selected>服务状态</option>
                                <option value="type" >服务类别</option>
                            </select>
                        </div>
                    </div>
		        	<div class="layui-inline">
		               <label class="layui-form-label" style="width: 30px;">近</label>
			            <div class="layui-input-inline" style="width:80px;">
			                <select id="daySelect">
			                    <option value="7" selected>7天</option>
			                    <option value="30" >30天</option>
			                    <option value="90" >90天</option>
			                </select>
			            </div>
			        </div>
			        <div class="layui-inline">
                        <button id="submitButton" class="layui-btn layui-btn-primary" type="button">确定</button>
                    </div>
			    </div>
		    </form>
			<div id="service" style="width:100%;height:600px">
			</div>
		</div>
    </div>
</div>

</body>
<script type="text/javascript">
//请求数据
layui.use(['layer','form'], function(){
	var $ = layui.jquery;
	var form = layui.form;
    
	form.render('select');
	
    var serviceChart = echarts.init(document.getElementById("service"));
    
    var dataAxis = [];
    var data = [];
    var dataShadow = [];
    var reportTitle;
	
	loadServiceReportData('status','7',$("input[name='userId']").val());
	
	$("#submitButton").click(function(){
		dataAxis = [];
	    data = [];
	    dataShadow = [];
		var userId = $("input[name='userId']").val();
		var day = $("#daySelect").val();
		var sCategory = $("#categorySelect").val();
		loadServiceReportData(sCategory,day,userId);
	});
	
	
	
	function loadServiceReportData(sCategory,day,userId){
		var load = layer.load();
		if(sCategory == 'status'){
			reportTitle = day+"天内服务状态统计情况";
		}else if(sCategory == 'type'){
			reportTitle = day+"天内服务类型统计情况";
		}else if(sCategory == 'degree'){
			reportTitle = day+"天内客户满意度统计情况"
		}
		
		$.ajax({
	       url : '${pageContext.request.contextPath}/report/countManagerService',
	       type : 'POST',
	       async : false,
	       data : {
	    	   'userId' : userId,
	           'day' : day,
	           'sCategory' : sCategory
	       },
	       dataType : 'json',
	       success : function(result){
	    	   console.log(result);
	    	   for(var i = 0 ; i < result.data.length ; i++){
	    		   dataAxis.push(result.data[i].sCategory);
	    		   data.push(result.data[i].count);
	    	   }
	    	   loadServiceReport();
	       },
	       complete : function(){
	    	   layer.close(load);
	       }
	    });	
	}

	
	//加载报表
	function loadServiceReport(){
    	option = {
    			title: {
    		        text: reportTitle,
    		        subtext: "数据来源：" + $("input[name='account']").val() + "的客户服务数据"
    		    },
    		    color: ['#3398DB'],
    		    tooltip : {
    		        trigger: 'axis',
    		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
    		            type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
    		        }
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
    		    grid: {
    		        left: '3%',
    		        right: '4%',
    		        bottom: '3%',
    		        containLabel: true
    		    },
    		    xAxis : [
    		        {
    		            type : 'category',
    		            data : dataAxis,
    		            axisTick: {
    		                alignWithLabel: true
    		            }
    		        }
    		    ],
    		    yAxis : [
    		        {
    		            type : 'value'
    		        }
    		    ],
    		    series : [
    		        {
    		            name:'数量',
    		            type:'bar',
    		            barWidth: '60%',
    		            data:data
    		        }
    		    ]
    		};
		    serviceChart.setOption(option);
	}
	
});


</script>

</html>