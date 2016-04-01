

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<script type="text/javascript" src="/staticPublic/js/jquery-1.9.1.min.js"></script>
	</head>
	<body>
		<h2>登录</h2>
		<br />
			用户名：<input type="text" id="name" name="name" />
			密码：<input type="password" id="password" name="password" />
			<button onclick="login()">提交</button>
			
			
		<br/>
		<br/>
		<br/>
		<br/>
		用户1：<input type="text" id="name1" name="name1" />
		用户2：<input type="text" id="name2" name="name2" />
			<button onclick="add()">新增</button>
		
	</body>
</html>

<script type="text/javascript">
function login(){
	$.ajax({
		type:'POST',
		url:"login",
		data:{
			name : $('#name').val(),
			password : $('#password').val()
		},
		dataType:'JSON',
		success:function(data){
			if(data && data.code == 200 ){
				location.href='/index';
			}else{
				alert(data.msg);
			}
		}
	})
}

function add(){
	$.ajax({
		type:'POST',
		url:"add",
		data:{
			name1 : $('#name1').val(),
			name2 : $('#name2').val()
		},
		dataType:'JSON',
		success:function(data){
			if(data && data.code == 200 ){
				alert('新增成功');
			}else{
				alert(data.msg);
			}
		}
	})
}
</script>