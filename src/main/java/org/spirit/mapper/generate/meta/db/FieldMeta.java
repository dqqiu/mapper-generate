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
  private String name;
  
  private String type;
  
  private String fieldType;
  
  private String length;
  
  private String digits;
  
  private String nullable;
  
  private String javaType;
  
  private String javaPackage;
  
  private String comment;
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getFieldType() {
    return fieldType;
  }

  public void setFieldType(String fieldType) {
    this.fieldType = fieldType;
  }
  
  public String getLength() {
    return length;
  }

  public void setLength(String length) {
    this.length = length;
  }

  public String getDigits() {
    return digits;
  }

  public void setDigits(String digits) {
    this.digits = digits;
  }

  public String getNullable() {
    return nullable;
  }

  public void setNullable(String nullable) {
    this.nullable = nullable;
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
