package com.xtlh.util;

import java.util.List;

import com.xtlh.util.easyUI.DatagridBean;
import com.xtlh.util.easyUI.DatagridJson;

/**
 * @作者       江成军
 * @创建日期   2013-4-3
 * @功能描述   业务层公共抽象接口 
 *
 */

public interface IBaseService<T>
{
		
		
		/**
		 * @作者      贾明
		 * @编辑时间  2013-5-03 下午04:48:42
		 * @功能描述  查询实体所有信息
		 * @输入参数  无
		 * @反馈值	  实体集合
		 */
		public List<T> findAll();
		
		/**
		 * @作者      贾明
		 * @编辑时间  2013-5-03 下午04:48:42
		 * @功能描述  新增实体信息
		 * @输入参数  实体
		 * @反馈值	  无
		 */
		public void save(T object);
		
		
		
		/**
		 * @作者      贾明
		 * @编辑时间  2013-5-03 下午04:48:42
		 * @功能描述  根据ID查询相应的实体
		 * @输入参数  ID
		 * @反馈值	  无
		 */
		public T findById(Long id);
		/**
		 * @作者      贾明
		 * @编辑时间  2013-5-03 下午04:48:42
		 * @功能描述  根据ID查询相应的实体
		 * @输入参数  ID
		 * @反馈值	  无
		 */
		public T findById(int id);
		
		
		/**
		 * @作者      贾明
		 * @编辑时间  2013-5-03 下午04:48:42
		 * @功能描述  更新实体信息
		 * @输入参数  实体
		 * @反馈值	  无
		 */
		public void update(T object);

		
		/**
		 * @功能描述	根据更新语句更新数据
		 * @输入参数	hql
		 * @反馈值
		 */
		public void updatas(String hql);
		
		public void change(T object);
		/**
		 * @作者      贾明
		 * @编辑时间  2013-5-03 下午04:48:42
		 * @功能描述  根据ID查询相应的实体
		 * @输入参数  ID
		 * @反馈值	  无
		 */
		public void updateById(Long  id);
		/**
		 * @作者      贾明
		 * @编辑时间  2013-5-03 下午04:48:42
		 * @功能描述  根据ID查询相应的实体
		 * @输入参数  ID
		 * @反馈值	  无
		 */
		public void updateById(int  id);

		/**
		 * @作者      贾明
		 * @编辑时间  2013-5-03 下午04:48:42
		 * @功能描述  删除实体信息
		 * @输入参数  实体
		 * @反馈值	  无
		 */
		public void delete(T object);
		/**
		 * @作者      贾明
		 * @编辑时间  2013-5-03 下午04:48:42
		 * @功能描述  删除实体信息
		 * @输入参数  实体
		 * @反馈值	  无
		 */
		public void deleteById(Long  id);
		
		/**
		 * @作者      杨威
		 * @功能描述  创建调用datagrid组件所需要的json对象，控制层直接调用方法
		 * @输入参数  
		 * @反馈值
		 */
		public DatagridJson createJson(DatagridBean db);
		
		/**
		 * @作者      杨威
		 * @功能描述  创建调用datagrid组件所需要的json对象，控制层直接调用方法（重载方法，追加查询条件）
		 * @输入参数  
		 * @反馈值
		 */
		public DatagridJson createJson(DatagridBean db,String hql);
		
		/**
		 * @作者      杨威
		 * @功能描述  更新或保存
		 * @输入参数  
		 * @反馈值
		 */
		public void saveOrUpdate(T object);
		
		/**
		 * @作者      杨威
	 	新new一个对象，如果该对象设置了ID，则这个对象就当作游离态处理：
		当ID在数据库中不能找到时，用update的话肯定会报异常，然而用merge的话，就会insert。
		当ID在数据库中能找到的时候，update与merge的执行效果都是更新数据，发出update语句；
		如果没有设置ID的话，则这个对象就当作瞬态处理：
		用update的话，由于没有ID，所以会报异常，merge此时则会保存数据，根据ID生产策略生成一条数据；
		 */
		public void merge(T object);
		
		/**
		 * @作者            贾明
		 * @编辑时间 2013-5-09 上午10:48:42
		 * @功能描述   根据相应的参数查找实体集合
		 * @输入参数   实体参数
		 * @反馈值       实体
		 */
		public List<T> queryList(String hql, Object[] params);
		
		/**
		 * @作者            贾明
		 * @编辑时间 2013-5-09 上午10:48:42
		 * @功能描述   根据相应的参数查找单个实体
		 * @输入参数   实体参数
		 * @反馈值       实体
		 */
		public T queryEntity(String hql, Object[] params);
		
		/**
		 * 
		 * @作者		王杰彬
		 * @创建日期	2015年7月25日 下午5:54:19
		 * @功能描述	使object成为托管状态
		 * @输入参数	
		 * @反馈值
		 */
		public void evict(T object);
}
