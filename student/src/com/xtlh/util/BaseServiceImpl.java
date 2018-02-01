package com.xtlh.util;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xtlh.util.easyUI.DatagridBean;
import com.xtlh.util.easyUI.DatagridJson;



/**
 * @作者       江成军
 * @创建日期   2013-4-3
 * @功能描述   业务层公共实现类  
 *
 */
@Service
@Transactional
public abstract class BaseServiceImpl<T> implements IBaseService<T>
{		
	
	public abstract IBaseDao<T> getDao();
	
	@Override
	public List<T> findAll()
	{
		return getDao().findAll();
	}
	
	@Override
	public void save(T object)
	{
		getDao().save(object);
	}
	
	@Override
	public T findById(Long id)
	{
		return (T) getDao().findById(id);
	}
	@Override
	public T findById(int id)
	{
		return (T) getDao().findById(id);
	}
	@Override
	public void update(T object)
	{
		getDao().update(object);
	}

	@Override
	public void updatas(String hql)
	{
		getDao().updatas(hql);
	}
	
	@Override
	public void change(T object)
	{
		getDao().update(object);
	}
	
	@Override
	public void updateById(Long id)
	{		
		getDao().updateById(id);
	}
	
	@Override
	public void updateById(int id)
	{		
		getDao().updateById(id);
	}
	
	
	/**
	 * @作者                 贾明
	 * @编辑时间  2013-5-09 上午10:48:42
	 * @功能描述       根据实体对象删除数据方法
	 * @输入参数  int类型的ID
	 * @反馈值	实体
	 */
	@Override
	public void delete(T object)
	{
		getDao().delete(object);
	}
	
	/**
	 * @作者                贾明
	 * @编辑时间  2013-5-09 上午10:48:42
	 * @功能描述      根据id删除数据方法
	 * @输入参数  Long类型的ID
	 * @反馈值	  实体
	 */
	@Override
	public void deleteById(Long id)
	{		
		getDao().deleteById(id);
	}
	
	@Override
	public DatagridJson createJson(DatagridBean db)
	{
		return getDao().createJson(db);
	}
	
	@Override
	public DatagridJson createJson(DatagridBean db,String hql)
	{
		return getDao().createJson(db, hql);
	}
	
	@Override
	public void saveOrUpdate(T object)
	{
		this.getDao().saveOrUpdate(object);
	}
	
	@Override
	public void merge(T object)
	{
		this.getDao().megre(object);
	}
	
	/**
	 * @作者            贾明
	 * @编辑时间 2013-5-09 上午10:48:42
	 * @功能描述   根据相应的参数查找单个实体
	 * @输入参数   实体参数
	 * @反馈值       实体
	 */
	@Override
	public T queryEntity(String hql,Object[] params)
	{
		return this.getDao().queryEntity(hql, params);
	}
	
	/**
	 * @作者            贾明
	 * @编辑时间 2013-5-09 上午10:48:42
	 * @功能描述   根据相应的参数查找实体集合
	 * @输入参数   实体参数
	 * @反馈值       实体
	 */
	@Override
	public List<T> queryList(String hql, Object[] params) 
	{
		return this.getDao().queryList(hql, params);
	}
	
	/**
	 * 
	 * @作者		王杰彬
	 * @创建日期	2015年7月25日 下午5:54:19
	 * @功能描述	使object成为托管状态
	 * @输入参数	
	 * @反馈值
	 */
	@Override
	public void evict(T object)
	{
		this.getDao().evict(object);
	}

}
