package com.victor2022.farlock.exceptions;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

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
