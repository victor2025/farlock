package com.victor2022.farlock.config;

import com.victor2022.farlock.consts.ConstStrings;
import com.victor2022.farlock.consts.Defaults;
import com.victor2022.farlock.exceptions.ConfigureException;
import lombok.Data;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author: victor2022
 * @date: 2022/07/26  上午11:27
 * @description: jedis的
 */
@Data
public class JedisConfigLoader extends BasicConfig {

    // 最大总连接数
    private Integer maxTotal;
    private Long lockTimeout;

    /**
     * @return: redis.clients.jedis.JedisPoolConfig
     * @author: victor2022
     * @date: 2022/7/26 下午4:38
     * @description: 获取连接池配置
     */
    public JedisPoolConfig getPoolConfig(){
        // 将配置设置到类属性中
        setProperties();
        // 创建并设置config
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(this.maxTotal);
        config.setMinIdle(this.minIdle);
        config.setMaxIdle(this.maxIdle);
        // 返回
        return config;
    }

    /**
     * @return: void
     * @author: victor2022
     * @date: 2022/7/26 下午4:41
     * @description: 设置关键参数
     */
    private void setProperties(){
        try{
            this.ip = properties.getProperty(ConstStrings.CONF_IP, Defaults.DEF_IP);
            this.port = Integer.parseInt(properties.getProperty(ConstStrings.CONF_PORT,Defaults.DEF_JEDIS_PORT));
            this.maxIdle = Integer.parseInt(properties.getProperty(ConstStrings.CONF_JEDIS_MAX,Defaults.DEF_JEDIS_MAX));
            this.minIdle = Integer.parseInt(properties.getProperty(ConstStrings.CONF_JEDIS_MIN,Defaults.DEF_JEDIS_MIN));
            this.timeout = Integer.parseInt(properties.getProperty(ConstStrings.CONF_JEDIS_TIMEOUT,Defaults.DEF_JEDIS_TIMEOUT));
            this.lockTimeout=Long.parseLong(properties.getProperty(ConstStrings.CONF_LOCK_TIMEOUT,Defaults.DEF_LOCK_TIMEOUT));
            // 当前独有
            this.maxTotal = Integer.parseInt(properties.getProperty(ConstStrings.CONF_JEDIS_TOTAL,Defaults.DEF_JEDIS_TOTAL));
        }catch (Exception e){
            throw new ConfigureException();
        }
    }

}
