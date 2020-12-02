package com.luozd.project.flywaydemo2.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;


public class Config {
    private static String defaultYmalPath = "/application.yml";

    /**读取默认配置文件
     * @return
     */
    public Map<String, Object> getConfig() {
        try{
            InputStream configStream = Config.class.getResourceAsStream(defaultYmalPath);
            if (configStream == null) {
                configStream = Config.class.getClassLoader().getParent().getResourceAsStream(defaultYmalPath);
            }
            if (configStream == null) {
                throw new Exception("Cannot find " + defaultYmalPath + " !!!");
            }
            Yaml yaml = new Yaml();
            Map<String, Object> map =(Map<String, Object>)yaml.load(configStream);
            return map;
        }catch (Exception ex){
        }
        return null;
    }

    /**读取指定配置文件
     * @param ymalPath
     * @return
     */
    public Map<String, Object> getConfigFrom(String ymalPath) {
        try{
            InputStream configStream = Config.class.getResourceAsStream(ymalPath);
            if (configStream == null) {
                configStream = Config.class.getClassLoader().getParent().getResourceAsStream(ymalPath);
            }
            if (configStream == null) {
                throw new Exception("Cannot find " + ymalPath + " !!!");
            }
            Yaml yaml = new Yaml();
            Map<String, Object> map =(Map<String, Object>)yaml.load(configStream);
            return map;
        }catch (Exception ex){
        }
        return null;
    }
}
