<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="教师名称">
        <el-input v-model="teacherObject.name"/>
      </el-form-item>
      <el-form-item label="教师头衔">
        <el-select v-model="teacherObject.level" clearable placeholder="请选择">
          <el-option :value="1" label="高级教师"/>
          <el-option :value="2" label="中级教师"/>
          <el-option :value="2" label="初级教师"/>
        </el-select>
      </el-form-item>
      <el-form-item label="教师资历">
        <el-input v-model="teacherObject.career"/>
      </el-form-item>
      <el-form-item label="教师简介">
        <el-input v-model="teacherObject.about" :rows="10" type="textarea"/>
      </el-form-item>

      <!-- 教师头像 -->
      <el-form-item label="教师头像">

        <!-- 头衔缩略图 和teacherObeject对象中的这个属性绑定-->
        <pan-thumb :image="String(teacherObject.avatar)"/>
        <!-- 文件上传按钮，点击就会弹出更换头像的弹窗 -->
        <el-button type="primary" icon="el-icon-upload" @click="imagecropperShow=true">更换头像
        </el-button>

        <!--
      v-show：是否显示上传组件
      :key：类似于id，如果一个页面多个图片上传控件，可以做区分
      :url：后台上传的url地址
      @close：关闭上传组件
      @crop-upload-success：上传成功后的自动执行的回调函数
       filed:类似于 <input type="file" name="file"/> name要和后端的接口一致！！
      -->
        <image-cropper
          v-show="imagecropperShow"
          :width="300"
          :height="300"
          :key="imagecropperKey"
          :url="BASE_API+'/edu/upload'"
          field="file"
          @close="close"
          @crop-upload-success="cropSuccess"/>
      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>
<script>
import teacherApi from '@/api/teacher'
import ImageCropper from '@/components/ImageCropper'
import PanThumb from '@/components/PanThumb'

export default {
  components: { ImageCropper, PanThumb }, // 引入组件后要声明才能使用
  data() {
    return {
      teacherObject: { avatar: '' }, // 定义一个对象来处理回显和数据传递的问题
      saveBtnDisabled: false, // 保存按钮是否禁用,
      imagecropperShow: false, // 上传弹框组件是否显示
      imagecropperKey: 0, // 上传组件key值
      BASE_API: process.env.BASE_API // 获取dev.env.js里面地址
    }
  },
  watch: { // 监听
    $route(to, from) { // 路由变化方式，路由发生变化，方法就会执行
      this.init()
    }
  },

  created() { // 页面渲染之前判断是进入修改功能还是添加功能
    this.init()
  },
  methods: {
    close() { // 关闭上传弹框的方法
      this.imagecropperShow = false
      // 上传组件初始化
      this.imagecropperKey = this.imagecropperKey + 1
    },
    // 上传成功,回调方法
    cropSuccess(data) { // 这里的data应该是和request方法里那个response类似，是一个带有返回值的参数的玩意，里面是上传图片后的url
      console.log(data)
      this.imagecropperShow = false
      // 上传之后接口返回图片地址
      this.teacherObject.avatar = data.url
      this.imagecropperKey = this.imagecropperKey + 1
    },
    init() {
      // 判断路径有id值,进入修改功能
      if (this.$route.params && this.$route.params.id) {
        // 从路径获取id值
        const id = this.$route.params.id
        console.log(id)// 获取到了数据了，但是似乎没调用到方法
        console.log(this)
        // 调用根据id查询的方法
        this.getInfo(id)
      } else { // 路径没有id值，进入添加功能
        // 清空表单
        this.teacherObject = {
        }
      }
    },

    // 根据教师id查询的方法（用来点击修改的时候，回显数据）
    getInfo(id) {
      teacherApi.getTeacherInfo(id)
        .then(response => {
          console.log(response)
          this.teacherObject = response.data.teacher
        })
    },
    saveOrUpdate() {
      // 判断是进入修改功能还是进入添加功能
      // 根据teacher是否有id(正在创建的肯定是没有ID值的（还没生成额） 所以进入添加功能)
      if (!this.teacherObject.id) {
        // 添加
        this.saveTeacher()
      } else {
        // 修改
        this.updateTeacher()
      }
    },
    // 修改教师的方法
    updateTeacher() {
      teacherApi.updateTeacherInfo(this.teacherObject)
        .then(response => {
          // 提示信息
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
          // 回到列表页面 路由跳转
          this.$router.push({ path: '/teacher/table' })
        })
    },
    // 添加教师的方法
    saveTeacher() {
      teacherApi.addTeacher(this.teacherObject)
        .then(response => { // 添加成功
          // 提示信息
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
          // 回到列表页面 路由跳转
          this.$router.push({ path: '/teacher/table' })
        })
    }

  }
}
</script>
