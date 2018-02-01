package com.xtlh.util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.xtlh.util.easyUI.DatagridBean;
import com.xtlh.util.easyUI.DatagridJson;

/**
 * @作者          贾 明
 * @创建日期      2012-4-17 上午10:03:16
 * @功能描述      描述  公共接口的实现类
 *
 */
public class BaseDaoImpl<T extends Serializable>   implements IBaseDao<T>
{
	
//	在使用Spring框架中@Autowired标签时默认情况下使用 @Autowired 注释进行自动注入时，
//	Spring 容器中匹配的候选 Bean 数目必须有且仅有一个。当找不到一个匹配的 Bean 时，
//	Spring 容器将抛BeanCreationException 异常，并指出必须至少拥有一个匹配的 Bean。
//	Spring 允许我们通过 @Qualifier 注释指定注入 Bean 的名称，这样歧义就消除了，可以通过下面的方法解决异常。
//	
//	@Qualifier("XXX") 中的 XX是 Bean 的名称，所以 @Autowired 和 @Qualifier 结合使用时，
//	自动注入的策略就从 byType 转变成 byName 了。
//	@Autowired 可以对成员变量、方法以及构造函数进行注释，而 @Qualifier 的标注对象是成员变量、方法入参、构造函数入参。
//	
	
	
	@Autowired 
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	private Class<T> persistentClass;
	
	public Class<T> getPersistentClass() 
	{
		return persistentClass;
	}
	
    public Session getSession()
    {
    	//事务必须是开启的，否则获取不到
	    return sessionFactory.getCurrentSession();
    }
    
    /**
	 * 在构造函数中利用反射机制获得参数T的具体类
	 */
    public BaseDaoImpl()
	{
		persistentClass = ReflectUtils.getClassGenricType(getClass());
    }
	/**   
	 * @功能描述  删除信息
	 * @输入参数  实体对象
	 * @反馈值    无
	 */
    @Override
	public void delete(T object) 
	{
		getSession().delete(object);
	}

	/**   
	 * @功能描述  查找全部信息
	 * @输入参数  无
	 * @反馈值    对象集合
	 */	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() 
	{
		 return (List<T>)getSession().createCriteria(this.persistentClass).list();
	}

	/**   
	 * @功能描述  查找相对应的实体类（此类为公共通用类，查询实体可以进行调用）
	 * @输入参数  对象的参数，可以任意多个
	 * @反馈值    实体
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T findEntity(String hql,Object[] params)
	{
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		return (T)query.uniqueResult();
	}
	/**   
	 * @功能描述  查找相对应的实体类（此类为公共通用类，查询实体可以进行调用）
	 * @输入参数  对象的参数，可以任意多个
	 * @反馈值    实体
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findList(String hql,Object[] params)
	{
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		return query.list();
	}
	
	/**   
	 * @功能描述  查找相对应的实体类（此类为公共通用类，查询实体可以进行调用）
	 * @输入参数  对象的参数，可以任意多个
	 * @反馈值    实体
	 */
	private void setQueryParams(Query query, Object[] params) 
	{
		if (null == params) 
		{
			return;
		}
		for (int i = 0; i < params.length; i++)
		{
			query.setParameter(i, params[i]);
		}
	}
	/**   
	 * @功能描述  查找信息
	 * @输入参数  对象id
	 * @反馈值    实体
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T findById(Long id) 
	{
		return (T) getSession().get(this.getPersistentClass(),id);
	}

	/**   
	 * @功能描述  查找信息
	 * @输入参数  对象id
	 * @反馈值    实体
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T findById(int id) 
	{
		return (T) getSession().get(this.getPersistentClass(),id);
	}
	
	/**   
	 * @功能描述  删除信息
	 * @输入参数  对象Id
	 * @反馈值    无
	 */
	@Override
	public void deleteById(Long id) 
	{
		T t = this.findById(id);
		if(null!=t)
		{
			getSession().delete(t);
		}
	}
	

	/**   
	 * @功能描述  保存信息
	 * @输入参数  实体对象
	 * @反馈值    无
	 */
	@Override
	public void save(T object) 
	{
		getSession().save( object);
	}
	
	
	
	/**   
	 * @功能描述  修改信息
	 * @输入参数  实体对象
	 * @反馈值    无
	 */
	@Override
	public void update(T object) 
	{
		getSession().update(object);
	}
	
	/**
	 * @功能描述	根据更新语句更新数据
	 * @输入参数	hql
	 * @反馈值
	 */
	@Override
	public void updatas(String hql)
	{
		getSession().createQuery(hql).executeUpdate();
	}
	
	/**
	 * @功能描述	根据更新语句更新数据
	 * @输入参数	sql
	 * @反馈值
	 */
	@Override
	public void updateSQL(String sql)
	{
		getSession().createSQLQuery(sql).executeUpdate();
	}
    /**
     * 
     * @功能描述   修改信息
     * @输入参数   对象id 
     * @反馈值     无
     */ 
	@Override
	public void updateById(Long  id) 
	{
		T t=this.findById(id);
		this.update(t);
	}
	/**
	 * 
	 * @功能描述   修改信息
	 * @输入参数   对象id 
	 * @反馈值     无
	 */ 
	@Override
	public void updateById(int  id) 
	{
		T t=this.findById(id);
		this.update(t);
	}
	 
	
	/**
	 * @作者      贾明
	 * @编辑时间  2013-5-09 上午10:48:42
	 * @功能描述  根据相应的参数查找对应实体
	 * @输入参数  实体参数
	 * @反馈值	  实体
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T queryEntity(String hql, Object[] params) 
	{
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		return (T) query.uniqueResult();
	}
	
	/**
	 * @作者            贾明
	 * @编辑时间 2013-5-09 上午10:48:42
	 * @功能描述   根据相应的参数查找实体集合
	 * @输入参数   实体参数
	 * @反馈值       实体
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryList(String hql, Object[] params) 
	{
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		setQueryParams(query, params);
		return query.list();
	}

	/**
	 * @作者     杨威
	 * @功能描述 创建调用datagrid组件所需要的json对象，控制层直接调用方法
	 * @输入参数
	 * @反馈值
	 */
	@Override
	public DatagridJson createJson(DatagridBean db)
	{
		Session session = this.getSession();
		String hql = this.createFromHql();
		//拼接条件查询sql语句
		if (db.getQueryparam() != null)
		{
			hql += this.createWhereHql(db.getQueryparam());
		}
		//拼接排序sql语句
		if (db.getSort() != null && db.getOrder() != null)
		{
			hql += this.createOrderHql(db.getSort(), db.getOrder());
		}
		Query query = session.createQuery(hql);
		
		long total = countNum(hql);
		
		query.setFirstResult((db.getPage() - 1) * db.getRows());
		query.setMaxResults(db.getRows());
		List<?> rows = query.list();
		DatagridJson dj = new DatagridJson(total,rows);
		return dj;
	}
	
	/**
	 * @作者     杨威
	 * @功能描述  获取查询的数据条数
	 * @输入参数  
	 * @反馈值
	 */
	private long countNum(String hql)
	{
		ClassMetadata meta = this.sessionFactory.getClassMetadata(this.getPersistentClass());
		String idName = meta.getIdentifierPropertyName();
		String strHql = "select count(" + idName + ") " + hql + "  ";
		return ((Number)getSession().createQuery(strHql).iterate().next()).intValue();
	}
	/**
	 * @作者     杨威
	 * @功能描述  创建调用datagrid组件所需要的json对象，控制层直接调用方法
	 * @输入参数  str为自定义查询语句
	 * @反馈值
	 */
	@Override
	public DatagridJson createJson(DatagridBean db,String str)
	{
		Session session = this.getSession();
		String hql = this.createFromHql();
		if (db.getQueryparam() != null) //js组装了查询条件
		{
			hql += this.createWhereHql(db.getQueryparam());
		}
		hql += " and " + str;
		//拼接排序sql语句
		if (db.getSort() != null && db.getOrder() != null)
		{
			hql += this.createOrderHql(db.getSort(), db.getOrder());
		}	
		Query query = session.createQuery(hql);

		long total = countNum(hql);
		//hibernate分页
		query.setFirstResult((db.getPage() - 1) * db.getRows());
		query.setMaxResults(db.getRows());
		List<?> rows = query.list();
		DatagridJson dj = new DatagridJson(total,rows);
		return dj;
	}
	
	/**
	 * @作者     杨威
	 * @功能描述 拼接where hql语句
	 * @输入参数
	 * @反馈值
	 */
	private String createFromHql()
	{
		String entityName = this.persistentClass.getName();
		String hql = "from " + entityName + " t where 1=1 ";
		return hql;
	}

	/**
	 * @作者     杨威
	 * @功能描述 凭借order hql语句
	 * @输入参数
	 * @反馈值
	 */
	private String createOrderHql(String sort, String order)
	{
		String hql = " order by " + sort + " " + order;
		return hql;
	}
	
	/**
	 * 
	 * @功能描述 拼接条件查询hql语句
	 * @输入参数
	 * @反馈值
	 */
	private String createWhereHql(String queryparam)
	{
		String strWhereCondition = "";
		if (!queryparam.equals(""))// 查询条件不为空
		{
			if (queryparam.indexOf("^") > 0)// 一个以上查询条件
			{
				String arrGroup[] = queryparam.split("\\^");
				int intGroupLen = arrGroup.length;
				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < intGroupLen; i++)
				{
					if (arrGroup[i].indexOf("$") > 0)
					{
						
						String arrCondition[] = arrGroup[i].split("\\$");
						if (arrCondition[1].indexOf("%") == -1
								&& arrCondition[1].indexOf(",") == -1
								&& arrCondition[1].indexOf("数") == -1
								&&arrCondition[1].indexOf("与") == -1)// 完全查询
						{
							sb.append("".equals(arrCondition[1]) ? "" : " and " + arrCondition[0]
									+ " = '" + arrCondition[1].replaceAll("'", "") + "'");
						} else if (arrCondition[1].indexOf(",") > 0)
						{
							sb.append("".equals(arrCondition[1]) ? "" : " and " + arrCondition[0]
									+ " in (" + arrCondition[1] + ")");
						} else if (arrCondition[1].indexOf("数") > -1)
						{
							sb.append("".equals(arrCondition[1]) ? "" : " and " + arrCondition[0]
									+ " >= " + arrCondition[1].replace("数", "") + " ");
						}else if (arrCondition[1].indexOf("与") > -1)
						{
							String values[]=arrCondition[1].split("\\与");
							sb.append(" and "+arrCondition[0]+" between "+values[0]+" and "+values[1]);
						}
						else
						// 模糊查询
						{
							sb.append("".equals(arrCondition[1]) ? "" : " and " + arrCondition[0]
									+ " like '" + arrCondition[1] + "'");
						}
						strWhereCondition = sb.toString();
					} else if (arrGroup[i].indexOf(".") == -1 && arrGroup[i].indexOf("#") > 0)
					{
						String arrCondition[] = arrGroup[i].split("\\#");
						sb.append("".equals(arrCondition[1]) ? "" : " and " + arrCondition[0]
								+ " like '" + arrCondition[1] + "'");
						strWhereCondition = sb.toString();
					} else if (arrGroup[i].indexOf(".") > 0)
					{
						String arrCondition[] = arrGroup[i].split("\\.");
						String time1[] = arrCondition[0].split("\\#");
						String time2[] = arrCondition[1].split("\\#");
						sb.append("".equals(time1[1]) ? "" : "  and " + time1[0] + ">= '"
								+ time1[1] + "'  and " + time1[0] + " <= '" + time2[1]
								+ " 23:59:59'");
						strWhereCondition = sb.toString();

					} else
					{
						System.out.println("查询条件格式不对，要形如'username$jcj||address$北京'");
						break;
					}
				}
			} else
			// 只有一个查询条件
			{
				StringBuilder sb = new StringBuilder();

				if (queryparam.indexOf(".") > 0 && queryparam.indexOf("#") > 0)
				{
					String strTimes[] = queryparam.split("\\.");
					String time1[] = strTimes[0].split("\\#");
					String time2[] = strTimes[1].split("\\#");
					sb.append("".equals(time1[1]) ? "" : " and " + time1[0] + ">= '" + time1[1]
							+ "'  and " + time1[0] + " <= '" + time2[1] + " 23:59:59'");
					strWhereCondition = sb.toString();
				} else if (queryparam.indexOf("#") > 0)
				{
					String arrCondition[] = queryparam.split("\\#");
					sb.append("".equals(arrCondition[1]) ? "" : " and " + arrCondition[0]
							+ " like '" + arrCondition[1] + "'");
					strWhereCondition = sb.toString();
				} else
				{
					if (queryparam.indexOf("$") > 0)
					{
						String arrCondition[] = queryparam.split("\\$");
						if (arrCondition[1].indexOf("%") == -1
								&& arrCondition[1].indexOf(",") == -1
								&&arrCondition[1].indexOf("与") == -1)// 完全查询
						{
							sb.append("".equals(arrCondition[1]) ? "" : " and " + arrCondition[0]
									+ " = '" + arrCondition[1].replaceAll("'", "") + "'");
						} else if (arrCondition[1].indexOf(",") > 0)
						{
							sb.append("".equals(arrCondition[1]) ? "" : " and " + arrCondition[0]
									+ " in (" + arrCondition[1] + ")");
						}else if (arrCondition[1].indexOf("与") > -1)
						{
							String values[]=arrCondition[1].split("\\与");
							sb.append(" and "+arrCondition[0]+" between "+values[0]+" and "+values[1]);
						}else
						// 模糊查询
						{
							sb.append("".equals(arrCondition[1]) ? "" : " and " + arrCondition[0]
									+ " like '" + arrCondition[1] + "'");
						}
						strWhereCondition = sb.toString();
					} else
					{
						System.out.println("查询条件格式不对，要形如'username$jcj||address$北京'");
					}
				}
			}
		}
		return strWhereCondition;
	}

	/**
	 * @作者     杨威
	 * @功能描述  保存或者更新实体
	 * @输入参数  
	 * @反馈值
	 */
	@Override
	public void saveOrUpdate(T object)
	{
		Session session = this.getSession();
		session.saveOrUpdate(object);
	}

	/**	
	 * @作者     杨威
	新new一个对象，如果该对象设置了ID，则这个对象就当作游离态处理：
	当ID在数据库中不能找到时，用update的话肯定会报异常，然而用merge的话，就会insert。
	当ID在数据库中能找到的时候，update与merge的执行效果都是更新数据，发出update语句；
	如果没有设置ID的话，则这个对象就当作瞬态处理：
	用update的话，由于没有ID，所以会报异常，merge此时则会保存数据，根据ID生产策略生成一条数据；
	*/
	@Override
	public void megre(T object)
	{
		this.getSession().merge(object);	
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
		this.getSession().evict(object);
	}

	@Override
	public SQLQuery createSQLQuery(String sql)
	{
		return this.getSession().createSQLQuery(sql);
	}

	
}





