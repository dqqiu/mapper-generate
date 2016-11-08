package ${modules['mapper'].targetPackage};

import ${modules['model'].targetPackage};
import org.spirit.mapper.generate.meta.support.BaseMapper;

/**
 * 
 * @created by mapper-generate
 */
public interface DictStateMapper extends BaseMapper<${modules['model'].targetPackage?substring(modules['model'].targetPackage?last_index_of('.') + 1)}>{
  
}
