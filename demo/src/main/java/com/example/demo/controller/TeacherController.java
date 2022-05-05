package com.example.demo.controller;


import com.example.demo.common.Result;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.TeacherQuery;
import com.example.demo.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 教师 前端控制器
 * </p>
 *
 * @author fuyao
 * @since 2022-05-03
 */
@RestController
@RequestMapping("/demo/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    /**
     * 查询所有教师
     * @return
     */
    @ApiOperation(value = "获取所有教师")
    @GetMapping("findAll")
    public Result findAllTeacher(){
        List<Teacher> list = teacherService.list(null);
        return Result.ok().data("items",list);
    }
    /**
     * 添加教师
     */
    @ApiOperation(value = "添加教师")
    @PostMapping("addTeacher")
    public Result addTeacher(@RequestBody Teacher teacher){
        boolean save = teacherService.save(teacher);
        if (save){
            return Result.ok();
        }
        else {
            return Result.error();
        }
    }
    /**
     * 多条件查询教师
     */
    @PostMapping("pageTeacherOnCondiction/{current}/{limit}")
    public Result pageTeacherOnCondiction(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) TeacherQuery teacherQuery){
        Map map = teacherService.listTeacher(current, limit, teacherQuery);
        return Result.ok().data("total",map.get("total")).data("rows",map.get("rows"));
    }
}

