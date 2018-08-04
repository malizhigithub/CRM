<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1, maximum-scale=1">
<title>处理关怀</title>
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
   
    <form class="layui-form" lay-filter="care-Form">    
            <div style="width: 99.8%;height:35px;line-height:35px;border: 1px #f1f1f1 solid;background-color: #f5f5f5;" ><span id="birthday-info" style="margin-left: 28px;"></span></div>
            <div style="margin-top: 30px;"  class="layui-form-item" >  
                <label class="layui-form-label">处理时间：</label>
                <div class="layui-input-inline" style="width: 320px;">
                    <input type="text" name="time" placeholder="请选择时间" lay-verify="required" class="layui-input" id="datetimeSelect"  />
                </div>
            </div>
            
            <div style="margin-top: 30px;"  class="layui-form-item" >  
                <label class="layui-form-label">处理方式：</label>
                <div class="layui-input-inline" style="width: 320px;">
                     <select id="typeSelect" name="type" lay-search lay-verify="required">
                        <option value="">--数据加载中--</option>
                     </select>
                </div>
            </div>
            
            <div style="margin-top: 30px;"  class="layui-form-item">  
                <label class="layui-form-label">处理内容：</label>
                <div class="layui-input-inline" style="width: 500px;">
                    <textarea id="reasonText" name="detail" style="width:100%; height:150px;" placeholder="请输入处理的内容"
                     lay-verify="required" class="layui-textarea" ></textarea>
                </div>
            </div>
            
            <div style="margin-top: 30px;"  class="layui-form-item">  
                <label class="layui-form-label">客户反馈：</label>
                <div class="layui-input-inline" style="width: 500px;">
                    <textarea id="reasonText" name="feedback" style="width:100%; height:150px;" placeholder="请输入客户反馈"
                     class="layui-textarea" ></textarea>
                </div>
            </div>
            
            <div class="layui-form-item">
	            <div class="layui-input-block">
	                <button type="reset" class="layui-btn layui-btn-warm" style="margin-left: 165px;">重置</button>
	                <button type="button" name="sumbitBtn" lay-submit lay-filter="sumbitBtn" class="layui-btn">保存</button>
	            </div>
            </div>  
            
            <input type="hidden" name="id" value="" />
             
            <input type="hidden" name="linkmanId" value="" />
            
            <input type="hidden" name="birthday" value="" />
            
            <input type="hidden" name="managerId" value="" />
            
            <input type="hidden" name="createTime" value="" />

    </form>
       
    
    
</body>

<script>
    layui.use(['form', 'laydate'], function() {
        var form = layui.form;
        var layer = layui.layer;
        var laydate = layui.laydate;
        var $ = layui.$;        
        
        //渲染日期选择器
        laydate.render({
            elem : '#datetimeSelect',
            type : 'datetime'
        });
        
        //表单提交的地址
        var submitUrl = '';
        
        form.render('select');
        
        //渲染方式下拉框
        loadSelect('typeSelect');
        
        
        //从url中获取参数
        var customerCareId = getParm().id;
        
        //设置表单的数值
        $("input[name='id']").val(customerCareId);
        
        //编辑或添加处理关怀
        if(customerCareId != '' && customerCareId != null ){
            loadCustomerCare(customerCareId);
            var submitUrl = '${pageContext.request.contextPath}/customerCare/handleCustomerCare';
        }
    
        //加载表单内容
        function loadCustomerCare(customerCareId){
            $.ajax({
                type : "POST",
                url : "${pageContext.request.contextPath}/customerCare/findCustomerCareById",
                async : false,
                data : {
                    id : customerCareId
                },
                dataType : "json",
                success : function(data){
                    console.log(data);
                    var html = '';
                    if(data.success){
                       //填充表单的值
                       var html = '';
                       linkman = data.customerCare.linkman;
                       $('#birthday-info').html('');
                       html += linkman.name + "[<span style='color:#999999;'>" + linkman.customer.name + "</span>]出生于公历：" + Format(linkman.birthday,'yyyy年MM月dd日') + "，" + new Date().getFullYear() + "年是其" + getAges(Format(linkman.birthday, 'yyyy-MM-dd')) + "岁生日";
                       $('#birthday-info').html(html);
                       form.val('care-Form',data.customerCare);
                       $("input[name='birthday']").val(Format(data.customerCare.birthday,"yyyy-MM-dd"));
                    }else {
                        layer.msg("读取数据出错！");
                    }
                    form.render('select');
                }
            });
        }
        
		//加载类型下拉框
        function loadSelect(selectName){
            var name = '';
            if(selectName == 'typeSelect'){
                name = "客户关怀方式";
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
                    console.log(data);
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
                    form.render('select');
                }
            });
        }
        
        //点击保存事件
        form.on('submit(sumbitBtn)',function(data){
            var formdata = data.field;
            console.log(formdata);
            $.ajax({
                type: "POST",
                url: submitUrl,
                data: formdata,
                dataType: "json",
                success: function(data){
                    if(data.success){//成功
                        top.layer.msg("保存成功！"); 
                        //关闭当前弹出层
                        var thisindex = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(thisindex);
                    }
                    else{
                    	top.layer.msg("1、服务器开小差了，请稍后再试...");
                    }
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    top.layer.msg("2、服务器开小差了，请稍后再试...");
                    // 状态码
                    console.log(XMLHttpRequest.status);
                    // 状态
                    console.log(XMLHttpRequest.readyState);
                    // 错误信息   
                    console.log(textStatus);
                    console.log(errorThrown);
                }
           });
            
           return false; 
        });
    });
</script>
</html>