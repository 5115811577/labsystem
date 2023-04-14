package com.bs.messervice.service;

import com.bs.messervice.entity.LabEquip;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 设备表 服务类
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
public interface LabEquipService extends IService<LabEquip> {

    boolean deleEquip(String equipId);
}
