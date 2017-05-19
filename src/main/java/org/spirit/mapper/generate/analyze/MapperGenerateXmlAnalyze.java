package org.spirit.mapper.generate.analyze;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spirit.mapper.generate.autoconfiguration.MapperGenerateProperties;
import org.spirit.mapper.generate.enums.ModuleElementEnum;
import org.spirit.mapper.generate.exception.MapperGenerateException;
import org.spirit.mapper.generate.meta.GenerateMeta;
import org.spirit.mapper.generate.meta.JDBCMeta;
import org.spirit.mapper.generate.meta.ModuleMeta;
import org.spirit.mapper.generate.meta.db.FieldMeta;
import org.spirit.mapper.generate.meta.db.TableMeta;
import org.spirit.mapper.generate.utils.BeanFactoryUtils;
import org.spirit.mapper.generate.utils.JDBCUtils;
import org.spirit.mapper.generate.utils.StringUtils;
import org.springframework.util.CollectionUtils;

@SuppressWarnings(value = {"unused", "rawtypes"})
public class MapperGenerateXmlAnalyze {
  private static final Logger logger = LoggerFactory.getLogger(MapperGenerateXmlAnalyze.class);

  public static GenerateMeta analyze(String xmlPath) {
    if(StringUtils.isEmpty(xmlPath)){
      MapperGenerateProperties mapperGenerateProperties = BeanFactoryUtils.getBean(MapperGenerateProperties.class);
      xmlPath = mapperGenerateProperties.getXmlPath();
    }
    File file = new File(xmlPath);
    if(!file.isAbsolute()){
      String path = Thread.currentThread().getContextClassLoader().getResource(xmlPath).getPath();      
      file = new File(path);
    }
    try {
      SAXReader reader = new SAXReader();
      Document doc = reader.read(file);
      Element root = doc.getRootElement();
      Element ele;
      List elements = root.elements();
      GenerateMeta generateMeta = new GenerateMeta();
      Element dbElement = root.element("database");
      JDBCMeta jdbcMeta = getJDBCMeta(dbElement);            
      generateMeta.setJdbcMeta(jdbcMeta);
      Map<String, String> typeMap = null;
      for (int i = 0; i < elements.size(); i++) {
        Element element = (Element)elements.get(i);
        if(element != null){
          switch (element.getName()) {
            case "modules":
              handlerModuleMeta(element, generateMeta);
              break;
            case "tables":
              handlerTableMeta(element, generateMeta);
              break;
            case "output":
              generateMeta.setOutputPath(getElementValue(element));
              break;
            case "typeMapping":
              typeMap = handlerTypeMapping(element, generateMeta);
              break;
            default:
              break;
          }
        }
      }
      List<TableMeta> tables = generateMeta.getTables();
      if(typeMap != null && ( tables != null && !tables.isEmpty())){
        for (TableMeta tableMeta : tables) {
          tableMeta.setTypeMap(typeMap);
          tableMeta.setTypeMapSize(typeMap.size());
        }
      }
      generateMeta.validate();
      return generateMeta;
    } catch (DocumentException e) {
      logger.error("An error occurred while reading the XML file.[XML File:{}]", file.getAbsolutePath(), e);
    } catch (MapperGenerateException e) {
      logger.error("An error occurred at mapper generate", e);
    } catch (Exception e) {
      logger.error("An error occurred at mapper generate", e);
    }
    return null;
  }

  /**
   *  @Description	: qiudequan 处理sql-java类型映射
   *  @param          : @param element
   *  @param          : @param generateMeta
   *  @return 		: void
   *  @Creation Date  : 2016年11月11日 下午2:48:49 
   *  @Author         : qiudequan
   */
  private static Map<String, String> handlerTypeMapping(Element element, GenerateMeta generateMeta) {
    if(element == null) {
      return null;
    } 
    List elements = element.elements("mapping");
    if(elements != null && !elements.isEmpty()) {
      Map<String, String> typeMap = new HashMap<>();
      for (Object object : elements) {
        Element ele = (Element) object;
        if(ele == null) {
          continue;
        }
        String sqlType = ele.attributeValue("sqlType");
        String javaType = ele.attributeValue("javaType");
        typeMap.put(sqlType.toUpperCase(), javaType);
      }
      return typeMap;
    }
    return null;
  }

  /**
   *  @Description	: qiudequan 处理模块生成
   *  @return         : void
   *  @Creation Date  : 2016年11月1日 下午11:15:27 
   *  @Author         : qiudequan
   */
  private static void handlerModuleMeta(Element element, GenerateMeta generateMeta){
    List<ModuleMeta> list = new ArrayList<>();
    Map<String, ModuleMeta> moduleMetaMap = new HashMap<>();
    List elements = element.elements();
    if(elements != null && elements.size() > 0){
      for(int i = 0,size = elements.size(); i < size; i++){
        Element ele = (Element) elements.get(i);
        if(ele != null){
          String eleName = ele.getName();
          String targetPackage = ele.attributeValue("targetPackage");
          String extend = ele.attributeValue("extend");
          String objectNameSuffix = ele.attributeValue("objectNameSuffix");
          ModuleMeta moduleMeta = new ModuleMeta();
          String targetPath = StringUtils.replaceAll(targetPackage, "\\.", "/");
          moduleMeta.setTargetPackage(targetPackage);
          moduleMeta.setTargetPath(targetPath);
          moduleMeta.setExtend(extend);
          if(StringUtils.isEmpty(objectNameSuffix)){
            moduleMeta.setObjectNameSuffix(ModuleElementEnum.getNameSuffix(eleName));
          } else {
            moduleMeta.setObjectNameSuffix(objectNameSuffix);
          }
          if("model".equals(eleName) || "vo".equals(eleName)){
            String serializable = ele.attributeValue("serializable");
            String rejectAttributes = ele.attributeValue("rejectAttributes");
            if(StringUtils.isNotEmpty(serializable)){
              moduleMeta.setSerializable(serializable);
            }
            if(StringUtils.isNotEmpty(rejectAttributes)){
              moduleMeta.setRejectAttributes(rejectAttributes);
            }
          }
          if("controller".equals(eleName)){
            String controllerAnnotation = ele.attributeValue("controllerAnnotation");
            if(StringUtils.isNotEmpty(controllerAnnotation)){
              moduleMeta.setControllerAnnotation(controllerAnnotation);
            }
          }
          if("service".equals(eleName)){
            String hasOverride = ele.attributeValue("hasOverride");
            if(StringUtils.isNotEmpty(hasOverride)){
              moduleMeta.setHasOverride(hasOverride);
            }
          }

          list.add(moduleMeta);
          moduleMetaMap.put(eleName, moduleMeta);
        }
      }
      generateMeta.setModules(list);
      generateMeta.setModulesMap(moduleMetaMap);
    }

  }


  /**
   *  @Description	: qiudequan 处理数据库表
   *  @return         : void
   *  @Creation Date  : 2016年11月1日 下午11:14:37 
   *  @Author         : qiudequan
   */
  private static void handlerTableMeta(Element element, GenerateMeta generateMeta){
    Map<String, String> tableKeys = new HashMap<>();
    Iterator elementIterator = element.elementIterator();
    // 解析xml节点数据
    while(elementIterator.hasNext()){
      Element ele = (Element) elementIterator.next();
      if(ele != null){
        String tableName = ele.attributeValue("tableName");
        String primaryKeys = ele.attributeValue("primaryKey");
        if(StringUtils.isNotEmpty(tableName)){
          tableKeys.put(tableName, primaryKeys);
        }
      }
    }
    // 解析数据表
    List<TableMeta> analyzeTables = analyzeTables(tableKeys, generateMeta.getJdbcMeta());
    generateMeta.setTables(analyzeTables);
  }

  /**
   *  @Description	: qiudequan 获取数据库连接信息
   *  @param          : @param element
   *  @param          : @return
   *  @return 		: JDBCMeta
   *  @Creation Date  : 2016年11月4日 下午2:21:21 
   *  @Author         : qiudequan
   */
  public static JDBCMeta getJDBCMeta(Element element){
    if(element == null){
      return null;
    }
    Element driverElement = element.element("driver");
    Element urlElement = element.element("url");
    Element userElement = element.element("user");
    Element passwordElement = element.element("password");
    String driver = getElementValue(driverElement);
    String url = getElementValue(urlElement);
    String user = getElementValue(userElement);
    String password = getElementValue(passwordElement);
    JDBCMeta jdbcMeta = new JDBCMeta();
    jdbcMeta.setDriver(driver);
    jdbcMeta.setUrl(url);
    jdbcMeta.setUser(user);
    jdbcMeta.setPassword(password);
    jdbcMeta.validate();
    return jdbcMeta;
  }

  private static String getAttributeValue(Attribute attribute){
    if(attribute == null){
      return "";
    }
    return attribute.getStringValue();
  }

  /**
   *  @Description	: qiudequan 获取元素的值
   *  @return         : String
   *  @Creation Date  : 2016年11月1日 下午10:59:33 
   *  @Author         : qiudequan
   */
  private static String getElementValue(Element element){
    if(element == null){
      return null;
    }
    return element.getStringValue();
  }

  /**
   *  @Description	: qiudequan 获取表名集合
   *  @param          : @param tables
   *  @param          : @return
   *  @return 		: List<String>
   *  @Creation Date  : 2016年11月4日 下午1:02:31 
   *  @Author         : qiudequan
   */
  public static List<String> getTableNames(List<TableMeta> tables){
    if(tables == null || tables.size() == 0){
      return null;
    }
    List<String> tableNames = new ArrayList<>();
    for (TableMeta tableMeta : tables) {
      tableNames.add(tableMeta.getName());
    }
    return tableNames;
  }

  /**
   *  @Description  : qiudequan 解析表和字段
   *  @param          : tableNames
   *  @return       : List<TableMeta>
   *  @Creation Date  : 2016年11月3日 下午1:35:25 
   *  @Author         : qiudequan
   */
  public static List<TableMeta> analyzeTables(Map<String, String> tableKeys, JDBCMeta jdbcMeta){
    JDBCUtils jdbcUtils = new JDBCUtils(jdbcMeta);
    jdbcUtils.connect();
    Connection connection = jdbcUtils.getConnection();
    DatabaseMetaData metaData = null;
    ResultSet tableSet = null;
    ResultSet columnSet = null;
    ResultSet executeQuery = null;
    ResultSet pkResultSet = null;
    try {
      metaData = connection.getMetaData();
      tableSet = metaData.getTables(null, "%", "%", new String[]{"TABLE"});
      List<TableMeta> tableMetas = new ArrayList<>();
      while(tableSet.next()){
        String tableName = tableSet.getString("TABLE_NAME");
        String tableComment = tableSet.getString("REMARKS");
        TableMeta tableMeta = null;
        Set<String> keys = tableKeys.keySet();
        if(tableKeys.containsKey(tableName)){
          String primaryKey = tableKeys.get(tableName);
          tableMeta = new TableMeta();
          if(StringUtils.isNotEmpty(primaryKey)){
            if(primaryKey.contains(",")){
              String[] pks = primaryKey.split(",");
              for (int i = 0, size = pks.length; i < size; i++) {
                pks[i] = pks[i].toLowerCase();
              }
              tableMeta.setPrimaryKey(primaryKey.split(","));
            } else {
              tableMeta.setPrimaryKey(new String[]{primaryKey.toLowerCase()});
            }
          } else {
            pkResultSet = metaData.getPrimaryKeys(connection.getCatalog().toUpperCase(), 
                metaData.getUserName().toUpperCase(), tableName.toUpperCase());
            List<String> pkList = new ArrayList<>();
            while(pkResultSet.next()) {
              String pk = pkResultSet.getString("COLUMN_NAME");
              pkList.add(pk.toLowerCase());
            }
            if(!CollectionUtils.isEmpty(pkList)) {
              tableMeta.setPrimaryKey(pkList.toArray(new String[]{}));
            }
          }
          tableMeta.setName(tableName);
          tableMeta.setTableComment(tableComment);
          tableMeta.setCamelName(StringUtils.camel(tableName.toLowerCase()));
          columnSet = metaData.getColumns(null, "%", tableName, "%");
          List<FieldMeta> fields = new ArrayList<>();
          FieldMeta fieldMeta;
          for (int i = 1; columnSet.next(); i++) {
            String sql = "select * from " + tableName + " where 1 = 2";
            jdbcUtils.setSql(sql);
            executeQuery = jdbcUtils.executeQuery();
            ResultSetMetaData columnMd = executeQuery.getMetaData();
            fieldMeta = new FieldMeta();
            String fieldName = columnSet.getString("COLUMN_NAME");
            String fieldComment = columnSet.getString("REMARKS");
            String dataType = columnSet.getString("TYPE_NAME");
            String length = String.valueOf(columnMd.getColumnDisplaySize(i));
            String digits = columnSet.getString("DECIMAL_DIGITS");
            String nullable = columnSet.getString("NULLABLE");
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
      logger.error("An exception appears in the analysis tables.", e);
      throw new MapperGenerateException("解析表时发生异常", e);
    } finally {
      jdbcUtils.closeResultSet(executeQuery);
      jdbcUtils.closeResultSet(columnSet);
      jdbcUtils.closeResultSet(tableSet);
      jdbcUtils.closeResultSet(pkResultSet);
      jdbcUtils.closeConnection();
      jdbcUtils.clear();
      jdbcUtils = null;
    }
  }
}