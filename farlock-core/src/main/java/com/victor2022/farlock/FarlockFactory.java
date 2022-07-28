package com.victor2022.farlock;

import com.mysql.cj.util.StringUtils;
import com.victor2022.farlock.config.ConfigHolder;
import com.victor2022.farlock.config.FarlockConfig;
import com.victor2022.farlock.exceptions.LockException;
import com.victor2022.farlock.locks.Lock;
import com.victor2022.farlock.utils.LockUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: victor2022
 * @date: 2022/07/26  下午12:11
 * @description: 单例的锁工厂
 */
public class FarlockFactory {

    // 锁工厂实例
    private static volatile FarlockFactory lockFactory = null;
    // 配置对象
    private static ConfigHolder configHolder = null;
    // 锁类型
    private Class<Lock> lockType = null;
    // 锁放入map中进行等待
    private volatile Map<String, Lock> lockMap = new ConcurrentHashMap<>();

    private FarlockFactory() {}

    /**
     * @return: com.victor2022.farlock.FarlockFactory
     * @author: victor2022
     * @date: 2022/7/27 上午11:05
     * @description: 单例获取锁工厂
     */
    public static FarlockFactory getFarlockFactory(){
        return getOrCreateFactory(null);
    }

    /**
     * @param config:
     * @return: com.victor2022.farlock.FarlockFactory
     * @author: victor2022
     * @date: 2022/7/28 下午1:24
     * @description: 单例获取锁工厂，判断是否为Spring创建
     */
    public static FarlockFactory getFarlockFactory(FarlockConfig config){
        return getOrCreateFactory(config);
    }

    /**
     * @param config:
     * @return: com.victor2022.farlock.FarlockFactory
     * @author: victor2022
     * @date: 2022/7/28 下午1:25
     * @description: 获取锁工厂内部方法
     */
    private static FarlockFactory getOrCreateFactory(FarlockConfig config){
        if(lockFactory==null){
            synchronized (FarlockFactory.class){
                if(lockFactory==null){
                    lockFactory = initLockFactory(config);
                }
            }
        }
        return lockFactory;
    }

    /**
     * @return: void
     * @author: victor2022
     * @date: 2022/7/28 下午12:46
     * @description: 初始化lockFactory
     * TODO
     */
    private static FarlockFactory initLockFactory(FarlockConfig config){
        // 初始化
        if(config==null){
            // 默认初始化
            FarlockFactory.configHolder = new ConfigHolder();
        }else{
            // 按照配置初始化
            FarlockFactory.configHolder = new ConfigHolder(config);
        }
        // 创建lockFactory
        lockFactory = new FarlockFactory();
        lockFactory.lockType = LockUtils.getClassByCenterType(configHolder.getConfig().getCenterType());
        return lockFactory;
    }

    /**
     * @param lockName:
     * @return: com.victor2022.farlock.locks.Lock
     * @author: victor2022
     * @date: 2022/7/27 上午11:30
     * @description: 获取指定名称的锁
     */
    public Lock getLock(String lockName){
        if(StringUtils.isNullOrEmpty(lockName))throw new LockException("Lock name is invalid!");
        // 从map中获取对应名称的锁
        // 若map中没有，则创建一个
        Lock lock = lockMap.get(lockName);
        if(lock==null){
            synchronized (FarlockFactory.class){
                if(lock==null){
                    lock = createLock(lockName);
                    lockMap.put(lockName,lock);
                }
            }
        }
        return lock;
    }

    /**
     * @param lockName:
     * @return: com.victor2022.farlock.locks.Lock
     * @author: victor2022
     * @date: 2022/7/27 上午11:16
     * @description: 通过反射方式创建指定类型的锁
     */
    private Lock createLock(String lockName) {
        Lock lock = null;
        try {
            lock = lockType.getConstructor(String.class).newInstance(lockName);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return lock;
    }
}
