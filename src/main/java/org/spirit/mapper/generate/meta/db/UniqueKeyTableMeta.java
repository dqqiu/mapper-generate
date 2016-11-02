package org.spirit.mapper.generate.meta.db;

/**
 * @Project       : mapper-generate
 * @Program Name  : org.spirit.mapper.generate.convert.UniqueKeyTableMeta.java
 * @Description   : qiudequan (唯一主键)表元数据
 * @Author        : qiudequan
 * @Creation Date : 2016年10月29日 下午11:02:42 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * qiudequan     2016年10月29日        create
 */
public class UniqueKeyTableMeta extends TableMeta {
  /** 主键 */
  private String primaryKey;

  public String getPrimaryKey() {
    return primaryKey;
  }

  public void setPrimaryKey(String primaryKey) {
    this.primaryKey = primaryKey;
  }
  
}
