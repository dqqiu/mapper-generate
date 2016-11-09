package ${modules['controller'].targetPackage};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import ${modules['controller'].controllerAnnotation};

import ${modules['service'].targetPackage}.${table.getFirstLetterUpperName()}${modules['service'].objectNameSuffix};
<#if (modules['controller'].extend)??>
import ${modules['controller'].extend}
</#if>

/**
 * @created by mapper-generate-1.0
 * @opensource https://www.github.com/dqqiu/mapper-generate
 */
@${(modules['controller'].controllerAnnotation)?substring(modules['controller'].controllerAnnotation?last_index_of('.') + 1)}
@RequestMapping(value = "/${table.name}")
public class ${table.getFirstLetterUpperName()}${modules['controller'].objectNameSuffix}<#if (modules['controller'].extend)??> extends ${(modules['controller'].extend)?substring(modules['controller'].extend?last_index_of('.') + 1)}</#if> {
  @Autowired
  private ${table.getFirstLetterUpperName()}${modules['service'].objectNameSuffix} ${table.camelName}${modules['service'].objectNameSuffix};

}