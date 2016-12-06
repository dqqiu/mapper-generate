package org.spirit.mapper.generate.meta.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spirit.mapper.generate.enums.MysqlTypeEnum;
import org.spirit.mapper.generate.utils.StringUtils;

/**
 * @Project       : mapper-generate
 * @Program Name  : org.spirit.mapper.generate.convert.TableMeta.java
 * @Description   : qiudequan 数据库表元数据
 * @Author        : qiudequan
 * @Creation Date : 2016年10月29日 下午11:06:36 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * qiudequan     2016年10月29日        create
 */
public class TableMeta {
  /** 主键 */
  private String[] primaryKey = {"id"};
  /** 表名 */
  private String name;

  /** 驼峰名 */
  private String camelName;

  /** 表注释 */
  private String tableComment;

  /** 表字段 */
  private List<FieldMeta> fields;

  /** sql-java类型映射Map */
  private Map<String, String> typeMap;
  /** sql-java类型映射Map数据大小 */
  private Integer typeMapSize;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTableComment() {
    return tableComment;
  }

  public void setTableComment(String tableComment) {
    this.tableComment = tableComment;
  }

  public List<FieldMeta> getFields() {
    return fields;
  }

  public void setFields(List<FieldMeta> fields) {
    this.fields = fields;
  }

  public String getCamelName() {
    return camelName;
  }

  public void setCamelName(String camelName) {
    this.camelName = camelName;
  }

  public String[] getPrimaryKey() {
    return primaryKey;
  }

  public void setPrimaryKey(String[] primaryKey) {
    this.primaryKey = primaryKey;
  }

  public Map<String, String> getTypeMap() {
    if(this.typeMap == null || this.typeMap.isEmpty() || (this.typeMap != null && this.typeMapSize != null && this.typeMap.size() == this.typeMapSize)){
      return getFieldTypeMap();
    }
    return typeMap;
  }

  public void setTypeMap(Map<String, String> typeMap) {
    this.typeMap = typeMap;
  }
  
  public Integer getTypeMapSize() {
    return typeMapSize;
  }

  public void setTypeMapSize(Integer typeMapSize) {
    this.typeMapSize = typeMapSize;
  }

  /**
   *  @Description	: qiudequan 获取所有字段名
   *  @param          : 
   *  @return 		: List<String>
   *  @Creation Date  : 2016年11月3日 下午1:38:35 
   *  @Author         : qiudequan
   */
  public List<String> getFieldNames(){
    if(fields == null || fields.size() == 0){
      return null;
    }
    List<String> fieldNames = new ArrayList<>();
    for (FieldMeta fieldMeta : fields) {
      fieldNames.add(fieldMeta.getName());
    }
    return fieldNames;
  }

  /**
   *  @Description	: qiudequan 获取字段类型映射
   *  @param          : @return
   *  @return 		: Map<String,String>
   *  @Creation Date  : 2016年11月11日 下午2:49:59 
   *  @Author         : qiudequan
   */
  public Map<String, String> getFieldTypeMap(){
    if(fields != null && fields.size() != 0){
      for (FieldMeta fieldMeta : fields) {
        String fieldType = fieldMeta.getType();
        if(typeMap.containsKey(fieldType)) {
          continue;
        }
        MysqlTypeEnum mysqlTypeEnum = MysqlTypeEnum.get(fieldType);
        if(mysqlTypeEnum != null){
          String javaPackage = mysqlTypeEnum.getJavaPackage();
          typeMap.put(fieldType, javaPackage);
        }
      }
    }
    return typeMap;
  }

  public String getFirstLetterUpperName(){
    return StringUtils.firstLetterToUpper(StringUtils.camel(this.name));
  }

  /**
   *  @Description	: qiudequan 获取字段和java类型映射Map集合
   *  @param          : @return
   *  @return 		: Map<String,String>
   *  @Creation Date  : 2016年11月9日 上午10:23:15 
   *  @Author         : qiudequan
   */
  public Map<String, String> getFieldJavaTypeMap(){
    Map<String, String> fieldJavaTypeMap = new HashMap<>();
    if(fields != null && fields.size() != 0){
      for (FieldMeta fieldMeta : fields) {
        String fieldName = fieldMeta.getName();
        String fieldType = fieldMeta.getType();
        // 默认读取用户自定义的sql-java类型映射
        if(this.typeMap != null && StringUtils.isNotEmpty(this.typeMap.get(fieldType))) {
          fieldJavaTypeMap.put(fieldName, this.typeMap.get(fieldType));
          continue;
        }
        MysqlTypeEnum mysqlTypeEnum = MysqlTypeEnum.get(fieldType);
        if(mysqlTypeEnum != null){
          String javaPackage = mysqlTypeEnum.getJavaPackage();
          fieldJavaTypeMap.put(fieldName, javaPackage);
        }
      }
    }
    return fieldJavaTypeMap;
  }

}
