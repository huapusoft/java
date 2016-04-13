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
 .selectItemcont {
	padding: 0px;
}
#selectItem {
	background: #FFF;
	position: absolute;
	top: 0px;
	left: center;
	border: 1px solid #D3D3D3;
	overflow: hidden;
	width: 500px;
	height:290px;
	z-index: 1000;
	overflow:auto;
}
.selectItemhidden {
	display: none;
}	
.divtab{
	border: 1px solid LightGray;
	border-collapse: collapse;
	table-layout: fixed;/*td内容过多 省略号的前提*/
}
.divtab th {
            border: 1px solid LightGray;
            font-weight:normal;
            font-size: 12px;font-family: Microsoft HeiTi;
}
.divtab tbody tr td {
	border: 1px solid LightGray;
	font-size: 12px;font-family: Microsoft HeiTi;
	white-space:nowrap;text-overflow: ellipsis; overflow:hidden;/*td内容过多 省略号*/
}
.on{
	background-color: #3299ff;
}	
</style>
</head>
<body onkeydown="keyCheck()">
<div class="box1">
<table>
<tr>
<td>领药部门</td>
<td><input class="easyui-combotree"  name="departmentId" id="departmentId" style="width:200px; height:38px;panelWidth:200px;">   </td>
<td>进价金额</td>
<td><input type="text" name="sum1" id="sum1" class="ipt" > </td>
<td>零售价总金额</td>
<td> <input type="text" name="sum2" id="sum2" class="ipt" ></td>
<td> <!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:80px">打开</a> -->
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px; height:38px;" onclick="dosave();">保存</a>
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
				<th data-options="field:'orderNo',width:80,align:'center'">序号</th>
				<th data-options="field:'itemName',width:200,align:'center',editor:'text'" >药品名称</th>
				<th data-options="field:'spec',width:150,align:'center'">规格</th>
				<th data-options="field:'vendor',width:200,align:'center'">厂家</th>
				<th data-options="field:'amount',width:100,align:'center',editor:{type:'numberbox',options:{precision:2}}">数量</th>
				<th data-options="field:'unit',width:80,align:'center'">单位</th>
				<th data-options="field:'inPrice',width:100,align:'center'">进价</th>
				<th data-options="field:'jj55',width:100,align:'center'">进价金额</th>
				<th data-options="field:'price',width:100,align:'center'">零售价</th>
				<th data-options="field:'ll77',width:100,align:'center'">零售价金额</th>
				<th data-options="field:'batchNo',width:100,align:'center'">批号</th>
				<th data-options="field:'validDate',width:100,align:'center'">有效期</th>
				<th data-options="field:'id',width:100,align:'center'">id</th>
				<th data-options="field:'invoiceNo',width:100,align:'center'">发票号</th>
			</tr>
		</thead>
		<tbody id="tbody">
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td ></td>
				<td></td>
				<td ></td>
				<td ></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table>
	</div>
	<div id="selectItem" class="selectItemhidden" >
		<div id="selectItemCount" class="selectItemcont">
			<div id="selectSub">
				<table width="100%" height="100%" id="table1" class="divtab">
					<thead>
						<tr style="width: 100%;">
							<th width="63%" height="22px;">名称</th>
							<th width="20%" height="22px;">规格</th>
							<th width="7%" height="22px;">单位</th>
							<th width="10%" height="22px;">批号</th>
							<th width="8%" height="22px;" style="display:none">厂家</th>
							<th width="8%" height="22px;" style="display:none">进价</th>
							<th width="8%" height="22px;" style="display:none">零售价</th>
							<th width="8%" height="22px;" style="display:none">有效期</th>
						</tr>
					</thead>
					<tbody id="table1_tbody">
						
					</tbody>
				</table>
			</div>
		</div>
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
	$('#mytable').datagrid('hideColumn','id');
	$('#mytable').datagrid('hideColumn','invoiceNo');
	$('#mytable').datagrid().datagrid('enableCellEditing');
	/* var ed = $('#mytable').datagrid('getEditor', {index:0,field:'attr1'});
    $(ed.target).datebox('setValue', '12'); */
});

function jsonDateFormat(jsonDate) {//json日期格式转换为正常格式
	
    try {
       
        var dateObj = JSON.parse(jsonDate);
        var date = new Date(dateObj);
       /*  alert(date.getFullYear()); */
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
       /*  var hours = date.getHours();
        var minutes = date.getMinutes();
        var seconds = date.getSeconds();
        var milliseconds = date.getMilliseconds(); */
        return date.getFullYear() + "-" + month + "-" + day; /* + " " + hours + ":" + minutes + ":" + seconds + "." + milliseconds */
    } catch (ex) {//出自http://www.cnblogs.com/ahjesus 尊重作者辛苦劳动成果,转载请注明出处,谢谢!
        return "";
    }
}
/**将json格式转化成json的树形结构 **/  
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

var rowNo= 0;
var clickIndex = [];//单击行的索引数组
var selectedData = [];//选中的数据集合
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
					//alert(param.field);
					//alert(fields[i]);
				}
			}
			//alert(param);
			$(this).datagrid('beginEdit', param.index);
            var ed = $(this).datagrid('getEditor', param);           
            if (ed){
            	
                if ($(ed.target).hasClass('textbox-f')){
                    $(ed.target).textbox('textbox').focus();//获得焦点
                    
                } else {
                   $(ed.target).focus();
                   if(param.field == "itemName")//列名等于名称
                   {
                	   $(ed.target).bind('keyup', function (event) {
                		   if(event.keyCode == 38 || event.keyCode == 40)
               				{
               					$(this).blur();
               					event.stopPropagation();//通过使用 stopPropagation() 方法只阻止一个事件起泡。 
               					return false;//通过返回false来取消默认的行为并阻止事件起泡
               				}
                    	   //alert("keyup");
                    	   var A_top = $(this).offset().top + $(this).outerHeight(true); //  1
                   		   var A_left = $(this).offset().left;
                    	   var val = $(this).val().trim();
                    	   //alert(val);
                    	   $("#table1  tr:not(:first)").remove();
                    	   rowNo= 0;
                    	   if(val != "" && val != null)
                    	   {
                    		   var param = "itemName=" + val;
                    		   $.ajax({
                    			    type : "post",
               						url : "/outStorage/getDrugListFromStore",
               						data : param,
               						dataType : "json",
               						success: function(data){
               							if(data.data != null && data.data.length != 0)
               							{
               								//alert(data.data.length);
               								for(var i = 0;i< data.data.length;i++)
               	 							{
               								   
               								    var validDate= jsonDateFormat(data.data[i].validDate);
               								/*  alert(validDate); */
               									//alert(data[i].itemName);
               	 								var _tr = $("<tr><td class='nametd' width='300px' height='22px' name ='matname' title='"+data.data[i].itemName+"'><input style='padding-right: 10px;' type='checkbox' name='cr01' id='cr01' value='"+data.data[i].itemName+"'/>"+data.data[i].itemName
               	 									+"</td><td width='70px' height='22px' name='spec' title='"+data.data[i].spec+"'>"+data.data[i].spec
               	 								    +"</td><td width='30px' height='22px' name='unitname' title='"+data.data[i].unit+"'>"+data.data[i].unit
               	 								    +"</td><td width='40px' height='22px' name='batchno' title='"+data.data[i].batchNo+"'>"+data.data[i].batchNo
               	 							        +"</td><td width='30px' height='22px' name='vendor' title='"+data.data[i].vendor+"' style=\"display:none\">"+data.data[i].vendor
               	 						            +"</td><td width='30px' height='22px' name='price' title='"+data.data[i].price+"'  style=\"display:none\">"+data.data[i].price
               	 					                +"</td><td width='30px' height='22px' name='inPrice' title='"+data.data[i].inPrice+"'  style=\"display:none\">"+data.data[i].inPrice
               	 									+"</td><td width='30px' height='22px' name='validDate' title='"+validDate+"'  style=\"display:none\">"+validDate+"</td></tr>");
               	 									$("#table1").append(_tr); 
               	   							}
               								$("#selectItem").show().css({
                    						"position" : "absolute",
                    						"top" : A_top + "px",
                    						"left" : A_left + "px"
                    						});
               								//单击行
               								$("#table1_tbody tr").click(function() {
               									//alert($(this).index());
               									rowNo = $(this).index()+1;//当前单击行的索引+1
               									clickIndex.push($(this).index()+1);
               									$(this).children().children().attr("checked", true);//选中checkbox
               									$(this).addClass("on").siblings("tr").removeClass("on");
               									selectedData.splice(0,selectedData.length);//每次点击前先清空
               	         							$(":checkbox:checked").each(function()
               	         							{
               	  									var tablerow = $(this).parent("td").parent("tr");
               	  									var matname = tablerow.find("[name='matname']").text();
               	  									var spec= tablerow.find("[name='spec']").text();
               	  									var unitname= tablerow.find("[name='unitname']").text();
               	  								    var batchno= tablerow.find("[name='batchno']").text();
               	  								    var vendor= tablerow.find("[name='vendor']").text();
               	  							        var price= tablerow.find("[name='price']").text();
               	  						            var inPrice= tablerow.find("[name='inPrice']").text();
               	  					                var validDate= tablerow.find("[name='validDate']").text();
               	  					               
               	  									selectedData.push({Name:matname,Spec:spec,UnitName:unitname,BatchNo:batchno,Vendor:vendor,Price:price,InPrice:inPrice,ValidDate:validDate});
               										});
               								});
               								//单击checkbox
               								$("#selectItem").find("#table1 :checkbox").click(function(e){
               									var cv = $(this).parent().parent().attr("class");
               									if(cv == "on")
               									{
               										$(this).parent().parent().removeClass("on");
               									}
               									selectedData.splice(0,selectedData.length);//每次点击前先清空
               	         							$(":checkbox:checked").each(function()
               	         							{
               	  									var tablerow = $(this).parent("td").parent("tr");
               	  									var matname = tablerow.find("[name='matname']").text();
               	  									var spec= tablerow.find("[name='spec']").text();
               	  									var unitname= tablerow.find("[name='unitname']").text();
               	  								    var batchno= tablerow.find("[name='batchno']").text();
            	  								    var vendor= tablerow.find("[name='vendor']").text();
            	  							        var price= tablerow.find("[name='price']").text();
            	  						            var inPrice= tablerow.find("[name='inPrice']").text();
            	  					                var validDate= tablerow.find("[name='validDate']").text();
               	  									selectedData.push({Name:matname,Spec:spec,UnitName:unitname,BatchNo:batchno,Vendor:vendor,Price:price,InPrice:inPrice,ValidDate:validDate});
               										});
               									e.stopPropagation();//阻止tr冒泡
               								});
               								//双击行  防止执行多次
               								$("#table1 tr").unbind("dblclick").dblclick(function() {
               									var rowindex = "";
               									var inumber = "";//有数据的行数
               									//var tb = document.getElementById("tbody");
               									var rows = $("#mytable").datagrid('getRows');//tb.rows;
               									//alert(rows);
               									for (var i = 0; i < rows.length; i++) {
               							            var textval = rows[i]['itemName'];
               							            //alert(textval);
               							            if(textval.trim() != "" && textval.trim() != null)//判断当前页面的物品这列是否有数据
             								 	 	{
             								 			//alert("i:"+i);
             								 			rowindex = i+1;
             								 		}
               							        }
               									//alert(selectedData.length);
               									for(var i = 0;i<selectedData.length;i++)
               	    							{
               	    								if(i+rowindex > rows.length-1)
               	    								{
               	    									$('#mytable').datagrid('appendRow',{
               	    										orderNo:'',
               	    										itemName: '',
               	    										spec: '',
               	    										unit: '',
               	    										vendor: '',
               	    										batchno:'',
               	    										price: '',
               	    										inPrice: '',
               	    										validDate: ''
               	    									});
               	    								}
               	    								$('#mytable').datagrid('updateRow',{
               	    									index: i+rowindex,
               	    									row: {
               	    										orderNo:1+i+rowindex,
               	    										itemName: selectedData[i]['Name'],
               	    										spec: selectedData[i]['Spec'],
               	    										unit: selectedData[i]['UnitName'],
               	    										vendor: selectedData[i]['Vendor'],
               	    										batchno: selectedData[i]['BatchNo'],
               	    										price: selectedData[i]['Price'],
               	    										inPrice: selectedData[i]['InPrice'],
               	    										validDate: selectedData[i]['ValidDate']
               	    									}
               	    								});
               	 									inumber = i+1+rowindex;
               	    							}
               	    							if(inumber == rows.length)//有数据的行和添加行后的页面的行相等就在添加一行
               	    							{
               	    								$('#mytable').datagrid('appendRow',{
               	    									orderNo:'',
           	    										itemName: '',
           	    										spec: '',
           	    										unit: '',
           	    										vendor: '',
           	    										batchno:'',
           	    										price: '',
           	    										inPrice: '',
           	    										validDate: ''
           	    									});
               	 											
               	    							}
               									$("#selectItem").hide();
               									
               								});
               							}
               						}
                    		   });
                    	   }
                    	   else
                    	   {
                    		   $("#selectItem").hide();
                    	   }
                       });
                	   
                	   
                   }
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
            	//alert(opts.editIndex);
                if (opts.editIndex != undefined){//editIndex为编辑的索引值,这里仅为引用
                    if (dg.datagrid('validateRow', opts.editIndex)){
                        dg.datagrid('endEdit', opts.editIndex);
                        opts.editIndex = undefined;
                        //alert("aa");
                    } else {
                        return;
                    }
                }
                //alert("index:"+index+",field:"+field);
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

function keyCheck(){
	var d=document.getElementById("selectItem");
	var sumLength = document.getElementById("table1").rows.length;
    //事件的标识代码   向上
	if (window.event.keyCode == 38)
    {
		//alert("上");
		var tr_top = document.getElementById("table1").rows(rowNo).offsetTop;
    	var tr_height = document.getElementById("table1").rows(rowNo).offsetHeight;//行高
        for(var k=0;k<sumLength;k++)
        {
            document.getElementById("table1").rows(k).className = "";
        }
        if(rowNo != 0)
        {
        	if(rowNo == 1)
    		{
    			//d.scrollTop =0;
    			$("#selectItem").animate({scrollTop:0},1);
    			document.getElementById("table1").rows(1).className = "on";
    			return false;
    		}
    		else
    		{
   			$("#selectItem").animate({scrollTop:tr_top-tr_height},1);
    			document.getElementById("table1").rows(--rowNo).className = "on";
    		}
        }
    }
	//事件的标识代码   向下
    if (window.event.keyCode== 40)
    {
    	//alert("下");
    	var tr_top = document.getElementById("table1").rows(rowNo).offsetTop;
    	var tr_height = document.getElementById("table1").rows(rowNo).offsetHeight;//行高
    	for(var k=0;k<sumLength;k++)
        {
            document.getElementById("table1").rows(k).className="";
        }
    	if(rowNo == sumLength-1)
    	{
    		document.getElementById("table1").rows(sumLength-1).className = "on";
    		return false;
    	}
    	else
    	{    		
    		$("#selectItem").animate({scrollTop:tr_top-290+2*tr_height},1);
    		document.getElementById("table1").rows(++rowNo).className = "on";
    	}
    }
  	//enter事件
	if(window.event.keyCode== 13)
	{
		//alert("enter");
		var flag = false;
		var rowindex = 0;
		var inumber = "";//有数据的行数
		//var tb = document.getElementById("tbody");
		var rows = $("#mytable").datagrid('getRows');//tb.rows;
		//alert(rows);
		for (var i = 0; i < rows.length; i++) 
		{
	       var textval = rows[i]['itemName'];
	       //alert(textval);
	       if(textval.trim() != "" && textval.trim() != null)//判断当前页面的物品这列是否有数据
		 	{
		 		//alert("i:"+i);
		 		rowindex = i+1;
		 	}
	    }
		for(var j = 0;j<clickIndex.length;j++)
		{
			if(rowNo == clickIndex[j])
			{
				flag = true;
			}
		}
		if(flag == false)
		{
			//alert(flag);
			var matname = $("#table1 tr:eq("+rowNo+")").find("[name='matname']").text().trim();
			var spec = $("#table1 tr:eq("+rowNo+")").find("[name='spec']").text().trim();
			var unitname = $("#table1 tr:eq("+rowNo+")").find("[name='unitname']").text().trim();
			var batchno=$("#table1 tr:eq("+rowNo+")").find("[name='batchno']").text().trim(); 
			    var vendor=$("#table1 tr:eq("+rowNo+")").find("[name='vendor']").text().trim(); 
		        var price= $("#table1 tr:eq("+rowNo+")").find("[name='price']").text().trim(); 
	            var inPrice= $("#table1 tr:eq("+rowNo+")").find("[name='inPrice']").text().trim(); 
              var validDate= $("#table1 tr:eq("+rowNo+")").find("[name='validDate']").text().trim(); 
			if(matname != null && matname != "")
			{
				selectedData.push({Name:matname,Spec:spec,UnitName:unitname,BatchNo:batchno,Vendor:vendor,Price:price,InPrice:inPrice,ValidDate:validDate});
			}
		}
		if(selectedData.length > 0)
		{
			for(var i = 0;i<selectedData.length;i++)
	    	{
	    		if(i+rowindex > rows.length-1)
	    		{
	    			$('#mytable').datagrid('appendRow',{
	    				orderNo:'',
							itemName: '',
							spec: '',
							unit: '',
							vendor: '',
							batchno:'',
							price: '',
							inPrice: '',
							validDate: ''
						});
	    		}	    	
	 			$('#mytable').datagrid('updateRow',{
						index: i+rowindex,
						row: {
							orderNo:1+i+rowindex,
								itemName: selectedData[i]['Name'],
								spec: selectedData[i]['Spec'],
								unit: selectedData[i]['UnitName'],
								vendor: selectedData[i]['Vendor'],
								batchno: selectedData[i]['BatchNo'],
								price: selectedData[i]['Price'],
								inPrice: selectedData[i]['InPrice'],
								validDate: selectedData[i]['ValidDate']
						}
					});
	 			inumber = i+1+rowindex;
	    	}
			
	    	if(inumber == rows.length)//有数据的行和添加行后的页面的行相等就在添加一行
	    	{
	 			$('#mytable').datagrid('appendRow',{
	 				orderNo:'',
						itemName: '',
						spec: '',
						unit: '',
						vendor: '',
						batchno:'',
						price: '',
						inPrice: '',
						validDate: ''
					});
	    	}
    	}
		
    	selectedData.splice(0,selectedData.length);//清空
    	clickIndex.splice(0,clickIndex.length);
    	$("#selectItem").hide();
	}
	//esc事件
	if(window.event.keyCode== 27)
	{
		rowNo = 0;
		selectedData.splice(0,selectedData.length);//清空
		clickIndex.splice(0,clickIndex.length);
		$("#selectItem").hide();
	}
};

String.prototype.trim=function()
{
	return this.replace(/(^\s*)|(\s*$)/g,'');
}

function dosave(){
	var detailList = {};
	var _list = {};
	var rows = $('#mytable').datagrid('getRows');
	/* var typeData = $('#departmentId').val(); */
	var typeData =$('#departmentId').combotree('getText'); 
	var sum1 = $('#sum1').val();
	var sum2 = $('#sum2').val();
	alert( sum1);
	var json = "";
	json= "{"+
	for (var i = 0; i < rows.length; i++) {
		
	    var row = rows[i];   
	    if(rows[i].orderNo!=""&&rows[i].orderNo!=null&&rows[i].orderNo!="undefined"){
	    	/* alert( rows[i].orderNo); */	
	    	 detailList["list[" + i + "].orderNo"] = rows[i].orderNo; //这里list要和后台的参数名List<Category> list一样
	 	    detailList["list[" + i + "].id"] = rows[i].id; 
	 	   detailList["list[" + i + "].invoiceNo"] = rows[i].invoiceNo; 
	 	  detailList["list[" + i + "].batchNo"] = rows[i].batchNo; 
	 	 detailList["list[" + i + "].amount"] = rows[i].amount; 
	 	detailList["list[" + i + "].price1"] = rows[i].inPrice; 
	 	detailList["list[" + i + "].price2"] = rows[i].price; 
	 	detailList["list[" + i + "].validDate"] = rows[i].validDate; 
	    }
	   
	}
	$.ajax({
		type : 'POST',
		url : "/outStorage/save",
		data : {billType:'出库',typeData:typeData,sum1:sum1,sum2:sum1			
		},
		dataType : 'JSON',
		success : function(data) {			
			if (data && data.code == 200) {				
				alert("asqw");
			} else {
				jQuery.messager.alert('提示:',data.msg,'info'); 
			}
		}
	});
}

</script>
</html>