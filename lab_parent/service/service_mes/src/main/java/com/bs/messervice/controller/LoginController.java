package com.bs.messervice.controller;
import com.bs.base.exceptionhandler.LabException;
import com.bs.messervice.entity.GdouAdmin;
import com.bs.messervice.entity.GdouStudent;
import com.bs.messervice.entity.GdouTeacher;
import com.bs.messervice.entity.vo.loginVo;
import com.bs.messervice.service.GdouAdminService;
import com.bs.messervice.service.GdouStudentService;
import com.bs.messervice.service.GdouTeacherService;
import com.bs.utils.JwtUtils;
import com.bs.utils.MD5;
import com.bs.utils.R;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("messervice")
public class LoginController {
    @Autowired
    private GdouAdminService adminService;
    @Autowired
    private GdouStudentService studentService;
    @Autowired
    private GdouTeacherService teacherService;



    @PostMapping("/login")
    public R login(@RequestBody loginVo loginForm){
        if (StringUtils.isEmpty(loginForm.getUsername())||StringUtils.isEmpty(loginForm.getPassword())){throw new LabException(20001,"用户名或密码为空");}
        switch (loginForm.getUserType()){
            case 1:
                  String atoken=adminService.login(loginForm);
                    return R.ok().data("token",atoken);
            case 2:
                String stoken=studentService.login(loginForm);
                return R.ok().data("token",stoken);

            case 3:
                String token=teacherService.login(loginForm);
                return R.ok().data("token",token);
        }
        return R.ok();
    }
    @GetMapping("getUserInfo")
    public R getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        Integer userTypeByJwtTolen = JwtUtils.getUserTypeByJwtTolen(request);
        switch (userTypeByJwtTolen){
            case 1:
                GdouAdmin byId = adminService.getById(memberId);
                return R.ok().data("roles",byId.getSymbol()).data("name",byId.getName()).data("avatar",1);
            case 2:
                GdouStudent byId1 = studentService.getById(memberId);
                return R.ok().data("roles",byId1.getSymbol()).data("name",byId1.getName()).data("avatar",2);
            case 3:
                GdouTeacher byId2 = teacherService.getById(memberId);
                return R.ok().data("roles",byId2.getSymbol()).data("name",byId2.getName()).data("avatar",3);
        }

        return R.ok();
    }
    @PostMapping("updatePWd/{oldPWd}/{newPWd}")
    public R updatePWd(@PathVariable String oldPWd,@PathVariable String newPWd,HttpServletRequest request){
        boolean b = JwtUtils.checkToken(request);
        if (b){
            return R.ok().message("token过期失效，请重新登录后修改密码");
        }
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        Integer userTypeByJwtTolen = JwtUtils.getUserTypeByJwtTolen(request);
        oldPWd= MD5.encrypt(oldPWd);
        newPWd= MD5.encrypt(newPWd);
        switch (userTypeByJwtTolen){
            case 1:
                GdouAdmin byId = adminService.getById(memberIdByJwtToken);
                if (byId.getPassword().equals(oldPWd)){
                    byId.setPassword(newPWd);
                    adminService.saveOrUpdate(byId);
                    return R.ok().message("修改成功");
                }else {
                    return R.ok().message("原密码有误");
                }
            case 2:
                GdouStudent byId1 = studentService.getById(memberIdByJwtToken);
                if (byId1.getPassword().equals(oldPWd)){
                    byId1.setPassword(newPWd);
                    studentService.saveOrUpdate(byId1);
                    return R.ok().message("修改成功");
                }else {
                    return R.ok().message("原密码有误");
                }
            case 3:
                GdouTeacher byId2 = teacherService.getById(memberIdByJwtToken);
                if (byId2.getPassword().equals(oldPWd)){
                    byId2.setPassword(newPWd);
                    teacherService.saveOrUpdate(byId2);
                    return R.ok().message("修改成功");
                }else {
                    return R.ok().message("原密码有误");
                }
        }

        return R.ok();
    }
    @GetMapping("getIdAndType")
    public R getIdAndType(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        Integer type = JwtUtils.getUserTypeByJwtTolen(request);
        return R.ok().data("id",memberId).data("type",type);
    }
    @GetMapping("getUser")
    public R getUser(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        Integer type = JwtUtils.getUserTypeByJwtTolen(request);
        switch (type){
            case 1:
                GdouAdmin byId = adminService.getById(memberId);
                return R.ok().data("myinfo",byId).data("type",1);
            case 2:
                GdouStudent byId1 = studentService.getById(memberId);
                return R.ok().data("myinfo",byId1).data("type",2);
            case 3:
                GdouTeacher byId2 = teacherService.getById(memberId);
                return R.ok().data("myinfo",byId2).data("type",3);
        }

        return R.ok();
    }
    @PostMapping("/logout")
    public R logout(HttpSession session) {
        session.invalidate();
        return R.ok().data("redirect:/login","redirect:/login");
    }


}
