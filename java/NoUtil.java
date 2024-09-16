package com.zhang.demo.util;

import lombok.Data;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
/**
 * @author ZhangEnHui
 */
public class NoUtil {
    public static String generateNo(String typeCode, boolean isShort) {
        StringBuilder sb = new StringBuilder();
        sb.append(typeCode);
        if (isShort) {
            sb.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss")));
        } else {
            sb.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmssSSS")));
        }
        Integer randomInt = (new Random()).nextInt(9999);
        String random = (new DecimalFormat("0000")).format(randomInt);
        sb.append(random);
        return sb.toString();
    }
}
