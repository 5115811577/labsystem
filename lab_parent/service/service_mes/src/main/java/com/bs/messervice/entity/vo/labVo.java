package com.bs.messervice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class labVo {

    @ApiModelProperty(value = "实验室位置")
    private String location;
}
