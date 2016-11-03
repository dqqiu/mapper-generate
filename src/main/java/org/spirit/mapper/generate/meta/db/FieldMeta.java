package org.spirit.mapper.generate.meta.db;

/**
 * @Project       : mapper-generate
 * @Program Name  : org.spirit.mapper.generate.convert.DataTypeMeta.java
 * @Description   : qiudequan 转换类元数据
 * @Author        : qiudequan
 * @Creation Date : 2016年10月29日 下午10:33:04 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * qiudequan     2016年10月29日        create
 */
public class FieldMeta {
  private String type;
  
  private String javaType;
  
  private String javaPackage;
  
  private String comment;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getJavaType() {
    return javaType;
  }

  public void setJavaType(String javaType) {
    this.javaType = javaType;
  }

  public String getJavaPackage() {
    return javaPackage;
  }

  public void setJavaPackage(String javaPackage) {
    this.javaPackage = javaPackage;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
  
}
