package org.spirit.mapper.generate.enums;

/**
 * @Project       : mapper-generate
 * @Program Name  : org.spirit.mapper.generate.enums.XMLElementEnum.java
 * @Description   : qiudequan xml文件元素枚举
 * @Author        : qiudequan
 * @Creation Date : 2016年10月30日 下午10:32:05 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * qiudequan     2016年10月30日        create
 */
public enum XMLElementEnum {
  ROOT("generate");
  
  
  private String elementName;
  
  private XMLElementEnum(String elementName){
    this.elementName = elementName;
  }
}
