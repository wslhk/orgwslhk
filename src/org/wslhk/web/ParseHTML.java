package org.wslhk.web;

import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.wslhk.Log.Log;
import org.wslhk.io.FileOperate;
import org.wslhk.web.bean.DownFile;
import org.wslhk.web.bean.Javascript;

public class ParseHTML {
	private static boolean quchongfu(List strlist,String str){
		for(int i=0;i<strlist.size();i++){
			//System.out.println(strlist.get(i)+"__"+str);
			if(strlist.get(i).equals(str)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @param html
	 * @param tagname
	 * @return
	 * @throws ParserException
	 */
	public   static   List<DownFile>   parseTag(String url,String html,String tagname)   throws   ParserException   { 
		List DownFilelist=new ArrayList();
		  try{  
		  Parser   parser=new   Parser(html); 
		  List ctemp=new ArrayList();//去重复 存储 
		  NodeFilter filter_title = new TagNameFilter(tagname);
		  NodeList   list=parser.parse(filter_title);
		  DownFile thisimg=null;
		  for(int i=0;i<list.size();i++){
			  //org.htmlparser.tags.
			//  org.htmlparser.Tag
			  //Class cc=obj.getClass();
			  org.htmlparser.Tag node=(org.htmlparser.Tag)list.elementAt(i);
			 thisimg=new DownFile();
			 thisimg.setId(i);
			 thisimg.setName(node.getText());
			 thisimg.setSrc(node.getAttribute("src"));
			 thisimg.setIn(false);
			 if((thisimg.getSrc()!=null)
					 &&(thisimg.getSrc().length()>4)
					 &&(thisimg.getSrc().substring(0,4).equals("http")==false)){
				 thisimg.setIn(true);
				 
			 }
			 // System.out.println("_"+node.getAttribute("src"));
			  node=null;
			  
			  /**
			   * ../问题 upfloder
			   */
			 
			 /// System.out.println(pp);
			  String pp=url.replace(Config.pathweb,"");
			  FileOperate fo=new FileOperate();
			  pp=fo.filetofloder(pp);
			  int ii=thisimg.getSrc().split("../").length;
			  for(int j=0;j<ii-2;j++){
				 // System.out.println(j+"====");
				  pp=fo.getUpFloder(pp);				  
			  }
			  fo=null;
			  thisimg.setSrc(pp+thisimg.getSrc().replace("../",""));
			  /**
			   *  /问题 root floder
			   */
			  if(thisimg.getSrc().substring(0,1).equals("/")){
				  //Log.Log("跟目录"+thisimg.getSrc());
				  String[] ttemp=thisimg.getSrc().split("/");
				  String tresult=null;
				  for(int j=2;j<ttemp.length;j++){
					  if(tresult==null){
						  tresult=ttemp[j];
					  }else{
					 tresult=tresult+"/"+ttemp[j];
					  }
				  }
				  thisimg.setSrc(tresult);
				 // System.out.println("--"+tresult);
			  }
			  
			  
			  if(quchongfu(ctemp,thisimg.getSrc())==false){
				  DownFilelist.add(thisimg);
				  ctemp.add(thisimg.getSrc());
			  }else{
				  ctemp.add(thisimg.getSrc());
			  }
			  thisimg=null;
		  }
		  //Node   node=list.elementAt(0);  
		
		// String title = node.getNextSibling().getNextSibling().toHtml(); 
		   //System.out.println(title);
		  }catch(Exception   e){  
		   e.printStackTrace();
		  }
		  return DownFilelist;
	}
	
	
}
