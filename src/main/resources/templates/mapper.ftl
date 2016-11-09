package ${modules['mapper'].targetPackage};

import java.util.List;
import ${modules['model'].targetPackage}.${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix};
import ${modules['vo'].targetPackage}.${table.getFirstLetterUpperName()}${modules['vo'].objectNameSuffix};
<#if (modules['mapper'].extend)??>
import ${modules['mapper'].extend};
</#if>

/**
 * 
 * @created by mapper-generate-1.0
 * @opensource https://www.github.com/dqqiu/mapper-generate
 */
public interface ${table.getFirstLetterUpperName()}${modules['mapper'].objectNameSuffix}<#if (modules['mapper'].extend)??> extends ${(modules['mapper'].extend)?substring((modules['mapper'].extend?last_index_of('.')) + 1)}</#if> {
  int insert(${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix} ${table.camelName}${modules['model'].objectNameSuffix});
  
  int insertBySelective(${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix} ${table.camelName}${modules['model'].objectNameSuffix});
  
  ${table.getFirstLetterUpperName()} getByPrimaryKey(<#list table.primaryKey as key><#list table.getFieldJavaTypeMap()?keys as map><#if key == map>${table.getFieldJavaTypeMap()[key]} ${StringUtils.camel(key)}<#if key_index != (table.primaryKey?size - 1)>,</#if></#if></#list></#list>);
  
  List<${table.getFirstLetterUpperName()}> getByCondition(${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix} ${table.camelName}${modules['model'].objectNameSuffix});
  
  List<${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix}> getByCondition(${table.getFirstLetterUpperName()}${modules['vo'].objectNameSuffix} ${table.camelName}${modules['vo'].objectNameSuffix});
  
  int getCountByCondition(${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix} ${table.camelName}${modules['model'].objectNameSuffix});
  
  int getCountByCondition(${table.getFirstLetterUpperName()}${modules['vo'].objectNameSuffix} ${table.camelName}${modules['vo'].objectNameSuffix});
  
  int updateByPrimaryKey(${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix} ${table.camelName}${modules['model'].objectNameSuffix});
  
  int deleteByPrimaryKey(<#list table.primaryKey as key><#list table.getFieldJavaTypeMap()?keys as map><#if key == map>${table.getFieldJavaTypeMap()[key]} ${StringUtils.camel(key)}<#if key_index != (table.primaryKey?size - 1)>,</#if></#if></#list></#list>);

  int deleteByCondition(${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix} ${table.camelName}${modules['model'].objectNameSuffix});

  int deleteByCondition(${table.getFirstLetterUpperName()}${modules['vo'].objectNameSuffix} ${table.camelName}${modules['vo'].objectNameSuffix});
}
