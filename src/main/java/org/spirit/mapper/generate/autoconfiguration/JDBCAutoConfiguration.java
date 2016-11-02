package org.spirit.mapper.generate.autoconfiguration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {JDBCProperties.class})
public class JDBCAutoConfiguration {

}
