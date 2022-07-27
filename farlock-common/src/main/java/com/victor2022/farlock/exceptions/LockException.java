package com.victor2022.farlock.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author: victor2022
 * @date: 2022/7/27 上午11:12
 * @description:
 */
@AllArgsConstructor
public class LockException extends VictorException{
    private String msg;

    @Override
    public String getMessage() {
        return msg;
    }
}
