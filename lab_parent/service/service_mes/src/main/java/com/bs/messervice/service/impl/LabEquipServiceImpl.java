package com.bs.messervice.service.impl;

import com.bs.messervice.entity.LabEquip;
import com.bs.messervice.mapper.LabEquipMapper;
import com.bs.messervice.service.LabEquipService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 设备表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
@Service
public class LabEquipServiceImpl extends ServiceImpl<LabEquipMapper, LabEquip> implements LabEquipService {

    @Override
    public boolean deleEquip(String equipId) {

        int i = baseMapper.deleteById(equipId);
        return i>0;
    }
}
