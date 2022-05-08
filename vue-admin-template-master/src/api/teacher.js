import request from '@/utils/request'

export default{
  // 按条件获取教师列表
  getTeacherList(current, limit, teacherQuery) {
    return request({
      url: `/demo/teacher/pageTeacherOnCondiction/${current}/${limit}`,
      // teacherQuery条件对象，后端使用RequestBody获取数据
      // data表示把对象转换json进行传递到接口里面
      method: 'post',
      data: teacherQuery
    })
  },
  // 删除教师
  deleteTeacherById(ID) {
    return request({
      url: `/demo/teacher/deleteTeacherBy/${ID}`,
      method: 'delete'
    })
  },
  // 添加教师
  addTeacher(teacher) {
    return request({
      url: `/demo/teacher/addTeacher`,
      method: 'post',
      data: teacher
    })
  },
  // 按照ID查询教师
  getTeacherInfo(id) {
    return request({
      url: `/demo/teacher/getTeacher/${id}`,
      method: 'get'
    })
  },
  // 修改教师
  updateTeacherInfo(teacher) {
    return request({
      url: `/demo/teacher/updateTeacher`,
      method: 'post',
      data: teacher
    })
  }
}
