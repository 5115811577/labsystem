package com.bs.messervice.service;

import com.bs.messervice.entity.GdouLab;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 实验室表 服务类
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
public interface GdouLabService extends IService<GdouLab> {

    boolean deleLab(String labId);
}
