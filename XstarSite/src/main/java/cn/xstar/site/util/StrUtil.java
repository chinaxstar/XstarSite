package cn.xstar.site.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class StrUtil {
    public static <K, V> String map(Map<K, V> map) {
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isEmpty(String text) {
        return null == text || "".equals(text.trim());
    }

    public static int parseInt(String text) {
        return isEmpty(text) ? 0 : Integer.parseInt(text);
    }

    public static double parseDouble(String text) {
        return isEmpty(text) ? 0.0 : Double.parseDouble(text);
    }
}
