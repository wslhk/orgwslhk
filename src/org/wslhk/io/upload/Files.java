package org.wslhk.io.upload;

public class Files {
String filename=null;
String ext=null;
long size=0;
String path=null;
String fieldname=null;
public String getFieldname() {
	return fieldname;
}
public void setFieldname(String fieldname) {
	this.fieldname = fieldname;
}
public String getExt() {
	return ext;
}
public void setExt(String ext) {
	this.ext = ext;
}
public String getFilename() {
	return filename;
}
public void setFilename(String filename) {
	this.filename = filename;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
public long getSize() {
	return size;
}
public void setSize(long size) {
	this.size = size;
}
}
