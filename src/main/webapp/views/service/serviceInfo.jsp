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
    margin-top: -1px;
    margin-left:60px;
    font-size: 16px;
}
.layui-input{
    border: none;
}
</style>
</head>
<body>
	<div class="layui-tab layui-tab-card">
		<ul class="layui-tab-title">
			<li class="layui-this">服务详情</li>
			<li>转交记录</li>
		</ul>
		<div class="layui-tab-content">
			<div class="layui-tab-item layui-show">
			    <form class="layui-form" lay-filter="myform">
			        <div style="width: 99.8%;height:35px;line-height:35px;border: 1px #f1f1f1 solid;background-color: #f5f5f5;" ><span style="margin-left: 28px;">基本信息</span></div>
			        
			        
			        <div class="layui-form-item" style="margin-top: 15px;">  
			        
			            <label class="layui-form-label">创建人：</label>
			            <div class="layui-input-inline" style="width: 250px">
			                <input type="text" class="layui-input" name="createrName" readonly="readonly" value="" />
			            </div>
			            
			            <label class="layui-form-label" style="margin-left: 150px;">处理人：</label>
			            <div class="layui-input-inline" style="width: 250px;">
			                <input type="text" class="layui-input" name="handlerName" readonly="readonly" value="" />
			            </div>
			        </div>
			    
			        <div style="width: 99.8%;height:35px;line-height:35px;border: 1px #f1f1f1 solid;background-color: #f5f5f5;" ><span style="margin-left: 28px;">服务信息</span></div>
			        
			        
			        <div class="layui-form-item" style="margin-top: 15px;">  
			            <label class="layui-form-label">客户名称：</label>
			            <div class="layui-input-inline" style="width: 320px">
			                <input type="text" class="layui-input" name="customerName" readonly="readonly" value="" />
			            </div>
			        </div>
			        
			        <hr>
			        
			        <div class="layui-form-item">  
			            <label class="layui-form-label">服务主题：</label>
			            <div class="layui-input-inline" style="width: 250px;">
			                <input type="text" class="layui-input" name="general" value="" readonly="readonly" />
			            </div>
			            <label class="layui-form-label" style="margin-left: 150px;">服务时间：</label>
			            <div class="layui-input-inline" style="width: 250px;">
			                <input type="text" name="createDate" class="layui-input" value=""  readonly="readonly" />
			            </div>
			        </div>
			        
			        <hr>
			        
			        <div class="layui-form-item">  
			            <label class="layui-form-label">服务类型：</label>
			            <div class="layui-input-inline" style="width: 250px;">
			                <input type="text" name="type" class="layui-input" value="" readonly="readonly"  />
			            </div>
			            <label class="layui-form-label" style="margin-left: 150px;">紧急程度：</label>
			            <div class="layui-input-inline" style="width: 250px;">
			                <input type="text" name="emergency" class="layui-input" value="" readonly="readonly" />
			            </div>
			        </div>
			        
			        <hr>
			        
			        <div class="layui-form-item">  
			            <label class="layui-form-label">联系电话：</label>
			            <div class="layui-input-inline" style="width: 250px;" >
			                <input type="text" name="phoneNumber" class="layui-input" value="" readonly="readonly" />
			            </div>
			        </div>
			        
			        <hr>
			        
			        <div class="layui-form-item">
			            <label class="layui-form-label">服务内容：</label>
			            <div class="layui-input-block" style="width: 700px;">
			                <div name="requestContent" style="padding: 9px 15px;"></div>
			            </div>
			        </div>
			        
			        
			        <div style="width: 99.8%;height:35px;line-height:35px;border: 1px #f1f1f1 solid;background-color: #f5f5f5;" ><span style="margin-left: 28px;">处理信息</span></div>
			        
			        
			        <div class="layui-form-item" style="margin-top: 15px;">  
			            <label class="layui-form-label">处理时间：</label>
			            <div class="layui-input-inline" style="width: 320px;">
			                <input type="text" name="handlerTime" class="layui-input" readonly="readonly" />
			            </div>
			        </div>
			        
			        <hr>
			        
			        <div class="layui-form-item">  
			            <label class="layui-form-label">处理过程：</label>
			            <div class="layui-input-block" style="width: 700px;">
			                <!-- <input type="text"  class="layui-input" readonly="readonly" /> -->
			                <div name="handleContent" style="padding: 9px 15px;"></div>
			            </div>
			        </div>
			        
			        <hr>
			        
			        <div class="layui-form-item">  
			            <label class="layui-form-label">处理结果：</label>
			            <div class="layui-input-block" style="width: 700px;">
			                <input type="text" name="handleResult" class="layui-input" readonly="readonly" />
			            </div>
			        </div>
			        
			        <hr style="height:2px;">
			        
			        <div class="layui-form-item">  
			            <label class="layui-form-label" >服务状态：</label>
			            <div class="layui-input-inline" style="width: 250px">
			                <input type="text" name="status" class="layui-input" value="" readonly="readonly" />
			            </div>
			        </div>
			        
			        <hr>
			        
			        <div class="layui-form-item" style="margin-bottom: 35px;">  
			            <label class="layui-form-label" style="width:100px;margin-left:-21px;">客户满意度：</label>
			            <div class="layui-input-inline" style="width: 320px">
			                <input type="text" name="degree" class="layui-input" value="" readonly="readonly" />
			            </div>
			        </div>          
			    </form>		
			</div>
			
			<div id="tabDiv" class="layui-tab-item">
				
			</div>
		</div>
	</div>
  
    
</body>

<script>
    layui.use(['form', 'laydate', 'element'], function() {
        var form = layui.form;
        var layer = layui.layer;
        var laydate = layui.laydate;
        var element = layui.element;
        var $ = layui.$;        
        //表单提交的地址
        var submitUrl = '';
 
        //从url中获取参数
        var serviceId = getParm().id;
        
        findServiceById(serviceId);
        findServiceTransfersByServiceId(serviceId);
        
        function findServiceById(id){
        	$.ajax({
                url : '${pageContext.request.contextPath}/service/findServiceById',
                async : false,
                type : 'POST',
                dataType : 'json',
                data : {
                    'id' : id
                },
                success : function(data){
                    if(data.success){
                        form.val('myform',data.data);
                        $("input[name='customerName']").val(data.data.customer.name);
                        $("input[name='createrName']").val(data.data.createrObject.account);
                        $("input[name='handlerName']").val(data.data.handlerObject.account);
                        $("div[name='handleContent']").html(data.data.handleContent);
                        $("div[name='requestContent']").html(data.data.request);
                    }else{
                        top.layer.msg("无法找到数据！");
                    }
                }
           });
           form.render();
       } 
       
       function findServiceTransfersByServiceId(serviceId){
    	   $.ajax({
    		  url : '${pageContext.request.contextPath}/service/findServiceTransfersByServiceId',
    		  async : false,
    		  type : 'POST',
    		  dataType : 'json',
    		  data : {
    			  'serviceId' : serviceId
    		  },
    		  success : function(data){
    			  console.log(data);
    			  $("#tabDiv").html("");
    			  var list = data.list;
    			  var content = '';
    			  if(list.length == 0){
    				  content = "<div align='center' style='color:#999999;'>暂无历史转移记录</div>";
    			  }
    			  for(i=0 ; i < list.length; i++){
    				  content += "<ul class='layui-timeline'>";
    				  content += "  <li class='layui-timeline-item'>";
    				  content += "      <i class='layui-icon layui-timeline-axis'>&#xe63f;</i>";
    				  content += "      <div class='layui-timeline-content layui-text'>";
    				  content += "          <h3 class='layui-timeline-title'>"+list[i].time+"</h3>";
    				  content += "          <p>";
    				  content += "              原处理人：" + list[i].oldManager.account + "<br/>";
    				  content += "              现处理人：" + list[i].newManager.account + "<br/>";
    				  content += "              转交原因：" + list[i].reason;     
    				  content += "          </p>";
    				  content += "      </div>";
    				  content += "  </li>";
    				  content += "</ul>";
    			  }  	
    			  $("#tabDiv").html(content);
    		  }
    	   });
       }
    });
</script>
</html>