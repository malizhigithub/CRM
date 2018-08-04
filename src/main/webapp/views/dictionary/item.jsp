<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>字典子项</title>
	<link rel="stylesheet" href="../../layui/css/layui.css">
	<script src="../../layui/layui.js"></script>
	<script type="text/javascript" src="../../js/myutil.js"></script>
</head>
<body>
	<form class="layui-form">
		<blockquote class="layui-elem-quote quoteBox">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" name="name" class="layui-input" placeholder="请输入搜索的内容">
				</div>
				<button type="button" class="layui-btn" data-type="reload" id="searchButton">搜索</button>
			</div>
			<div class="layui-inline">
				<button type="button" class="layui-btn layui-btn-warm" id="add-button">添加</button>
			</div>
			<div class="layui-inline">
				<button type="button" id="delete-button" class="layui-btn layui-btn-danger layui-btn-normal">删除</button>
			</div>
		</blockquote>
	</form>
	
	<table class="layui-hide" id="item-table" lay-filter="item-table"></table>
	<script type="text/html" id="item-bar">
  		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>
	
	<script type="text/html" id="checkboxTpl">
        <input type="checkbox" name="status" value="{{d.id}}" title="锁定" lay-filter="lockStatus" {{ d.status == 0 ? 'checked' : '' }}>
	</script>	
	
<script type="text/javascript">
layui.use(['table','form'],function(){
	var table = layui.table;
	var layer = layui.layer;
	var form = layui.form;
	var $ = layui.$;
	
	//获取参数列表
	var parms = getParm();
	var id = parms['typeid'];

	var tableload = layer.load(2);
	table.render({
		    elem: '#item-table'       //html中表格的id
		    ,even: true
		    ,url: '${pageContext.request.contextPath}/dictionary/item/list' //数据接口
		    ,method : 'post'
		    ,page:false
		    ,height: 'full-200'
		    ,cols: [[ //表头
		        {type:'checkbox'}
		      	/* ,{field: 'id', 
		    	  title: 'ID',
		    	  width:100, 
		    	  align:'center',
		    	  sort: true
		    	} */
		      ,{field:'name',  title: '名称',align:'center',edit:'text'}
		      	,{field:'status',  
		      	  title: '状态',
		      	  width:200,
		      	  align:'center',
		      	  templet: '#checkboxTpl', unresize: true
		      	}
		     // ,{ width:178, align:'center', toolbar: '#item-bar'}
		      
		    ]],
		    where:{"typeid":id},
		    done: function(res, curr, count){
		    	layer.close(tableload);
		    },
		    text:{
		    	none:'这里什么都没有'
		    }
		  }); 
	
        //删除事件
	    $('#delete-button').click(function(){
	        var checkStatus = table.checkStatus('item-table')
	        ,data = checkStatus.data;
	        if(data.length <= 0){
	            layer.msg('请至少选择一行数据');
	            return;
	        }
	        
	        var showStr = '你确定删除以下字典子项吗？<br>';
	        layui.each(data,function(index,item){
	            showStr += item.name + '<br>';
	        });
	        
	        layer.confirm(showStr,{icon:7, title:'确认删除'},function(index){
	            layer.close(index);
	            var ids = [];
	            layui.each(data,function(index,item){
	                ids.push(item.id);
	            });
	            
	            $.ajax({
	                  url:'${pageContext.request.contextPath}/dictionary/item/admin/delete',
	                  data:{'ids':ids},
	                  traditional:true,   //使用传统模式传递数组
	                  success:function(data){
	                      var str = '<div style="text-align:center" >删除完成<br>';
	                      str += '成功条目：'+ data.success.length + ' <br>';
	                      str += '失败条目：'+ data.fail.length + ' <br>';
	                      str += '合计条目：'+ ids.length + ' <br></div>';
	                      var index = layer.open({
	                          type:1,
	                          title:'操作完成',
	                          btn:'确定',
	                          content:str,
	                          end:function(){
	                              layer.close(index);
	                              table.reload('item-table');
	                          }
	                      }); 
	                  }
	            });
	        });
	 
	    });
		
		
		
	    
		
	    //监听锁定操作
	    form.on('checkbox(lockStatus)', function(obj){
	      //layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
	      
	      var itemid = this.value;
	      var status  = 0;
	      if(obj.elem.checked){
	    	  status  = 0;
	      }else{
	    	  status = 1;
	      }
	      var index = top.layer.msg('修改中...', {
	            icon : 16,
	            time : false,
	            shade : 0.8
	        });
	      $.post('${pageContext.request.contextPath}/dictionary/item/admin/update',
	    		  {'id':itemid,'status':status},
	    		  function(data){
	    			  layer.msg(data.msg);
	    			  top.layer.close(index);
	    			  table.render();
	    		  }
	      );
	      
	    });
		
		
		//监听名称编辑操作
		table.on('edit(item-table)', function(obj){
		  var value = obj.value //得到修改后的值
		  ,data = obj.data //得到所在行所有键值
		  ,field = obj.field; //得到字段
		  var itemid = data.id;
		  var name = value;
		  var index = top.layer.msg('修改中...', {
              icon : 16,
              time : false,
              shade : 0.8
          });
	        $.post('${pageContext.request.contextPath}/dictionary/item/admin/update',
	                {'id':itemid,'name':name},
	                function(data){
	                    layer.msg(data.msg);
	                    top.layer.close(index);
	                    table.render();
	                }
	        );
		  
		});	    
	    
		
		//点击添加按钮
		$('#add-button').click(function(){
			var typeid = id;
			showForm('添加子项',typeid);
		});
	
	  function showForm(title,id){
		  	layer.open({
		  		  type: 2,
		  		  title: title,
		  		  closeBtn: 1,
		  		  area: ['350px','250px'],
		  		  shadeClose: false,
		  		  content: '${pageContext.request.contextPath}/views/dictionary/edititem.jsp?typeId=' + id,
		  		  end:function(){
		  				//执行重载
		  		      table.reload('item-table', {});
		  		  }
		  		});
		    }
	
	
});
</script>
		
</body>
</html>