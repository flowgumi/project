<template>
  <el-container style="height: 300px; border: 1px solid #eee">
    <el-header style="display: flex; justify-content: space-between;">
        <!-- <span style="font-size: 40px;">智能设备管理平台</span>
        <span type="text" style="margin-right: 15px;font-size: 20px">
            欢迎{{userId}}
        </span>
        <template>
            <el-button type="text" @click="open">退出登录</el-button>
        </template> -->
        <nav class="fixed navbar w-full z-10">
                <div class="py-6 px-4 sm:px-6 lg:px-8">
                <div class="flex items-center justify-between">
                    <div class="hidden md:block" style="width: 100%;">
                    <div class="flex items-baseline justify-between">

                        <div>
                        <span class="artistic-font1">智能设备管理平台 </span>
                        <!-- <span class="artistic-font2">欢迎{{ userId }}</span> -->
                        </div>

                        <div class="flex items-center justify-center" style="flex-grow: 1;">
                            <span class="artistic-font2">Welcome {{ userId }}</span>
                        </div>

                        <template>
                        <el-button type="text" @click="open" style="font-size: 15px">退出登录</el-button>
                        </template>

                    </div>
                    </div>
                </div>
                </div>
            </nav>
    </el-header>
      <el-container>
        <!-- <el-aside width="200px">
            <div style="background-color: rgb(255, 255, 255); width: 100%; height: 100%;">
                <el-menu :default-openeds="[]">
                    <el-menu-item index="1" style="background-color: white;">
                        <router-link class="link" to="/home" >设备管理</router-link>
                    </el-menu-item>
                    <el-menu-item index="2" >
                        <router-link class="link" to="/query" >设备查询</router-link>
                    </el-menu-item>
                    <el-menu-item index="3">
                        <router-link  class="link" to="">租借设备</router-link>
                    </el-menu-item>
                    <el-menu-item index="4">
                          <router-link class="link" to="/return" >归还设备</router-link>
                    </el-menu-item>
                    <el-menu-item index="5">
                          <router-link class="link" to="/statistics" >统计设备</router-link>
                      </el-menu-item>
                </el-menu>
            </div>
        </el-aside> -->
        
        <el-aside width="200px" style="border: 1px solid #eee">
                <div style="background-color: rgb(255, 255, 255); width: 100%; height: 100%;">        
                <el-menu :default-openeds="['1']" background-color="#fff" text-color="#333" active-text-color="#409EFF">
                  <el-menu-item index="1">
                    <router-link class="link" to="/home"><i class="el-icon-menu"></i>设备管理</router-link>
                    </el-menu-item>
                    <el-menu-item index="2">
                    <router-link class="link" to="/query"><i class="el-icon-setting"></i>设备查询</router-link>
                    </el-menu-item>
                    <el-menu-item index="3">
                    <router-link class="link" to="/borrow"><i class="el-icon-s-platform"></i>租借设备</router-link>
                    </el-menu-item>
                    <el-menu-item index="4">
                    <router-link class="link" to="/return"><i class="el-icon-s-check"></i>归还设备</router-link>
                    </el-menu-item>
                    <el-menu-item index="5">
                    <router-link class="link" to="/statistics"><i class="el-icon-s-data"></i>统计设备</router-link>
                    </el-menu-item>
                </el-menu>
                </div>
            </el-aside>


        <el-container>
          <el-main>
              <br>
              <el-table :data="tableData" :key="itemKey" border style="width: 100%">
                <el-table-column  prop="equipmentName" label="设备名称" width="130px"></el-table-column>
                <el-table-column  prop="equipmentId" label="设备编号" width="100px"></el-table-column>
                <el-table-column label="设备图片" width="150">
                  <template slot-scope="scope">
                          <!-- <img :src=scope.row.equipmentImage> -->
                          <img :src="scope.row.equipmentImage" alt="设备图片" style="width: 100px; height: 100px; object-fit: cover;">
                      </template>
                </el-table-column>
                <el-table-column label="所属部门" width="150">
                  <template slot-scope="scope">
                    <!-- 根据deptId的值显示不同的实验室名称 -->
                    <span v-if="scope.row.deptId == 1">实验室1</span>
                    <span v-if="scope.row.deptId == 2">实验室2</span>
                    <span v-if="scope.row.deptId == 3">实验室3</span>
                    <span v-if="scope.row.deptId == 4">实验室4</span>
                    <span v-if="scope.row.deptId == 5">实验室5</span>
                  </template>
                </el-table-column>
                <el-table-column prop="category" label="所属类别" width="140"></el-table-column>
                <el-table-column prop="createTime" label="购置日期" width="150">
                  <template v-slot:item="{ item }">
                          <el-table-cell>{{ item.createTime }}</el-table-cell>
                      </template>
                </el-table-column>
                <el-table-column prop="extra" label="描述" width="150"></el-table-column>
                <el-table-column  label="状态" width="100">
                  <template slot-scope="scope">
                       {{scope.row.status == 0 ?'正常':(scope.row.status == 1 ?'报废':'借出')}}
                    </template>
                </el-table-column>
                <el-table-column  label="租借" width="150">
                      <template slot-scope="scope">
                        <el-button type="success" @click="borrowEquipment(scope.row.equipmentId)"  style="background-color: #95f9a3; border-color: #b6e6bc;">租借</el-button>
                      </template>
                    </el-table-column>
                </el-table>
          </el-main>
          <!-- <el-footer>Footer</el-footer> -->
        </el-container>
      </el-container>
  </el-container>
</template>
<script>
/* import { ref } from 'vue';
*/
import {addEquipmentHttp,getAllOtherEquipment,borrowEquipmentHttp } from '@/api'
export default {
  data() {
      return {
        options1: [{
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
        options: [{
            value: 0,
            label: '正常'
          }, {
            value: 1,
            label: '报废'
          }, {
            value: 2,
            label: '借出'
          }],
        itemKey:Math.random(),
        queryId: null,
        fileList: [],
        token: localStorage.getItem('token'),
        userId:localStorage.getItem('userId'),
        tableData: [],
        dialogFormVisible: false,
        queryEquipment:{
          equipmentId:"",
          deptId : "",
          category:'' ,
          status:''

        },
        newEquipment:'', /* {
          equipmentName: '',
          equipmentId: '',
          equipmentImage: '',
          category: '',
          createTime: null,
          extra: '',
          status: null,
      } */
      formLabelWidth: '120px'
      }
  },
  methods: {

    /* query(){
          getEquipmentByAll(this.queryEquipment,this.token).then(res => {
              if (res.data.code === 1) {
                this.tableData = [res.data.data];
                this.itemKey = Math.random();
                this.$forceUpdate();
              } else if(res.data.msg == "NOT_LOGIN") {
                  this.$message.error("未登录");
                  this.$router.push('/firstpage');
              }
              else{
                this.$message.error("未找到对应设备");
              }
          })
    }, */
    addEquipment(){
      this.dialogFormVisible = false;
      addEquipmentHttp(this.newEquipment,this.token).then(res => {
                      if (res.data.code === 1) {
                        this.$message.success('添加成功');
                      } else if(res.data.msg == "NOT_LOGIN") {
                          this.$message.error("未登录");
                          this.$router.push('/firstpage');
                      }
                      else{
                        this.$message.error("添加失败");
                      }
                  })
    },
    borrowEquipment(borrowId){
      this.$confirm('确认申请该设备吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          borrowEquipmentHttp(borrowId,this.token).then(res => {
                        if (res.data.code === 1) {
                          this.$message.success('成功发送请求');
                        } else if(res.data.msg == "NOT_LOGIN") {
                            this.$message.error("未登录");
                            this.$router.push('/firstpage');
                        }
                        else{
                          this.$message.error("发送申请失败");
                        }
                    })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          });          
        });
        
      },
    handleUpload(event) {
      const file = event.detail.files[0];
      const reader = new FileReader();
      reader.onload = (e) => {
        this.newEquipment.equipmentImage = e.target.result;
      };
      reader.readAsDataURL(file);
    },
    open() {
      this.$confirm('确认退出吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$router.push('/testFP');    // /fisrtpage
        this.$message({
          type: 'success',
          message: '退出成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });          
      });
    }
  },
  mounted() {
    getAllOtherEquipment(this.token).then(res => {
                      if (res.data.code === 1) {
                        this.tableData = res.data.data;
                        
                      } else if(res.data.msg == "NOT_LOGIN") {
                          this.$message.error("未登录");
                          this.$router.push('/firstpage');
                      }else{
                        this.$message.error("获取数据失败");
                      }
                  })
    
  },
}
</script>

<style scoped>
@import 'https://cdn.bootcdn.net/ajax/libs/Swiper/9.4.1/swiper-bundle.min.css';
@import 'https://fonts.googleapis.com/css2?family=Jaldi&family=Merriweather:wght@300;400;700&display=swap';
@import '../../assets/amanda/assets/index-ix7uzjuy.css';
.flex.items-baseline {
  margin-top: -25px; /* 根据需要调整数值 */
}

@import url('https://fonts.googleapis.com/css2?family=Pacifico&display=swap');

.artistic-font1 {
    font-family: '', cursive;
    font-size: 40px;
    color: #080808; /* Customize the color */
}
.artistic-font2 {
    font-family: 'Pacifico', cursive;
    font-size: 40px;
    color: #080808; /* Customize the color */
}


</style>

<style>
body {
  background-image: url("../../assets/images/leaf-img-pattern-left.png");
  /* background-repeat: no-repeat;
  background-size: 100% 130%;
  height:100% */
}

.el-header {
  background-color: #deeff0;
  color: #333333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
.link{
color: black; 
text-decoration: none;
}
el-menu-item{
background-color: white;
}
</style>