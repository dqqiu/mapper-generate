package org.spirit.mapper.generate.meta.support;

import java.util.List;

/**
 * @Project       : mapper-generate
 * @Program Name  : org.spirit.mapper.generate.meta.support.BaseMapper.java
 * @Description   : qiudequan 类描述
 * @Author        : qiudequan
 * @Creation Date : 2016年11月7日 下午1:29:00 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * qiudequan     2016年11月7日        create
 */
public interface BaseMapper<T> {
  /**
   *  @Description	: qiudequan 新增记录
   *  @param          : @param t
   *  @param          : @return
   *  @return 		: int
   *  @Creation Date  : 2016年11月7日 下午2:13:57 
   *  @Author         : qiudequan
   */
  int insert(T t);
  /**
   *  @Description	: qiudequan 根据主键获取记录
   *  @param          : @param id
   *  @param          : @return
   *  @return 		: T
   *  @Creation Date  : 2016年11月7日 下午1:29:49 
   *  @Author         : qiudequan
   */
  T getByPrimaryKey(String id);
  
  /**
   *  @Description	: qiudequan 按条件查询
   *  @param          : @param t
   *  @param          : @return
   *  @return 		: List<T>
   *  @Creation Date  : 2016年11月7日 下午1:32:28 
   *  @Author         : qiudequan
   */
  List<T> getByCondition(T t);
  
  /**
   *  @Description	: qiudequan 查询符合条件的记录数
   *  @param          : @param t
   *  @param          : @return
   *  @return 		: int
   *  @Creation Date  : 2016年11月7日 下午1:52:56 
   *  @Author         : qiudequan
   */
  int getCountByCondition(T t);
  
  /**
   *  @Description	: qiudequan 按主键更新记录
   *  @param          : @param t
   *  @param          : @return
   *  @return 		: int
   *  @Creation Date  : 2016年11月7日 下午1:54:13 
   *  @Author         : qiudequan
   */
  int updateByPrimaryKey(T t);
  
  /**
   *  @Description	: qiudequan 按主键删除记录
   *  @param          : @param t
   *  @param          : @return
   *  @return 		: int
   *  @Creation Date  : 2016年11月7日 下午1:54:57 
   *  @Author         : qiudequan
   */
  int deleteByPrimaryKey(String id);
}
