<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags"  prefix="shiro"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<style type="text/css">
.layui-form-item{
    margin-top: 30px;
}
.layui-form-item input{
    width:300px;
}
.layui-form-label {
    width:100px;
}
</style>
</head>
<body>
	<div class="layui-form" style="width:100%;margin-top: 20px;">
	    <blockquote class="layui-elem-quote quoteBox">
	    <!-- 必须要有查询用户的权限 -->
	    <shiro:hasPermission name="1001">
			<label style="margin-left: 20px;" style="" class="layui-label">用户名：</label>
	
			<div style="width: 120px;" class="layui-inline">
				<input type="text" name="account" placeholder="请输入用户名"
					autocomplete="off" class="layui-input">
			</div>
	
			<label style="margin-left: 20px;" style="" class="layui-label">真实姓名：</label>
	
			<div style="width: 120px;" class="layui-inline">
				<input type="text" name="realName" placeholder="请输入真实姓名"
					autocomplete="off" class="layui-input">
			</div>
	
			<label style="margin-left: 20px;" class="layui-label">职位：</label>
	
			<div style="width: 120px;" class="layui-inline" lay-filter="role1">
				<select id="role1" name="roleId" style="width:100%;" lay-verify="required">
				    <option value="">--数据加载中--</option>
				</select>
			</div>
	
			<button style="margin-left: 20px;" id="searchBtn" class="layui-btn"
				type="button" lay-filter="userForm">搜索</button>
		</shiro:hasPermission>
		
		<shiro:hasPermission name="1003">
		    <button id="addBtn" class="layui-btn layui-btn-normal">添加</button>
		</shiro:hasPermission>
		
		</blockquote>
	</div>
	<script type="text/html" id="btnGroup">
        <shiro:hasPermission name="1002"> <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a></shiro:hasPermission>
        <shiro:hasPermission name="1004"><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a></shiro:hasPermission>
    </script>
    <script type="text/html" id="statusTemplate">
        {{# if(d.status == 0){ }}
            <input type="checkbox"   lay-skin="switch" lay-text="锁定|正常" >
        {{# }else{ }}
            <input type="checkbox"   lay-skin="switch" lay-text="锁定|正常" checked>
        {{# } }}
    </script>
	<table id="userTable" lay-filter="userTable"></table>
	<!-- 编辑添加框 -->
	<!-- form外面要有一个div-->
	<div style="margin-top: 50px;" id="myform-div" class="layui-hide">
		<form class="layui-form" style="margin-left: 10px;" lay-filter="myform">
			<input type="hidden" name="id" style="width: 250px;" />
			<div class="layui-form-item" style="margin-top: 15px;">
				<label class="layui-form-label"><span style="color: red;font-size: 20px;margin-right: 5px;">*</span>账号：</label>
				<div class="layui-input-inline">
					<input type="text" name="account" lay-verify="" 
						placeholder="请输入账号" autocomplete="off" class="layui-input" />
				</div>
				
			</div>
			<!-- <div class="layui-form-item">
				<label class="layui-form-label"><span style="color: red;font-size: 20px;margin-right: 5px;">*</span>密码：</label>
				<div class="layui-input-inline">
					<input type="password" name="password" placeholder="请输入密码" 
						autocomplete="off" class="layui-input" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"><span style="color: red;font-size: 20px;margin-right: 5px;">*</span>确认密码：</label>
				<div class="layui-input-inline">
					<input type="password" name="rePassword" placeholder="请再次输入密码" 
						autocomplete="off" class="layui-input" />
				</div>
			</div> -->
			<div class="layui-form-item">
				<label class="layui-form-label"><span style="color: red;font-size: 20px;margin-right: 5px;">*</span>真实姓名：</label>
				<div class="layui-input-inline">
					<input type="text" name="realName" lay-verify="required" 
						placeholder="请输入真实姓名" autocomplete="off" class="layui-input" />
				</div>
			</div>
			<div class="layui-form-item">
                <label class="layui-form-label"><span style="color: red;font-size: 20px;margin-right: 5px;">*</span>手机号码：</label>
                <div class="layui-input-inline">
                    <input type="text" name="phoneNumber" lay-verify="required|phone" 
                        placeholder="请输入手机号码"  class="layui-input" />
                </div>
            </div>
            
            <div class="layui-form-item">
                <label class="layui-form-label"><span style="color: red;font-size: 20px;margin-right: 5px;">*</span>电子邮箱：</label>
                <div class="layui-input-inline">
                    <input type="text" name="email" lay-verify="" 
                        placeholder="请输入电子邮箱" class="layui-input" />
                </div>
            </div>
            
			<div class="layui-form-item" >
				<label class="layui-form-label"><span style="color: red;font-size: 20px;margin-right: 5px;">*</span>职位：</label>
				<div class="layui-input-inline" style="width: 300px;" lay-filter="role2">
					<select id="role2"  name="roleId" style="width: 100%;" lay-verify="required"  >
                        <option value="">--数据加载中--</option>
					</select>
				</div>
			</div>
			<button type="button" lay-submit lay-filter="formSubmit"
				id="submitButton" class="layui-hide"></button>
		</form>
	</div>
</body>

<script>
	layui.use([ 'table', 'form' ], function() {
		var formUrl = null;
		var table = layui.table;
		var form = layui.form;
		var layer = layui.layer;
		var $ = layui.$;
        var currentPage = 0;
        showRoles("role1");
		//form.render('select');
		//showRoles(selectName);
		//加载表格数据
		var tableload = layer.load(2);
		table.render({
			elem : '#userTable', //指定原始表格元素选择器（推荐id选择器）
			url : '${pageContext.request.contextPath}/user/findUser',
			even : true,
			id : 'userTable',
			cols : [ [
				{
					field : "account",
					title : "用户名"
				},
				{
					field : "realName",
					title : "真实姓名"
				},
				{
                    field : "phoneNumber",
                    title : "电话号码"
                },
                {
                    field : "email",
                    title : "电子邮箱"
                },
				{
					field : "role",
					title : "职位",
					templet : "<div>{{d.role.name}}</div>"
				},
				{
                    field : "createTime",
                    title : "创建时间",
                    sort : true,
                },
                {
                    field : "lastLoginTime",
                    title : "上一次登录时间",
                    sort : true,
                },
                {
                    title : "锁定状态",
                    align: "center",
                    templet : "#statusTemplate",
                    event: "switch",
                    width: "12%",
                },
				{
					fixed : 'right',
					title : "操作",
					align : 'center',
					toolbar : '#btnGroup'
				},
			] ],
			edit : "text", //单元格编辑类型（默认不开启）目前只支持：text（输入框）
			limit : 10,
			limits : [ 10, 20, 30, 40, 50, 60, 70, 80, 90 ],
			loading : true, //分页加载样式
			page : true, //是否开启分页
			done : function(res, curr, count) {
				currentPage = curr;
				layer.close(tableload);
			}
		});

		table.on('tool(userTable)', function(obj) {
			var data = obj.data;
			if (obj.event === 'edit') {
				toUpdateUser(data);
			} else if (obj.event === 'delete') {
				layer.confirm('确定要删除该用户吗？', function(index) {
					layer.close(index);
					deleteUser(data);
				});
			} else if(obj.event == 'switch'){
				//正常状态和禁用状态的切换
                var submitStatus;
                if(data.status == 0){
                    submitStatus = 1;
                }else{
                    submitStatus = 0;
                }
                updateUserStatus(data.id , submitStatus);
			}
		});

		//更新用户的锁定状态
		function updateUserStatus(id,status){
			$.ajax({
				type : 'POST',
				url : '${pageContext.request.contextPath}/user/editUser',
				dataType : 'json',
				data : {
					'id' : id,
					'status' : status
				},
				success : function(data){
					if(data.success){
						layer.msg(data.msg);
					}else{
						layer.msg(data.msg);
						table.reload('userTable',{});
					}
				},
				error : function(){
					layer.msg("服务器开小差了，请稍后再试...");
				}
			});
		}
		
		function toUpdateUser(data) {
			//设置password为自定义检验
			$("input[name='password']").attr("lay-verify", "checkPasswd2");
			$("input[name='rePassword']").attr("lay-verify", "checkPasswd2"); //设置password为必要属性
			$("input[name='account']").attr("lay-verify", "required|username"); 
			$("input[name='email']").attr("lay-verify", "required|email");
			$("input[name='account']").attr("readonly", "readonly");
			showUserDiv('编辑用户');
			console.log(data.roleId);
			showRoles('role2');
			//设置值
			form.val('myform', {
				"id" : data.id,
				"account" : data.account,
				"realName" : data.realName,
				"roleId" : data.roleId,
				"email" : data.email,
				"phoneNumber" : data.phoneNumber
			});
			
			formUrl = "user/editUser";
		}
		
		//监听表单提交按钮
		form.on('submit(formSubmit)', function(data) {
			var but = data.elem; //获取点击的按钮
			var loadanima = null;
			var formdata = data.field; //读取form表单中的数据 ,键值对形式
            console.log(formdata.id);
            
			$.ajax({
				type : "POST",
				url : formUrl,
				data : formdata,
				dataType : "json",
				//请求前执行
				beforeSend : function() {
					//给按钮添加禁用样式
					but.className += " layui-btn-disabled";
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
								table.reload('userTable');
							},
							cancel : function() {
								//执行重载
								layer.closeAll();
								table.reload('userTable');
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
		function showUserDiv(title) {
		    showRoles('role2');
			var html = '';
			var window = layer.open({
				type : 1,
				title : title,
				closeBtn : 2,
				area : [ '530px', '450px' ],
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
				var realName = $("input[name='realName']");
				var roleId = $("select[name='roleId']");
				var account = $("input[name='account']");
				//执行重载
				table.reload('userTable', {
					page : {
						curr : 1 //重新从第 1 页开始
					},
					where : {
						"realName" : realName.val(),
						"roleId" : roleId.val(),
						"account" : account.val()
					}
				});
			}
		};
		$('#searchBtn').on('click', function() {
			active['reload'] ? active['reload'].call(this) : '';
		});



		function deleteUser(data) {
			var load = null;
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/user/deleteUser",
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
								table.reload('userTable', {
									page : {
										curr : 1 //重新从第 1 页开始
									}
								});
							},
							cancel : function() {
								//执行重载
								layer.closeAll();
								table.reload('userTable', {
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
			//设置password为必要属性
			$("input[name='account']").removeAttr("readonly");
			$("input[name='password']").attr("lay-verify", "checkPasswd1|password");
			$("input[name='rePassword']").attr("lay-verify", "checkPasswd1|password");
			$("input[name='email']").attr("lay-verify", "required|email|checkEmail");
			$("input[name='account']").attr("lay-verify", "required|username|checkAccount");
			//显示表单
			showUserDiv('添加用户（初始密码：123456）');
			//设置值
			form.val('myform', {
				"id" : "",
			})
			//设置form表单提交地址
			formUrl = "user/addUser"; //重要，必填属性
		});
		form.verify({
			//自定义验证
			checkPasswd1 : function(value, item) {
				if (value == '') {
					return '必填项不能为空'
				}
				if (item.name == 'password' && $("input[name='rePassword']").val() != value) {
					return '两次输入密码不一致'
				}
				if (item.name == 'rePassword' && $("input[name='password']").val() != value) {
					return '两次输入密码不一致'
				}
			},
			//自定义验证
			checkPasswd2 : function(value, item) {
				if (item.name == 'password' && $("input[name='rePassword']").val() != value) {
					return '两次输入密码不一致'
				}
				if (item.name == 'rePassword' && $("input[name='password']").val() != value) {
					return '两次输入密码不一致'
				}
			},
			//自定义验证
            checkAccount : function(value, item) {
                var flag;
                if (item.name == 'account') {
                    $.ajax({
                    	type : 'POST',
                    	url : '${pageContext.request.contextPath}/user/checkUserAccount',
                    	async : false,
                    	data : {
                    		'account' : value
                    	},
                    	dataType : 'json',
                    	success : function(data){
                    	    console.log(data);
                    		flag = data.success;
                    	}             	
                    });
                }
                if(flag == false){
                    return '该账号已存在！';
                }
            },
            //用户名验证
            username: function(value, item){ //value：表单的值、item：表单的DOM对象
                if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                  return '用户名不能有特殊字符';
                }
                if(/(^\_)|(\__)|(\_+$)/.test(value)){
                  return '用户名首尾不能出现下划线\'_\'';
                }
                if(/^\d+\d+\d$/.test(value)){
                  return '用户名不能全为数字';
                }
              },    
            //我们既支持上述函数式的方式，也支持下述数组的形式
            //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
            password: [
            /^[\S]{6,12}$/
              ,'密码必须6到12位，且不能出现空格'
            ],
            checkEmail : function(value,item){
                var flag;
                if(value == null || value == ''){
                    return '邮箱不可以为空！';
                }
                $.ajax({
                    type : 'POST',
                    url : '${pageContext.request.contextPath}/user/checkUserEmail',
                    async : false,
                    data : {
                        'email' : value
                    },
                    dataType : 'json',
                    success : function(data){
                        console.log(data);
                        flag = data.success;
                    }               
                });
                if(flag == false){
                    return '该邮箱已被使用！';
                }
            }
			
		})
		
		//展示角色菜单
		function showRoles(selectName){
		  var load = null;
		  $.ajax({
                type : "POST",
                async: false,
                url : "${pageContext.request.contextPath}/user/findRoles",
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
                    var html = '';
                    if (data.success) {
                        $("#"+selectName+"").html("");
                        roles = data.list;
                        html += "<option value=''></option>";
                        for(i = 0 ; i < roles.length ; i++ ){
                            /* var selectedHtml = ""; */
                            /* if(roles[i].id == roleId){
                                selectedHtml = " selected ";
                            } */
                            html += "<option value='"+roles[i].id+"'>"+roles[i].name+"</option>";
                        }
                        $("#"+selectName+"").html(html);
                    }
                    form.render('select');
                }
            });
		};
	});
</script>
</html>