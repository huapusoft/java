

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
		<h2>Hello，${user.name}</h2>
		<br></br>
		<button onclick="logout()">退出</button>
	</body>
</html>
<script>
function logout(){
	location.href="logout";
}
</script>