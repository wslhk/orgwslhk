package org.wslhk.io;

public class ExtensionException extends Exception{
	String message="Extension of file is error/文件格式不支持";
	public ExtensionException(){	
		//this.getMessage()
		
	}
	public String getMessage(){
		return message;
	}
}
