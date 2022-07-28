package com.victor2022.farlock.config;

import com.victor2022.farlock.consts.Defaults;
import com.victor2022.farlock.consts.Strings;
import com.victor2022.farlock.exceptions.ConfigureNotFoundException;
import com.victor2022.farlock.utils.IDUtils;
import com.victor2022.farlock.utils.PathUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.NonNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author: victor2022
 * @date: 2022/07/26  上午11:44
 * @description: 全局配置
 */
@Data
@ConfigurationProperties(prefix = Strings.CONF_PREFIX)
public class FarlockConfig {

    public FarlockConfig(){}

    /**
     * @param props:
     * @return: null
     * @author: victor2022
     * @date: 2022/7/28 下午2:27
     * @description: 由配置文件创建配置对象
     */
    public FarlockConfig(Properties props) {
        if(props.containsKey(Strings.CONF_TYPE))this.centerType = props.getProperty(Strings.CONF_TYPE);
        if(props.containsKey(Strings.CONF_DEVICE_ID))this.deviceId = Long.parseLong(props.getProperty(Strings.CONF_DEVICE_ID));
        if(props.containsKey(Strings.CONF_IP))this.ip = props.getProperty(Strings.CONF_IP);
        if(props.containsKey(Strings.CONF_PORT))this.port = Integer.parseInt(props.getProperty(Strings.CONF_PORT));
        if(props.containsKey(Strings.CONF_JEDIS_MAX))this.maxIdle = Integer.parseInt(props.getProperty(Strings.CONF_JEDIS_MAX));
        if(props.containsKey(Strings.CONF_JEDIS_MIN))this.minIdle = Integer.parseInt(props.getProperty(Strings.CONF_JEDIS_MIN));
        if(props.containsKey(Strings.CONF_JEDIS_TIMEOUT))this.timeout = Integer.parseInt(props.getProperty(Strings.CONF_JEDIS_TIMEOUT));
        if(props.containsKey(Strings.CONF_JEDIS_TOTAL))this.maxTotal = Integer.parseInt(props.getProperty(Strings.CONF_JEDIS_TOTAL));
        if(props.containsKey(Strings.CONF_LOCK_TIMEOUT))this.lockTimeout = Long.parseLong(props.getProperty(Strings.CONF_LOCK_TIMEOUT));
        if(props.containsKey(Strings.CONF_LOCK_PREFIX))this.lockPrefix = props.getProperty(Strings.CONF_LOCK_PREFIX);

    }

    /**
     * @author: victor2022
     * @date: 2022/7/26 下午3:01
     * @description: 锁中心类型
     */
    private String centerType = Defaults.DEF_TYPE;

    /**
     * @author: victor2022
     * @date: 2022/7/26 下午3:01
     * @description: 设备ID
     */
    private long deviceId = IDUtils.getRandomInt(Defaults.ID_BOUND);

    /**
     * @author: victor2022
     * @date: 2022/7/27 上午11:00
     * @description: 非静态公有属性，在具体配置中加载
     */
    private String ip = Defaults.DEF_IP;

    private int port = Integer.parseInt(Defaults.DEF_JEDIS_PORT);

    private int maxTotal = Integer.parseInt(Defaults.DEF_JEDIS_TOTAL);

    private int maxIdle = Integer.parseInt(Defaults.DEF_JEDIS_MAX);

    private int minIdle = Integer.parseInt(Defaults.DEF_JEDIS_MIN);

    private int timeout = Integer.parseInt(Defaults.DEF_JEDIS_TIMEOUT);

    private long lockTimeout = Long.parseLong(Defaults.DEF_LOCK_TIMEOUT);

    private String lockPrefix = Defaults.DEF_LOCK_PREFIX;
}
