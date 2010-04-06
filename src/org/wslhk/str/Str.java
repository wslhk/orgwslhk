package org.wslhk.str;
import java.security.*;
import java.text.SimpleDateFormat;
import java.util.*;



public class Str {//字符串转换 

	static public String getBytesGb2312(String ostr){
		String newstr=null;
		try{
		newstr=new String(ostr.getBytes("ISO-8859-1"), "gb2312");
		}catch(Exception e){System.out.print("Str.java:"+e);e.printStackTrace();newstr="error";}
		return newstr;
	}
	
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
	/*
	static public String GbtoUtf8Str(String ostr){
		String newstr=null;
		try{
		newstr=new String(ostr.getBytes("gb2312"), "utf-8");
		}catch(Exception e){System.out.print("Str.java:"+e);e.printStackTrace();newstr="error";}
		return newstr;
	}
	static public String Utf8toGb(String ostr){
		String newstr=null;
		if(ostr!=null){
		try{
		newstr=new String(ostr.getBytes("utf-8"), "gb2312");
		}catch(Exception e){System.out.print("Str.java:"+e);e.printStackTrace();newstr="error";}
		}
		return newstr;
	}
	*/
	public static String Md5(String plainText) {
		StringBuffer buf = new StringBuffer(""); 
		if(plainText!=null){
		  try {
		   MessageDigest md = MessageDigest.getInstance("MD5");

		   md.update(plainText.getBytes());
		   byte b[] = md.digest();

		   int i;
		   
		   for (int offset = 0; offset < b.length; offset++) {
		    i = b[offset];
		    if(i<0) i+= 256;
		    if(i<16)
		     buf.append("0");
		    buf.append(Integer.toHexString(i));
		   }
		   //System.out.println("result: " + buf.toString());
		   
		  } catch (NoSuchAlgorithmException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		}
		  return buf.toString();
	}
	//时间补０
	public static String addzero(int value){
		
		String result=""+value;
		if(value<10){
			result="0"+result;
		}		
		return result;
	}
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

	public static String DateToCnFormat_YMD(Date date1){//时间转换字符串//x年x月x日
		String result=null;
		if(date1!=null){
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(date1);
			result=DateToCnFormat_YMD(calendar1);
		}
		
		return result;
	}
	public static String DateToCnFormat_YMD(Calendar calendar1){//时间转换字符串//x年x月x日
		String result=null;
		if(calendar1!=null){
			result=""+calendar1.get(calendar1.YEAR)+"年";
			result=result+(calendar1.get(calendar1.MONTH)+1)+"月";
			result=result+calendar1.get(calendar1.DAY_OF_MONTH)+"日";
		}
		
		return result;
	}
	public static String DateToEnFormat_YMD(Calendar calendar1){//时间转换字符串//x年x月x日
		String result=null;
		if(calendar1!=null){
			result=""+calendar1.get(calendar1.YEAR)+"-";
			result=result+(calendar1.get(calendar1.MONTH)+1)+"-";
			result=result+calendar1.get(calendar1.DAY_OF_MONTH)+"";
		}
		
		return result;
	}
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
	public static String DateToEnFormat_YM(Date date1){//时间转换字符串//x-x
		String result=null;
		if(date1!=null){
			//System.out.println(date1);
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(date1);
			//System.out.println(calendar1.get(arg0));
			result=""+calendar1.get(calendar1.YEAR)+"-";
			result=result+(calendar1.get(calendar1.MONTH)+1)+"";
		}
		
		return result;
	}
	public static String DateToEnFormat_YM(Calendar date1){//时间转换字符串//x-x
		String result=null;
		if(date1!=null){
			//System.out.println(date1);
			Calendar calendar1 = date1;
			result=""+calendar1.get(calendar1.YEAR)+"-";
			result=result+(calendar1.get(calendar1.MONTH)+1)+"";
		}
		
		return result;
	}
	/*
	public static boolean DateEqualsByMonth(Calendar date1,Calendar date2){//时间转换字符串//x-x
		boolean result=false;
		String str1;
		String str2;
		//.out.println("str2:"+date2.get(date2.YEAR));
		if(date1!=null&&date2!=null){
			//System.out.println("===");
			Calendar calendar1 = date1;
			Calendar calendar2 = date2;
			
			str1=""+calendar1.get(calendar1.YEAR)+"-";
			str1=str1+(calendar1.get(calendar1.MONTH)+1)+"";
			str2=""+calendar2.get(calendar2.YEAR)+"-";
			str2=str2+(calendar2.get(calendar2.MONTH)+1)+"";
			if(str1.equals(str2)){
				
				result=true;
			}
		}
		return result;
	}
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
	public static String ArrToStr(String[] arr){
		String result=null;
			for(int i=0;i<arr.length;i++){
				if(result==null){
					result=arr[i];
				}else{
					result=result+","+arr[i];
				}
			}
		return result;
	}
	public static String ArrToStr(Integer[] arr){
		String result=null;
			for(int i=0;i<arr.length;i++){
				if(result==null){
					result=arr[i]+"";
				}else{
					result=result+","+arr[i]+"";
				}
			}
		return result;
	}
	
	//16进制随机数
	public static String getRndHex(){
		return Integer.toHexString(new Random().nextInt())+Integer.toHexString(new Random().nextInt()); 
	}
	//enurlcode
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
	//deurlcode
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

	//html过滤
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
	 //添加省略号
	 public static String Sub(String str,int length){
		 try{
		 return Sub(str,length,"...");
		 }catch(Exception e){
			 return str;
		 }
	 }
	 //添加任意符号
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
	/*
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
