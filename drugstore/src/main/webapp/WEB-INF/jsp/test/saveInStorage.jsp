

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
		<script type="text/javascript" src="/staticPublic/js/jquery.validate.js"></script>
		<script type="text/javascript" src="/staticPublic/js/jquery.form.js"></script>
		<script type="text/javascript" src="/staticPublic/js/formJson.js"></script>
	</head>
	<body>
		<h4>保存入库登记</h4>
		参数格式：
		<br/>
&nbsp;&nbsp;{<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"billType" : "入库",<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"typeData" : "国药控股盐城有限公司",<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"sum1" : 3010.000,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"sum2" : 4510.000,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"detailList" : <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"orderNo" : 1,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"drugId" : 10021,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"invoiceNo" : "FAPIAO0001",<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"batchNo" : "PIHAO0001",<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"amount" : 100,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"price1" : 10.10,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"price2" : 15.00,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"validDate" : "2018-01-09"//可能多种格式<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"orderNo" : 2,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"drugId" : 10017,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"invoiceNo" : "FAPIAO0002",<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"batchNo" : "PIHAO0002",<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"amount" : 100,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"price1" : 20.0,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"price2" : 30.10,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"validDate" : "2018-01-09"//可能多种格式<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;]<br/>
&nbsp;&nbsp;}<br/>
			请求地址：
			<select id="url" name="url">
				<option value="/inStorage/save">保存入库登记-/inStorage/save</option>
			</select>
			<button onclick="save()">测试</button>
			<br/>
			返回结果：<textarea id="result" name="result" rows="10"  cols="150" ></textarea>
			
			<br></br>
			<br></br>
			<br></br>
			<br></br>
			<form id="companyForm" method="post">
			  <div class="table_group qydj">
			    <table class="table">
			      <tr>
			        <td>企业名称</td>
			        <td colspan="2"><input type="text" class="text" id="companyName" name="companyName" value="1111" /></td>
			        <td>组织机构代码</td>
			        <td colspan="2"><input type="text" class="text" id="orgCode" name="orgCode"  value="1111" /></td>
			      </tr>
			      <tr>
			        <td>联系地址</td>
			        <td colspan="3"><input type="text" class="text" id="linkAddress" name="linkAddress"  value="1111" /></td>
			      </tr>
			    </table>
			    <table class="table addline">
			      <tr class="editr">
			        <td>
			          <div class="tite">企业联系人</div>
			          <input type="text" class="text" name="linkmanList[0].linkman"  value="1111" />
			        </td>
			        <td>
			          <div class="tite">职务</div>
			          <input type="text" class="text" name="linkmanList[0].position"  value="1111" />
			        </td>
			        <td>
			          <div class="tite">固定电话</div>
			          <input type="text" class="text" name="linkmanList[0].telephone"  value="1111" />
			        </td>
			        <td>
			          <div class="tite">手机</div>
			          <input type="text" class="text" name="linkmanList[0].mobile"  value="1111" />
			        </td>
			        <td>
			          <div class="tite">QQ</div>
			          <input type="text" class="text" name="linkmanList[0].qq"  value="1111" /></td>
			        <td>
			          <div class="tite">E-mail</div>
			          <input type="text" class="text" name="linkmanList[0].email"  value="1111" />
			        </td>
			      </tr>
			    </table>
			    <table class="table addline">
			      <tr class="editr">
			        <td>
			          <div class="tite">企业联系人</div>
			          <input type="text" class="text" name="linkmanList[1].linkman"  value="1111" />
			        </td>
			        <td>
			          <div class="tite">职务</div>
			          <input type="text" class="text" name="linkmanList[1].position"  value="1111" />
			        </td>
			        <td>
			          <div class="tite">固定电话</div>
			          <input type="text" class="text" name="linkmanList[1].telephone"  value="1111" />
			        </td>
			        <td>
			          <div class="tite">手机</div>
			          <input type="text" class="text" name="linkmanList[1].mobile"  value="1111" />
			        </td>
			        <td>
			          <div class="tite">QQ</div>
			          <input type="text" class="text" name="linkmanList[1].qq"  value="1111" /></td>
			        <td>
			          <div class="tite">E-mail</div>
			          <input type="text" class="text" name="linkmanList[1].email"  value="1111" />
			        </td>
			      </tr>
			    </table>
			  </div>
			</form>
			<button onclick="testGetJson()">测试</button>
	</body>
</html>

<script type="text/javascript">
function save(){
	$('#saveBtn').attr("disabled", true);
	$('#result').html("")
	var $paramJson={
			"billType" : "入库",
			"typeData" : "国药控股盐城有限公司",
			"sum1" : 3010.000,
			"sum2" : 4510.000,
			"detailList" : 
				[
			      	{
			    	  	"orderNo" : 1,
						"drugId" : 10021,
						"invoiceNo" : "FAPIAO0001",
						"batchNo" : "PIHAO0002",
						"amount" : 100,
						"price1" : 10.00,
						"price2" : 15.00,
						"validDate" : "2018-01-09"//可能多种格式
			      	},
			      	{
			    	  	"orderNo" : 2,
						"drugId" : 10017,
						"invoiceNo" : "FAPIAO0002",
						"batchNo" : "PIHAO0002",
						"amount" : 100,
						"price1" : 20.00,
						"price2" : 30.10,
						"validDate" : "2018-01-09"//可能多种格式
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

function testGetJson(){
	var $paramJson = $.formHelper.getObject($('#companyForm').serialize());
	alert( JSON.stringify( $paramJson ) );
	
}
</script>