package com.bs.messervice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bs.messervice.entity.Mycourse;

import com.bs.messervice.service.MycourseService;
import com.bs.utils.MD5;
import com.bs.utils.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 我的课程表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
@RestController
@RequestMapping("/messervice/mycourse")
public class MycourseController {
    @Autowired
    private MycourseService mycourseService ;

    //查询所有我的课程
    @GetMapping("findAll")
    public R findAllMycourse(){
        List<Mycourse> list = mycourseService.list(null);
        return R.ok().data("allMycourse",list);
    }
    //根据ID删除我的课程
    @DeleteMapping("{mycourseId}")
    public R deleMycourse(@PathVariable String mycourseId){
        boolean r  = mycourseService.deleMycourse(mycourseId);
        if (r){return R.ok();}else {return R.error();}
    }
    //增加我的课程
    @PostMapping("addMycourse")
    public R addMycourse(@RequestBody Mycourse mycourse){
        boolean save = mycourseService.save(mycourse);
        if (save){return R.ok();}else {return R.error();}

    }
    //根据ID查询我的课程
    @GetMapping("getMycourse/{id}")
    public R getMycourse(@PathVariable String id){
        Mycourse byId = mycourseService.getById(id);
        return R.ok().data("mycourse",byId);
    }
    //更新我的课程信息
    @PostMapping("updateMycourse")
    public R updateMycourse(@RequestBody Mycourse mycourse){
        boolean b = mycourseService.updateById(mycourse);
        if (b){return R.ok();}else {return R.error();}
    }
    //分页查询
    @GetMapping("pageMycourse/{current}/{limit}")
    public R pageMycourse(@PathVariable long current,@PathVariable long limit){
        //创建page对象
        Page<Mycourse> pageMycourse = new Page<>(current,limit);
        mycourseService.page(pageMycourse,null);
        long total = pageMycourse.getTotal();
        List<Mycourse> records = pageMycourse.getRecords();
        return R.ok().data("total",total).data("records",records);
    }
    //根据自己的名字查询自己的课程
    @GetMapping("getMycourseByName/{user}")
    public R getMycourseByName(@PathVariable String user){
        List<Mycourse> mycourses = mycourseService.getMycourseByName(user);
        return R.ok().data("mycourses",mycourses);
    }

}

