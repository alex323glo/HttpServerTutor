package com.alex323glo.utils;

/**
 * Created by alex323glo on 14.10.17.
 */
public class UrlUtils {

    public static String[] parseURL(String url) {
        if (url == null || url.isEmpty()) {
            throw new NullPointerException("null or empty url");
        }

        return url.split("/");
    }

}
