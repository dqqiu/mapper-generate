package org.spirit.mapper.generate;

import org.spirit.mapper.generate.analyze.MapperGenerate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MapperGenerateApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapperGenerateApplication.class, args);
		MapperGenerate mapperGenerate = new MapperGenerate();
		mapperGenerate.setXmlPath((args != null && args.length > 0) ? args[0] : "");
		mapperGenerate.generate();
	}
}
