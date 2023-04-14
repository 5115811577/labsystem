(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-68063845"],{"265b":function(t,e,n){"use strict";n.r(e);var a=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"app-container"},[n("el-form",{staticClass:"demo-form-inline",attrs:{inline:!0}},[n("el-form-item",[n("el-input",{attrs:{placeholder:"实验室位置"},model:{value:t.lab.name,callback:function(e){t.$set(t.lab,"name",e)},expression:"lab.name"}})],1),n("el-button",{staticStyle:{"margin-right":"10px"},attrs:{type:"primary",icon:"el-icon-search"},on:{click:function(e){return t.getList()}}},[t._v("查询")]),n("el-button",{staticStyle:{"margin-right":"10px"},attrs:{type:"primary"},on:{click:function(e){return t.resetData()}}},[t._v("清空")]),1===t.type||3===t.type?n("router-link",{attrs:{to:"/lab/save/"}},[n("el-button",{staticStyle:{"margin-right":"10px"},attrs:{type:"primary",size:"medium",icon:"el-icon-edit"}},[t._v("添加实验室")])],1):t._e(),1===t.type?n("router-link",{attrs:{to:"/lab/check/"}},[n("el-button",{attrs:{type:"primary",size:"medium",icon:"el-icon-edit"}},[t._v("预约审批")])],1):t._e()],1),n("el-table",{attrs:{data:t.list,border:"",fit:"","highlight-current-row":""}},[n("el-table-column",{attrs:{label:"序号",width:"70",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s((t.page-1)*t.limit+e.$index+1)+" ")]}}])}),n("el-table-column",{attrs:{prop:"location",label:"实验室位置",width:"100"}}),n("el-table-column",{attrs:{prop:"principal",label:"实验室管理员"}}),n("el-table-column",{attrs:{prop:"number",label:"容纳人数"}}),n("el-table-column",{attrs:{prop:"status",label:"状态"}}),n("el-table-column",{attrs:{label:"操作",width:"300",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[2===t.type?n("router-link",{attrs:{to:"/lab/studentLab/"+e.row.id}},[n("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-edit"}},[t._v("查看")])],1):t._e(),1===t.type||3===t.type?n("router-link",{attrs:{to:"/lab/teacherLab/"+e.row.id}},[n("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-edit"}},[t._v("查看")])],1):t._e(),1===t.type||3===t.type?n("el-button",{attrs:{type:"danger",size:"mini",icon:"el-icon-delete"},on:{click:function(n){return t.removeDataById(e.row.id)}}},[t._v("删除")]):t._e(),n("router-link",{attrs:{to:"/lab/appointment/"+e.row.id}},[n("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-edit"}},[t._v("预约")])],1)]}}])})],1),n("el-pagination",{staticStyle:{padding:"30px 0","text-align":"center"},attrs:{"current-page":t.page,"page-size":t.limit,total:t.total,layout:"total, prev, pager, next, jumper"},on:{"current-change":t.getList}})],1)},r=[],i=n("bab5"),o=n("474e"),c={data:function(){return{type:0,list:null,page:1,limit:10,total:0,lab:{}}},created:function(){this.getList(),this.gettype()},methods:{getList:function(){var t=this,e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:1;this.page=e,i["a"].getAllLab(this.page,this.limit,this.lab).then((function(e){t.list=e.data.rows,t.total=e.data.total,console.log(t.list),console.log(t.total)}))},resetData:function(){this.lab={},this.getList()},removeDataById:function(t){var e=this;this.$confirm("此操作将永久删除学生记录, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){i["a"].deleteLabId(t).then((function(t){e.$message({type:"success",message:"删除成功!"}),e.getList()}))}))},gettype:function(){var t=this;o["a"].gettype().then((function(e){t.type=e.data.type}))}}},l=c,s=n("2877"),u=Object(s["a"])(l,a,r,!1,null,null,null);e["default"]=u.exports},"474e":function(t,e,n){"use strict";n("99af");var a=n("b775");e["a"]={getInfoById:function(t,e,n){return Object(a["a"])({url:"/messervice/student/pageStudentCondition/".concat(t,"/").concat(e),method:"post",data:n})},getInfoByIdToTea:function(t,e,n){return Object(a["a"])({url:"/messervice/teacher/pageTeacherCondition/".concat(t,"/").concat(e),method:"post",data:n})},getStudentList:function(){return Object(a["a"])({url:"/messervice/student/findAll",method:"get"})},deletestudentId:function(t){return Object(a["a"])({url:"/messervice/student/".concat(t),method:"delete"})},addStudent:function(t){return Object(a["a"])({url:"/messervice/teacher/addTeacher",method:"post",data:t})},getStudentInfo:function(t){return Object(a["a"])({url:"/messervice/teacher/getTeacher/".concat(t),method:"get"})},updateStudentInfo:function(t){return Object(a["a"])({url:"/messervice/teacher/updateTeacher",method:"post",data:t})},deleteTeacherId:function(t){return Object(a["a"])({url:"/messervice/teacher/".concat(t),method:"delete"})},addTeacher:function(t){return Object(a["a"])({url:"/messervice/teacher/addTeacher",method:"post",data:t})},getTeacherInfo:function(t){return Object(a["a"])({url:"/messervice/teacher/getTeacher/".concat(t),method:"get"})},updateTeacherInfo:function(t){return Object(a["a"])({url:"/messervice/teacher/updateTeacher",method:"post",data:t})},getUser:function(){return Object(a["a"])({url:"/messervice/getUser",method:"get"})},deletePassword:function(t,e){return Object(a["a"])({url:"/messervice/updatePWd/".concat(t,"/").concat(e),method:"post"})},gettype:function(){return Object(a["a"])({url:"/messervice/getIdAndType",method:"get"})}}},bab5:function(t,e,n){"use strict";n("99af");var a=n("b775");e["a"]={getAllLab:function(t,e,n){return Object(a["a"])({url:"/messervice/lab/pageLabCondition/".concat(t,"/").concat(e),method:"post",data:n})},deleteLabId:function(t){return Object(a["a"])({url:"/messervice/lab/".concat(t),method:"delete"})},getLabInfo:function(t){return Object(a["a"])({url:"/messervice/lab/getLab/".concat(t),method:"get"})},updateLab:function(t){return Object(a["a"])({url:"/messervice/lab/updateLab",method:"post",data:t})},addLab:function(t){return Object(a["a"])({url:"/messervice/lab/addLab",method:"post",data:t})},addAppoint:function(t){return Object(a["a"])({url:"/messervice/appoint/addAppoint",method:"post",data:t})},findAll:function(){return Object(a["a"])({url:"/messervice/appoint/findAll",method:"get"})},deleAppoint:function(t){return Object(a["a"])({url:"/messervice/appoint/".concat(t),method:"delete"})},updateAppoint:function(t){return Object(a["a"])({url:"/messervice/appoint/updateAppoint",method:"post",data:t})},pageAppoint:function(t,e,n){return Object(a["a"])({url:"/messervice/appoint/pageAppointCondition/".concat(t,"/").concat(e),method:"post",data:n})},getAppoint:function(t){return Object(a["a"])({url:"/messervice/appoint/getAppoint/".concat(t),method:"get"})},getReportList:function(t){return Object(a["a"])({url:"/messervice/appoint/getReportList/".concat(t),method:"get"})}}}}]);