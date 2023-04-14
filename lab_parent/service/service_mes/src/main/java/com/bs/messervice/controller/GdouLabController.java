package com.bs.messervice.controller;


import com.aliyuncs.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bs.messervice.entity.GdouCourse;
import com.bs.messervice.entity.GdouLab;
import com.bs.messervice.entity.vo.courseVo;
import com.bs.messervice.entity.vo.labVo;
import com.bs.messervice.service.GdouLabService;
import com.bs.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 实验室表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
@RestController
@RequestMapping("/messervice/lab")
public class GdouLabController {
    @Autowired
    private GdouLabService labService ;

    //查询所有实验室
    @GetMapping("findAll")
    public R findAllLab(){
        List<GdouLab> list = labService.list(null);
        return R.ok().data("allLab",list);
    }
    //根据ID删除实验室
    @DeleteMapping("{labId}")
    public R deleLab(@PathVariable String labId){
        boolean r  = labService.deleLab(labId);
        if (r){return R.ok();}else {return R.error();}
    }
    //增加实验室
    @PostMapping("addLab")
    public R addLab(@RequestBody GdouLab lab){
        boolean save = labService.save(lab);
        if (save){return R.ok();}else {return R.error();}

    }
    //根据ID查询实验室
    @GetMapping("getLab/{id}")
    public R getLab(@PathVariable String id){
        GdouLab byId = labService.getById(id);
        return R.ok().data("lab",byId);
    }
    //根据位置查询实验室
    @PostMapping("getLocal/{local}")
    public R getLocal(@RequestBody String local){
        QueryWrapper<GdouLab> wrapper = new QueryWrapper<>();
        wrapper.like("local", local);
        List<GdouLab> list = labService.list(wrapper);
        return R.ok().data("lab",list);

    }
    //更新实验室信息
    @PostMapping("updateLab")
    public R updateLab(@RequestBody GdouLab lab){
        boolean b = labService.updateById(lab);
        if (b){return R.ok();}else {return R.error();}
    }
    //分页查询实验室
    @GetMapping("pageLab/{current}/{limit}")
    public R pageLab(@PathVariable long current,@PathVariable long limit){
        //创建page对象
        Page<GdouLab> pageLab = new Page<>(current,limit);
        labService.page(pageLab,null);
        long total = pageLab.getTotal();
        List<GdouLab> records = pageLab.getRecords();
        return R.ok().data("total",total).data("records",records);
    }
    @PostMapping("pageLabCondition/{current}/{limit}")
    public R pageLabCondition(@PathVariable long current,@PathVariable long limit,
                                @RequestBody(required = false) labVo lab) {
        //创建page对象
        Page<GdouLab> pageLab = new Page<>(current,limit);
        //构建条件
        QueryWrapper<GdouLab> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String locat = lab.getLocation();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(locat)) {
            //构建条件
            wrapper.like("locat",locat);
        }
        //排序
        wrapper.orderByAsc("gmt_create");
        //调用方法实现条件查询分页
        labService.page(pageLab,wrapper);

        long total = pageLab.getTotal();//总记录数
        List<GdouLab> records = pageLab.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }


}

