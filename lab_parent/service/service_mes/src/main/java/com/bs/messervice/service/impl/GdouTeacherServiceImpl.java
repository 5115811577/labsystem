package com.bs.messervice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.base.exceptionhandler.LabException;
import com.bs.messervice.entity.GdouTeacher;
import com.bs.messervice.entity.GdouTeacher;
import com.bs.messervice.entity.vo.loginVo;
import com.bs.messervice.mapper.GdouTeacherMapper;
import com.bs.messervice.service.GdouTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.utils.JwtUtils;
import com.bs.utils.MD5;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 老师表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
@Service
public class GdouTeacherServiceImpl extends ServiceImpl<GdouTeacherMapper, GdouTeacher> implements GdouTeacherService {

    @Override
    public boolean deleTeacher(String teacherId) {

        int i = baseMapper.deleteById(teacherId);
        return i>0;
    }

    @Override
    public String login(loginVo loginForm) {
        QueryWrapper<GdouTeacher> wrapper = new QueryWrapper<>();
        wrapper.eq("symbol", loginForm.getUsername());
        GdouTeacher gdouTeacher = baseMapper.selectOne(wrapper);
        if (gdouTeacher == null){throw new LabException(20001,"登录失败");}
        if (!MD5.encrypt(loginForm.getPassword()).equals(gdouTeacher.getPassword())){
            throw new LabException(20001,"登录失败");
        }
        if (!loginForm.getPassword().equals(gdouTeacher.getPassword())){throw new LabException(20001,"密码错误");}
        String jwtToken = JwtUtils.getJwtToken(gdouTeacher.getId(), gdouTeacher.getName(),loginForm.getUserType());
        return jwtToken;
    }

}
