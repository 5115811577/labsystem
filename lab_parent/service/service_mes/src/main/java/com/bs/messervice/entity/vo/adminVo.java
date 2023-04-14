package com.bs.messervice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class adminVo {
    @ApiModelProperty(value = "学院")
    private String college;
    @ApiModelProperty(value = "姓名")
    private String name;
}
