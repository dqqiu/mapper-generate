package org.spirit.mapper.generate.exception;

public class MapperGenerateException extends RuntimeException {
  private static final long serialVersionUID = 2841169491457259420L;
  private static final String DEFAULT_MESSAGE = "数据库映射异常";
  
  public MapperGenerateException(){
    super();
  }
  
  public MapperGenerateException(String message){
    super(message);
  }
  
  public MapperGenerateException(Throwable cause){
    super(DEFAULT_MESSAGE, cause);
  }
  
  public MapperGenerateException(String message, Throwable cause){
    super(message, cause);
  }
}
