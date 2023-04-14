package com.bs.messervice.service;

import com.bs.messervice.entity.GdouTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.messervice.entity.vo.loginVo;

/**
 * <p>
 * 老师表 服务类
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
public interface GdouTeacherService extends IService<GdouTeacher> {

    boolean deleTeacher(String teacherId);

    String login(loginVo loginVo);
}
