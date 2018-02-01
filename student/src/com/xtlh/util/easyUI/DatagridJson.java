package com.xtlh.util.easyUI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @作者 	       杨威
 * @创建日期	2014-7-16
 * @功能描述	控制层调用 datagrid 返回的json对象
 * 
 */
@SuppressWarnings("serial")
public class DatagridJson implements Serializable
{
	private long total;	//总页数
	@SuppressWarnings("rawtypes")
	private List rows = new ArrayList();	//实体集合
	@SuppressWarnings("rawtypes")
	private List footer = new ArrayList();
	
	public DatagridJson(long total,List<?> rows)
	{
		this.total = total;
		this.rows = rows;
	}
	
	public long getTotal()
	{
		return total;
	}

	public void setTotal(long total)
	{
		this.total = total;
	}

	@SuppressWarnings("rawtypes")
	public List getRows()
	{
		return rows;
	}

	public void setRows(@SuppressWarnings("rawtypes") List rows)
	{
		this.rows = rows;
	}

	@SuppressWarnings("rawtypes")
	public List getFooter()
	{
		return footer;
	}

	public void setFooter(@SuppressWarnings("rawtypes") List footer)
	{
		this.footer = footer;
	}
	
}
