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
 * 我的课程表
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Mycourse对象", description="我的课程表")
public class Mycourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "我的课程id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    @ApiModelProperty(value = "用户")
    private String currentuser;

    @ApiModelProperty(value = "课程")
    private String course;

    @ApiModelProperty(value = "申请用户")
    private String user;

    @ApiModelProperty(value = "预约id")
    private String appointid;

    @ApiModelProperty(value = "实验室")
    private String lab;

    @ApiModelProperty(value = "实验报告")
    private String report;

    @ApiModelProperty(value = "成绩")
    private String score;
    @ApiModelProperty(value = "开始时间")
    private Date gmtBagin;

    @ApiModelProperty(value = "结束时间")
    private Date gmtEnd;


    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
