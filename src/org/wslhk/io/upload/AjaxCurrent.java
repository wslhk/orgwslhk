package org.wslhk.io.upload;
/**
 * 当前下载状态
 * @author wsl
 *
 */
public class AjaxCurrent {
long size;
long currentsize;
String filename;
long ms;
public AjaxCurrent(){
	size=0;
	currentsize=0;
	filename="";
	ms=0;
}
public long getCurrentsize() {
	return currentsize;
}
public void setCurrentsize(long currentsize) {
	this.currentsize = currentsize;
}
public String getFilename() {
	return filename;
}
public void setFilename(String filename) {
	this.filename = filename;
}
public long getMs() {
	return ms;
}
public void setMs(long ms) {
	this.ms = ms;
}
public long getSize() {
	return size;
}
public void setSize(long size) {
	this.size = size;
}
}
