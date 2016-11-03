package org.spirit.mapper.generate.utils;

/**
 * @Project       : mapper-generate
 * @Program Name  : org.spirit.mapper.generate.utils.StringUtils.java
 * @Description   : qiudequan 字符串工具类
 * @Author        : qiudequan
 * @Creation Date : 2016年11月1日 下午10:47:11 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * qiudequan     2016年11月1日        create
 */
public class StringUtils {
  /**
   *  @Description	: qiudequan 判断字符串是否为空
   *  @return         : boolean
   *  @Creation Date  : 2016年11月1日 下午10:47:27 
   *  @Author         : qiudequan
   */
  public static boolean isEmpty(String source){
    if(source == null || "".equals(source)){
      return true;
    }
    return false;
  }

  /**
   *  @Description	: qiudequan 判断字符串不为空
   *  @return         : boolean
   *  @Creation Date  : 2016年11月1日 下午10:47:39 
   *  @Author         : qiudequan
   */
  public static boolean isNotEmpty(String source){
    return !isEmpty(source);
  }
}
