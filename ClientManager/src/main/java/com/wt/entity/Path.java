package com.wt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Path
 * @Description TODO
 * @Author UnKnW
 * @Date 2020/12/30 13:51
 **/

public class Path {
    private static String path;

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        Path.path = path;
    }
}
