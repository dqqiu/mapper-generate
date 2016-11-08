package org.spirit.mapper.generate.meta.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spirit.mapper.generate.enums.MysqlTypeEnum;
import org.spirit.mapper.generate.meta.ModuleMeta;
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
  private String[] primaryKey;
  /** 表名 */
  private String name;
  
  /** 驼峰名 */
  private String camelName;

  /** 表注释 */
  private String tableComment;

  /** 表字段 */
  private List<FieldMeta> fields;
  
  private Map<String, ModuleMeta> moduleMetas;

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
  
  public Map<String, ModuleMeta> getModuleMetas() {
    return moduleMetas;
  }

  public void setModuleMetas(Map<String, ModuleMeta> moduleMetas) {
    this.moduleMetas = moduleMetas;
  }

  public String[] getPrimaryKey() {
    return primaryKey;
  }

  public void setPrimaryKey(String[] primaryKey) {
    this.primaryKey = primaryKey;
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

  public Map<String, String> getFieldTypeMap(){
    Map<String, String> typeMap = new HashMap<>();
    if(fields != null && fields.size() != 0){
      for (FieldMeta fieldMeta : fields) {
        String fieldType = fieldMeta.getType();
        MysqlTypeEnum mysqlTypeEnum = MysqlTypeEnum.get(fieldType);
        if(mysqlTypeEnum != null){
          String javaPackage = mysqlTypeEnum.getJavaPackage();
          String javaType = mysqlTypeEnum.getJavaType();
          typeMap.put(javaType, javaPackage);
        }
      }
    }
    return typeMap;
  }
  
  public String getFirstLetterUpperName(){
    return StringUtils.firstLetterToUpper(StringUtils.camel(this.name));
  }

}
