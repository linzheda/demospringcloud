package com.linzd.basecore.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * 描述 代码生成器
 *
 * @author Lorenzo Lin
 * @created 2020年01月15日 9:50
 */
public class MybatisPlusGenerator {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        //获取配置文件

        Properties properties = null;
        try {
            properties = new Properties();
            ClassPathResource classPathResource = new ClassPathResource("generator.properties");
            InputStream in = classPathResource.getInputStream();
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException("读取配置文件失败", e);
        }

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + File.separator + properties.getProperty("generator.project.name");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("linzd");
        gc.setOpen(false);  // 是否打开输出目录,默认true
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        gc.setEnableCache(false); // 是否在xml中添加二级缓存配置,默认false
        gc.setFileOverride(false); // 是否覆盖已有文件
        gc.setActiveRecord(true);//设置ActiveRecord
        //自定义文件命名
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");


        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(properties.getProperty("generator.jdbc.url"));
        dsc.setDriverName(properties.getProperty("generator.jdbc.driver"));
        dsc.setUsername(properties.getProperty("generator.jdbc.username"));
        dsc.setPassword(properties.getProperty("generator.jdbc.password"));
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.linzd." + properties.getProperty("generator.project.name") + ".core");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        //String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        templateConfig.setController("templates/controller2.java");

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel); // 数据库表映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);// 数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setSuperEntityClass("com.linzd." + properties.getProperty("generator.project.name") + ".common.entity.BaseEntity");//自定义继承的Entity类全称，带包名
        strategy.setEntityLombokModel(true);// 是否为lombok模型
        strategy.setEntityBooleanColumnRemoveIsPrefix(true); // Boolean类型字段是否移除is前缀
        strategy.setRestControllerStyle(true); // 生成 @RestController 控制器
        if (!"all".equals(scanner("是否生成所有实体类(所有请输入:all)"))) {
            strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));//设置要生成的表名
        }
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("createby","createtime","updateby","updatetime");

        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
//        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_"); // 表前缀
        mpg.setStrategy(strategy);
        // mpg.setTemplateEngine(new FreemarkerTemplateEngine()); //设置模板引擎类型，默认为 velocity
        mpg.execute();
    }
}
