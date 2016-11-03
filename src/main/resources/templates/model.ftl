package ${targetPackage};

<#if typeMap?exists>
<#list typeMap?keys as key>
import ${typeMap[key]};
</#list>
</#if>

public class ${className} {
  <#list fields as field>
  /**
   * 备注：${field.comment}. 字段：${field.getColumnType()}.
   */
  private ${field.javaType} ${field.camelName};

  </#list>

  <#list fields as field>

  public void set${field.firstLetterUpper}(${field.javaType} ${field.camelName}) {
    this.${field.camelName} = ${field.camelName};
  }

  public ${field.javaType} get${field.firstLetterUpper}() {
    return this.${field.camelName};
  }

  </#list>
}