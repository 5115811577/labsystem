package com.bs.messervice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bs.messervice.entity.GdouTeacher;
import com.bs.messervice.entity.GdouTeacher;
import com.bs.messervice.entity.vo.teacherVo;
import com.bs.messervice.service.GdouTeacherService;
import com.bs.utils.MD5;
import com.bs.utils.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 老师表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
@RestController
@RequestMapping("/messervice/teacher")
public class GdouTeacherController {

    @Autowired
    private GdouTeacherService teacherService;
    @GetMapping("findAll")
    public R findAllStudent(){
        List<GdouTeacher> list = teacherService.list(null);
        return R.ok().data("allTeacher",list);
    }
    //根据ID删除学生
    @DeleteMapping("{teacherId}")
    public R deleStudent(@PathVariable String teacherId){
        boolean r  = teacherService.deleTeacher(teacherId);
        if (r){return R.ok();}else {return R.error();}
    }
    //增加学生
    @PostMapping("addStudent")
    public R addStudent(@RequestBody GdouTeacher teacher){
        String password = teacher.getPassword();
        String encrypt = MD5.encrypt(password);
        teacher.setPassword(encrypt);
        boolean save = teacherService.save(teacher);
        if (save){return R.ok();}else {return R.error();}

    }
    //根据ID查询学生
    @GetMapping("getStudent/{id}")
    public R getStudent(@PathVariable String id){
        GdouTeacher byId = teacherService.getById(id);
        return R.ok().data("teacher",byId);
    }
    //更新学生信息
    @PostMapping("updateStudent")
    public R updateStudent(@RequestBody GdouTeacher teacher){
        boolean b = teacherService.updateById(teacher);
        if (b){return R.ok();}else {return R.error();}
    }
    //分页查询
    @GetMapping("pageStudent/{current}/{limit}")
    public R pageStudent(@PathVariable long current,@PathVariable long limit){
        //创建page对象
        Page<GdouTeacher> pageTeacher = new Page<>(current,limit);
        teacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();
        List<GdouTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total",total).data("records",records);
    }
    //条件分页查询
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,@PathVariable long limit,
                                  @RequestBody(required = false) teacherVo teacher) {
        //创建page对象
        Page<GdouTeacher> pageTeacher = new Page<>(current,limit);

        //构建条件
        QueryWrapper<GdouTeacher> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String college = teacher.getCollege();
        String majorclass = teacher.getMajorclass();
        String name = teacher.getName();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(college)) {
            //构建条件
            wrapper.eq("college",college);
        }
        if(!StringUtils.isEmpty(majorclass)) {
            wrapper.like("majorclass",majorclass);
        }
        if(!StringUtils.isEmpty(name)) {
            wrapper.like("name",name);
        }

        //排序
        wrapper.orderByAsc("gmt_create");


        //调用方法实现条件查询分页
        teacherService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();//总记录数
        List<GdouTeacher> records = pageTeacher.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }


}

