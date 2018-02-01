package com.xtlh.util;

import java.util.List;

import org.hibernate.SQLQuery;

import com.xtlh.util.easyUI.DatagridBean;
import com.xtlh.util.easyUI.DatagridJson;




/**
 * @作者          贾 明
 * @创建日期      2012-4-17 上午10:03:16  
 * @功能描述      描述  公共的接口包括新增，修改，删除，通过id查找实体，通过Id修改
 *
 */
public interface IBaseDao<T>
{
	/**
	 * @功能描述  通过ID查找实体数据
	 * @输入参数  ID
	 * @反馈值    实体T
	 */
	public T findById(Long id);
	/**
	 * @功能描述  通过ID查找实体数据
	 * @输入参数  ID
	 * @反馈值    实体T
	 */
	public T findById(int id);

	/**
	 * @功能描述  查找实体所有数据
	 * @输入参数  无
	 * @反馈值    实体集合LIST<T>
	 */
	public List<T> findAll();
	
	/**   
	 * @功能描述  查找相对应的实体类（此类为公共通用类，查询实体可以进行调用）
	 * @输入参数  对象的参数，可以任意多个
	 * @反馈值    实体
	 */
	public T findEntity(String hql,Object[] params);
	
	/**   
	 * @功能描述  查找相对应的实体类集合（此类为公共通用类，查询实体可以进行调用）
	 * @输入参数  对象的参数，可以任意多个
	 * @反馈值    实体集合
	 */
	public List<T> findList(String hql,Object[] params);
	/**
	 * @功能描述  删除实体数据
	 * @输入参数  实体
	 * @反馈值    无
	 */
	public void delete(T object);
	
 
	/**
	 * @功能描述  新增数据
	 * @输入参数  实体
	 * @反馈值    无
	 */
	public void save(T object);

	/**
	 * @功能描述  更新实体数据
	 * @输入参数  实体
	 * @反馈值    无
	 */
	public void update(T object);

	
	/**
	 * @功能描述	根据更新语句更新数据
	 * @输入参数	hql
	 * @反馈值
	 */
	public void updatas(String hql);
	/**
	 * @功能描述  通过ID删除实体数据
	 * @输入参数  ID
	 * @反馈值    无
	 */
	/**
	 * @功能描述	根据更新语句更新数据
	 * @输入参数	sql
	 * @反馈值
	 */
	public void updateSQL(String sql);
	
	
	
	public void deleteById(Long id);

	
	/**
	 * @功能描述  通过ID更新实体数据
	 * @输入参数  
	 * @反馈值    
	 */
	public void updateById(Long  id);
    /**
     * @功能描述  通过ID更新实体数据
     * @输入参数  
     * @反馈值    
     */
    public void updateById(int  id); 
	
    /**
	 * @作者            贾明
	 * @编辑时间 2013-5-09 上午10:48:42
	 * @功能描述   根据相应的参数查找单个实体
	 * @输入参数   实体参数
	 * @反馈值       实体
	 */
	public T queryEntity(String hql, Object[] params);
	
	/**
	 * @作者            贾明
	 * @编辑时间 2013-5-09 上午10:48:42
	 * @功能描述   根据相应的参数查找集合
	 * @输入参数   实体参数
	 * @反馈值       实体
	 */
	public List<T> queryList(String hql, Object[] params);
	
	/**
	 * 
	 * @功能描述  构建easyui-datagrid组件需要的json对象
	 * @输入参数  
	 * @反馈值
	 */
	public DatagridJson createJson(DatagridBean db);
	
	/**
	 * 
	 * @功能描述  重载方法，支持自己拼装hql语句
	 * @输入参数  
	 * @反馈值
	 */
	public DatagridJson createJson(DatagridBean db,String hql);
	
	/**
	 * 
	 * @功能描述  保存或者更新实体
	 * @输入参数  
	 * @反馈值
	 */
	public void saveOrUpdate(T object);
	
	/**	
	新new一个对象，如果该对象设置了ID，则这个对象就当作游离态处理：
	当ID在数据库中不能找到时，用update的话肯定会报异常，然而用merge的话，就会insert。
	当ID在数据库中能找到的时候，update与merge的执行效果都是更新数据，发出update语句；
	如果没有设置ID的话，则这个对象就当作瞬态处理：
	用update的话，由于没有ID，所以会报异常，merge此时则会保存数据，根据ID生产策略生成一条数据；
	*/
	public void megre(T object);
	
	/**
	 * 
	 * @作者		王杰彬
	 * @创建日期	2015年7月25日 下午5:54:19
	 * @功能描述	使object成为托管状态
	 * @输入参数	
	 * @反馈值
	 */
	public void evict(T object);
	
	public SQLQuery createSQLQuery(String sql);
	
	
}
