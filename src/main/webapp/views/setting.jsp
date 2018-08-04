<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="layui-fluid">
  <div class="layui-row">
    <div class="layui-col-xs12">
      <form class="layui-form" action="">
        <input type="radio" name="theme" lay-filter="theme"  value="layui-bg-cyan" title="默认（藏青）" checked><br/>
        <input type="radio" name="theme" lay-filter="theme"  value="layui-bg-red" title="红色"><br/>
        <input type="radio" name="theme" lay-filter="theme"  value="layui-bg-orange" title="橙色"><br/>
        <input type="radio" name="theme" lay-filter="theme"  value="layui-bg-green" title="墨绿"><br/>
        <input type="radio" name="theme" lay-filter="theme"  value="layui-bg-blue" title="蓝色"><br/>
        <input type="radio" name="theme" lay-filter="theme"  value="layui-bg-black" title="雅黑"><br/>
      </form>
    </div>
  </div>
</div>
<script>
  layui.use(['form'], function() {
    var form = layui.form,
      $ = layui.jquery;
    var layer = layui.layer;

    form.render();
    form.on('radio(theme)', function(data) {
      var val = data.value;
      var header = $('.layui-header');
      header.removeClass();
      header.addClass('layui-header ' + val);
      
      var nav = $('.kit-nav');
      nav.removeClass();
      nav.addClass('kit-nav ' + val);
      
      var scroll = $('.layui-side-scroll');
      scroll.removeClass();
      scroll.addClass('layui-side-scroll ' + val);
    });
  });
</script>
<style scoped>

</style>