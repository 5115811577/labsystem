package com.bs.messervice.service;

import com.bs.messervice.entity.LabAppoint;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 实验室预约表 服务类
 * </p>
 *
 * @author testjava
 * @since 2023-03-19
 */
public interface LabAppointService extends IService<LabAppoint> {

    boolean deleAppoint(String appointId);
}
