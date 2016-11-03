package org.spirit.mapper.generate.meta.db;

import org.spirit.mapper.generate.enums.MysqlTypeEnum;
import org.spirit.mapper.generate.utils.StringUtils;

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
@SuppressWarnings("unused")
public class FieldMeta {
  private String name;
  
  private String type;
  
  private String camelName;
  
  private String firstLetterUpper;
  
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

  public String getCamelName() {
    if(StringUtils.isEmpty(this.name)){
      return "";
    }
    return StringUtils.camel(this.name);
  }

  public void setCamelName(String camelName) {
    this.camelName = camelName;
  }
  
  public String getFirstLetterUpper() {
    return StringUtils.firstLetterToUpper(getCamelName());
  }

  public void setFirstLetterUpper(String firstLetterUpper) {
    this.firstLetterUpper = firstLetterUpper;
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
    return MysqlTypeEnum.get(this.type).getJavaType();
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
  
  public String getColumnType() {
    if(StringUtils.isEmpty(this.type)){
      return "";
    }
    StringBuilder sBuilder = new StringBuilder(this.type);
    if(StringUtils.isNotEmpty(this.length)){
      sBuilder.append("(").append(this.length);
      if(StringUtils.isNotEmpty(this.digits)){
        sBuilder.append(", ").append(this.digits);
      }
      sBuilder.append(")");
    }
    return sBuilder.toString();
  }
  
}
