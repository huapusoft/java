

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
		<link rel="stylesheet" type="text/css" href="/staticPublic/themes/default/easyui.css"></link>
		<link rel="stylesheet" type="text/css" href="/staticPublic/themes/icon.css"></link>
		<script type="text/javascript" src="/staticPublic/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="/staticPublic/js/jquery.easyui.min.js"></script>
		<script type="text/javascript">
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

		$(function(){
			$('#mytable').datagrid().datagrid('enableCellEditing');
		})
		</script>
	</head>
	<body>
	<div style="margin:20px 0;">
	</div>
	<font style="font-size: 12px;font-family: Microsoft YaHei;">供应商：</font><input class="easyui-searchbox" data-options="prompt:'Please Input Value'" style="width:200px"></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<font style="font-size: 12px;font-family: Microsoft YaHei;">进价总金额：</font><input class="easyui-numberbox" precision="2" style="width:200px;height:22px"></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<font style="font-size: 12px;font-family: Microsoft YaHei;">零售价总金额：</font><input class="easyui-numberbox" precision="2" style="width:200px;;height:22px"></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:80px">打开</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">保存</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:80px">打印</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="width:80px">提交</a>
	<div id="tb" style="padding:10px 0px;">
	<table class="easyui-datagrid" style="width:100%;height:600px;" id="mytable"
			data-options="singleSelect:true,collapsible:true,url:'datagrid_data1.json',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'xh',width:80,align:'center'">序号</th>
				<th data-options="field:'ff11',width:200,align:'center',editor:'text',editor:'text'">发票号</th>
				<th data-options="field:'mm22',width:200,align:'center',editor:'text'" >名称</th>
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
				<td>zzz</td>
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
</html>


