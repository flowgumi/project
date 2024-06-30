<template>
  <div class="wthree-dot">   
    <div class="profile">
      <div class="wrap">		
        <div class="wthree-grids">
          <div class="content-main">			
            <div class="w3ls-subscribe">
			<h1>科研设备管理系统</h1>
			<br><br>
              <h4>用户登陆</h4>
              <form :model="adminForm"  onsubmit="return false;" action="" method="">
                <input type="text" name="userId" id="userId" class="input" value="" v-model="adminForm.userId" placeholder="Username" required>
                <input type="password" name="userPassword" id="pwd" class="input" value="" v-model="adminForm.userPassword" placeholder="Password" required>
                
                <span class="checkbox">
                <input type="checkbox" name="">
                </span>
                &nbsp;
                    <span style="color: azure;">记住密码</span> 
                <br>
                <!-- <button type="submit" class="submit" @click="submits()">登录</button> -->
                <input type="submit" name="" value="Log in" class="submit" @click="submits()"><br>
                <br>
              <a class="right" href="#/testUR" style="color: white;">注册账号</a>
              </form>

				<!-- <form :model="adminForm">
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
				</form> -->


              <br><br>
              
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
// export default {
//   name: 'SignupForm',
//   data() {
//     return {
//       firstName: '',
//       lastName: '',
//       email: '',
//       password: '',
//       confirmPassword: ''
//     };
//   },
//   methods: {
//     submitForm() {
//       // 实现表单提交的逻辑
//       console.log("Form submitted:", this.firstName, this.lastName, this.email, this.password, this.confirmPassword);
//     }
//   }
// }
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


<style scoped>
@import "../../assets/register/css/style.css";
/* @import "../../assets/register/css/font-awesome.css"; */

</style>

<style>
.submit{
    margin-left: 200px;   
}

.h6{
    color: azure;
}
</style>
