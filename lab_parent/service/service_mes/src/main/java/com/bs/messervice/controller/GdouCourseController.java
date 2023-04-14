package com.bs.messervice.controller;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bs.base.exceptionhandler.LabException;
import com.bs.messervice.Utils.ConstantVodUtils;
import com.bs.messervice.Utils.InitVodCilent;
import com.bs.messervice.entity.GdouCourse;
import com.bs.messervice.entity.Mycourse;
import com.bs.messervice.entity.vo.courseVo;
import com.bs.messervice.service.GdouCourseService;
import com.bs.utils.R;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 实验课程表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
@RestController
@RequestMapping("/messervice/course")
public class GdouCourseController {
    @Autowired
    private GdouCourseService courseService;
    @GetMapping
    public R getCourseList() {
        List<GdouCourse> list = courseService.list(null);
//        String[] list2 = list.toArray(new String[list.size()]);
        return R.ok().data("list",list);
    }

    //添加课程基本信息的方法
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody GdouCourse course) {
        //返回添加之后课程id，为了后面添加大纲使用
        boolean id = courseService.save(course);
        if (id){return R.ok();}else {return R.error();}
    }

    //根据课程id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        GdouCourse courseInfoVo = courseService.getById(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }

    //修改课程信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody GdouCourse courseInfoVo) {
        courseService.updateById(courseInfoVo);
        return R.ok();
    }

    //删除课程
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId) {
        GdouCourse byId = courseService.getById(courseId);
        String courseId1 = byId.getVideoid();
        if(!StringUtils.isEmpty(courseId1)) {
            //根据视频id，远程调用实现视频删除

            try {
                //初始化对象
                DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
                //创建删除视频request对象
                DeleteVideoRequest request = new DeleteVideoRequest();
                //向request设置视频id
                request.setVideoIds(courseId1);
                //调用初始化对象的方法实现删除
                client.getAcsResponse(request);
                return R.ok();
            }catch(Exception e) {
                e.printStackTrace();
                throw new LabException(20001,"删除视频失败");
            }
        }
        courseService.removeById(courseId);
        return R.ok();
    }    @PostMapping("pageCourseCondition/{current}/{limit}")
    public R pageAdminCondition(@PathVariable long current,@PathVariable long limit,
                                @RequestBody(required = false) courseVo cou) {
        //创建page对象
        Page<GdouCourse> pageAdmin = new Page<>(current,limit);
        //构建条件
        QueryWrapper<GdouCourse> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = cou.getName();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        //排序
        wrapper.orderByAsc("gmt_create");
        //调用方法实现条件查询分页
        courseService.page(pageAdmin,wrapper);

        long total = pageAdmin.getTotal();//总记录数
        List<GdouCourse> records = pageAdmin.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }


//视频上传
    @PostMapping("/uploadVideo")
    public R uploadAlyiVideo(MultipartFile file) {
        String videoUrl="";
        String videoId="";
        try {
            videoId = courseService.uploadVideoAly(file);
            if (videoId != null) {
                List<GetPlayInfoResponse.PlayInfo> list =getPlayAuth(videoId);
                //返回视频播放地址
                 videoUrl = list.get(0).getPlayURL();

            } else {
               return R.error();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return R.ok().data("videourl",videoUrl).data("videoid",videoId);
    }

    /**
     * 阿里云 根据视频id获取视频地址
     * @return
     */

    @ApiOperation(value = "阿里云-根据视频id获取视频地址", notes = "阿里云-根据视频id获取视频地址")
    @GetMapping("/getPlayAuth")
    public List<GetPlayInfoResponse.PlayInfo> getPlayAuth(@RequestParam(name = "id") String id) {
        DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        List<GetPlayInfoResponse.PlayInfo> playInfoList = new ArrayList<>();
        try {
            response = getPlayInfo(client, id);
            playInfoList = response.getPlayInfoList();
            //播放地址
//            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
//                System.out.print("播放地址  PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
//            }
//            //Base信息
//            System.out.print("标题 VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("错误信息 ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("响应 RequestId = " + response.getRequestId() + "\n");
        return playInfoList;
    }

    /*获取播放地址函数*/
    public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client, String id) throws Exception {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(id);
        return client.getAcsResponse(request);
    }
    //根据视频id删除阿里云视频
    @DeleteMapping("removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable String id) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return R.ok();
        }catch(Exception e) {
            e.printStackTrace();
            throw new LabException(20001,"删除视频失败");
        }
    }
    @PostMapping("uploadOssFile")
    public R uploadOssFile(MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径C
        String url = courseService.uploadFile(file);
        return R.ok().data("url",url);
    }
    @GetMapping("getCourseByName/{coursename}")
    public R getCourseByName(@PathVariable String coursename){
        List<GdouCourse> mycourses = courseService.getCourseByName(coursename);
        return R.ok().data("course",mycourses);
    }

}

