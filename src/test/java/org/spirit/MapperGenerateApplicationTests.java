package org.spirit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spirit.mapper.generate.MapperGenerateApplication;
import org.spirit.mapper.generate.analyze.MapperGenerate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MapperGenerateApplication.class)
public class MapperGenerateApplicationTests {
  
  @Test
  public void test() {
    MapperGenerate mapperGenerate = new MapperGenerate();
    mapperGenerate.setXmlPath("D:\\mapper-generate.xml");
    mapperGenerate.generate();
  }

}
