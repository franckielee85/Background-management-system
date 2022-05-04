package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.TeacherQuery;
import com.example.demo.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        //创建Page对象
        Page<Teacher> page = new Page<>(current,limit);
        //构建条件（可以使用动态SQL）
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        //获取条件
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //哪个不空就按条件输入哪个
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            queryWrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            queryWrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create",end);
        }
        //根据创建时间进行排序
        queryWrapper.orderByDesc("gmt_create");
        teacherService.page(page, queryWrapper);
        long total = page.getTotal();
        List<Teacher> records = page.getRecords();
        return Result.ok().data("total",total).data("rows",records);
    }
}

