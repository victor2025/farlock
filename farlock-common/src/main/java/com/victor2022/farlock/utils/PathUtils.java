package com.victor2022.farlock.utils;

import com.victor2022.farlock.consts.ConstStrings;

/**
 * @author: victor2022
 * @date: 2022/7/26 下午11:43
 * @description:
 */
public class PathUtils {

    /**
     * @return: java.lang.String
     * @author: victor2022
     * @date: 2022/7/26 下午11:44
     * @description: 获取类路径
     */
    public static String getClassPath(){
        return PathUtils.class.getResource("/").getPath();
    }

    /**
     * @return: java.lang.String
     * @author: victor2022
     * @date: 2022/7/26 下午11:45
     * @description: 获取配置文件路径
     */
    public static String getConfigFilePath(){
        return getClassPath()+ ConstStrings.CONF_FILENAME;
    }
}
