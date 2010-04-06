package org.wslhk.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XML {
Document doc=null;
	
public XML(String path){
	try{
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder domparser = factory.newDocumentBuilder();
	doc = domparser.parse(new FileInputStream(path));
	}catch(Exception e){
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	
}
public String  getTextContentByTag(String tagName){
	String result=null;
	result=doc.getElementsByTagName(tagName).item(0).getTextContent();
	return result;
}
public static void  main(String[] args){
XML xx=new XML("E:/workspace/lelebox/WEB-INF/lelebox.xml");
//System.out.println();
System.out.println(xx.getTextContentByTag("s60cmspath"));
//xx.readxml();
	//System.out.println("aa");
//File FileTest=new File("./");
	 // System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));     
	 // System.out.println(ClassLoader.getSystemResource("")); 
	  //System.out.println(FileTest.class.getClassLoader().getResource(""));        

	       
	 // System.out.println(FileTest.class.getResource(""));        
	//  System.out.println(FileTest.class.getResource("/")); //Class文件所在路径  
	 // System.out.println(new File("/").getAbsolutePath());        
	 // System.out.println(System.getProperty("user.dir"));   
}
}
