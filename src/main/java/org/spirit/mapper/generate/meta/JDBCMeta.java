package org.spirit.mapper.generate.meta;

import org.spirit.mapper.generate.exception.MapperGenerateException;
import org.springframework.util.StringUtils;

/**
 * @Project       : mapper-generate
 * @Program Name  : org.spirit.mapper.generate.meta.JDBCMeta.java
 * @Description   : qiudequan JDBC元数据
 * @Author        : qiudequan
 * @Creation Date : 2016年11月1日 下午10:16:19 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * qiudequan     2016年11月1日        create
 */
public class JDBCMeta {
  private static final String ERROR_MESSAGE = "数据库连接数据异常";
  private String driver;
  private String url;
  private String user;
  private String password;
  public String getDriver() {
    return driver;
  }
  public void setDriver(String driver) {
    this.driver = driver;
  }
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  public String getUser() {
    return user;
  }
  public void setUser(String user) {
    this.user = user;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  
  public void validate() throws MapperGenerateException{
    if(StringUtils.isEmpty(this.driver) 
        || StringUtils.isEmpty(this.url)
        || StringUtils.isEmpty(this.user)
        || StringUtils.isEmpty(this.password)){
      throw new MapperGenerateException(ERROR_MESSAGE);
    }
  }
}
