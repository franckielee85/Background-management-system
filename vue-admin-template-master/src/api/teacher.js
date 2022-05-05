import request from '@/utils/request'

export default{
  // 按条件获取讲师列表
  getTeacherList(current, limit, teacherQuery) {
    return request({
      url: `/demo/teacher/pageTeacherOnCondiction/${current}/${limit}`,
      // teacherQuery条件对象，后端使用RequestBody获取数据
      // data表示把对象转换json进行传递到接口里面
      method: 'post',
      data: teacherQuery
    })
  },
  // 删除讲师
  deleteTeacherById(id) {
    return request({
      url: `/edu/teacher/${id}`,
      method: 'delete'
    })
  },
  // 添加讲师
  addTeacher(teacher) {
    return request({
      url: `/demo/teacher/addTeacher`,
      method: 'post',
      data: teacher
    })
  },
  // 按照ID查询讲师
  getTeacherInfo(id) {
    return request({
      url: `/edu/teacher/getTeacher/${id}`,
      method: 'get'
    })
  },
  // 修改讲师
  updateTeacherInfo(teacher) {
    return request({
      url: `/edu/teacher/updateTeacher`,
      method: 'post',
      data: teacher
    })
  }
}
