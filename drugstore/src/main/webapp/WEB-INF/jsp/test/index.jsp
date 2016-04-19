

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
		<h4>${user.name }, 欢迎登录！</h4><button onclick="logout()">退出</button>
		<!-- <h4>----事务测试</h4>
		用户1：<input type="text" id="name1" name="name1" />
		用户2：<input type="text" id="name2" name="name2" />
			<button onclick="add()">新增</button>
		
		<br/>
		<br/>
		<br/>
		<br/> -->
		<br/>
		<br/>
		<h4>前后台交互demo</h4>
		<a href="/test/saveInStorage" target="_blank">1.入库保存-->点击查看</a>
		<br/>
		<br/>
		<a href="/test/transferParams" target="_blank">2.出库保存-->点击查看</a>
		<br/>
		<br/>
		<h4>excel导出</h4>
		<a href="/test/export" target="_blank">excel导出-->点击查看</a>
		
		<br/>
		<br/>
		<h4>excel导入</h4>
		<a href="/test/excelImport" target="_blank">excel导入-->点击查看</a>
		
		
		<br/>
		<br/>
		<h4>库存excel导出</h4>
		<a href="/storeQuery/export" target="_blank">excel导出-->点击查看</a>
		
	</body>
</html>

<script type="text/javascript">
/* function add(){
	$.ajax({
		type:'POST',
		url:"/test/add",
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
} */
function logout(){
	location.href="logout";
}
</script>