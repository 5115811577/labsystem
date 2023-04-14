package com.bs.messervice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class studentVo {
    @ApiModelProperty(value = "学院")
    private String college;
    @ApiModelProperty(value = "专业班级")
    private String majorclass;
}
