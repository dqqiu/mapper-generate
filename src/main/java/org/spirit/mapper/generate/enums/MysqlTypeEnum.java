package org.spirit.mapper.generate.enums;

/**
 * @Project       : mapper-generate
 * @Program Name  : org.spirit.mapper.generate.enums.MysqlTypeEnum.java
 * @Description   : qiudequan Mysql数据类型枚举，设立与java类型对应关系
 * @Author        : qiudequan
 * @Creation Date : 2016年10月29日 下午10:55:02 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * qiudequan     2016年10月29日        create
 */
public enum MysqlTypeEnum {
  VARCHAR("VARCHAR", "String", "java.lang.String", "可变字符类型"),
  CHAR("CHAR", "String", "java.lang.String", "字符类型"),
  TEXT("TEXT", "String", "java.lang.String", "长文本类型"),
  BLOB("BLOB", "byte[]", "java.lang.byte", "长文本类型"),
  INT("INT", "Integer", "java.lang.Integer", "整型"), 
  INTEGER("INTEGER", "Long", "java.lang.Long", "整型"), 
  TINYINT("TINYINT", "Byte", "java.lang.Byte", "整型"), 
  SMALLINT("SMALLINT", "Integer", "java.lang.Integer", "整型"),
  MEDIUMINT("MEDIUMINT", "Integer", "java.lang.Integer", "整型"),
  BIGINT("BIGINT", "Long", "java.lang.Long", "整型"),
  BIT("BIT", "Boolean", "java.lang.Boolean", "BIT类型"),
  FLOAT("FLOAT", "Float", "java.lang.Float", "浮点型"),
  DOUBLE("DOUBLE", "Double", "java.lang.Double", "双精度浮点型"),
  DECIMAL("DECIMAL", "BigDecimal", "java.math.BigDecimal", "数字型"),
  DATE("DATE", "Date", "java.util.Date", "日期类型"),
  TIME("TIME", "Time", "java.sql.Time", "时间类型"),
  DATETIME("DATETIME", "Timestamp", "java.sql.Timestamp", "时间类型"),
  TIMESTAMP("TIMESTAMP", "Timestamp", "java.sql.Timestamp", "时间戳"),
  YEAR("YEAR", "Date", "java.sql.Date", "年");
  
  private String dbType;
  private String javaType;
  private String javaPackage;
  private String comment;
  
  private MysqlTypeEnum(String dbType, String javaType, String javaPackage, String comment){
    this.dbType = dbType;
    this.javaType = javaType;
    this.javaPackage = javaPackage;
    this.comment = comment;
  }
  
  public String getJavaType(){
    return this.javaType;
  }
  
  public String getJavaPackage(){
    return this.javaPackage;
  }
  
  public String getDbType(){
    return this.dbType;
  }
  
  public String getComment(){
    return this.comment;
  }
  
  public void setDbType(String dbType){
    this.dbType = dbType;
  }
  
  public static MysqlTypeEnum get(String dbType){
    if(dbType == null || "".equals(dbType) || "null".equals(dbType)){
      return null;
    }
    for (MysqlTypeEnum mysqlTypeEnum : MysqlTypeEnum.values()) {
      if(mysqlTypeEnum.getDbType().equals(dbType)){
        return mysqlTypeEnum;
      }
    }
    return null;
  } 
}
