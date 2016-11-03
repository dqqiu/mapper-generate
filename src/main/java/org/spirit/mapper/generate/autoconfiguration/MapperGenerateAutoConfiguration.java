package org.spirit.mapper.generate.autoconfiguration;

import org.spirit.mapper.generate.analyze.MapperGenerateXmlAnalyze;
import org.spirit.mapper.generate.meta.GenerateMeta;
import org.spirit.mapper.generate.utils.JDBCUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project       : mapper-generate
 * @Program Name  : org.spirit.mapper.generate.autoconfiguration.MapperGenerateAutoConfiguration.java
 * @Description   : qiudequan 类描述
 * @Author        : qiudequan
 * @Creation Date : 2016年11月3日 上午10:52:14 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * qiudequan     2016年11月3日        create
 */
@Configuration
public class MapperGenerateAutoConfiguration {
  
  @Bean
  @ConditionalOnMissingBean(value = GenerateMeta.class)
  public GenerateMeta getGenerateMata(){
    return MapperGenerateXmlAnalyze.analyze();
  }
  
  @Bean
  @ConditionalOnBean(value = GenerateMeta.class)
  public JDBCUtils jdbcUtils(GenerateMeta generateMeta){
    return new JDBCUtils(generateMeta.getJdbcMeta());
  }
}
