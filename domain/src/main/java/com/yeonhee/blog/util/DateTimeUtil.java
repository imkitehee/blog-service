package com.yeonhee.blog.util;

import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class DateTimeUtil {

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("uuuuMMdd");

    public static final DateTimeFormatter DATE_FORMAT_DASH = DateTimeFormatter.ofPattern("uuuu-MM-dd");

}
