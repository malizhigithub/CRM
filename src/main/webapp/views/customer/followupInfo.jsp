<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>跟踪记录</title>
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
       <table class="layui-table" lay-skin="col">
           <colgroup>
               <col width="30%">
               <col width="70%">
           </colgroup>
           <thead>
               <tr>
                 <th></th>
                 <th></th>
               </tr> 
           </thead>
           <tbody class="my-table-class">
               
                                                                                                    
           </tbody>
       </table>
</div>
	
<script type="text/javascript">
layui.use(['upload'],function(){
	var layer = layui.layer;
	var upload = layui.upload;
	var $ = layui.$;

	var parm = getParm();
	
	layer.load(2);
	
	$.post('${pageContext.request.contextPath}/followup/find',{'id':parm.id},function(data){
		var follow = data.data;
		var title = '' + follow.time[0] + '-' + follow.time[1] + '-' + follow.time[2] + '   ' + follow.time[3] + ':' +follow.time[4] + ':' +follow.time[5];
		var str = '';
		str += '<tr><td>客户名称：</td><td>' + follow.customer.name + '</td></tr>';
		str += '<tr><td>跟进时间：</td><td>' + title +  '</td></tr>';
	    str += '<tr><td>跟进人：</td><td>' + follow.manager.account + '</td></tr>';
	    str += '<tr><td>地点：</td><td>' + follow.address + '</td></tr> ';
	    str += '<tr><td>概要：</td><td>' + follow.general + '</td></tr>';
	    str += '<tr><td>结果：</td><td>' + follow.result + '</td></tr>';
	    str += '<tr><td>详细信息：</td><td>' + follow.content + '</td></tr>';
	    str += '<tr><td>备注：</td><td>' + follow.remark + '</td></tr>';
	    
	    if(follow.document != null && follow.document != ''){
	    	var down = '';
	    	var filename = title + '.' + follow.document.split(".")[1];
	    	var html = '<tr><td>相关文档：</td><td><a class="layui-btn" style="margin-left:30px;" href="${pageContext.request.contextPath}/upload/' + follow.document + '"'
            + ' download="' + filename + '">下载文件</a></td></tr>';
            str += html;
	    }else{
	    	str += '<tr><td>相关文档：</td><td></td></tr>'  ;
	    }
	    $('.my-table-class').html(str);
	    layer.closeAll('loading');
	});

});
</script>
		
</body>
</html>