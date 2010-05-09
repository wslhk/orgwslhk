package org.wslhk.util;
import java.security.*;
import java.text.SimpleDateFormat;
import java.util.*;



public class Str {//字符串转换 

	/**
	 * 字符串编码传唤gb2312
	 */
	static public String getBytesGb2312(String ostr){
		String newstr=null;
		try{
		newstr=new String(ostr.getBytes("ISO-8859-1"), "gb2312");
		}catch(Exception e){System.out.print("Str.java:"+e);e.printStackTrace();newstr="error";}
		return newstr;
	}
	/**
	 * 字符串编码转换 u8
	 * @param ostr
	 * @return
	 */
	static public String getBytesUtf8(String ostr){
		String newstr=null;
		try{
			if(ostr!=null){
		newstr=new String(ostr.getBytes("ISO-8859-1"), "utf-8");
			}else{
				newstr=ostr;
			}
		}catch(Exception e){System.out.print("Str.java:"+e);e.printStackTrace();newstr="error";}
		return newstr;
	}


	/**
	 * 时间补０
	 * @param value
	 * @return
	 */
	public static String addzero(int value){
		
		String result=""+value;
		if(value<10){
			result="0"+result;
		}		
		return result;
	}
	/**
	 * 时间补０
	 * @param value
	 * @return
	 */
	public static String addzero(String value){
		
		if(value!=null){
			try{
			value=Str.addzero(Integer.parseInt(value.trim()));//.addzore(Integer.parseInt(value));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return value;
	}

	/**
	 * 时间转换字符串//x年x月x日
	 * @param date1
	 * @return
	 */
	public static String DateToCnFormat_YMD(Date date1){//时间转换字符串//x年x月x日
		String result=null;
		if(date1!=null){
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(date1);
			result=DateToCnFormat_YMD(calendar1);
		}
		
		return result;
	}
	
	/**
	 * 时间转换字符串//x年x月x日
	 * @param calendar1
	 * @return
	 */
	public static String DateToCnFormat_YMD(Calendar calendar1){//时间转换字符串//x年x月x日
		String result=null;
		if(calendar1!=null){
			result=""+calendar1.get(calendar1.YEAR)+"年";
			result=result+(calendar1.get(calendar1.MONTH)+1)+"月";
			result=result+calendar1.get(calendar1.DAY_OF_MONTH)+"日";
		}
		
		return result;
	}
	
	/**
	 * 时间转换字符串//x年x月x日
	 * @param calendar1
	 * @return
	 */
	public static String DateToEnFormat_YMD(Calendar calendar1){//时间转换字符串//x年x月x日
		String result=null;
		if(calendar1!=null){
			result=""+calendar1.get(calendar1.YEAR)+"-";
			result=result+(calendar1.get(calendar1.MONTH)+1)+"-";
			result=result+calendar1.get(calendar1.DAY_OF_MONTH)+"";
		}
		
		return result;
	}
	/**
	 * xxxx年xx月
	 * @param date1
	 * @return
	 */
	public static String DateToCnFormat_YM(Calendar date1){//xxxx年xx月
		String result=null;
		if(date1!=null){
			Calendar calendar1 = Calendar.getInstance();
			calendar1=date1;
			result=""+calendar1.get(calendar1.YEAR)+"年 ";
			result=result+(calendar1.get(calendar1.MONTH)+1)+"月";
		}
		
		return result;
	}
	/**
	 * 时间转换字符串//x年x月x日
	 */
	public static String DateToCnFormat_D(Date date1){//时间转换字符串//x年x月x日
		String result=null;
		if(date1!=null){
			//System.out.println(date1);
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(date1);
			result=""+calendar1.get(calendar1.DAY_OF_MONTH)+"日";
		}
		
		return result;
	}
	
	/**
	 * 时间转换字符串//xxxx-xx-xx xx:xx:xx
	 * @param date1
	 * @return
	 */
	public static String DateToEnFormat_YMDHMS(Date date1){//时间转换字符串//xxxx-xx-xx xx:xx:xx
		String result=null;
		if(date1!=null){
			//System.out.println(date1);
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(date1);
			result=""+calendar1.get(calendar1.YEAR)+"-";
			result=result+addzero(calendar1.get(calendar1.MONTH)+1)+"-";
			result=result+addzero(calendar1.get(calendar1.DAY_OF_MONTH))+" ";
			result=result+addzero(calendar1.get(calendar1.HOUR_OF_DAY))+":";
			result=result+addzero(calendar1.get(calendar1.MINUTE))+":";
			result=result+addzero(calendar1.get(calendar1.SECOND))+"";
			//System.out.println(result);
		}
		
		return result;
	}
	
	/**
	 * 时间转换字符串//0000000000000
	 * @param date1
	 * @return
	 */
	public static String DateToIntFormat_YMDHMS(Date date1){//时间转换字符串//0000000000000
		String result=null;
		if(date1!=null){
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(date1);
			result=""+calendar1.get(calendar1.YEAR)+"";
			result=result+addzero(calendar1.get(calendar1.MONTH)+1)+"";
			result=result+addzero(calendar1.get(calendar1.DAY_OF_MONTH))+"";
			result=result+addzero(calendar1.get(calendar1.HOUR_OF_DAY))+"";
			result=result+addzero(calendar1.get(calendar1.MINUTE))+"";
			result=result+addzero(calendar1.get(calendar1.SECOND))+"";
		}
		
		return result;
	}
	
	/**
	 * 字符串转时间
	 * @param datestr
	 * @return
	 */
	public static Date StrToDate(String datestr){
		java.util.Date result=null;
		if(datestr!=null){
		try{	
		SimpleDateFormat localTime   =    new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result=localTime.parse(datestr);
		}catch(Exception e){
			e.printStackTrace();
		}
		}
		return result;
	}

/**
 * 字符串转时间
 * @param datestr
 * @param format 比如 "yyyy-MM-dd HH:mm:ss"
 * @return
 */
	public static Date StrToDate(String datestr,String format){
		java.util.Date result=null;
		if(datestr!=null){
		try{	
		SimpleDateFormat localTime   =    new   SimpleDateFormat(format);
		result=localTime.parse(datestr);
		}catch(Exception e){
			e.printStackTrace();
		}
		}
		return result;
	}
//	public static String ArrToStr(String[] arr){
//		String result=null;
//			for(int i=0;i<arr.length;i++){
//				if(result==null){
//					result=arr[i];
//				}else{
//					result=result+","+arr[i];
//				}
//			}
//		return result;
//	}
//	/**
//	 * 数组转字符串,中间用','填充
//	 * @param arr
//	 * @return
//	 */
//	public static String ArrToStr(Integer[] arr){
//		String result=null;
//			for(int i=0;i<arr.length;i++){
//				if(result==null){
//					result=arr[i]+"";
//				}else{
//					result=result+","+arr[i]+"";
//				}
//			}
//		return result;
//	}
	
	/**
	 * 16进制随机数
	 * @return
	 */
	public static String getRndHex(){
		return Integer.toHexString(new Random().nextInt())+Integer.toHexString(new Random().nextInt()); 
	}
	/**
	 * enurlcode
	 * @param str
	 * @return
	 */
	public static String uriencode(String str){
		String result=null;
		if(str!=null){
		try{	
			result=java.net.URLEncoder.encode(str,"utf-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		}
		return result;
	}
	/**
	 * uridecode
	 * @param str
	 * @return
	 */
	public static String uridecode(String str){
		String result=null;
		if(str!=null){
		try{	
			result=java.net.URLDecoder.decode(str,"utf-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		}
		return result;
	}

	/**
	 * html 基本过滤
	 * @param str
	 * @return
	 */
	 public static String HTML2TEXT(String str){
		  str=str.replace("&","&amp;");
		  str=str.replace("<","&lt;");
		  str=str.replace(">","&gt;");
		  str=str.replace("\"","&quot;"); 
		  str=str.replace("insert","ihsert"); 
		  str=str.replace("updata","updata"); 
		  str=str.replace("delete","de1ete"); 
		  str=str.replace("-","——");
		  //str=str.replace(";","");
		  //str=str.replace("or","");
		  return str;
	  }
	 /**
	  * 截取字符串并且莫非添加'...'
	  * @param str
	  * @param length
	  * @return
	  */
	 public static String Sub(String str,int length){
		 try{
		 return Sub(str,length,"...");
		 }catch(Exception e){
			 return str;
		 }
	 }
	 /**
	  * 截取字符串
	  * @param str 原字符
	  * @param length 长度
	  * @param overstr 末尾添加内容,比如'...'
	  * @return
	  */
	 public static String Sub(String str,int length,String overstr){
		 if(str!=null){
		 if(str.length()<length){
			 return str; 
		 }else{
			 return str.substring(0, length)+overstr;
		 }
		 }else{
			 return null;
		 }
		 
	 }
	/**
	 * 数组去重复
	 */
	 public static String[] Distinct(String str[]){
		 List<String> list = new LinkedList<String>();
	        for(int i = 0; i < str.length; i++) {
	            if(!list.contains(str[i])) {
	                list.add(str[i]);
	            }
	        }
	        return (String[])list.toArray(new String[list.size()]);
		 
	 }

}
