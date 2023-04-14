package com.bs.messervice.controller;


import com.aliyuncs.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bs.base.exceptionhandler.LabException;
import com.bs.messervice.entity.LabAppoint;
import com.bs.messervice.entity.Mycourse;
import com.bs.messervice.entity.vo.labVo;
import com.bs.messervice.service.LabAppointService;
import com.bs.messervice.service.MycourseService;
import com.bs.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 实验室预约表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-03-19
 */
@RestController
@RequestMapping("/messervice/appoint")
public class LabAppointController {
    @Autowired
    public LabAppointService appointService;
    @Autowired
    private MycourseService mycourseService ;
    @GetMapping("findAll")
    public R findAllAppoint(){
        List<LabAppoint> list = appointService.list(null);
        return R.ok().data("allAppoint",list);
    }
    //根据ID删除实验室
    @DeleteMapping("{appointId}")
    public R deleLab(@PathVariable String appointId){
        boolean r  = appointService.deleAppoint(appointId);
        if (r){return R.ok();}else {return R.error();}
    }
    //增加实验室
    @PostMapping("addAppoint")
    public R addAppoint(@RequestBody LabAppoint appoint){
        boolean save = appointService.save(appoint);
        if (save){return R.ok();}else {return R.error();}

    }
    //根据ID查询实验室
    @GetMapping("getAppoint/{id}")
    public R getAppoint(@PathVariable String id){
        LabAppoint byId = appointService.getById(id);
        return R.ok().data("Appoint",byId);
    }

    //更新实验室信息
    @PostMapping("updateAppoint")
    public R updateLab(@RequestBody LabAppoint appoint){
        boolean b = appointService.updateById(appoint);
        if (b){return R.ok();}else {return R.error();}
    }
    //分页查询实验室
    @GetMapping("pageAppoint/{current}/{limit}")
    public R pageAppoint(@PathVariable long current,@PathVariable long limit){
        //创建page对象
        Page<LabAppoint> pageAppoint = new Page<>(current,limit);
        appointService.page(pageAppoint,null);
        long total = pageAppoint.getTotal();
        List<LabAppoint> records = pageAppoint.getRecords();
        return R.ok().data("total",total).data("records",records);
    }
    @PostMapping("pageAppointCondition/{current}/{limit}")
    public R pageLabCondition(@PathVariable long current,@PathVariable long limit) {
        //创建page对象
        Page<LabAppoint> pageLab = new Page<>(current,limit);
        //构建条件
        QueryWrapper<LabAppoint> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql

        //判断条件值是否为空，如果不为空拼接条件

        wrapper.orderByAsc("gmt_create");
        //调用方法实现条件查询分页
        appointService.page(pageLab,wrapper);

        long total = pageLab.getTotal();//总记录数
        List<LabAppoint> records = pageLab.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }
    @GetMapping("getReportList/{id}")
    public R getReportList(@PathVariable String id){
        LabAppoint byId = appointService.getById(id);
        if (byId == null) {
            // 处理未找到实验室预约的情况
            throw new LabException(20001,"shibaiasdasdasdasda");
        }
        List<String> studentlist = byId.getStudentlist();

        String appointid = byId.getId();
        List<Mycourse> list = new ArrayList<>();

        studentlist.forEach(name -> {
           Mycourse mycourse =mycourseService.getMycourseByNameAndById(appointid,name);
           list.add(mycourse);
        });

        return R.ok().data("reportList",list);
    }


}

