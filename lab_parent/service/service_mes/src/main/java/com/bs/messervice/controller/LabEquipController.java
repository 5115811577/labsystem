package com.bs.messervice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.bs.messervice.entity.LabEquip;

import com.bs.messervice.entity.vo.equipVo;
import com.bs.messervice.service.LabEquipService;
import com.bs.utils.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 设备表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
@RestController
@RequestMapping("/messervice/equip")
public class LabEquipController {
    @Autowired
    private LabEquipService equipService ;

    //查询所有设备
    @GetMapping("findAll")
    public R findAllEquip(){
        List<LabEquip> list = equipService.list(null);
        return R.ok().data("allEquip",list);
    }
    //根据ID删除设备
    @DeleteMapping("{equipId}")
    public R deleEquip(@PathVariable String equipId){
        boolean r  = equipService.deleEquip(equipId);
        if (r){return R.ok();}else {return R.error();}
    }
    //增加设备
    @PostMapping("addEquip")
    public R addEquip(@RequestBody LabEquip equip){
        boolean save = equipService.save(equip);
        if (save){return R.ok();}else {return R.error();}

    }
    //根据ID查询设备
    @GetMapping("getEquid/{id}")
    public R getEquip(@PathVariable String id){
        LabEquip byId = equipService.getById(id);
        return R.ok().data("equip",byId);
    }
    //更新设备信息
    @PostMapping("updateEquip")
    public R updateEquip(@RequestBody LabEquip equip){
        boolean b = equipService.updateById(equip);
        if (b){return R.ok();}else {return R.error();}
    }
    //分页查询
    @GetMapping("pageEquip/{current}/{limit}")
    public R pageEquip(@PathVariable long current,@PathVariable long limit){
        //创建page对象
        Page<LabEquip> pageEquip = new Page<>(current,limit);
        equipService.page(pageEquip,null);
        long total = pageEquip.getTotal();
        List<LabEquip> records = pageEquip.getRecords();
        return R.ok().data("total",total).data("records",records);
    }
    //条件分页查询
    @PostMapping("pageEquipCondition/{current}/{limit}")
    public R pageEquipCondition(@PathVariable long current,@PathVariable long limit,
                                  @RequestBody(required = false) equipVo equip) {
        //创建page对象
        Page<LabEquip> pageEquip = new Page<>(current,limit);

        //构建条件
        QueryWrapper<LabEquip> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String location = equip.getLocation();
        String name = equip.getName();
        String status = equip.getStatus();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(location)) {
            //构建条件
            wrapper.like("location",location);
        }
        if(!StringUtils.isEmpty(name)) {
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(status)) {
            wrapper.eq("status",status);
        }

        //排序
        wrapper.orderByAsc("gmt_create");


        //调用方法实现条件查询分页
        equipService.page(pageEquip,wrapper);

        long total = pageEquip.getTotal();//总记录数
        List<LabEquip> records = pageEquip.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

}

