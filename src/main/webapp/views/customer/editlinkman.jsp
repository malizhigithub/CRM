<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>联系人编辑</title>
	<link rel="stylesheet" href="../../layui/css/layui.css">
	<script src="../../js/myutil.js"></script>
	<script src="../../layui/layui.js"></script>
</head>
<body >
<div style="width: 96%;margin-left: 2%;">

	<form class="layui-form" lay-filter="linkman-form">
		
		<input type="hidden" name="id" value="0">
		<input type="hidden" name="customerId" value="-1">
		
		<div class="layui-form-item">
	        <label class="layui-form-label">名称：</label>
	        <div class="layui-input-inline">
	            <input type="text" name="name" lay-verify="required"  class="layui-input"  />
	        </div>
        </div>
	
		<div class="layui-form-item">
	        <label class="layui-form-label">职位：</label>
	        <div class="layui-input-inline">
	            <input type="text" name="position"  class="layui-input"  />
	        </div>
        </div>	
	
		<div class="layui-form-item">
	        <label class="layui-form-label">固定电话：</label>
	        <div class="layui-input-inline">
	            <input type="text" name="officePhone"  class="layui-input"  />
	        </div>
        </div>	
	
		<div class="layui-form-item">
	        <label class="layui-form-label">移动电话：</label>
	        <div class="layui-input-inline">
	            <input type="text" name="mobilePhone"  class="layui-input"  />
	        </div>
        </div>		

		<div class="layui-form-item">
	        <label class="layui-form-label">生日：</label>
	        <div class="layui-input-inline">
	            <input type="text" name="birthday" lay-verify="required"  id="birthday" class="layui-input"  />
	        </div>
        </div>
	
		<div class="layui-form-item">
	        <label class="layui-form-label">性别：</label>
	        <div class="layui-input-inline">
	          	<input type="radio" name="sex"  class="layui-input" title="男" value="男"/>
	        	<input type="radio" name="sex"  class="layui-input" title="女" value="女"/>
	        </div>  
        </div>	
	
		<div class="layui-form-item">
	        <label class="layui-form-label">备注：</label>
	        <div class="layui-input-inline">
	          	<textarea class="layer-input" name="remark" style="height: 100px;width: 290px"></textarea>
	        </div>  
        </div>		
	
		<div class="layui-form-item">
	        <label class="layui-form-label">级别：</label>
		        <div class="layui-input-inline">
		          <select name="level" lay-filter="level" id="level">
		            <option value="1">普通</option>
		            <option value="0">重要</option>
		          </select>
		        </div>  
        </div>		
	
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button type="reset" class="layui-btn" style="margin-left: 30px;">重置</button>
				<button type="button" name="form-submit-btn" lay-submit lay-filter="form-submit-btn" class="layui-btn">保存</button>
			</div>
		</div>	
	</form>
</div>
	
<script type="text/javascript">
layui.use(['form','upload','laydate'],function(){
	var form = layui.form;
	var layer = layui.layer;
	var upload = layui.upload;
	var laydate = layui.laydate;
	var $ = layui.$;

	var parm = getParm();
	
	var url = '${pageContext.request.contextPath}/linkman/add';
	
	
	//加载日期选择器
	laydate.render({
	    elem: '#birthday' //指定元素
	    ,type:'date'
	    ,format:'yyyy-MM-dd'
	    ,trigger: 'click'
	  });
	
	//保存点击事件
	form.on('submit(form-submit-btn)',function(data){
	
		var formdata = data.field;
		
		if(parm.type == 'add'){
			formdata.customerId = parm.customerId;
		}
		
		if(formdata.level == 0){
			layer.confirm('重要联系人只能设置一个，确定设置该联系人为重要联系人吗？',function(index){
				layer.close(index);
				submitData(formdata);
			});
			return false;
		}
		
		submitData(formdata);
		return false;
		
	});
	
	//提交数据
	function submitData(formdata){
		$.ajax({
			type:"POST",
			url:url,
			data:formdata,
			dataType: "json",
			success:function(data){
				layer.msg(data.msg);
				if(data.status){
					//关闭当前弹出层
                    var thisindex = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(thisindex);
				}
			},
			error:function(){
				layer.msg('服务器去云游了，请稍后再试...');
			}
		});
	}

	
	//如果是更新则从服务器读取最新的联系人数据
	if(parm.type == 'update'){
		url = '${pageContext.request.contextPath}/linkman/update';
		$.post('${pageContext.request.contextPath}/linkman/find',{'id':parm.id},function(data){
			var formdata = data.data;
			if(formdata.birthday != null){
				formdata.birthday = '' + formdata.birthday[0] + '-' + formdata.birthday[1] + '-' + formdata.birthday[2];
			}
			
			form.val('linkman-form',formdata);
			form.render();
			
			if(formdata.level == 0){
				$('#level').attr("disabled","disabled");
				form.render();
			}
			
		});
	}

	
});
</script>
		
</body>
</html>