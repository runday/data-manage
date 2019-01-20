package com.sdcsoft.datamanage.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期Util类
 * @Author Brimen
 */
public class DateUtil {

    SimpleDateFormat simpleDateFormat=null;

    /**
     * 格式化日期
     * @param format
     * @param date
     * @return
     */
    public static Date formatDate(String format, String date){
        try {
            if(null!=date){
                return new SimpleDateFormat(format).parse(date);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

       return null;
    }

}
