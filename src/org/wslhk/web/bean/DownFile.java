package org.wslhk.web.bean;
/**
 * 需要下载的文件
 * @author wsl
 *
 */
public class DownFile {
	
	
	int id=0;
	String name=null;
	/**
	 * 连接地址
	 */
	String src=null;
	/**
	 * 是否是内连，默认为否
	 */
	boolean in=false;
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
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public boolean isIn() {
		return in;
	}
	public void setIn(boolean in) {
		this.in = in;
	}
}
