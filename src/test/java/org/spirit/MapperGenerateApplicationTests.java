package org.spirit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spirit.mapper.generate.MapperGenerateApplication;
import org.spirit.mapper.generate.analyze.DBAnalyze;
import org.spirit.mapper.generate.analyze.MapperGenerateXmlAnalyze;
import org.spirit.mapper.generate.meta.GenerateMeta;
import org.spirit.mapper.generate.meta.db.TableMeta;
import org.spirit.mapper.generate.utils.FreemarkerUtils;
import org.spirit.mapper.generate.utils.JDBCUtils;
import org.spirit.mapper.generate.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MapperGenerateApplication.class)
public class MapperGenerateApplicationTests {
    @Autowired
    private JDBCUtils jdbcUtils;
    @Autowired
    private DBAnalyze dbAnalyze;
	@Test
	public void test() {
	  GenerateMeta analyze = MapperGenerateXmlAnalyze.analyze();
	  List<String> tableNames = analyze.getTableNames();
	  List<TableMeta> analyzeTables = dbAnalyze.analyzeTables(tableNames);
	  System.out.println(analyzeTables.size());
	  Map<String, Object> root = new HashMap<>();
	  root.put("targetPackage", analyze.getModules().get(0).getTargetPackage());
	  root.put("typeMap", analyzeTables.get(0).getFieldTypeMap());
	  root.put("className", StringUtils.firstLetterToUpper(StringUtils.camel("dict_state")));
	  root.put("fields", analyzeTables.get(0).getFields());
	  try {
      FreemarkerUtils.printFile("model.ftl", root, analyze.getModules().get(0).getTargetPackage().replaceAll("\\.", "/") + "/" + StringUtils.firstLetterToUpper(StringUtils.camel("dict_state")) + ".java", analyze.getOutputPath(), "");
    } catch (Exception e) {
      e.printStackTrace();
    }
	}

}
