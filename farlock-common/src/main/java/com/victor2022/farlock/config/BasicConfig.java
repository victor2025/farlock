package com.victor2022.farlock.config;

import com.victor2022.farlock.consts.Defaults;
import com.victor2022.farlock.consts.Strings;
import com.victor2022.farlock.exceptions.ConfigureNotFoundException;
import com.victor2022.farlock.utils.IDUtils;
import com.victor2022.farlock.utils.PathUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author: victor2022
 * @date: 2022/07/26  上午11:44
 * @description: 全局配置
 */
@EnableConfigurationProperties(BasicConfig.class)
@ConfigurationProperties(prefix = Strings.CONF_PREFIX)
@Data
public class BasicConfig {

    /**
     * @author: victor2022
     * @date: 2022/7/26 下午3:01
     * @description: 锁中心类型
     */
    private static String centerType;

    /**
     * @author: victor2022
     * @date: 2022/7/26 下午3:01
     * @description: 设备ID
     */
    private static long deviceId;

    /**
     * @author: victor2022
     * @date: 2022/7/26 下午4:25
     * @description: 从文件中读取的配置
     */
    protected static Properties properties;

    /**
     * @author: victor2022
     * @date: 2022/7/27 上午11:00
     * @description: 非静态公有属性，在具体配置中加载
     */
    protected String ip;
    protected int port;



    static{
        // 启动后读取配置文件
        loadProperties();
        // 设置centerType和设备id，其余属性延迟到子类进行补充
        centerType = properties.getProperty(Strings.CONF_TYPE, Defaults.DEF_TYPE);
        deviceId = IDUtils.getRandomInt(Defaults.ID_BOUND);
    }

    /**
     * @return: void
     * @author: victor2022
     * @date: 2022/7/26 下午5:31
     * @description: 读取配置文件
     */
    private static void loadProperties(){
        // 读取文件，创建配置
        FileInputStream is = null;
        try {
            is = new FileInputStream(PathUtils.getConfigFilePath());
            Properties props = new Properties();
            props.load(is);
            properties = props;
        } catch (IOException e) {
            throw new ConfigureNotFoundException();
        }
    }

    public static Properties getProperties(){
        return properties;
    }
    public static String getCenterType(){return centerType;}
    public static long getDeviceId(){
        return deviceId;
    }

}
