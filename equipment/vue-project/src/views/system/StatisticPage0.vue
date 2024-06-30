<template>
    <el-container style="height: 300px; border: 1px solid #eee">
      <el-header style="display: flex; justify-content: space-between;">
          <span style="font-size: 40px;">智能设备管理平台</span>
          <span type="text" style="margin-right: 15px;font-size: 20px">
              欢迎{{userId}}
          </span>
          <template>
              <el-button type="text" @click="open">退出登录</el-button>
          </template>
      </el-header>
        <el-container>
          <el-aside width="200px">
              <div style="background-color: rgb(255, 255, 255); width: 100%; height: 100%;">
                  <el-menu :default-openeds="[]">
                      <el-menu-item index="1" style="background-color: white;">
                          <router-link class="link" to="/home" >设备管理</router-link>
                      </el-menu-item>
                      <el-menu-item index="2" >
                          <router-link class="link" to="" >设备查询</router-link>
                      </el-menu-item>
                      <el-menu-item index="3">
                          <router-link  class="link" to="/borrow" >租借设备</router-link>
                      </el-menu-item>
                      <el-menu-item index="4">
                          <router-link class="link" to="/return" >归还设备</router-link>
                      </el-menu-item>
                  </el-menu>
              </div>
          </el-aside>

          <el-container>
            <el-main>
              <div style="display: flex;">
                <span style="font-size: 20px;">查询学校设备</span>
                <el-input style="width:200px;margin-left:10px;" v-model="queryEquipment.category" placeholder="请输入设备种类" clearable></el-input>
                <el-input style="width:200px;margin-left:10px;" v-model="queryEquipment.equipmentId" placeholder="请输入设备编号" clearable></el-input>
                <el-select style="margin-left:10px" v-model="queryEquipment.deptId" placeholder="请选择部门" clearable>
                  <el-option
                    v-for="item in options1"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>

                <el-select style="margin-left:10px" v-model="queryEquipment.status" placeholder="请选择设备状态" clearable>
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
                <el-button style="margin-left: 10px;" type="primary" @click="query">查询</el-button>
             <!--    <el-button style="margin-left: 400px;" type="primary" @click="dialogFormVisible = true">添加设备</el-button> -->
              </div>

                <br>
                <el-table :data="tableData" :key="itemKey" border style="width: 100%">
                  <el-table-column  prop="equipmentName" label="设备名称" width="130"></el-table-column>
                  <el-table-column  prop="equipmentId" label="设备编号" width="150"></el-table-column>
                  <el-table-column label="设备图片" width="140">
                    <template slot-scope="scope">
                            <!-- <img :src=scope.row.equipmentImage> -->
                            <img :src="scope.row.equipmentImage" alt="设备图片" style="width: 100px; height: 100px; object-fit: cover;">
                        </template>
                  </el-table-column>
                  <el-table-column prop="category" label="所属类别" width="140"></el-table-column>
                  <el-table-column prop="createTime" label="购置日期" width="180">
                    <template v-slot:item="{ item }">
                            <el-table-cell>{{ item.createTime }}</el-table-cell>
                        </template>
                  </el-table-column>
                  <el-table-column prop="extra" label="描述" width="150"></el-table-column>
                  <el-table-column  label="状态" width="150">
                    <template slot-scope="scope">
                         {{scope.row.status == 0 ?'正常':(scope.row.status == 1 ?'报废':'借出')}}
                      </template>
                  </el-table-column>
                

                  </el-table>
            </el-main>
            <!-- <el-footer>Footer</el-footer> -->
          </el-container>          
        </el-container>
    <el-container>
        <el-table :data="deptEquipmentCounts" style="width: 100%">
                <el-table-column prop="deptId" label="部门ID" width="180"></el-table-column>
                <el-table-column prop="count" label="设备数量" width="100"></el-table-column>
            </el-table>
            <el-table :data="categoryCounts" style="width: 100%">
                <el-table-column prop="category" label="设备类别" width="180"></el-table-column>
                <el-table-column prop="count" label="数量" width="100"></el-table-column>
            </el-table>
            <el-table :data="statusCounts" style="width: 100%">
                <el-table-column prop="status" label="设备状态" width="180"></el-table-column>
                <el-table-column prop="count" label="数量" width="100"></el-table-column>
            </el-table>
    </el-container>  
    </el-container>
</template>
<script>
/* import { ref } from 'vue';
 */


import axios from 'axios';
import {getEquipmentByAll} from '@/api'  //, getAllEquipment
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
        newEquipment:'', 
        formLabelWidth: '120px',

        deptEquipmentCounts: [],
        categoryCounts: [],
        statusCounts: [],

        }
           
    },

    methods: {

      query(){
            getEquipmentByAll(this.queryEquipment,this.token).then(res => {
                if (res.data.code === 1) {
                  this.tableData = res.data.data;
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
        },
    // 其他方法保持不变
    calculateStatistics() {
        this.calculateDeptEquipmentCounts();
        this.calculateCategoryCounts();
        this.calculateStatusCounts();
    },
    calculateDeptEquipmentCounts() {
        const deptCount = {};
        this.tableData.forEach(item => {
            if (!deptCount[item.deptId]) {
                deptCount[item.deptId] = 0;
            }
            deptCount[item.deptId]++;
        });
        this.deptEquipmentCounts = Object.keys(deptCount).map(key => ({
            deptId: key,
            count: deptCount[key]
        }));
    },
    calculateCategoryCounts() {
        const categoryCount = {};
        this.tableData.forEach(item => {
            if (!categoryCount[item.category]) {
                categoryCount[item.category] = 0;
            }
            categoryCount[item.category]++;
        });
        this.categoryCounts = Object.keys(categoryCount).map(key => ({
            category: key,
            count: categoryCount[key]
        }));
    },
    calculateStatusCounts() {
        const statusCount = {};
        this.tableData.forEach(item => {
            const statusLabel = this.options.find(option => option.value === item.status)?.label || '未知';
            if (!statusCount[statusLabel]) {
                statusCount[statusLabel] = 0;
            }
            statusCount[statusLabel]++;
        });
        this.statusCounts = Object.keys(statusCount).map(key => ({
            status: key,
            count: statusCount[key]
        }));

    // 其他方法保持不变
        },

    },
    mounted() {
    //   getAllEquipment(this.token).then(res => {
    //                     if (res.data.code === 1) {
    //                       this.tableData = res.data.data;
                          
    //                     } else if(res.data.msg == "NOT_LOGIN") {
    //                         this.$message.error("未登录");
    //                         this.$router.push('/firstpage');
    //                     }else{
    //                       this.$message.error("获取数据失败");
    //                     }
    //                 })

       axios.get("https://yapi.pro/mock/433129/equipment/dept/search").then(result => {
        this.tableData = result.data;
        this.calculateStatistics();  // 计算统计数据
    })
    },
}
</script>
<style>

.el-header {
    background-color: #0ae6f6;
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