package org.spirit.mapper.generate.analyze;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.spirit.mapper.generate.exception.MapperGenerateException;
import org.spirit.mapper.generate.meta.db.FieldMeta;
import org.spirit.mapper.generate.meta.db.TableMeta;
import org.spirit.mapper.generate.utils.JDBCUtils;
import org.spirit.mapper.generate.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Project       : mapper-generate
 * @Program Name  : org.spirit.mapper.generate.analyze.DBAnalyze.java
 * @Description   : qiudequan 数据库相关解析类
 * @Author        : qiudequan
 * @Creation Date : 2016年11月3日 下午12:56:50 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * qiudequan     2016年11月3日        create
 */

@Component
@SuppressWarnings("unused")
public class DBAnalyze {
  @Autowired
  private JDBCUtils jdbcUtils;
  
  /**
   *  @Description	: qiudequan 解析表和字段
   *  @param          : tableNames
   *  @return 		: List<TableMeta>
   *  @Creation Date  : 2016年11月3日 下午1:35:25 
   *  @Author         : qiudequan
   */
  public List<TableMeta> analyzeTables(List<String> tableNames){
    jdbcUtils.connect();
    Connection connection = jdbcUtils.getConnection();
    DatabaseMetaData metaData = null;
    ResultSet resultSet = null;
    try {
      metaData = connection.getMetaData();
      resultSet = metaData.getTables(null, "%", "%", new String[]{"TABLE"});
      List<TableMeta> tableMetas = new ArrayList<>();
      while(resultSet.next()){
        String tableName = resultSet.getString("TABLE_NAME");
        String tableComment = resultSet.getString("REMARKS");
        TableMeta tableMeta = null;
        if(tableNames.contains(tableName)){
          tableMeta = new TableMeta();
          tableMeta.setName(tableName);
          tableMeta.setTableComment(tableComment);
          ResultSet rst = metaData.getColumns(null, "%", tableName, "%");
          List<FieldMeta> fields = new ArrayList<>();
          FieldMeta fieldMeta;
          while (rst.next()) {
            fieldMeta = new FieldMeta();
            String fieldName = rst.getString("COLUMN_NAME");
            String fieldComment = rst.getString("REMARKS");
            String dataType = rst.getString("TYPE_NAME");
            String length = rst.getString("COLUMN_SIZE");
            String digits = rst.getString("DECIMAL_DIGITS");
            String nullable = rst.getString("NULLABLE");
            fieldMeta.setName(fieldName);
            fieldMeta.setComment(fieldComment);
            fieldMeta.setType(dataType);
            fieldMeta.setLength(length);
            fieldMeta.setDigits(digits);
            fieldMeta.setNullable(nullable);
            fields.add(fieldMeta);
          }
          tableMeta.setFields(fields);
          tableMetas.add(tableMeta);
        }
      }
      return tableMetas;
    } catch (Exception e) {
      throw new MapperGenerateException("解析表时发生异常", e);
    } finally {
      jdbcUtils.closeResultSet(resultSet);
      jdbcUtils.closeConnection();
      jdbcUtils.clear();
    }
  }
  
  /**
   *  @Description	: qiudequan 获取连接时的schema
   *  @param          : @param connection
   *  @param          : @return
   *  @param          : @throws SQLException
   *  @return 		: String
   *  @Creation Date  : 2016年11月3日 下午1:35:10 
   *  @Author         : qiudequan
   */
  private String getSchema(Connection connection) throws SQLException{
    String schema = connection.getMetaData().getUserName();
    if(StringUtils.isEmpty(schema)){
      throw new MapperGenerateException("schema为空");
    }
    return schema;
  }
}
