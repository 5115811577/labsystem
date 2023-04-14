package com.bs.messervice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class equipVo {
    @ApiModelProperty(value = "仪器名称")
    private String name;

    @ApiModelProperty(value = "仪器类型")
    private String type;

    @ApiModelProperty(value = "位置")
    private String location;

    @ApiModelProperty(value = "仪器状态 Draft可以使用  Normal已坏")
    private String status;
}
