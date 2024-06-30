<template>
    <div class="container">
      <body></body>
      <el-header style="font-size: 40px">科研设备管理系统</el-header>
        <form :model="adminForm"  onsubmit="return false;" action="" method="">
          <br>
          <h2>用户登录</h2>
            <label for="userId">用户名</label><br>
            <input type="text" name="userId" id="userId" class="input" value="" v-model="adminForm.userId"><br>
            <label for="pwd">密码</label><br>
            <input type="password" name="userPassword" id="pwd" class="input" value="" v-model="adminForm.userPassword">
            <span class="checkbox">
                <input type="checkbox" name="">
            </span>
            &nbsp;
                <span>记住密码</span> 
            <br>
            <button type="submit" class="submit" @click="submits()">登录</button>
            <br>
          <a class="right" href="#/register">注册账号</a>
        </form>
  </div>
</template>

<script>
/* import axios from 'axios';*/
import { userLogin } from "@/api";

export default {
  data() {
    return {
      adminForm: {
                userId: '',
                userPassword: '',
        },
      
    }
  },
  methods:{
    submits(){
        userLogin(this.adminForm).then(res => {
                  this.$message.success("登录成功");
                    if (res.data.code === 1) {
                      const token = res.data.data;
                      const userId = this.adminForm.userId;
                      localStorage.setItem('userId', userId);
                      localStorage.setItem('token', token);
                      this.$router.push('/home');
                    } else {
                        this.$message.error();
                    }
                })
      }
  }
};

</script>

<style>
body {
  background-image: url("../../assets/images/1.jpg");
  background-repeat: no-repeat;
  background-size: 100% 130%;
  height:100%
}
.el-header {
  /* background-color: #B3C0D1; */
  color: #fff2f2;
  text-align: center;
  line-height: 60px;
}

/* body{
    background: #006666;
} */
h2{
    color: #000;
    text-align: center;
}
.container{
    margin: 100px auto;
    width: 30%;
}
form{
    background: #FFF;
    height: 400px;
    width: 100%;
    border-radius: 10px;
    position: relative;
}
label{
    color: #000;
    font-weight: bold;
    font-size: 20px;
    margin-left: 40px;
}
input{
    text-align: left;
    margin: 10px;
}
span{
  text-align: left;
}
.input{
    width: 80%;
    height: 35px;
    margin-left: 40px;
}
.checkbox{
    margin-left: 30px;
}
a{
    text-decoration: none;
    font-weight: bold;
}
.submit{
    display: inline-block;
    margin-top: 0;
    margin-left:170px;
    background: #000;
    border: none;
    color: #FFF;
    height: 45px;
    width: 100px;
    text-align: center;
    font-weight: bold;
    border-radius: 5px;
}
.left{
    margin: 20px;
}
.right{
    position: absolute;
    right: 20px;
}
</style>