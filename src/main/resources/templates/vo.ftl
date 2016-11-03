package ${targetPackage};

<#if typeMap?exists>
<#list typeMap?keys as key>
import ${typeMap[key]};
</#list>
</#if>

public class ${className}VO {
  <#list fields as field>
  /**
   * 备注：${field.comment}. 字段：${field.getColumnType()}.
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

  <#list fields as field>

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