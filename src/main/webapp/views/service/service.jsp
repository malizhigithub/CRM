<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@taglib prefix="eShiro" tagdir="/WEB-INF/tags" %>  
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1, maximum-scale=1">
</head>
<body>
    <div class="layui-form" style="width:100%;margin-top: 20px;">
        <blockquote class="layui-elem-quote quoteBox">
                <shiro:hasPermission name="10001">
	                <div class="layui-inline">
	                    <button id="searchButton" class="layui-btn"><i class="layui-icon">&#xe615;</i>搜索</button>	            
		            </div>
	            </shiro:hasPermission>
	            <eShiro:hasAnyPermissions name="10002,10007">
		            <div class="layui-inline" id="add-btn-div">
			            <ul>
			                <li id="add-btn">
			                   <button class="layui-btn">
			                      <i class="layui-icon layui-icon-add-1"></i>新建
			                   </button>
			                </li>
		                </ul>
		                
		                <ul id="add-btn-more" style="z-index: 999;position: fixed;display: none;" class="layui-bg-green">
			                <shiro:hasPermission name="10002">
				                <li>
	                                <button id="addButton" class="layui-btn">
	                                  <i class="layui-icon layui-icon-add-1"></i>新建服务
	                               </button>
	                            </li>
			                </shiro:hasPermission>
			                
			                <shiro:hasPermission name="10007">
				                <li>
	                                <button id="addHandleButton" class="layui-btn">
	                                  <i class="layui-icon layui-icon-add-1"></i>新建处理
	                               </button>
	                            </li>
			                </shiro:hasPermission>
			                
		                </ul>
	                </div>
	            </eShiro:hasAnyPermissions>
                
                <eShiro:hasAnyPermissions name="10003,10005">
                   <div class="layui-inline" id="edit-btn-div">
	                   <ul>
	                        <li id="edit-btn">
	                           <button class="layui-btn layui-btn-normal">
	                              <i class="layui-icon layui-icon-edit"></i>修改
	                           </button>
	                        </li>
	                    </ul>
	                    
	                    <ul id="edit-btn-more" style="z-index: 999;position: fixed;display: none;" class="layui-bg-green">
	                        <shiro:hasPermission name="10003">
		                        <li>
	                                <button id="editButton" class="layui-btn layui-btn-normal">
	                                  <i class="layui-icon layui-icon-edit"></i>修改服务
	                               </button>
	                            </li>
	                        </shiro:hasPermission>
	                        
	                        <shiro:hasPermission name="10005">
		                        <li>
	                                <button id="editHandleButton" class="layui-btn layui-btn-normal">
	                                  <i class="layui-icon layui-icon-edit"></i>修改处理
	                               </button>
	                            </li>
	                        </shiro:hasPermission>
	                        
	                    </ul>
                    </div>
                    
                </eShiro:hasAnyPermissions>
                
                
	            <shiro:hasPermission name="10006">
		            <div class="layui-inline">
	                   <button id="deleteButton" class="layui-btn layui-btn layui-btn-danger"><i class="layui-icon">&#xe640;</i>删除</button>
	                </div>
	            </shiro:hasPermission>
	            
	            <shiro:hasPermission name="10004">
		            <div class="layui-inline">
	                   <button id="transferButton" class="layui-btn layui-btn layui-btn-warm"><i class="layui-icon layui-icon-senior"></i>转交</button>
	                </div>
	            </shiro:hasPermission>
        </blockquote>
    </div>
    
    <table id="serviceTable" lay-filter="serviceTable"></table>
    
    <!-- 编辑搜索框 -->
    <!-- form外面要有一个div-->
    <!--
    <div id="searchDiv" style="display: none;">
        <form class="layui-form" lay-filter="myform" >
            <div style="width: 99.8%;height:35px;line-height:35px;border: 1px #f1f1f1 solid;background-color: #f5f5f5;" ><span style="margin-left: 28px;">基本信息</span></div>

            <div class="layui-form-item">  
                <label class="layui-form-label">客户：</label>
                <div class="layui-input-inline" style="width: 320px">
                    <select id="customerSelect" name="customerId" lay-search  lay-verify="required">
                    </select>
                </div>
            </div>
        </form>
    </div>  -->


    <div id="myform-div" class="layui-hide">
        <form class="layui-form" lay-filter="transferForm" style="width: 99%">    
            <div style="width:100%; margin-top: 30px;"  class="layui-form-item" >  
                <label class="layui-form-label">客户经理：</label>
                <div class="layui-input-inline" style="width: 320px;">
                    <select id="managerSelect" name="newManagerId" lay-search lay-verify="required">
                    </select>
                </div>
            </div>
            <div style="width:100%; margin-top: 30px;"  class="layui-form-item">  
                <label class="layui-form-label">转交原因：</label>
                <div class="layui-input-inline" style="width: 500px;">
                    <textarea id="reasonText" name="reason" style="width:100%; height:200px;" placeholder="请输入转交的原因" class="layui-textarea" ></textarea>
                </div>
            </div>
            <button type="button" lay-submit lay-filter="formSubmit" id="submitButton" class="layui-hide"></button>    
        </form>
    </div>
    <div class="layui-hide">
	    <input type="text" name="customerInput"  />
	    <input type="text" name="typeInput"  />
	    <input type="text" name="statusInput"  />
	    <input type="text" name="degreeInput"  />
	    <input type="text" name="emergencyInput"  />
	    <button type="button" id="search-button-submit"></button>
    </div>
</body>

<script>
    layui.use([ 'table', 'form', 'laydate'], function() {
        var formUrl = null;
        var table = layui.table;
        var form = layui.form;
        var layer = layui.layer;
        var laydate = layui.laydate;
        var $ = layui.$;
        
	    //加载表格数据
        var tableload = layer.load(2);
        table.render({
            elem : '#serviceTable', //指定原始表格元素选择器（推荐id选择器）
            url : '${pageContext.request.contextPath}/service/findServices',
            even : true,
            id : 'serviceTable',
            cols : [ [
                {
                    type : 'checkbox'
                },
                {
                    field : "createDate",
                    title : "服务时间",
                    templet : function(data){
                        return str = '<a style="color:#178eb9;" href="javascript:" lay-event="serviceDetail">' +data.createDate + '</a>';
                    },
                    sort : true
                },
                {
                    field : "customer",
                    title : "客户名称",
                    templet : function(data){
                        return str = '<a style="color:#178eb9;" href="javascript:" lay-event="customerDetail">' +data.customer.name + '</a>';
                    },
                },
                {
                    field : "type",
                    title : "服务类型"
                },
                {
                    field : "general",
                    title : "服务概要"
                },
                {
                    field : "createrObject",
                    title : "创建人",
                    templet : function(data){
                        return str = '<a style="color:#178eb9;" href="javascript:" lay-event="userDetail">' +data.createrObject.account + '</a>';
                    }
                },
                {
                    field : "emergency",
                    title : "紧急程度",
                    templet : function(data){
                    	if(data.emergency == '非常紧急'){
                    		return "<span style='color:red;'>" + data.emergency + "</span>";
                    	}else if(data.emergency == '紧急'){
                    		return "<span style='color:blue;'>" + data.emergency + "</span>";
                    	}else{
                    		return data.emergency;
                    	}
                    }
                },
                {
                    field : "status",
                    title : "处理状态",
                    templet : function(data){
                    	if(data.status == '未处理'){
                    		return "<span style='color:red;'>" + data.status + "</span>";
                    	}else if(data.status == '已归档'){
                    		return "<span style='color:#999999;'>" + data.status + "</span>";
                    	}else{
                    		return "<span style='color:blue;'>" + data.status + "</span>";
                    	}
                    }
                }
            ] ],
            edit : "text", //单元格编辑类型（默认不开启）目前只支持：text（输入框）
            limit : 10,
            limits : [ 10, 20, 30, 40, 50, 60, 70, 80, 90 ],
            loading : true, //分页加载样式
            page : true, //是否开启分页
            done : function(res, curr, count) {
                layer.close(tableload);
            }
        });
        
        

        table.on('tool(serviceTable)', function(obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
            	showAddOrEditServicePage('编辑服务',data.id);
            } else if (obj.event === 'delete') {
                layer.confirm('确定要删除该服务吗？', function(index) {
                    layer.close(index);
                    deleteService(data);
                });
            } else if(obj.event === 'handle'){
            	showHandleServicePage('处理服务',data.id);
            }
        });   

        //添加服务
        $("#addButton").click(function(){
        	showAddOrEditServicePage("添加服务",-1);
        });

        
        //新建处理
        $("#addHandleButton").click(function(){
        	var checkStatus = table.checkStatus('serviceTable');
            var data = checkStatus.data;
            if(data.length < 1){
               layer.msg('请选择要处理的服务！');
            }else if(data.length > 1){
               layer.msg(' 同时只能处理一条服务，请只选择一个！');
            }else if(data[0].status == '已归档'){
                layer.msg("已归档的服务不能操作！");
            }else{
               showHandleServicePage('处理服务',data[0].id);
            }
        });
        
        $("#editButton").click(function(){
        	var checkStatus = table.checkStatus('serviceTable');
        	var data = checkStatus.data;
        	if(data.length < 1){
        	   layer.msg('请选择要修改的服务！');
        	}else if(data.length > 1){
        	   layer.msg(' 同时只能修改一条服务，请只选择一个！');
        	}else if(data[0].status == '已归档'){
                layer.msg("已归档的服务不能操作！");
            }else{
        	   showAddOrEditServicePage('修改服务',data[0].id);
        	}
        });
        
        $("#editHandleButton").click(function(){
        	var checkStatus = table.checkStatus('serviceTable');
            var data = checkStatus.data;
            if(data.length < 1){
               layer.msg('请选择要处理的服务！');
            }else if(data.length > 1){
               layer.msg(' 同时只能处理一条服务，请只选择一个！');
            }else if(data[0].status == '已归档'){
                layer.msg("已归档的服务不能操作！");
            }else{
            	showHandleServicePage('处理服务',data[0].id);
            }
        });

        //批量删除记录
        $("#deleteButton").click(function(){
            var checkStatus = table.checkStatus('serviceTable');
            var data = checkStatus.data;
            var ids = '';
            if(data.length < 1){
               layer.msg('请选择要删除的记录！');
            }else{
                layer.confirm('确定要删除'+data.length+'条记录吗？', function(index) {
                    layer.close(index);
                    //循环读取数据，并把id通过-连接起来
	                for(i = 0 ; i < data.length ; i++){
	                   ids += data[i].id + '-'; 
	                }
	                deleteServices(ids);
                });
            }
        });
        
        $("#transferButton").click(function(){
        	var checkStatus = table.checkStatus('serviceTable');
            var data = checkStatus.data;
            if(data.length < 1){
            	layer.msg("请选择要转交的记录！");
            }else if(data.length > 1){
            	layer.msg("同时只能转交一条服务，请只选择一个！");
            }else if(data[0].status == '已归档'){
                layer.msg("已归档的服务不能转交！");
            }else {
            	showTransferPage(data[0].id);
            }
        });
        
        //展示转发页面
        function showTransferPage(id){
        	var url ='${pageContext.request.contextPath}/views/service/transferService.jsp?id='+id;
        	layer.open({
                type : 2,
                title : '转交',
                content : url,
                clostbtn : 2 ,
                shadeClose: true,
                area : [ '800px', '430px' ],
                end : function() {
                    table.reload('serviceTable',{});
                }
            });
        }
        
        //监听表单提交按钮
        form.on('submit(formSubmit)', function(data) {
            var but = data.elem; //获取点击的按钮
            var loadanima = null;
            var formdata = data.field; //读取form表单中的数据 ,键值对形式

            $.ajax({
                type : "POST",
                url : '${pageContext.request.contextPath}/service/transferSerive',
                data : formdata,
                dataType : "json",
                //请求前执行
                beforeSend : function() {
                    //给按钮添加禁用样式
                    but.className += " layui-btn-disabled";
                    //console.log(but.className);
                    //显示加载动画
                    loadanima = layer.load(2);
                },
                //请求完成执行，无论请求是否成功
                complete : function() {
                    //去除禁用属性
                    but.className = but.className.replace("layui-btn-disabled", "");
                    //关闭加载动画
                    layer.close(loadanima);
                },
                success : function(data) {
                    if (data.success) { //成功
                        //隐藏表单
                        hideDiv();
                        layer.msg(data.msg)
                        layer.closeAll();
                        table.reload('serviceTable');
                    } else { //失败
                        //隐藏表单
                        hideDiv();
                        layer.msg(data.msg)
                    }

                },
                error : function() {
                    layer.msg("服务器开小差了，请稍后再试...");
                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });
        
        
        function submitTransfer(id){
            console.log($("#managerSelect").text()+"213213");
            console.log($("#reasonText").text()+"123213123");
            $.ajax({
                url : '${pageContext.request.contextPath}/service/transferSerive',
                type : 'POST',
                dataType : 'json',
                data : {
                    'serviceId' : id,
                    'newManagerId' : $("#managerSelect").val(),
                    'reason' : $("#reasonText").val()
                },
                success : function(data){
                    if(data.success){
                        //执行重载
                        layer.closeAll();
                        layer.msg(data.msg);
                        table.reload('serviceTable');
                    }else{
                        //执行重载
                        layer.closeAll();
                        layer.msg(data.msg);
                    }
                }
            });
        }
        
        function loadMangerSelect(){
        	$.ajax({
        		url : '${pageContext.request.contextPath}/user/findOthersManager',
        		async : true,
        		type : 'POST',
        		dataType : 'json',
        		success : function(data){
                    if(data.success){
                    	var html = '';
                        if(data.success){
                            $("#managerSelect").html("");
                            var managers = data.list;
                            html += "<option value=''></option>";
                            for(i = 0 ; i < managers.length ; i++){
                                html += "<option value='"+managers[i].id+"'>"+managers[i].account+"</option>";
                            }
                            $("#managerSelect").html(html);  
                        }else {
                            layer.msg("读取客户经理数据出错！");
                        }
                    }		
        		}
        	});
        	form.render('select');
        };
        
        //展示搜索框
        $("#searchButton").click(function(){
        	layer.open({
                type : 2,
                title : '搜索',
                content : "${pageContext.request.contextPath}/views/service/searchService.jsp",
                area : ['900px','450px']
            });
        });
        
        function hideDiv() {
            layer.closeAll('page');
        };

        //显示添加或编辑表单
        function showAddOrEditServicePage(title,id) {
           var url = '${pageContext.request.contextPath}/views/service/addService.jsp';
           if( id > 0 ){
               url += '?id=' + id;
           }
           layer.open({
                type : 2,
                title : title,
                closeBtn : 1,
                area : ['1040px','650px'],
                shadeClose : false,
                content : url,
                end : function() {
                    table.reload('serviceTable',{});
                }
           }); 
        };
        
        //显示添加或编辑表单
        function showHandleServicePage(title,id) {
           var url = '${pageContext.request.contextPath}/views/service/handleService.jsp';
           if( id > 0 ){
               url += '?id=' + id;
           }
           layer.open({
                type : 2,
                title : title,
                closeBtn : 1,
                area : ['1040px','650px'],
                shadeClose : false,
                content : url,
                end : function() {
                    table.reload('serviceTable',{});
                }
           });
        };

        
        
        //搜索功能
        var active = {
            reload : function() {
                var customerId = $("#customerInput").val();
                var type = $("#typeInput").val();
                var status = $("#name='statusInput").val();
                var emergency = $("#emergencyInput").val();
                var degree = $("#degreeInput").val();
                //执行重载
                table.reload('serviceTable', {
                    page : {
                        curr : 1 //重新从第 1 页开始
                    },
                    where : {
                        "customerId" : customerId,
                        "type" : type,
                        "status" : status,
                        "emergency" : emergency,
                        "degree" : degree
                    }
                });
            }
        };
        
        $('#search-button-submit').on('click', function() {
        	var customerId = $("input[name=customerInput]").val();
            var type = $("input[name=typeInput]").val();
            var status = $("input[name=statusInput]").val();
            var emergency = $("input[name=emergencyInput]").val();
            var degree = $("input[name=degreeInput]").val();
            
            //执行重载
            table.reload('serviceTable', {
                page : {
                    curr : 1 //重新从第 1 页开始
                },
                where : {
                    "customerId" : customerId,
                    "type" : type,
                    "status" : status,
                    "emergency" : emergency,
                    "degree" : degree
                }
            });
        });

        function deleteServices(ids) {
            var load = null;
            $.ajax({
                type : "POST",
                url : "${pageContext.request.contextPath}/service/deleteServices",
                data : {
                    "ids" : ids
                },
                dataType : "json",
                //请求前执行，无论请求是否成功
                beforeSend : function() {
                    //显示加载动画
                    load = layer.load(2);
                },
                complete : function() {
                    //关闭加载动画
                    layer.close(load);
                },
                success : function(data) {
                	console.log(data);
                    if (data.success) {
                        //隐藏表单
                        hideDiv();
                        //操作成功 重新加载表格数据
                        layui.layer.open({
                            content : data.msg,
                            btn : [ '确认' ],
                            yes : function(index, layero) {
                                //执行重载
                                layer.closeAll();
                                table.reload('serviceTable', {
                                    page : {
                                        curr : 1 //重新从第 1 页开始
                                    }
                                });
                            },
                            cancel : function() {
                                //执行重载
                                layer.closeAll();
                                table.reload('serviceTable', {
                                    page : {
                                        curr : 1 //重新从第 1 页开始
                                    }
                                });
                            },
                        })
                    }else{
                        layer.msg(data.msg);
                    }
                    
                },
                error : function() {
                    //隐藏表单
                    hideDiv();
                    //操作成功 重新加载表格数据
                    layui.layer.open({
                        content : '删除失败!',
                        btn : [ '确认' ],
                        yes : function(index, layero) {
                            layer.closeAll();
                        },
                        cancel : function() {
                            layer.closeAll();
                        },
                    })
                }
            });
        }

        //点击添加按钮后的处理
        $('#addBtn').click(function() {
            showAddOrEditServicePage('添加服务', -1);
        });
 
        //加载客户下拉框
        function loadCustomerSelect(){
        	$.ajax({
        		type : "POST",
                url : "${pageContext.request.contextPath}/service/findAllCustomerByRole",
                async : true,
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
        	}else if(selectName == 'statusSelect'){
        		name = "服务状态";
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
            //加载下拉框完成后，要重新进行渲染
            form.render('select');
        }
       
        
       //显示添加按钮鼠标经过事件
       $('#add-btn-div').mouseover(function(){
           $('#add-btn-more').show();
       });
       $('#add-btn-div').mouseleave(function(){
           $('#add-btn-more').hide();
       });
       $('#edit-btn-div').mouseover(function(){
           $('#edit-btn-more').show();
       });
       $('#edit-btn-div').mouseleave(function(){
           $('#edit-btn-more').hide();
       });
       
       //详情弹出窗
       table.on('tool(serviceTable)',function(obj){
           var data = obj.data;
           if(obj.event === 'serviceDetail'){
               layer.open({
                   type:2,
                   title:'服务详情',
                   area : ['1040px','650px'],
                   clostBtn:1,
                   shadeClose: true,
                   content:'${pageContext.request.contextPath}/views/service/serviceInfo.jsp?id='+ data.id
               });   
           }
           if(obj.event == 'customerDetail'){
        	   layer.open({
                   type:2,
                   title:'客户详情',
                   area:['80%','100%'],
                   clostBtn:1,
                   shadeClose: true,
                   maxmin:true,
                   offset:'r',
                   content:'views/customer/customerInfo.jsp?id='+ data.customerId
               });
           }
           if(obj.event == 'userDetail'){
               layer.open({
                   type:2,
                   title:'用户信息',
                   area : ['1000px','400px'],
                   clostBtn:1,
                   shadeClose: true,
                   content:'${pageContext.request.contextPath}/views/user/showUserInfo.jsp?id='+ data.createrObject.id
               });  
           }
       });
       
       function hideDiv() {
            layer.closeAll('page');
        }
        
    });
</script>
</html>