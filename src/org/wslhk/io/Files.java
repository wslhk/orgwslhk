package org.wslhk.io;
import java.io.*;
public class Files{
public void CreateFile(String path,String filename,String content){
	File file=new File(path,filename);
	try{
	if(file.exists()==false){
		file.createNewFile();
	}
	FileWriter fw = new FileWriter(path+filename,true);
    fw.write(content);
    fw.flush();
    fw.close();
	}catch(IOException e){
		e.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	
}

public static void main(String[] args){
	//Files files=new Files();
	//files.CreateFile("e:/","test.txt","中文");
	
}
}
