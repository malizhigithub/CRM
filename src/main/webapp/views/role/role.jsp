<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1, maximum-scale=1">
</head>
<body>
    <div class="layui-form" style="width:100%;margin-top: 20px;">
        <blockquote class="layui-elem-quote quoteBox">
            <shiro:hasPermission name="4001">
		        <label style="margin-left: 20px;" style="" class="layui-label">职位名：</label>
		
		        <div style="width: 200px;" class="layui-inline">
		            <input id="roleName" type="text" name="name" placeholder="请输入职位名"
		                autocomplete="off" class="layui-input">
		        </div>
		
		        <button style="margin-left: 20px;" id="searchBtn" class="layui-btn"
		            type="button" lay-filter="roleForm">搜索</button>
	        </shiro:hasPermission>
	        <shiro:hasPermission name="4003">
	           <button id="addBtn" class="layui-btn layui-btn-normal">添加</button>
	        </shiro:hasPermission>

        </blockquote>
    </div>
    <script type="text/html" id="btnGroup">
        <shiro:hasPermission name="4002"><a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a></shiro:hasPermission>
        <shiro:hasPermission name="4004"><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a></shiro:hasPermission>
        <shiro:hasPermission name="4005"><a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="allot">分配权限</a></shiro:hasPermission>
    </script>
    <table id="roleTable" lay-filter="roleTable"></table>
    <!-- 编辑添加框 -->
    <!-- form外面要有一个div-->
    <div id="myform-div" class="layui-hide">
        <form class="layui-form" lay-filter="myform">
            <div style="margin-top: 15px;" class="layui-form-item">
                <label class="layui-form-label">职位编号：</label>
                <div class="layui-input-inline">
                    <input type="text" name="id" readonly="readonly" style="width: 250px;"
                        placeholder="由系统设置" autocomplete="off" class="layui-input"  />
                </div>
            </div>
            <div style="width:100%;" class="layui-form-item">
                <label class="layui-form-label">角色名：</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" lay-verify="required" style="width: 250px;"
                        placeholder="请输入职位名" autocomplete="off" class="layui-input" />
                </div>
            </div>
            <div style="width:100%;" class="layui-form-item">
                <label class="layui-form-label">角色描述：</label>
                <div class="layui-input-block">
                    <textarea name="description" style="width: 250px; height:200px;"  
                        placeholder="请输入职位描述" class="layui-textarea"></textarea>
                </div>
            </div>
            <button type="button" lay-submit lay-filter="formSubmit"
                id="submitButton" class="layui-hide"></button>
        </form>
    </div>
    
    
    
</body>

<!-- 引入treejs -->
<script type="text/javascript" src="./layui/layui-xtree.js" ></script>
<script>
    layui.use([ 'table', 'form', 'tree', 'layer'], function() {
        var formUrl = null;
        var table = layui.table;
        var form = layui.form;
        var layer = layui.layer;
        var $ = layui.$;
        
        var curPage;
        
        //加载表格数据
        var tableload = layer.load(2);
        table.render({
            elem : '#roleTable', //指定原始表格元素选择器（推荐id选择器）
            url : '${pageContext.request.contextPath}/role/findRoles',
            even : true,
            id : 'roleTable',
            cols : [ [
                {
                    type : 'numbers',
                    width: "4%"
                },
                {
                    field : "id",
                    title : "职位编号",
                    width: "10%",
                    sort : true
                },
                {
                    field : "name",
                    width: "15%",
                    title : "职位名"
                },
                {
                    field : "description",
                    width: "50%",
                    title : "职位描述"
                },
                {
                    fixed : 'right',
                    title : "操作",
                    align : 'center',
                    width: "18%",
                    toolbar : '#btnGroup'
                }
            ] ],
            edit : "text", //单元格编辑类型（默认不开启）目前只支持：text（输入框）
            limit : 10,
            limits : [ 10, 20, 30, 40, 50, 60, 70, 80, 90 ],
            loading : true, //分页加载样式
            page : true, //是否开启分页
            done : function(res, curr, count) {
                layer.close(tableload);
                curPage = curr; //当前页
            }
        });

        table.on('tool(roleTable)', function(obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                toUpdateRole(data);
            } else if (obj.event === 'delete') {
                layer.confirm('确定要删除该职位吗？', function(index) {
                    layer.close(index);
                    deleteRole(data);
                });
            } else if(obj.event == 'allot'){
            	//权限分配
            	layer.open({
            		type: "1",
            		content: '<form class="layui-form"><div id="permissionTree"></div><button style="margin: 20px 0 20px 20px" type="button" id="allotBtn" class="layui-btn">确定</button></form>',
            		area:['500px', '500px'],
            		offset: '100px',
            		success:function(layero, index){
            			
            			getPermissionTree(data.id);  //传入角色的id
            			
                        permissionTree = new layuiXtree({
                            elem: 'permissionTree',   //(必填) 放置xtree的容器id，不要带#号
                            form: form,     //(必填) layui 的 from
                            data: permissionJson,    //(必填) json数组（数据格式在下面）
                            ckall: true,    //启用全选功能，默认值：false
                            color: {       //三种图标颜色，独立配色，更改几个都可以
                                open: "#EE9A00",        //节点图标打开的颜色
                                close: "#EEC591",    //节点图标关闭的颜色
                                end: "#828282",     //末级节点图标的颜色
                            },
                            ckallback: function () { //全选框状态改变后执行的回调函数
                            	
                            },
                            click: function (data) {  //节点选中状态改变事件监听，全选框有自己的监听事件
                                //console.log(data.value); //开关value值，也可以通过data.elem.value得到
                            },
                        });
                        
                        ///确认分配按钮点击后
                        $("#allotBtn").click(function(){
                        	var checked = [];
                        	$("input.layui-xtree-checkbox:checked").each(function(){
                        		if(!$(this).hasClass("layui-xtree-ckall")){
                        			checked.push($(this).val());
                        		}
                        	});
                        	//提交分配权限操作
                        	$.ajax({
                        		url:"${pageContext.request.contextPath}/role/allotPermission",
                        		type:"post",
                        		data:{
                        			"permissionIds[]" : checked, //List
                        			"roleId" : data.id,
                        		},
                        		traditional: true,
                        		beforeSend:function(){
                        			loading = layer.load();
                        		},
                        		success:function(result){
                        			if(result != null && result.code == 0){
                        				layer.closeAll();
                        				layer.msg("分配成功!");
                        				table.reload("roleTable");  //重新加载表格数据
                        			}else{
                        				layer.msg("分配失败!");
                        			}
                        		},
                        		error:function(){
                        			layer.msg("服务器开小差了!");
                        		},
                        		complete:function(){
                        			layer.close(loading);
                        		}
                        	});
                        });
            		},
            	});
            	
            }
        });

        function toUpdateRole(data) {
            //设置password为自定义检验
            showRoleDiv('编辑职位');
            console.log(data.id);
            //设置值
            form.val('myform', {
                "id" : data.id,
                "name" : data.name,
                "description" : data.description
            });
            
            formUrl = "role/editRole";
        }
        
        //监听表单提交按钮
        form.on('submit(formSubmit)', function(data) {
            var but = data.elem; //获取点击的按钮
            var loadanima = null;
            var formdata = data.field; //读取form表单中的数据 ,键值对形式

            $.ajax({
                type : "POST",
                url : formUrl,
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
                        //操作成功 重新加载表格数据
                        layui.layer.open({
                            content : '操作成功!',
                            btn : [ '确认' ],
                            yes : function(index, layero) {
                                //执行重载
                                layer.closeAll();
                                table.reload('roleTable');
                            },
                            cancel : function() {
                                //执行重载
                                layer.closeAll();
                                table.reload('roleTable');
                            },
                        })
                    } else { //失败
                        //隐藏表单
                        hideDiv();
                        //操作成功 重新加载表格数据
                        layui.layer.open({
                            content : '操作失败!',
                            btn : [ '确认' ],
                            yes : function(index, layero) {
                                layer.closeAll();
                            },
                            cancel : function() {
                                //执行重载
                                layer.closeAll();
                            },
                        });
                    }

                },
                error : function() {
                    layer.msg("服务器开小差了，请稍后再试...");
                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

        function hideDiv() {
            layer.closeAll('page');
        }

        //显示添加表单
        function showRoleDiv(title) {
            var html = '';
            var window = layer.open({
                type : 1,
                title : title,
                closeBtn : 2,
                area : [ '400px', '450px' ],
                btn : [ '确定', '取消' ],
                btn1 : function() {
                    $("#submitButton").click();
                },
                shadeClose : false,
                content : '<div id="show-form-div"></div>',
                end : function() {
                    $('#myform-div').html(html);
                }
            });
            html = $('#myform-div').html();
            $('#myform-div').html("");
            $('#show-form-div').html(html);
            return window;
        }

        //搜索功能
        var active = {
            reload : function() {
                var name = $("#roleName").val();
                //执行重载
                table.reload('roleTable', {
                    page : {
                        curr : 1 //重新从第 1 页开始
                    },
                    where : {
                        "name" : name
                    }
                });
            }
        };
        $('#searchBtn').on('click', function() {
            active['reload'] ? active['reload'].call(this) : '';
        });



        function deleteRole(data) {
            var load = null;
            $.ajax({
                type : "POST",
                url : "${pageContext.request.contextPath}/role/deleteRole",
                data : {
                    "id" : data.id
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
                                table.reload('roleTable', {
                                    page : {
                                        curr : 1 //重新从第 1 页开始
                                    }
                                });
                            },
                            cancel : function() {
                                //执行重载
                                layer.closeAll();
                                table.reload('roleTable', {
                                    page : {
                                        curr : 1 //重新从第 1 页开始
                                    }
                                });
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

        //点击添加按钮后的处理
        $('#addBtn').click(function() {
            //显示表单
            showRoleDiv('添加职位');
            //设置值
            form.val('myform', {
                "id" : "",
            })
            //设置form表单提交地址
            formUrl = "role/addRole"; //重要，必填属性
        });
        
        
        /**
                        获取权限树
          roleid 角色id
        **/
        function getPermissionTree(roleid){
            $.ajax({
            	url:"${pageContext.request.contextPath}/permiss/getPermissionTree",
            	type:"get",
            	data:{},
            	async:false,
            	dataType:"json",
            	beforeSend:function(){
            		loading = layer.load();
            	},
            	success:function(data){
            		if(data != null ){
            			
            			//获取这个角色所拥有的权限
            			$.ajax({
            				url: "${pageContext.request.contextPath}/role/findRolePermissionId",
            				type:"post",
            				data:{
            					"roleId" : roleid,
            				},
            				dataType:"json",
            				async:false,
            				beforeSend:function(){
            					loading = layer.load();
            				},
            				success:function(result){
            					if(result != null){
            						console.log("用户拥有的权限ID: "+result.result);
            						permissionJson = createPermissionTree(data.tree, result.result); //构建权限树
            					}
            				},
            				error:function(){
            					layer.msg("服务器开小差了");
            				},
            				complete:function(){
            					layer.close(loading);
            				}
            			})
            			
            			
            		}
            	},
            	complete:function(){
            		layer.close(loading);
            	},
            	error:function(){
            		layer.msg("服务器开小差了!");
            	}
            })
        }
        
        /**
                    构建权限树
        */
        function createPermissionTree(tree, data){
        	if(tree == null){
                return null;
            }
            var treeNode = [];
            for(var i=0;i<tree.length; i++){
            	var flag = false;
            	if(data != null){
            		for(j=0;j<data.length;j++){
                        if(data[j].permissionId == tree[i].id){
                            flag = true;
                            break;
                        }
                    }
            	}
            	
            	var node = {
                        "title" : tree[i].title,
                        "data": createPermissionTree(tree[i].childPermission, data),
                        "value" : tree[i].id,
                        "checked": flag,
                   }
                treeNode.push(node);
            }
            return treeNode;
        }
    });
</script>
</html>