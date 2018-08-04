<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>          
<hr class="layui-bg-orange">

<!-- 搜索框 -->
<form class="layui-form">
		<blockquote class="layui-elem-quote quoteBox">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" class="layui-input " name="name"  placeholder="请输入搜索的内容">
				</div>
				<button type="button" class="layui-btn" data-type="reload" id="searchButton">搜索</button>
			</div>
			
			<shiro:hasPermission name="20002">
				<div class="layui-inline">
					<button type="button" class="layui-btn layui-btn-warm" id="add-button">添加</button>
				</div>
			</shiro:hasPermission>
			
			<shiro:hasPermission name="20004">
				<div class="layui-inline">
					<button type="button" class="layui-btn layui-btn-danger layui-btn-normal" id="delete-button">删除</button>
				</div>
			</shiro:hasPermission>
	</blockquote>
</form>

<!-- 要显示的表格 id是必须的 -->
<table class="layui-hide" id= "dictionary-table" lay-filter="dictionary-table">
</table>        

<script type="text/html" id="table-bar">
  <a class="layui-btn layui-btn-xs" lay-event="detail">查看子项</a>
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script >
layui.use(['table','element'], function(){
    $ = layui.jquery;
    var table = layui.table;
    var layer = layui.layer;
    var element = layui.element;

  //加载表格数据
  var tableload = layer.load(2);
  table.render({
    elem: '#dictionary-table'       //html中表格的id
    ,even: true						//开启隔行变色
    ,url: 'dictionary/admin/list' //数据接口
    ,method : 'post'
    ,page: true //开启分页
    ,cols: [[ //表头
        {type:'checkbox'}
      ,{field: 'id', title: 'ID',align:'center', width:100, sort: true, }//fixed: 'left'
      ,{field:'name', title: '字典名称'}
      ,{fixed: 'right', align:'center',width:200, toolbar: '#table-bar'}
    ]],
    done: function(){
    	layer.close(tableload);
    }
  });
    
  //监听数据操作按钮
  table.on('tool(dictionary-table)', function(obj){
        var data = obj.data;
        if(obj.event === 'detail'){
          	seeDictionaryItem(data);
        } else if(obj.event === 'del'){
          layer.confirm('真的删除该字典吗?', function(index){
        	  layer.close(index);
        	  deleteData(data.id); 
          });
        } else if(obj.event === 'edit'){
        	//显示表单
            showForm('更新字典',data.id);
        }
      });
  
  //删除操作
  function deleteData(id){
	  var index = 0;
	  $.ajax({
          type: "POST",
          url: 'dictionary/admin/delete',
          data: {"id":id},
          dataType: "json",
          //请求前执行
          beforeSend:function(){
              load = layer.load(2);//显示加载动画
          },
          complete:function(){//请求完成执行，无论请求是否成功
              layer.close(load);//关闭加载动画
          },
          success: function(data){
              layer.msg(data.msg);
              if(data.success){//成功
                  //执行重载
                  table.reload('dictionary-table', {
                    page: {
                      curr: 1 //重新从第 1 页开始
                    }
                  });
              }
          },
          error:function(){
              layer.msg("服务器开小差了，请稍后再试...");
          }
          
      });
  }
  
  //批量删除
  $('#delete-button').click(function(){
	  
	  var checkStatus = table.checkStatus('dictionary-table')
      ,data = checkStatus.data;
      if(data.length <= 0){
          layer.msg('请至少选择一行数据');
          return;
      }
      
      var showStr = '你确定删除以下字典吗？<br>';
      layui.each(data,function(index,item){
          showStr += item.name + '<br>';
      });
      
      layer.confirm(showStr,{icon:7, title:'确认删除'},function(index){
          layer.close(index);
          
          var su = 0;
          var fail = 0;
          var len = data.length;
          for(var i=0;i<data.length;i++){
        	    
        	  $.ajax({
                  type: "POST",
                  url: 'dictionary/admin/delete',
                  data: {"id":data[i].id},
                  dataType: "json",
                  success: function(data){
                	  var p = (i + 1)/len * 100;
                	  element.progress('demo', p + '%');
                	  if(data.success){
                		  su ++;
                	  }else{
                		  fail ++;
                	  }              	  
                  },
                  error:function(){
                      layer.msg("服务器开小差了，请稍后再试...");
                  } 
              }); 
        	  
          }  
          })
      });
	  

  //搜索功能
  $('#searchButton').on('click', function(){
      //执行重载
      table.reload('dictionary-table', {
        page: {
          curr: 1 //重新从第 1 页开始
        }
        ,where: {
            name: $('input[name=name]').val()
        }
      });
     
	});
  
  
  //查看字典子项
  function seeDictionaryItem(data){
	  var window = layer.open({
		  type: 2,
		  title: '字典子项',
		  area:['50%','80%'],
		  closeBtn: 1,
		  //area: '516px',
		  //skin: 'layui-bg-black', //没有背景色
		  shade:0.5,
		  shadeClose: false,
		  content: 'views/dictionary/item.jsp?typeid=' + data.id,
		});
  	}
  
	//点击添加按钮
	$('#add-button').click(function(){
		showForm('添加字典',-1);
	});

  function showForm(title,id){
  	layer.open({
  		  type: 2,
  		  title: title,
  		  closeBtn: 1,
  		  //area: '516px',
  		  //skin: 'layui-bg-black', //没有背景色
  		  shadeClose: false,
  		  content: 'views/dictionary/edittype.jsp?id=' + id,
  		  end:function(){
  				//执行重载
  		      table.reload('dictionary-table', {
  		        /* page: {
  		          curr: 1 //重新从第 1 页开始
  		        } */
  		      });
  		  }
  		});
    }
});

</script>