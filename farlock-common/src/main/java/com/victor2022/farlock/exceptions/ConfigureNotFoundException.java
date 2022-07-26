package com.victor2022.farlock.exceptions;

import lombok.NoArgsConstructor;

/**
 * @author: victor2022
 * @date: 2022/07/26  下午4:28
 * @description:
 */
@NoArgsConstructor
public class ConfigureNotFoundException extends VictorException{

    @Override
    public String getMessage() {
        return "Configuration file is missing!";
    }
}
