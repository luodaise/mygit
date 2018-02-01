package com.xtlh.util.easyUI;

import java.util.List;
import java.util.Map;

/**
 * @作者                   康荣彩
 * @文件名      TreeNode.java
 * @创建日期    2014年10月20日 下午15:41:34
 * @功能描述    easyui数据类型封装实体类
 *
 */
@SuppressWarnings("serial")
public class TreeNode implements java.io.Serializable
{
	private String id;              // 树节点id
	private String pid;
	private String text;             // 树节点名称
	private String iconCls;          // 前面的小图标样式
	private Boolean checked = false; // 是否勾选状态
	private List<TreeNode> children; // 子节点
	private String state = "open";   // 是否展开(open,closed)
	private Map<String, Object> attributes;// 其他参数

	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getText()
	{
		return text;
	}
	public void setText(String text)
	{
		this.text = text;
	}
	public String getIconCls()
	{
		return iconCls;
	}
	public void setIconCls(String iconCls)
	{
		this.iconCls = iconCls;
	}
	public Boolean getChecked()
	{
		return checked;
	}
	public void setChecked(Boolean checked)
	{
		this.checked = checked;
	}
	public Map<String, Object> getAttributes()
	{
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes)
	{
		this.attributes = attributes;
	}
	public List<TreeNode> getChildren()
	{
		return children;
	}
	public void setChildren(List<TreeNode> children)
	{
		this.children = children;
	}
	public String getState()
	{
		return state;
	}
	public void setState(String state)
	{
		this.state = state;
	}
	
}
