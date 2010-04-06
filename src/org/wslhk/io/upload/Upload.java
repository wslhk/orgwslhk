package org.wslhk.io.upload;

import java.io.File;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.wslhk.str.Str;


public class Upload {
void upload(){
}
List fieldlist=new ArrayList();
List filelist=new ArrayList();

public static int TRUENAME=0;
public static int RNDNAME=1;
public static int SETNAME=2;

long maxsize=0;
String name=null;
String path=null;
String temppath=null;
String extension=null;
int nametype=0;

AjaxCurrent ajax=new AjaxCurrent();
HttpServletRequest request=null;

/*默认上传*/
public void upload(HttpServletRequest request)throws Exception{
	this.request=request;
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
private void formadd(FileItem fi){
	Field f=new Field();
	f.setName(fi.getFieldName());
	f.setValue(fi.getString());
	//System.out.println(f.getName()+"___"+f.getValue());
	fieldlist.add(f);
}
public Field getField(String fieldname){
	Field f=new Field();
	for(int i=0;i<this.fieldlist.size();i++){
		f=(Field)this.fieldlist.get(i);
		if(f.getName().equals(fieldname)){
			return f;
		}
	}
	f=null;
	return new Field();
}
private void fileadd(FileItem fi)throws ExtensionException,Exception{
	//fi.g
	java.util.Random r=new java.util.Random();
	Calendar ca=Calendar.getInstance();
	Files thisfile=new Files();
	if(extension!=null&&this.isAllowExtension(fi.getName(), extension)==false){
   	  throw new ExtensionException();
     }
	thisfile.setExt(getExtension(fi.getName()));
	thisfile.setPath(this.getPath());
	thisfile.setSize(fi.getSize());
	thisfile.setFilename(getFilename(fi.getName()));
	thisfile.setFieldname(fi.getFieldName());
	
	setAjaxfilename(getFilename(fi.getName())+""+getExtension(fi.getName()));
	
	if(this.getNametype()==this.RNDNAME){
		thisfile.setFilename(""+ca.get(ca.YEAR)+ca.get(ca.MONTH)+ca.get(ca.DAY_OF_MONTH)+ca.get(ca.HOUR_OF_DAY)+ca.get(ca.MINUTE)+ca.get(ca.SECOND)+"_"+r.nextInt(999));
	}else if(this.getNametype()==this.TRUENAME){
		//donothing
	}
	if(this.getNametype()==this.SETNAME){
		if(thisfile.getFieldname().indexOf("file")==-1){
			throw new FileNameException("field_name,can't indexof 'file'");
		}
		if(thisfile.getFieldname().split("file")[0].length()==1){
			throw new FileNameException("field_name,only indexof 'file'");
		}
			//this
			String thisid=thisfile.getFieldname().split("file")[1];
			
			String t_setfname=null;
			for(int i=0;i<this.getFieldlist().size();i++){
				//t_setfname=((Field)this.getFieldlist().get(i)).getName();
				if(((Field)this.getFieldlist().get(i)).getName().equals("filename"+thisid)){
					t_setfname=((Field)this.getFieldlist().get(i)).getName();
				}
			}
			if(t_setfname==null){
				throw new FileNameException("field_name,can't find 'filename"+thisid+"'");
			}
			thisfile.setFilename(t_setfname);
	}
    File savedFile = new File(thisfile.getPath(),thisfile.getFilename()+"."+thisfile.getExt());
    setAjaxfilename(thisfile.getFilename()+"."+thisfile.getExt());
    fi.write(savedFile);
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
public boolean isAllowExtension(String filename,String Extension){
	/**
	 * Extension用|分割
	 */
	boolean result=false;
	String fileext=getExtension(filename.toLowerCase());
	String[] temp=Extension.toLowerCase().split("[|]");
	for(int i=0;i<temp.length-1;i++){
		//System.out.println(temp[i]+"=?"+fileext);
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
public List getFieldlist() {
	return fieldlist;
}
public void setFieldlist(List fieldlist) {
	this.fieldlist = fieldlist;
}
public List getFilelist() {
	return filelist;
}
public void setFilelist(List filelist) {
	this.filelist = filelist;
}
public String getTemppath() {
	return temppath;
}
public void setTemppath(String temppath) {
	this.temppath = temppath;
}

private void setAjaxfilename(String filename){
	if(ajax==null){ajax=new AjaxCurrent();}
	ajax.setFilename(filename);
	request.getSession().setAttribute("ajaxupload", ajax);
}

private void setAjaxsize(long size){
	if(ajax==null){ajax=new AjaxCurrent();}
	ajax.setSize(size);
	request.getSession().setAttribute("ajaxupload", ajax);
}

private void setAjaxCurrentsize(long size){
	if(ajax==null){ajax=new AjaxCurrent();}
	ajax.setCurrentsize(size);
	request.getSession().setAttribute("ajaxupload", ajax);
}
private void setAjaxMs(long ms){
	if(ajax==null){ajax=new AjaxCurrent();}
	ajax.setMs(ms);
	request.getSession().setAttribute("ajaxupload", ajax);
}
}

