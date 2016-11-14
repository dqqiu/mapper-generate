package ${modules['model'].targetPackage};

<#if table.typeMap?exists>
<#list table.typeMap?keys as key>
import ${table.typeMap[key]};
</#list>
</#if>
<#if (modules['model'].extend)??>
import ${modules['model'].extend};
</#if>
<#if modules['model'].serializable?? && modules['model'].serializable == 'true'> 
import java.io.Serializable;
</#if>

/**
 * ${table.tableComment}
 * @created by mapper-generate-1.0
 * @opensource https://www.github.com/dqqiu/mapper-generate
 */
public class ${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix}<#if (modules['model'].extend)??> extends ${(modules['model'].extend)?substring((modules['model'].extend?last_index_of('.')) + 1)}</#if><#if modules['model'].serializable?? && modules['model'].serializable == 'true'> implements Serializable</#if> {
  <#list table.fields as field>
  <#assign key="${field.type}"> 
  /**
   * 字段：${field.name}. 类型：${field.getColumnType()}. 备注：${field.comment}.
   */
  private ${StringUtils.getStrAfterLastestPoint(table.typeMap[key]!)} ${field.camelName};

  </#list>

  <#list table.fields as field>
  <#assign key="${field.type}">
  public void set${field.firstLetterUpper}(${StringUtils.getStrAfterLastestPoint(table.typeMap[key]!)} ${field.camelName}) {
    this.${field.camelName} = ${field.camelName};
  }

  public ${StringUtils.getStrAfterLastestPoint(table.typeMap[key]!)} get${field.firstLetterUpper}() {
    return this.${field.camelName};
  }

  </#list>
}