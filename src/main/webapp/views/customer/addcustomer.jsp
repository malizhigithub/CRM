<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户编辑</title>
	<link rel="stylesheet" href="../../layui/css/layui.css">
	<script src="../../js/myutil.js"></script>
	<script src="../../layui/layui.js"></script>
</head>
<body >
<div style="width: 96%;margin-left: 2%;">

<hr>
<div style="width: 100%;height: 20px;background-color: #efff57;padding: 6px 0px;margin-bottom: 5px;">
	<p style="width: 100%;line-height: 20px;margin-left: 20px;">公司信息</p>
</div>
<form class="layui-form "  lay-filter="customer-form"> 
    
    <!-- 客户ID -->
    <input type="hidden" name="id" value="0">
    
    <div class="layui-form-item">
        <!-- 客户名称 -->  
        <label class="layui-form-label">客户名称<strong style="color: red">*</strong>：</label>
        <div class="layui-input-inline">
          <input type="text" name="customerName" lay-verify="required" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux" id="customer-name-msg"></div>
    </div>		

   <div class="layui-form-item">
        <!-- 公司电话 --> 
        <label class="layui-form-label">公司电话：</label>
        <div class="layui-input-inline">
          <input type="text" name="companyPhone"  class="layui-input">
        </div>
        
        <!-- 客户状态 --> 
        <label class="layui-form-label">客户状态：</label>
        <div class="layui-input-inline">
          <select name="status">
                <option value="">--数据加载中--</option>
          </select>
        </div>
        <div class="layui-form-mid layui-word-aux">
            <a href="#" name="客户状态" id="field-status"  style="color: blue;">
                <i class="layui-icon  layui-icon-add-1"></i>编辑
            </a>
        </div>  
    </div>            

    <div class="layui-form-item">
        <!-- 意向产品 -->  
        <label class="layui-form-label">意向产品：</label>
        <input type="hidden" name="productId"  class="layui-input">
        <div class="layui-input-inline">
            <span style="color: #999999;line-height: 38px;" id="choose-product-name">产品名称</span>
            <a href="#" style="color: blue;" id="choose-product">
                <i class="layui-icon  layui-icon-add-1"></i>选择
            </a>
        </div>
        
        <!-- 客户类别 --> 
        <label class="layui-form-label">客户类别：</label>
        <div class="layui-input-inline">
	      <select name="type">
	           <option value="">--数据加载中--</option>
	      </select>
	    </div>
        <div class="layui-form-mid layui-word-aux">
            <a href="#" name="客户类别" id="field-type"  style="color: blue;">
                <i class="layui-icon  layui-icon-add-1"></i>编辑
            </a>
        </div>	    	
    </div>	
	
    <div class="layui-form-item">
        <!-- 客户来源-->  
        <label class="layui-form-label">客户来源：</label>
        <div class="layui-input-inline" style="width: 135px;">
            <select name="source">
	            <option value="">--数据加载中--</option>
	        </select>
        </div>
        <div class="layui-form-mid layui-word-aux">
            <a href="#" name="客户来源" id="field-source"  style="color: blue;">
                <i class="layui-icon  layui-icon-add-1"></i>编辑
            </a>
        </div>          
        <!-- 客户等级 --> 
        <label class="layui-form-label">客户等级：</label>
        <div class="layui-input-inline">
	      <select name="customerLevel">
	        <option value="">--数据加载中--</option>
	      </select>
	    </div>	
        <div class="layui-form-mid layui-word-aux">
            <a href="#" name="客户等级" id="field-customerLevel"  style="color: blue;">
                <i class="layui-icon  layui-icon-add-1"></i>编辑
            </a>
        </div>  	    
    </div>	
	
    <div class="layui-form-item">
       <!-- 客户信用度-->  
       <label class="layui-form-label">客户信用度：</label>
       <div class="layui-input-inline" style="width: 135px;">
            <select name="credit">
	            <option value="">--数据加载中--</option>
            </select>
        </div>
         <div class="layui-form-mid layui-word-aux">
            <a href="#" name="客户信用度" id="field-credit"  style="color: blue;">
                <i class="layui-icon  layui-icon-add-1"></i>编辑
            </a>
        </div> 
        
               
        <!-- 客户成熟度 --> 
        <label class="layui-form-label">客户成熟度：</label>
        <div class="layui-input-inline">
          <select name="maturity">
            <option value="">--数据加载中--</option>
          </select>
        </div>  
        <div class="layui-form-mid layui-word-aux">
            <a href="#" name="客户成熟度" id="field-maturity"  style="color: blue;">
                <i class="layui-icon  layui-icon-add-1"></i>编辑
            </a>
        </div>         
    </div>	
    
    <div class="layui-form-item">
    	<!-- 客户所在地区 -->  
        <label class="layui-form-label">所在地区：</label>
        <div class="layui-input-inline">
          <input type="text" name="area"  class="layui-input">
        </div>
        
        <!-- 邮政编码 -->  
        <label class="layui-form-label">邮政编码：</label>
        <div class="layui-input-inline">
          <input type="text" name="postCode"  class="layui-input">
        </div>
    </div>
	
	<!-- 详细地址 -->
	<div class="layui-form-item">
		<label class="layui-form-label">详细地址：</label>
		<div class="layui-input-block">
			<input type="text" name="companyAddress" class="layui-input"  style="width: 500px;" />
		</div>
	</div>
	
	
	<!-- 相关文件 -->
	<div class="layui-form-item">
		<input type="hidden" name="document" value=""/>
		<label class="layui-form-label">相关文件：</label>
		<div class="layui-input-block">
			<div class="layui-inline layui-upload " >
				<button type="button" class="layui-btn layui-btn-normal" id="fileupload">选择文件</button>
				<span id="upload-filename" class="layui-inline" style="color: #999999"></span>
				<button type="button" class="layui-btn layui-btn-disabled layui-hide" id="upload-btn">开始上传</button>
			</div>
			<div class="layui-form-mid layui-word-aux">只可上传一个文件</div>
		</div>
	</div>
	
	<!-- 客户介绍 -->
	<div class="layui-form-item">
		<label class="layui-form-label">客户介绍：</label>
		<div class="layui-input-block">
			<textarea  name="description" class="layui-input"  style="width: 500px;height: 100px;" ></textarea>
		</div>
	</div>	
	
    <div class="layui-form-item">
    	<!-- 传真地址 -->  
        <label class="layui-form-label">传真地址：</label>
        <div class="layui-input-inline">
          <input type="text" name="faxAddress"  class="layui-input">
        </div>
        
        <!-- 公司网站 -->  
        <label class="layui-form-label">公司网站：</label>
        <div class="layui-input-inline">
          <input type="text" name="companyWebsite"  class="layui-input">
        </div>
    </div>	


    <div class="layui-form-item">
    	<!-- 法人 -->  
        <label class="layui-form-label">法人：</label>
        <div class="layui-input-inline">
          <input type="text" name="corporation"  class="layui-input">
        </div>
        
        <!-- 开户银行 -->  
        <label class="layui-form-label">开户银行：</label>
        <div class="layui-input-inline">
          <input type="text" name="depositBank"  class="layui-input">
        </div>
    </div>	
	
	<!-- 银行账号 -->
	<div class="layui-form-item">
		<label class="layui-form-label">银行账号：</label>
		<div class="layui-input-block">
			<input type="text" name="bankAccount" class="layui-input"  style="width: 500px;" />
		</div>
	</div>	
	
	<!-- 年营业额-->
	<div class="layui-form-item">
		<label class="layui-form-label">年营业额：</label>
		<div class="layui-input-block">
			<input type="text" name="annualSale" class="layui-input"  style="width: 500px;" />
		</div>
	</div>	
	
	
	<!-- 营业执照注册号 -->
	<div class="layui-form-item">
		<label class="layui-form-label">营业执照注册号：</label>
		<div class="layui-input-block">
			<input type="text" name="licenseNumber" class="layui-input"  style="width: 500px;" />
		</div>
	</div>

	<!-- 地税登记号 -->
	<div class="layui-form-item">
		<label class="layui-form-label">地税登记号：</label>
		<div class="layui-input-block">
			<input type="text" name="landTaxNumber" class="layui-input"  style="width: 500px;" />
		</div>
	</div>	
	
	<!-- 国税登记号 -->
	<div class="layui-form-item">
		<label class="layui-form-label">国税登记号：</label>
		<div class="layui-input-block">
			<input type="text" name="nationalTaxNumber" class="layui-input"  style="width: 500px;" />
		</div>
	</div>	
<div id="linkman-form">
<div style="width: 100%;height: 20px;background-color: #efff57;padding: 6px 0px;margin-bottom: 5px;">
	<p style="width: 100%;line-height: 20px;margin-left: 20px;">主要联系人</p>
</div>

	<input type="hidden" name="id">
	
	<div class="layui-form-item">
		<!-- 联系人名称 -->
		<label class="layui-form-label">姓名<strong style="color: red">*</strong>：</label>
		<div class="layui-input-inline">
			<input type="text" name="linkmanName" lay-verify="required" class="layui-input"  />
		</div>
		
		
 
        <label class="layui-form-label">生日：</label>
        <div class="layui-input-inline">
            <input type="text" name="birthday" lay-verify="required"  id="birthday" class="layui-input"  />
        </div>
	

	</div> 
	
	
    <div class="layui-form-item">   
        <!-- 性别 -->
        <label class="layui-form-label">性别：</label>
        <div class="layui-input-inline">
            <input type="radio" name="sex" class="layui-input" value="男" title="男"/>
            <input type="radio" name="sex" class="layui-input" value="女" title="女"/>
        </div>
    </div>  	
	
    <div class="layui-form-item">
        <!-- 联系人办公室电话-->
        <label class="layui-form-label">办公室电话：</label>
        <div class="layui-input-inline">
            <input type="text" name="officePhone"  class="layui-input"  />
        </div>
        
        <!-- 手机电话 -->
        <label class="layui-form-label">手机电话：</label>
        <div class="layui-input-inline">
            <input type="text" name="mobilePhone" class="layui-input" />
        </div>
    </div> 	


</div>
	
	
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button type="button" class="layui-btn" style="margin-left: 180px;">重置</button>
			<button type="button" name="customer-form-submit-btn" lay-submit lay-filter="customer-form-submit" class="layui-btn">保存</button>
		</div>
	</div>	
</div>				 
</form>

	
<script type="text/javascript">
layui.use(['form','upload','laydate'],function(){
	var form = layui.form;
	var layer = layui.layer;
	var upload = layui.upload;
	var laydate = layui.laydate;
	var $ = layui.$;

	var parm = getParm();
	
	var url = '${pageContext.request.contextPath}/customer/add';
	
	//点击保存事件
	form.on('submit(customer-form-submit)',function(data){
		var formdata = data.field;
		//console.log(formdata);
		
		//添加客户时检验客户名可用性
		if($('#customer-name-msg').text() == '客户名已存在' && parm.type == 'add'){
			layer.msg('客户名重复，请重新填写');
			return false;
		}

		//判断是否选择了文件
		if(data.field.file != null && data.field.file != ''){
			//文件存在，提交文件
			//console.log(data.field.file);
			$('#upload-btn').click();
			return false;
		}
		
		
		delete formdata.file;
		$.ajax({
            type: "POST",
            url: url,
            data: formdata,
            dataType: "json",
            success: function(data){
                top.layer.msg(data.msg);    //使用top显示
                if(data.success){//成功
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

	
	//获取客户状态字典并加载下拉框
	getSelectData('客户状态','status');
	
    //获取客户类别字典并加载下拉框
    getSelectData('客户类别','type');
    
    //获取客户来源字典并加载下拉框
	getSelectData('客户来源','source');
    
	//获取客户等级字典并加载下拉框
    getSelectData('客户等级','customerLevel');
	
    //获取客户信用度字典并加载下拉框
    getSelectData('客户信用度','credit');
    
    //获取客户成熟度字典并加载下拉框
    getSelectData('客户成熟度','maturity');
    
    
    function getSelectData(dictionaryName,selectName){
    	$.post('${pageContext.request.contextPath}/dictionary/find',{'name':dictionaryName},function(data){
            var d = data.data.dictionaryItems;
            var str = '<option value="">请选择</option>';
            for(var i=0;i<d.length;i++){
                str += '<option value="' + d[i].name + '">' + d[i].name + '</option>';
            }
            $('select[name=' + selectName + ']').html(str);
            form.render('select');
        }); 
    }
    
    
	//商品选择
	$('#choose-product').click(function(){
		layer.open({
			type:2,
			title:'商品选择',
			closeBtn:1,
			shadeClose:false,
			area:['auto','50%'],
			content:'${pageContext.request.contextPath}/views/customer/productchoose.jsp'
		});
	});

	
	
	//客户名检测
    $('input[name=customerName]').blur(function(){
    	var name = $('input[name=customerName]').val();
        $.post('${pageContext.request.contextPath}/customer/checkname',{'name':name},function(data){
        	if(data){
                $('#customer-name-msg').text('客户名已存在');
            }else{
                $('#customer-name-msg').text('客户名可用');
            }
        });
    });	

	//文件上传实现
	var uploadindex = 0;
	upload.render({
	  elem: '#fileupload'
	  ,url: '${pageContext.request.contextPath}/file/upload'
	  ,accept:'file'     //允许所有类型文件上传
	  ,size:10240        //上传大小限制为10M
	  ,auto:false        //不自动上传
	  ,number:1
	  ,bindAction:'#upload-btn'
	  ,choose:function(obj){
	  	obj.preview(function(index, file, result){
	  		$('#upload-filename').text(file.name);
	  	});
	  	$('#upload-btn').removeClass('layui-btn-disabled');
	  }
	  ,before:function(){
	  	uploadindex = layer.load(2);
	  	$('#upload-btn').addClass('layui-btn-disabled');
	  }
	  ,done: function(res){
	  	layer.close(uploadindex);
	  	if(res.status){
	  		//layer.msg("上传完成");
	  		$('input[name=document]').val(res.filename);
	  		$('input[name=file]').val(''); //将file输入框内容去除
	  		$('button[name=customer-form-submit-btn]').click();
	  	}else{
	  		layer.msg(res.msg);
	  	}
	
	  }
	});   

	//加载日期选择器
    laydate.render({
        elem: '#birthday' //指定元素
        ,type:'date'
        ,format:'yyyy-MM-dd'
        ,trigger: 'click'
      });
	
	
	$('a[id^=field]').click(function(){
		var name = this.name;
		var field = this.id.split('-')[1];
		editDictionary(name,field);
		
		return false;
	});
	
	
	//编辑字典
	function editDictionary(name,field){
		$.ajax({
            url:'${pageContext.request.contextPath}/dictionary/find',
            data:{'name':name},
            success:function(data){
                //console.log(name);
                //console.log(data);
                if(!data.success){
                	layer.msg('字典不存在，无法编辑');
                	return;
                }
                
                layer.open({
                    type: 2,
                    title: '字典子项',
                    area:['80%','90%'],
                    closeBtn: 1,
                    //area: '516px',
                    //skin: 'layui-bg-black', //没有背景色
                    shade:0.5,
                    shadeClose: false,
                    content: '${pageContext.request.contextPath}/views/dictionary/itemlist.jsp?typeid=' + data.data.id,
                    end:function(){
                    	getSelectData(name,field);
                    }
                  });
            }
      });
	}
	
});
</script>
		
</body>
</html>