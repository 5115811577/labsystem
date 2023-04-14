package com.bs.messervice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class courseVo {
    @ApiModelProperty(value = "实验名称")
    private String name;
}
