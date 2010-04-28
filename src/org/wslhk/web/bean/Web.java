package org.wslhk.web.bean;

import java.util.List;

/**
 * 一个网页包含的信息和内容
 * @author wsl
 *
 */
public class Web {
int id=0;
String name=null;
List<Header> header=null;
List<DownFile> imagelist=null;
List<DownFile> iframelist=null;
String html=null;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public List<Header> getHeader() {
	return header;
}
public void setHeader(List<Header> header) {
	this.header = header;
}
public String getHtml() {
	return html;
}
public void setHtml(String html) {
	this.html = html;
}
public List<DownFile> getImagelist() {
	return imagelist;
}
public void setImagelist(List<DownFile> imagelist) {
	this.imagelist = imagelist;
}
public List<DownFile> getIframelist() {
	return iframelist;
}
public void setIframelist(List<DownFile> iframelist) {
	this.iframelist = iframelist;
}

}
