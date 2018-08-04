<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1, maximum-scale=1">
<title>添加服务</title>
<link rel="stylesheet" href="../../layui/css/layui.css">
<script src="../../layui/layui.js"></script>
<script src="../../layui/layui.js"></script>
<script src="../../js/myutil.js"></script>
<style type="text/css">
.layui-form-item{
    margin-top: 15px;
    margin-left:60px;
}
</style>
</head>
<body>
   
    <form class="layui-form" lay-filter="transferForm">    
            <div style="margin-top: 30px;"  class="layui-form-item" >  
                <label class="layui-form-label">客户经理：</label>
                <div class="layui-input-inline" style="width: 320px;">
                    <select id="managerSelect" name="newManagerId" lay-search lay-verify="required">
                        <option value="">--数据加载中--</option>
                    </select>
                </div>
            </div>
            <div style="margin-top: 30px;"  class="layui-form-item">  
                <label class="layui-form-label">转交原因：</label>
                <div class="layui-input-inline" style="width: 500px;">
                    <textarea id="reasonText" name="reason" style="width:100%; height:200px;" placeholder="请输入转交的原因"
                     lay-verify="required" class="layui-textarea" ></textarea>
                </div>
            </div>
            
            <div style="margin-top: 30px; "  class="layui-form-item">
	            <div class="layui-input-block" >
	                <button type="reset" class="layui-btn layui-btn-warm" style="margin-left: 150px;">重置</button>
	                <button type="button" name="sumbitBtn" lay-submit lay-filter="sumbitBtn" class="layui-btn">转交</button>
	            </div>      
            </div>  
            <input type="hidden" name="serviceId" value="" />
    </form>
       
    
    
</body>

<script>
    layui.use(['form'], function() {
        var form = layui.form;
        var layer = layui.layer;
        var laydate = layui.laydate;
        var $ = layui.$;        
        //表单提交的地址
        var submitUrl = '';
        
        //渲染经理下拉框
        loadMangerSelect();
        
        
        
        //从url中获取参数，判断为服务更新还是编辑
        var serviceId = getParm().id;
        
        //设置表单的数值
        $("input[name='serviceId']").val(serviceId);
         
        //点击保存事件
		form.on('submit(sumbitBtn)',function(data){
		    var formdata = data.field;
		    console.log(formdata);
		    $.ajax({
		        type: "POST",
		        url: '${pageContext.request.contextPath}/service/transferSerive',
		        data: formdata,
		        dataType: "json",
		        success: function(data){
		            if(data.success){//成功
		            	top.layer.msg(data.msg); 
		                //关闭当前弹出层
		                var thisindex = parent.layer.getFrameIndex(window.name);
		                parent.layer.close(thisindex);
		            }else{
		            	top.layer.msg(data.msg); 
		            }
		        },
		        error:function(){
		            top.layer.msg("服务器开小差了，请稍后再试...");
		        }
		   });
		   return false; 
		});
        
         function loadMangerSelect(){
            $.ajax({
                url : '${pageContext.request.contextPath}/user/findOthersManager',
                async : false,
                type : 'POST',
                dataType : 'json',
                success : function(data){
                    if(data.success){
                        var html = '';
                        if(data.success){
                            $("#managerSelect").html("");
                            var managers = data.list;
                            html += "<option value=''></option>";
                            for(i = 0 ; i < managers.length ; i++){
                                html += "<option value='"+managers[i].id+"'>"+managers[i].account+"</option>";
                            }
                            $("#managerSelect").html(html);  
                        }else {
                            layer.msg("读取客户经理数据出错！");
                        }
                    }       
                }
            });
            form.render('select');
        };
        
        
    });
</script>
</html>