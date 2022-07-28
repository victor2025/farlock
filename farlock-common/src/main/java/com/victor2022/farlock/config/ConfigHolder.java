package com.victor2022.farlock.config;

import com.victor2022.farlock.exceptions.ConfigureNotFoundException;
import com.victor2022.farlock.utils.PathUtils;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author: victor2022
 * @date: 2022/07/28  下午1:52
 * @description: 根据参数进行初始化
 * 总在所有其他配置初始化之前执行配置的读取操作
 * 有两种初始化方案：
 * 1. 自行读取配置文件进行初始化
 * 2. 根据传入的配置文件进行初始化
 */
@Getter
public class ConfigHolder {

    // 静态配置对象，可以传递给子类
    protected static FarlockConfig config;

    /**
     * @return: null
     * @author: victor2022
     * @date: 2022/7/28 下午1:55
     * @description: 自行读取配置文件进行初始化
     */
    public ConfigHolder() {
        // 读取配置文件
        Properties props = loadProperties();
        ConfigHolder.config = new FarlockConfig(props);
    }

    /**
     * @param config:
     * @return: null
     * @author: victor2022
     * @date: 2022/7/28 下午2:12
     * @description: 根据由spring传入的配置文件进行初始化
     */
    public ConfigHolder(FarlockConfig config){
        ConfigHolder.config = config;
    }

    /**
     * @return: java.util.Properties
     * @author: victor2022
     * @date: 2022/7/28 下午1:58
     * @description: 读取配置文件
     */
    private Properties loadProperties(){
        // 读取文件，创建配置
        FileInputStream is = null;
        try {
            is = new FileInputStream(PathUtils.getConfigFilePath());
            Properties props = new Properties();
            props.load(is);
            return props;
        } catch (IOException e) {
            throw new ConfigureNotFoundException();
        }
    }

    /**
     * @return: com.victor2022.farlock.config.FarlockConfig
     * @author: victor2022
     * @date: 2022/7/28 下午2:34
     * @description: 返回config对象
     */
    public static FarlockConfig getConfig(){
        return config;
    }
}
