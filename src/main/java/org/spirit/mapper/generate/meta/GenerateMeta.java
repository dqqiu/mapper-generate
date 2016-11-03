package org.spirit.mapper.generate.meta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.spirit.mapper.generate.exception.MapperGenerateException;
import org.spirit.mapper.generate.meta.db.TableMeta;
import org.spirit.mapper.generate.utils.StringUtils;

/**
 * @Project       : mapper-generate
 * @Program Name  : org.spirit.mapper.generate.meta.GenerateMeta.java
 * @Description   : qiudequan 元数据
 * @Author        : qiudequan
 * @Creation Date : 2016年11月1日 下午10:15:41 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * qiudequan     2016年11月1日        create
 */
public class GenerateMeta {
  /** jdbc元数据 */
  private JDBCMeta jdbcMeta;
  /** 模块元数据 */
  private List<ModuleMeta> modules;
  private Map<String, ModuleMeta> modulesMap;
  /** 数据库表元数据 */
  private List<TableMeta> tables;
  /** 代码生成路径 */
  private String outputPath;
  public JDBCMeta getJdbcMeta() {
    return jdbcMeta;
  }
  public void setJdbcMeta(JDBCMeta jdbcMeta) {
    this.jdbcMeta = jdbcMeta;
  }
  public List<ModuleMeta> getModules() {
    return modules;
  }
  public void setModules(List<ModuleMeta> modules) {
    this.modules = modules;
  }
  public List<TableMeta> getTables() {
    return tables;
  }
  public void setTables(List<TableMeta> tables) {
    this.tables = tables;
  }
  
  public String getOutputPath() {
    return outputPath;
  }
  public void setOutputPath(String outputPath) {
    this.outputPath = outputPath;
  }
  
  public Map<String, ModuleMeta> getModulesMap() {
    return modulesMap;
  }
  public void setModulesMap(Map<String, ModuleMeta> modulesMap) {
    this.modulesMap = modulesMap;
  }
  public void validate() throws MapperGenerateException {
    jdbcMeta.validate();
    if(modules == null || modules.size() <= 0){
      throw new MapperGenerateException("请至少指定一个模块");
    }
    if(tables == null || tables.size() <= 0){
      throw new MapperGenerateException("请至少指定一张数据表");
    }
    if(StringUtils.isEmpty(outputPath)){
      throw new MapperGenerateException("请指定代码生成路径");
    }
  }
  
  public List<String> getTableNames(){
    if(tables == null || tables.size() == 0){
      return null;
    }
    List<String> tableNames = new ArrayList<>();
    for (TableMeta tableMeta : tables) {
      tableNames.add(tableMeta.getName());
    }
    return tableNames;
  }
}
