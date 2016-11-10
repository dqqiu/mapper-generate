package org.spirit.mapper.generate.analyze;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.spirit.mapper.generate.enums.ModuleElementEnum;
import org.spirit.mapper.generate.exception.MapperGenerateException;
import org.spirit.mapper.generate.meta.GenerateMeta;
import org.spirit.mapper.generate.meta.ModuleMeta;
import org.spirit.mapper.generate.meta.db.TableMeta;
import org.spirit.mapper.generate.utils.FreemarkerUtils;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;

/**
 * @Project       : mapper-generate
 * @Program Name  : org.spirit.mapper.generate.analyze.MapperGenerate.java
 * @Description   : qiudequan 代码生成类
 * @Author        : qiudequan
 * @Creation Date : 2016年11月4日 下午1:46:44 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * qiudequan     2016年11月4日        create
 */
public class MapperGenerate {
  private String xmlPath;
  
  public String getXmlPath() {
    return xmlPath;
  }

  public void setXmlPath(String xmlPath) {
    this.xmlPath = xmlPath;
  }

  public void generate() {
    GenerateMeta generateMeta = MapperGenerateXmlAnalyze.analyze(getXmlPath());
    List<TableMeta> tables = generateMeta.getTables();
    try {
      Map<String, ModuleMeta> modulesMap = generateMeta.getModulesMap();
      for (TableMeta tableMeta : tables) {
        Set<String> keySet = modulesMap.keySet();
        for (String key : keySet) {
          Map<String, Object> root = new HashMap<>();
          BeansWrapper beansWrapper = new BeansWrapper();
          TemplateHashModel staticModels = beansWrapper.getStaticModels();
          TemplateHashModel templateModel = (TemplateHashModel) staticModels.get("org.spirit.mapper.generate.utils.StringUtils");
          ModuleMeta moduleMeta = modulesMap.get(key);
          root.put("table", tableMeta);
          root.put("modules", modulesMap);
          root.put("StringUtils", templateModel);
          
          String nameSuffix = ModuleElementEnum.getNameSuffix(key);
          String ftlName = key + ".ftl";
          String fileName = "/" + moduleMeta.getTargetPath() + "/" + tableMeta.getFirstLetterUpperName() + nameSuffix + ModuleElementEnum.getFileSuffix(key);
          FreemarkerUtils.printFile(ftlName, root, fileName, generateMeta.getOutputPath(), "");
          if("service".equals(key)){
            String implFileName = "/" + moduleMeta.getTargetPath() + "/impl/" + tableMeta.getFirstLetterUpperName() + nameSuffix + "Impl" + ModuleElementEnum.getFileSuffix(key);
            FreemarkerUtils.printFile(key + "Impl.ftl", root, implFileName, generateMeta.getOutputPath(), "");
          }
        }
      }
    } catch (Exception e) {
      throw new MapperGenerateException("mapper generate error.[MapperGenerate.generate--> xml : {" + getXmlPath() + "}]", e);
    }
  }
}
