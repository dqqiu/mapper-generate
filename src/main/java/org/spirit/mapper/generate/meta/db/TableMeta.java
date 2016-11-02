package org.spirit.mapper.generate.meta.db;

import java.util.List;

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
  /** 表名 */
  private String name;
  
  /** 表注释 */
  private String tableComment;
  
  /** 表字段 */
  private List<FieldMeta> fields;

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
  
}
