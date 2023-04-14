package com.bs.messervice.Utils.typeHander;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


/**
 * @author admin
 * @版本號 1.0
 * @開發者: fu
 * @創建時間: 2021/5/24 21:52
 * @Content:
 */
public abstract class AbstractJson2ListJacksonTypeHandler<T> extends AbstractJsonTypeHandler<List<T>> {

    private final Class<? extends List<T>> type;

    private static final ObjectMapper om;

    static {
        om = new ObjectMapper();
    }

    public AbstractJson2ListJacksonTypeHandler(Class<? extends List<T>> type) {
        this.type = type;
    }

    @SneakyThrows
    @Override
    protected List<T> parse(String json) {
        return om.readValue(json, new TypeReference<List>() {
            @Override
            public Type getType() {
                return getTemplateType();
            }
        });
    }

    @SneakyThrows
    @Override
    protected String toJson(List<T> obj) {
        return om.writeValueAsString(obj);
    }

    /**
     *
     * @return List<T>格式的Type
     */
    public Type getTemplateType() {
        Type  t = null;
        try {
            // 获取T的实际类型
            // 发生异常时则表示无法获取T的实际类型（T 的类型未知）
            Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()) .getActualTypeArguments()[0];

            // 构造一个A<B>格式的Type
            t = TypeToken.getParameterized(List.class, clazz).getType();

        } catch (Exception e) {
            // 构造一个类A格式的Type
            t = TypeToken.getParameterized(List.class).getType();
        }
        return t;
    }

}
