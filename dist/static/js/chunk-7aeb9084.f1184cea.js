(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-7aeb9084"],{"204b":function(e,t,r){"use strict";r("99af");var s=r("b775");t["a"]={pageMycourse:function(e,t){return Object(s["a"])({url:"/messervice/mycourse/pageMycourse/".concat(e,"/").concat(t),method:"get"})},findAllMycourse:function(){return Object(s["a"])({url:"/messervice/mycourse/findAll",method:"get"})},deleMycourse:function(e){return Object(s["a"])({url:"/messervice/mycourse/".concat(e),method:"delete"})},getMycourse:function(e){return Object(s["a"])({url:"/messervice/mycourse/getMycourse/".concat(e),method:"get"})},updateMycourse:function(e){return Object(s["a"])({url:"/messervice/mycourse/updateMycourse",method:"post",data:e})},addMycourse:function(e){return Object(s["a"])({url:"/messervice/mycourse/addMycourse",method:"post",data:e})},getMycourseByName:function(e){return Object(s["a"])({url:"/messervice/mycourse/getMycourseByName/".concat(e),method:"get"})}}},"8f09":function(e,t,r){"use strict";r.r(t);var s=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",[r("el-form",{attrs:{"label-width":"120px"}},[r("el-form-item",{attrs:{label:"实验室："}},[e._v(" "+e._s(e.mycourse.lab)+" ")]),r("el-form-item",{attrs:{label:"课程："}},[r("a",{on:{click:function(t){return e.showDetail(e.mycourse.course)}}},[e._v(e._s(e.mycourse.course)+"（点击可查看）")])]),r("el-form-item",{attrs:{label:"申请用户："}},[e._v(" "+e._s(e.mycourse.user)+" ")]),r("el-form-item",{attrs:{label:"开始时间："}},[e._v(" "+e._s(e.mycourse.gmtBagin)+" ")]),r("el-form-item",{attrs:{label:"结束时间："}},[e._v(" "+e._s(e.mycourse.gmtEnd)+" ")]),r("el-form-item",{attrs:{label:"结束时间："}},[e._v(" "+e._s(e.mycourse.gmtEnd)+" ")]),r("router-link",{attrs:{to:"/lab/studentreport/"+this.mycourse.appointid}},[r("el-button",{attrs:{type:"primary",size:"medium",icon:"el-icon-edit"}},[e._v("实 验 报 告")])],1)],1)],1)},o=[],c=r("204b"),u={data:function(){return{mycourse:{},fileList:[],type:0}},created:function(){var e=this;c["a"].getMycourse(this.$route.params.id).then((function(t){e.mycourse=t.data.mycourse,console.log("账号是:"+e.mycourse.appointid),e.mycourse.report&&(e.fileList=[{name:e.mycourse.report}])}))},methods:{showDetail:function(e){this.$router.push({name:"studentcoursebyname",params:{name:e}})},handleSuccess:function(e){this.fileList=[{name:e}]}}},n=u,a=r("2877"),i=Object(a["a"])(n,s,o,!1,null,null,null);t["default"]=i.exports}}]);