<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>Insert title here</title>
<style type="text/css">
.layui-form-item{
    margin-top: -1px;
    margin-left:60px;
}
.layui-input , .layui-textarea{
    border: none;
}
</style>
<base href="<%=request.getContextPath() + '/'%>" />
</head>
<body>

	<div class="layui-form" style="width: 100%; margin-top: 20px;">
		<blockquote class="layui-elem-quote quoteBox">

			<label style="margin-left: 10px;" class="layui-label">客户：</label>
			<div style="width: 100px;" class="layui-inline">
				<input type="text" name="customerId" lay-verify="required"
					style="width: 100%;" placeholder="客户名称" autocomplete="off"
					class="layui-input" />
			</div>

			<label style="margin-left: 10px;" class="layui-label">成功率：</label>
			<div style="width: 100px;" class="layui-inline">
				<select name="success" style="width: 100%;" lay-verify="required">
					<option value=""></option>
					<option value="50">>50%</option>
					<option value="60">>60%</option>
					<option value="70">>70%</option>
					<option value="80">>80%</option>
					<option value="90">>90%</option>
				</select>
			</div>

			<!-- <label style="margin-left: 10px;" class="layui-label">创建人：</label>
			<div style="width: 100px;" class="layui-inline">
				<input type="text" name="create" lay-verify="required"
					style="width: 100%;" placeholder="创建人名称" autocomplete="off"
					class="layui-input" />
			</div>

			<label style="margin-left: 10px;" class="layui-label">被指派经理：</label>
			<div style="width: 100px;" class="layui-inline">
				<input type="text" name="managerId" lay-verify="required"
					style="width: 100%;" placeholder="经理名称" autocomplete="off"
					class="layui-input" />
			</div> -->

			<label style="margin-left: 10px;" class="layui-label">机会状态：</label>
			<div style="width: 100px;" class="layui-inline">
				<select name="status" style="width: 100%;" lay-verify="required">
					<option value=""></option>
				</select>
			</div>

			<!-- <label style="margin-left: 10px;" class="layui-label">是否删除：</label>
			<div style="width: 100px;" class="layui-inline">
				<select name="deleteStatus" style="width: 100%;"
					lay-verify="required">
					<option value=""></option>
					<option value="0">否
					</option>
					<option value="1">是
					</option>
				</select>
			</div> -->
			<button style="margin-left: 30px; margin-right: 20px;" id="searchBtn"
				class="layui-btn" type="button" lay-filter="userForm"><i class="layui-icon">&#xe615;</i>搜索</button>
			<shiro:hasPermission name="9001">
				<button id="addBtn" class="layui-btn layui-btn-norm al"><i class="layui-icon layui-icon-add-1"></i>添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="9002">
				<button id="deleteBtn" class="layui-btn layui-btn-norm al"><i class="layui-icon layui-icon-delete"></i>删除</button>
			</shiro:hasPermission>

			<!-- <div style="width: 100%;margin-top:10px;" align="center" class="layui-inline">
			</div> -->
		</blockquote>
	</div>

	<table class="layui-hide" id="saleOpportunityTable"
		lay-filter="saleOpportunityTable"></table>

	<script type="text/html" id="btnGroup"> 
        <a class="layui-btn layui-btn-xs layui-btn-primary" lay-event="details"><i class="layui-icon layui-icon-about"></i>详情</a>
		<shiro:hasPermission name="9003">
        <a class="layui-btn layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
		</shiro:hasPermission>
    </script>

	<script type="text/javascript">
	layui.use([ 'table', 'form', 'laydate'], function() {
		var layer = layui.layer;
		var tableload = layer.load(3); //加载等待样式
		var table = layui.table;
		var $ = layui.$;
		var form = layui.form;
		showSelect("status"); //加载销售机会状态下拉框
		table.render({
			elem : '#saleOpportunityTable',
			url : 'opportunity/findSaleOpportunity',
			cellMinWidth : 50,//全局定义常规单元格的最小宽度，layui 2.2.1 新增
			cols : [ [ 
				{
                    type : 'checkbox'
            },{
				field : 'id',
				title : '',
				width : '3%',
				align : 'center'
			}, {
				field : 'customerId',
				title : '客户',
				align : 'center',
				width : '7%',
				templet : function(data) {
					 return str = '<a style="color:blue;" href="javascript:" lay-event="customerDetail">' +data.customer.name + '</a>';
				}
			}
			// ,{field:'source',  title: '机会来源', align: 'center'} 
			, {
				field : 'success',
				title : '成功机率',
				sort : true,
				align : 'center',
				width : '8%',
				templet : function(data) {
					return data.success + '%';
				}
			}, {
				field : 'contactId',
				title : '客户联系人',
				align : 'center',
				templet : function(data) {
					return data.linkman.name;
				}
			}, {
				field : 'contactPhone',
				title : '联系人电话',
				align : 'center',
				width : '12%',
				templet : function(data) {
					return data.linkman.officePhone;
				}
			}, {
				field : 'creater',
				title : '机会创建人',
				align : 'center',
				templet : function(data){
	                return str = '<a style="color:blue;" href="javascript:" lay-event="userDetail">' +data.createrUser.account + '</a>';
	            }
			}, /* {
				field : 'createDate',
				title : '创建时间',
				sort : true,
				width : '12%',
				align : 'center'
			},  {
				field : 'managerId',
				title : '被指派的经理',
				align : 'center',
				templet : function(data) {
					return data.manager.realName;
				}
			},*/ {
				field : 'allotDate',
				title : '分配时间',
				align : 'center',
				width : '15%',
				sort  : true
			}, {
				field : 'status',
				title : '机会状态',
				align : 'center'
			}, {
				fixed : 'right',
				title : "操作",
				align : 'center',
				width : '15%',
				toolbar : '#btnGroup'
			} ] ],
			loading : true //分页加载样式
			,
			page : true//是否开启分页
			,
			loading : true //分页加载样式
			,
			done : function() {
				layer.close(tableload);
			}
		});
		
		
		table.on('tool(saleOpportunityTable)', function(obj) {
            var data = obj.data;
            if (obj.event === 'details') {
            	showDetailsOpportunityPage('销售机会详情',data.id);
            }else if(obj.event === 'edit'){
            	showAddOrEditOpportunityPage('更新销售机会',data.id);
            }else if(obj.event === 'customerDetail'){
            	var customerid = data.customerId;
	    		layer.open({
	    			type:2,
	    			title:'客户详情',
	    			area:['80%','100%'],
	    			clostBtn:1,
	    			shadeClose: true,
	    			maxmin:true,
	    			offset:'r',
	    			content:'views/customer/customerInfomation.jsp?id='+ customerid
	    		});
	        }else if(obj.event == 'userDetail'){
               layer.open({
                   type:2,
                   title:'用户信息',
                   area : ['1000px','400px'],
                   clostBtn:1,
                   shadeClose: true,
                   content:'${pageContext.request.contextPath}/views/user/showUserInfo.jsp?id='+ data.createrUser.id
               });  
	       }
        }); 


		//添加按钮触发时间
		$("#addBtn").click(function() {
			showAddOrEditOpportunityPage("添加销售机会", -1);
		})

		//显示添加或编辑表单
		function showAddOrEditOpportunityPage(title, id) {
			//var url = '${pageContext.request.contextPath}/views/opportunity/saleOpportunity.jsp';
			var url = 'views/opportunity/addSaleOpportunity.jsp';
			if (id > 0) {
				url += '?id=' + id;
			}
			layer.open({
				type : 2,
				title : title,
				closeBtn : 1,
				area : [ '1040px', '650px' ],
				shadeClose : false,
				content : url,
				end : function() {
					table.reload('saleOpportunityTable', {});
				}
			});
		}
		

		//弹出销售机会详情
		function showDetailsOpportunityPage(title,id) {
			layer.open({
				type : 2,
				title : title,
				area : [ '80%', '100%' ],
				clostBtn : 1,
				shadeClose : true,
				maxmin : true,
				offset : 'r',
				content : 'views/opportunity/saleOpportunityDetails.jsp?id=' + id
			});
		}

		/* 加载客户下拉框,从数据字典获取  admin/dictionary/find?id=84 */
		function showSelect(selectName) {
			$.ajax({
				type : "GET",
				async : false,
				url : "dictionary/find?name=销售机会状态",
				dataType : "json",
				success : function(result) {
					$.each(result.data.dictionaryItems, function(index, item) {
						$("select[name='" + selectName + "']").append(
								"<option value='"+item.name+"'>" + item.name
										+ "</option>");
					});
					/* 
					console.log("<option value='"+item.id+"'>" + item.name
							+ "</option>");
					for (i = 0; i <	data.dictionaryItems.length; i++) {
						html += "<option value='"+d[i].id+"'>" + d[i].name
								+ "</option>";
					} */
					//重新渲染下拉框
					form.render('select');
				}
			});
		}

		//搜索功能
		var active = {
			reload : function() {
				var customerId = $("input[name='customerId']");
				var success = $("select[name='success']");
				var create = $("input[name='create']");
				var managerId = $("input[name='managerId']");
				var status = $("select[name='status']");
				var deleteStatus = $("select[name='deleteStatus']");
				//执行重载
				table.reload('saleOpportunityTable', {
					page : {
						curr : 1
					//重新从第 1 页开始
					},
					where : {
						"customer.name" : customerId.val(),
						"success" : success.val(),
						"createrUser.realName" : create.val(),
						"manager.realName" : managerId.val(),
						"status" : status.val(),
						"deleteStatus" : deleteStatus.val()
					}
				});
			}
		};
		$('#searchBtn').on('click', function() {
			active['reload'] ? active['reload'].call(this) : '';
		});

		//批量删除销售机会（软删除）
		function deleteOpportunities(ids) {
			var load = null;
			$
					.ajax({
						type : "POST",
						url : "${pageContext.request.contextPath}/opportunity/deleteSaleOpportunities",
						data : {
							"ids" : ids
						},
						dataType : "json",
						//请求前执行，无论请求是否成功
						beforeSend : function() {
							//显示加载动画
							load = layer.load(3);
						},
						complete : function() {
							//关闭加载动画
							layer.close(load);
						},
						success : function(data) {
							if (data.success) {
								//隐藏表单
								hideDiv();
								//操作成功 重新加载表格数据
								layui.layer.open({
									content : '删除成功!',
									btn : [ '确认' ],
									yes : function(index, layero) {
										//执行重载
										layer.closeAll();
										table
												.reload('saleOpportunityTable',
														{});
									},
									cancel : function() {
										//执行重载
										layer.closeAll();
										table
												.reload('saleOpportunityTable',
														{});
									},
								})
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

		function hideDiv() {
			layer.closeAll('page');
		}

		//可批量删除销售机会（软删除）
		$("#deleteBtn").click(
						function() {
							var checkStatus = table
									.checkStatus('saleOpportunityTable');
							var data = checkStatus.data;
							var ids = '';
							if (data.length < 1) {
								layer.msg('请选择要删除的销售机会！');
							} else {
								layer.confirm(
										'确定要删除' + data.length + '条销售机会吗？',
										function(index) {
											layer.close(index);
											//循环读取数据，并把id通过-连接起来
											for (i = 0; i < data.length; i++) {
												ids += data[i].id + '-';
											}
											deleteOpportunities(ids);
										});
							}
						});
	})
		
		
	</script>
</body>
</html>