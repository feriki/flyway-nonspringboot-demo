package com.luozd.project.flywaydemo2.flyway;

import com.luozd.project.flywaydemo2.utils.Config;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.flywaydb.core.internal.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MigrationMySql {

    private static Map<String,Object> configMap;
    private static Map<String,Object> datasourceMap;
    private static Map<String,Object> flywayMap;

    static {
        Config config=new Config();
        configMap=config.getConfig();
        datasourceMap=(Map<String,Object>)configMap.getOrDefault("datasource",new HashMap<>());
        flywayMap=(Map<String,Object>)configMap.getOrDefault("flyway",new HashMap<>());
    }

    public void migrate(){
        Boolean enabled=(Boolean)flywayMap.getOrDefault("enabled","false");
        if(enabled){
            String url=(String)datasourceMap.getOrDefault("url","");
            String username=(String)datasourceMap.getOrDefault("username","");
            String password=(String)datasourceMap.getOrDefault("password","");
            if(!StringUtils.hasText(url)||!StringUtils.hasText(username)){
                throw new IllegalArgumentException("datasource.url与datasource.username不能为空");
            }


            String table=(String)flywayMap.getOrDefault("table","flyway_schema_history");
            boolean baselineOnMigrate=(Boolean)flywayMap.getOrDefault("baseline-on-migrate","false");
            Integer baselineVersion=(Integer)flywayMap.getOrDefault("baseline-version","1");

            ArrayList<String> locations=(ArrayList<String>)flywayMap.getOrDefault("locations","classpath:db/migration");

            String driverClassName=(String)flywayMap.getOrDefault("driver-class-name","com.mysql.cj.jdbc.Driver");
            try {
                Class.forName(driverClassName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            FluentConfiguration configuration = Flyway.configure();

            configuration.dataSource(url,username,password);
            configuration.table(table);
            String[] array = locations.toArray(new String[locations.size()]);
            configuration.locations(array);
            configuration.baselineOnMigrate(baselineOnMigrate);
            configuration.baselineVersion(String.valueOf(baselineVersion));
            configuration.encoding("UTF-8");
            // 针对数据库在同一段时间有修改，但不会造成冲突的情况，通常实际项目中主要存在这样的情况
            configuration.outOfOrder(false);
            //初始化flyway类
            Flyway flyway = new Flyway(configuration);
            try {
                flyway.migrate();
            } catch (Exception e) {
                flyway.repair();
                e.printStackTrace();
            }
        }
    }
}
