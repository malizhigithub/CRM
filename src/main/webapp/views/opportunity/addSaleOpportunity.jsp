<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1, maximum-scale=1">
<title>添加销售机会</title>
<link rel="stylesheet" href="../../layui/css/layui.css">
<script src="../../layui/layui.js"></script>
<script src="../../js/myutil.js"></script>
<script src="../../js/wangEditor.min.js"></script>
<style type="text/css">
.layui-form-item{
    margin-top: 15px;
    margin-left:60px;
}
</style>
</head>
<body>
   
    <!-- 编辑添加框 -->
    <!-- form外面要有一个div-->
    <form class="layui-form" lay-filter="myform">
        
        <input type="hidden" name="id" value=""/>
        <input type="hidden" name="status" value=""/>
        <input type="hidden" name="creater" value=""/>
        <input type="hidden" name="contactPhone" value=""/>
        <input type="hidden" name="createDate" value=""/>
        <input type="hidden" name="managerId" value=""/>
        <input type="hidden" name="allotDate" value=""/>
        <input type="hidden" name="status" value=""/>
        <input type="hidden" name="deleteStatus" value=""/>
        <input type="hidden" name="customerId" value=""/>
        <input type="hidden" name="contactId" value=""/>
        
        <div class="layui-form-item">  
            <label class="layui-form-label">客户：</label>
            <div id="customerSelectDiv" class="layui-input-inline" style="width: 250px">
                <select id="customerSelect" name="customerId" lay-search  lay-verify="required" lay-filter="customerSelect">
                	<option>数据加载中...</option>
                </select>
            </div>
            <label class="layui-form-label">机会来源:</label>
            <div id="sourceSelectDiv" class="layui-input-inline" style="width: 250px;">
                <select id="sourceSelect" name="source" lay-search  lay-verify="required">
                	<option>数据加载中...</option>
                </select>
            </div>
        </div>
        
        <hr size="1">
        
        <div class="layui-form-item">  
            <label class="layui-form-label">成功机率:</label>
            <div class="layui-input-inline" style="width: 250px;">
                <select id="successSelect" name="success" lay-search  lay-verify="required">
                	<option value=""></option>
					<option value="30">30%</option>
					<option value="40">40%</option>
					<option value="50">50%</option>
					<option value="60">60%</option>
					<option value="70">70%</option>
					<option value="80">80%</option>
					<option value="90">90%</option>
                </select>
            </div>
            <label class="layui-form-label" style="width: 90px;">客户联系人：</label>
            <div id="contactIdSelectDiv" class="layui-input-inline" style="width: 250px;">
                <select id="contactIdSelect" name="contactId" lay-search  lay-verify="required" lay-filter="contactIdSelect">
                </select>
            </div>
        </div>
        
        <hr size="1">

		<div class="layui-form-item">  
            <label class="layui-form-label">机会状态:</label>
            <div class="layui-input-inline" style="width: 250px;">
                <select id="statusSelect" name="status" lay-search  lay-verify="required">
                </select>
            </div>
        </div>

        <hr size="1">
        
        <div style="margin-top: 15px;margin-left:35px;" class="layui-form-item">
            <label class="layui-form-label">机会概要：</label>
            <div class="layui-input-block" style="width: 600px;">
                <textarea name="general" style="height:100px;" placeholder="请输入机会概要的内容，不超过100字" class="layui-textarea"  lay-verify="required"></textarea>
            </div>
        </div>
        
        <hr size="1">
        
        <div style="margin-top: 15px;margin-left:35px;" class="layui-form-item">
            <label class="layui-form-label">机会描述：</label>
            <div id="descriptionContent" class="layui-input-block" style="width: 700px;">
                <!-- <textarea name="description" style="height:200px;" placeholder="请输入机会描述的内容" class="layui-textarea"  lay-verify="required"></textarea> -->
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
        var layer = layui.layer;
    	var load = layer.load(3); //加载等待样式
        var form = layui.form;
        var laydate = layui.laydate;
        var $ = layui.$;  
        //form.render('select');
        
        //销售机会描述富文本框
        var E = window.wangEditor;
        var editor = new E('#descriptionContent');
        editor.customConfig.zIndex = 100;
        //上传文件的服务端接口
        editor.customConfig.uploadImgServer = '${pageContext.request.contextPath}/upload/images';
        //上传文件的参数名字
        editor.customConfig.uploadFileName = 'images';
        
        editor.create();
        
        
        //表单提交的地址
        var submitUrl = '';
        
        //渲染日期选择器
        /* laydate.render({
            elem : '#datetimeSelect',
            type : 'datetime'
        }); */
        
        
        //渲染所有下拉框
        loadAllSelect();
        
        //从url中获取参数，判断机会是更新还是添加
        var data = getParm();
        if(data.id != null && data.id != "" ) { //编辑销售机会
        	var id = data.id;
        	submitUrl = "${pageContext.request.contextPath}/opportunity/updateSaleOpportunity";
        	
        	//设置客户下拉框不能修改
        	var html = "<input id='customerSelect' class='layui-input' value='' />";
        	$("#customerSelectDiv").html(html);
        	$("#customerSelect").attr("readonly","readonly");
        	//设置联系人下拉框不能修改
        	html = "<input id='contactIdSelect' value='' class='layui-input' />"
        	$("#contactIdSelectDiv").html(html);
        	$("#contactIdSelect").attr("readonly","readonly");
        	
        	//填充表单
        	updateSaleOpportunity(id);
        	
        }else{//添加销售机会
        	submitUrl = "${pageContext.request.contextPath}/opportunity/addSaleOpportunity";
        }
        //渲染完毕，关闭等待样式
        layer.close(load);
        
        //根据ID获取销售机会实例，并填充到表单数据
        function updateSaleOpportunity(id){
        	$.ajax({
        		type: "POST",
        		url : "${pageContext.request.contextPath}/opportunity/findSaleOpportunity",
        		data: { "id": id},
        		dataType : "json",
        		success : function(result){
        			if(result.success){
        				console.log(result.data);
        				form.val('myform',result.data[0]);
        				//$("#contactIdSelect").append("<option selected value='"+result.data[0].contactId+"'>"+result.data[0].linkman.name+"</option>");
        				$("#customerSelect").val(result.data[0].customer.name);
        				$("#contactIdSelect").val(result.data[0].linkman.officePhone);
        				//设置富文本框的内容
        				editor.txt.html(result.data[0].description);
        			}
        			form.render('select');
        		}
        	});
        }
        
        //点击保存事件
		form.on('submit(sumbitBtn)',function(data){
		    var formdata = data.field;
		    console.log(editor.txt.html());
		    formdata.description = editor.txt.html();
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
                url : "${pageContext.request.contextPath}/opportunity/findAllCustomerByRole",
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
        
        //加载销售机会下拉框
        function loadSelect(selectName){
            var name = '';
            if(selectName == 'sourceSelect'){
                name = "销售机会来源";
            }
            if(selectName == 'statusSelect'){
            	name= '销售机会状态';
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
            loadCustomerSelect();//加载客户下拉框
            loadSelect('sourceSelect');//加载机会来源下拉框（数据字典）
            //加载下拉框完成后，要重新进行渲染
            form.render('select');
            //加载销售机会状态下拉框(数据字典)
            loadSelect('statusSelect');
        }
        
        form.on('select(customerSelect)', function(data){
        	  //console.log(data.elem); //得到select原始DOM对象
        	  //console.log(data.value); //得到被选中的值
        	  load = layer.load(3); //加载等待样式
        	  $.ajax({
        		  
        		  url:"${pageContext.request.contextPath}/opportunity/findLinkmanByCustomerId",
        		  data:{
        			  	id : data.value
        			  },
        		  type:"POST",
        		  dataType:"json",
        		  async : false,
        		  success : function(result){
        			  if(result.success){
		       			  var select = $("#contactIdSelect");
		       			  //清空原有的选项
		       			  select.html("");
		       			  $.each(result.data,function(index,item){
		       				  //level == 0 代表为主要联系人
		       				  if(item.level==0){
		       				  console.log(item);
			       				  select.append("<option selected value='"+item.id+"'>"+"(主要)"+item.name+"</option>");
		       				  }else{
			       				  select.append("<option value='"+item.id+"'>"+item.name+"</option>");
		       				  }
		       			  });
        			  }else{
        				  layer.msg("读取联系人数据出错,重新选择客户");
        			  }
        			  //重新渲染下拉框
        			  form.render('select');
        			  layer.close(load);
        		  } 
        	  });
        	  
        	  
        	});
        
    });
</script>
</html>