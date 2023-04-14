package com.bs.messervice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.base.exceptionhandler.LabException;
import com.bs.messervice.entity.GdouAdmin;
import com.bs.messervice.entity.vo.loginVo;
import com.bs.messervice.mapper.GdouAdminMapper;
import com.bs.messervice.service.GdouAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.utils.JwtUtils;
import com.bs.utils.MD5;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
@Service
public class GdouAdminServiceImpl extends ServiceImpl<GdouAdminMapper, GdouAdmin> implements GdouAdminService {

    @Override
    public boolean deleAdmin(String adminId) {
        int i = baseMapper.deleteById(adminId);
        return i > 0;
    }

    @Override
    public String login(loginVo loginForm) {
        QueryWrapper<GdouAdmin> wrapper = new QueryWrapper<>();
        wrapper.eq("symbol", loginForm.getUsername());
        GdouAdmin gdouAdmin = baseMapper.selectOne(wrapper);
        if (gdouAdmin == null) {
            throw new LabException(20001, "登录失败");
        }
        if (!MD5.encrypt(loginForm.getPassword()).equals(gdouAdmin.getPassword())){
            throw new LabException(20001,"登录失败");
        }
//        if (!loginForm.getPassword().equals(gdouAdmin.getPassword())) {
//            throw new LabException(20001, "密码错误");
//        }
        String jwtToken = JwtUtils.getJwtToken(gdouAdmin.getId(), gdouAdmin.getName(), loginForm.getUserType());

        return jwtToken;
    }
}



