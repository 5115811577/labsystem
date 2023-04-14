package com.bs.messervice.service;

import com.bs.messervice.entity.Mycourse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 我的课程表 服务类
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
public interface MycourseService extends IService<Mycourse> {

    boolean deleMycourse(String mycourseId);

    List<Mycourse> getMycourseByName(String user);

    Mycourse getMycourseByNameAndById(String name, String appointid);
}
