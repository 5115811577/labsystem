package com.bs.messervice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.base.exceptionhandler.LabException;
import com.bs.messervice.entity.GdouStudent;
import com.bs.messervice.entity.GdouStudent;
import com.bs.messervice.entity.GdouTeacher;
import com.bs.messervice.entity.vo.loginVo;
import com.bs.messervice.entity.vo.studentVo;
import com.bs.messervice.mapper.GdouStudentMapper;
import com.bs.messervice.service.GdouStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.utils.JwtUtils;
import com.bs.utils.MD5;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 学生表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
@Service
public class GdouStudentServiceImpl extends ServiceImpl<GdouStudentMapper, GdouStudent> implements GdouStudentService {


    @Override
    public boolean deleStudent(String studengid) {
        int i = baseMapper.deleteById(studengid);
        return i>0;
    }

    @Override
    public String login(loginVo loginForm) {

        QueryWrapper<GdouStudent> wrapper = new QueryWrapper<>();
        wrapper.eq("symbol", loginForm.getUsername());
        GdouStudent gdouStudent = baseMapper.selectOne(wrapper);
        if (gdouStudent == null){throw new LabException(20001,"登录失败");}
        if (!MD5.encrypt(loginForm.getPassword()).equals(gdouStudent.getPassword())){
            throw new LabException(20001,"登录失败");
        }
        String jwtToken = JwtUtils.getJwtToken(gdouStudent.getId(), gdouStudent.getName(),loginForm.getUserType());
        return jwtToken;
    }


}
