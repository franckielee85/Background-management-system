package com.example.demo.service;

import com.example.demo.common.Result;
import com.example.demo.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.TeacherQuery;

import java.util.Map;

/**
 * <p>
 * 老师 服务类
 * </p>
 *
 * @author fuyao
 * @since 2022-05-03
 */
public interface TeacherService extends IService<Teacher> {

    Map listTeacher(long current, long limit, TeacherQuery teacherQuery);
}
