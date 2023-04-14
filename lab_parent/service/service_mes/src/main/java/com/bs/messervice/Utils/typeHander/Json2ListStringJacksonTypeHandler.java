package com.bs.messervice.Utils.typeHander;

import java.util.List;

/**
 * @version 1.0
 * @author: fu
 * @time: 2021/9/5 14:43
 * @Content:
 */
public class Json2ListStringJacksonTypeHandler extends AbstractJson2ListJacksonTypeHandler<String> {
    public Json2ListStringJacksonTypeHandler(Class<? extends List<String>> type) {
        super(type);
    }
}
