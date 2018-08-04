<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>跟踪记录</title>
	<link rel="stylesheet" href="../../layui/css/layui.css">
	<script src="../../js/myutil.js"></script>
	<script src="../../layui/layui.js"></script>
	
</head>
<body >
<div style="width: 96%;margin-left: 2%;">

    <form class="layui-form" lay-filter="search-form" style="width: 96%">
	    <div class="layui-form-item">
	        <!-- 客户名称 -->  
	        <label class="layui-form-label">客户名称：</label>
	        <div class="layui-input-inline">
	          <input type="text" name="name" class="layui-input">
	        </div>
	        <div class="layui-form-mid layui-word-aux">模糊查询</div>
	    </div>      
	
	    <div class="layui-form-item">
            <!-- 客户状态 --> 
            <label class="layui-form-label">客户状态：</label>
            <div class="layui-input-inline">
              <select name="status" lay-filter="status">
                <option value="">--数据加载中--</option>
              </select>
            </div> 
	        
	        <!-- 客户类别 --> 
	        <label class="layui-form-label">客户类别：</label>
	        <div class="layui-input-inline">
	          <select name="type" lay-filter="type">
	            <option value="">--数据加载中--</option>
	          </select>
	        </div>  
	    </div>  
	    
	    <div class="layui-form-item">
	        <!-- 客户来源-->  
	        <label class="layui-form-label">客户来源：</label>
	        <div class="layui-input-inline">
	            <select name="source" lay-filter="source">
	                <option value="">--数据加载中--</option>
	            </select>
	        </div>
	        
	        <!-- 客户等级 --> 
	        <label class="layui-form-label">客户等级：</label>
	        <div class="layui-input-inline">
		          <select name="level" lay-filter="level">
		            <option value="">--数据加载中--</option>
		          </select>
	        </div>  
	    </div>  
	    
	    <div class="layui-form-item">
	       <!-- 客户信用度-->  
	       <label class="layui-form-label">客户信用度：</label>
	       <div class="layui-input-inline">
	            <select name="credit" lay-filter="credit">
	                <option value="">--数据加载中--</option>
	            </select>
	        </div>
	        
	        <!-- 客户成熟度 --> 
	        <label class="layui-form-label">客户成熟度：</label>
	        <div class="layui-input-inline">
	          <select name="maturity" lay-filter="maturity">
	               <option value="">--数据加载中--</option>
	          </select>
	        </div>  
	    </div>  
	    <div class="layui-form-item"  style="width: 450px;margin-top: 40px;">
            <div class="layui-input-block">
                <button type="reset" class="layui-btn" style="margin-left: 135px;">重置</button>
                <button type="button" name="form-submit-btn" lay-submit lay-filter="form-submit-btn" class="layui-btn">查询</button>
            </div>
        </div>  
    </form>

</div>
	
<script type="text/javascript">
layui.use(['form'],function(){
	var form = layui.form;
	var layer = layui.layer;
	var $ = layui.$;
	//获取父窗口jQuery
	var parent$ = window.parent.layui.jquery;
	
	form.on('submit(form-submit-btn)',function(data){
		var formdata = data.field;
		//console.log(formdata);
		//对父窗口的表单进行数据填充
		parent$('input[name=name]').val(formdata.name);
		parent$('input[name=status]').val(formdata.status);
		parent$('input[name=type]').val(formdata.type);
		parent$('input[name=source]').val(formdata.source);
		parent$('input[name=level]').val(formdata.level);
		parent$('input[name=credit]').val(formdata.credit);
		parent$('input[name=maturity]').val(formdata.maturity);
		
		//执行查询功能
		parent$('#search-button').click();
		//关闭当前弹出层
        var thisindex = parent.layer.getFrameIndex(window.name);
        parent.layer.close(thisindex);
		return false;
	});
	

	
	//获取客户状态字典并加载下拉框
    setTimeout(getSelectData('客户状态','status'),0);
	//getSelectData('客户状态','status');
    
    //获取客户类别字典并加载下拉框
    setTimeout(getSelectData('客户类别','type'),500);
    //getSelectData('客户类别','type');
    
    //获取客户来源字典并加载下拉框
    setTimeout(getSelectData('客户来源','source'),1500);
    //getSelectData('客户来源','source');
    
    //获取客户等级字典并加载下拉框
    setTimeout(getSelectData('客户等级','level'),2000);
    //getSelectData('客户等级','level');
    
    //获取客户信用度字典并加载下拉框
    setTimeout(getSelectData('客户信用度','credit'),2500);
    //getSelectData('客户信用度','credit');
    
    //获取客户成熟度字典并加载下拉框
    setTimeout(getSelectData('客户成熟度','maturity'),3000);
    //getSelectData('客户成熟度','maturity');
    
    
    function getSelectData(dictionaryName,selectName){
        $.post('${pageContext.request.contextPath}/dictionary/find',{'name':dictionaryName},function(data){
            var d = data.data.dictionaryItems;
            var str = '<option value="">请选择</option>';
            for(var i=0;i<d.length;i++){
                str += '<option value="' + d[i].name + '">' + d[i].name + '</option>';
            }
            $('select[name=' + selectName + ']').html(str);
            form.render('select');
            $('select[name=' + selectName + ']').val(parent$('input[name=' + selectName + ']').val());
        }); 
    }

    
    
    //从父窗口中获取上次的查询参数
    setParm();
    function setParm(){
    	$('input[name=name]').val(parent$('input[name=name]').val());
    }
    
    
    
});
</script>
		
</body>
</html>