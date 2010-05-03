package org.wslhk.web;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.wslhk.io.FileOperate;
import org.wslhk.util.Log.Log;
/**
 * 获取景泰静态网页的工具
 * @author wsl
 *
 */
public class Static {
	//URLConnection conn=null;
	public static String getHtml(URLConnection conn,String charName) throws Exception{
		StringBuffer result=new StringBuffer();
       // URL urlt=new URL(url);
      //  URLConnection conn=urlt.openConnection();
       // conn.connect();
       // conn.getDoInput();
        InputStream inp= conn.getInputStream();
        Scanner sca=new Scanner(inp,charName);
       /* Map<String, List<String>> a= conn.getHeaderFields();
        Set<String> c=a.keySet();
        for (String r : c) {
              
             // System.out.print(r);
              result.append(r);
              //System.out.print(" :   ");
              result.append(" :   ");
              List<String> d=a.get(r);
              for (String e : d) {
                  //  System.out.print(e);
                    result.append(e);
                    
              }
              //System.out.println();
              result.append("\n");
        }*/
        while(sca.hasNext())
        {
              //System.out.println(sca.nextLine());
              result.append(sca.nextLine()+"\n");
        }
        return result.toString();
	  }
	/**
	 * 获取Scanner
	 * @param url
	 */
	private static URLConnection getConn(String url)throws Exception{
		if(url==null){url="http://www.baidu.com";}
		URLConnection conn=null;
		try{
        URL urlt=new URL(url);
        
        conn=urlt.openConnection();
        conn.connect();
        conn.getDoInput();
        //InputStream inp= conn.getInputStream();
        //sca=new Scanner(inp);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("获取连接异常");
		}
        //return sca;
		return conn;
	}
/**
 * 获取头文件信息
 * @param url
 * @return
 * @throws Exception
 */
//	  public static List<Header> getHeaderList(URLConnection conn) throws Exception{
//		  //if(url==null){url="http://www.baidu.com";}
//          //URL urlt=new URL(url);
//          
//          //URLConnection conn=urlt.openConnection();
//         // conn.connect();
//         // conn.getDoInput();
//          InputStream inp= conn.getInputStream();
//          Scanner sca=new Scanner(inp);
//        
//          Map<String, List<String>> a= conn.getHeaderFields();
//          Set<String> c=a.keySet();
//          
//          Header temp_h=new Header();
//          List<Header> listheader=new ArrayList();
//          for (String r : c) {
//        	  temp_h=new Header();
//        	  if(r!=null){
//        		  temp_h.setName(r);
//        	  }else{
//        		  temp_h.setName("state");
//        	  }
//               // System.out.print(r);
//              //  System.out.print(" :   ");
//                List<String> d=a.get(r);
//                for (String e : d) {
//                	if(temp_h.getValue()==null){
//                		temp_h.setValue(e);
//                     // System.out.print(e);
//                	}else{
//                		temp_h.setValue(temp_h.getValue()+"|"+e);
//                	}
//                      
//                }
//                listheader.add(temp_h);
//                
//          }
//          temp_h=null;
//          return listheader;
//
//	  }
	  
	  /**
	   * 获得一个页面的全部信息
	   * @param url
	   * @return
	   */
//	  public static Web getWeb(String url,String charname){
//		  try{
//			  URLConnection conn=getConn(url);
//			  Web thisweb=new Web();
//			  
//			  //设置获取头信息
//			 
//			  thisweb.setHeader(getHeaderList(conn));
//			  
//			  //设置内容 
//			  
//			  thisweb.setHtml(getHtml(conn,charname));
//			  
//			  //获得全部图片
//			  //System.out.println(getHtml(conn,charname));
//			  thisweb.setImagelist(ParseHTML.parseTag(url,getHtml(conn,charname),"IMG"));
//
//			  //获得全部iframe文件
//			 thisweb.setIframelist(ParseHTML.parseTag(url,thisweb.getHtml(),"IFRAME"));
//			  
//			 // thisweb.setJslist(ParseHTML.parseJavascript(thisweb.getHtml()));
//			  
//		  return thisweb;
//		  }catch(Exception e){
//			  e.printStackTrace();
//		  }
//		  
//		  return null;
//		  
//	  }
	  
//	  public static void Run(String url,String charset) {
//          // TODO Auto-generated method stub
//          try {
//        	  
//        	  //创建文件夹
//        	  File savefile=new File(Config.pathsave);
//        	  
//        	  if(savefile.exists()==false){
//        		  savefile.mkdirs();
//        		  Log.Log("创建文件夹 "+Config.pathsave);
//        	  }
//        	  savefile=null;
//        	  
//        	  //查看文件源是否存在
//        	  
//        	  File rpath=new File(Config.pathresource);
//        	  if(rpath.exists()==false){
//        		  Log.Log("找不到文件地址 "+Config.pathsave);
//        	  }
//        	  rpath=null;
//        	  
//        	  //执行网页静态单页
//        	  Web thisweb=getWeb(url,charset);
//        	  
//        	  //拷贝内部图片
//        	  for(int i=0;i<thisweb.getImagelist().size();i++){
//        		  DownFile thisimage=thisweb.getImagelist().get(i);
//        		  if(thisimage.isIn()==true){//如果是内部连接
//        			  FileOperate fo=new FileOperate();
//        			  fo.newFolder(Config.pathsave+thisimage.getSrc());
//        			  Log.Log(Config.pathsave+thisimage.getSrc());
//        			  fo.copyFile(Config.pathresource+thisimage.getSrc()
//        					  , Config.pathsave+thisimage.getSrc());
//        			  //fo.newFolder("d:/aa/bb/cc");
//        			//  fo.copyFile("D:/forumnokia/sch/cai2.html", "D:/forumnokia/sch/static/cai2.html");
//        		  }
//        		 thisimage=null;
//        	  }
//        	  
//        	  //处理iframe
//        	  for(int i=0;i<thisweb.getIframelist().size();i++){
//        		  DownFile thisimage=thisweb.getIframelist().get(i);
//        		  if(thisimage.isIn()==true){//如果是内部连接
//        			  FileOperate fo=new FileOperate();
//        			  fo.newFolder(Config.pathsave+thisimage.getSrc());
//        			  Log.Log(Config.pathsave+thisimage.getSrc());
//        			  fo.copyFile(Config.pathresource+thisimage.getSrc()
//        					  , Config.pathsave+thisimage.getSrc());
//        			 Run(Config.pathweb+thisimage.getSrc(),charset);
//        		  }
//        		 thisimage=null;
//        	  }
//        	  
//          } catch (Exception e) {
//                // TODO: handle exception
//                e.getStackTrace();
//                e.printStackTrace();
//          }
//
//    }
	  public static void main(String[] args) {
//		  String url="http://www1.forum.nokia.com.cn/sch/template.jsp?article_id=24&navchild_id=6&navroot_id=11&active1_id=6&active2_id=-1";
//		  String charset="utf-8";
//		  Run(url,charset);
		  String html;
		try {
			html = getHtml(Static.getConn("http://www.baidu.com"),"utf-8");
			ParseHTML.parseTag("",html,"script");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
			  
	  }
}
