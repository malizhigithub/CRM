<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>权限管理</title>
</head>
<style>
#queryForm{
	margin-top:30px;
	text-align: center;
}
</style>
<body>

    <script type="text/html" id="buttonGroup">
        <shiro:hasPermission name="3002"><a class="layui-btn layui-btn-xs" lay-event="detail">详细</a></shiro:hasPermission>
        <shiro:hasPermission name="3004"><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a></shiro:hasPermission>
    </script>
    <script type="text/html" id="statusTemplate">
        {{# if(d.status == 0){ }}
            <input type="checkbox"   lay-skin="switch" lay-text="正常|禁用" checked>
        {{# }else{ }}
            <input type="checkbox"   lay-skin="switch" lay-text="正常|禁用" >
        {{# } }}
    </script>
    
    <!-- 菜单栏  搜索框 -->
    <form class="layui-form" action="" id="queryForm">
	    <div class="layui-form-item" >
	    	<!-- 添加按钮 -->
	 		<div class="layui-inline">
	 		    <shiro:hasPermission name="3005">
		 		    <button type="button" class="layui-btn" id="showTreeButton">
	                                                     显示权限树
	                </button>
	 		    </shiro:hasPermission>
	 		    <shiro:hasPermission name="3003">
			 		<button type="button" class="layui-btn" id="addButton">
					  <i class="layui-icon">&#xe608;</i> 添加权限
					</button>
				</shiro:hasPermission>
	 		</div>
	 		
	 	  <shiro:hasPermission name="3001">
	 		<!-- 权限名称 -->
		  <div class="layui-inline">
			    <label class="layui-form-label">权限名称</label>
			    <div class="layui-input-inline">
			      <input type="text" name="title" placeholder="权限名称"  class="layui-input">
			    </div>
		  </div>
		  
		  <div class="layui-inline" >
		  	  <label class="layui-form-label">权限状态</label>
		  	  <div class="layui-input-inline" style="width:100px;">
				  <select name="status" lay-verify="">
				  	  <option value="" selected>全部</option>
					  <option value="0" >正常状态</option>
					  <option value="1">禁用状态</option>
				  </select> 
			  </div>
		  </div>
		  
			  <div class="layui-inline">
			  	 <button class="layui-btn" type="button" id="queryButton" >搜索</button>
			  </div>
		  </shiro:hasPermission>
		</div>
    </form>
    
    <!-- 添加权限框 -->
    <div id="addPermission" class="layui-hide" >
		<form class="layui-form" action="" style="padding:10px" lay-filter="addPermission">
		  <div class="layui-form-item">
		    <label class="layui-form-label">权限名称</label>
		    <div class="layui-input-inline">
		      <input type="text" name="title" required  lay-verify="required" placeholder="请输入权限名称" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label">请求URL</label>
		    <div class="layui-input-inline">
		      <input type="text" name="url"   placeholder="请输入请求url" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label">上级权限</label>
		    <div class="layui-input-inline">
		      <input type="text" id="superPermissionName" value="" class="layui-input" readonly>
		      <input type="hidden" name="pid" />
		    </div>
		    <div class="layui-form-mid layui-word-aux" style="padding:0 !important;">
		          <button type="button" id="openButton"  class="layui-btn">选择上级权限</button>
		    </div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label">权限编码</label>
		    <div class="layui-input-inline">
		      <input type="text" name="code"  placeholder="请输入权限编码" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label">类型</label>
		    <div class="layui-input-inline">
		      <select name="type" lay-verify="required" >
		        <option value="0">菜单</option>
		        <option value="1">功能</option>
		      </select>
		    </div>
		  </div>
		  
		  <div class="layui-form-item layui-form-text">
		    <label class="layui-form-label">权限描述</label>
		    <div class="layui-input-block">
		      <textarea name="description" placeholder="请输入权限描述" class="layui-textarea"></textarea>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="addPermission">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		  </div>
		</form>
	</div><!-- 添加权限框 -->
		
	
	<!-- 编辑权限框 -->
    <div id="updatePermissDialog" class="layui-hide" >
    	<form class="layui-form updatePermissDialog" action="" style="padding:10px" lay-filter="updatePermissDialog">
    	  <input type="hidden" name="id" value="" />
		  <div class="layui-form-item">
		    <label class="layui-form-label">权限名称</label>
		    <div class="layui-input-inline">
		      <input type="text" id="permissionTitle" lay-verify="required" readonly class="layui-input">
		    </div>
		  </div>
		  
		  <div class="layui-form-item">
            <label class="layui-form-label">权限类型</label>
            <div class="layui-input-inline">
              <select name="type" lay-verify="required" >
                <option value="0">菜单</option>
                <option value="1">功能</option>
              </select>
            </div>
          </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label">上级权限</label>
		    <div class="layui-input-inline">
		      <input type="text" id="superPermissionNameUpdate" value="" class="layui-input" readonly>
		      <input type="hidden" name="pid" />
		    </div>
		    <div class="layui-form-mid layui-word-aux" style="padding:0 !important;">
		          <button type="button" id="openButtonUpdate"  class="layui-btn">选择上级权限</button>
		          <button type="button" id="setTopPermission"  class="layui-btn">设置顶级</button>
		    </div>
		  </div>
		 
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="updatePermissDialog">立即提交</button>
		    </div>
		  </div>
		</form>
    </div>
    <!-- 编辑权限框 -->

	<!-- 数据表格 -->
	<table id="permission" lay-filter="permissionTable">
		
	</table> 
	
	
	
	<script type="text/javascript">
	var curPage;
	var permissionTree; //权限树
	layui.use([ 'table', 'layer', 'laytpl', 'tree' ,'form'], function() {
		var $ = layui.$;
		var loading = layer.load(); //加载动画
		var form = layui.form;
		form.render('select');
		var permissionTable = layui.table.render({
			elem : '#permission', //指定原始表格元素选择器（推荐id选择器）
			url : '${pageContext.request.contextPath}/permiss/findPermission',
			cols : [ [
				{
					field : "id",
					title : "权限ID",
					width : "8%",
				},
				{
					field : "title",
					title : "权限名称",
					edit : "text", //单元格编辑类型（默认不开启）目前只支持：text（输入框）
					width : "12%",
				},
				{
					field : "url",
					title : "请求地址",
					edit : "text", //单元格编辑类型（默认不开启）目前只支持：text（输入框）
					width : "10%",
				},
				{
					field : "code",
					title : "权限编码",
					edit : "text", //单元格编辑类型（默认不开启）目前只支持：text（输入框）
					width: "8%",
				},
				{
					field : "type",
					title : "类型",
					templet:function(d){
						console.log(d.type);
						if(d.type == 0){
							return '菜单';
						}else{
							return '功能';
						}
					},
					width: "8%",
				},
				{
					field : "description",
					title : "权限描述",
					width : "30%",
					edit : "text", //单元格编辑类型（默认不开启）目前只支持：text（输入框）
				},	
				{
					title: "状态",
					align: "center",
					templet : "#statusTemplate",
					event: "switch",
					width: "12%",
				},
				{
					title: "操作",
					align: "center",
					toolbar : "#buttonGroup",  //工具栏
				},
			] ],
			limit : 10,
			limits : [ 10, 20, 30, 40, 50, 60, 70, 80, 90 ],
			loading : true, //分页加载样式
			page : true, //是否开启分页
			done : function(res, curr, count) {
				//如果是异步请求数据方式，res即为你接口返回的信息。
				//如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
				console.log(res);

				//得到当前页码
				console.log(curr);
				curPage = curr;

				//得到数据总量
				console.log(count);
				layer.close(loading); //关闭动画
			}
		});
		
		//表格单元格被修改后
		layui.table.on('edit(permissionTable)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
			  console.log(obj.value); //得到修改后的值
			  console.log(obj.field); //当前编辑的字段名
			  //console.log(obj.data); //所在行的所有相关数据  
			  var data = obj.data;
			  var field = obj.field;
			  var value = obj.value;
			  var submitData = {
				 "id"	  :	data.id,
			  };
			  submitData[field] = value;
			  updatePermission(submitData, obj);
		});
		
		//表格事件
		layui.table.on('tool(permissionTable)', function(obj){//注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
			var data = obj.data; //获得当前行数据
			var event = obj.event;
			var tr = obj.tr; //获得当前行 tr 的DOM对象
			console.log(event);
			if(event == "switch"){
				//正常状态和禁用状态的切换
				var submitStatus;
				if(data.status == 0){
					submitStatus = 1;
				}else{
					submitStatus = 0;
				}
				var submitData = {
					"id" : data.id,
					"status" : submitStatus,
				};
				updatePermission(submitData, obj);
			}else if(event == "del"){
				//删除
				layer.confirm("确定要删除这条权限吗？", function(index){
					deletePermission(data.id, obj);
					layer.close(index);
				});
				
			}else if(event == "detail"){
				var html = "";
			     //查看详细
				layer.open({
					type:"1",
					content: $("#updatePermissDialog").html(),
					area:'600px',
					title: "权限详情",
					success:function(layero, index){
						html = $("#updatePermissDialog").html();
						$("#updatePermissDialog").html("");
						
						//设置参数
						console.log(data);
						$(".updatePermissDialog input[name=id]").val(data.id);
						$(".updatePermissDialog input[id=permissionTitle]").val(data.title);				
						if(data.parentPermission == null){
							$("#superPermissionNameUpdate").val("无");
							$(".updatePermissDialog [name=pid]").val("");
						}else{
							$("#superPermissionNameUpdate").val(data.parentPermission.title);
							$(".updatePermissDialog [name=pid]").val(data.parentPermission.id);
						}
						$(".updatePermissDialog select[name=type]").val(data.type);
						layui.form.render(null, "updatePermissDialog");

						
						//设置为顶级权限
                        $("button#setTopPermission").click(function(){
                        	$(".updatePermissDialog [name=pid]").val(0);  //提交过去的pid 为0为可以设置为顶级权限
                        	$("#superPermissionNameUpdate").val("无");
                        });       
						/**
							注册点击弹出更新窗口事件
						*/
						$("#openButtonUpdate").click(function(){
							
							//1. 弹出权限选择层
				        	openPermissionTree();
				        	layui.tree({
                                elem : "#permissionTree",
                                nodes: permissionTree,
                                click: function(node){
                                  console.log(node);
                                  $(".updatePermissDialog input[name=pid]").val(node.id);
                                  $("#superPermissionNameUpdate").val(node.name);
                                  layer.close(permissionTreeDialog);
                                },
                                
                            });
						})
						
						
					},
					cancel:function(index, layero){
						$("#updatePermissDialog").html(html);
					},
					end: function(){
						$("#updatePermissDialog").html(html);
					}
				})
			}
		});
		
		/**
		  删除权限
		*/
		function deletePermission(id, obj){
			$.ajax({
				url: "${pageContext.request.contextPath}/permiss/deletePermission",
				type:"post",
				data:{
					"id" : id,
				},
				dataType:"json",
				beforeSend:function(){
					loading = layer.load();
				},
				success:function(data){
					if(data.code == 0){
						layer.msg("删除成功!");
						obj.del();
					}else{
						layer.msg(data.msg);
					}
				},
				error:function(){
					layer.msg("服务器开小差了!");
				},
				complete:function(){
					layer.close(loading);
				}
			})
		}
		
		/**
		  更新权限
		*/
		function updatePermission(submitData, obj){
			$.ajax({
				url:"${pageContext.request.contextPath}/permiss/updatePermission",
				type:"post",
				data:submitData,
				dataType:"json",
				beforeSend:function(){
					loading = layer.load();  //加载动画
				},
				success:function(data){
					if(data.code == 0){
						layer.msg("修改成功");
						//obj.update(submitData);
						//reload();
					}else{
						layer.msg(data.msg);
					}
				},
				error:function(){},
				complete:function(){
					layer.close(loading);//关闭动画
				}
			})
		}
		
		/**
			搜索
		*/
		$("#queryButton").click(function(){
			reload();
		});
		
		/**
		  重新加载表格内容
		*/
		function reload(){
			permissionTable.reload({
				page: {
			        curr: 1 //重新从第 1 页开始
			    },
				where :{
					title: $("input[name=title]").val(),
					status : $("select[name=status]").val(),
				},  //搜索条件
			})
		}
		
		/*
			添加权限  打开弹出框
		**/
		$("#addButton").click(function(){
			//打开页面层
			var html = "";
			layer.open({
				type: 1,
				title: "权限添加",
				content: $('#addPermission').html(),
				area: '500px',
				success:function(layero, index){
					html = $('#addPermission').html();
					$('#addPermission').html("");
					
					layui.form.render(null, 'addPermission');
					
					/**
			                          注册打开权限树选择  按钮
			        */
			        $("#openButton").click(function(){
			        	
			        	//1. 获取权限树
			        	openPermissionTree();
			        	layui.tree({
                            elem : "#permissionTree",
                            nodes: permissionTree,
                            click: function(node){
                              console.log(node);
                              $("[name=pid]").val(node.id);
                              $("#superPermissionName").val(node.name);
                              layer.close(permissionTreeDialog);
                            },
                            
                        });
			        });
					
				},
				cancel: function(index, layero){ 
					$('#addPermission').html(html);
				},
				end: function(){
					$('#addPermission').html(html);
				}
			})
		});
		
		//添加权限表单提交
		layui.form.on("submit(addPermission)", function(data){
			console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
		    console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
		    console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
			
			//1. 提交数据
			$.ajax({
				url: "${pageContext.request.contextPath}/permiss/addPermiss",
				type:"post",
				data: data.field,
				dataType:"json",
				beforeSend:function(){
					loading = layer.load();
				},
				success:function(data){
					if(data != null){
						if(data.code == 0){
							
							//2. 关闭添加窗口
							layer.closeAll();
							
							//3. 重新加载表格数据
							reload();
							
							//4. 提示信息
							layer.msg("添加成功!");
							
						}else{
							layer.msg(data.msg);
						}
					}
				},
				error:function(){
					layer.msg("服务器开小差了!");
				},
				complete:function(){
					layer.close(loading);
				}
			})
			
		    return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
		});
		
		//更新权限表单提交
		layui.form.on("submit(updatePermissDialog)", function(data){
			console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
		    console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
		    console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
			
			//1. 提交数据
			$.ajax({
				url: "${pageContext.request.contextPath}/permiss/updatePermission",
				type:"post",
				data: data.field,
				dataType:"json",
				beforeSend:function(){
					loading = layer.load();
				},
				success:function(data){
					if(data != null){
						if(data.code == 0){
							
							//2. 关闭添加窗口
							layer.closeAll();
							
							//3. 重新加载表格数据
							reload();
							
							//4. 提示信息
							layer.msg("更新成功!");
							
						}else{
							layer.msg(data.msg);
						}
					}
				},
				error:function(){
					layer.msg("服务器开小差了!");
				},
				complete:function(){
					layer.close(loading);
				}
			})
			
		    return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
		});
		
		/**
		  添加权限 ： 打开权限树
		*/
		function openPermissionTree(){
			permissionTreeDialog = layer.open({
                type: "1",
                shade: false,
                content: '<div id="permissionTree"></div>',
                area: ['500px','500px'],
                zIndex: layer.zIndex, //重点1
                success:function(lay, ind){
                    layer.setTop(lay); //重点2

                    //获取权限树
                    $.ajax({
                        url : "${pageContext.request.contextPath}/permiss/getPermissionTree",
                        type: "get",
                        async: false,
                        data: {},
                        dataType:"json",
                        beforeSend: function(){
                            loading = layer.load();//加载动画
                        },
                        success:function(data){
                            console.log(data.tree);
                            if( data != null ){
                                //组合权限树
                              permissionTree = createPermissionTree(data.tree);
                            }
                            
                        },
                        complete:function(){
                            layer.close(loading);
                        },
                        error:function(){
                        	layer.msg("服务器开小差了!");
                        },
                    });
                }
            });
		}
		
		
		/***
			构建权限树
		**/
		function createPermissionTree(tree){
			
			if(tree == null){
				return null;
			}
			var treeNode = [];
			for(var i=0;i<tree.length; i++){
				var node = {
					"name" : tree[i].title+"( <strong>"+tree[i].code+"</strong> )"+(tree[i].type==0?"菜单":"功能"),
					"children": createPermissionTree(tree[i].childPermission),
					"id" : tree[i].id,
				}
				treeNode.push(node);
			}
			return treeNode;
		}
		
		/**
		  显示权限树
		*/
		$("#showTreeButton").click(function(){
			openPermissionTree();
			layui.tree({
                elem : "#permissionTree",
                nodes: permissionTree,
            });
		})
		
		
	})
	</script>
	
	
</body>


	
</html>