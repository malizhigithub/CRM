<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
body{
    background: white;
}
.layui-card-header{
    border-left: 5px solid #009688;
}
.layui-container{
    margin-top:20px;
}

</style>

<div class="layui-container">
    <div class="layui-row">

	<!-- 服务器信息 -->
	<div class="layui-col-md12">
		<div class="layui-card">
			<div class="layui-card-header" >服务器</div>
			<div class="layui-card-body" >
				<table class="layui-table">
					<colgroup>
					    <col width="30%">
					    <col width="70%">
					</colgroup>
					<tbody >
						<tr>
						    <td>服务器IP</td>
						    <td id="server-ip"></td>
						</tr>
						<tr>
							<td>服务器URL</td>
							<td id="server-url"></td>
						</tr>
						<tr>
						    <td>服务器类型</td>
						    <td  id="server-type"></td>
						</tr>
						<tr>
						    <td>服务器时间</td>
						    <td  id="server-time"></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- 系统概览 -->

	<!-- 操作系统 -->
	<div class="layui-col-md12">
		<div class="layui-card">
			<div class="layui-card-header" >操作系统</div>
			<div class="layui-card-body" >
				<table class="layui-table">
					<colgroup>
					    <col width="30%">
					    <col width="70%">
					</colgroup>
					<tbody >
						<tr>
						    <td>操作系统名称</td>
						    <td  id="system-name"></td>
						</tr>
						<tr>
							<td>操作系统版本</td>
							<td   id="system-version"></td>
						</tr>
						<tr>
						    <td>用户名称</td>
						    <td   id="user-name"></td>
						</tr>
						<tr>
						    <td>用户主目录</td>
						    <td   id="user-home"></td>
						</tr>
						<tr>
						    <td>操作系统时区</td>
						    <td   id="system-timezone"></td>
						</tr>						
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- 操作系统-->

	<!-- 物理内存 -->
	<div class="layui-col-md12">
		<div class="layui-card">
			<div class="layui-card-header" >物理内存</div>
			<div class="layui-card-body" >
				<table class="layui-table">
					<colgroup>
					    <col width="30%">
					    <col width="70%">
					</colgroup>
					<thead>
						<tr>
							<td>物理内存总量</td>
							<td>内存使用率<!-- <button id="btn-memory" class="layui-btn" style="float:right">动态刷新</button> --></td>
						</tr>
					</thead>
					<tbody >
						<tr>
						    <td  id="memory-total"></td>
						    <td  id="memory-used" style="line-height: 40px;"></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- 物理内存-->
	
	<!-- CPU -->
	<div class="layui-col-md12">
		<div class="layui-card">
			<div class="layui-card-header" >CPU信息</div>
			<div class="layui-card-body" >
				<table class="layui-table">
					<colgroup>
					    <col width="30%">
					    <col width="70%">
					</colgroup>
					<thead>
						<tr>
							<td>总频率</td>
							<td>使用率<!-- <button id="btn-cpu" class="layui-btn" style="float:right">动态刷新</button> --></td>
						</tr>
					</thead>
					<tbody id="cpu-tbody">
						<tr>
						    <td>1000Mhz</td>
						    <td>
							</td>
						</tr>				
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- 操作系统-->
	
	<!-- 网卡 -->
	<div class="layui-col-md12">
		<div class="layui-card">
			<div class="layui-card-header" >网卡信息</div>
			<div class="layui-card-body" >
				<table class="layui-table">
					<colgroup>
					    <col width="30%">
					    <col width="70%">
					</colgroup>
					<thead>
						<tr>
							<td>网卡名称</td>
							<td>IP</td>
						</tr>
					</thead>
					<tbody id="net-tbody">
						<tr>
						    <td>操作系统名称</td>
						    <td>0.0.0.0</td>
						</tr>								
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- 网卡-->
	
	<!-- Java虚拟机-->
	<div class="layui-col-md12">
		<div class="layui-card">
			<div class="layui-card-header" >Java虚拟机</div>
			<div class="layui-card-body" >
				<table class="layui-table">
					<colgroup>
					    <col width="30%">
					    <col width="70%">
					</colgroup>
					<tbody >
						<tr>
						    <td>JDK安装路径</td>
						    <td id="java-path"></td>
						</tr>
						<tr>
							<td>JDK供应商</td>
							<td id="java-vendor"></td>
						</tr>
						<tr>
						    <td>JDK版本</td>
						    <td id="java-version"></td>
						</tr>
						<tr>
						    <td>JDK名称</td>
						    <td id="java-name"></td>
						</tr>
						<tr>
						    <td>虚拟机版本</td>
						    <td id="jvm-version"></td>
						</tr>
						<tr>
						    <td>虚拟机总内存</td>
						    <td id="jvm-total-memory"></td>
						</tr>
						<tr>
						    <td>虚拟机剩余内存</td>
						    <td id="jvm-free-memory"></td>
						</tr>																		
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- java虚拟机-->
	
	<!-- 数据库 -->
	<div class="layui-col-md12">
		<div class="layui-card">
			<div class="layui-card-header" >数据库</div>
			<div class="layui-card-body" >
				<table class="layui-table">
					<colgroup>
					    <col width="30%">
					    <col width="70%">
					</colgroup>
					<tbody >
						<tr>
						    <td>数据库类型</td>
						    <td id="database-type"></td>
						</tr>
						<tr>
							<td>数据库版本</td>
							<td id="database-version"></td>
						</tr>
						<tr>
						    <td>数据库驱动</td>
						    <td id="database-driver"></td>
						</tr>
						<tr>
						    <td>数据库驱动版本</td>
						    <td id="driver-version"></td>
						</tr>
						<tr>
						    <td>数据库URL</td>
						    <td id="jdbc-url"></td>
						</tr>						
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- 数据库-->
</div>
</div>
	
<script type="text/javascript">
layui.use(['element'],function(){
	var element = layui.element;
	var layer = layui.layer;
	var $ = layui.$;

	var cpuStatus = false;
	var cpuLoop = null;
	var memStatus = false;
	var memLoop = null;
	
	//进行详细信息显示
	getSystemInfo();
	
	//加载客户详细信息
	function getSystemInfo(){
		layer.load(2);
		$.ajax({
	        type: "POST",
	        url: '${pageContext.request.contextPath}/system/info',
	        dataType: "json",
	        success: function(data){
	        	layer.closeAll('loading');
				var info = data.serverInfo;
				
				console.log(info);
				//服务器信息
				$('#server-ip').html(info.serverIP);
	            $('#server-url').html(info.serverURL);
	            $('#server-type').html(info.serverType);
	            $('#server-time').html(new Date(info.serverTime).toLocaleString());
				
				
				//操作系统
				$('#system-name').html(info.osName);
	            $('#system-version').html(info.osVersion);
	            $('#user-name').html(info.userName);
	            $('#user-home').html(info.userHome);
	            $('#system-timezone').html(info.osTimeZone);

	            //物理内存
	            $('#memory-total').html(info.memTotal);
	            var memTotal = parseInt(info.memTotal.split('M')[0]);
	            var memUsed = parseInt(info.memUsed.split('M')[0]);
	            var memStr = '';
	            memStr = '<div class="layui-progress layui-progress-big" lay-showPercent="true" lay-filter="memory-progress">';
	            memStr +='<div class="layui-progress-bar layui-bg-blue" lay-percent="0%"></div></div>';
	            $('#memory-used').html(memStr);	 
	            element.progress('memory-progress',memUsed/memTotal*100 + '%');
	            
	            
	            
	            //CPU
	            //<tr><td>1000MHz</td><td id="cpu-i">0.0.0.0</td><t>
	            var cpuStr = '';
	            var usedList = [];
	            var cpuUsed = '';
	            for(var i=0;i<info.cpuNum;i++){
	            	
	            	cpuUsed = '<div class="layui-progress layui-progress-big" lay-showPercent="true" lay-filter="cpu-' + i + '">';
	            	cpuUsed +='<div class="layui-progress-bar layui-bg-blue" lay-percent="0%"></div></div>';
	            	cpuStr += '<tr><td>' + info.cpuList[i].cpuMhz + 'MHz</td><td>' + cpuUsed + '</td><t>';
	            	usedList[i] = info.cpuList[i].cpuCombined;
	            
	            }
	            $('#cpu-tbody').html(cpuStr);
	            for(var i=0;i<info.cpuNum;i++){
	            	element.progress('cpu-' + i, usedList[i]);
	            }
	            
				

	            //网卡信息
	            var netStr = '';
	            for(var i=0;i<info.netNum;i++){
	            	netStr += '<tr><td>' + info.netList[i].name + '</td><td>' + info.netList[i].address + '</td><t>'
	            }
	            $('#net-tbody').html(netStr);



	            //Java虚拟机
	            $('#java-path').html(info.javaPath);
				$('#java-vendor').html(info.javaVendor);
	            $('#java-version').html(info.javaVersion);
	            $('#java-name').html(info.javaName);
	            $('#jvm-version').html(info.jvmVersion);
	            $('#jvm-total-memory').html(info.jvmTotalMemory);
	            $('#jvm-free-memory').html(info.jvmFreeMemory);


	            //数据库
	            $('#database-type').html(info.databaseType);
				$('#database-version').html(info.databaseVersion);
	            $('#database-driver').html(info.databaseDriver);
	            $('#driver-version').html(info.driverVersion);
	            $('#jdbc-url').html(info.jdbcUrl);	            

	            
	            //setInterval(getMemory,1000);
	            //
	        },
	        error:function(){
	            layer.msg("服务器开小差了，请稍后再试...");
	        }
	    });
	}
	
	$('#btn-memory').click(function(){
		//true 执行中
		//false 停止中
		
		//执行刷新
		if(memStatus == false){
			$('#btn-memory').text('停止刷新');
			memStatus = true;
			memLoop = setInterval(getMemory,1000);
		}else{
			$('#btn-memory').text('动态刷新');
			memStatus = false;
			clearInterval(memLoop);
		}
	});
	
	function getMemory(){
		$.ajax({
	        type: "POST",
	        url: '${pageContext.request.contextPath}/system/memory',
	        dataType: "json",
	        success: function(data){
	            //物理内存
	            var memTotal = parseInt(data.total.split('M')[0]);
	            var memUsed = parseInt(data.used.split('M')[0]);
	            element.progress('memory-progress',memUsed/memTotal*100 + '%');
	        }
	    });
	}
	
	
	$('#btn-cpu').click(function(){
		//true 执行中
		//false 停止中
		
		//执行刷新
		if(cpuStatus == false){
			$('#btn-cpu').text('停止刷新');
			cpuStatus = true;
			cpuLoop = setInterval(getCpu,1000);
		}else{
			$('#btn-cpu').text('动态刷新');
			cpuStatus = false;
			clearInterval(cpuLoop);
		}
	});
	
	function getCpu(){
		$.ajax({
	        type: "POST",
	        url: '${pageContext.request.contextPath}/system/cpu',
	        dataType: "json",
	        success: function(data){
	        	for(var i=0;i<data.cpuList.length;i++){
	            	element.progress('cpu-' + i, data.cpuList[i].cpuCombined);
	            }
	        }
	    });
	}
	
});
</script>
