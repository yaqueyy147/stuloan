package com.stuloan.web.util;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.Properties;

/**
 * Created by Administrator on 2018/5/9 0009.
 */
public class PropertyUtil {

    /**
     * Spring 提供的 PropertiesLoaderUtils 允许您直接通过基于类路径的文件地址加载属性资源
     * 最大的好处就是：实时加载配置文件，修改后立即生效，不必重启
     */
    public static Properties getproperties(String proertyfilename) throws Exception{
        Properties props = new Properties();
        while(true){
            try {
                props= PropertiesLoaderUtils.loadAllProperties(proertyfilename);
                return props;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
        }
    }

    public static void main(String[] args) {
        try {
            Properties props = getproperties("zfbinfo.properties");
            props.get("pid");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
