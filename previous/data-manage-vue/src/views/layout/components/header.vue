<template>
  <div class="header">
    <hamburger class="hamburger-container" :toggleClick="toggleSideBar" :isActive="sidebar.opened"></hamburger>
    <span class="title">简洁软件数据管理平台</span>
    <div class="right-menu">
      <span class="employee-info"><span v-if="organizationName">组织：{{organizationName}}</span>&nbsp;&nbsp;<span v-if="realName">姓名：{{realName}}</span>&nbsp;&nbsp;</span>
      <el-dropdown class="avatar-container right-menu-item" trigger="click">
        <div class="avatar-wrapper">设置<i class="el-icon-caret-bottom"></i> </div>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item divided>
            <span @click="handleUpdatePassWord" style="display:block;">修改密码</span>
          </el-dropdown-item>
          <el-dropdown-item divided>
            <span @click="logout" style="display:block;">退出</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <el-dialog title="修改密码" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="passWordChangeFormData" :rules="rules" ref="passWordChangeForm" label-width="90px"  style='width: 90%; margin-left:15px;' label-position="right">
        <el-form-item label="原密码" prop="oldPassWord">
          <el-input type="password"  v-model="passWordChangeFormData.oldPassWord" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPassWord">
          <el-input type="password" v-model="passWordChangeFormData.newPassWord" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="新密码确认" prop="checkNewPassWord">
          <el-input type="password" v-model="passWordChangeFormData.checkNewPassWord" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import Hamburger from '@/components/Hamburger'
  import {editEmployeePass} from '@/api/employee'
  export default {
    components: {
      Hamburger
    },
    data(){
      let validateOldPassWord = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入原始密码'));
        } else {
          if (value!=this.$store.state.user.passWord) {
            callback(new Error('原始密码输入不正确'));
          }
          callback();
        }
      }
      let validateNewPassWord = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入新密码'));
        } else {
          if (this.passWordChangeFormData.checkNewPassWord !== '') {
            this.$refs.passWordChangeForm.validateField('checkNewPassWord');
          }
          callback();
        }
      }
      let validateCheckNewPassWord = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.passWordChangeFormData.newPassWord) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      }
      return{
        realName: this.$store.state.user.realName,
        organizationName:this.$store.state.user.organizationName,
        dialogFormVisible:false,
        passWordChangeFormData: {
          oldPassWord:'',
          newPassWord: '',
          checkNewPassWord: ''
        },
        rules: {
          oldPassWord: [{trigger: 'blur',validator: validateOldPassWord}],
          newPassWord: [{ trigger: 'blur',validator: validateNewPassWord}],
          checkNewPassWord: [{trigger: 'blur',validator: validateCheckNewPassWord}],

        }
      }
    },
    computed: {
      ...mapGetters([
        'sidebar'
      ])
    },
    methods: {
      toggleSideBar() {
        this.$store.dispatch('toggleSideBar')
      },
      handleUpdatePassWord(){
        this.passWordChangeFormData.oldPassWord=''
        this.passWordChangeFormData.newPassWord=''
        this.checkNewPassWord=''
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['passWordChangeForm'].clearValidate()
        })
      },
      submitForm() {
        this.$refs['passWordChangeForm'].validate((valid) => {
          if (valid) {
            editEmployeePass({id:this.$store.state.user.token,password:this.passWordChangeFormData.newPassWord}).then(()=>{
              this.dialogFormVisible = false
              this.$message( {
                message: '修改成功',
                type: 'success'
              });
              Promise.all([this.$store.dispatch('LogOut'),this.$store.dispatch('removeRouters'),this.$store.dispatch('delAllViews')]).then(()=>{
                this.$router.push({ path: '/login' })
              })
            }).catch(()=>{
              this.$message( {
                message: '修改失败',
                type: 'error'
              });
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      logout() {
        this.$confirm('确认退出?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          Promise.all([this.$store.dispatch('LogOut'),this.$store.dispatch('removeRouters'),this.$store.dispatch('delAllViews')]).then(()=>{
            this.$router.push({ path: '/login' })
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消退出'
          });
        });
      }
    }

  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .header {
    width: 100%;
    height: 60px;
    line-height: 60px;
    background-color:#2a579a;
    .hamburger-container {
      float: left;
      padding-top:5px;
      padding-left: 10px;
    }
    .title{
      margin-left: 5px;
      font-size: 23px;
      color: white;
    }
    .right-menu {
      font-size: 15px;
      float: right;
      height: 100%;
      line-height:100%;
      .employee-info{
        color: white;
      }
      &:focus{
        outline: none;
      }
      .right-menu-item {
        display: inline-block;
        margin: 0 8px;
      }
      .screenfull {
        height: 20px;
      }
      .international{
        vertical-align: top;
      }
      .theme-switch {
        vertical-align: 15px;
      }
      .avatar-container {
        margin-top: 25px;
        line-height: 23px;
        margin-right: 10px;
        color: white;
        .avatar-wrapper {
          cursor: pointer;
          .el-icon-caret-bottom {
            color: white;
            right: -15px;

          }
        }
      }
    }
  }
</style>
