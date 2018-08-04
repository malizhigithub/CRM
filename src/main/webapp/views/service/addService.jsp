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
<script src="../../js/myutil.js"></script>
<script src="../../js/wangEditor.min.js"></script>
<style type="text/css">
.layui-form-item{
    margin-top: 16px;
    margin-left:60px;
    font-size : 16px;
}
</style>
</head>
<body>
   
    <!-- 编辑添加框 -->
    <!-- form外面要有一个div-->
    <form class="layui-form" lay-filter="myform">
        <div style="width: 99.8%;height:35px;line-height:35px;border: 1px #f1f1f1 solid;background-color: #f5f5f5;" ><span style="margin-left: 28px;">服务信息</span></div>
        
        <input type="hidden" name="id" value=""/>
        <input type="hidden" name="status" value=""/>
        <input type="hidden" name="creater" value=""/>
        
        <div class="layui-form-item">  
            <label class="layui-form-label">客户：</label>
            <div class="layui-input-inline" style="width: 320px">
                <select id="customerSelect" name="customerId" lay-search  lay-verify="required">
                    <option value="">--数据加载中--</option>
                </select>
            </div>
        </div>
        
        <hr size="1">
        
        <div class="layui-form-item">  
            <label class="layui-form-label">服务主题：</label>
            <div class="layui-input-inline" style="width: 250px;">
                <input type="text" name="general" lay-verify="required" style="width: 250px;"
                    placeholder="请输入服务主题" autocomplete="off" class="layui-input" />
            </div>
            <label class="layui-form-label" style="margin-left: 150px;">服务时间：</label>
            <div class="layui-input-inline" style="width: 250px;">
                <input type="text" name="createDate" placeholder="请选择时间" class="layui-input" id="datetimeSelect"  />
            </div>
        </div>
        
        <hr size="1">
        
        <div class="layui-form-item">  
            <label class="layui-form-label">服务类型：</label>
            <div class="layui-input-inline" style="width: 250px;">
                <select id="typeSelect" name="type" lay-search  lay-verify="required">
                    <option value="">--数据加载中--</option>
                </select>
            </div>
            <label class="layui-form-label" style="margin-left: 150px;">紧急程度：</label>
            <div class="layui-input-inline" style="width: 250px;">
                <select id="emergencySelect" name="emergency" lay-search  lay-verify="required">
                    <option value="">--数据加载中--</option>
                </select>
            </div>
        </div>
        
        <hr size="1">
        
        <div class="layui-form-item">  
            <label class="layui-form-label">联系电话：</label>
            <div class="layui-input-inline" style="width: 250px;">
                <input type="text" name="phoneNumber" lay-verify="required" style="width: 250px;"
                    placeholder="请输入联系电话" autocomplete="off" class="layui-input" />
            </div>
        </div>
        
        <hr>
        <div class="layui-form-item">
            <label class="layui-form-label">服务内容：</label>
            <div class="layui-input-block" id="requestContent" style="width: 730px;">
                
            </div>
        </div>
        
        <div class="layui-form-item">
	        <div class="layui-input-block">
	            <button type="reset" class="layui-btn layui-btn-warm" style="margin-left: 250px;">重置</button>
	            <button type="button" name="sumbitBtn" lay-submit lay-filter="sumbitBtn" class="layui-btn">保存</button>
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
        
        loadAllSelect();
        
        //渲染日期选择器
        laydate.render({
            elem : '#datetimeSelect',
            type : 'datetime'
        });
        
        var E = window.wangEditor;
        var editor = new E('#requestContent');
        editor.customConfig.zIndex = 100;
        //上传文件的服务端接口
        editor.customConfig.uploadImgServer = '${pageContext.request.contextPath}/upload/images';
        //上传文件的参数名字
        editor.customConfig.uploadFileName = 'images'; 
        editor.create();
        
    
        //从url中获取参数，判断为服务更新还是编辑
        var data = getParm();
        if(data.id != null) {//编辑服务
        	var id = data.id;
        	editService(id);
        	submitUrl = "${pageContext.request.contextPath}/service/editService";
        }else{
        	//添加服务
        	submitUrl = "${pageContext.request.contextPath}/service/addService";
        }
        
        //根据ID获取服务，并填充到表单数据
        function editService(id){
        	$.ajax({
        		type: "POST",
        		url : "${pageContext.request.contextPath}/service/findServiceById",
        		data: { "id": id},
        		dataType : "json",
        		success : function(data){
        			if(data.success){
        				console.log(data.data);
        				form.val('myform',data.data);
        				$("input[name='createTime']").val(data.data.createDate);
        				editor.txt.html(data.data.request);
        			}
        			form.render('select');
        		}
        	});
        }
        
        //根据获取到的数据,填充到表单
        
        
        
        //点击保存事件
		form.on('submit(sumbitBtn)',function(data){
		    var formdata = data.field;
		    formdata.request = editor.txt.html();
		    console.log(formdata);
		    $.ajax({
		        type: "POST",
		        url: submitUrl,
		        data: formdata,
		        dataType: "json",
		        success: function(data){
		            if(data.success){//成功
		            	top.layer.msg(data.msg); 
		                //关闭当前弹出层
		                var thisindex = parent.layer.getFrameIndex(window.name);
		                parent.layer.close(thisindex);
		            }
		        },
		        error:function(){
		            top.layer.msg("服务器开小差了，请稍后再试...");
		        }
		   });
		    
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
            //加载下拉框完成后，要重新进行渲染
            form.render('select');
        }
        
        
        
        
    });
</script>
</html>