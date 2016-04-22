    

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
		<script type="text/javascript" src="/staticPublic/locale/easyui-lang-zh_CN.js"></script> 
		<script type="text/javascript">
		String.prototype.trim=function()
		{
	    	return this.replace(/(^\s*)|(\s*$)/g,'');
		}
		
		var ai = "";
		var fieldName = "";//点击的列field
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
                    //console.info($(ed.target).attr('class'));
                    if (ed){
                        if ($(ed.target).hasClass('textbox-f')){
                        	$(ed.target).textbox('textbox').focus();//获得焦点
                            $(ed.target).next("span").children().first().one('blur', function (e) {
                            	//alert("aaa");
                            	//console.info($(this).val().trim());
                            	var amount = 0;
                            	var price1 = 0;
                            	var sum1 = 0;
                            	var eds = $('#mytable').datagrid('getRows');
                            	if(param.field == "amount")//列名等于名称
                                {
                            		amount = $(this).val().trim();
                            		price1 = eds[param.index]['price1'];
                            		//console.info(amount*price1);
                            		$('#mytable').datagrid('updateRow',{
       									index: param.index,
       									row: {
       										amount:amount,
       										total1: (price1*amount).toFixed(3)
       									}
       								});
                                }
                            	if(param.field == "price1")
                            	{
                            		price1 = $(this).val().trim();
                            		amount = eds[param.index]['amount'];
                            		$('#mytable').datagrid('updateRow',{
       									index: param.index,
       									row: {
       										price1:price1,
       										total1: (price1*amount).toFixed(3)
       									}
       								});
                            	}
                            	for(var i = 0;i<eds.length;i++)
                            	{
                            		if(eds[i]['total1'] != "" && eds[i]['total1'] != null)
                            		{
                            			sum1 += parseFloat(eds[i]['total1']);
                            		}
                            	}
                            	//console.info(sum1);
                            	$('#sum1').numberbox('setValue', sum1);
                            });
                            /*
                            $(ed.target).textbox('textbox').bind('blur',function(){
                            	var amount = 0;
                            	var price1 = 0;
                            	var eds = $('#mytable').datagrid('getRows');
                            	if(param.field == "amount")//列名等于名称
                                {
                            		amount = $(this).val().trim();
                            		price1 = eds[param.index]['price1'];
                            		//console.info(amount*price1);
                            		$('#mytable').datagrid('updateRow',{
       									index: param.index,
       									row: {
       										amount:amount,
       										total1: (price1*amount).toFixed(2)
       									}
       								});
                                }
                            	if(param.field == "price1")
                            	{
                            		price1 = $(this).val().trim();
                            		amount = eds[param.index]['amount'];
                            		$('#mytable').datagrid('updateRow',{
       									index: param.index,
       									row: {
       										price1:price1,
       										total1: (price1*amount).toFixed(2)
       									}
       								});
                            	}
                            });*/
                        } else {
                           $(ed.target).focus();
                           if(param.field == "itemName")//列名等于名称
                           {
                        	   $(ed.target).bind('keyup', function (event) {
                        		   if(event.keyCode == 38 || event.keyCode == 40 )
                       				{
                       					$(this).blur();
                       					event.stopPropagation();//通过使用 stopPropagation() 方法只阻止一个事件起泡。 
                       					return false;//通过返回false来取消默认的行为并阻止事件起泡
                       				}
                        		   
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
                       						url : "/inStorage/getEnabledDrugList",
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
                       	 								var _tr = $("<tr><td class='nametd' width='300px' height='22px' name ='matname' title='"+data.data[i].itemName+"'><input style='padding-right: 10px;' type='checkbox' name='cr01' id='cr01' value='"+data.data[i].itemName+"'/>"+data.data[i].itemName
                       	 									+"</td><td width='70px' height='22px' name='spec' title='"+data.data[i].spec+"'>"+data.data[i].spec
                       	 									+"</td><td width='30px' height='22px' name='unitname' title='"+data.data[i].unit+"'>"+data.data[i].unit
                       	 									+"</td><td width='30px' height='22px' name='vendor' style='display:none' title='"+data.data[i].vendor+"'>"+data.data[i].vendor
                       	 									+"</td><td width='30px' height='22px' name='drugid' style='display:none' title='"+data.data[i].id+"'>"+data.data[i].id
                       	 									+"</td></tr>");
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
                       	  									var vendor = tablerow.find("[name='vendor']").text();
                       	  									var drugid = tablerow.find("[name='drugid']").text();
                       	  									selectedData.push({Name:matname,Spec:spec,UnitName:unitname,Vendor:vendor,Drugid:drugid});
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
                       	  									var vendor = tablerow.find("[name='vendor']").text();
                   	  										var drugid = tablerow.find("[name='drugid']").text();
                       	  									selectedData.push({Name:matname,Spec:spec,UnitName:unitname,Vendor:vendor,Drugid:drugid});
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
                       	    							if(rows[maincurRow].xh != "" && rows[maincurRow].xh != null)//序号不为空 表示要修改
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
	                           	    								vendor:selectedData[i]['Vendore'],
	                           	    								drugid:selectedData[i]['Drugid'],
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
	                           	    									xh:'',
	                           	    									itemName: '',
	                           	    									spec: '',
	                           	    									unit: '',
	                           	    									vendor:'',
	                           	    									drugid:''
	                           	    									});
	                           	    							}
	                       	    								$('#mytable').datagrid('updateRow',{
	                           	    								index: i+rowindex,
	                           	    								row: {
	                           	    									xh:1+i+rowindex,
	                           	    									itemName: selectedData[i]['Name'],
	                           	    									spec: selectedData[i]['Spec'],
	                           	    									unit: selectedData[i]['UnitName'],
	                           	    									vendor:selectedData[i]['Vendore'],
	                           	    									drugid:selectedData[i]['Drugid'],
	                           	    								}
	                           	    							});
	                           	 								inumber = i+1+rowindex;
                       	    								}
                       	    							}
                       	    							if(inumber == rows.length)//有数据的行和添加行后的页面的行相等就在添加一行
                       	    							{
                       	    								$('#mytable').datagrid('appendRow',{
                   	    										xh:'',
                   	    										itemName: '',
                   	    										spec: '',
                   	    										unit: '',
                   	    										vendor:'',
                   	    										drugid:''
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
                    	fieldName = field;
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
                
            }
			,keyCtr : function (jq) {
				return jq.each(function () { 
					var grid = $(this); 
					grid.datagrid('getPanel').panel('panel').attr('tabindex', 1).bind('keydown', function (event) { 
					switch (event.keyCode) { 
					case 9: 
						//alert("9999");
						
					break;
					case 13:
						//alert("13");
						//keyTab();
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
					//console.info("value"+value);
					//console.info("value.length"+value.length);
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
				window.event.returnValue = false;//阻止事件的默认行为
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
		    	window.event.returnValue = false;//阻止事件的默认行为
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
				//alert($("#selectItem").is(':hidden'));
				//alert(maincurRow);
				window.event.returnValue = false;//阻止事件的默认行为
				if($("#selectItem").is(':hidden'))//true 隐藏
				{
					//alert('ent');
					keyTab();
				}
				else
				{
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
						var vendor = $("#table1 tr:eq("+rowNo+")").find("[name='vendor']").text().trim();
						var drugid = $("#table1 tr:eq("+rowNo+")").find("[name='drugid']").text().trim();
						if(matname != null && matname != "")
						{
							selectedData.push({Name:matname,Spec:spec,UnitName:unitname,Vendor:vendor,Drugid:drugid});
						}
					}
					
					//alert(matname+","+spec+","+unitname);
					//alert("selectedData.length:::"+selectedData.length);
					//alert(clickIndex.length);
					if(selectedData.length > 0)
					{
						if(rows[maincurRow].xh != "" && rows[maincurRow].xh != null)//序号不为空 表示要修改
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
	   								vendor:selectedData[i]['Vendore'],
	   								drugid:selectedData[i]['Drugid'],
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
	   									xh:'',
	   									itemName: '',
	   									spec: '',
	   									unit: '',
	   									vendor:'',
	   									drugid:''
	   									});
	   							}
									$('#mytable').datagrid('updateRow',{
	   								index: i+rowindex,
	   								row: {
	   									xh:1+i+rowindex,
	   									itemName: selectedData[i]['Name'],
	   									spec: selectedData[i]['Spec'],
	   									unit: selectedData[i]['UnitName'],
	   									vendor:selectedData[i]['Vendore'],
	   									drugid:selectedData[i]['Drugid'],
	   								}
	   							});
								inumber = i+1+rowindex;
							}
						}
						
				    	if(inumber == rows.length)//有数据的行和添加行后的页面的行相等就在添加一行
				    	{
				 			$('#mytable').datagrid('appendRow',{
									xh:'',
									itemName: '',
									spec: '',
									unit: '',
									vendor:'',
									drugid:''
								});
				    	}
			    	}
					
			    	selectedData.splice(0,selectedData.length);//清空
			    	rowNo = 0;
			    	clickIndex.splice(0,clickIndex.length);
			    	$("#selectItem").hide();
			    	keyTab();
				}
		 		
			}
			//esc事件
			if(window.event.keyCode== 27)
			{
				rowNo = 0;
				selectedData.splice(0,selectedData.length);//清空
				clickIndex.splice(0,clickIndex.length);
				$("#selectItem").hide();
			}
			//Tab键盘
			if(window.event.keyCode== 9)
			{
				keyTab();
			}
		};
		
		function keyTab()
		{
			var array = ['invoiceNo','itemName','amount','price1'];
			for(var i = 0;i<array.length;i++)
			{
				if(fieldName == array[i])
				{
					ai = i+1;
				}
			}
			//alert(ai);
			console.info(fieldName);
			console.info(ai);
			if(ai%(array.length) == 0)
			 {
				 //alert("aa");
				 maincurRow +=1;
			 }
			var dg = $('#mytable');
            var opts = dg.datagrid('options');
			opts.onClickCell(maincurRow, array[ai%(array.length)]);
			opts.onClickCell(maincurRow, array[ai%(array.length)]);//再点击一次，获得焦点
			if(array[ai%(array.length)].trim() == "amount")//输入框在itemName时,模糊查询药品名称,按enter后(即按下enter 后为amount时，再点击一次，获得焦点 ).
			{
				opts.onClickCell(maincurRow, array[ai%(array.length)]);
			}
			 /*
			//alert(opts.editIndex);
			console.info(opts.editIndex);
			 if (opts.editIndex != undefined){//editIndex为编辑的索引值,这里仅为引用
                 if ($('#mytable').datagrid('validateRow', opts.editIndex)){
                	 $('#mytable').datagrid('endEdit', opts.editIndex);
                     //opts.editIndex = undefined;
                 } else {
                     return;
                 }
             }
			 //$('#mytable').datagrid('endEdit', opts.editIndex);
			 var fields = $('#mytable').datagrid('getColumnFields',true).concat($('#mytable').datagrid('getColumnFields'));
			 for(var i=0; i<fields.length; i++)
			 {
				var col = $('#mytable').datagrid('getColumnOption', fields[i]);//每列的field 
				col.editor1 = col.editor;//每列的editor
				if (fields[i] != array[ai%(array.length)])
				{
					col.editor = null;
				}
			 }
			 $('#mytable').datagrid('beginEdit', opts.editIndex);
             var ed = $('#mytable').datagrid('getEditor', {index:opts.editIndex,field:array[ai%(array.length)]});
             //alert(JSON.stringify($(ed.target)));
             console.info(opts.editIndex+','+array[ai%(array.length)]);
             console.info($(ed.target).attr('class'));
             if (ed)
             {
                 if ($(ed.target).hasClass('textbox-f'))
                 {
                     $(ed.target).textbox('textbox').focus();//获得焦点
                 }
                 else 
                 {
                	$(ed.target).focus();
                 }
                 
             }
             
			 for(var i=0; i<fields.length; i++)
			 {
			 	var col = $('#mytable').datagrid('getColumnOption', fields[i]);
				col.editor = col.editor1;
			 }
			 */
		}
		
		function save(){
			var detailList = {};
			var rows = $('#mytable').datagrid('getRows');  
			var typeData =$('#typeData').combobox('getText').trim();
			if(typeData == "" || typeData == null)
			{
				$.messager.alert('提示:','请选择供应商！','info');
				return false;
			}
			//alert(typeData);
			var sum1 = $('#sum1').val();
			var sum2 = $('#sum2').val();
			//alert(sum1);
			//alert(sum2);
			if($('#billNo').val() != "" && $('#billNo').val() != null)
			{
				$.messager.alert('提示:','单据已经保存,不能重复保存！','info');
				return false;
			}
			var obj = {};
			 obj.billType = "入库";
			 obj.typeData = typeData;
			 obj.sum1 = sum1;
			 obj.sum2 = sum2;
			 var newArray = [];
			 var json = '';
			 //var entities = "";
			 for(i = 0;i < rows.length;i++)  
			 {  
				if(rows[i]['itemName'].trim() != "" && rows[i]['itemName'].trim() != null)
				{
					//entities = entities  + JSON.stringify(rows[i]);
					if(parseInt(rows[i].amount.trim()) == 0 || rows[i].amount.trim() == "" || rows[i].amount.trim() == null)
					{
						$.messager.alert('提示:',"第"+(i+1)+"行数量异常！",'info');
						return false;
					}
					var objes = {};
			    	 objes.orderNo = rows[i].xh;
			    	 objes.invoiceNo = rows[i].invoiceNo;
			    	 objes.drugId = rows[i].drugid;
			    	 objes.batchNo = rows[i].batchno;
			    	 objes.amount = rows[i].amount;
			    	 objes.price1 = rows[i].price1;
			    	 objes.price2 = rows[i].price2;
			    	 objes.validDate = rows[i].validDate;
			    	 //alert(rows[i].validDate);
			    	 //alert(typeof(rows[i].validDate));
				     newArray.push(objes);
				}
			 } 
			 if(newArray.length ==0)
			 {
				 $.messager.alert('提示:',"请填写入库明细！",'info');
				 return false;
			 }
			 obj.detailList = newArray;
			 //alert(entities);
			 $.ajax({  
	             url: '/inStorage/save',  
	             type: "post",  
	             dataType: 'json',
	             contentType:"application/json;charset=UTF-8",
	             data: JSON.stringify(obj),
	             success: function (data) {  
	                 if(data.code == 200){  
	                	 $('#billNo').val(data.data);
	                	 
	                	 //$("#saveBtn").linkbutton("disable");
	                	 //$("#sum1").numberbox("disable");
	                	 //$("#sum2").numberbox("disable");
	                	 //$("#typeData").combobox("disable");
	                	 //$('#mytable').datagrid('cancelEdit', 0);
	                	 $.messager.show({
	                         title:'提示',
	                         msg:'保存'+data.msg+'!',
	                         showType:'show',
	                         style:{
	                        	    left:'', // 与左边界的距离
	                        	    top:0 ,// 与顶部的距离
	                        	    right : 0,
	                        	}
	                     	});
	                    }else
	                    {  
	                    	$.messager.alert(data.msg);  
	                        return;  
	                    }  
	             }  
	           });  
	    }
		
		function submit()
		{
			var detailList = {};
			var rows = $('#mytable').datagrid('getRows');  
			var typeData =$('#typeData').combobox('getText').trim();
			if(typeData == "" || typeData == null)
			{
				$.messager.alert('提示:','请选择供应商！','info');
				return false;
			}
			//alert(typeData);
			var sum1 = $('#sum1').val();
			var sum2 = $('#sum2').val();
			var obj = {};
			 obj.billType = "入库";
			 obj.typeData = typeData;
			 obj.sum1 = sum1;
			 obj.sum2 = sum2;
			 var newArray = [];
			 var json = '';
			 //var entities = "";
			 for(i = 0;i < rows.length;i++)  
			 {  
				if(rows[i]['itemName'].trim() != "" && rows[i]['itemName'].trim() != null)
				{
					//entities = entities  + JSON.stringify(rows[i]);
					if(parseInt(rows[i].amount.trim()) == 0 || rows[i].amount.trim() == "" || rows[i].amount.trim() == null)
					{
						$.messager.alert('提示:',"第"+(i+1)+"行数量异常！",'info');
						return false;
					}
					var objes = {};
			    	 objes.orderNo = rows[i].xh;
			    	 objes.invoiceNo = rows[i].invoiceNo;
			    	 objes.drugId = rows[i].drugid;
			    	 objes.batchNo = rows[i].batchno;
			    	 objes.amount = rows[i].amount;
			    	 objes.price1 = rows[i].price1;
			    	 objes.price2 = rows[i].price2;
			    	 objes.validDate = rows[i].validDate;
				     newArray.push(objes);
				}
			 } 
			 if(newArray.length ==0)
			 {
				 $.messager.alert('提示:',"请填写入库明细！",'info');
				 return false;
			 }
			 obj.detailList = newArray;
			 $.ajax({  
	             url: '/inStorage/submit',  
	             type: "post",  
	             dataType: 'json',
	             contentType:"application/json;charset=UTF-8",
	             data: JSON.stringify(obj),
	             success: function (data) {  
	                 if(data.code == 200){  
	                	 $('#billNo').val();
	                	 $("#sum1").numberbox("disable");
	                	 $("#sum2").numberbox("disable");
	                	 $("#typeData").combobox("disable");
	                	 $('#mytable').datagrid('loadData', { total: 0, rows: [] });//清空下方DateGrid 
	     				$('#mytable').datagrid('appendRow',{
	     	 					xh:'',
	     	 					invoiceNo:'',
	     						itemName: '',
	     						spec: '',
	     						vendor: '',
	     						unit: '',
	     						amount:'',
	     						price1:'',
	     						total1:'',
	     						price2:'',
	     						total2:'',
	     						batchNo:'',
	     						validDate: '',
	     						drugId:''
	     					});
	     				$('#mytable').datagrid('appendRow',{
	     					xh:'',
     	 					invoiceNo:'',
     						itemName: '',
     						spec: '',
     						vendor: '',
     						unit: '',
     						amount:'',
     						price1:'',
     						total1:'',
     						price2:'',
     						total2:'',
     						batchNo:'',
     						validDate: '',
     						drugId:''
	     					});
	     				$('#mytable').datagrid('appendRow',{
	     					xh:'',
     	 					invoiceNo:'',
     						itemName: '',
     						spec: '',
     						vendor: '',
     						unit: '',
     						amount:'',
     						price1:'',
     						total1:'',
     						price2:'',
     						total2:'',
     						batchNo:'',
     						validDate: '',
     						drugId:''
	     					});
	     				$('#mytable').datagrid('appendRow',{
	     					xh:'',
     	 					invoiceNo:'',
     						itemName: '',
     						spec: '',
     						vendor: '',
     						unit: '',
     						amount:'',
     						price1:'',
     						total1:'',
     						price2:'',
     						total2:'',
     						batchNo:'',
     						validDate: '',
     						drugId:''
	     					});
	     				$('#mytable').datagrid('appendRow',{
	     					xh:'',
     	 					invoiceNo:'',
     						itemName: '',
     						spec: '',
     						vendor: '',
     						unit: '',
     						amount:'',
     						price1:'',
     						total1:'',
     						price2:'',
     						total2:'',
     						batchNo:'',
     						validDate: '',
     						drugId:''
	     					});
	     				$('#mytable').datagrid('appendRow',{
	     					xh:'',
     	 					invoiceNo:'',
     						itemName: '',
     						spec: '',
     						vendor: '',
     						unit: '',
     						amount:'',
     						price1:'',
     						total1:'',
     						price2:'',
     						total2:'',
     						batchNo:'',
     						validDate: '',
     						drugId:''
	     					});
	                	 $.messager.show({
	                         title:'提示',
	                         msg:'提交'+data.msg+'!',
	                         showType:'show',
	                         style:{
	                        	    left:'', // 与左边界的距离
	                        	    top:0 ,// 与顶部的距离
	                        	    right : 0,
	                        	}
	                     	});
	                    }else
	                    {  
	                    	$.messager.alert(data.msg);  
	                        return;  
	                    }  
	             }  
	           });  
		}
		
		$(function(){
			$('#mytable').datagrid().datagrid('enableCellEditing');
			$('#mytable').datagrid().datagrid('keyCtr');
			$('#mytable').datagrid('options').onSelect = function(index, rowData){selectRow = index;}
			$.ajax({
					type : "post",
    				url : "/inStorage/getEnabledDicProviderList",
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
    							/*
    							for(var i = 0;i<newData.length;i++)
								{
									alert(newData[i].providerName);
								}*/
    							return newData;
    						}
    						});
    				}
				});
			
		})
		var toolbar = [{
            text:'插行',
            iconCls:'icon-add',
            handler:function(){
            	alert(selectRow);
            	if(selectRow >=0)
            	{
            		$('#mytable').datagrid('insertRow',{
                		index: selectRow,
                		row: {
                			xh:'ch',//标准序号不为空
        					itemName: '',
        					spec: '',
        					unit: '',
        					vendor:'',
        					drugid:''
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
            	alert(selectRow);
            	$('#mytable').datagrid('deleteRow',selectRow);
            }
        }];
		</script>
<style type="text/css">
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
</style>
	</head>
	<body onkeydown="keyCheck()">
	<div style="margin:20px 0;">
	</div>
	<input id="billNo" type="hidden" />
	<font style="font-size: 12px;font-family: Microsoft YaHei;">供应商：</font><input id="typeData" class="easyui-combobox" style="width:230px" data-options="
	"></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<font style="font-size: 12px;font-family: Microsoft YaHei;">进价总金额：</font><input id="sum1" class="easyui-numberbox" data-options="disabled:true,precision:3" style="width:200px;height:22px"></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<font style="font-size: 12px;font-family: Microsoft YaHei;">零售价总金额：</font><input id="sum2" class="easyui-numberbox" data-options="disabled:true,precision:3" style="width:200px;;height:22px"></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="#" id="modifyBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:80px">打开</a>
    <a href="#" id="saveBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px" onclick="save()">保存</a>
    <a href="#" id="printBtn" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:80px">打印</a>
    <a href="#" id="submitBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="width:80px" onclick="submit()">提交</a>

	<div id="tb" style="padding:10px 0px;">
	<table class="easyui-datagrid" style="width:100%;height:600px;" id="mytable"
			data-options="rownumbers:true,singleSelect:true,collapsible:true,method:'get',toolbar:toolbar">
		<thead>
			<tr>
				<th data-options="field:'xh',width:80,align:'center',hidden:'true'">序号</th>
				<th data-options="field:'invoiceNo',width:200,align:'center',editor:'text'">发票号</th>
				<th data-options="field:'itemName',width:200,align:'center',editor:'text'" >名称</th>
				<th data-options="field:'spec',width:150,align:'center'">规格</th>
				<th data-options="field:'vendor',width:200,align:'center'">厂家</th>
				<th data-options="field:'amount',width:100,align:'center',editor:{type:'numberbox',options:{precision:2,validType:'notLing[0]'}}">数量</th>
				<th data-options="field:'unit',width:80,align:'center'">单位</th>
				<th data-options="field:'price1',width:100,align:'center',editor:{type:'numberbox',options:{precision:3}}">进价</th>
				<th data-options="field:'total1',width:100,align:'center'">进价金额</th>
				<th data-options="field:'price2',width:100,align:'center'">零售价</th>
				<th data-options="field:'total2',width:100,align:'center'">零售价金额</th>
				<th data-options="field:'batchNo',width:100,align:'center'">批号</th>
				<th data-options="field:'validDate',width:100,align:'center',editor:{ type:'datebox'}">有效期</th>
				<th data-options="field:'drugid',hidden:'true'">drugid</th>
			</tr>
		</thead>
		<tbody id="tbody">
			<tr>
				<td></td>
				<td>zzz</td>
				<td></td>
				<td>xxx</td>
				<td>cccc</td>
				<td ></td>
				<td>eeee</td>
				<td ></td>
				<td >jjjjj</td>
				<td></td>
				<td></td>
				<td>mmm</td>
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
	<div id="selectItem" class="selectItemhidden" >
		<div id="selectItemCount" class="selectItemcont">
			<div id="selectSub">
				<table width="100%" height="100%" id="table1" class="divtab">
					<thead>
						<tr style="width: 100%;">
							<th width="63%" height="22px;">名称</th>
							<th width="22%" height="22px;">规格</th>
							<th width="15%" height="22px;">单位</th>
						</tr>
					</thead>
					<tbody id="table1_tbody">
						
					</tbody>
				</table>
			</div>
		</div>
	</div> 
	</body>
</html>


