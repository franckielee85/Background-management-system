package com.example.demo.controller;


import com.example.demo.common.Result;
import com.example.demo.entity.Teacher;
import com.example.demo.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 老师 前端控制器
 * </p>
 *
 * @author fuyao
 * @since 2022-05-03
 */
@RestController
@RequestMapping("/demo/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    /**
     * 查询所有老师
     * @return
     */
    @ApiOperation(value = "所有老师列表")
    @GetMapping("findAll")
    public Result findAllTeacher(){
        List<Teacher> list = teacherService.list(null);
        return Result.ok().data("items",list);
    }
    /**
     * 添加老师
     */
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
}

