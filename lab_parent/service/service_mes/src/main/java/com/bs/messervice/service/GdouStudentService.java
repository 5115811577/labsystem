package com.bs.messervice.service;

import com.bs.messervice.entity.GdouStudent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.messervice.entity.vo.loginVo;
import com.bs.messervice.entity.vo.studentVo;

/**
 * <p>
 * 学生表 服务类
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
public interface GdouStudentService extends IService<GdouStudent> {


    boolean deleStudent(String studengid);

    String login(loginVo loginVo);

}
