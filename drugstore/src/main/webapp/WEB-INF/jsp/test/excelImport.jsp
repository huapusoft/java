

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
		<script type="text/javascript" src="/staticPublic/js/jquery.form.js"></script>
	</head>
	<body>
		<h4>excel导入</h4>
			<form id="targetForm" enctype="multipart/form-data" method="post">
				上传excel文件：<input type="file" id="excelFile" name="excelFile" />
				<button>测试</button>
			</form>
		
	</body>
</html>

<script type="text/javascript">
$(function(){
	$('#targetForm').submit(function() {   
		var options = {
		        url : "/test/excelImport" ,
		        type : "POST",
		        success : function(data) {
		          	//var data=JSON.parse(dataStr);
                    if( data ){
                        alert(data.msg);
                    }
		        },
		        error : function(XMLHttpRequest, textStatus, errorThrown) {
		            alert(textStatus + "," + errorThrown);
		        }
		 };
         $( "#targetForm").ajaxSubmit(options);//异步提交文件 enctype="multipart/form-data"
         return false; 
	});
})
</script>