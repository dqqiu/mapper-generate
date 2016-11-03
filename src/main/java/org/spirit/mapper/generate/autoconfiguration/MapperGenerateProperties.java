package org.spirit.mapper.generate.autoconfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project       : mapper-generate
 * @Program Name  : org.spirit.mapper.generate.autoconfiguration.MapperGenerateProperties.java
 * @Description   : qiudequan 类描述
 * @Author        : qiudequan
 * @Creation Date : 2016年11月3日 上午11:01:23 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * qiudequan     2016年11月3日        create
 */
@Component
@ConfigurationProperties(prefix = "mapper.generate")
public class MapperGenerateProperties {
  /** 代码生成配置文件路径 */
  private String xmlPath = "META-INF/mapper-generate.xml";

  public String getXmlPath() {
    return xmlPath;
  }

  public void setXmlPath(String xmlPath) {
    this.xmlPath = xmlPath;
  }
}
