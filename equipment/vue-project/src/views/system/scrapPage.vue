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
                          <router-link class="link" to="/query" >设备查询</router-link>
                      </el-menu-item>
                      <el-menu-item index="2" >
                          <router-link class="link" to="/home" >设备管理</router-link>
                      </el-menu-item>
                      <el-menu-item index="3">
                          <router-link class="link" to="" >设备报废</router-link>
                      </el-menu-item>
                      <el-menu-item index="4">
                          <router-link  class="link" to="/borrow" >租借设备</router-link>
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
                    <el-submenu index="1">
                    <template slot="title"><i class="el-icon-menu"></i><span>设备管理</span></template>
                    <el-menu-item index="1-1">
                        <router-link class="link" to="/home">设备管理</router-link>
                    </el-menu-item>
                    <el-menu-item index="1-2">
                        <router-link class="link" to="/query">设备查询</router-link>
                    </el-menu-item>
                    </el-submenu>
                    <el-menu-item index="2">
                    <router-link class="link" to="/borrow"><i class="el-icon-s-platform"></i>租借设备</router-link>
                    </el-menu-item>
                    <el-menu-item index="3">
                    <router-link class="link" to="/return"><i class="el-icon-s-check"></i>归还设备</router-link>
                    </el-menu-item>
                    <el-menu-item index="4">
                    <router-link class="link" to="/statistics"><i class="el-icon-s-data"></i>统计设备</router-link>
                    </el-menu-item>
                </el-menu>
                </div>
            </el-aside>

          <el-container>
            <el-main>
            <!--   <div style="display: flex;">
                <el-input style="width:200px;margin-left:150px;" v-model="queryId" placeholder="请输入设备编号"></el-input>
                <el-button style="margin-left: 10px;" type="primary" @click="query">查询</el-button>
                <el-button style="margin-left: 400px;" type="primary" @click="dialogFormVisible = true">添加设备</el-button>
              </div> -->

                <el-dialog title="设备信息" :visible.sync="dialogFormVisible">
                  <el-form :model="newEquipment">
                    <el-form-item label="设备名称" :label-width="formLabelWidth">
                      <el-input v-model="newEquipment.equipmentName" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="设备编号" :label-width="formLabelWidth">
                      <el-input v-model="newEquipment.equipmentId" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="设备图片" :label-width="formLabelWidth">
                        <el-upload v-model="newEquipment.equipmentImage" accept=".jpg,.png" @change="handleUpload($event)">
                          <el-button type="primary" style="margin-left: 10px;  background-color: #2d9aff;">上传图片</el-button>
                        </el-upload>
                      </el-form-item>
                    <el-form-item label="所属类别" :label-width="formLabelWidth">
                      <el-input v-model="newEquipment.category" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="购置日期" :label-width="formLabelWidth">
                      <el-date-picker v-model="newEquipment.createTime" autocomplete="off"></el-date-picker>
                    </el-form-item>
                    <el-form-item label="设备描述" :label-width="formLabelWidth">
                      <el-input v-model="newEquipment.extra" autocomplete="off"></el-input>
                    </el-form-item>
                    <!-- <el-form-item label="状态" :label-width="formLabelWidth">
                      <el-select v-model="newEquipment.status" placeholder="请选择">
                        <el-option label="正常" value=0></el-option>
                        <el-option label="报废" value=1></el-option>
                        <el-option label="借出" value=2></el-option>
                      </el-select>
                    </el-form-item> -->
                  </el-form>
                  <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="addEquipment()">确 定</el-button>
                  </div>
                </el-dialog>
                <br>
                <el-table ref="tableInstance" :data="tableData" border
                style="width: 100%" >
                    <el-table-column prop="equipmentName" label="设备名称" width="150">
                        <template v-slot:item="{ item }">
                            <el-table-cell>{{ item.equipmentName }}</el-table-cell>
                        </template>
                    </el-table-column>
                    <el-table-column prop="equipmentId" label="设备编号" width="100">
                        <template v-slot:item="{ item }">
                            <el-table-cell>{{ item.equipmentId }}</el-table-cell>
                        </template>
                    </el-table-column>
                    <el-table-column prop="equipmentImage" label="设备图片" width="150">
                        <template slot-scope="scope">
                            <img :src=scope.row.equipmentImage alt="设备图片">
                        </template>
                    </el-table-column>
                    <el-table-column prop="category" label="所属类别" width="150">
                        <template v-slot:item="{ item }">
                            <el-table-cell>{{ item.category }}</el-table-cell>
                        </template>
                    </el-table-column>
                    <el-table-column prop="createTime" label="购置日期" width="150">
                        <template v-slot:item="{ item }">
                            <el-table-cell>{{ item.createTime }}</el-table-cell>
                        </template>
                    </el-table-column>
                    <el-table-column prop="extra" label="描述" width="150">
                        <template v-slot:item="{ item }">
                            <el-table-cell>{{ item.extra }}</el-table-cell>
                        </template>
                    </el-table-column>
                    <el-table-column prop="status" label="状态" width="150">
                      <template slot-scope="scope">
                         {{scope.row.status == 0 ?'正常':(scope.row.status == 1 ?'报废':'借出')}}
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
import {getEquipmentByDept,addEquipmentHttp,getEquipmentById } from '@/api'
export default {
    data() {
      const item = {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      };
        return {
          queryId: null,
          fileList: [],
          token: localStorage.getItem('token'),
          userId:localStorage.getItem('userId'),
          tableData: Array(20).fill(item),
          dialogFormVisible: false,
          newEquipment: {
            equipmentName: '',
            equipmentId: '',
            equipmentImage: '',
            category: '',
            createTime: null,
            extra: '',
            /* status: null, */
        },
        formLabelWidth: '120px'
        }
    },
    methods: {

      query(){
        getEquipmentById(this.queryId,this.token).then(res => {
                        if (res.data.code === 1) {
                          this.$message.success('添加成功');
                          this.tableData = res.data.data;
                          this.$message.success(this.tableData.equipmentName);
                          this.$nextTick(() => {
                            this.$refs.tableInstance.refresh();
                          });
                        } else if(res.data.msg == "NOT_LOGIN") {
                            this.$message.error("未登录");
                            this.$router.push('/firstpage');
                        }
                        else{
                          /* this.$message.error("添加失败"); */
                        }
                    })
      },
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
      getEquipmentByDept(this.token).then(res => {
                        if (res.data.code === 1) {
                          this.tableData = res.data.data;
                          
                        } else {
                            this.$message.error("未登录");
                            this.$router.push('/firstpage');
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