if (layui === undefined) {
  console.error('请先引用layui.js文件.');
} else {

  var modules = {
    admin: 'kit_modules/admin',
    axios: 'kit_modules/axios',
    lodash: 'kit_modules/lodash',
    menu: 'kit_modules/menu',
    mockjs: 'kit_modules/mockjs',
    mockjsbase: 'kit_modules/mockjsbase',
    route: 'kit_modules/route',
    tabs: 'kit_modules/tabs',
    utils: 'kit_modules/utils',
    component:'kit_modules/component',
    nprogress:'kit_modules/nprogress',
    kit:'kit_modules/kit',
    sidebar:'kit_modules/sidebar',
    select:'kit_modules/select',
    echarts:'kit_modules/echarts'
  };

  layui.injectModules(modules);
}