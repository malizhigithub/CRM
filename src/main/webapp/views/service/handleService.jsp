<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1, maximum-scale=1">
<title>处理服务</title>
<link rel="stylesheet" href="../../layui/css/layui.css">
<script src="../../layui/layui.js"></script>
<script src="../../layui/layui.js"></script>
<script src="../../js/myutil.js"></script>
<script src="../../js/wangEditor.min.js"></script>
<style type="text/css">
.layui-form-item{
    margin-top: 16px;
    margin-left:60px;
    font-size: 16px;
}
</style>
</head>
<body>
   
    <!-- 编辑添加框 -->
    <!-- form外面要有一个div-->
    <form class="layui-form" lay-filter="myform">
        <div style="width: 99.8%;height:35px;line-height:35px;border: 1px #f1f1f1 solid;background-color: #f5f5f5;" ><span style="margin-left: 28px;">处理信息</span></div>
        
        <input type="hidden" name="id" value=""/>
        <input type="hidden" name="status" value=""/>
        <input type="hidden" name="creater" value=""/>
        
        <div class="layui-form-item">  
            <label class="layui-form-label">客户：</label>
            <div class="layui-input-inline" style="width: 320px">
                <input type="hidden" name="customerId" value="" />
                <input type="text" name="customerName" class="layui-input" readonly="readonly" value="" />
            </div>
        </div>
        
        <hr size="1">
        
        <div class="layui-form-item">  
            <label class="layui-form-label">处理时间：</label>
            <div class="layui-input-inline" style="width: 320px;">
                <input type="text" name="handlerTime" placeholder="请选择时间" class="layui-input" id="datetimeSelect"  />
            </div>
        </div>
        
        <hr size="1">
        
        <div class="layui-form-item">  
            <label class="layui-form-label">处理过程：</label>
            <div id="handleContent" class="layui-input-block" style="width: 730px;">
                
            </div>
        </div>
        
        <hr size="1">
        
        <div class="layui-form-item">  
            <label class="layui-form-label">处理结果：</label>
            <div class="layui-input-block" style="width: 730px;">
                <textarea name="handleResult" style="height:150px;" placeholder="请输入处理的结果" class="layui-textarea" ></textarea>
            </div>
        </div>
        
        <hr>
        
        <div class="layui-form-item">  
            <label class="layui-form-label" >更新状态：</label>
            <div class="layui-input-inline" style="width: 320px">
                <select id="statusSelect" name="status" lay-search  lay-verify="required">
                    <option value="">--数据加载中--</option>
                </select>
            </div>
        </div>
        
         <hr>
        
        <div class="layui-form-item">  
            <label class="layui-form-label" style="width: 100px;margin-left: -20px;">客户满意度：</label>
            <div class="layui-input-inline" style="width: 320px">
                <select id="degreeSelect" name="degree" lay-search  lay-verify="">
                    <option value="">--数据加载中--</option>
                </select>
            </div>
        </div>
        
        <div class="layui-form-item" style="margin-top: 30px;">
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
        
        form.render('select');

        //表单提交的地址
        var submitUrl = '';
        
        //渲染日期选择器
        laydate.render({
            elem : '#datetimeSelect',
            type : 'datetime'
        });
        //渲染所有下拉框
        loadAllSelect();
        
        var E = window.wangEditor;
        var editor = new E('#handleContent');
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
        				$("input[name='handleTime']").val(data.data.handlerTime);
        				$("input[name='customerName']").val(data.data.customer.name);
        				//设置富文本框的内容
        				editor.txt.html(data.data.handleContent);
        			}
        			form.render('select');
        		}
        	});
        }   
        
        //点击保存事件
		form.on('submit(sumbitBtn)',function(data){
		    var formdata = data.field;
		    console.log(editor.txt.html());
		    formdata.handleContent = editor.txt.html();
		    console.log(formdata);
		    if(formdata.status == '已归档' ){
		    	layer.confirm('归档后的记录将无法进行编辑等操作，确定要归档吗？', function(index) {
                    layer.close(index);
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
                });
		    }else{
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
		    }
		   return false; 
		});

        
        //加载类型下拉框
        function loadSelect(selectName){
            var name = '';
            if(selectName == 'statusSelect'){
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
            loadSelect('statusSelect');
            loadSelect('degreeSelect');
            //加载下拉框完成后，要重新进行渲染
            form.render('select');
        }
        
        
        
    });
</script>
</html>