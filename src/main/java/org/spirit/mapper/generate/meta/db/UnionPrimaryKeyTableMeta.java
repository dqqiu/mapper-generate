package org.spirit.mapper.generate.meta.db;

import java.util.List;

/**
 * @Project       : mapper-generate
 * @Program Name  : org.spirit.mapper.generate.convert.UniqueKeyTableMeta.java
 * @Description   : qiudequan (复合主键)表元数据
 * @Author        : qiudequan
 * @Creation Date : 2016年10月29日 下午11:02:42 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * qiudequan     2016年10月29日        create
 */
public class UnionPrimaryKeyTableMeta extends TableMeta {
  /** 复合主键集合 */
  private List<String> primaryKeys;

  public List<String> getPrimaryKeys() {
    return primaryKeys;
  }

  public void setPrimaryKeys(List<String> primaryKeys) {
    this.primaryKeys = primaryKeys;
  }
  
}
