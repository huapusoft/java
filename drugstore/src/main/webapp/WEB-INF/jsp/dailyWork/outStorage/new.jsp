<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<script type="text/javascript" src="/staticPublic/js/jquery.min.js"></script>
		<script type="text/javascript" src="/staticPublic/js/jquery.easyui.min.js"></script>
		<link rel="stylesheet" type="text/css" href="/staticPublic/themes/default/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="/staticPublic/themes/icon.css"/>
		<script src="/staticPublic/js/easyui-lang-zh_CN.js"></script>
<title>出库页面</title>
<style type="text/css">
.ipt {
	border: 1px solid #d3d3d3;
	padding: 7px 7px;
	width: 115px;
	font-size: 13px;
 }
 .ipts {	
	margin-left: 17px ;	
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
            font-size: 13px;font-family: Microsoft HeiTi;
}
.divtab tbody tr td {
	border: 1px solid LightGray;
	font-size: 13px;font-family: Microsoft HeiTi;
	white-space:nowrap;text-overflow: ellipsis; overflow:hidden;/*td内容过多 省略号*/
}
.on{
	background-color: #3299ff;
}	
.fonttitle{
	font-size: 13px;
	/* font-family: Microsoft YaHei; */
}
</style>
<script type="text/javascript">
var editIndex = undefined;
var fieldname = "";//选中单无格列名
var columnarray = {};//列名集合
var columnMap={};
window.onload = function() {
 columnarray = $('#mytable').datagrid('getColumnFields');
 for(var i=0;i<columnarray.length;i++){
  columnMap[columnarray[i]]=i;
 }
}
function onClickCell(index, field){
	fieldname = field;
	/* $(this).focus(); */
}
/* function clickCell(rowIndex, field, value){//单击单元格事件,在定义datagrid时调用
 fieldname = field;
} */
$(function(){
	$('#mytable').datagrid().datagrid('enableCellEditing');
	
	$('#mytable').datagrid('options').onSelect = function(index, rowData){selectRow = index;}	
	
})
var toolbar = [{
    text:'插行',
    iconCls:'icon-add',
    handler:function(){
    	/* alert(selectRow); */
    	if(selectRow >=0)
    	{
    		$('#mytable').datagrid('insertRow',{
        		index: selectRow,
        		row: {
        			
        			orderNo:'ch',//标准序号不为空
						itemName: '',
						spec: '',
						unit: '',
						vendor: '',
						batchno:'',
						price: '',
						inPrice: '',
						validDate: '',
						drugId:''
        		}
			});	
    	}
    	else
    	{
    		$.messager.alert('提示:',"请先选择一行!",'info');
			 	return false;
    	}
    }
},'-',{
    text:'删行',
    iconCls:'icon-cut',
    handler:function(){
    	/* alert(selectRow); */
    	$('#mytable').datagrid('deleteRow',selectRow);
    }
}];
</script>
</head>
<body onkeydown="keyCheck()">
<input type="hidden" name="billNo" id="billNo">
<div class="box1">
<table >
<tr>
<td class="fonttitle">票据号：</td>
<td><input type="text" class="textbox" name="billNos" id="billNos" data-options="prompt:'请选择领药部门'" style="width:115px;height:25px">   </td>
<td> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:70px" onclick="doopen();">打开</a> 
</tr>
<tr>
<td class="fonttitle">领药部门：</td>
<td><input class="easyui-combotree"  name="departmentId" id="departmentId" data-options="prompt:'请选择领药部门'" style="width:115px; height:25px;">   </td>
<td class="fonttitle">进价金额：</td>
<td ><input id="sum1"  name="sum2" class="easyui-numberbox" precision="2" style="width:120px;height:25px"></td>
<td class="fonttitle">零售价总金额：</td>
<td> <input  name="sum2" id="sum2" class="easyui-numberbox" precision="2" style="width:120px;height:25px" ></td>
<td> <!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:80px">打开</a> -->
    <a href="#" class="easyui-linkbutton ipts" data-options="iconCls:'icon-save'" style="width:70px; height:25px;" onclick="dosave();">保存</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:70px; height:25px;">打印</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="width:70px; height:25px;" onclick="dosubmit();">提交</a></td>

</tr>
</table>

</div>
<div class="maintable">
	<table class="easyui-datagrid" style="width:100%;height:600px;" id="mytable"
			data-options="rownumbers:true,singleSelect:true,collapsible:true,url:'datagrid_data1.json',method:'get',onClickCell: onClickCell,toolbar:toolbar">
		<thead>
			<tr>
				<th data-options="field:'orderNo',width:80,align:'center',hidden:'true'">序号</th>
				<th data-options="field:'itemName',width:200,align:'center',editor:'text'" >药品名称</th>
				<th data-options="field:'spec',width:150,align:'center'">规格</th>
				<th data-options="field:'vendor',width:200,align:'center'">厂家</th>
				<th data-options="field:'amount',width:100,align:'center',editor:{type:'numberbox',options:{precision:2,validType:'notLing[0]'}}">数量</th>
				<th data-options="field:'unit',width:80,align:'center'">单位</th>
				<th data-options="field:'inPrice',width:100,align:'center'">进价</th>
				<th data-options="field:'total1',width:100,align:'center'">进价金额</th>
				<th data-options="field:'price',width:100,align:'center'">零售价</th>
				<th data-options="field:'total2',width:100,align:'center'">零售价金额</th>
				<th data-options="field:'batchno',width:100,align:'center'">批号</th>
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
	$('#mytable').datagrid('options').onSelect = function(index, rowData){selectRow = index;}
	 $("#mytable").datagrid().datagrid("keyCtr"); 
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
        if (data[i].parentCode == pid) {
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

//WITHOUT Plugin
var EventUtil = {

    addHandler: function(element, type, handler){
        if (element.addEventListener){
            element.addEventListener(type, handler, false);
        } else if (element.attachEvent){
            element.attachEvent("on" + type, handler);
        } else {
            element["on" + type] = handler;
        }
    },
	
	removeHandler: function(element, type, handler){
        if (element.removeEventListener){
            element.removeEventListener(type, handler, false);
        } else if (element.detachEvent){
            element.detachEvent("on" + type, handler);
        } else {
            element["on" + type] = null;
        }
    },
	
	getEvent: function(event) {
        return event ? event : window.event;
    },
	
	getTarget: function(event) {
		return event.target || event.srcElement;    
	},
	
	getWheelDelta: function(event) {
        if (event.wheelDelta){
            return event.wheelDelta;
        } else {
            return -event.detail * 40;
        }
    },
	
	preventDefault: function(event) {
        if (event.preventDefault){
            event.preventDefault();
        } else {
            event.returnValue = false;
        }
    }
    
};

function onWheel(event) {

	event = EventUtil.getEvent(event);
	var curElem = EventUtil.getTarget(event);
	var curVal = parseInt(curElem.value);
	var delta = EventUtil.getWheelDelta(event);
	
	if (delta > 0) {
		curElem.value = curVal + 1;
	} else{ 
		curElem.value = curVal - 1;
	}
	
	EventUtil.preventDefault(event);
	
}
/***字符串转换成float类型的数据***/
function isNumber(n) {
	  return !isNaN(parseFloat(n)) && isFinite(n);
	}
var maincurRow = "";//主页面表格行索引
var selectRow = "";//选择行
var saveclick  = 0;
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
                    $(ed.target).textbox('textbox').hover(function(){
                		EventUtil.addHandler(document,'mousewheel',onWheel);
                		EventUtil.addHandler(document,'DOMMouseScroll',onWheel);
                	},
                	function(){
                		EventUtil.removeHandler(document,'mousewheel',onWheel);
                		EventUtil.removeHandler(document,'DOMMouseScroll',onWheel);
                	});
                    $(ed.target).textbox('textbox').bind('blur',function(){
                    	var amount = 0;
                    	var price1 = 0;
                    	var price2 = 0;
                    	var eds = $('#mytable').datagrid('getRows');
                    	if(param.field == "amount")//列名等于名称
                        {
                    		amount = $(this).val().trim();
                    		price1 = eds[param.index]['inPrice'];
                    		price2 = eds[param.index]['price']; 
                    		//console.info(amount*price1);
                    		$('#mytable').datagrid('updateRow',{
								index: param.index,
								row: {
									amount:amount,
									total1: (price1*amount).toFixed(2),
									total2: (price2*amount).toFixed(2)
								}
							});
                    		
                        }
                    	
                    	var sum1 = 0;
               		    var sum2 = 0;
               		 
                  		for (var i = 0; i < eds.length; i++) {			
                  		    var row = eds[i];                           		  
                  		    if(eds[i].itemName!=""&&eds[i].itemName!=null&&eds[i].itemName!="undefined"){
                  		    	 alert("tt"+amount);   
                  		    	/* alert(eds[i].inPrice); */
                  		    	var qty = isNumber(eds[i].amount) ? eds[i].amount : 0;
                  		    	/* alert(qty); */
                  		        var inPrice = eds[i].inPrice;
                  		        /* alert(inPrice); */
                  		        var price = eds[i].price;
                  		        sum1 += qty * inPrice;
                  		        sum2 += qty * price;
                  		        /* alert(sum1); */
                  		     
                  		    }	  
                  		}
                  		 /* alert(sum1); */
                  		 $('#sum1').numberbox('setValue', sum1);
                  		 $('#sum2').numberbox('setValue', sum2);
                    });
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
                    	   //console.info(val);
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
               									//alert(data.data[i].vendor);
               									//alert(data.data[i].id);
               									var validDate= jsonDateFormat(data.data[i].validDate);
               									var _tr = $("<tr><td class='nametd' width='300px' height='22px' name ='matname' title='"+data.data[i].itemName+"'><input style='padding-right: 10px;' type='checkbox' name='cr01' id='cr01' value='"+data.data[i].itemName+"'/>"+data.data[i].itemName
                   	 									+"</td><td width='70px' height='22px' name='spec' title='"+data.data[i].spec+"'>"+data.data[i].spec
                   	 								    +"</td><td width='30px' height='22px' name='unitname' title='"+data.data[i].unit+"'>"+data.data[i].unit
                   	 								    +"</td><td width='40px' height='22px' name='batchno' title='"+data.data[i].batchNo+"'>"+data.data[i].batchNo
                   	 							        +"</td><td width='30px' height='22px' name='vendor' title='"+data.data[i].vendor+"' style=\"display:none\">"+data.data[i].vendor
                   	 						            +"</td><td width='30px' height='22px' name='price' title='"+data.data[i].price+"'  style=\"display:none\">"+data.data[i].price
                   	 					                +"</td><td width='30px' height='22px' name='inPrice' title='"+data.data[i].inPrice+"'  style=\"display:none\">"+data.data[i].inPrice
                   	 					                +"</td><td width='30px' height='22px' name='drugId' title='"+data.data[i].drugId+"'  style=\"display:none\">"+data.data[i].drugId
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
               	  					                var drugId= tablerow.find("[name='drugId']").text();
               	  									selectedData.push({Name:matname,Spec:spec,UnitName:unitname,BatchNo:batchno,Vendor:vendor,Price:price,InPrice:inPrice,ValidDate:validDate,DrugId:drugId});
               										});
               								/*
               	    						alert(selectedData.length);
               	    						for(var i = 0;i<selectedData.length;i++)
               	    						{
               	    							alert(selectedData[i]['Name']);
               	    							alert(selectedData[i]['Spec']);
               	    							alert(selectedData[i]['UnitName']);
               	    						}*/
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
            	  					                var drugId= tablerow.find("[name='drugId']").text();
               	  									selectedData.push({Name:matname,Spec:spec,UnitName:unitname,BatchNo:batchno,Vendor:vendor,Price:price,InPrice:inPrice,ValidDate:validDate,DrugId:drugId});
               										});
               									e.stopPropagation();//阻止tr冒泡
               								});
               								//双击行  防止执行多次
               								$("#table1 tr").unbind("dblclick").dblclick(function() {
               									//alert(maincurRow);
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
               	    							//alert(rows[maincurRow].xh);
               	    							if(rows[maincurRow].orderNo != "" && rows[maincurRow].orderNo != null)//序号不为空 表示要修改
               	    							{
               	    								for(var i = 0;i<selectedData.length;i++)
                       	    						{
                   	    								//alert(rows[maincurRow].xh.trim());
                   	    								$('#mytable').datagrid('updateRow',{
                       	    							index: maincurRow,
                       	    							row: {                       	    								
               	    										itemName: selectedData[i]['Name'],
               	    										spec: selectedData[i]['Spec'],
               	    										unit: selectedData[i]['UnitName'],
               	    										vendor: selectedData[i]['Vendor'],
               	    										batchno: selectedData[i]['BatchNo'],
               	    										price: selectedData[i]['Price'],
               	    										inPrice: selectedData[i]['InPrice'],
               	    										validDate: selectedData[i]['ValidDate'],
               	    										drugId:selectedData[i]['DrugId']
                       	    								}
                       	    							});
                   	    							 }
               	    							}
               	    							else
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
                   	    										validDate: '',
                   	    										drugId:''
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
                   	    										validDate: selectedData[i]['ValidDate'],
                   	    										drugId:selectedData[i]['DrugId']
                       	    								}
                       	    							});
                       	 								inumber = i+1+rowindex;
               	    								}
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
           	    										validDate: '',
           	    										drugId:''
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
            	maincurRow = index;//点击加入当前行索引
            	//alert("maincurRow:"+maincurRow);
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
            
            $(document).click(function(event) {
    			//console.info("event:"+event.target.getAttribute("field"));
    			if (event.target.getAttribute("field") != "itemName") 
    			{
    				rowNo = 0;
    				$("#selectItem").hide();
    			}
    			
    			//alert(event.target.getAttribute('class'));
    			//alert($(event.target).text());
    				if(event.target.getAttribute('class') != "" && event.target.getAttribute('class') != null)
        			{
        				if(event.target.getAttribute('class').indexOf('datagrid-cell') == -1)//是否点击表格cell 不包含
        				{
        					dg.datagrid('endEdit', opts.editIndex);
        				}
        			}
        			else
        			{
        				if($(event.target).text().trim() != "")
            			{
        					dg.datagrid('endEdit', opts.editIndex);
            			}
        			}
    			
    			
    		});
            $("#selectItem").click(function(e) {
    			e.stopPropagation(); //  阻止冒泡
    		});
        });
        
    } ,
    keyCtr : function (jq) {
        return jq.each(function () {         				  
            var grid = $(this);             
            grid.datagrid('getPanel').panel('panel').attr('tabindex', 1).bind('keydown', function (e) {            
                switch (e.keyCode) {               
                case 13: // up
                
                    var selected = grid.datagrid('getSelected');                   
                    if (selected) {                    	 
                    	var index = grid.datagrid('getRowIndex', selected);
                        grid.datagrid('selectRow', index);                       
                      //单元格聚焦
                      alert(fieldname);
                        if(fieldname != ""){
                        	if(fieldname=="itemName"){
                        	var ed =  $('#mytable').datagrid('getEditor', {index:index,field:'itemName'});
                        	 $(ed.target).blur();
                        		$('#mytable').datagrid('endEditCell', {index: index, field:'itemName'}); 
                           	 $('#mytable').datagrid('beginEditCell', {index: index, field: 'amount'});                           	                       
                           	 /* alert(ed); */                          
                           	 fieldname="amount";                           
                            grid.datagrid('selectRow', index);
                            var ed =  $('#mytable').datagrid('getEditor', {index:index,field:'amount'});                         
                           $(".datagrid-editable-input").focus(function(){
                           this.select();
                           });
                           $(ed.target).focus();
                                index=index+1;
                        	}else if(fieldname=="amount"){    
                        		var ed =  $('#mytable').datagrid('getEditor', {index:index,field:'amount'});
                           	    $(ed.target).blur();
                        		$('#mytable').datagrid('endEditCell', {index: index, field: 'amount'});                         		
                              	 $('#mytable').datagrid('beginEditCell', {index: index+1, field: 'itemName'});
                              	 var ed = $('#mytable').datagrid('getEditor', {index: index+1,field:'itemName'});
                              	  /* alert(ed); */
                              	  fieldname="itemName";
                              	index=index+1;
                              	grid.datagrid('selectRow', index);   
                                $(".datagrid-editable-input").focus(function(){
                                    this.select();
                                    });
                                    $(ed.target).focus();
                        	}
                        	 
                        
                     }
                    } 
                    break;
                case 9: // down
                	  var selected = grid.datagrid('getSelected');                   
                      if (selected) {                    	 
                      	var index = grid.datagrid('getRowIndex', selected);
                          grid.datagrid('selectRow', index);                       
                        //单元格聚焦
                        alert(fieldname);
                          if(fieldname != ""){
                          	if(fieldname=="itemName"){
                          	var ed =  $('#mytable').datagrid('getEditor', {index:index,field:'itemName'});
                          	 $(ed.target).blur();
                          		$('#mytable').datagrid('endEditCell', {index: index, field:'itemName'}); 
                             	 $('#mytable').datagrid('beginEditCell', {index: index, field: 'amount'});                           	                       
                             	 /* alert(ed); */                          
                             	 fieldname="amount";                           
                              grid.datagrid('selectRow', index);
                              var ed =  $('#mytable').datagrid('getEditor', {index:index,field:'amount'});                         
                             $(".datagrid-editable-input").focus(function(){
                             this.select();
                             });
                             $(ed.target).focus();
                                  index=index+1;
                          	}else if(fieldname=="amount"){    
                          		var ed =  $('#mytable').datagrid('getEditor', {index:index,field:'amount'});
                             	    $(ed.target).blur();
                          		$('#mytable').datagrid('endEditCell', {index: index, field: 'amount'});                         		
                                	 $('#mytable').datagrid('beginEditCell', {index: index+1, field: 'itemName'});
                                	 var ed = $('#mytable').datagrid('getEditor', {index: index+1,field:'itemName'});
                                	  /* alert(ed); */
                                	  fieldname="itemName";
                                	index=index+1;
                                	grid.datagrid('selectRow', index);   
                                  $(".datagrid-editable-input").focus(function(){
                                      this.select();
                                      });
                                      $(ed.target).focus();
                          	}
                          	 
                          
                       }
                      } 
                    break;
               
                }
            });
        });
    } 
	
});

//数量校验不为0
$.extend($.fn.validatebox.defaults.rules, {
	notLing: {
		validator: function(value, param){
			console.info("value"+value);
			console.info("value.length"+value.length);
			return value != param[0];
		},
		message: '不能为 {0}.'
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
    			/*
    			if(tr_top <= 262+d.scrollTop)
    			{
    				d.scrollTop = d.scrollTop;
    				if(tr_top == d.scrollTop+1)
    				{
    					d.scrollTop = d.scrollTop-29;
    				}
    			}
    			*/
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
    		
    		//if(tr_top >= d.scrollTop+1)
    		//{
    			//d.scrollTop = d.scrollTop;
    			//$("#selectItem").animate({scrollTop:d.scrollTop},1);
    			//if(tr_top == 262+d.scrollTop)
    			//{
    				//alert(d.scrollTop);
    				//alert(d.scrollHeight);
    				//alert(tr_top);
    				//d.scrollTop = d.scrollTop+29;
    				//$("#selectItem").animate({scrollTop:tr_top-233},1);
    			//}
    		//}
    		
    		$("#selectItem").animate({scrollTop:tr_top-290+2*tr_height},1);
    		document.getElementById("table1").rows(++rowNo).className = "on";
    	}
    }
  	//enter事件
	if(window.event.keyCode== 13)
	{
		//alert(maincurRow);
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
              var drugId= $("#table1 tr:eq("+rowNo+")").find("[name='drugId']").text().trim(); 
			if(matname != null && matname != "")
			{
				selectedData.push({Name:matname,Spec:spec,UnitName:unitname,BatchNo:batchno,Vendor:vendor,Price:price,InPrice:inPrice,ValidDate:validDate,DrugId:drugId});
			}
		}
		
		//alert(matname+","+spec+","+unitname);
		//alert("selectedData.length:::"+selectedData.length);
		//alert(clickIndex.length);
		if(selectedData.length > 0)
		{
			if(rows[maincurRow].orderNo != "" && rows[maincurRow].orderNo != null)//序号不为空 表示要修改
			{
				for(var i = 0;i<selectedData.length;i++)
					{
					//alert(rows[maincurRow].xh.trim());
					$('#mytable').datagrid('updateRow',{
						index: maincurRow,
						row: {
							itemName: selectedData[i]['Name'],
							spec: selectedData[i]['Spec'],
							unit: selectedData[i]['UnitName'],
							vendor: selectedData[i]['Vendor'],
							batchno: selectedData[i]['BatchNo'],
							price: selectedData[i]['Price'],
							inPrice: selectedData[i]['InPrice'],
							validDate: selectedData[i]['ValidDate'],
							drugId: selectedData[i]['DrugId']
							}
						});
				}
			}
			else
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
								validDate: '',
								drugId:''
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
								validDate: selectedData[i]['ValidDate'],
								drugId: selectedData[i]['DrugId']
							}
						});
					inumber = i+1+rowindex;
				}
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
					validDate: '',
					drugId:''
					});
	    	}
    	}
		
    	selectedData.splice(0,selectedData.length);//清空
    	rowNo = 0;
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
	var sum1 = $('#sum1').numberbox('getValue');
	var sum2 = $('#sum2').numberbox('getValue');
	 var obj = {};
	 obj.billType = "出库";
	 obj.typeData = typeData;
	 obj.sum1 = sum1;
	 obj.sum2 = sum2; 
	 if($('#billNo').val()!=""&&$('#billNo').val()!=null&&$('#billNo').val()!="undefined"){
		 obj.billNo = $('#billNo').val();
	 }
	 var newArray = [];
	var json = '';
	/* json= "{"+"\"billType\":\"出库\",\"typeData\":\""+typeData+"\",\"sum1\":"+sum1+",\"sum2\":"+sum2+",\"detailList\":["; */
	for (var i = 0; i < rows.length; i++) {			
	    var row = rows[i];   
	    if(rows[i].orderNo!=""&&rows[i].orderNo!=null&&rows[i].orderNo!="undefined"){
	    	/* json+="{\"orderNo\":"+rows[i].orderNo+"\",\"amount\":"+rows[i].amount+",\"price1\":"+rows[i].inPrice+",\"price2\":"+rows[i].price+",\"validDate\":\""+rows[i].validDate+"\"},";	   */    	
	    	var objes = {};
	    	 objes.orderNo = rows[i].orderNo;
	    	 objes.drugId = rows[i].drugId;
	    	 objes.batchNo = rows[i].batchno;
	    	 objes.amount = rows[i].amount;
	    	 objes.price1 = rows[i].inPrice;
	    	 objes.price2 = rows[i].price;
	    	 objes.validDate = rows[i].validDate;
		        newArray.push(objes);	    	
	    }	  
	}
	if(typeData==""||typeData==null||typeData=="undefined"){
		jQuery.messager.alert('提示:',"请选择领药部门！",'info');
		return false;
	}
	/* alert(newArray.length); */
	if(newArray.length==0){
		jQuery.messager.alert('提示:',"请选择要出库的药品！",'info');
		return false;
	}
	 obj.detailList = newArray;
	   /*  alert(obj); */
	$.ajax({
		type : 'POST',
		url : "/outStorage/save",
		data : JSON.stringify(obj),
		contentType:"application/json;charset=UTF-8",
		dataType : 'JSON',
		success : function(data) {			
			if (data && data.code == 200) {										
				$('#billNo').val(data.data);
				 var interval;  
 				 var time=1000;  
 				 var x=2;    //设置时间2s
 				 jQuery.messager.alert('提示:','保存'+data.msg+'!','info',function(){});     				
 				 interval=setInterval(fun,time);  
 				        function fun(){  
 				      --x;  
 				      if(x==0){  
 				          clearInterval(interval);  
 				  $(".messager-body").window('close');    
 				       }  
 				}; 
			} else {
				jQuery.messager.alert('提示:',data.msg,'info'); 
			}
		}
	});
}
function dosubmit(){	
	var detailList = {};
	var _list = {};
	var rows = $('#mytable').datagrid('getRows');
	/* var typeData = $('#departmentId').val(); */
	var typeData =$('#departmentId').combotree('getText'); 
	var sum1 = $('#sum1').numberbox('getValue');
	var sum2 = $('#sum2').numberbox('getValue');
	 var obj = {};
	 obj.billType = "出库";
	 obj.typeData = typeData;
	 obj.sum1 = sum1;
	 obj.sum2 = sum2;
	 if($('#billNo').val()!=""&&$('#billNo').val()!=null&&$('#billNo').val()!="undefined"){
		 obj.billNo = $('#billNo').val();
	 }
	 var newArray = [];
	var json = '';
	/* json= "{"+"\"billType\":\"出库\",\"typeData\":\""+typeData+"\",\"sum1\":"+sum1+",\"sum2\":"+sum2+",\"detailList\":["; */
	for (var i = 0; i < rows.length; i++) {			
	    var row = rows[i];   
	    if(rows[i].orderNo!=""&&rows[i].orderNo!=null&&rows[i].orderNo!="undefined"){
	    	/* json+="{\"orderNo\":"+rows[i].orderNo+"\",\"amount\":"+rows[i].amount+",\"price1\":"+rows[i].inPrice+",\"price2\":"+rows[i].price+",\"validDate\":\""+rows[i].validDate+"\"},";	   */    	
	    	var objes = {};
	    	 objes.orderNo = rows[i].orderNo;
	    	 objes.drugId = rows[i].drugId;
	    	 objes.batchNo = rows[i].batchno;
	    	 objes.amount = rows[i].amount;
	    	 objes.price1 = rows[i].inPrice;
	    	 objes.price2 = rows[i].price;
	    	 objes.validDate = rows[i].validDate;
		        newArray.push(objes);	    	
	    }	  
	}
	if(typeData==""||typeData==null||typeData=="undefined"){
		jQuery.messager.alert('提示:',"请选择领药部门！",'info');
		return false;
	}
	alert(newArray.length);
	if(newArray.length==0){
		jQuery.messager.alert('提示:',"请选择要出库的药品！",'info');
		return false;
	}
	 obj.detailList = newArray;
	   /*  alert(obj); */
	 $.ajax({
		type : 'POST',
		url : "/outStorage/submit",
		data : JSON.stringify(obj),
		contentType:"application/json;charset=UTF-8",
		dataType : 'JSON',
		success : function(data) {			
			if (data && data.code == 200) {				
				/* alert("asqw"); */
				$('#mytable').datagrid('loadData', { total: 0, rows: [] });//清空下方DateGrid 
				$('#mytable').datagrid('appendRow',{
	 				orderNo:'',
						itemName: '',
						spec: '',
						unit: '',
						vendor: '',
						batchno:'',
						price: '',
						inPrice: '',
						validDate: '',
						drugId:''
					});
				$('#mytable').datagrid('appendRow',{
	 				orderNo:'',
						itemName: '',
						spec: '',
						unit: '',
						vendor: '',
						batchno:'',
						price: '',
						inPrice: '',
						validDate: '',
						drugId:''
					});
				$('#mytable').datagrid('appendRow',{
	 				orderNo:'',
						itemName: '',
						spec: '',
						unit: '',
						vendor: '',
						batchno:'',
						price: '',
						inPrice: '',
						validDate: '',
						drugId:''
					});
				$('#mytable').datagrid('appendRow',{
	 				orderNo:'',
						itemName: '',
						spec: '',
						unit: '',
						vendor: '',
						batchno:'',
						price: '',
						inPrice: '',
						validDate: '',
						drugId:''
					});
				$('#mytable').datagrid('appendRow',{
	 				orderNo:'',
						itemName: '',
						spec: '',
						unit: '',
						vendor: '',
						batchno:'',
						price: '',
						inPrice: '',
						validDate: '',
						drugId:''
					});
				$('#mytable').datagrid('appendRow',{
	 				orderNo:'',
						itemName: '',
						spec: '',
						unit: '',
						vendor: '',
						batchno:'',
						price: '',
						inPrice: '',
						validDate: '',
						drugId:''
					});
				$('#billNo').val('');
				 var interval;  
 				 var time=1000;  
 				 var x=2;    //设置时间2s
 				 jQuery.messager.alert('提示:','提交'+data.msg+'!','info',function(){});     				
 				 interval=setInterval(fun,time);  
 				        function fun(){  
 				      --x;  
 				      if(x==0){  
 				          clearInterval(interval);  
 				  $(".messager-body").window('close');    
 				       }  
 				}; 
			} else {
				jQuery.messager.alert('提示:',data.msg,'info'); 
			}
		}
	}); 
}
function doopen(){
 var billNo=$('#billNos').val();
 $('#mytable').datagrid('loadData', { total: 0, rows: [] });//清空下方DateGrid 
	$.ajax({
		type : 'POST',
		url : "/outStorage/getDetailData",
		data : {billNo:billNo},
		dataType : 'JSON',
		success : function(data) {						
			if (data && data.code == 200) {										
				var validDate=data.data.detailAndDrugList.validDate;				
				for(var i=0;i<data.data.detailAndDrugList.length;i++){					
					data.data.detailAndDrugList[i]["validDate"]=jsonDateFormat(data.data.detailAndDrugList[i]["validDate"]);
					data.data.detailAndDrugList[i]["inPrice"]=data.data.detailAndDrugList[i]["price1"];
					data.data.detailAndDrugList[i]["price"]=data.data.detailAndDrugList[i]["price2"];
					data.data.detailAndDrugList[i]["total1"]=data.data.detailAndDrugList[i]["amount"]*data.data.detailAndDrugList[i]["price1"];
					data.data.detailAndDrugList[i]["total2"]=data.data.detailAndDrugList[i]["amount"]*data.data.detailAndDrugList[i]["price2"];
					
					data.data.detailAndDrugList[i]["price1"] = undefined;
					  data.data.detailAndDrugList[i]["price2"] = undefined;
				}
				   $('#mytable').datagrid('loadData',{"total" : data.data.detailAndDrugList.length,"rows" : data.data.detailAndDrugList});  
				/*$('#mytable').datagrid({   
				    url:data.data.detailAndDrugList  
				}); */
					$('#mytable').datagrid('appendRow',{
		 				orderNo:'',
							itemName: '',
							spec: '',
							unit: '',
							vendor: '',
							batchno:'',
							price: '',
							inPrice: '',
							validDate: '',
							drugId:''
						});
			} else {
				jQuery.messager.alert('提示:',data.msg,'info'); 
			}
		}
	});
	 
	
	
	
} 

(function ($) {

	var editNumCell = undefined;
	var zdyBeforeEdit = undefined;
	var zdyCheckField = undefined;
	var showNumValue = undefined; 
	var checkFocus = undefined;//用来检查失去焦点的状态
	var comboboxValue = {};
	var copyArray = [];//用来存数最新的数据
	var zdyFlag = undefined;//判断值是否发生改变的标记
	var EditorFields = [];//存放具有编辑器的列的集合
	 
	    //开启编辑单元格状态,参数options的格式构成为:Object {index: **, field: "***"},由索引index和field字段名构成
	    function beginEditCell(target, options) {
	         
	        _initCellEditor(target, options.index, options.field);
	        _outerWidthOfEditable(target);
	        //$.validateRow(target, options.index);暂时先不使用,不知道该方法作用
	    }
	 
	    /**
	     * 初始化表格编辑器
	     * @param target
	     * @param _index
	     * @param _field
	     */
	    function _initCellEditor(target, _index, _field) {
	        //获得datagrid数据表格的属性options
	        var opts = $.data(target, "datagrid").options;
	        //获得数据表格的tr
	        var tr = opts.finder.getTr(target, _index);
	        //获得填充表格的tr的记录row
	        var row = opts.finder.getRow(target, _index);
	         
	        //循环该tr下的每一个td
	        tr.children("td").each(function (index) {
	            //此时还未开启编辑,可以看到每一个cell都是一个div,格式为[div.datagrid-cell.datagrid-cell-c1-practName...]
	            var cell = $(this).find("div.datagrid-cell");
	            var field = $(this).attr("field");
	 
//	            if (field == _field) {//找到与传递参数相同field的单元格
	                //返回指定datagrid的列属性
	                var col = $(target).datagrid("getColumnOption", field);
	                
	                if (col && col.editor) {
	                    EditorFields.push(field);
	                    var editorType, editorOp;
	                    if (typeof col.editor == "string") {
	                        editorType = col.editor;
	                    } else {
	                        editorType = col.editor.type;
	                        editorOp = col.editor.options;
	                    }
	                    var editor = opts.editors[editorType];
	                    //设置编辑器样式和在该div中填充一个table表格
	                    if (editor) {
	                        var html = cell.html();
	                        var outerWidth = cell._outerWidth();
	                        cell.addClass("datagrid-editable");
	                        cell._outerWidth(outerWidth);
	                        cell.html("<table border=\"0\" cellspacing=\"0\" cellpadding=\"1\"><tr><td></td></tr></table>");
	                        cell.children("table").bind(
	                            "click dblclick contextmenu",
	                            function (e) {
	                                e.stopPropagation();
	                            });
	                        $.data(cell[0], "datagrid.editor", {
	                            actions: editor,
	                            target: editor.init(cell.find("td"),
	                                editorOp),
	                            field: field,
	                            type: editorType,
	                            oldHtml: html
	                        });
	                    }
	                }
	 
	                tr.find("div.datagrid-editable").each(function () {
	                    var field = $(this).parent().attr("field");
	                    var ed = $.data(this, "datagrid.editor");
//	                    $('.datagrid-editable')[0].style['height']="auto";//设置包裹该editorde的div的样式
	                    ed.actions.setValue(ed.target, row[field]);
	                });
//	            }
	        });
	    }
	 
	    //为可编辑的单元格设置外边框
	    //来自jquery.easyui.1.8.0.js的 function _4d8()方法
	    function _outerWidthOfEditable(target) {
	        var dc = $.data(target, "datagrid").dc;
	        dc.view.find("div.datagrid-editable").each(function () {
	            var _this = $(this);
	            var field = _this.parent().attr("field");
	            var col = $(target).datagrid("getColumnOption", field);
	            _this._outerWidth(col.width);
	            var ed = $.data(this, "datagrid.editor");
	            if (ed.actions.resize) {
	                ed.actions.resize(ed.target, _this.width());
	            }
	        });
	    }
	 
	    //关闭编辑单元格状态
	    function endEditCell(target, options) {
	        var opts = $.data(target, "datagrid").options;
	 
	        var updatedRows = $.data(target, "datagrid").updatedRows;
	        var insertedRows = $.data(target, "datagrid").insertedRows;
	 
	        var tr = opts.finder.getTr(target, options.index);
	        var row = opts.finder.getRow(target, options.index);
	 
	        var _535 = false;
	        var _536 = {};
	        tr.find("div.datagrid-editable").each(function () {
	            var _537 = $(this).parent().attr("field");
	            var ed = $.data(this, "datagrid.editor");
	            var _538 = ed.actions.getValue(ed.target);
	            if (row[_537] != _538) {
	                row[_537] = _538;
	                _535 = true;
	                _536[_537] = _538;
	            }
	        });
	        if (_535) {
	            if (_45f(insertedRows, row) == -1) {
	                if (_45f(insertedRows, row) == -1) {
	                    updatedRows.push(row);
	                }
	            }
	        }
	         
	        _destroyCellEditor(target, options);
	        $(target).datagrid("refreshRow", options.index);
	        opts.onAfterEdit.call(target, options.index, row, _536);
	    }
	 
	    function _45f(a, o) {
	        for (var i = 0, len = a.length; i < len; i++) {
	            if (a[i] == o) {
	                return i;
	            }
	        }
	        return -1;
	    }
	 
	    //销毁单元格编辑器
	    function _destroyCellEditor(target, options) {
	        var opts = $.data(target, "datagrid").options;
	        var tr = opts.finder.getTr(target, options.index);
	 
	        tr.children("td").each(function () {
	            var field = $(this).attr("field");
	                var cell = $(this).find("div.datagrid-editable");
	                if (cell.length) {
	                    var ed = $.data(cell[0], "datagrid.editor");
	                    if (ed.actions.destroy) {
	                        ed.actions.destroy(ed.target);
	                    }
	                    cell.html(ed.oldHtml);
	                    $.removeData(cell[0], "datagrid.editor");
	                    cell.removeClass("datagrid-editable");
	                    cell.css("width", "");
	                }
	        });
	    }
	 
	    $.extend($.fn.datagrid.methods, {
	        beginEditCell: function (target, options) {
	            return target.each(function () {
	                beginEditCell(this, options);
	            });
	        },
	        endEditCell: function (target, options) {
	            return target.each(function () {
	                endEditCell(this, options);
	            });
	        }
	    });
	     
	    /**
	     * 开启单击单元格
	     * 
	     */
	    var _rowIndex = undefined;
	    var _field = undefined;
	    var _rowId=undefined;
	    function onClickRow(rowIndex, field, value,id) {
	        if(zdyBeforeEdit!=undefined && $.isFunction(zdyBeforeEdit)){
	            zdyBeforeEdit(rowIndex,id);
	        }
	        var columnOption=$(id).datagrid("getColumnOption",field);
	        if(columnOption && columnOption.readonly == true)
	            return false;
	        if (_rowIndex != rowIndex || _field != field) {
	            if (endEditing(id)){
	                $(id).datagrid('selectRow',rowIndex);
	                $(id).datagrid('beginEditCell', {index: rowIndex, field: field});
	                //去除当前被编辑的单元格之外的编辑框的错误信息提示的图标
	                $(".validatebox-invalid").removeClass("validatebox-invalid");
	                 
	                var ed = $(id).datagrid('getEditor', {index:rowIndex,field:field});
	                if(ed == null){
	                    _destroyCellEditor($(id)[0],{index:rowIndex,field:field});/*必须要先销毁再点击,否则会出现一个div中有多个编辑框*/
	                    onClickRow(rowIndex, EditorFields[0], value,id);
	                    return false;
	                }
	                _rowIndex = rowIndex;
	                _field = field;
	                _rowId=id;
	                 
	                $(".datagrid-editable-input").focus(function(){
	                    this.select();
	                });
	                $(ed.target).focus();
	                 
	                var row=$(id).datagrid("getRowData",rowIndex);
	                if(zdyCheckField!=undefined && $.isFunction(zdyCheckField)){
	                    zdyCheckField(row,field);
	                    $(".datagrid-editable-input")[0].select();
	                }
	                 
	                $(".datagrid-editable-input").blur(function(){
	                    field = $(this).parents('td[field]').attr('field');
	                    copyArray[field] = this.value;
	                    checkFocus = false;//用来检查获得是否在失去焦点的时候点击的是input文本框
	                     
	                    zdyFlag = false;//该值用来检测是否有值发生改变,true表示发生改变,false表示没有
	                    for(var name in copyArray){
	                        if(copyArray[name]!= row[name]){
	                            row[name] = copyArray[name];//让row中的数据是最新的数据,即修改过后的数据
	                            zdyFlag = true;
	                        }
	                    }
	                     
	                    $("input").focus(function(){
	                        checkFocus = true;
	                    });
	                    setTimeout(function(){
	                        if(!checkFocus/*表示点击非文本框input之外的地方而失去焦点*/){timeOutCode(id,rowIndex,field,row,ed);}
	                    },200);
	                });
	                $(".datagrid-editable-input").bind('keydown', function(){
	                    switch (window.event.keyCode) {
	                        case 13:
	                            $(this).blur();
	                        case 37 :/* Left  ←*/
	                            $(this).blur();
	                            var prevRow = $(id).datagrid('prevRow');
	                            var preRowIndex = $(id).datagrid('getRowIndex',prevRow);
	                            if(prevRow){
	                                onClickRow(preRowIndex, field, value,id);
	                            }
	                        case 40 :/*Down ↓*/
	                            $(this).blur();
	                            var nextRow = $(id).datagrid('nextRow');
	                            var nextRowIndex = $(id).datagrid('getRowIndex',nextRow);
	                            if(nextRow){
	                                onClickRow(nextRowIndex, field, value,id);
	                            }
	                    }
	                });
	            }
	        }
	        return true;
	    }    
	    function endEditing(id) {
	        if (_rowIndex == undefined || _field == undefined || id == undefined) {
	            return true;
	        }
	 
	        $(id).datagrid('endEditCell', {index: _rowIndex, field: _field});
	        _rowIndex = undefined;
	        _field = undefined;
	        _rowId = undefined;
	        return true;
	    }
	 
	    function afterEdit(rowIndex, rowData,id){
	        $(id).datagrid('updateRow',{
	            index: rowIndex,
	            row: rowData
	        });
	        $(id).datagrid('acceptChanges');
	    }
	    function onAfterEditing(rowIndex,row,id){
	        var saveData=save(rowIndex,row,id);
	        row=saveData[0];
	         
	        afterEdit(rowIndex,row,id);
	    }
	 
	/**
	 * 改变执行顺序的代码片段,要在判断了input获得焦点之后才执行,否则会先于判断执行
	 */
	function timeOutCode(id,rowIndex,field,row,ed){
	    //如果所有的编辑器都失去焦点,那么进入下面方法
	    if(ed.type=="combobox" && zdyCheckField!=undefined){
	        comboboxValue.id = id;
	        comboboxValue.rowIndex = rowIndex;
	        comboboxValue.field = field;
	        comboboxValue.row = row;
	        comboboxValue.value = $(".datagrid-editable-input").val();
	        _rowIndex = undefined;
	        _field = undefined;
	    }else{
	         
	        _destroyCellEditor($(id)[0],{index:rowIndex,field:field});
	        if(showNumValue!=undefined){
	            oldVal = showNumValue;
	        }
	        if(zdyFlag){//如果值发生改变
	            onAfterEditing(rowIndex,row,id);
	        }
	         
	        _rowIndex = undefined;
	        _field = undefined;
	    }
	}
})(jQuery);	
</script>
</html>