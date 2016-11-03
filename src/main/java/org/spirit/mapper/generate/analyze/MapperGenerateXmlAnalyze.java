package org.spirit.mapper.generate.analyze;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spirit.mapper.generate.autoconfiguration.MapperGenerateProperties;
import org.spirit.mapper.generate.exception.MapperGenerateException;
import org.spirit.mapper.generate.meta.GenerateMeta;
import org.spirit.mapper.generate.meta.JDBCMeta;
import org.spirit.mapper.generate.meta.ModuleMeta;
import org.spirit.mapper.generate.meta.db.TableMeta;
import org.spirit.mapper.generate.utils.BeanFactoryUtils;
import org.spirit.mapper.generate.utils.StringUtils;

@SuppressWarnings(value = {"unused", "rawtypes"})
public class MapperGenerateXmlAnalyze {
  private static final Logger logger = LoggerFactory.getLogger(MapperGenerateXmlAnalyze.class);
  public static GenerateMeta analyze() {
    MapperGenerateProperties mapperGenerateProperties = BeanFactoryUtils.getBean(MapperGenerateProperties.class);
    String path = Thread.currentThread().getContextClassLoader().getResource(mapperGenerateProperties.getXmlPath()).getPath();
    File file = new File(path);
    try {
      SAXReader reader = new SAXReader();
      Document doc = reader.read(file);
      Element root = doc.getRootElement();
      Element ele;
      List elements = root.elements();
      GenerateMeta generateMeta = new GenerateMeta();
      for (int i = 0; i < elements.size(); i++) {
        Element element = (Element)elements.get(i);
        if(element != null){
          switch (element.getName()) {
            case "database":
              handlerJDBCMeta(element, generateMeta);              
              break;
            case "modules":
              handlerModuleMeta(element, generateMeta);
              break;
            case "tables":
              handlerTableMeta(element, generateMeta);
              break;
            case "output":
              generateMeta.setOutputPath(getElementValue(element));
              break;
            default:
              break;
          }
        }
      }
      generateMeta.validate();
      return generateMeta;
    } catch (DocumentException e) {
      logger.error("An error occurred while reading the XML file.[XML File:{}]", file.getAbsolutePath());
      e.printStackTrace();
    } catch (MapperGenerateException e) {
      logger.error("An error occurred at mapper generate");
      e.printStackTrace();
    } catch (Exception e) {
      logger.error("An error occurred at mapper generate");
      e.printStackTrace();
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
          Attribute attribute = ele.attribute("targetPackage");
          String targetPackage = attribute.getStringValue();
          ModuleMeta moduleMeta = new ModuleMeta();
          moduleMeta.setTargetPackage(targetPackage);
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
    List<TableMeta> list = new ArrayList<>();
    Iterator elementIterator = element.elementIterator();
    TableMeta tableMeta = null;
    while(elementIterator.hasNext()){
      Element ele = (Element) elementIterator.next();
      if(ele != null){
        tableMeta = new TableMeta();
        String tableName = getAttributeValue(ele.attribute("tableName"));
        if(StringUtils.isNotEmpty(tableName)){
          tableMeta.setName(tableName);
        }
        list.add(tableMeta);
      }
    }
    generateMeta.setTables(list);
  }
  /**
   *  @Description	: qiudequan 处理JDBC连接信息
   *  @return         : void
   *  @Creation Date  : 2016年11月1日 下午11:04:15 
   *  @Author         : qiudequan
   */
  private static void handlerJDBCMeta(Element element, GenerateMeta generateMeta){
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
    generateMeta.setJdbcMeta(jdbcMeta);
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
}