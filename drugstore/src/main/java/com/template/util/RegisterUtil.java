package com.template.util;

import java.util.prefs.Preferences;

/**
 * 注册表工具类
 * @Description: 注册表工具类
 * @author army.liu
 */
public class RegisterUtil {

	/**
	  * 获取注册表中key对应的值
	  * @Description: 获取注册表中key对应的值
	  * @author army.liu
	  * @param  
	  * @return 字符串
	  * @throws
	  */
	public static String getValue(String key){
		System.out.println(Preferences.systemRoot().absolutePath());
		System.out.println(Preferences.systemRoot().parent().absolutePath());
		
		return null;
	}
	
	//写注册表
		public void writeValue(String key,String value) {  
	    	//HKEY_LOCAL_MACHINE\Software\Wow6432Node\JavaSoft\prefs下写入注册表值.  
	        Preferences pre = Preferences.systemRoot().node("/jshp/hospital"); 
	        pre.put(key, value);
	    }
		
		//读取注册表内容
		public String readValue(String key) {  
	   	 	//HKEY_LOCAL_MACHINE\Software\Wow6432Node\JavaSoft\prefs下读取注册表值.  
	   	 	Preferences pre = Preferences.systemRoot().node("/jshp/hospital");  
	   	 	String value=pre.get(key, "");
	   	 	
	   	 	//判断如果读取不到数据，则设置一个默认的并取出默认值
	   	 	if(value==""){
	   	 		value=this.defaultValue(pre, key);
	   	 	}
	   	 	return value;
	   }
		
		//如果没有值，则取初始的默认值
		public String defaultValue(Preferences pre,String key) { 
			//返回值
			String value="";
			
			//当前药品名称输入法，默认是"名称100"
			if(key=="inputmethod"){
				value="100";
				pre.put(key, value);
			}else if(key=="defaultbillprint"){//打印机，默认是"票据打印机bill"
				value="bill";
				pre.put(key, value);
			}else if(key=="checkpapersizex"){//盘点单纸张宽度（默认A4，2100mm）
				value="2100";
				pre.put(key, value);
			}else if(key=="checkpapersizey"){//盘点单纸张高度（默认A4，2970mm）
				value="2970";
				pre.put(key, value);
			}else{
				 value="";
			}
			
	   	 	return value;
	   }
		
		public static void main(String[] args) {  
	        RegisterUtil reg = new RegisterUtil();  
	        
	        reg.writeValue("lastuser", "aaa");
	        String value=reg.readValue("lastuser");
	        if(value==""){
	        	 System.out.println("value为空");
	        }else{
	        	System.out.println("value=="+value);
	        }
	        
	    } 
}
