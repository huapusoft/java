/**
 * 全局对象
 */
// 全局对象
var g = {

	resize : 0

};

// 定义全局静态方法
g.test = function() {
	alert(this.name);
};

// 截取中文字符串
g.mSubstr = function(str, slen) {
	var tmp = 0;
	var len = 0;
	var okLen = 0;
	for ( var i = 0; i < slen; i++) {
		if (str.charCodeAt(i) > 255) {
			tmp += 2;
		} else {
			len += 1;
		}
		okLen += 1;
		if (tmp + len == slen) {
			return (str.substring(0, okLen));
		}
		if (tmp + len > slen) {
			return (str.substring(0, okLen - 1));
		}
	}
};

// 计算包含中文的支付串长度
g.mStrLen = function(str) {
	var len;
	var i;
	len = 0;
	for (i = 0; i < str.length; i++) {
		if (str.charCodeAt(i) > 255)
			len += 2;
		else
			len++;
	}
	return len;
};

// 判断是否是邮件地址
g.isEmail = function(str) {
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	return reg.test(str);
};

// 判断是否是手机号码格式
g.isMobile = function(str) {
	var reg = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/;
	return reg.test(str);
};

// 检查数字
g.clearNotNum = function(obj) {
	// 先把非数字的都替换掉，除了数字和.
	obj.value = obj.value.replace(/[^\d.]/g, "");
	// 必须保证第一个为数字而不是.
	obj.value = obj.value.replace(/^\./g, "");
	// 保证只有出现一个.而没有多个.
	obj.value = obj.value.replace(/\.{2,}/g, ".");
	// 保证.只出现一次，而不能出现两次以上
	obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
};

//
g.clearNotInt = function(obj) {
	// 先把非数字的都替换掉，除了数字和.
	obj.value = obj.value.replace(/[^\d]/g, "");
};


// 日期字符串转时间戳
g.datetimeToUnix = function(datetime) {
	var tmp_datetime = datetime.replace(/:/g, '-');
	tmp_datetime = tmp_datetime.replace(/ /g, '-');
	var arr = tmp_datetime.split("-");
	var now = new Date(Date.UTC(arr[0], arr[1] - 1, arr[2], arr[3] - 8, arr[4],
			arr[5]));
	return parseInt(now.getTime() / 1000);
};

// 时间戳转日期
g.unixToDatetime = function(unix) {
	var now = new Date(parseInt(unix) * 1000);
	return now.toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
};

// 获取当前时间
g.getDate = function(time) {
	var d, s;
	d = new Date();// 当前时间
	if (typeof (time) != 'undefined') {
		d.setTime(d.getTime() + time);
	}
	s = d.getFullYear() + "-"; // 取年份
	s = s + (d.getMonth() + 1) + "-";// 取月份
	s += d.getDate(); // 取日期
	return (s);
};

// 获取当前时间
g.getTime = function() {
	var d, s;
	d = new Date();
	s = d.getHours() + ":"; // 取小时
	s += d.getMinutes() + ":"; // 取分
	s += d.getSeconds(); // 取秒
	return (s);
};

// 删除左右两端的空格
g.trim = function(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
};

// 删除左边的空格
g.ltrim = function(str) {
	return str.replace(/(^\s*)/g, "");
};

// 删除右边的空格
g.rtrim = function(str) {
	return str.replace(/(\s*$)/g, "");
};

// 检查url
g.isURL = function(str_url) {
	var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
			+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@
			+ "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
			+ "|" // 允许IP和DOMAIN（域名）
			+ "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
			+ "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
			+ "[a-z]{2,6})" // first level domain- .com or .museum
			+ "(:[0-9]{1,4})?" // 端口- :80
			+ "((/?)|" // a slash isn't required if there is no file name
			+ "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
	var re = new RegExp(strRegex);
	// re.test()
	if (re.test(str_url)) {
		return (true);
	} else {
		return (false);
	}
};


//生日选择的年月日，自动生成option
g.birthOption = function(target, start, end, value) {
	if (start < end) {
		for (var i=start; i<=end; i++) {
			var select = "";
			if (i == value) {
				select = "selected";
			}
			$(target).append("<option value='"+i+"' "+select+">"+i+"</option>");
		}
	} else {
		for (var i=start; i>=end; i--) {
			var select = "";
			if (i == value) {
				select = "selected";
			}
			$(target).append("<option value='"+i+"' "+select+">"+i+"</option>");
		}
	}
};
