package com.victor2022.farlock.exceptions;

/**
 * @author: victor2022
 * @date: 2022/07/26  下午4:27
 * @description:
 */
public class VictorException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Inner exception";
    }
}
