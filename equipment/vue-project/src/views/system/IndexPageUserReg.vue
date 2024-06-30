<template>
  <div class="wthree-dot">   
    <div class="profile">
      <div class="wrap">		
        <div class="wthree-grids">
          <div class="content-main">			
            <div class="w3ls-subscribe">
			<h1>科研设备管理系统</h1>
			<br><br>
              <h4>New Customer?</h4>
              <form :model="adminForm">
                <!-- <input type="text" v-model="firstName" placeholder="First Name" required> 必填required -->
                <input type="text" name="" id="username" v-model="adminForm.userId" class="register" placeholder="Username" required> 
                <input type="password" name="" id="pwd" v-model="adminForm.userPassword" class="register" placeholder="Password" required>
                <input type="password" name="" id="c_pwd" class="register" placeholder="Confirm Password" required>
                <template>
                  <el-select v-model="adminForm.deptId" placeholder="请选择部门" >
                    <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                    </el-option>
                  </el-select>
                </template>
                <!-- <input type="submit" value="Sign Up"> -->
                <br>
                <input type="submit" name="" value="注册" class="submit" @click="register()"><br>
              </form>

              <br><br>
              <!-- <h9>Already have an account <a href="#">Sign In</a></h9> -->
              <h9 style="color: gray;">Already have an account <router-link to="/testFP" class="right" style="color: white;">Sign in</router-link></h9>
            </div>
          </div>
          <div class="clear"> </div>
        </div>
        <div class="wthree_footer_copy">
          <p>© All rights reserved | Design by DengHK YanXX NiuCY</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { userRegister } from "@/api";
export default {
    data() {
        return {
            adminForm: {
                userId: '',
                userPassword: '',
                deptId:null
            },
            options: [{
                value: 1,
                label: '实验室1'
                }, {
                value: 2,
                label: '实验室2'
                }, {
                value: 3,
                label: '实验室3'
                }, {
                value: 4,
                label: '实验室4'
                }, {
                value: 5,
                label: '实验室5'
                }],
                value: ''
        }
    },
    methods: { 
        register(){
            event.preventDefault();
            userRegister(this.adminForm).then(res => {
                        if (res.data.code === 1) {
                            this.$message.success('注册成功');
                            this.$router.replace('/testFP');
                        } else {
                            this.$message.error();
                        }
                    })
        }
    },
}
</script>

<style>
.el-select__caret {
  
  position: relative; /* 调整位置 */
  top: -8px; /* 上下调整 */
  right: 6px; /* 左右调整 */
}

</style>

<style scoped>
@import "../../assets/register/css/style.css";
/* @import "../../assets/register/css/font-awesome.css"; */

</style>

<style>
.submit{
    margin-left: 250px;   
}

.h6{
    color: azure;
}
</style>
