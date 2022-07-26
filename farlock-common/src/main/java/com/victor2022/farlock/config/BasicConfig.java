package com.victor2022.farlock.config;

import com.victor2022.farlock.consts.ConstStrings;
import com.victor2022.farlock.exceptions.ConfigureNotFoundException;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * @author: victor2022
 * @date: 2022/07/26  上午11:44
 * @description: 全局配置
 */
@Data
public class BasicConfig {

    /**
     * @author: victor2022
     * @date: 2022/7/26 下午3:01
     * @description: 锁中心IP地址
     */
    protected String ip;

    /**
     * @author: victor2022
     * @date: 2022/7/26 下午3:01
     * @description: 锁中心端口
     */
    protected int port;

    /**
     * @author: victor2022
     * @date: 2022/7/26 下午3:16
     * @description: 最多连接数
     */
    protected int maxIdle;

    /**
     * @author: victor2022
     * @date: 2022/7/26 下午3:17
     * @description: 最少连接数
     */
    protected int minIdle;

    /**
     * @author: victor2022
     * @date: 2022/7/26 下午4:20
     * @description: 连接超时时间
     */
    protected int timeout;

    /**
     * @author: victor2022
     * @date: 2022/7/26 下午4:25
     * @description: 从文件中读取的配置
     */
    protected static Properties properties;

    static{
        // 启动后读取配置文件
        loadProperties();
    }

    /**
     * @return: void
     * @author: victor2022
     * @date: 2022/7/26 下午5:31
     * @description: TODO
     */
    private static void loadProperties(){
        // 读取文件，创建配置
        InputStream is = BasicConfig.class.getResourceAsStream(ConstStrings.CONF_FILENAME);
        try {
            Properties props = new Properties();
            props.load(is);
            properties = props;
        } catch (IOException e) {
            throw new ConfigureNotFoundException();
        }
    }
    /**
     * @return: java.util.Properties
     * @author: victor2022
     * @date: 2022/7/26 下午4:24
     * @description: 读取配置文件
     */
    public static Properties getProperties(){
        return properties;
    }

}
