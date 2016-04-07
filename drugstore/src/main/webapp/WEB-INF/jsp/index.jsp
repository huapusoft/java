

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
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
		<script type="text/javascript" src="/staticPublic/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="/staticPublic/js/jquery.easyui.min.js"></script>
		<link rel="stylesheet" type="text/css" href="/staticPublic/themes/material/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="/staticPublic/themes/icon.css"/>
		<style type="text/css">
		 #maincenter {
	
	width: 98%;
	height: 98%;	
    marging:0px,auto;
   
}
#hbox {
	background-color: #32bfbb;
}
.bs_banner_logo {
	background-image: url(img/logo.png);
	background-repeat: no-repeat;
	width: 180px; /*logo宽度*/
	height: 74px; /*logo高度*/
	position: absolute;
	margin-top: 25px; /*logo y坐标*/
	margin-left: 40px; /*logo x坐标*/
}

.bs_banner_title {
	background-image: url(img/title.png);
	background-repeat: no-repeat;
	width: 400px; /*头部标题宽度*/
	height: 40px; /*头部标题高度*/
	position: absolute;
	margin-top: 45px; /*头部标题y坐标*/
	margin-left: 130px; /*头部标题x坐标*/
	color: #FFFFFF;
	text-align: left;
	font-family: "黑体", "Tahoma", "宋体", "Arial", "Verdana";
	font-size: 20pt;
	font-weight: bold;
	line-height: 30px;
	vertical-align: bottom;
}

.htabFunction {
	position: absolute;
	color: white;
	right: 35px;
	top: 60px
}

.bs_navleft {
	float: left;
	padding: 0 0 0 15px;
	color: white;
}

.bs_navleft span {
	float: left;
	padding-right: 4px;
}

.bs_navleft li {
	float: left;
	padding-right: 4px;
}

.fontTitle {
	padding: 15px 0 0 0;
}

.clear {
	clear: both;
	line-height: 1px;
}

.icon_message {
	background-image: url(img/message.png);
	background-repeat: no-repeat;
	background-position: 0 40%;
	padding: 0 5px 0 18px;
	display: inline-block;
	white-space: nowrap;
	word-break: keep-all;
	word-wrap: normal;
	width: 50px; /*头部标题宽度*/
	height: 48px; /*头部标题高度*/
}

.icon_exit {
	background-image: url(img/exit.png);
	background-repeat: no-repeat;
	background-position: 0 40%;
	padding: 0 5px 0 18px;
	display: inline-block;
	white-space: nowrap;
	word-break: keep-all;
	word-wrap: normal;
	width: 50px; /*头部标题宽度*/
	height: 48px; /*头部标题高度*/
}

.hand {
	cursor: pointer;
	cursor: hand;
}

.fontlocal {
    font-size: 12px;
	padding: 0 0 52px 27px;
}

#leftwest{
	background-color: #F7F7F7;
}
#left{
    width: 100%;
   
    overflow: hidden;
    background: #F7F7F7;	    
}
.item {
    list-style-type:none;
	width: 100%;
	height: 25px;
	border-top: 1px solid transparent;
	
	line-height: 24px;
	text-indent: 20%;
	font-size: 12px;
	font-family: Microsoft YaHei;
	
}
* {
	margin: 0;
	padding: 0;
}
		
		</style>
	</head>
	<body>
	<%-- <h2>Hello，${user.name}</h2>
		<br></br>
		<button onclick="logout()">退出</button> --%>
	<div style="margin-top:0px;margin-left:15px;margin-right:15px;margin-bottom:0px;">  
    <div class="easyui-layout" id="maincenter" fit="true">
     <div data-options="region:'north'" style="height:12%;" id="hbox" >
     <div class="bs_banner_logo">
				</div>
				<div class="bs_banner_title"></div>
				<div class="htabFunction">
					<div class="bs_navleft">
						<span class="fontTitle">欢迎您，张三 </span>
					</div>
					<div class="bs_navleft">


						<span class="icon_message hand fontTitle" id="home"> <span
							class="fontlocal">1</span> </span>
						<span class="icon_exit hand fontTitle" id="exit"></span>
						<div class="clear"></div>
					</div>
					<div class="clear"></div>
				</div>
     </div>
     <div data-options="region:'south',split:true" style="height:3%;"></div>
     <div data-options="region:'west',split:true"  style="width:13%;" id="leftwest" >
     <div class="easyui-accordion"  border="false" id="left" >
     <div title="日常操作 " style="overflow: auto; padding: 0px; " id="leftmenuetip"  data-options="collapsible:true,iconCls:'icon-firstmenues'"  >	
		<ul class="menu-two">
		<li class="item hand"> <a onclick="javascript:addTab('药品入库','test1.jsp')">药品入库</a></li>
		
		<li class="item hand"><a onclick="javascript:addTab('药品出库','test2.jsp')">药品出库</a></li>
		
		<li class="item hand"><a onclick="javascript:addTab('药品退货','test13.jsp')">药品退货</a></li>
		
		<li class="item hand"><a onclick="javascript:addTab('药品报损','test13.jsp')">药品报损</a></li>
		
		<li class="item hand"><a onclick="javascript:addTab('药品调价','test13.jsp')">药品调价</a></li>
		
		<li class="item hand"><a onclick="javascript:addTab('药品盘点','test13.jsp')">药品盘点</a></li>
		
		<li class="item hand"><a onclick="javascript:addTab('采购计划','test13.jsp')">采购计划</a></li>
		</ul>	
		</div>
			
		<div title="查询统计 " style=" padding: 0px;" data-options="iconCls:'icon-nextmenues'"  >	
		<ul class="menu-two">
		<li class="item hand"> <a onclick="javascript:addTab('入库查询','test1.jsp')">入库查询</a></li>
		
		<li class="item hand"><a onclick="javascript:addTab('出库查询','test2.jsp')">出库查询</a></li>
		
		<li class="item hand"><a onclick="javascript:addTab('退货查询','test13.jsp')">退货查询</a></li>
		
		<li class="item hand"><a onclick="javascript:addTab('报损查询','test13.jsp')">报损查询</a></li>
		
		<li class="item hand"><a onclick="javascript:addTab('调价查询','test13.jsp')">调价查询</a></li>
		
		<li class="item hand"><a onclick="javascript:addTab('盘点查询','test13.jsp')">盘点查询</a></li>
		
		<li class="item hand"><a onclick="javascript:addTab('采购计划','test13.jsp')">采购计划</a></li>
		
		<li class="item hand"><a onclick="javascript:addTab('进出统计','test13.jsp')">进出统计</a></li>
		
		<li class="item hand"><a onclick="javascript:addTab('药品台账','test13.jsp')">药品台账</a></li>
		
		<li class="item hand"><a onclick="javascript:addTab('库存查询','test13.jsp')">库存查询</a></li>
		</ul>	
		</div>
		<div title="设置" style="padding: 0px;" data-options="iconCls:'icon-lastmenues'">	
		<ul class="menu-two">
		<li class="item hand"> <a onclick="javascript:addTab('药品设置','test1.jsp')">药品设置</a></li>
		
		<li class="item hand"><a onclick="javascript:addTab('供应商设置','test2.jsp')">供应商设置</a></li>
		
		<li class="item hand"><a onclick="javascript:addTab('库存分类','test13.jsp')">库存分类</a></li>
		
		<li class="item hand"><a onclick="javascript:addTab('库存药品设置','test13.jsp')">库存药品设置</a></li>
		
		<li class="item hand"><a onclick="javascript:addTab('打印设置','test13.jsp')">打印设置</a></li>
		
		<li class="item hand"><a onclick="javascript:addTab('领药部门设置','test13.jsp')">领药部门设置</a></li>
		
		<li class="item hand"><a onclick="javascript:addTab('密码修改','test13.jsp')">密码修改</a></li>
		</ul>	
		</div>
     </div>
     </div>
     <div data-options="region:'center'" style="width:83%;">
         <div class="easyui-tabs" id="centerTab" fit="true" border="false">
					<div title="欢迎页" style="padding: 20px; overflow: hidden;">
						<div style="margin-top: 20px;">
							<h3>
								你好，欢迎来到权限管理系统
							</h3>
						</div>
					</div>
				</div>
         </div>
    </div>
    </div>
		
	</body>
</html>
<script>
$(document).ready(function() {
    
    $(".menu-two li").hover(function() {
			$(this).css('background-color', '#DDDDDD');
		}, function() {
			$(this).css('background', '');
		});
		$(".menu-two li").click(function() {						
			  $(this).addClass("navdown").siblings().removeClass("navdown");
          });
    });
      function addTab(text, url) {
        if ($("#centerTab").tabs('exists', text)) {
            $('#centerTab').tabs('select', text);
        } else {
            $('#centerTab').tabs('add', {
                title : text,
                closable : true,
                content :  '<iframe src="'   
                 + url  
               + '" frameborder="0" style="border:0;width:98%;height:99.4%;overflow:hidden;"></iframe>'  
            });
        }
    }
function logout(){
	location.href="logout";
}
</script>