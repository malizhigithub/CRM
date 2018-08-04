<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags"  prefix="shiro"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
</head>
<script type="text/javascript" src="./js/myutil.js"></script>

<body>
    <div class="layui-tab layui-tab-brief" lay-filter="care-tab">
	  <ul class="layui-tab-title">
	    <li class="layui-this">7天内过生日客户</li>
	    <li>15天内过生日客户</li>
	    <li>30天内过生日客户</li>
	  </ul>
	  <div class="layui-tab-content" style="padding: 0px;">
	    <div class="layui-tab-item layui-show">
	       <table id="customerCareTableA" lay-filter="customerCareTableA"></table>
	    </div>
	    <div class="layui-tab-item">
	       <table id="customerCareTableB" lay-filter="customerCareTableB"></table>
	    </div>
	    <div class="layui-tab-item">
	       <table id="customerCareTableC" lay-filter="customerCareTableC"></table>
	    </div>
	  </div>
    </div>
    <shiro:hasPermission name='19002'></shiro:hasPermission>
    <shiro:hasPermission name='19006'></shiro:hasPermission>
</body>

<script>
	layui.use([ 'table', 'form', 'laydate','element' ], function() {
		var formUrl = null;
		var table = layui.table;
		var form = layui.form;
		var layer = layui.layer;
		var laydate = layui.laydate;
		var element =layui.element;
		var $ = layui.$;

		loadTable('customerCareTableA',7);
		
		
		
		element.on('tab(care-tab)',function(data){
			if(data.index == 0){
				//7天内的生日
				loadTable('customerCareTableA',7);
			}else if(data.index == 1){
				//15天内的生日
				loadTable('customerCareTableB',15);
			}else if(data.index == 2){
				//30天内的生日
				loadTable('customerCareTableC',30);
			}
		});
		
		function loadTable(tableId,day){
			//加载表格数据
	        var tableload = layer.load(2);
	        table.render({
	            elem : '#'+tableId, //指定原始表格元素选择器（推荐id选择器）
	            url : '${pageContext.request.contextPath}/customerCare/findCustomerCare?day='+day,
	            even : true,
	            id : tableId,
	            cols : [ [
	                {
	                    field : "name",
	                    title : "客户或联系人",
	                    templet : function(data) {
	                        return data.linkman.name + "[<a style='color:#999999;' href='javascript:' lay-event='customerDetail'>" + data.linkman.customer.name + "</a>]";
	                    }
	                },
	                {
	                    field : "birthday",
	                    title : "生日",
	                    templet : function(data) {
	                        return Format(data.birthday, 'MM月dd日')+","+getAges(Format(data.birthday, 'yyyy-MM-dd'))+"岁生日，<span style='color:red;'>"+brthDate(Format(data.birthday, 'MM'),Format(data.birthday, 'dd'))+"</span>";
	                    }
	                },
	                {
	                    field : "birthday1",
	                    title : "出生日期",
	                    templet : function(data) {
	                        return "公历：" + Format(data.birthday,'yyyy-MM-dd') + " (" + getWeek(Format(data.birthday,'yyyy-MM-dd')) + ")" ;
	                    }
	                },
	                {
                        field : "manager",
                        title : "处理人",
                        templet : function(data) {
                            return str = '<a style="color:#178eb9;" href="javascript:" lay-event="userDetail">' +data.manager.account + '</a>';
                        }
                    },
	                {
	                    field : "status",
	                    title : "状态",
	                    templet : function(data){
	                    	if(data.status == '未处理'){
	                    		return "<p style='color:red;'>"+data.status+"</p>";
	                    	}else if(data.status == '已处理'){
	                    		return "<p style='color:blue;'><a href='javascript:' lay-event='customerCareDetail'>"+data.status+"</a></p>";
	                    	}else if(data.status == '已忽略'){
	                    		return "<p style='color:#999999;'>"+data.status+"</p>";
	                    	}
	                    }
	                },
	                {
	                    field : "operation",
	                    title : "操作",
	                    align : 'center',
	                    templet : function(data){
	                       if(data.status == '未处理'){
	                           return "<shiro:hasPermission name='19002'><a class='layui-btn layui-btn-xs' lay-event='handle-now'><i class='layui-icon layui-icon-add-1'></i>现在处理</a></shiro:hasPermission><shiro:hasPermission name='19006'><a class='layui-btn layui-btn-xs layui-btn-danger' lay-event='skip-care'><i class='layui-icon layui-icon-close'></i>忽略</a></shiro:hasPermission>"
	                       }else {
	                           return "";
	                       }
	                    }
	                }
	            ] ],
	            limit : 10,
	            limits : [ 10, 20, 30, 40, 50, 60, 70, 80, 90 ],
	            loading : true, //分页加载样式
	            page : true, //是否开启分页
	            done : function(res, curr, count) {
	                layer.close(tableload);
	            }
	        });
		};
	
		//监听表格内的事件
		table.on('tool(customerCareTableA)', function(obj) {
            var data = obj.data;
            //处理关怀
            if(obj.event == 'handle-now'){
            	showHandleCarePage(data.id,'customerCareTableA');
            //查看编辑关怀
            }else if(obj.event == 'customerCareDetail'){
                showEditCarePage(data.id, data.currentCustomerCareId ,'customerCareTableA');
            }else if(obj.event == 'customerDetail'){
            	showCustomerDetailPage(data.linkman.customerId);
            }else if(obj.event == 'skip-care'){
            	layer.confirm('确定要忽略该联系人的生日吗?', function(index) {
                    layer.close(index);
                    skipCustomerCare(data.id,'customerCareTableA');
                });
            }else if(obj.event == 'userDetail'){
                layer.open({
                    type:2,
                    title:'用户信息',
                    area : ['1000px','400px'],
                    clostBtn:1,
                    shadeClose: true,
                    content:'${pageContext.request.contextPath}/views/user/showUserInfo.jsp?id='+ data.manager.id
                });  
            }
		});
		
		
		table.on('tool(customerCareTableB)', function(obj) {
            var data = obj.data;
            if(obj.event == 'handle-now'){
            	showHandleCarePage(data.id,'customerCareTableB');
            }else if(obj.event == 'customerCareDetail'){
                showEditCarePage(data.id,data.currentCustomerCareId,'customerCareTableB');
            }else if(obj.event == 'customerDetail'){
                showCustomerDetailPage(data.linkman.customerId);
            }else if(obj.event == 'skip-care'){
                layer.confirm('确定要忽略该联系人的生日吗?', function(index) {
                    layer.close(index);
                    skipCustomerCare(data.id,'customerCareTableB');
                });
            }
        });
        
        table.on('tool(customerCareTableC)', function(obj) {
            var data = obj.data;
            if(obj.event == 'handle-now'){
            	showHandleCarePage(data.id,'customerCareTableC');
            }else if(obj.event == 'customerCareDetail'){
                showEditCarePage(data.id, data.currentCustomerCareId, 'customerCareTableC');
            }else if(obj.event == 'customerDetail'){
                showCustomerDetailPage(data.linkman.customerId);
            }else if(obj.event == 'skip-care'){
                layer.confirm('确定要忽略该联系人的生日吗?', function(index) {
                    layer.close(index);
                    skipCustomerCare(data.id,'customerCareTableC');
                });
            }
        });
        
        //展示处理页面
        function showHandleCarePage(id,tableFilter){
        	var url = '${pageContext.request.contextPath}/views/customerCare/handleCare.jsp?id=' + id;
            layer.open({
                type : 2,
                title : '生日处理结果',
                content : url,
                clostbtn : 2,
                shadeClose : true,
                area : [ '800px', '650px' ],
                end : function() {
                    table.reload(tableFilter, {});
                }
            });
        }
        
        //展示处理页面
        function showEditCarePage(id,customerCareId,tableFilter){
            var url = '${pageContext.request.contextPath}/views/customerCare/handleCare.jsp?id=' + id + "&customerCareId=" + customerCareId;
            layer.open({
                type : 2,
                title : '生日处理结果',
                content : url,
                clostbtn : 2,
                shadeClose : true,
                area : [ '800px', '650px' ],
                end : function() {
                    table.reload(tableFilter, {});
                }
            });
        };
        
        //展示客户详情页面
        function showCustomerDetailPage(customerId){
        	layer.open({
                type:2,
                title:'客户详情',
                area:['80%','100%'],
                clostBtn:1,
                shadeClose: true,
                maxmin:true,
                offset:'r',
                content:'views/customer/customerInfomation.jsp?id='+ customerId
            });
        };
        
        function skipCustomerCare(id,tableFilter){
        	$.ajax({
                type : "POST",
                url : "${pageContext.request.contextPath}/customerCare/skipCustomerCare",
                async : false,
                data : {
                    id : id
                },
                dataType : "json",
                success : function(data){
                    var html = '';
                    if(data.success){
                       layer.msg('忽略成功');
                    }else {
                        layer.msg("读取数据出错！");
                    }        
                }
            });
        	table.reload(tableFilter, {});
        }
        
		
		
	});
</script>
</html>