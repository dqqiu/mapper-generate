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
  <#if field.camelName?ends_with("Time")>
  /**
   * 备注：${field.comment}--开始时间,查询用
   */
  private ${field.javaType} ${field.camelName}Begin;
  /**
   * 备注：${field.comment}--结束时间,查询用
   */
  private ${field.javaType} ${field.camelName}End;
  </#if>

  </#list>

  <#list table.fields as field>

  public void set${field.firstLetterUpper}(${field.javaType} ${field.camelName}) {
    this.${field.camelName} = ${field.camelName};
  }

  public ${field.javaType} get${field.firstLetterUpper}() {
    return this.${field.camelName};
  }
  <#if field.camelName?ends_with("Time")>
  public void set${field.firstLetterUpper}Begin(${field.javaType} ${field.camelName}Begin) {
    this.${field.camelName}Begin = ${field.camelName}Begin;
  }

  public ${field.javaType} get${field.firstLetterUpper}Begin() {
    return this.${field.camelName}Begin;
  }

  public void set${field.firstLetterUpper}End(${field.javaType} ${field.camelName}End) {
    this.${field.camelName}End = ${field.camelName}End;
  }

  public ${field.javaType} get${field.firstLetterUpper}End() {
    return this.${field.camelName}End;
  }
  </#if>

  </#list>
}