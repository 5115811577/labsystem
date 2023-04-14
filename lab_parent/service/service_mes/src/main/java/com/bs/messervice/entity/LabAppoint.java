package com.bs.messervice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.bs.messervice.Utils.typeHander.Json2ListStringJacksonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 实验室预约表
 * </p>
 *
 * @author testjava
 * @since 2023-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value="LabAppoint对象", description="实验室预约表")
public class LabAppoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "预约id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "用户")
    private String user;

    @ApiModelProperty(value = "课程")
    private String course;

    @ApiModelProperty(value = "实验室")
    private String lab;

    @ApiModelProperty(value = "开始时间")
    private Date gmtBagin;

    @ApiModelProperty(value = "结束时间")
    private Date gmtEnd;

    @ApiModelProperty(value = "实验室简介")
    private String introduction;
    @TableField(typeHandler = FastjsonTypeHandler.class)
    @ApiModelProperty(value = "参与人集合")
    private List<String> studentlist;

    @ApiModelProperty(value = "实验室状态 Draft未同意  Normal已同意")
    private String status;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
