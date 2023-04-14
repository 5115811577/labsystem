package com.bs.demo;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @版本号 1.0
 * @开发者: fu
 * @创建时间: 2021/5/20 17:31
 * @Content:
 */
public class MyTest {

    public static void main(String[] args) {
        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");

        gc.setAuthor("admin");
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setFileOverride(false); //重新生成时文件是否覆盖
        gc.setServiceName("%sService");	//去掉Service接口的首字母I
        gc.setIdType(IdType.AUTO); //主键策略
        /*gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型*/
        gc.setSwagger2(true);//开启Swagger2模式

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://xi.fu6.top:3356/zr_menu?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("menu");
        dsc.setPassword("menu5583");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();

        pc.setParent("icom.fu.demo");
        pc.setModuleName("menu"); //模块名
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
//        pc.setXml("mapper");
        mpg.setPackageInfo(pc);


        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("menu_collect", "menu_comment", "menu_cuisine", "menu_menu", "menu_posts", "menu_user");
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

        mpg.setStrategy(strategy);


        // 6、执行
        mpg.execute();
    }

//    public static void main(String[] args) {
//        ZoneId zone = ZoneId.systemDefault();
////        long theDayStart = LocalDate.now().plusDays(-1).atStartOfDay().atZone(zone).toInstant().toEpochMilli();
////        long end = LocalDate.now().plusDays(-1 + 1).atStartOfDay().atZone(zone).toInstant().toEpochMilli();
////        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
////        System.out.println(LocalDate.now().plusDays(-1).atStartOfDay().format(dateTimeFormatter));
////        System.out.println(theDayStart);
////        System.out.println(LocalDate.now().plusDays(-1 + 1).atStartOfDay().format(dateTimeFormatter));
////        System.out.println(end - 1);
//        long todayBeginMillis = LocalDate.now().atStartOfDay().atZone(zone).toInstant().toEpochMilli();
////        System.out.println(todayBeginMillis);
//        long yesterdayBeginMillis = LocalDate.now().plusDays(-1).atStartOfDay().atZone(zone).toInstant().toEpochMilli();
//        long yesterdayEndMillis = LocalDate.now().atStartOfDay().atZone(zone).toInstant().toEpochMilli() - 1;
//        System.out.println(yesterdayBeginMillis);
//        System.out.println(yesterdayEndMillis);
//    }

}
