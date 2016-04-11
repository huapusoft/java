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
	width: 200px;
	font-size: 16px;
 }
 
</style>
</head>
<body>
<div class="box1">
<table>
<tr>
<td>领药部门</td>
<td><input class="easyui-combotree"  name="departmentId" id="departmentId" style="width:200px; height:38px;panelWidth:200px;">   </td>
<td>进价金额</td>
<td><input type="text" name="" id="" class="ipt" > </td>
<td>零售价总金额</td>
<td> <input type="text" name="" id="" class="ipt" ></td>
<td> <!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:80px">打开</a> -->
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px; height:38px;">保存</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:80px; height:38px;">打印</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="width:80px; height:38px;">提交</a></td>

</tr>
</table>

</div>
<div class="maintable">
	<table class="easyui-datagrid" style="width:100%;height:600px;" id="mytable"
			data-options="singleSelect:true,collapsible:true,url:'datagrid_data1.json',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'number',width:80,align:'center'">序号</th>
				<th data-options="field:'name',width:200,align:'center',editor:'text'" >药品名称</th>
				<th data-options="field:'listprice',width:150,align:'center'">规格</th>
				<th data-options="field:'productid',width:200,align:'center'">厂家</th>
				<th data-options="field:'attr1',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">数量</th>
				<th data-options="field:'unitcost',width:80,align:'center'">单位</th>
				<th data-options="field:'status',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">进价</th>
				<th data-options="field:'jj55',width:100,align:'center'">进价金额</th>
				<th data-options="field:'ll66',width:100,align:'center'">零售价</th>
				<th data-options="field:'ll77',width:100,align:'center'">零售价金额</th>
				<th data-options="field:'pp88',width:100,align:'center'">批号</th>
				<th data-options="field:'total',width:100,align:'center'">有效期</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>aa</td>
				<td></td>
				<td>xxx</td>
				<td>cccc</td>
				<td class="iq_pr" id="iqty"></td>
				<td>eeee</td>
				<td class="iq_pr" id="price"></td>
				<td id="total"></td>
				<td>kkk</td>
				<td>lll</td>
				<td>mmm</td>
				<td>nnn</td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
		</tbody>
	</table>

</div>
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
				var result = fn(data.data,0);
				$('#departmentId').combotree('loadData',result); 
			} else {
				jQuery.messager.alert('提示:',data.msg,'info'); 
			}
		}
	});
	$('#mytable').datagrid().datagrid('enableCellEditing');
	
}); 
  
function fn(data, pid) {
    var result = [], temp;
    for (var i = 0; i < data.length; i++) {
        if (data[i].parentId == pid) {
        	var src=data[i].departmentName.replace(/[ ]/g,"");
            var obj = {"text": data[i].departmentName.replace(/[ ]/g,""),"id": data[i].departmentId};            
            temp = fn(data, data[i].departmentId);
            if (temp.length > 0) {
                obj.children = temp;
            }
            result.push(obj);
        }
    }
    return result;
}

$.extend($.fn.datagrid.methods, {
	editCell: function(jq,param){
		return jq.each(function(){
			var opts = $(this).datagrid('options');
			var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);//每列的field 
				col.editor1 = col.editor;//每列的editor
				if (fields[i] != param.field){
					col.editor = null;
				}
			}
			$(this).datagrid('beginEdit', param.index);
            var ed = $(this).datagrid('getEditor', param);
            if (ed){
                if ($(ed.target).hasClass('textbox-f')){
                    $(ed.target).textbox('textbox').focus();//获得焦点
                } else {
                   $(ed.target).focus();
                }
            }
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor = col.editor1;
			}
		});
	},
    enableCellEditing: function(jq){
        return jq.each(function(){
            var dg = $(this);
            var opts = dg.datagrid('options');
            opts.oldOnClickCell = opts.onClickCell;
            opts.onClickCell = function(index, field){
                if (opts.editIndex != undefined){//editIndex为编辑的索引值,这里仅为引用
                    if (dg.datagrid('validateRow', opts.editIndex)){
                        dg.datagrid('endEdit', opts.editIndex);
                        opts.editIndex = undefined;
                    } else {
                        return;
                    }
                }
                dg.datagrid('selectRow', index).datagrid('editCell', {
                    index: index,
                    field: field
                });
                opts.editIndex = index;
                opts.oldOnClickCell.call(this, index, field);
            }
        });
    }
});


</script>
</html>