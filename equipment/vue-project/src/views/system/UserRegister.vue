<template>
    <div class="container">
      <body></body>
      <el-header style="font-size: 40px">科研设备管理系统</el-header>
        <form :model="adminForm">
            <br>
            <span class="p">*</span>
            <label for="username">用户名</label>
            <input type="text" name="" id="username" v-model="adminForm.userId" class="register"><br><br>
            <span class="q">*</span>
            <label for="pwd">登录密码</label>
            <input type="password" name="" id="pwd" v-model="adminForm.userPassword" class="register"><br><br>
            <span class="q">*</span>
            <label for="c_pwd">确认密码</label>
            <input type="password" name="" id="c_pwd" class="register"><br><br>
            <span class="q">*</span>
            <label for="kind">所属部门</label>
            <template>
                <el-select v-model="adminForm.deptId" placeholder="请选择">
                    <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                    </el-option>
                </el-select>
            </template>

            <br><br>
            <input type="submit" name="" value="注册" class="submit" @click="register()"><br>
            <router-link to="/firstpage" class="right">返回登录</router-link>
        </form>
    </div>
</template>

<script>
import { userRegister } from "@/api";
/* import http from "../../utils/request";
 */
/* import axios from 'axios';
 */
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
            userRegister(this.adminForm).then(res => {
                        if (res.data.code === 1) {
                            this.$message.success('注册成功');
                            this.$router.push('/firstpage');
                        } else {
                            this.$message.error();
                        }
                    })
        }
    },
}
</script>

<style>

body {
  background-image: url("../../assets/images/machine.jpg");
  background-repeat: no-repeat;
  background-size: 100% 150%;
  height:100%
}
.el-header
{
  /* background-color: #B3C0D1; */
  color: #000;
  text-align: center;
  line-height: 60px;
}

.container{
    margin: 100px auto;
    width: 30%;
}
form{
    width: 450px;
    margin: 0 auto;
    background: #FFF;
    border-radius: 15px;
    position: relative;
}
h1{
    font-size: 28px;
    text-align: center;
    color: #FFF;
}
.p{
    color: red;
    margin-left: 33px;
    display: inline-block;
}
label{
    font-size: 18px;
    font-weight: bold;
    margin-right:17px;
}
.register{
    height: 35px;
    width: 300px;
}
.q{
    color:red;
    margin-left:17px;
    display:inline-block;
}
.checkbox{
    margin-left: 100px;
    display: inline-block;
    width: 15px;
    height: 15px;
}
.submit{
    border-radius: 7px;
    margin-left: 150px;
    height: 35px;
    width: 150px;
    background-color: #000;
    border: none;
    display: block;
    padding: 0;
    color: #FFF;
    font-weight: bold;
    cursor: pointer;
}
a{
    text-decoration: none;
    font-weight: bold;
}
.left,.right{
    position: absolute;
    bottom: 20px;
}
.left{
    left: 20px;
}
.right{
    right: 20px;
}
el-select{
    size:medium;
}
</style>