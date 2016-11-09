package org.spirit.mapper.generate.meta;

/**
 * @Project       : mapper-generate
 * @Program Name  : org.spirit.mapper.generate.meta.ModuleMeta.java
 * @Description   : qiudequan 模块元数据
 * @Author        : qiudequan
 * @Creation Date : 2016年11月1日 下午10:17:51 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * qiudequan     2016年11月1日        create
 */
public class ModuleMeta {
  /** 代码生成所在包 */
  private String targetPackage;
  /** 代码生成所在路径 */
  private String targetPath;
  /** 生成对象的名称后缀 */
  private String objectNameSuffix;
  private String serializable;
  private String extend;
  private String rejectAttributes;
  /** Controller模块特有属性 */
  private String controllerAnnotation;

  public String getTargetPackage() {
    return targetPackage;
  }

  public void setTargetPackage(String targetPackage) {
    this.targetPackage = targetPackage;
  }

  public String getTargetPath() {
    return targetPath;
  }

  public void setTargetPath(String targetPath) {
    this.targetPath = targetPath;
  }

  public String getObjectNameSuffix() {
    return objectNameSuffix;
  }

  public void setObjectNameSuffix(String objectNameSuffix) {
    this.objectNameSuffix = objectNameSuffix;
  }

  public String getSerializable() {
    return serializable;
  }

  public void setSerializable(String serializable) {
    this.serializable = serializable;
  }

  public String getExtend() {
    return extend;
  }

  public void setExtend(String extend) {
    this.extend = extend;
  }

  public String getRejectAttributes() {
    return rejectAttributes;
  }

  public void setRejectAttributes(String rejectAttributes) {
    this.rejectAttributes = rejectAttributes;
  }

  public String getControllerAnnotation() {
    return controllerAnnotation;
  }

  public void setControllerAnnotation(String controllerAnnotation) {
    this.controllerAnnotation = controllerAnnotation;
  }
  
}
