package com.bs.messervice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class teacherVo {
    @ApiModelProperty(value = "名字")
    private String name;
    @ApiModelProperty(value = "学院")
    private String college;

    @ApiModelProperty(value = "专业")
    private String majorclass;

}
