# mapper-generate
Mybatis代码生成,目前只支持mysql

## 实现流程图
![实现流程图](https://www.github.com/dqqiu/mapper-generate/raw/master/other/about/mapper-generate.png)

## mapper-generate.xml示例及说明
``` xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- mapper-generate-1.0,代码生成，暂时只实现mysql支持 -->
<generate xmlns="http://www.spirit.org/mapper-generate-1.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.spirit.org/mapper-generate-1.0
 https://raw.githubusercontent.com/dqqiu/mapper-generate/master/other/xsd/mapper-generate-1.0.xsd">
 	<!-- 数据库连接信息 -->
	<database>
		<driver>com.mysql.jdbc.Driver</driver>
		<url>jdbc:mysql://127.0.0.1:3306/dictionary</url>
		<user>root</user>
		<password>root</password>
	</database>
	<!-- 
	  模块信息配置，为顺序标签，需要按照如下顺序配置，否则将会报错
	    标签下公用属性：
	    targetPackage：生成的包路径
		objectNameSuffix：生成的对象名称后缀，如：数据表为dict_state,此属性配置为Model，则生成的对象名称为DictStateModel,其他同理
		    此属性各个模块的默认值===>>> model(""),vo("Vo"),mapper("Mapper"),
							  		  xml("Mapper")[指的是生成的mybatis映射xml文件的文件名后缀],
							  		  service("Service"),controller("Controller")
		extend：需要继承的类全名(含包名)
	 -->
	<modules>
		<!-- 
		  Model模块：生成实体类 
		      特殊属性：
		    rejectAttributes：生成代码时，忽略字段(暂未实现)
			serializable：是否需要实现序列化，true/false
		-->
		<model targetPackage="org.spirit.together.dictionary.api.model" />
		<!-- 
		  VO模块：生成视图实体类(暴露给视图层的对象，可用于远程传输[实现序列化])
		    特殊属性：同model模块
		-->
		<vo targetPackage="org.spirit.together.dictionary.api.vo" serializable="true" />
		<!-- 
		  Mapper模块：给Mybatis扫描的Mapper层
		    特殊属性：无	
		 -->
		<mapper targetPackage="org.spirit.together.dictionary.api.mapper" />
		<!-- 
		  XML模块：生成Mybatis映射xml文件
		    特殊属性：无
		 -->
		<xml targetPackage="org.spirit.together.dictionary.api.xml" />
		<!-- 
		  Service模块：生成Service层
		    特殊属性：
		    hasOverride：service的实现层实现的方法是否需要@Override注解
		 -->
		<service targetPackage="org.spirit.together.dictionary.api.service" />
		<!-- 
		  Controller属性：生成Controller
		    特殊属性：
		    controllerAnnotation：controller注解全名[含包名](org.springframework.web.bind.annotation.RestController[默认]/org.springframework.web.bind.annotation.Controller)
		 -->
		<controller targetPackage="org.spirit.together.dictionary.rest.controller" /> 
	</modules>
	<!-- 要生成代码的数据库表 -->
	<tables>	
		<!-- 
		  table：需要生成代码的数据表
		    属性：
			tableName: 数据库表名
			primaryKey: 表主键，默认值id，主键非id字段的需自行指定
		 -->
		<table tableName="dict_state" primaryKey="cd"/>
		<table tableName="dict_province"  primaryKey="cd" />
		<table tableName="dict_city" primaryKey="cd"/>
		<table tableName="dict_district"  primaryKey="cd" />
		<table tableName="dict_data" primaryKey="cd,dict_data_type_cd"/>
		<table tableName="dict_data_type"  primaryKey="cd" />
	</tables>
	<!-- 输出生成的代码的路径 -->
	<output>D:/generate/code</output>
	<!-- sql-java类型映射 -->
	<typeMapping>
		<!-- 
			mapping：具体的sql-java类型映射 
			属性：
				sqlType：数据库类型
				javaType：java类型[带包名]
		-->
		<mapping sqlType="datetime" javaType="java.util.Date" />
	</typeMapping>
</generate>
```

## 使用流程
1. 编写配置文件
2. 如需修改配置文件位置，需在MapperGenerateApplication类中设置配置文件路径xmlPath
3. 启动MapperGenerateApplication

## 使用代码示例
``` Java
package org.spirit.mapper.generate;

import org.spirit.mapper.generate.analyze.MapperGenerate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MapperGenerateApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapperGenerateApplication.class, args);
		MapperGenerate mapperGenerate = new MapperGenerate();
		// 如需修改配置文件地址，取消此注释并修改值
		// mapperGenerate.setXmlPath("D:\\mapper\\generate\\mapper-generate.xml");
		mapperGenerate.generate();
	}
}
```


