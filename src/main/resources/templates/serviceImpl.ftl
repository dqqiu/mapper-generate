package ${modules['service'].targetPackage}.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${modules['model'].targetPackage}.${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix};
import ${modules['vo'].targetPackage}.${table.getFirstLetterUpperName()}${modules['vo'].objectNameSuffix};
import ${modules['mapper'].targetPackage}.${table.getFirstLetterUpperName()}${modules['mapper'].objectNameSuffix};
import ${modules['service'].targetPackage}.${table.getFirstLetterUpperName()}${modules['service'].objectNameSuffix};

/**
 * 
 * @created by mapper-generate-1.0
 * @opensource https://www.github.com/dqqiu/mapper-generate
 */
@Service(value = "${table.camelName}${modules['service'].objectNameSuffix}Impl")
public class ${table.getFirstLetterUpperName()}${modules['service'].objectNameSuffix}Impl<#if (modules['service'].extend)??> extends ${(modules['service'].extend)?substring((modules['service'].extend?last_index_of('.')) + 1)}</#if> implements ${table.getFirstLetterUpperName()}${modules['service'].objectNameSuffix} {
  
  @Autowired
  private ${table.getFirstLetterUpperName()}${modules['mapper'].objectNameSuffix} ${table.camelName}${modules['mapper'].objectNameSuffix};
  
  <#if modules['service'].hasOverride == 'true'>@Override</#if>
  public int insert(${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix} ${table.camelName}${modules['model'].objectNameSuffix}) {
    return ${table.camelName}${modules['mapper'].objectNameSuffix}.insert(${table.camelName}${modules['model'].objectNameSuffix});
  }
  
  <#if modules['service'].hasOverride == 'true'>@Override</#if>
  public int insertBySelective(${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix} ${table.camelName}${modules['model'].objectNameSuffix}) {
    return ${table.camelName}${modules['mapper'].objectNameSuffix}.insertBySelective(${table.camelName}${modules['model'].objectNameSuffix});
  }
  
  <#if modules['service'].hasOverride == 'true'>@Override</#if>
  public ${table.getFirstLetterUpperName()} getByPrimaryKey(<#list table.primaryKey as key><#list table.getFieldJavaTypeMap()?keys as map><#if key == map>${table.getFieldJavaTypeMap()[key]} ${StringUtils.camel(key)}<#if key_index != (table.primaryKey?size - 1)>,</#if></#if></#list></#list>) {
    return ${table.camelName}${modules['mapper'].objectNameSuffix}.getByPrimaryKey(<#list table.primaryKey as key>${key}<#if key_index != (table.primaryKey?size - 1)>, </#if></#list>);
  }
  
  <#if modules['service'].hasOverride == 'true'>@Override</#if>
  public List<${table.getFirstLetterUpperName()}> getByCondition(${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix} ${table.camelName}${modules['model'].objectNameSuffix}) {
    return ${table.camelName}${modules['mapper'].objectNameSuffix}.getByCondition(${table.camelName}${modules['model'].objectNameSuffix});
  }
  
  <#if modules['service'].hasOverride == 'true'>@Override</#if>
  public List<${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix}> getByCondition(${table.getFirstLetterUpperName()}${modules['vo'].objectNameSuffix} ${table.camelName}${modules['vo'].objectNameSuffix}) {
    return ${table.camelName}${modules['mapper'].objectNameSuffix}.getByCondition(${table.camelName}${modules['vo'].objectNameSuffix});
  }
  
  <#if modules['service'].hasOverride == 'true'>@Override</#if>
  public int getCountByCondition(${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix} ${table.camelName}${modules['model'].objectNameSuffix}) {
    return ${table.camelName}${modules['mapper'].objectNameSuffix}.getCountByCondition(${table.camelName}${modules['model'].objectNameSuffix});
  }
  
  <#if modules['service'].hasOverride == 'true'>@Override</#if>
  public int getCountByCondition(${table.getFirstLetterUpperName()}${modules['vo'].objectNameSuffix} ${table.camelName}${modules['vo'].objectNameSuffix}) {
    return ${table.camelName}${modules['mapper'].objectNameSuffix}.getCountByCondition(${table.camelName}${modules['vo'].objectNameSuffix});
  }
  
  <#if modules['service'].hasOverride == 'true'>@Override</#if>
  public int updateByPrimaryKey(${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix} ${table.camelName}${modules['model'].objectNameSuffix}) {
    return ${table.camelName}${modules['mapper'].objectNameSuffix}.updateByPrimaryKey(${table.camelName}${modules['model'].objectNameSuffix});
  }
  
  <#if modules['service'].hasOverride == 'true'>@Override</#if>
  public int deleteByPrimaryKey(<#list table.primaryKey as key><#list table.getFieldJavaTypeMap()?keys as map><#if key == map>${table.getFieldJavaTypeMap()[key]} ${StringUtils.camel(key)}<#if key_index != (table.primaryKey?size - 1)>,</#if></#if></#list></#list>) {
    return ${table.camelName}${modules['mapper'].objectNameSuffix}.deleteByPrimaryKey(<#list table.primaryKey as key>${key}<#if key_index != (table.primaryKey?size - 1)>, </#if></#list>);
  }

  <#if modules['service'].hasOverride == 'true'>@Override</#if>
  public int deleteByCondition(${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix} ${table.camelName}${modules['model'].objectNameSuffix}) {
    return ${table.camelName}${modules['mapper'].objectNameSuffix}.deleteByCondition(${table.camelName}${modules['model'].objectNameSuffix});
  }

  <#if modules['service'].hasOverride == 'true'>@Override</#if>
  public int deleteByCondition(${table.getFirstLetterUpperName()}${modules['vo'].objectNameSuffix} ${table.camelName}${modules['vo'].objectNameSuffix}) {
    return ${table.camelName}${modules['mapper'].objectNameSuffix}.deleteByCondition(${table.camelName}${modules['vo'].objectNameSuffix});
  }
}
