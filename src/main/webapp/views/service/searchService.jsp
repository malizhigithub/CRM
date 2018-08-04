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
    margin-top: 26px;
    margin-left:42px;
}
</style>
</head>
<body>
   
    <form class="layui-form" lay-filter="myform">
        <div style="width: 99.8%;height:35px;line-height:35px;border: 1px #f1f1f1 solid;background-color: #f5f5f5;" ><span style="margin-left: 28px;">搜索条件</span></div>
        
        <div class="layui-form-item">  
            <label class="layui-form-label">客户：</label>
            <div class="layui-input-inline" style="width: 250px;">
                <select id="customerSelect" name="customerId" lay-search>
                    <option value="">--数据加载中--</option>
                </select>
            </div>
        </div>    
        
        <hr size="1">
        
        <div class="layui-form-item">  
            <label class="layui-form-label">服务类型：</label>
            <div class="layui-input-inline" style="width: 250px;">
                <select id="typeSelect" name="type" lay-search>
                    <option value="">--数据加载中--</option>
                </select>
            </div>
            <label class="layui-form-label" style="margin-left: 50px;">紧急程度：</label>
            <div class="layui-input-inline" style="width: 250px;">
                <select id="emergencySelect" name="emergency" lay-search>
                    <option value="">--数据加载中--</option>
                </select>
            </div>
        </div>
        <hr size="1">
        <div class="layui-form-item">  
            <label class="layui-form-label">服务状态：</label>
            <div class="layui-input-inline" style="width: 250px;">
                <select id="statusSelect" name="status" lay-search >
                    <option value="">--数据加载中--</option>
                </select>
            </div>
            <label class="layui-form-label" style="margin-left: 29px;width: 100px;">客户满意度：</label>
            <div class="layui-input-inline" style="width: 250px;">
                <select id="degreeSelect" name="degree" lay-search >
                    <option value="">--数据加载中--</option>
                </select>
            </div>
        </div>
        
        
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-top: 71px;margin-left: 60px;">
                <button type="reset" class="layui-btn layui-btn-warm" style="margin-left: 250px;">重置</button>
                <button type="button" name="sumbitBtn" lay-submit lay-filter="sumbitBtn" class="layui-btn">搜索</button>
            </div>
        </div>  
    </form>
       
    
    
</body>

<script>
    layui.use(['form', 'laydate'], function() {
        var form = layui.form;
        var layer = layui.layer;
        var laydate = layui.laydate;
        var $ = layui.$;        
        //表单提交的地址
        var submitUrl = '';
        
        form.render('select');
        
        //渲染所有下拉框
        loadAllSelect();
        
        var parent$ = window.parent.layui.jquery;
        
        //点击保存事件
		form.on('submit(sumbitBtn)',function(data){
		    var formdata = data.field;	
		    
			parent$("input[name=customerInput]").val(formdata.customerId);
			
			parent$("input[name=typeInput]").val(formdata.type);
			
			parent$("input[name=degreeInput]").val(formdata.degree);
			
			parent$("input[name=statusInput]").val(formdata.status);
			
			parent$("input[name=emergencyInput]").val(formdata.emergency);
			
			console.log("点击前");
            //进行搜索操作  
            parent$("#search-button-submit").click();
            
            console.log("点击后");
            //关闭当前弹出层
            var thisindex = parent.layer.getFrameIndex(window.name);
            parent.layer.close(thisindex);
            return false;
		});
        
        //加载客户下拉框
        function loadCustomerSelect(){
            $.ajax({
                type : "POST",
                url : "${pageContext.request.contextPath}/service/findAllCustomerByRole",
                async : false,
                dataType : "json",
                success : function(data){
                    var html = '';
                    if(data.success){
                        $("#customerSelect").html("");
                        var customers = data.list;
                        html += "<option value=''></option>";
                        for(i = 0 ; i < customers.length ; i++){
                            html += "<option value='"+customers[i].id+"'>"+customers[i].name+"</option>";
                        }
                        $("#customerSelect").html(html); 
                    }else {
                        layer.msg("读取客户数据出错！");
                    }
                }
            });
        }
        
        
        //加载类型下拉框
        function loadSelect(selectName){
            var name = '';
            if(selectName == 'typeSelect'){
                name = "服务类型";
            }else if(selectName == 'emergencySelect'){
                name = "服务紧急程度";
            }else if(selectName == 'statusSelect'){
            	name = "服务状态";
            }else if(selectName == 'degreeSelect'){
            	name = "服务满意度";
            }
            $.ajax({
                type : "POST",
                url : "${pageContext.request.contextPath}/dictionary/find",
                async : false,
                data : {
                    name : name
                },
                dataType : "json",
                success : function(data){
                    var html = '';
                    if(data.success){
                        $("#"+selectName).html("");
                        var types = data.data.dictionaryItems;
                        html += "<option value=''></option>";
                        for(i = 0 ; i < types.length ; i++){
                            html += "<option value='"+types[i].name+"'>"+types[i].name+"</option>";
                        }
                        $("#"+selectName).html(html);  
                    }else {
                        layer.msg("读取数据出错！");
                    }
                }
            });
        }
       

        function loadAllSelect(){
            loadCustomerSelect();
            loadSelect('typeSelect');
            loadSelect('emergencySelect');
            loadSelect('statusSelect');
            loadSelect('degreeSelect');
            //加载下拉框完成后，要重新进行渲染
            form.render('select');
        }
        
        
        
    });
</script>
</html>