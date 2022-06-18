package com.me.framework.util;

import org.yaml.snakeyaml.Yaml;

import java.util.LinkedHashMap;
import java.util.Map;

/*** 读取yaml配置文件工具类
 * @author yg
 * @date 2022/5/12 10:51
 * @version 1.0
 */
public class YamlUtil {

    private Yaml yaml = new Yaml();

    private Map<String,Object> map;

    public YamlUtil(){
        this("application.yml");
    }

    public YamlUtil(String path){
        map = yaml.load(ClassLoader.getSystemResourceAsStream(path));
    }

    /**
     * 获取指定类型的值
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getValueByKey(String key,Class<T> clazz){
        String [] sp = key.split("\\.");
        if(sp.length == 1){
            return clazz.cast(map.get(key));
        }else{
            Map<String,Object> objectMap = this.getMapByKey(this.map,sp[0]);
            if(objectMap != null) {
                if (sp.length == 2) {
                    return clazz.cast(objectMap.get(sp[1]));
                }else {
                    for (int i = 1; i < sp.length; i++) {
                        if(i == sp.length-1){
                            return clazz.cast(objectMap.get(sp[i]));
                        }
                        if((objectMap = this.getMapByKey(objectMap,sp[i])) == null){
                            return null;
                        }
                    }
                }
            }
        }
        return null;
    }

    private Map<String,Object> getMapByKey(Map<String,Object> objectMap,String key){
        if(objectMap.containsKey(key)){
            return (LinkedHashMap<String,Object>)objectMap.get(key);
        }
        return null;
    }
}
