package cn.ly.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

/**
 * @author liuyu685
 * @since 2018/7/20
 */
public class JsonUtils {

  /**
   * json字符创装java对象
   *
   * @param json
   * @param clazz
   * @param <T>
   * @return
   */
  public static <T> T toObject(String json, Class<T> clazz) {
    return JSON.parseObject(json, clazz);
  }

  /**
   * json字符串转成java的list集合
   *
   * @param json
   * @param clazz
   * @param <T>
   * @return
   */
  public static <T> List<T> toList(String json, Class<T> clazz) {
    return JSONArray.parseArray(json, clazz);
  }

  /**
   * java对象转成json字符串
   *
   * @param o
   * @return
   */
  public static String toJSONString(Object o) {
    return JSON.toJSONString(
        o, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
  }
}
