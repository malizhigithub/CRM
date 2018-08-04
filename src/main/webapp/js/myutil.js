
/**
 * 通过地址获取参数
 * @returns 返回获取到的参数，没有参数则返回一个空字典{}
 */
function getParm() {
	var search = location.search; //获取search
	var search_str = search.split("?"); //去掉问号
	var data = {};
	if (search_str.length < 2) { //判断问号后面是否有参数
		return {};
	}
	search_parm = search_str[1]; //获取问号后的参数字符串
	var parms = search_parm.split("&"); //按照&符号分割字符串
	for (var i = 0; i < parms.length; i++) {
		var parm = parms[i].split("="); //	按照=分割字符串，获得参数名与参数值
		data[parm[0]] = parm[1]; //保存参数
	}
	return data;
};

/**
 * 格式化时间
 * @returns 返回格式化后的时间
 */
function Format(datetime, fmt) {
	if (parseInt(datetime) == datetime) {
		if (datetime.length == 10) {
			datetime = parseInt(datetime) * 1000;
		} else if (datetime.length == 13) {
			datetime = parseInt(datetime);
		}
	}
	datetime = new Date(datetime);
	var o = {
		"M+" : datetime.getMonth() + 1, //月份   
		"d+" : datetime.getDate(), //日   
		"h+" : datetime.getHours(), //小时   
		"m+" : datetime.getMinutes(), //分   
		"s+" : datetime.getSeconds(), //秒   
		"q+" : Math.floor((datetime.getMonth() + 3) / 3), //季度   
		"S" : datetime.getMilliseconds() //毫秒   
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (datetime.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};

/*根据出生日期算出年龄*/
function getAges(strBirthday){       
    var returnAge;
    var strBirthdayArr = strBirthday.split("-");
    var birthYear = strBirthdayArr[0];
    var birthMonth = strBirthdayArr[1];
    var birthDay = strBirthdayArr[2];
    
    d = new Date();
    var nowYear = d.getFullYear();
    var nowMonth = d.getMonth() + 1;
    var nowDay = d.getDate();
    
    if(nowYear == birthYear){
        returnAge = 0;//同年 则为0岁
    }
    else{
        var ageDiff = nowYear - birthYear ; //年之差
        if(ageDiff > 0){
            if(nowMonth == birthMonth) {
                var dayDiff = nowDay - birthDay;//日之差
                if(dayDiff < 0)
                {
                    returnAge = ageDiff - 1;
                }
                else
                {
                    returnAge = ageDiff ;
                }
            }
            else
            {
                var monthDiff = nowMonth - birthMonth;//月之差
                if(monthDiff < 0)
                {
                    returnAge = ageDiff - 1;
                }
                else
                {
                    returnAge = ageDiff ;
                }
            }
        }
        else
        {
            returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天
        }
    }
    
    return returnAge;//返回周岁年龄
};

//计算还有几天到生日
		function brthDate(m,d){
		    console.log(m);
		    console.log(d);
		    var today=new Date();
		    var year=today.getFullYear();
		    var month=today.getMonth();
		    var date=today.getDate();
		     
		    var byear=year;
		    //计算今年生日是否过完，过完就算明年生日了
		    /* if(m<month){
		        byear++;
		    }else if(m==month || d<=date){
		        byear++;
		    } */
		     
		    var brthday=new Date(byear+"-"+m+"-"+d);
		    
		    console.log(brthday)
		    
		    //核心，两个日期相减，得到一个整数，是两个日期之间相差的毫秒数
		    var dms=brthday-today;
		    //毫秒除以1000得到秒，除以3600得到小时，除以24得到日
		    var dday=Math.round(dms/(1000*3600*24));
		    console.log(dday);
		    if(dday=='0' || dday < 0){
		      return "今天生日";
		    }
		    return "还有"+dday+"天";
};

/**
 * 是否为Null
 * @param object
 * @returns {Boolean}
 */ 
function isNull(object){ 
    if(object == null || typeof object == "undefined"){ 
        return true; 
    } 
    return false; 
};

/**
 * 根据日期字符串获取星期几
 * @param dateString 日期字符串（如：2016-12-29），为空时为用户电脑当前日期
 * @returns {String}
 */
function getWeek(dateString){
    var date;
    if(isNull(dateString)){
        date = new Date();
    }else{
        var dateArray = dateString.split("-");
        date = new Date(dateArray[0], parseInt(dateArray[1] - 1), dateArray[2]);
    }
    //var weeks = new Array("日", "一", "二", "三", "四", "五", "六");
    //return "星期" + weeks[date.getDay()];
    return "周" + "日一二三四五六".charAt(date.getDay());
};

