package ${modules['mapper'].targetPackage};

import java.util.List;
import ${modules['model'].targetPackage}.${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix};
import ${modules['vo'].targetPackage}.${table.getFirstLetterUpperName()}${modules['vo'].objectNameSuffix};
<#if (modules['model'].extend)??>
import ${modules['model'].extend};
<#else>
import org.spirit.mapper.generate.meta.support.BaseMapper;
</#if>

/**
 * 
 * @created by mapper-generate-1.0
 * @opensource https://www.github.com/dqqiu/mapper-generate
 * @remark 不建议继承自定义类，若继承应继承自接口org.spirit.mapper.generate.meta.support.BaseMapper
 */
public interface DictStateMapper<#if (modules['model'].extend)??> extends ${(modules['model'].extend)?substring((modules['model'].extend?last_index_of('.')) + 1)}<#else> extends BaseMapper<${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix}></#if>{
  List<${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix}> getByCondition(${table.getFirstLetterUpperName()}${modules['vo'].objectNameSuffix} ${table.camelName}${modules['vo'].objectNameSuffix});
  int getCountCondition(${table.getFirstLetterUpperName()}${modules['vo'].objectNameSuffix} ${table.camelName}${modules['vo'].objectNameSuffix});
}
