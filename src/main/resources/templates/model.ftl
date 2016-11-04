package ${targetPackage};

<#if table.getFieldTypeMap()?exists>
<#list table.getFieldTypeMap()?keys as key>
import ${table.getFieldTypeMap()[key]};
</#list>
</#if>

/**
 *	${table.tableComment}
 * @created by mapper-generate
 */
public class ${table.getFirstLetterUpperName()}${nameSuffix} {
  <#list table.fields as field>
  /**
   * 字段：${field.name}. 类型：${field.getColumnType()}. 备注：${field.comment}.
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