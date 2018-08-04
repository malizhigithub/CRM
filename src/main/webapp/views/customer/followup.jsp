<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src="js/myutil.js"></script>

<div style="width: 96%;margin-left: 2%;">
        <!-- 跟踪记录 -->
        <shiro:hasPermission name="6002">
	        <div>
	                <button class="layui-btn" id="add-follow">新建跟踪记录</button>
	                <!-- <button class="layui-btn" id="delete-follow">删除</button> -->
	        </div>
        </shiro:hasPermission>
        <div id="show-followup">
            <ul class="layui-timeline" id="follow-flow">
            </ul>          
        </div>
</div>
	
<script type="text/javascript">
layui.use(['table','flow'],function(){
	var flow = layui.flow;
	var layer = layui.layer;
	var table = layui.table;
	var $ = layui.$;


	//使用流加载跟踪记录
	flow.load({
	   elem: '#follow-flow' //指定列表容器
	   ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
	     var lis = [];
	     //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
	     $.post('${pageContext.request.contextPath}/followup/list',{'page':page}, function(res){
	       //假设你的列表返回在data集合中
	       layui.each(res.data, function(index, item){
	    	 var title = '' + item.time[0] + '年' + item.time[1] + '月' + item.time[2] + '日' + '   ' + item.time[3] + ':' +item.time[4] + ':' +item.time[5];
	         //var title = Format(item.time,'yyyy-MM-dd HH:mm:ss');
	    	 var str = '<li class="layui-timeline-item"><i class="layui-icon layui-timeline-axis">&#xe63f;</i>';
	         str += '<div class="layui-timeline-content layui-text" >';
	         str += '<h3 class="layui-timeline-title"> <a id="manager-' + item.manager.id + '" style="font-size: 16px;">' + item.manager.account + '</a> ';
	         str += '<a href="javascript:" style="font-size: 18px;color:black;" id="followup-' + item.id + '">' + title + '</a></h3><p>';
	         str += '跟踪对象：<a href="javascript:" style="color:black;" id="customerInfo-' + item.customer.id + '">' + item.customer.name + '</a><br/>';
	         str += '概要信息：' + item.general + '</p></div></li>';
	    	 lis.push(str);
	       }); 
	       
	       //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
	       //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
	       next(lis.join(''), page < res.pages);    
	         
	     });
	   }
	 });                                   
                  
                  
     //点击新建跟踪记录按钮        
     $('#add-follow').click(function(){
    	 layer.open({
             type:2,
             title:'新建跟踪',
             area:['500px','710px'],
             closeBtn:1,
             shadeClose:false,
             content:'${pageContext.request.contextPath}/views/customer/editfollowup.jsp?type=add',
             end:function(){
            	  location.reload();
             }       
         });
     });             
                  
     
	//展示跟踪记录详细信息
	//动态加载出来的元素需要使用on来绑定
	$(document).on("click","a[id^=followup]",function(){
        //layer.msg('click');
        //console.log(this);
        var id = this.id.split("-")[1];
        layer.open({
            type:2,
            title:'详情',
            area:['400px','60%'],
            closeBtn:1,
            shadeClose: true,
            content:'${pageContext.request.contextPath}/views/customer/followupInfo.jsp?id=' + id,
            end:function(){
                 //location.reload();
            }       
        });
    });

   $(document).on("click","a[id^=manager]",function(){
        //layer.msg('click');
        //console.log(this);
        
        var id = this.id.split("-")[1];
        layer.open({
            type:2,
            title:'用户信息',
            area : ['1000px','400px'],
            clostBtn:1,
            shadeClose: true,
            content:'${pageContext.request.contextPath}/views/user/showUserInfo.jsp?id='+ id
        }); 
    });
	
	$(document).on("click","a[id^=customerInfo]",function(){
		   var customerid = this.id.split("-")[1];
		   layer.open({
		        type:2,
		        title:'客户详情',
		        area:['80%','100%'],
		        clostBtn:1,
		        shadeClose: true,
		        maxmin:true,
		        offset:'r',
		        content:'${pageContext.request.contextPath}/views/customer/customerInfo.jsp?id='+ customerid
		    });	
		
	});

	function localDateTimeToStr(time){
		var str = '';
		var len = time.length;
		switch(len){
			case 1: str = '' + time[0];break;
			case 2: str = '' + time[0] + '-' + time[1];break;
			case 3: str = '' + time[0] + '-' + time[1] + '-' + time[2];break;
			case 4: str = '' + time[0] + '-' + time[1] + '-' + time[2] + '   ' + time[3];break;
			case 5: str = '' + time[0] + '-' + time[1] + '-' + time[2] + '   ' + time[3] + ':' + time[4];break;
			case 6: str = '' + time[0] + '-' + time[1] + '-' + time[2] + '   ' + time[3] + ':' + time[4] + ':' + time[5];break;
		}
		return str;
	}
	
});
</script>
