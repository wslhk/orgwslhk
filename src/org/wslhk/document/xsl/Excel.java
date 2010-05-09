package org.wslhk.document.xsl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * excel导出
 * @author Administrator
 *
 */
/**
 * 说明
 * 用作jxl组件的扩展，避免每次都写很多重复的东西
 * 使用方法
 * List title 为字符串列表， 
 * 方式一:
 * 支持分号分割的方法如"标题1;标题2"
 * 方式二：
 * 直接付值
 * 
 * List value 为List跌代一次。添加方法是
 * 方式一：
 * List hang;List value;
 * hang.add("第一列的内容");hang.add("第2列的内容");hang.add("第3列的内容");
 * value.add(hang);
 * 方式二：
 * this.obj.setValueAddHang(List hang);
 */
public class Excel {
List title=null;
List value=null;
String tablename=null;
String path=null;
public void OutputExcel(){
	try{
		if(title==null){throw new Exception("excel'title can't null");}
		if(value==null){throw new Exception("excel'title can't null");}
		if(tablename==null){throw new Exception("excel'title can't null");}
		if(path==null){throw new Exception("excel'title can't null");}
		OutputExcel(title,value,path,tablename);
	}catch(Exception e){
		e.getMessage();
	}
}
public void OutputExcel(List<String> title,List value,String Path,String tablename){
	
	
	WritableWorkbook book=null;
	   try{
	        book = Workbook.createWorkbook(new File(Path));
//	设置表名
	WritableSheet sheet = book.createSheet(tablename,0);
//	生成表格题头
	for(int i=0;i<title.size();i++){
		Label labe1 = new Label(i, 0, (String)title.get(i) );
		sheet.addCell(labe1);
	}
//将生成的单元格添加到工作表中    
	
	Iterator it = value.iterator();//.iterator();
	int i = 1;//行

	     while(it.hasNext()){

	    	 List temp = (List)it.next();

//	取得数据生成单元格
	for(int j=0;j<temp.size();j++){//列
		Label  label1=new  Label(j,i,(String)temp.get(j));
//		将生成的单元格添加到工作表中   
		sheet.addCell(label1);
	}
	i++;
	       }
	       book.write(); 
	       book.close();
	         } catch (RowsExceededException e) {    
	            e.printStackTrace();    
	        } catch (WriteException e) {    
	            e.printStackTrace();    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        } finally{
	try{
	     if(book!=null)book.close();
	}catch(Exception e){
	  System.out.println("exception when closing Connection in finally");
	  //System.out.println(e.getMessage().toString());
	}
	       }
	    }
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
public String getTablename() {
	return tablename;
}
public void setTablename(String tablename) {
	this.tablename = tablename;
}
public List getTitle() {
	return title;
}
public void setTitle(List title) {
	this.title = title;
}
public void setTitle(String title) {
	try{
		if(title==null){throw new Exception("title can't null");}
	String temp[]=title.split(";");
	this.title=new ArrayList();
	for(int i=0;i<temp.length;i++){
		this.title.add(temp[i]);
	}
	
	}catch(Exception e){
		e.getMessage();
	}
}
public List getValue() {
	return value;
}
public void setValue(List value) {
	this.value = value;
}
public void setValueAddHang(List hang) {
	if(this.value==null){
		this.value=new ArrayList();
	}
	this.value.add(hang);
}

public static void main(String[] args){
	//测试用例
	String path="e:/asdf.xls";
	String title="标题1;标题2;标题3";
	List hang=new ArrayList();
	hang.add("aaa");hang.add("bbbb");hang.add("cc");
	Excel excel=new Excel();
	excel.setPath(path);
	excel.setTablename("测试表格");
	excel.setTitle(title);
	excel.setValueAddHang(hang);
	excel.setValueAddHang(hang);//插入第2行内容完全一样
	excel.OutputExcel();
}
}
