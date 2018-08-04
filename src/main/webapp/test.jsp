<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" href="layui/css/layui.css">

<title>导航栏测试</title>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">

  <!-- 头部 -->
  <div class="layui-header">
    <div class="layui-logo">layui 后台布局</div>
    
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
          贤心
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="">退了</a></li>
    </ul>
  </div>
  
  <!-- 菜单栏  -->
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="menu">
        <li class="layui-nav-item">
          <a class="" href="javascript:;">所有商品</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">列表一</a></dd>
            <dd><a href="javascript:;">列表二</a></dd>
            <dd><a href="javascript:;">列表三</a></dd>
            <dd><a href="">超链接</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">解决方案</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">列表一</a></dd>
            <dd><a href="javascript:;">列表二</a></dd>
            <dd><a href="">超链接</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href="#/dictionary">数据字典</a></li>
        <li class="layui-nav-item"><a href="">发布商品</a></li>
        
        <!-- 权限管理 -->
                        <li class="kit-menu-item">
                            <a href="#/permission">
                                <i class="layui-icon"></i>
                                <span>权限管理</span>
                            </a>
                        </li>
                        <!-- 权限管理 -->
                        
                        <!-- 服务管理 -->
                        <li class="kit-menu-item">
                            <a href="#/service">
                                <i class="layui-icon"></i>
                                <span>服务管理</span>
                            </a>
                        </li>
                        <!-- 服务管理 -->
                        
                        <!-- 客户关怀 -->
                        <li class="kit-menu-item">
                            <a href="#/customerCare">
                                <i class="layui-icon"></i>
                                <span>客户关怀</span>
                            </a>
                        </li>
                        <!-- 客户关怀  -->
                        
                        <!-- 用户管理 -->
                        <li class="kit-menu-item">
                            <a href="#/user">
                                <i class="layui-icon"></i>
                                <span>用户管理</span>
                            </a>
                        </li>
                        <!-- 用户管理 -->
                        
                        <!-- 角色管理 -->
                        <li class="kit-menu-item">
                            <a href="#/role">
                                <i class="layui-icon"></i>
                                <span>角色管理</span>
                            </a>
                        </li>
                        <!-- 角色管理 -->
                        
                        <!-- 日志 -->
                        <li class="kit-menu-item">
                            <a href="#/logging">
                                <i class="layui-icon"></i>
                                <span>日志管理</span>
                            </a>
                        </li>
                        <!-- 日志 -->
                        
                        <!-- 数据字典 start -->
                        <li class="kit-menu-item">
                            <a href="#/dictionary">
                                <i class="layui-icon"></i>
                                <span>数据字典</span>
                            </a>
                        </li>
                        <!-- 数据字典 end -->
                        
                        <!-- 客户管理 start -->
                        <li class="kit-menu-item">
                            <a href="javascript:" class="child">
                                <i class="layui-icon"></i>
                                <span>客户管理</span>
                            </a>
                            <ul class="kit-menu-child layui-anim layui-anim-upbit">
                                 <li class="kit-menu-item">
                                     <a href="#/customer">
                                         <i class="layui-icon"></i>
                                         <span>全部客户</span>
                                     </a>
                                 </li>
                                 <li class="kit-menu-item">
                                     <a href="#/customerloss">
                                         <i class="layui-icon"></i>
                                         <span>客户流失</span>
                                     </a>
                                 </li>
                                 
                             </ul>
                        </li>
                        <!-- 客户管理 end -->
                        
                        <!-- 销售机会 start -->
                        <li class="kit-menu-item">
                            <a href="#/opportunity">
                                <i class="layui-icon"></i>
                                <span>销售机会</span>
                            </a>
                        </li>
                        <!-- 销售机会 end -->
                        
                        <li class="kit-menu-item">
                            <a href="javascript:;" class="child">
                                <i class="layui-icon"></i>
                                <span>菜单一</span>
                            </a>
                            <ul class="kit-menu-child kit-menu-child layui-anim layui-anim-upbit">
                                <li class="kit-menu-item">
                                    <a href="javascript:;" class="child">
                                        <i class="layui-icon"></i>
                                        <span>菜单二</span>
                                    </a>
                                    <ul class="kit-menu-child kit-menu-child layui-anim layui-anim-upbit">
                                        <li class="kit-menu-item">
                                            <a href="#/user/table2">
                                                <i class="layui-icon"></i>
                                                <span>菜单三</span>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="kit-menu-item">
                                    <a href="javascript:;" class="child">
                                        <i class="layui-icon"></i>
                                        <span>菜单1.1</span>
                                    </a>
                                    <ul class="kit-menu-child kit-menu-child layui-anim layui-anim-upbit">
                                        <li class="kit-menu-item">
                                            <a href="#/user/table2">
                                                <i class="layui-icon"></i>
                                                <span>菜单1.2</span>
                                            </a>
                                            <ul class="kit-menu-child kit-menu-child layui-anim layui-anim-upbit">
                                                <li class="kit-menu-item">
                                                    <a href="#/user/table2">
                                                        <i class="layui-icon"></i>
                                                        <span>菜单1.3</span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
     <router-view></router-view>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
  </div>
</div>
<script src="layui/layui.js"></script>
<script>
//JavaScript代码区域
    layui.config({
            base: 'js/'
        }).use('test');
</script>
</body>
</html>