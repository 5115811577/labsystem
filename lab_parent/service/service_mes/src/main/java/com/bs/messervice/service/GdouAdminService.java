package com.bs.messervice.service;

import com.bs.messervice.entity.GdouAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.messervice.entity.vo.loginVo;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
public interface GdouAdminService extends IService<GdouAdmin> {

    boolean deleAdmin(String adminId);

    String login(loginVo loginForm);
}
