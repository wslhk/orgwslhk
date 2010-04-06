package org.wslhk.io.upload;

public class ExtensionException extends Exception{
	String message="Extension of file is error";
	public ExtensionException(){	
		//this.getMessage()
		
	}
	public String getMessage(){
		return message;
	}
}
