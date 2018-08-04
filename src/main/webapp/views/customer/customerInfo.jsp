<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户信息</title>
	<link rel="stylesheet" href="../../layui/css/layui.css">
	<script src="../../js/myutil.js"></script>
	<script src="../../layui/layui.js"></script>
	<style type="text/css">
	   .my-table-class{}
	   .my-table-class tr td:nth-child(even){
           text-align: left;
       }
	   .my-table-class tr td:nth-child(odd){
	       text-align: right;
	   }
	</style>
</head>
<body >
<div style="width: 96%;margin-left: 2%;">
	<div class="layui-tab layui-tab-card">
	    <!-- 详细资料 -->
	    <div class="layui-tab-item  layui-show">

            <table class="layui-table" lay-skin="col">
                <colgroup>
				    <col width="20%">
				    <col width="30%">
				    <col width="20%">
                    <col width="30%">
				</colgroup>
				<!-- <thead>
				    <tr>
				      <th>公司信息</th>
				      <th></th>
				      <th></th>
				      <th></th>
				    </tr> 
				</thead> -->
                <tbody class="my-table-class">
                    <tr>
				      <td>客户名称：</td>
				      <td id="customer-name"></td>
				      <td></td>
                      <td></td>
				    </tr>
				    <tr>
                      <td>客户创建者：</td>
                      <td id="creater"></td>
                      <td>客户创建时间：</td>
                      <td id="createTime"></td>
                    </tr>
				    <tr>
                      <td>公司电话：</td>
                      <td id="companyPhone"></td>
                      <td>意向产品：</td>
                      <td id="productId"></td>
                    </tr>
                    <tr>
                      <td>客户状态：</td>
                      <td id="status"></td>
                      <td>客户类别：</td>
                      <td id="type"></td>
                    </tr>
                    <tr>
                      <td>客户来源：</td>
                      <td id="source"></td>
                      <td>客户等级：</td>
                      <td id="level"></td>
                    </tr>
                    <tr>
                      <td>客户信用度：</td>
                      <td id="credit"></td>
                      <td>客户成熟度：</td>
                      <td id="maturity"></td>
                    </tr>
                    <tr>
                      <td>客户所在地区：</td>
                      <td id="area"></td>
                      <td>邮政编码：</td>
                      <td id="postCode"></td>
                    </tr>
                    <tr>
                      <td colspan="1">客户详细地址：</td>
                      <td colspan="3" id="companyAddress"></td>
                    </tr>
                    <tr>
                      <td colspan="1">客户相关资料：</td>
                      <td colspan="3" id="document"></td>
                    </tr>
                    <tr>
                      <td colspan="1">客户介绍：</td>
                      <td colspan="3" id="description"></td>
                    </tr>
                    <tr>
                      <td>传真地址：</td>
                      <td id="faxAddress"></td>
                      <td>公司网站：</td>
                      <td id="companyWebsite"></td>
                    </tr>
                    <tr>
                      <td>公司法人：</td>
                      <td id="corporation"></td>
                      <td>开户银行：</td>
                      <td id="depositBank"></td>
                    </tr>
                    <tr>
                      <td colspan="1">银行账户：</td>
                      <td colspan="3" id="bankAccount"></td>
                    </tr>
                    <tr>
                      <td colspan="1">年营业额：</td>
                      <td colspan="3" id="annualSale"></td>
                    </tr>
                    <tr>
                      <td colspan="1">营业执照注册号：</td>
                      <td colspan="3" id="licenseNumber"></td>
                    </tr>
                    <tr>
                      <td colspan="1">地税登记号：</td>
                      <td colspan="3" id="landTaxNumber"></td>
                    </tr>
                    <tr>
                      <td colspan="1">国税登记号：</td>
                      <td colspan="3" id="nationalTaxNumber"></td>
                    </tr>
                </tbody>
            </table>
            <!--endprint-->
        </div>
        
        
	 </div>

</div>
	
<script type="text/javascript">
layui.use(['table'],function(){
	var layer = layui.layer;
	var table = layui.table;
	var $ = layui.$;
	
	var parm = getParm();

	//进行详细信息显示
	getCustomerInfo();
	
	//加载客户详细信息
	function getCustomerInfo(){
		var loadindex = layer.load(2);
		$.ajax({
	        type: "POST",
	        url: '${pageContext.request.contextPath}/customer/find',
	        data: {'id':parm.id},
	        dataType: "json",
	        success: function(data){
	        	if(data.success){
	                var customer = data.data;
	                $('#customer-name').text(customer.name);
	                $('#type').text(customer.type);
	                $('#level').text(customer.level);
	                $('#status').text(customer.status);
	                $('#credit').text(customer.credit); 
	                $('#area').text(customer.area); 
	                $('#companyAddress').text(customer.companyAddress); 
	                $('#companyPhone').text(customer.companyPhone); 
	                $('#postCode').text(customer.postCode); 
	                $('#faxAddress').text(customer.faxAddress); 
	                $('#companyWebsite').text(customer.companyWebsite); 
	                $('#licenseNumber').text(customer.licenseNumber);
	                $('#corporation').text(customer.corporation);
	                $('#annualSale').text(customer.annualSale);
	                $('#depositBank').text(customer.depositBank);
	                $('#bankAccount').text(customer.bankAccount);
	                $('#landTaxNumber').text(customer.landTaxNumber);
	                $('#nationalTaxNumber').text(customer.nationalTaxNumber);
	                $('#source').text(customer.source);
	                $('#description').text(customer.description);
	                $('#maturity').text(customer.maturity);
	               //添加文件下载按钮
                    if(customer.document != null && customer.document != ''){
                        var document = customer.document;
                        var filename = '' + customer.name + '.' + document.split(".")[1];
                        var html = '<a class="layui-btn" style="margin-left:30px;" href="${pageContext.request.contextPath}/upload/' + customer.document + '"'
                                    + ' download="' + filename + '">下载文件</a>';
                        html = '' + document +  html;
                        //$('#document').text('asdf');
                        $('#document').html(html);
                    }
	                
	                if(customer.manager != null){
	                	$('#managerId').text(customer.manager.account);
	                }
	                
	                if(customer.product != null){
	                	$('#productId').text(customer.product.name);
	                }
	                
	                if(customer.createrObject != null){
	                	$('#creater').text(customer.createrObject.realName);
	                }
	                
	                $('#createTime').text(localDateTimeToStr(customer.createTime));
	            }else{
	            	layer.msg('获取不到客户');
	            }
	        	layer.close(loadindex);
	        	
	        },
	        error:function(){
	            layer.msg("服务器开小差了，请稍后再试...");
	        }
	    });
	}
	
	
	//打印功能
	$('#print-btn').click(function(){
		if(printstatus){
			  window.pritn();
			  return;
		}
		bdhtml=window.document.body.innerHTML;//获取当前页的html代码
		console.log(bdhtml);
        sprnstr="<!--startprint-->";//设置打印开始区域
        eprnstr="<!--endprint-->";//设置打印结束区域
        prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+17); //从开始代码向后取html
        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html
        window.document.body.innerHTML=prnhtml;
        window.print('aaa');    
        window.document.body.innerHTML=bdhtml;
	});


	
	function localDateTimeToStr(time){
		var str = '';
		var len = time.length;
		switch(len){
			case 1: str = '' + time[0];break;
			case 2: str = '' + time[0] + '-' + time[1];break;
			case 3: str = '' + time[0] + '-' + time[1] + '-' + time[2];break;
			case 4: str = '' + time[0] + '-' + time[1] + '-' + time[2] + '   ' + time[3];break;
			case 5: str = '' + time[0] + '-' + time[1] + '-' + time[2] + '   ' + time[3] + ':' + time[4];break;
			case 6: str = '' + time[0] + '-' + time[1] + '-' + time[2] + '   ' + time[3] + ':' + time[4] + ':' + time[5];break;
		}
		return str;
	}
	
});
</script>
		
</body>
</html>