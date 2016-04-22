

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
		<script type="text/javascript" src="/staticPublic/js/jquery.min.js"></script>
		<script type="text/javascript" src="/staticPublic/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="/staticPublic/js/easyui-lang-zh_CN.js"></script> 
		<title>盘点页面</title>
		<script type="text/javascript">
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
		$(function(){						
			
			$('#mytable').datagrid('options').onSelect = function(index, rowData){selectRow = index;}
		/* 	$.ajax({
					type : "post",
    				url : "/salesReturn/getEnabledDicProviderList",
    				data : {
    					providerName : ''
    				},
    				dataType : "json",
    				success: function(data){
    					//alert(JSON.stringify(data.data));
    					$('#typeData').combobox({
    						data: data.data,
    						method:'post',
    						valueField:'id',
    						textField:'providerName',
    						loadFilter: function (data){
    							var newData = [];
    							for(var i = 0;i<data.length;i++)
    							{
    								newData.push({id:data[i].id,providerName:data[i].providerName.trim()});
    							}
    							
    							return newData;
    						}
    						});
    				}
				}); */
			
		})
		
		</script>
<style type="text/css">
.ipt {
	border: 1px solid #d3d3d3;
	padding: 7px 7px;
	width: 115px;
	font-size: 13px;
 }
 .ipts {	
	padding-left: 10px ;	
 }
 .iptes {	
	margin-left: 10px ;	
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
	width: 490px;
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
.fonttitle{
	font-size: 13px;
	/* font-family: Microsoft YaHei; */
}	
</style>
	</head>
	<body >
	<div style="margin:20px 0;">
	</div>
<input type="hidden" name="billNo" id="billNo">
<div class="box1">
<table >
<tr>
<td class="fonttitle">盘点号：</td>
<td><input type="text" class="easyui-textbox" name="checkNo" id="checkNo" data-options="" style="width:80px;height:25px"/>   </td>
<td class="ipts"> <a href="#" id="openbtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:70px" onclick="doopen();">打开</a> </td>
</tr>
<tr>
<!-- <td class="fonttitle">盘点号：</td>
<td><input id="typeData" class="easyui-textbox" style="width:230px;height:25px" data-options="prompt:'请填写调价批文'"></input>  </td>
 --><td class="fonttitle ">理论总金额：</td>
<td ><input id="sum1"  name="sum1" class="easyui-numberbox" precision="2" style="width:80px;height:25px"/></td>
<td class="fonttitle ipts">实际总金额：</td>
<td> <input  name="sum2" id="sum2" class="easyui-numberbox" precision="2" style="width:80px;height:25px" /></td>
<td class="fonttitle ipts">名称搜索：</td>
<td> <input class="easyui-combobox" name="itemName" id="itemName"   style="width:170px;height:25px"data-options="loader: btsloader,mode: 'remote',valueField: 'id',textField: 'itemName',panelWidth: 350,panelHeight: 'auto',formatter:formatter,onSelect:onccSelect,onHidePanel:onHidePanel" />
 <!-- <input  name="itemName" id="itemName" class="easyui-textbox" precision="2" style="width:50px;height:25px" /> class="easyui-textbox" -->
<input  name="itemNames" id="itemNames" type="hidden"  precision="2" style="width:120px;height:25px" /><input  name="id" id="id" type="hidden" /></td>
<td class="fonttitle ipts">实际数量：</td>
<td> <input  name="sum3" id="sum3" class="easyui-numberbox" precision="2" style="width:100px;height:25px" /></td>
<td> <a href="#" id="addbtn" class="easyui-linkbutton iptes" data-options="iconCls:'icon-add'" style="width:70px" onclick="doadd();">新建</a> 
<a href="#" id="cancelbtn" class="easyui-linkbutton" data-options="iconCls:'icon-reload',disabled:true" style="width:70px" onclick="docancel();" >取消</a> 
    <a href="#" id="savebtn" class="easyui-linkbutton " data-options="iconCls:'icon-save',disabled:true" style="width:70px; height:25px;" onclick="dosave();">保存</a>
    <a href="#" id="printbtn" class="easyui-linkbutton" data-options="iconCls:'icon-print',disabled:true" style="width:70px; height:25px;" >打印</a>
    <a href="#" id="submitbtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok',disabled:true" style="width:70px; height:25px;" onclick="dosubmit();" >封存</a>
    <a href="#" id="removebtn" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',disabled:true" style="width:70px; height:25px;" onclick="doremove();">作废</a></td>
</tr>
</table>

</div>
	<div id="tb" style="padding:10px 0px;">
	<table class="easyui-datagrid" style="width:100%;height:600px;" id="mytable"
			data-options="rownumbers:true,singleSelect:true,collapsible:true,method:'get'">
		<thead>
			<tr>
				<th data-options="field:'orderNo',width:80,align:'center',hidden:'true'">序号</th>				
				<th data-options="field:'itemName',width:200,align:'center',editor:'text'" >名称</th>
				<th data-options="field:'spec',width:150,align:'center'">规格</th>
				<th data-options="field:'batchNo',width:200,align:'center'">批号</th>
				<th data-options="field:'price',width:100,align:'center'">单价</th>
				<th data-options="field:'amount',width:100,align:'center'">数量</th>
				<th data-options="field:'unit',width:80,align:'center'">单位</th>				
				<th data-options="field:'storePrice',width:100,align:'center'">库存金额</th>
				<th data-options="field:'realAmount',width:100,align:'center'">实际数量</th>
				<th data-options="field:'realPrice',width:100,align:'center'">实际金额</th>
				<th data-options="field:'validDate',width:100,align:'center',hidden:'true'">有效期</th>
				<th data-options="field:'vendor',width:100,align:'center',hidden:'true'">vendor</th>
				<th data-options="field:'drugId',hidden:'true'">drugId</th>
				<th data-options="field:'inPrice',hidden:'true'">inPrice</th>
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
				<td></td>
			</tr>
		</tbody>
	</table>
	</div>
	
	<script type="text/javascript">
	$(document).ready(function() {
		$("#sum1").numberbox("disable");
		$("#sum2").numberbox("disable");
	});
	function onHidePanel(){
		$('#sum3').numberbox().next('span').find('input').focus();
		var id=$('#itemName').combobox('getValue');
		
		var rows = $('#mytable').datagrid('getRows');
		
		var index="";
		 for(i = 0;i < rows.length;i++)  
		 {
			 //alert(id+rows[i].drugId);
			 if(rows[i].drugId==id){
				 index=i;
				 $('#mytable').datagrid('selectRow', index); 
				 break; 
			 }
		 }
		 //alert(index);
		 if(index!=""||index==0){
			// alert(index);			
			  $("#sum3").next("span").children().first().on('input', function (e) {
				//alert('rr'+ $(this).val()+rows[index-1].price);
				 var realAmount= $(this).val();
				 var realPrice=rows[index].price*$(this).val();
				 $('#mytable').datagrid('updateRow',{
						index: index,
						row: {
							realAmount: realAmount,
							realPrice:realPrice.toFixed(3),
						}
					});
					var sum2=0;
				 if(rows.length>0){					
						for(i = 0;i < rows.length;i++)  
						 {							
							sum2+=parseFloat(rows[i].realPrice);							 
						 }
					}				
					$('#sum2').numberbox('setValue', sum2);
		        }) 
		 }
		
	}
	function formatter(row){		
		 var ss=row.itemName+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+row.spec+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+row.unit;
      
		 return ss;
		
	}
	function onccSelect(record){
		var itemName=$('#itemName').combobox('getText');		
		var id=$('#itemName').combobox('getValue');
		$('#itemName').val(itemName);
		$('#id').val(id);
	}
	var btsloader = function(param,success,error){		  
		  //获取输入的值
		  var q = param.q || "";		  
		  //此处q的length代表输入多少个字符后开始查询
		  if(q.length <= 0) return false;
		  $.ajax({
		  url:"/storeCheck/getEnabledDrugList",
		  type:"post",
		  data:{
		  //传值，还是JSON数据
		  itemName:q
		  },		  
		  //重要，如果写jsonp会报转换错误，此处不写都可以
		  dataType:"json",
		  success:function(data){	
			  //alert(JSON.stringify(data.rows));
		  //关键步骤，遍历一个MAP对象
		  var items = $.map(data.data, function(item){
			 // var ss=item.itemName.trim()+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+item.spec.trim()+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+item.unit.trim();
		  return {
		  id:item.id,
		  itemName:item.itemName.trim(),
		  spec:item.spec.trim(),
		  unit:item.unit.trim()
		  };
		  });		  
		  //执行loader的success方法
		  success(items);
		  },		  
		  //异常处理
		  error:function(xml, text, msg){
		  error.apply(this, arguments);
		  }
		  });
		  };
	/***字符串转换成float类型的数据***/
	function isNumber(n) {
		  return !isNaN(parseFloat(n)) && isFinite(n);
		}
	 $("#mytable").datagrid({
		 onClickRow: function (index, row) {			
			 $('#itemName').combobox('setValue', row.itemName);
			 $('#sum3').numberbox().next('span').find('input').focus();
			 $("#sum3").next("span").children().first().on('input', function (e) {
					//alert('rr'+ $(this).val()+rows[index-1].price);
					 var realAmount= $(this).val();
					 var realPrice=row.price*$(this).val();
					
					 $('#mytable').datagrid('updateRow',{
							index: index,
							row: {
								realAmount: realAmount,
								realPrice:realPrice.toFixed(3),
							}
						});
					 var rows = $('#mytable').datagrid('getRows');
					 var sum2=0;
					 if(rows.length>0){							
							for(var i = 0;i < rows.length;i++)  
							 { 								
								sum2+=parseFloat(rows[i].realPrice);								 
							 }
							//alert(sum2);
						}				
						$('#sum2').numberbox('setValue', sum2);
			        }) 
		 }
	 })
	function doadd(){
		$.ajax({
			type : 'POST',
			url : "/storeCheck/getStoreDrugList",
			data : {},
			dataType : 'JSON',
			success : function(data) {						
				if (data && data.code == 200) {	
					$("#addbtn").linkbutton("disable");
					$("#openbtn").linkbutton("disable");
					$("#cancelbtn").linkbutton("enable");
					$("#savebtn").linkbutton("enable");
					$("#printbtn").linkbutton("enable");
					$("#submitbtn").linkbutton("enable");
					$("#removebtn").linkbutton("enable");
				$('#mytable').datagrid('loadData',{"total" : data.data.length,"rows" : data.data});
				if(data.data.length>0){
					var sum1=0;
					var sum2=0;
					for(var m=0;m<data.data.length;m++){
						sum1+=data.data[m].storePrice;
						sum2+=data.data[m].realPrice;
					}
				}				
				$('#sum1').numberbox('setValue', sum1);
				$('#sum2').numberbox('setValue', sum2);
				//var itemName=$('#itemName').combobox('getValue');
				
				} else {
					jQuery.messager.alert('提示:',data.msg,'info'); 
				}
			}
		});
	}
	//保存
	function dosave(){
		var detailList = {};
		var rows = $('#mytable').datagrid('getRows');  
		//var typeData =$('#typeData').val().trim();		
		//alert(typeData);
		var sum1 = $('#sum1').val();
		var sum2 = $('#sum2').val();
		var sum3 = $('#sum3').val();
		//alert(sum1);
		/* alert($('#billNo').val()); */
		
		var obj = {};
		 //obj.billType = "盘点";
		 //obj.typeData = typeData;
		 obj.retailSum = sum1;
		 obj.checkRetailSum = sum2;
		 var newArray = [];
		 var json = '';
		 if($('#checkNo').val()!=""&&$('#checkNo').val()!=null&&$('#checkNo').val()!="undefined"){
			 obj.checkNo = $('#checkNo').val();
		 }
		 //var entities = "";
		 for(i = 0;i < rows.length;i++)  
		 {  
			if(rows[i]['itemName'].trim() != "" && rows[i]['itemName'].trim() != null)
			{
				//entities = entities  + JSON.stringify(rows[i]);
				if( rows[i].realAmount == "" || rows[i].realAmount == null)
				{
					$.messager.alert('提示:',"第"+(i+1)+"行实际数量异常！",'info');
					return false;
				}
				var objes = {};
				 objes.orderNo = rows[i].orderNo;
		    	 objes.drugId = rows[i].drugId;
		    	 objes.batchNo = rows[i].batchno;
		    	 objes.amount = rows[i].num;
		    	 objes.price = rows[i].price;
		    	 objes.inPrice = rows[i].inPrice;
		    	// objes.price2 = rows[i].newprice;
		    	 //objes.validDate = rows[i].validDate;		    	
		    	 objes.realAmount = rows[i].realAmount;
		    	// objes.realPrice = rows[i].realPrice;		    	
			     newArray.push(objes);
			}
		 } 
		 /* if(newArray.length ==0)
		 {
			 $.messager.alert('提示:',"请新建或打开盘点单！",'info');
			 return false;
		 } */
		 obj.detailList = newArray;
		 //alert(entities);
		 $.messager.confirm('确认对话框', '确认保存该盘点数据吗？', function(r){
	if (r){	   
		$.ajax({  
            url: '/storeCheck/save',  
            type: "post",  
            dataType: 'json',
            contentType:"application/json;charset=UTF-8",
            data: JSON.stringify(obj),
            success: function (data) {  
                if(data.code == 200){  
               	 //$('#checkNo').val(data.data);
               	 $('#checkNo').textbox('setValue', data.data);     
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
                   }else
                   {  
                   	//$.messager.alert(data.msg);  
                   	$.messager.alert('提示:',data.msg,'info');
                       return;  
                   }  
            }  
          }); 
	}
});
    }
	//清除
 function docancel(){
	 $.messager.confirm('确认对话框', '您确认要取消该盘点数据吗？', function(r){
			if (r){
				 $('#mytable').datagrid('loadData', { total: 0, rows: [] });//清空下方DateGrid 
				 $('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
				 $('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
				 $('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
				 $('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
				 $('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
				 $('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
						$('#checkNo').textbox('setValue','');
						$('#sum1').numberbox('setValue', '');
						$('#sum2').numberbox('setValue', '');
						$('#sum3').numberbox('setValue', '');  
						$("#addbtn").linkbutton("enable");
						$("#openbtn").linkbutton("enable");
						$("#cancelbtn").linkbutton("disable");
						$("#savebtn").linkbutton("disable");
						$("#printbtn").linkbutton("disable");
						$("#submitbtn").linkbutton("disable");
						$("#removebtn").linkbutton("disable");
			}
		});  
	 }
	//封存
	function dosubmit()
	{
		var detailList = {};
		var rows = $('#mytable').datagrid('getRows');  
		//var typeData =$('#typeData').val().trim();		
		//alert(typeData);
		var sum1 = $('#sum1').val();
		var sum2 = $('#sum2').val();
		var sum3 = $('#sum3').val();
		//alert(sum1);
		/* alert($('#billNo').val()); */
		
		var obj = {};
		 //obj.billType = "盘点";
		 //obj.typeData = typeData;
		 obj.retailSum = sum1;
		 obj.checkRetailSum = sum2;
		 var newArray = [];
		 var json = '';
		 if($('#checkNo').val()!=""&&$('#checkNo').val()!=null&&$('#checkNo').val()!="undefined"){
			 obj.checkNo = $('#checkNo').val();
		 }
		 //var entities = "";
		 for(i = 0;i < rows.length;i++)  
		 {  
			if(rows[i]['itemName'].trim() != "" && rows[i]['itemName'].trim() != null)
			{
				//entities = entities  + JSON.stringify(rows[i]);
				if( rows[i].realAmount == "" || rows[i].realAmount == null)
				{
					$.messager.alert('提示:',"第"+(i+1)+"行实际数量异常！",'info');
					return false;
				}
				var objes = {};
				 objes.orderNo = rows[i].orderNo;
		    	 objes.drugId = rows[i].drugId;
		    	 objes.batchNo = rows[i].batchno;
		    	 objes.amount = rows[i].num;
		    	 objes.price = rows[i].price;
		    	 objes.inPrice = rows[i].inPrice;
		    	// objes.price2 = rows[i].newprice;
		    	 //objes.validDate = rows[i].validDate;		    	
		    	 objes.realAmount = rows[i].realAmount;
		    	// objes.realPrice = rows[i].realPrice;		    	
			     newArray.push(objes);
			}
		 } 		 	 
		 obj.detailList = newArray;
		 $.messager.confirm('确认对话框', '封存后，盘点数据将不可再更改，确认要封存吗？', function(r){
				if (r){
				    // 封存操作;
					$.ajax({  
			            url: '/storeCheck/submit',  
			            type: "post",  
			            dataType: 'json',
			            contentType:"application/json;charset=UTF-8",
			            data: JSON.stringify(obj),
			            success: function (data) {  
			                if(data.code == 200){  
			               	 //$('#checkNo').val(data.data);
			               	 $('#checkNo').textbox('setValue', data.data);     
			               	 var interval;  
			    				 var time=1000;  
			    				 var x=2;    //设置时间2s
			    				 jQuery.messager.alert('提示:','封存'+data.msg+'!','info',function(){});     				
			    				 interval=setInterval(fun,time);  
			    				        function fun(){  
			    				      --x;  
			    				      if(x==0){  
			    				          clearInterval(interval);  
			    				  $(".messager-body").window('close');    
			    				       }  
			    				}; 
			    				$('#mytable').datagrid('loadData', { total: 0, rows: [] });//清空下方DateGrid 
			    				$('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
			    				$('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
			    				$('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
			    				$('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
			    				$('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
			    				$('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
								$('#checkNo').textbox('setValue','');
								$('#sum1').numberbox('setValue', '');
								$('#sum2').numberbox('setValue', '');
								$('#sum3').numberbox('setValue', '');  
								$("#addbtn").linkbutton("enable");
								$("#openbtn").linkbutton("enable");
								$("#cancelbtn").linkbutton("disable");
								$("#savebtn").linkbutton("disable");
								$("#printbtn").linkbutton("disable");
								$("#submitbtn").linkbutton("disable");
								$("#removebtn").linkbutton("disable");
			                   }else
			                   {  
			                   	//$.messager.alert(data.msg);  
			                   	$.messager.alert('提示:',data.msg,'info');
			                       return;  
			                   }  
			                
			            }  
			          });
				}
			});
 
	}
	//作废
	function doremove()
	{
		//alert($('#checkNo').val());
		//var checkNo=parseInt($('#checkNo').val());
		//alert(checkNo);
		if($('#checkNo').val()==""){
			$.messager.alert('提示:','盘点数据未保存无法作废！请点击取消按钮！','info');
            return;  
		}
			 $.messager.confirm('确认对话框', '确认要作废盘点数据吗？', function(r){
				if (r){
				    // 作废操作;
				    $.ajax({
							type : 'POST',
							url : '/storeCheck/delete',
							data : {checkNo : parseInt($('#checkNo').val())},
							dataType : 'JSON',
							success : function(data) {
							if(data.code == 200){  			               	 
							var interval;  
							 var time=1000;  
							var x=2;    //设置时间2s
							jQuery.messager.alert('提示:','作废'+data.msg+'!','info',function(){});     				
							interval=setInterval(fun,time);  
							 function fun(){  
							--x;  
							if(x==0){  
							clearInterval(interval);  
							 $(".messager-body").window('close');    
							 }  
							}; 
							$('#mytable').datagrid('loadData', { total: 0, rows: [] });//清空下方DateGrid 
							$('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
							$('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
							$('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
							$('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
							$('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
							$('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
							$('#checkNo').textbox('setValue','');
							$('#sum1').numberbox('setValue', '');
							$('#sum2').numberbox('setValue', '');
							$('#sum3').numberbox('setValue', '');  
							$("#addbtn").linkbutton("enable");
							$("#openbtn").linkbutton("enable");
							$("#cancelbtn").linkbutton("disable");
							$("#savebtn").linkbutton("disable");
							$("#printbtn").linkbutton("disable");
							$("#submitbtn").linkbutton("disable");
							$("#removebtn").linkbutton("disable");
							}else
							{  
							$.messager.alert('提示:',data.msg,'info');
							 return;  
							 } 
							}
							})
				/* 	$.ajax({  
			            url: '/storeCheck/delete',  
			            type: "post",  
			            dataType: 'json',
			            contentType:"application/json;charset=UTF-8",
			            data: {checkNo:$('#checkNo').val()},
			            success: function (data) { 
			            	alert(data.code);
			                if(data.code == 200){  			               	 
			               	 var interval;  
			    				 var time=1000;  
			    				 var x=2;    //设置时间2s
			    				 jQuery.messager.alert('提示:','封存'+data.msg+'!','info',function(){});     				
			    				 interval=setInterval(fun,time);  
			    				        function fun(){  
			    				      --x;  
			    				      if(x==0){  
			    				          clearInterval(interval);  
			    				  $(".messager-body").window('close');    
			    				       }  
			    				}; 
			    				$('#mytable').datagrid('loadData', { total: 0, rows: [] });//清空下方DateGrid 
			    				$('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
			    				$('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
			    				$('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
			    				$('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
			    				$('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
			    				$('#mytable').datagrid('appendRow',{orderNo:'',itemName: '',spec: '',unit: '',vendor: '',batchno:'',price: '',inPrice: '',validDate: '',drugId:''});
								$('#checkNo').textbox('setValue','');
								$('#sum1').numberbox('setValue', '');
								$('#sum2').numberbox('setValue', '');
								$('#sum3').numberbox('setValue', '');  
								$("#addbtn").linkbutton("enable");
								$("#openbtn").linkbutton("enable");
								$("#cancelbtn").linkbutton("disable");
								$("#savebtn").linkbutton("disable");
								$("#printbtn").linkbutton("disable");
								$("#submitbtn").linkbutton("disable");
								$("#removebtn").linkbutton("disable");
			                   }else
			                   {  
			                   	//$.messager.alert(data.msg);  
			                   	$.messager.alert('提示:',data.msg,'info');
			                       return;  
			                   }  
			                
			            }  
			          }); */
				}
			});
 
	}
	function doopen(){
		 var billNo=$('#billNos').val();
		 $('#mytable').datagrid('loadData', { total: 0, rows: [] });//清空下方DateGrid 
			$.ajax({
				type : 'POST',
				url : "/breakage/getDetailData",
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

	</script> 
	</body>
</html>


