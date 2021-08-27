package kr.fiveminutesmarket.common.utils;

public class RedisUtils {

    public static String createKeyWithPrefix(String prefix, String key) {
        return prefix + ":" + key;
    }
}
