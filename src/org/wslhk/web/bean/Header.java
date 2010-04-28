package org.wslhk.web.bean;
/**
 * 用户保存获取网页的header信息
 * @author wsl
 *
 */
public class Header {
String name=null;
String value=null;
/**
 * 获取键名
 * @return
 */
public String getName() {
	return name;
}
/**
 * 设置键名
 * @param name
 */
public void setName(String name) {
	this.name = name;
}
/**
 * 获取键值
 * @return
 */
public String getValue() {
	return value;
}
/**
 * 设置键值
 * @return
 */
public void setValue(String value) {
	this.value = value;
}
}
