package com.yourtion.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateUtil
 * Created by Yourtion on 26/06/2017.
 */
public class DateUtil {
    /**
     * 返回格式化的当前日期
     *
     * @return Date
     * @throws ParseException 错误
     */
    public static Date getDate() throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(sdf.format(date));
    }
}
