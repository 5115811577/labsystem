package com.bs.messervice.service;

import com.bs.messervice.entity.GdouCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 实验课程表 服务类
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
public interface GdouCourseService extends IService<GdouCourse> {

    String uploadVideoAly(MultipartFile file);

    String uploadFile(MultipartFile file);

    List<GdouCourse> getCourseByName(String coursename);
}
