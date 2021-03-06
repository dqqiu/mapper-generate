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
  private static final char UNDERLINE = '_';
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

  /**
   *  @Description  : qiudequan 字符串拼接，适用于较短的字符串拼接
   *  @return         : String
   *  @Creation Date  : 2016年10月13日 下午5:59:22 
   *  @Author         : qiudequan
   */
  public static String append(String... sources) {
    if(sources == null || sources.length == 0){
      return "";
    }
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < sources.length; i++) {
      builder.append(sources[i]);
    }
    return builder.toString();
  }

  /**
   *  @Description  : qiudequan 以"_"隔开的字符串转换为驼峰命名
   *  @param          : @param source 源字符串
   *  @param          : @return
   *  @return       : String
   *  @Creation Date  : 2016年10月31日 下午1:13:45 
   *  @Author         : qiudequan
   */
  public static String camel(String source){
    if(isEmpty(source)){
      return "";
    }
    int length = source.length();
    StringBuilder stringBuilder = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      char c = source.charAt(i);
      if(c == UNDERLINE){
        if(++i < length){
          stringBuilder.append(Character.toUpperCase(source.charAt(i)));
        }
      } else {
        stringBuilder.append(c);
      }
    }
    return stringBuilder.toString();
  }

  /**
   *  @Description  : qiudequan 驼峰命名转换为以"_"隔开的命名
   *  @param          : @param source 源字符串
   *  @param          : @return
   *  @return       : String
   *  @Creation Date  : 2016年10月31日 下午1:20:38 
   *  @Author         : qiudequan
   */
  public static String camelToUnderline(String source){
    if(isEmpty(source)){
      return "";
    }
    int length = source.length();
    StringBuilder stringBuilder = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      char c = source.charAt(i);
      if(Character.isUpperCase(c)){
        stringBuilder.append(UNDERLINE);
        stringBuilder.append(Character.toLowerCase(c));
      } else {
        stringBuilder.append(c);
      }
    }
    return stringBuilder.toString();
  }

  /**
   *  @Description  : qiudequan 首字母大写
   *  @param          : @param source
   *  @param          : @return
   *  @return       : String
   *  @Creation Date  : 2016年10月31日 下午1:35:41 
   *  @Author         : qiudequan
   */
  public static String firstLetterToUpper(String source){
    if(isEmpty(source)){
      return "";
    }
    char[] chars = source.toCharArray();
    chars[0] -= 32;
    return String.valueOf(chars);
  }

  public static Integer integerOf(String source){
    if(isEmpty(source)){
      return null;
    }
    try {
      return Integer.valueOf(source);
    } catch (NumberFormatException e) {
      return null;
    }
  }
  
  public static String replaceAll(String str, String sourceStr, String replaceStr){
    if(isEmpty(str)){
      return str;
    }
    return str.replaceAll(sourceStr, replaceStr);
  }
  
  /**
   *  @Description	: qiudequan 获取字符串中最后一个"."后的字符
   *  @param          : @param source
   *  @param          : @return
   *  @return 		: String
   *  @Creation Date  : 2016年11月11日 下午4:10:55 
   *  @Author         : qiudequan
   */
  public static String getStrAfterLastestPoint(String source) {
    if(isEmpty(source)) {
      return "";
    }
    return source.substring(source.lastIndexOf(".") + 1);
  }
}
