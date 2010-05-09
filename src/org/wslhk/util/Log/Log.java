package org.wslhk.util.Log;

public class Log {
	String classname=null;
	Log(Object obj){
		this.classname=obj.getClass().getName();
	}
	Log(Class cla){
		this.classname=cla.getName();
	}
	public void debug(String msg){
		System.out.print(msg);
	}
	public static void Log(String log){
		System.out.println(log);
	}
}
