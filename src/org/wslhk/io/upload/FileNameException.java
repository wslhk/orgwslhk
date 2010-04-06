package org.wslhk.io.upload;

public class FileNameException extends Exception{
	String message="filename set error/文件名称设置错误";
	public FileNameException(){
		
	}
	public FileNameException(String test){	
		this.message=this.message+"\n"+test;
		
	}
	public String getMessage(){
		return message;
	}
}
