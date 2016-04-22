

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
<script type="text/javascript" src="/staticPublic/js/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="/staticPublic/themes/material/easyui.css"/>
<script src="/staticPublic/js/easyui-lang-zh_CN.js"></script>
<style type="text/css">
body {
	background: #a3e9e6;
	font-family: "Helvetica Neue", "Hiragino Sans GB", "Microsoft YaHei","\9ED1\4F53", Arial, sans-serif;
	color: #222;
	font-size: 18px;
}

* {
	padding: 0px;
	margin: 0px;
}

.maincenter {
	margin: 130px auto auto;
	background: #a3e9e6;
	border-image: none;
	width: 900px;
	height: 650px;
	text-align: center;
}

.mainlogn {
	margin: 50px auto auto;
	border-image: none;
	width: 780px;
	height: 50px;
}

.mainlogncenter {
	margin: 10px auto auto;
	background: url(/staticPublic/img/denglukuang.png);
	border-image: none;
	width: 780px;
	height: 430px;
	filter: progid:DXImageTransform.Microsoft.Shadow(color=#909090, strength=15);/*ie*/
	-moz-box-shadow: 3px 3px 10px #909090; /*firefox*/
	-webkit-box-shadow: 3px 3px 10px #909090; /*safari或chrome*/
	/*  box-shadow:0px 0px 30px #909090; */ /*opera或ie9*/
	box-shadow: 0 6px 6px -2px rgba(255, 255, 255, 0.7) inset, 0 8px 10px	2px rgba(0, 0, 0, 0.3)
}

.fonttitle {
	float: left;
	padding-left: 405px;
	padding-top: 25px;
	font-family: "Helvetica Neue", "Hiragino Sans GB", "Microsoft YaHei","\9ED1\4F53", Arial, sans-serif;
	color: #000;
	font-size: 24px;
}

.fonttitles {
	float: left;
	padding-left: 405px;
	padding-top: 25px;
	font-family: "Helvetica Neue", "Hiragino Sans GB", "Microsoft YaHei","\9ED1\4F53", Arial, sans-serif;
	color: #000;
	font-size: 24px;
}

.ipt {
	border: 2px solid #d3d3d3;
	padding: 10px 10px;
	width: 300px;
	margin-top: 5px;
	margin-left: 365px;
	/* border-radius: 4px; */
	/* padding-left: 35px; */
	/* -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
	box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
	-webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
	transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s */
}

.selecttype {
	width: 325px;
	height: 36px;
	margin-top: 5px;
	margin-left: 365px;
	border: 2px solid #d3d3d3;
}

.buttonmenue {
	width: 180px;
	height: 60px;
	border: none;
	margin-left: 365px;
	background: url(/staticPublic/img/buttonlog.png);
}

.menuetitle {
	font-family: "黑体";
	color: #fff;
	font-size: 34px;
}
.logoimg {
    width: 120px;
	height: 55px;
	float:left;
	background: url(/staticPublic/img/logoes.png) no-repeat;
}
 .logotitle {
    width: 180px;
	height: 55px;
	float:left;
	background: url(/staticPublic/img/logotitle.png) no-repeat;
 }
</style>
</head>
<body>
	<div class="maincenter">
		<div class="mainlogn">
		<div class="logoimg"></div>
		<div class="logotitle"></div>
		</div>
		<div class="mainlogncenter">
			<span class="fonttitle"> 药&nbsp;&nbsp;&nbsp;库</span>

			<p style="position: relative;">
				<select name="storeName" class="selecttype" id="storeName">

				</select>
			</p>
			<span class="fonttitle"> 用户名</span>

			<p style="position: relative;">
				<select name="name" class="selecttype" id="name">

				</select>
			</p>
			<span class="fonttitles"> 密&nbsp;&nbsp;&nbsp;码</span>

			<p style="position: relative;">
				<input class="ipt" type="password" id="password" name="password" />
			</p>
			<br /> <br />
			
			<button type="button" onclick="login();" class="buttonmenue"
				id="lognbutton">
				<span class="menuetitle">登录</span>
			</button>

		</div>
	</div>

</body>
</html>

<script type="text/javascript">
	$(document).ready(function() {
						$.ajax({    async : true,
									type : 'POST',
									url : "getAllDicDrugStore",
									data : {},
									dataType : 'JSON',
									success : function(data) {
										if (data && data.code == 200) {

											for (i = 0; i < data.data.length; i++) {
												var tname = data.data[i].storeName;
												var tid = data.data[i].id;
												$("#storeName").append(
														"<option value='"+tname+"'>"+ tname+ "</option>");
											}
											$.ajax({
														async : true,
														type : 'POST',
														url : "getDicEmployeeBySotreName",
														data : {
															storeName : $('#storeName').val()
														},
														dataType : 'JSON',
														success : function(data) {
															if (data&& data.code == 200) {
																$("#name").html("");//清空下拉框  
																for (i = 0; i < data.data.length; i++) {
																	var tname = data.data[i];
																	var tid = data.data[i];
																	$("#name").append("<option value='"+tname+"'>"+ tname+ "</option>");
																}

															} else {
																jQuery.messager.alert('提示:',data.msg,'info'); 
																/* alert(data.msg); */
															}
														}
													})
										} else {
											jQuery.messager.alert('提示:',data.msg,'info'); 
											/* alert(data.msg); */
										}

									}
								});
						$("#storeName").change(
										function() {
											$.ajax({
														async : true,
														type : 'POST',
														url : "getDicEmployeeBySotreName",
														data : {
															storeName : $('#storeName').val()
														},
														dataType : 'JSON',
														success : function(data) {
															if (data&& data.code == 200) {
																$("#name").html("");//清空下拉框  
																for (i = 0; i < data.data.length; i++) {
																	var tname = data.data[i];
																	var tid = data.data[i];
																	$("#name").append("<option value='"+tname+"'>"+ tname+ "</option>");
																}

															} else {
																jQuery.messager.alert('提示:',data.msg,'info'); 
															}
														}
													})
										});

						$('#lognbutton').mousedown(function() {
											$(this).css('background','url(/staticPublic/img/buttonlogs.png) no-repeat');
											//alert('mousedown function is running !');
										}).mouseup(
										function() {
											$(this).css('background','url(/staticPublic/img/buttonlog.png) no-repeat');
										});
						$("#password").keydown(function(event) {
							if (event.which == "13")
								login();
						});
					});

	function login() {

		$.ajax({
			type : 'POST',
			url : "validate",
			data : JSON.stringify({
				name : $('#name').val(),
				storeName : $('#storeName').val(),
				password : $('#password').val()
			}),
			contentType : "application/json;charset=UTF-8",
			dataType : 'JSON',
			success : function(data) {
				if (data && data.code == 200) {
					location.href = '/index';
				} else {
					jQuery.messager.alert('提示:',data.msg,'info'); 
					/* alert(data.msg); */
				}
			}
		})
	}
</script>