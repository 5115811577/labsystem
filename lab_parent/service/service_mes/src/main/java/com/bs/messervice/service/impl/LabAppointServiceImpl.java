package com.bs.messervice.service.impl;

import com.bs.messervice.entity.LabAppoint;
import com.bs.messervice.mapper.LabAppointMapper;
import com.bs.messervice.service.LabAppointService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 实验室预约表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2023-03-19
 */
@Service
public class LabAppointServiceImpl extends ServiceImpl<LabAppointMapper, LabAppoint> implements LabAppointService {

    @Override
    public boolean deleAppoint(String appointId) {

        int i = baseMapper.deleteById(appointId);
        return i>0;

    }
}
