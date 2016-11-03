package org.spirit.mapper.generate.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spirit.mapper.generate.exception.MapperGenerateException;
import org.spirit.mapper.generate.meta.JDBCMeta;

/**
 * @Project       : mapper-generate
 * @Program Name  : org.spirit.mapper.generate.utils.JDBCUtils.java
 * @Description   : qiudequan JDBC工具类
 * @Author        : qiudequan
 * @Creation Date : 2016年10月29日 下午11:28:53 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * qiudequan     2016年10月29日        create
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public class JDBCUtils {
  private final JDBCMeta jdbcMeta;
  private Connection connection;
  private List sqlValues;
  private String sql;
  
  public Connection getConnection() {
    return connection;
  }

  public String getSql() {
    return sql;
  }

  public void setSql(String sql) {
    this.sql = sql;
  }

  public List getSqlValues() {
    return sqlValues;
  }

  public void setSqlValues(List sqlValues) {
    this.sqlValues = sqlValues;
  }

  private final Logger logger = LoggerFactory.getLogger(JDBCUtils.class);

  public JDBCUtils(JDBCMeta jdbcMeta){
    this.jdbcMeta = jdbcMeta;
    jdbcMeta.validate();
    connect();
  }

  /**
   *  @Description	: qiudequan 连接数据库
   *  @param          : 
   *  @return 		: void
   *  @Creation Date  : 2016年11月3日 上午10:07:31 
   *  @Author         : qiudequan
   */
  public void connect(){
    if(this.connection != null){
      return;
    }
    try {
      Class.forName(jdbcMeta.getDriver());
      connection = DriverManager.getConnection(jdbcMeta.getUrl(), jdbcMeta.getUser(), jdbcMeta.getPassword());
    } catch (ClassNotFoundException | SQLException e) {
      logger.error("connect databse error", e);
      throw new MapperGenerateException("连接数据库时发生异常", e);
    }
  }

  /**
   *  @Description	: qiudequan 执行查询
   *  @param          : @return
   *  @return 		: ResultSet
   *  @Creation Date  : 2016年11月3日 上午9:56:55 
   *  @Author         : qiudequan
   */
  public Result executeQuery(){
    Result result = null;
    ResultSet resultSet = null;  
    PreparedStatement pst = null;
    try {  
      pst = connection.prepareStatement(sql);  
      if(sqlValues != null && sqlValues.size() > 0){  //当sql语句中存在占位符时  
        setSqlValues(pst, sqlValues);  
      }  
      resultSet = pst.executeQuery();  
      result = ResultSupport.toResult(resultSet);
    } catch (SQLException e) {
      logger.error("execute query error.[sql:({}), sqlValues:({})]", sql, sqlValues != null ? Arrays.toString(sqlValues.toArray(new String[]{})) : "", e);
      e.printStackTrace();  
    } finally {
      closeStatement(pst);
      closeResultSet(resultSet);
      closeConnection();
      clear();
    }
    return result;  
  }

  /**
   *  @Description	: qiudequan 执行更新语句，包括增删改
   *  @param          : @return
   *  @param          : @throws SQLException
   *  @return 		: int
   *  @Creation Date  : 2016年11月3日 上午10:01:10 
   *  @Author         : qiudequan
   */
  public int executeUpdate() throws SQLException {
    int result = 0;
    PreparedStatement pstmt = null;
    Statement stmt = null;
    try{
      if(sqlValues != null && sqlValues.size() > 0){
        pstmt = connection.prepareStatement(sql);
        setSqlValues(pstmt, sqlValues);
        result = pstmt.executeUpdate();
      }else{
        stmt = connection.createStatement();
        result = stmt.executeUpdate(sql);
      }
    } finally {
      closeStatement(stmt);
      closeStatement(pstmt);
      closeConnection();
      clear();
    }
    return result;
  }

  /** 
   * 给sql语句中的占位符赋值 
   * @param pst 
   * @param sqlValues 
   */  
  private void setSqlValues(PreparedStatement pst,List sqlValues){  
    for(int i=0;i<sqlValues.size();i++){  
      try {  
        pst.setObject(i+1,sqlValues.get(i));  
      } catch (SQLException e) { 
        logger.error("setSqlValues error", e);
        e.printStackTrace();  
      }  
    }  
  }  

  public void clear(){
    this.sql = null;
    this.sqlValues = null;
  }

  /**
   *  @Description	: qiudequan 关闭数据库连接
   *  @param          : @param connection
   *  @return 		: void
   *  @Creation Date  : 2016年11月3日 上午10:10:19 
   *  @Author         : qiudequan
   */
  public void closeConnection(){
    if(this.connection != null){
      try {
        this.connection.close();
      } catch (SQLException e) {
        logger.error("close db connection error", e);
        e.printStackTrace();
      }
    }
  }

  /**
   *  @Description	: qiudequan 关闭sql声明
   *  @param          : @param statement
   *  @return 		: void
   *  @Creation Date  : 2016年11月3日 上午10:09:46 
   *  @Author         : qiudequan
   */
  public void closeStatement(Statement statement){
    if(statement != null){
      try {
        statement.close();
      } catch (SQLException e) {
        logger.error("close statement error", e);
        e.printStackTrace();
      }
    }
  }

  /**
   *  @Description	: qiudequan 关闭结果集
   *  @param          : @param resultSet
   *  @return 		: void
   *  @Creation Date  : 2016年11月3日 上午10:09:38 
   *  @Author         : qiudequan
   */
  public void closeResultSet(ResultSet resultSet){
    if(resultSet != null){
      try {
        resultSet.close();
      } catch (SQLException e) {
        logger.error("close resultSet error", e);
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    JDBCMeta jdbcMeta = new JDBCMeta();
    jdbcMeta.setDriver("com.mysql.jdbc.Driver");
    jdbcMeta.setUrl("jdbc:mysql://127.0.0.1:3306/dictionary");
    jdbcMeta.setUser("root");
    jdbcMeta.setPassword("root");

    String sql = "select * from dict_state where dscp like ?";
    List list = new ArrayList<>();
    list.add("%中%");

    JDBCUtils jdbcUtils = new JDBCUtils(jdbcMeta);
    jdbcUtils.connect();
    jdbcUtils.setSql(sql);
    jdbcUtils.setSqlValues(list);
    Result result = jdbcUtils.executeQuery();
    if(result != null){
      int rows = result.getRowCount();
      if(rows > 0){
        SortedMap[] maps = result.getRows();
        for (SortedMap sortedMap : maps) {
          String dscp = (String) sortedMap.get("dscp");
          System.out.println(dscp);
        }
      }
    }
  }
}
