package org.spirit.mapper.generate.enums;

import org.spirit.mapper.generate.utils.StringUtils;

/**
 * @Project       : mapper-generate
 * @Program Name  : org.spirit.mapper.generate.enums.XMLElementEnum.java
 * @Description   : qiudequan 模块枚举
 * @Author        : qiudequan
 * @Creation Date : 2016年10月30日 下午10:32:05 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * qiudequan     2016年10月30日        create
 */
public enum ModuleElementEnum {
  MODEL("model", ".java", ""),
  VO("vo", ".java", "Vo"),
  MAPPER("mapper", ".java", "Mapper"),
  XML("xml", ".xml", "Mapper"),
  SERVICE("service", ".java", "Service"),
  CONTROLLER("controller", ".java", "Controller");
  
  private String moduleName;
  private String fileSuffix;
  private String nameSuffix;
  
  private ModuleElementEnum(String moduleName, String fileSuffix, String nameSuffix){
    this.moduleName = moduleName;
    this.fileSuffix = fileSuffix;
    this.nameSuffix = nameSuffix;
  }
  
  public static String getNameSuffix(String moduleName){
    if(StringUtils.isEmpty(moduleName)){
      return "";
    }
    for (ModuleElementEnum moduleElementEnum : ModuleElementEnum.values()) {
      if(moduleElementEnum.moduleName.equals(moduleName)){
        return moduleElementEnum.nameSuffix;
      }
    }
    return "";
  }
  
  public static String getFileSuffix(String moduleName){
    if(StringUtils.isEmpty(moduleName)){
      return "";
    }
    for (ModuleElementEnum moduleElementEnum : ModuleElementEnum.values()) {
      if(moduleElementEnum.moduleName.equals(moduleName)){
        return moduleElementEnum.fileSuffix;
      }
    }
    return "";
  }
}
