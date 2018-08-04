<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1, maximum-scale=1">
<title>销售机会详情</title>
<link rel="stylesheet" href="../../layui/css/layui.css">
<script src="../../layui/layui.js"></script>
<script src="../../js/myutil.js"></script>
<style type="text/css">
.layui-form-item{
    margin-top: -1px;
    margin-left:60px;
}
.layui-input , .layui-textarea{
    border: none;
}
</style>
</head>
<body>
      
    <div class="layui-tab layui-tab-card">
		<div class="layui-tab-content">
			<div class="layui-tab-item layui-show">
			    <form class="layui-form" lay-filter="myform">
			        <div style="width: 99.8%;height:35px;line-height:35px;border: 1px #f1f1f1 solid;background-color: #f5f5f5;" ><span style="margin-left: 28px;">基本信息</span></div>
			        
			        
			        <div class="layui-form-item" style="margin-top: 15px;">  
			        
			            <label class="layui-form-label">创建人：</label>
			            <div class="layui-input-inline" style="width: 250px;">
			                <input type="text" class="layui-input" id="createrName" name="createrUser.name" readonly="readonly" value="" />
			            </div>
			            
			            
			            <label class="layui-form-label" style="margin-left: 150px;">创建时间：</label>
			            <div class="layui-input-inline" style="width: 250px;">
			                <input type="text" class="layui-input" name="createDate" readonly="readonly" value="" />
			            </div>
			            
			            <hr>
			            
			            <label class="layui-form-label">分配经理：</label>
			            <div class="layui-input-inline" style="width: 250px">
			                <input type="text" class="layui-input" id="managerName" name="manager.name" readonly="readonly" value="" />
			            </div>
			            
			            <label class="layui-form-label" style="margin-left: 150px;">分配时间：</label>
			            <div class="layui-input-inline" style="width: 250px;">
			                <input type="text" class="layui-input" name="allotDate" readonly="readonly" value="" />
			            </div>
			        </div>
			    
			        <div style="width: 99.8%;height:35px;line-height:35px;border: 1px #f1f1f1 solid;background-color: #f5f5f5;" ><span style="margin-left: 28px;">客户信息</span></div>
			        
			        
			        <div class="layui-form-item" style="margin-top: 15px;">  
			            
			            <label class="layui-form-label">客户名称：</label>
			            <div class="layui-input-inline" style="width: 250px">
			                <input type="text" class="layui-input" id="customerName" name="customer.name" readonly="readonly" value="" />
			            </div>
			            
			            <label class="layui-form-label" style="margin-left: 150px;">成功机率：</label>
			            <div class="layui-input-inline" style="width: 250px;">
			                <input type="text" class="layui-input" id="successName" name="success" readonly="readonly" value="" />
			            </div>
			            
			            
			        </div>
			        
			        <hr>
			        
			        <div class="layui-form-item">  
			            <label class="layui-form-label">联系人：</label>
			            <div class="layui-input-inline" style="width: 250px;">
			                <input type="text" class="layui-input" id="linkmanName" name="linkman.name" value="" readonly="readonly" />
			            </div>
			            <label class="layui-form-label" style="margin-left: 150px;">联系方式：</label>
			            <div class="layui-input-inline" style="width: 250px;">
			                <input type="text" id="linkmanMobilePhone" name="linkman.mobilePhone" class="layui-input" value=""  readonly="readonly" />
			            </div>
			        </div>
			        
			        <hr>
			        
			        <div class="layui-form-item">  
			            <label class="layui-form-label">机会来源：</label>
			            <div class="layui-input-inline" style="width: 250px;">
			                <input type="text" name="source" class="layui-input" value="" readonly="readonly"  />
			            </div>
			            <label class="layui-form-label" style="margin-left: 150px;">分配状态：</label>
			            <div class="layui-input-inline" style="width: 250px;">
			                <input type="text" name="status" class="layui-input" value="" readonly="readonly" />
			            </div>
			        </div>
			        
			        <hr>
			        
			        <div class="layui-form-item">
						<label class="layui-form-label">机会概要：</label>
						<div class="layui-input-block" style="width: 500px;">
							<textarea name="general" style="height: 100px;"
								class="layui-textarea"  readonly="readonly"></textarea>
						</div>
					</div>
			        
			        <hr>
			        
			        <div class="layui-form-item">
						<label class="layui-form-label">机会描述：</label>
						<div class="layui-input-block" style="width: 700px;">
							<!-- <textarea name="description" style="height: 200px;"
								class="layui-textarea" readonly="readonly"></textarea> -->
							<div name="description" style="padding: 9px 15px;"></div>
						</div>
					</div>
			        
			        <hr>
			                
			    </form>		
			</div>
			
			<div id="tabDiv" class="layui-tab-item">
				
			</div>
		</div>
	</div>
    
</body>

<script>
    layui.use(['form', 'laydate'], function() {
        var layer = layui.layer;
    	var load = layer.load(3); //加载等待样式
        var form = layui.form;
        var laydate = layui.laydate;
        var $ = layui.$;  
        //form.render('select');
        
        //表单提交的地址
        var submitUrl = '';
        
        //从url中获取参数，判断机会是更新还是添加
        var data = getParm();
        if(data.id != null && data.id != "") {//编辑销售机会
        	var id = data.id;
        	submitUrl = "${pageContext.request.contextPath}/opportunity/updateSaleOpportunity";
        	//设置客户下拉框不能修改
        	$("#customerSelect").attr("disabled","disabled").css("background-color","#EEEEEE;");
        	//设置联系人下拉框不能修改
        	$("#contactIdSelect").attr("disabled","disabled").css("background-color","#EEEEEE;");
        	
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
        		async : false,
        		dataType : "json",
        		success : function(result){
        			if(result.success){
        				console.log(result.data);
        				form.val('myform',result.data[0]);
        				$("#createrName").val(result.data[0].createrUser.realName);
        				$("#linkmanName").val(result.data[0].linkman.name);
        				$("#linkmanMobilePhone").val(result.data[0].linkman.mobilePhone);
        				$("#managerName").val(result.data[0].manager.realName);
        				$("#customerName").val(result.data[0].customer.name);
        				$("#successName").val(result.data[0].success+'%');
        				$("div[name='description']").html(result.data[0].description);
        			}
        		}
        	});
        }
        
        
    });
</script>
</html>