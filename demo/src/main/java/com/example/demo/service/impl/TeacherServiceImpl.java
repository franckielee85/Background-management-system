package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.TeacherQuery;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 老师 服务实现类
 * </p>
 *
 * @author fuyao
 * @since 2022-05-03
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Autowired
    TeacherService teacherService;
    @Override
    public Map listTeacher(long current, long limit, TeacherQuery teacherQuery) {
        HashMap<String, Object> hashMap = new HashMap<>();
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
        hashMap.put("total",total);
        hashMap.put("rows",records);
        return hashMap;
    }
}
