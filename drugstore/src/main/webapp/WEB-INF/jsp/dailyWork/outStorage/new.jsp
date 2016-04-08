<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<script type="text/javascript" src="/staticPublic/js/jquery.min.js"></script>
		<script type="text/javascript" src="/staticPublic/js/jquery.easyui.min.js"></script>
		<link rel="stylesheet" type="text/css" href="/staticPublic/themes/material/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="/staticPublic/themes/icon.css"/>
<title>出库页面</title>
<style type="text/css">
.ipt {
	border: 1px solid #d3d3d3;
	padding: 10px 10px;
	width: 300px;
	
 }
</style>
</head>
<body>
<div class="box1">
<table>
<tr>
<td>领药部门</td>
<td><select class="easyui-combotree"  name="departmentId" id="departmentId" style="width:300px; height:38px;"/></td>
<td>进价金额</td>
<td><input type="text" name="" id="" class="ipt" > </td>
<td>零售价总金额</td>
<td> <input type="text" name="" id="" class="ipt" ></td>
<td> <button type="button" name="">保存</button> <button type="button" name="">打印</button> <button type="button" name="">提交</button></td>

</tr>
</table>

</div>
<div class="maintable"></div>
</body>
<script type="text/javascript">
$(document).ready(function() {
	$.ajax({
		type : 'POST',
		url : "/outStorage/getAllDicDepartmentList",
		data : {
			
		},
		dataType : 'JSON',
		success : function(data) {
			if (data && data.code == 200) {
				
				var result = convert (data.data);
				/* alert(result); */
				$('#departmentId').combobox("loadData", result);
			} else {
				jQuery.messager.alert('提示:',data.msg,'info'); 
				/* alert(data.msg); */
			}
		}
	})
	
}); 
function convert(source){
	alert(source.length-1);
    var tmp={},parent,n ,s=source.length-1;
    for(n in source){
        var item=source[n];
       /*  alert(parent); */
        if(item.parentId==0){
            parent=item.departmentId;
        }
        if(!tmp[item.departmentId]){
            tmp[item.departmentId]={};
        }
        tmp[item.departmentId].text=item.departmentName;
        tmp[item.departmentId].id=item.departmentId;
        if(!("children" in tmp[item.departmentId]))tmp[item.departmentId].children=[];
         
        if(item.departmentId!=0){   
            if(tmp[item.parentId]){
                tmp[item.parentId].children.push(tmp[item.departmentId]);
            }
            else{
                tmp[item.parentId]={children:[tmp[item.departmentId]]};
            }
        }
         alert(tmp[item.parentId].id); 
    }
    return tmp[parent];
}
</script>
</html>