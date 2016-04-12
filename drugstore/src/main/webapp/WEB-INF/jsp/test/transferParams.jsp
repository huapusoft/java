

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
		<h4>保存出库登记</h4>
		参数格式：
		<br/>
&nbsp;&nbsp;{<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"billType" : "出库",<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"typeData" : "手术室",<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"sum1" : 123.11,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"sum2" : 456.32,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"detailList" : <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"orderNo" : 1,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"drugId" : 1001,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"invoiceNo" : "P000234123",<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"batchNo" : "国药准字CB12382193",<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"amount" : 2,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"price1" : 1,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"price2" : 3.4,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"validDate" : "2016-04-11"//可能多种格式<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"orderNo" : 2,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"drugId" : 1002,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"invoiceNo" : "P00045623",<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"batchNo" : "国药准字CB12376876",<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"amount" : 5,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"price1" : 10,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"price2" : 47,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"validDate" : "2016-05-12"//可能多种格式<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;]<br/>
&nbsp;&nbsp;}<br/>
			请求地址：
			<select id="url" name="url">
				<option value="/outStorage/save">保存出库登记-/outStorage/save</option>
			</select>
			<button onclick="save()">测试</button>
			<br/>
			返回结果：<textarea id="result" name="result" rows="10"  cols="150" ></textarea>
			
		
	</body>
</html>

<script type="text/javascript">
function save(){
	$('#saveBtn').attr("disabled", true);
	$('#result').html("")
	var $paramJson={
			"billType" : "出库",
			"typeData" : "手术室",
			"sum1" : 123.11,
			"sum2" : 456.32,
			"detailList" : 
				[
			      	{
			    	  	"orderNo" : 1,
						"drugId" : 1001,
						"batchNo" : "国药准字CB12382193",
						"amount" : 2,
						"price1" : 1,
						"price2" : 3.4,
						"validDate" : "2016-04-11"//可能多种格式
			      	},
			      	{
			    	  	"orderNo" : 2,
						"drugId" : 1002,
						"batchNo" : "国药准字CB12376876",
						"amount" : 5,
						"price1" : 10,
						"price2" : 47,
						"validDate" : "2016-05-12"//可能多种格式
			      	}       
				]
	};
	
	$.ajax({
		async:false,
		type : 'POST',
		url : $('#url').val(),
		data : JSON.stringify( $paramJson ),
		contentType:"application/json;charset=UTF-8",
		dataType :'JSON',
		success : function(data){
			if( data ){
				$('#result').html("data.code--"+data.code+"\r\ndata.msg--"+data.msg);
				
			}else{
				$('#result').html("无");
				
			}
			$('#saveBtn').attr("disabled", false);
		}
	})
	
}
</script>