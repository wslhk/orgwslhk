package org.wslhk.io.upload;

import java.io.File;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Upload {
void upload(){
}
//表单对象
List<Field> fieldlist=new ArrayList();
//文件对象
List<Files> filelist=new ArrayList();
//真实文件名
public static int TRUENAME=0;
//随机文件名
public static int RNDNAME=1;
//设置文件名
public static int SETNAME=2;
//最大尺寸限制
long maxsize=0;
//文件名
String name=null;
//文件保存路径
String path=null;
//临时路径
String temppath=null;
//后缀名
String extension=null;
//当前类型
int nametype=0;

UploadProgress progressListener = new UploadProgress();  
/**
 * 默认上传
 * @param request
 * @throws Exception
 */
public void upload(HttpServletRequest request)throws Exception{
//	Thread thread=new Thread(
//			 new Runnable() { public void run() { 
//			 int i=0;
//				 while(i<5000);
//					 try{
//					 	Thread.sleep(1);
//					 }catch(Exception e){
//					 	e.printStackTrace();
//					 }
//				 i++;
//				 ProgressListener pl= (ProgressListener)request.getSession().getAttribute("progress");
//				 }
//			 
//			 }
//			 );
	
if(temppath==null){temppath=path;}
	   File tempPathFile=new File(temppath);
	   if(tempPathFile.exists()==false){tempPathFile.mkdirs();}
	   File uploadPath=new File(path);
	   if(uploadPath.exists()==false){uploadPath.mkdirs();}
    // Create a factory for disk-based file items
    DiskFileItemFactory factory = new DiskFileItemFactory();

    // Set factory constraints
    factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
    factory.setRepository(tempPathFile);// 设置缓冲区目录

    // Create a new file upload handler
    ServletFileUpload upload = new ServletFileUpload(factory);
    

    upload.setSizeMax(maxsize); // 设置最大文件尺寸，这里是200k
try{
    List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
    //添加监听
    //uploadsetProgressListener(new UploadListener(request));
    request.getSession().setAttribute("progress", progressListener);   
    upload.setProgressListener(progressListener);   
    
    Iterator<FileItem> i = items.iterator();
    while (i.hasNext()) {
       FileItem fi = (FileItem) i.next();
	       if(fi.isFormField()){
	    	   formadd(fi);
	       }else if(fi.getSize()>0){
	    	   fileadd(fi);
	       }
	       
       }
}catch(Exception e){
	throw e;
}
	
}
/**
 * 添加表单内容
 * @param fi
 */
private void formadd(FileItem fi){
	Field f=new Field();
	f.setName(fi.getFieldName());
	f.setValue(fi.getString());
	//System.out.println(f.getName()+"___"+f.getValue());
	fieldlist.add(f);
}
/**
 * 通过提交名称查找一个文本域对象
 * @param fieldname
 * @return
 */
public Field getField(String fieldname){
	Field f=new Field();
	for(int i=0;i<this.fieldlist.size();i++){
		f=(Field)this.fieldlist.get(i);
		if(f.getName().equals(fieldname)){
			return f;
		}
	}
	f=null;
	return f;
}

/**
 * 通过提交名称查找一个文件列表
 * @param fieldname
 * @return
 */
public List<Files> getFilelist(String fieldname){
	Files f=new Files();
	List<Files> listfileresult=new ArrayList();
	for(int i=0;i<this.filelist.size();i++){
		f=(Files)this.filelist.get(i);
		if(f.getFieldname().equals(fieldname)){
			listfileresult.add(f);
		}
		
	}
	return listfileresult;
}

/**
 * 文件处理
 * @param fi
 * @throws ExtensionException
 * @throws Exception
 */
private void fileadd(FileItem fi)throws ExtensionException,Exception{
	//随机对象
	java.util.Random r=new java.util.Random();
	//日历对象
	Calendar ca=Calendar.getInstance();
	//文件对象
	Files thisfile=new Files();
	//查看是否支持后缀名
	if(extension!=null&&this.isAllowExtension(fi.getName(), extension)==false){
   	  throw new ExtensionException();
     }
	//设置后缀名
	thisfile.setExt(getExtension(fi.getName()));
	//设置路径
	thisfile.setPath(this.getPath());
	//设置文件大小
	thisfile.setSize(fi.getSize());
	//设置真实文件名
	thisfile.setFilename(getFilename(fi.getName()));
	//设置域名
	thisfile.setFieldname(fi.getFieldName());
	//如果设置的是随机文件名,重新设置保存随机文件名
	if(this.getNametype()==this.RNDNAME){
		thisfile.setFilename(""+ca.get(ca.YEAR)+ca.get(ca.MONTH)+ca.get(ca.DAY_OF_MONTH)+ca.get(ca.HOUR_OF_DAY)+ca.get(ca.MINUTE)+ca.get(ca.SECOND)+"_"+r.nextInt(999));
	}else if(this.getNametype()==this.TRUENAME){
		//donothing
	}else if(this.getNametype()==this.SETNAME){
		//如果设置自定义文件名
		
		//字段名必须包含file
		if(thisfile.getFieldname().indexOf("file")==-1){
			throw new FileNameException("field_name,can't indexof 'file'");
		}
		if(thisfile.getFieldname().split("file")[0].length()==1){
			throw new FileNameException("field_name,only indexof 'file'");
		}
			//获得 file后的编号
			String thisid=thisfile.getFieldname().split("file")[1];
			//设置的文件名必须在上传文件前设置,必须以filename开头,编号必须和文件相同
			String t_setfname=null;
			for(int i=0;i<this.getFieldlist().size();i++){
				//t_setfname=((Field)this.getFieldlist().get(i)).getName();
				if(((Field)this.getFieldlist().get(i)).getName().equals("filename"+thisid)){
					t_setfname=((Field)this.getFieldlist().get(i)).getName();
					break;
				}
			}
			if(t_setfname==null){
				throw new FileNameException("field_name,can't find 'filename"+thisid+"'");
			}
			thisfile.setFilename(t_setfname);
	}
	//保存文件组合
    File savedFile = new File(thisfile.getPath(),thisfile.getFilename()+"."+thisfile.getExt());
    
    
    fi.write(savedFile);
    System.out.println("upload.java:"+progressListener.getAllSize());
    filelist.add(thisfile);
    fi.delete();
}


public String getExtension(String filename){
	String[] temp=filename.split("[.]");
	  int leng=temp.length;
	  if(leng==1){
		  return "";
	  }else{
	  return temp[leng-1];
	  }
}
public String getFilename(String filenamefull){
	String[] temp=filenamefull.split("[.]");
	  int leng=temp.length;
	  String temp1name=null;
	  for(int i=0;i<temp.length-1;i++){
		  if(temp1name==null){
		  temp1name=temp[i];
		  }else{
			  temp1name=temp1name+"."+temp[i]; 
		  }
	  }
	  return temp1name;
	 // }
	 // return temp[leng-1];
}
/**
 * 是否允许后缀名
 * @param filename
 * @param Extension
 * @return
 */
public boolean isAllowExtension(String filename,String Extension){
	/**
	 * Extension用|分割
	 */
	boolean result=false;
	String fileext=getExtension(filename.toLowerCase());
	String[] temp=Extension.toLowerCase().split("[|]");
	for(int i=0;i<temp.length;i++){
	
		if(temp[i].equals(fileext)){
			result=true;
			break;
		}
	}
	return result;
}
public String getExtension() {
	return extension;
}
public void setExtension(String extension) {
	this.extension = extension;
}
public long getMaxsize() {
	return maxsize;
}
public void setMaxsize(long maxsize) {
	this.maxsize = maxsize;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
public int getNametype() {
	return nametype;
}
public void setNametype(int nametype) {
	this.nametype = nametype;
}
public List<Field> getFieldlist() {
	return fieldlist;
}
public void setFieldlist(List<Field> fieldlist) {
	this.fieldlist = fieldlist;
}
public List<Files> getFilelist() {
	return filelist;
}
public void setFilelist(List<Files> filelist) {
	this.filelist = filelist;
}
public String getTemppath() {
	return temppath;
}
public void setTemppath(String temppath) {
	this.temppath = temppath;
}
public ProgressListener getProgressListener() {
	return progressListener;
}

}

