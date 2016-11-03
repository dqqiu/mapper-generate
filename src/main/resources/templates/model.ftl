package ${targetPackage};

<#if table.getFieldTypeMap()?exists>
<#list table.getFieldTypeMap()?keys as key>
import ${table.getFieldTypeMap()[key]};
</#list>
</#if>

/**
 *	${table.tableComment}
 */
public class ${table.getFirstLetterUpperName()} {
  <#list table.fields as field>
  /**
   * 备注：${field.comment}. 字段：${field.getColumnType()}.
   */
  private ${field.javaType} ${field.camelName};

  </#list>

  <#list table.fields as field>

  public void set${field.firstLetterUpper}(${field.javaType} ${field.camelName}) {
    this.${field.camelName} = ${field.camelName};
  }

  public ${field.javaType} get${field.firstLetterUpper}() {
    return this.${field.camelName};
  }

  </#list>
}