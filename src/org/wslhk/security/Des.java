package org.wslhk.security;

/**  
 *   FileName:     Encryptstr.java  
 *   Description: 字符串加密：通过指定相同长度的key字符串，将源字符串和Key字符串的Ascii码进行异或，并进行简单操作，获得加密后的数字字符串  
 *   Copyright: Copyright   (c)   2003  
 *   @Author: Totodo  
 *   @Email: totodo@vip.sina.com  
 *   @Version: 1.0  
 */  


public   class   Des   {  
//static   String   sWrong;  
//static   String   sSkey="207806514039";  

//public   Desc()   {  
//sWrong="";  
//}  

private   static   String   encryptkey(String   sKey,int   iLength)   {  
//获取指定长度的key  
String   sKeystr=sKey;  
int   kLength;  
kLength=sKey.length();  
if   (iLength<=kLength)  
sKeystr=sKey.substring(0,iLength);   //截取和加密字符串长度相等的key  
else   {  
//重复key使长度相等  
while   (kLength<iLength)   {  
if   (kLength+sKey.length()>=iLength) {  
sKeystr=sKeystr+sKey.substring(0,iLength-kLength);  
}  
else   {  
sKeystr=sKeystr+sKey;  
}  
kLength=sKeystr.length();  
}  
}  
return   sKeystr;  

}  

/**
 * 加密使用
 * @param sKey		key
 * @param sSource	需要加密的内容
 * @return
 */
public   static  String   encrypt(String   sKey,String   sSource)   {  
//加密字符串  
String   sTarget="";  
String   sKeystr;//和加密字符串长度相等的key  
int   sLength;//加密字符串长度  
char   cS,cK,cT;  
int   iS,iK,iT;  
int   i;  

if   (sSource=="")   {  
//sWrong="字符串为空！";  
return   sTarget;  
}  
if   (sKey=="")   {  
//sWrong="请指定密钥！";  
return   sTarget;  
}  

sLength=sSource.length();  
sKeystr=encryptkey(sKey,sLength);  

for   (i=0;i<sLength;i++)   {  
cS=sSource.charAt(i);  
cK=sKeystr.charAt(i);  
iS=(int)cS;  
iK=(int)cK;  
iT=iS^iK;  
// System.out.print(iS);  
// System.out.print("   ");  
// System.out.print(iK);  
// System.out.print("   ");  
// System.out.print(iT);  
// System.out.print("   ");  
// System.out.println(iT+29);  
iT=iT+29;  
iT=1000+iT/10%10*100+iT/100*10+iT%10;//十位、百位、个位  
sTarget=sTarget+Integer.toString(iT).substring(1);  
}  

return   sTarget;  
}  

//public   String   encrypt(String   sSource)   {  
//return   encrypt(sSkey,sSource);  
//}  

/**
 * 解密使用
 * @param sKey  	key
 * @param sTarget 	需要解密的内容
 */
public   static   String   decode(String   sKey,String   sTarget)   {  
//解密  

String   sSource="";  
String   sKeystr;//和解密字符串长度相等的key  
int   sLength; //解密字符串长度  
char   cS,cK;  
String   cT;  
int   iS,iK,iT;  
int   i;  

if   (sTarget=="")   {  
//sWrong="字符串为空！";  
return   sSource;  
}  
if   (sKey=="")   {  
//sWrong="请指定密钥！";  
return   sSource;  
}  

sLength=sTarget.length()/3;  
sKeystr=encryptkey(sKey,sLength);  

for   (i=0;i<sLength;i++)   {  
cT=sTarget.substring(i*3,(i+1)*3);  
cK=sKeystr.charAt(i);  
iT=Integer.parseInt(cT);  
iT=iT/10%10*100+iT/100*10+iT%10;  
iK=(int)cK;  
iS=(iT-29)^iK;  
// System.out.print(iT);  
// System.out.print("   ");  
// System.out.print(iT-29);  
// System.out.print("   ");  
// System.out.print(iK);  
// System.out.print("   ");  
// System.out.println(iS);  
cS=(char)iS;  
sSource=sSource+cS;  
}  

return   sSource;  
}  

//public   static   String   decode(String   sTarget)   {  
//return   decode(sSkey,sTarget);  
//}  

/**
 * 测试用例
 */
public   static   void   main(String[]   args)   throws   Exception{    
String   str;  
//Encryptstr   en_str   =   new   Encryptstr();  
str=Des.encrypt("abcde","中文");  
System.out.println("中文加密后的字符传:"+str);  
System.out.println("中文解迷后的字符船:"+Des.decode("abcde",str));  
}  
}   
