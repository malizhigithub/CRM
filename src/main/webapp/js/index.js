var routeOpts = {};
layui.config({
	base: 'layui/lay/modules/'
	}).define(['element', 'sidebar', 'mockjs', 'menu', 'route', 'utils', 'component', 'kit','tabs'], function(exports) {
  var element = layui.element,
    utils = layui.utils,
    $ = layui.jquery,
    _ = layui.lodash,
    route = layui.route,
    layer = layui.layer,
    menu = layui.menu,
    tabs = layui.tabs,
    component = layui.component,
    kit = layui.kit;

  var Admin = function() {
    this.config = {
      elem: '#setting',
      loadType: 'SPA'
    };
    this.version = '1.0.0';
  };


  Admin.prototype.ready = function(callback) {
    var that = this,
      config = that.config;

    // 初始化加载方式
    const { getItem } = utils.localStorage;
    const setting = getItem("KITADMIN_SETTING_LOADTYPE");
    if (setting !== null && setting.loadType !== undefined) {
      config.loadType = setting.loadType;
    }

    kit.set({
      type: config.loadType
    }).init();

    // 初始化路由
    _private.routeInit(config);
    // 初始化左侧菜单
    _private.menuInit(config);

    // 跳转至首页
    if (location.hash === '') {
      utils.setUrlState('主页', '#/');
    }

    // 监听头部右侧 nav
    component.on('nav(header_right)', function(_that) {
      var target = _that.elem.attr('kit-target');
      if (target === 'setting') {
        // 绑定sidebar
        layui.sidebar.render({
          elem: _that.elem,
          //content:'', 
          title: '设置',
          shade: true,
          // shadeClose:false,
          // direction: 'left'
          dynamicRender: true,
          url: 'views/setting.jsp',
          // width: '50%', //可以设置百分比和px
        });
      }
    });

    // 初始化渲染
    route.render();
    that.render();

    // 执行回调函数
    typeof callback === 'function' && callback();
  }
  Admin.prototype.render = function() {
    var that = this;
    return that;
  }

  var _private = {
    routeInit: function(config) {
      var that = this;
      // route.set({
      //   beforeRender: function (route) {
      //     // 此配置可以限制页面访问
      //     if (!utils.oneOf(route.path, ['/user/table', '/user/table2', '/'])) {
      //       return {
      //         id: new Date().getTime(),
      //         name: 'unauthorized',
      //         path: '/error/unauthorized',
      //         component: 'views/error/unauthorized.html'
      //       };
      //     }
      //     return route;
      //   }
      // });
      // 配置路由
      /*routeOpts = {
        routes: [{
          path: '/',
          component: 'views/app.jsp',
          name: '控制面板'
        }, {
            path: '/dictionary',
            component: 'views/dictionary/dictionary.jsp',
            name: '数据字典'
         },{
        	 path: '/logging',
             component: 'views/logging.jsp',
             name: '日志管理'	
         },{
        	 path: '/user',
             component: 'views/user/user.jsp',
             name: '用户管理'	
         },{
        	 path: '/role',
             component: 'views/role/role.jsp',
             name: '角色管理'	
         },{
        	 path: '/permission',
             component: 'views/permiss/permission.jsp',
             name: '权限管理'	
         },{
        	 path: '/service',
             component: 'views/service/service.jsp',
             name: '服务管理'	
         },{
        	 path: '/customerCare',
             component: 'views/customerCare/customerCare.jsp',
             name: '客户关怀'	
         },{
        	 path: '/customer',
             component: 'views/customer/customer.jsp',
             name: '客户管理'	
         },{
        	 path: '/customerloss',
             component: 'views/customer/customerloss.jsp',
             name: '客户流失'	
         },{
        	 path: '/opportunity',
             component: 'views/opportunity/saleOpportunity.jsp',
             name: '销售机会'
         }
        ]
      };*/
      console.log(routeOpts);
      route.setRoutes(routeOpts);
      return this;
    },
    menuInit: function(config) {
      var that = this;

      // 配置menu
      menu.set({
        dynamicRender: false,
        elem: '#menu-box',
        isJump: config.loadType === 'SPA',
        onClicked: function(obj) {
        },
        remote: {
//          url: apiconfig.user.getMenus,
          method: 'post'
        },
        cached: false
      }).render();
      return this;
    },
    tabsInit: function() {
      tabs.set({
        onChanged: function(layid) {
          // var tab = tabs.get(layid);
          // if (tab !== null) {
          //   utils.setUrlState(tab.title, tab.path);
          // }
        }
      }).render(function(obj) {
        // 如果只有首页的选项卡
        if (obj.isIndex) {
          route.render('#/');
        }
      });
    }
  }

  var admin = new Admin();
  admin.ready(function() {
    console.log('Init successed.');
  });

  //输出admin接口
  exports('index', {});
});