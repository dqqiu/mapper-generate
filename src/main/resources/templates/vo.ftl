package ${modules['vo'].targetPackage};

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
 *	${table.tableComment}
 * @created by mapper-generate
 * @opensource https://www.github.com/dqqiu/mapper-generate
 */
public class ${table.getFirstLetterUpperName()}${modules['vo'].objectNameSuffix}<#if (modules['model'].extend)??> extends ${(modules['model'].extend)?substring((modules['model'].extend?last_index_of('.')) + 1)}</#if><#if modules['model'].serializable?? && modules['model'].serializable == 'true'> implements Serializable</#if> {
  <#list table.fields as field>
  <#assign key="${field.type}">
  /**
   * 字段：${field.name}. 类型：${field.getColumnType()}. 备注：${field.comment}.
   */
  private ${StringUtils.getStrAfterLastestPoint(table.typeMap[key]!)} ${field.camelName};
  <#if field.camelName?ends_with("Time")>
  /**
   * 备注：${field.comment}--开始时间,查询用
   */
  private ${StringUtils.getStrAfterLastestPoint(table.typeMap[key]!)} ${field.camelName}Begin;
  /**
   * 备注：${field.comment}--结束时间,查询用
   */
  private ${StringUtils.getStrAfterLastestPoint(table.typeMap[key]!)} ${field.camelName}End;
  </#if>

  </#list>

  <#list table.fields as field>
  <#assign key="${field.type}">

  public void set${field.firstLetterUpper}(${StringUtils.getStrAfterLastestPoint(table.typeMap[key]!)} ${field.camelName}) {
    this.${field.camelName} = ${field.camelName};
  }

  public ${StringUtils.getStrAfterLastestPoint(table.typeMap[key]!)} get${field.firstLetterUpper}() {
    return this.${field.camelName};
  }
  <#if field.camelName?ends_with("Time")>
  public void set${field.firstLetterUpper}Begin(${StringUtils.getStrAfterLastestPoint(table.typeMap[key]!)} ${field.camelName}Begin) {
    this.${field.camelName}Begin = ${field.camelName}Begin;
  }

  public ${StringUtils.getStrAfterLastestPoint(table.typeMap[key]!)} get${field.firstLetterUpper}Begin() {
    return this.${field.camelName}Begin;
  }

  public void set${field.firstLetterUpper}End(${StringUtils.getStrAfterLastestPoint(table.typeMap[key]!)} ${field.camelName}End) {
    this.${field.camelName}End = ${field.camelName}End;
  }

  public ${StringUtils.getStrAfterLastestPoint(table.typeMap[key]!)} get${field.firstLetterUpper}End() {
    return this.${field.camelName}End;
  }
  </#if>

  </#list>
}