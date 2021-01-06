package com.wt.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName FormatUtil
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/6 8:50
 **/
public class FormatUtil {
    public static String formatDate(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
