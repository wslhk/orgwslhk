package org.wslhk.io.upload;

public class ExtensionException extends Exception{
	String message="Extension of file is error/文件格式不支持";
	public ExtensionException(){	
		//this.getMessage()
		
	}
	public String getMessage(){
		return message;
	}
	public String toString(){
		return message;
	}
}
