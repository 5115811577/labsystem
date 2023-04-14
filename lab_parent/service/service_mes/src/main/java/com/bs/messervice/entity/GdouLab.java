package com.bs.messervice.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 实验室表
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GdouLab对象", description="实验室表")
public class GdouLab implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "实验室id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "实验室位置")
    private String location;

    @ApiModelProperty(value = "实验室管理员")
    private String principal;

    @ApiModelProperty(value = "容纳人数")
    private String number;

    @ApiModelProperty(value = "实验室简介")
    private String introduction;

    @ApiModelProperty(value = "实验室设备")
    private String equipment;

    @ApiModelProperty(value = "课程状态 Draft未使用  Normal正在使用")
    private String status;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
