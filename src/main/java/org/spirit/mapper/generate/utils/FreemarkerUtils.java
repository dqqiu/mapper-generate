package org.spirit.mapper.generate.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @Project       : mapper-generate
 * @Program Name  : org.spirit.mapper.generate.utils.FreemarkerUtils.java
 * @Description   : qiudequan Freemarker工具类
 * @Author        : qiudequan
 * @Creation Date : 2016年11月3日 下午3:47:49 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * qiudequan     2016年11月3日        create
 */
public class FreemarkerUtils {

    /**
     *  @Description	: qiudequan 打印到控制台
     *  @param          : @param ftlName
     *  @param          : @param root
     *  @param          : @param ftlPath
     *  @param          : @throws Exception
     *  @return 		: void
     *  @Creation Date  : 2016年11月3日 下午3:48:17 
     *  @Author         : qiudequan
     */
    public static void print(String ftlName, Map<String,Object> root, String ftlPath) throws Exception{
        try {
            Template temp = getTemplate(ftlName, ftlPath);      //通过Template可以将模板文件输出到相应的流
            temp.process(root, new PrintWriter(System.out));
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     *  @Description	: qiudequan 输出到输出到文件
     *  @param          : @param ftlName    ftl文件名     
     *  @param          : @param root       传入的map     
     *  @param          : @param outFile    输出后的文件全部路径 
     *  @param          : @param filePath   输出前的文件上部路径 
     *  @param          : @param ftlPath
     *  @param          : @throws Exception
     *  @return 		: void
     *  @Creation Date  : 2016年11月3日 下午3:48:50 
     *  @Author         : qiudequan
     */
    public static void printFile(String ftlName, Map<String,Object> root, String outFile, String filePath, String ftlPath) throws Exception{
        try {
            File file = new File(PathUtils.getClasspath() + filePath + outFile);
            if(!file.getParentFile().exists()){             //判断有没有父路径，就是判断文件整个路径是否存在
                file.getParentFile().mkdirs();              //不存在就全部创建
            }
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
            Template template = getTemplate(ftlName, ftlPath);
            template.process(root, out);                    //模版输出
            out.flush();
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 通过文件名加载模版
     * @param ftlName
     */
    public static Template getTemplate(String ftlName, String ftlPath) throws Exception{
        try {
            Configuration cfg = new Configuration();                                                //通过Freemaker的Configuration读取相应的ftl
            cfg.setEncoding(Locale.CHINA, "utf-8");
            cfg.setDirectoryForTemplateLoading(new File(PathUtils.getClassResources()+"/ftl/"+ftlPath));     //设定去哪里读取相应的ftl模板文件
            Template temp = cfg.getTemplate(ftlName);                                               //在模板文件目录中找到名称为name的文件
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
