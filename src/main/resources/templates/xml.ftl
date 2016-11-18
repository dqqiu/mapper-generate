<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
PUBLIC "-//ibatis.apache.org//DTD Mapper 3.0//EN"
"http://ibatis.apache.org/dtd/ibatis-3-mapper.dtd">

<!-- generated by mapper-generate-1.0, opensource https://www.github.com/dqqiu/mapper-generate -->
<mapper namespace="${modules['mapper'].targetPackage}.${table.getFirstLetterUpperName()}${modules['mapper'].objectNameSuffix}">
	<resultMap id="${table.camelName}" type="${modules['model'].targetPackage}.${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix}">
	<#list table.fields as field>
		<result property="${field.camelName}" column="${field.name}" />
	</#list>
	</resultMap>
	
	<resultMap id="${table.camelName}${modules['vo'].objectNameSuffix}" type="${modules['vo'].targetPackage}.${table.getFirstLetterUpperName()}${modules['vo'].objectNameSuffix}">
	<#list table.fields as field>
		<result property="${field.camelName}" column="${field.name}" />
	</#list>
	</resultMap>
	
	<!-- 新增记录，会插入空值 -->
	<insert id="insert" parameterType="${modules['model'].targetPackage}.${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix}">
		insert into ${table.name} (
		<#list table.fields as field>
			${field.name}<#if field_index != ((table.fields?size) - 1)>,</#if>
		</#list>
		)
		values (
		<#list table.fields as field>
			${r'#{'}${field.camelName}${r'}'}<#if field_index!=((table.fields?size)-1)>,</#if>
		</#list>
		)
	</insert>

	<!-- 新增记录，不插入空值 -->
	<insert id="insertBySelective" parameterType="${modules['model'].targetPackage}.${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix}">
		insert into ${table.name}
		<trim prefix="(" suffix=")" suffixOverrides=","> 
		<#list table.fields as field>
			<if test="${field.camelName} != null and ${field.camelName} != ''">
				${field.name},
			</if>
		</#list>
		</trim>
		<trim prefix="values (" suffix=")" suffixOverrides=","> 
		<#list table.fields as field>
			<if test="${field.camelName} != null and ${field.camelName} != ''">
				${r'#{'}${field.camelName}${r'}'},
			</if>
		</#list>
		</trim>
	</insert>
	
	<!-- 抽取数据库表中的所有列 -->
	<sql id="columns">
	    <![CDATA[
		<#list table.fields as field><#if field_index==0> ${field.name}</#if><#if field_index!=0>, ${field.name}</#if></#list>
	    ]]>
	</sql>
	
	<!-- 抽取更新条件 -->
	<sql id="update_sql">
		<set>
		<#list table.fields as field>
			<if test="${field.camelName} != null and ${field.camelName} != ''">
				${field.name} = ${r'#{'}${field.camelName}${r'}'},
			</if>
		</#list>
		</set>
	</sql>

	<!-- 根据主键获取记录,每张表必须有主键，否则只能查出一条记录 -->
	<select id="getByPrimaryKey" resultMap="${table.camelName}" parameterType="${modules['model'].targetPackage}.${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix}">
		SELECT
		<include refid="columns" />
		FROM ${table.name} 
		<where> 
		<#list table.primaryKey as key>
			AND ${key} = ${r'#{'}${StringUtils.camel(key)}${r'}'}
		</#list>
		</where>
		limit 1
	</select>
	
	<!-- 抽取查询条件 -->
	<sql id="condition">
		<where>
		<#list table.fields as field>
			<if test="${field.camelName} != null and ${field.camelName} != ''">
				AND ${field.name} = ${r'#{'}${field.camelName}${r'}'}
			</if>
			<#if field.camelName?ends_with("Time")>
			<if test="${field.camelName}Begin != null and ${field.camelName}Begin != ''">
				AND ${field.name} &gt;= ${r'#{'}${field.camelName}Begin${r'}'}
			</if>
			<if test="${field.camelName}End != null and ${field.camelName}End != ''">
				AND ${field.name} &lt;= ${r'#{'}${field.camelName}End${r'}'}
			</if>	
			</#if>
		</#list>
		</where>
	</sql>
	
	<!-- 根据条件查询记录 -->
	<select id="getByCondition" resultMap="${table.camelName}">
		SELECT
		<include refid="columns" />
		FROM ${table.name}
		<include refid="condition" />
	</select>

	<!-- 获取符合条件的记录数 -->
	<select id="getCountByCondition">
		SELECT
		count(*)
		FROM ${table.name}
		<include refid="condition" />
	</select>
	
	<!-- 按主键更新，必须设置主键的值 -->
	<update id="updateByPrimaryKey">
		update ${table.name}
		<include refid="update_sql" />
		<where> 
		<#list table.primaryKey as key>
			AND ${key} = ${r'#{'}${StringUtils.camel(key)}${r'}'}
		</#list>
		</where>
	</update>
	
	<!-- 根据主键删除记录，必须设置主键 -->
	<delete id="deleteByPrimaryKey">
		delete
		from ${table.name}
		<where> 
		<#list table.primaryKey as key>
			AND ${key} = ${r'#{'}${StringUtils.camel(key)}${r'}'}
		</#list>
		</where>
	</delete>
	
	<!-- 根据条件删除记录 -->
	<delete id="deleteByCondition" parameterType="${modules['model'].targetPackage}.${table.getFirstLetterUpperName()}${modules['model'].objectNameSuffix}">
		delete
		from ${table.name}
		<include refid="condition" />
	</delete>
</mapper>

