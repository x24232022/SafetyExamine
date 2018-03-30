package com.avicsafety.lib.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @author: whb
 * @date: 2017-05-02 13:56
 */

public class GsonUtils {
    /**
     * 自定义TypeAdapter ,null对象将被解析成空字符串
     */
    public static final TypeAdapter<String> STRING = new TypeAdapter<String>() {
        public String read(JsonReader reader) {
            try {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return "";
                }else{
                    String s = reader.nextString();
                    if("null".equalsIgnoreCase(s)){
                        s = "";
                    }
                    return s;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        public void write(JsonWriter writer, String value) {
            try {
                if (value == null) {
                    writer.nullValue();
                    return;
                }
                writer.value(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 自定义TypeAdapter ,null对象将被解析成空字符串
     */
    public static final TypeAdapter<Integer> INT = new TypeAdapter<Integer>() {
        public Integer read(JsonReader reader) {
            try {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return 0;
                }else{
                    if(reader.peek()==JsonToken.BOOLEAN){
                        if(reader.nextBoolean())
                            return 1;
                        else
                            return 0;
                    }else{
                        return reader.nextInt();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }

        public void write(JsonWriter writer, Integer value) {
            try {
                if (value == null) {
                    writer.nullValue();
                    return;
                }
                writer.value(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 自定义TypeAdapter ,null对象将被解析成空字符串
     */
    public static final TypeAdapter<Double> DOUBLE = new TypeAdapter<Double>() {
        public Double read(JsonReader reader) {
            try {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return 0.0d;//原先是返回Null，这里改为返回空字符串
                }else{
                    return reader.nextDouble();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0.0;
        }

        public void write(JsonWriter writer, Double value) {
            try {
                if (value == null) {
                    writer.nullValue();
                    return;
                }
                writer.value(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    public static Gson gson;

    static {
        GsonBuilder gsonBulder = new GsonBuilder();
        gsonBulder.registerTypeAdapter(String.class, STRING);
        gsonBulder.registerTypeAdapter(Integer.class, INT);
        gsonBulder.registerTypeAdapter(int.class, INT);
        //gsonBulder.registerTypeAdapter(Double.class, DOUBLE);
        //gsonBulder.registerTypeAdapter(double.class, DOUBLE);

        //gsonbulder.registertypeadapter(boolean.class, boolean); //int类型对float做兼容

        //通过反射获取instancecreators属性
        try {
            Class builder = (Class) gsonBulder.getClass();
            Field f = builder.getDeclaredField("instanceCreators");
            f.setAccessible(true);
            Map<Type, InstanceCreator<?>> val = (Map<Type, InstanceCreator<?>>) f.get(gsonBulder);//得到此属性的值
            //注册数组的处理器
            gsonBulder.registerTypeAdapterFactory(new CollectionTypeAdapterFactory(new ConstructorConstructor(val)));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        gson = gsonBulder.create();
    }

    /**
     * Json字符串 转为指定对象
     *
     * @param json json字符串
     * @param type 对象类型
     * @param <T>  对象类型
     * @return
     * @throws
     */
    public static <T> T tobean(String json, Class<T> type) throws JsonSyntaxException {
        T obj = gson.fromJson(json, type);
        return obj;
    }

    public static <T> T tobean(String json, Type typeOfT) throws JsonSyntaxException {
        T obj = gson.fromJson(json, typeOfT);
        return obj;
    }

}
