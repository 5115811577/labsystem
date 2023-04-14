package com.bs.messervice.controller;


import com.aliyuncs.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bs.messervice.entity.GdouAdmin;
import com.bs.messervice.entity.vo.adminVo;
import com.bs.messervice.service.GdouAdminService;
import com.bs.utils.MD5;
import com.bs.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
@RestController
@RequestMapping("/messervice/admin")
public class GdouAdminController {

    @Autowired
    private GdouAdminService adminService;
    @GetMapping("findAll")
    public R findAllStudent(){
        List<GdouAdmin> list = adminService.list(null);
        return R.ok().data("allAdmin",list);
    }
    //根据ID删除用户
    @DeleteMapping("{adminId}")
    public R deleStudent(@PathVariable String adminId){
        boolean r  = adminService.deleAdmin(adminId);
        if (r){return R.ok();}else {return R.error();}
    }
    //增加用户
    @PostMapping("addAdmin")
    public R addStudent(@RequestBody GdouAdmin admin){
        String password = admin.getPassword();
        String encrypt = MD5.encrypt(password);
        admin.setPassword(encrypt);

        boolean save = adminService.save(admin);
        if (save){return R.ok();}else {return R.error();}

    }
    //根据ID查询用户
    @GetMapping("getStudent/{id}")
    public R getStudent(@PathVariable String id){
        GdouAdmin byId = adminService.getById(id);
        return R.ok().data("admin",byId);
    }
    //更新用户信息
    @PostMapping("updateStudent")
    public R updateStudent(@RequestBody GdouAdmin admin){
        boolean b = adminService.updateById(admin);
        if (b){return R.ok();}else {return R.error();}
    }
    //分页查询
    @GetMapping("pageStudent/{current}/{limit}")
    public R pageStudent(@PathVariable long current,@PathVariable long limit){
        //创建page对象
        Page<GdouAdmin> pageAdmin = new Page<>(current,limit);
        adminService.page(pageAdmin,null);
        long total = pageAdmin.getTotal();
        List<GdouAdmin> records = pageAdmin.getRecords();
        return R.ok().data("total",total).data("records",records);
    }
    //条件分页查询
    @PostMapping("pageAdminCondition/{current}/{limit}")
    public R pageAdminCondition(@PathVariable long current,@PathVariable long limit,
                                  @RequestBody(required = false) adminVo admin) {
        //创建page对象
        Page<GdouAdmin> pageAdmin = new Page<>(current,limit);

        //构建条件
        QueryWrapper<GdouAdmin> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String college = admin.getCollege();
        String name = admin.getName();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(college)) {
            //构建条件
            wrapper.eq("college",college);
        }

        if(!StringUtils.isEmpty(name)) {
            wrapper.like("name",name);
        }

        //排序
        wrapper.orderByAsc("gmt_create");


        //调用方法实现条件查询分页
        adminService.page(pageAdmin,wrapper);

        long total = pageAdmin.getTotal();//总记录数
        List<GdouAdmin> records = pageAdmin.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

}

