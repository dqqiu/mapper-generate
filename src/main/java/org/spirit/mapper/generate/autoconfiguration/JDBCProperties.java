package org.spirit.mapper.generate.autoconfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Project       : mapper-generate
 * @Program Name  : org.spirit.mapper.generate.autoconfiguration.JDBCProperties.java
 * @Description   : qiudequan JDBC属性配置类
 * @Author        : qiudequan
 * @Creation Date : 2016年10月29日 下午11:30:38 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * qiudequan     2016年10月29日        create
 */

@ConfigurationProperties(prefix = "jdbc")
public class JDBCProperties {
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
  
}
