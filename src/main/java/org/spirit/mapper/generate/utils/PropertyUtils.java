package org.spirit.mapper.generate.utils;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Project       : mapper-generate
 * @Program Name  : org.spirit.mapper.generate.utils.PropertyUtils.java
 * @Description   : qiudequan 系统属性工具类
 * @Author        : qiudequan
 * @Creation Date : 2016年11月3日 上午11:00:43 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * qiudequan     2016年11月3日        create
 */
@Component
public class PropertyUtils implements EnvironmentAware {

  private static Environment env;

  @Override
  public void setEnvironment(Environment environment) {
    env = environment;
  }

  public static String getProperty(String key) {
    return env.getProperty(key);
  }

}