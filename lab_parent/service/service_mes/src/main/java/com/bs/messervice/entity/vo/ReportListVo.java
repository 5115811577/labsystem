package com.bs.messervice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReportListVo {
    @ApiModelProperty(value = "姓名")
    private String currentuser;
    @ApiModelProperty(value = "报告")
    private String report;

}
