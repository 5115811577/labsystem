package com.bs.messervice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bs.messervice.entity.GdouStudent;
import com.bs.messervice.entity.vo.studentVo;
import com.bs.messervice.service.GdouStudentService;
import com.bs.utils.MD5;
import com.bs.utils.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 学生表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
@RestController
@RequestMapping("/messervice/student")
public class GdouStudentController {

    @Autowired
   private GdouStudentService studentService ;

    //查询所有学生
    @GetMapping("findAll")
    public R findAllStudent(){
        List<GdouStudent> list = studentService.list(null);
        return R.ok().data("allStudent",list);
    }
//根据ID删除学生
    @DeleteMapping("{studentId}")
    public R deleStudent(@PathVariable String studentId){
        boolean r  = studentService.deleStudent(studentId);
        if (r){return R.ok();}else {return R.error();}
    }
    //增加学生
    @PostMapping("addStudent")
    public R addStudent(@RequestBody GdouStudent student){
        String password = student.getPassword();
        String encrypt = MD5.encrypt(password);
        student.setPassword(encrypt);
        boolean save = studentService.save(student);
        if (save){return R.ok();}else {return R.error();}

    }
    //根据ID查询学生
    @GetMapping("getStudent/{id}")
    public R getStudent(@PathVariable String id){
        GdouStudent byId = studentService.getById(id);
        return R.ok().data("student",byId);
    }
    //更新学生信息
    @PostMapping("updateStudent")
    public R updateStudent(@RequestBody GdouStudent student){
        boolean b = studentService.updateById(student);
        if (b){return R.ok();}else {return R.error();}
    }
    //分页查询
    @GetMapping("pageStudent/{current}/{limit}")
    public R pageStudent(@PathVariable long current,@PathVariable long limit){
        //创建page对象
        Page<GdouStudent> pageStudent = new Page<>(current,limit);
        studentService.page(pageStudent,null);
        long total = pageStudent.getTotal();
        List<GdouStudent> records = pageStudent.getRecords();
        return R.ok().data("total",total).data("records",records);
    }
    //条件分页查询
    @PostMapping("pageStudentCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,@PathVariable long limit,
                                  @RequestBody(required = false) studentVo student) {
        //创建page对象
        Page<GdouStudent> pageTeacher = new Page<>(current,limit);

        //构建条件
        QueryWrapper<GdouStudent> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String college = student.getCollege();
        String majorclass = student.getMajorclass();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(college)) {
            //构建条件
            wrapper.like("college",college);
        }
        if(!StringUtils.isEmpty(majorclass)) {
            wrapper.like("majorclass",majorclass);
        }

        //排序
        wrapper.orderByAsc("gmt_create");


        //调用方法实现条件查询分页
        studentService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();//总记录数
        List<GdouStudent> records = pageTeacher.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }



}

