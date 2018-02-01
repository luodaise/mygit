package com.xtlh.util.easyUI;

import java.io.Serializable;

/**
 * 
 * @作者 	  杨威
 * @创建日期 2014-7-16
 * @功能描述 easyui datagrid请求包含的参数123123
 * 
 * @param <T>
 */
@SuppressWarnings("serial")
public class DatagridBean implements Serializable
{
	private int rows; // 总条数
	private int page; // 当前页
	private String sort; //排序字段
	private String order; //排序规则
	private String queryparam; //查询条件

	public int getRows()
	{
		return rows;
	}

	public void setRows(int rows)
	{
		this.rows = rows;
	}

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public String getOrder()
	{
		return order;
	}

	public void setOrder(String order)
	{
		this.order = order;
	}

	public String getSort()
	{
		return sort;
	}

	public void setSort(String sort)
	{
		this.sort = sort;
	}

	public String getQueryparam()
	{
		return queryparam;
	}

	public void setQueryparam(String queryparam)
	{
		this.queryparam = queryparam;
	}	
	
}
