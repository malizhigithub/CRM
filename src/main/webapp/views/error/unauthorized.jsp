<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="layui-fluid">
  <div class="layui-row">
    <div class="layui-col-xs12">
      <div style="text-align: center; margin-top: 80px;">
        <h2>无权访问该页</h2>
        <div id="ccccc_hash"></div>
      </div>
    </div>
  </div>
</div>
<script>
  layui.use(['layer', 'util'], function () {
    var layer = layui.layer,
      $ = layui.jquery,
      util = layui.util;
    $('#ccccc_hash').html(util.toDateString(new Date().getTime()));
  });
</script>
<style scoped>
</style>