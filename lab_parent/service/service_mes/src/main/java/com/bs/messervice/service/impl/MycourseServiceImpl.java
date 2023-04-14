package com.bs.messervice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.messervice.entity.Mycourse;
import com.bs.messervice.mapper.MycourseMapper;
import com.bs.messervice.service.MycourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 我的课程表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
@Service
public class MycourseServiceImpl extends ServiceImpl<MycourseMapper, Mycourse> implements MycourseService {

    @Override
    public boolean deleMycourse(String mycourseId) {
        int i = baseMapper.deleteById(mycourseId);
        return i>0;

    }

    @Override
    public List<Mycourse> getMycourseByName(String user) {

        QueryWrapper<Mycourse> wrapper = new QueryWrapper<>();
        wrapper.eq("currentuser",user);
        wrapper.orderByAsc("gmt_create");
        List<Mycourse> mycourses = baseMapper.selectList(wrapper);
        return mycourses;
    }

    @Override
    public Mycourse getMycourseByNameAndById(String appointid, String name) {
        QueryWrapper<Mycourse> wrapper = new QueryWrapper<>();
        System.out.println(appointid+name);
        wrapper.eq("currentuser",name);
        wrapper.eq("appointid",appointid);
        Mycourse mycourse = baseMapper.selectOne(wrapper);
        return mycourse;
    }
}
