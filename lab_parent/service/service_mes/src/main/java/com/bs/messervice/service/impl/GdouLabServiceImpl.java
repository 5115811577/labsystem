package com.bs.messervice.service.impl;

import com.bs.messervice.entity.GdouLab;
import com.bs.messervice.mapper.GdouLabMapper;
import com.bs.messervice.service.GdouLabService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 实验室表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
@Service
public class GdouLabServiceImpl extends ServiceImpl<GdouLabMapper, GdouLab> implements GdouLabService {

    @Override
    public boolean deleLab(String labId) {
        int i = baseMapper.deleteById(labId);
        return i>0;
    }
}
