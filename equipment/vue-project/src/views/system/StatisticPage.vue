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

            <!-- <el-aside width="200px" style="border: 1px solid #eee">
                <div style="background-color: rgb(255, 255, 255); width: 100%; height: 100%;">
                    <el-menu :default-openeds="[]">
                        <el-menu-item index="1" style="background-color: white;">
                            <router-link class="link" to="/home" >设备管理</router-link>
                        </el-menu-item>
                        <el-menu-item index="2" >
                            <router-link class="link" to="/query" >设备查询</router-link>
                        </el-menu-item>
                        <el-menu-item index="3">
                            <router-link  class="link" to="/borrow" >租借设备</router-link>
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

            <!-- <el-aside width="250px" style="border: 1px solid #eee;">
                <div style="background-color: #f9f9f9; width: 100%; height: 100%; "> -->
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

                    <!-- <el-table :data="pagedTableData" :key="itemKey" border style="width: 100%">
                    <el-table-column  prop="equipmentName" label="设备名称" width="130"></el-table-column>
                    <el-table-column  prop="equipmentId" label="设备编号" width="150"></el-table-column>
                    <el-table-column label="设备图片" width="140">
                        <template slot-scope="scope">
                                
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
                    </el-table> -->
                    <!-- <br>
                    <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page="currentPage"
                        :page-sizes="[5, 10, 20, 50]"
                        :page-size="pageSize"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="tableData.length">
                    </el-pagination>   -->          
                
                <hr>
                <br>

                <el-container>             
                <!-- <el-table :data="deptEquipmentCounts" style="width: 100%">
                    <el-table-column prop="deptId" label="部门ID" width="180"></el-table-column>
                    <el-table-column prop="count" label="设备数量" width="100"></el-table-column>
                </el-table> -->
                <el-table :data="categoryCounts" style="width: 100%">
                    <el-table-column prop="category" label="设备类别" width="180"></el-table-column>
                    <el-table-column prop="count" label="数量" width="100"></el-table-column>
                </el-table>
                <el-table :data="statusCounts" style="width: 100%">
                    <el-table-column prop="status" label="设备状态" width="180"></el-table-column>
                    <el-table-column prop="count" label="数量" width="100"></el-table-column>
                </el-table>
                </el-container>
                

            <br><br>

                <el-container>
                    <!-- <ve-pie :data="deptChartData" width="400px" height="300px" /> -->
                    <ve-bar :data="categoryChartData" width="400px" height="300px" />
                    <ve-pie :data="statusChartData" width="400px" height="300px" />
                </el-container>

                <br><br>
                <hr>

                <el-container>
                    <el-main>
                        <el-table :data="categoryStatusCounts" style="width: 100%">
                            <el-table-column prop="类别" label="设备类别" width="180"></el-table-column>
                            <el-table-column prop="正常" label="正常" width="100"></el-table-column>
                            <el-table-column prop="报废" label="报废" width="100"></el-table-column>
                            <el-table-column prop="借出" label="借出" width="100"></el-table-column>
                        </el-table>

                        <br><br>
                        <ve-bar :data="categoryStatusChartData" />
                    </el-main>
                </el-container>

                </el-main>
            </el-container>

        </el-container>  

    </el-container>

</template>




<script>
import VePie from 'v-charts/lib/pie.common'
import VeBar from 'v-charts/lib/bar.common'
//import axios from 'axios'

import {getEquipmentByAll, getEquipmentByDept} from '@/api'  //
export default {
    components: {
          VePie,
          VeBar
      },

    data() {
        return {
          categoryStatusCounts: [], // 新增统计数据
          categoryStatusChartData: { // 用于条形图的数据
              columns: ['类别', '正常', '报废', '借出'],
              rows: []
            },

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
        
         deptChartData: {
            columns: ['部门', '数量'],
            rows: []
        },
        categoryChartData: {
            columns: ['类别', '数量'],
            rows: []
        },
        statusChartData: {
            columns: ['状态', '数量'],
            rows: []
        },
        
        currentPage: 1, // 当前页数，默认为第一页
        pageSize: 5, // 每页显示条数
        }
           
    },


    computed: {
        // 计算当前页的数据
        pagedTableData() {
          const start = (this.currentPage - 1) * this.pageSize;
          const end = start + this.pageSize;
          return this.tableData.slice(start, end);
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
    
    open() {
        this.$confirm('确认退出吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$router.push('/testFP');
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
      },


    // 其他方法保持不变
    calculateStatistics() {
        this.calculateDeptEquipmentCounts();
        this.calculateCategoryCounts();
        this.calculateStatusCounts();
        this.calculateCategoryStatusCounts(); // 新增统计函数调用
        this.updateChartData(); // 更新图表数据
    },

    calculateCategoryStatusCounts() {
            let map = {};
            this.tableData.forEach(item => {
                if (!map[item.category]) {
                    map[item.category] = { 正常: 0, 报废: 0, 借出: 0 };
                }
                if (item.status === 0) map[item.category].正常++;
                else if (item.status === 1) map[item.category].报废++;
                else if (item.status === 2) map[item.category].借出++;
            });
            this.categoryStatusCounts = Object.keys(map).map(key => ({
                类别: key,
                正常: map[key].正常,
                报废: map[key].报废,
                借出: map[key].借出
            }));
            this.categoryStatusChartData.rows = this.categoryStatusCounts; // 更新条形图数据
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


    updateChartData() {
        this.deptChartData = {
            columns: ['部门', '数量'],
            rows: this.deptEquipmentCounts.map(d => ({ 部门: d.deptId, 数量: d.count }))
        };
        this.categoryChartData = {
            columns: ['类别', '数量'],
            rows: this.categoryCounts.map(c => ({ 类别: c.category, 数量: c.count }))
        };
        this.statusChartData = {
            columns: ['状态', '数量'],
            rows: this.statusCounts.map(s => ({ 状态: s.status, 数量: s.count }))
        };
    },


    handleSizeChange(newSize) {
      this.pageSize = newSize;
    },
    handleCurrentChange(newPage) {
      this.currentPage = newPage;
    },
  },

   

    mounted() {
      /* this.tableData = [{"equipmentId":1,"equipmentName":"设备一","equipmentImage":"","extra":"详情描述","deptId":"1","createTime":"2024-06-15T12:00:00","status":0,"category":"电脑"},{"equipmentId":2,"equipmentName":"设备二","equipmentImage":"","extra":"需检修","deptId":"3","createTime":"2024-06-16T08:30:00","status":1,"category":"打印机"},{"equipmentId":3,"equipmentName":"设备三","equipmentImage":"","extra":"良好","deptId":"2","createTime":"2024-06-17T09:45:00","status":2,"category":"扫描仪"},{"equipmentId":4,"equipmentName":"设备四","equipmentImage":"","extra":"近乎全新","deptId":"4","createTime":"2024-06-18T10:15:00","status":0,"category":"电脑"},{"equipmentId":5,"equipmentName":"设备五","equipmentImage":"","extra":"老旧","deptId":"1","createTime":"2024-06-19T11:20:00","status":1,"category":"打印机"},{"equipmentId":6,"equipmentName":"设备六","equipmentImage":"","extra":"使用频繁","deptId":"3","createTime":"2024-06-20T12:00:00","status":0,"category":"扫描仪"},{"equipmentId":7,"equipmentName":"设备七","equipmentImage":"","extra":"需维护","deptId":"2","createTime":"2024-06-21T14:35:00","status":2,"category":"投影仪"},{"equipmentId":8,"equipmentName":"设备八","equipmentImage":"","extra":"准备报废","deptId":"4","createTime":"2024-06-22T16:45:00","status":1,"category":"电脑"},{"equipmentId":9,"equipmentName":"设备九","equipmentImage":"","extra":"性能优越","deptId":"1","createTime":"2024-06-23T17:00:00","status":0,"category":"投影仪"},{"equipmentId":10,"equipmentName":"设备十","equipmentImage":"","extra":"需更新软件","deptId":"3","createTime":"2024-06-24T18:10:00","status":2,"category":"电脑"},{"equipmentId":11,"equipmentName":"设备十一","equipmentImage":"","extra":"正常运行","deptId":"2","createTime":"2024-06-25T09:30:00","status":0,"category":"电脑"},{"equipmentId":12,"equipmentName":"设备十二","equipmentImage":"","extra":"待检修","deptId":"1","createTime":"2024-06-26T08:45:00","status":1,"category":"打印机"},{"equipmentId":13,"equipmentName":"设备十三","equipmentImage":"","extra":"维护中","deptId":"3","createTime":"2024-06-27T10:00:00","status":2,"category":"扫描仪"},{"equipmentId":14,"equipmentName":"设备十四","equipmentImage":"","extra":"新设备","deptId":"4","createTime":"2024-06-28T11:15:00","status":0,"category":"显微镜"},{"equipmentId":15,"equipmentName":"设备十五","equipmentImage":"","extra":"使用寿命长","deptId":"2","createTime":"2024-06-29T12:30:00","status":1,"category":"投影仪"},{"equipmentId":16,"equipmentName":"设备十六","equipmentImage":"","extra":"需要更新驱动","deptId":"1","createTime":"2024-06-30T14:00:00","status":0,"category":"打印机"},{"equipmentId":17,"equipmentName":"设备十七","equipmentImage":"","extra":"维修中","deptId":"3","createTime":"2024-07-01T15:20:00","status":2,"category":"扫描仪"},{"equipmentId":18,"equipmentName":"设备十八","equipmentImage":"","extra":"近乎报废","deptId":"4","createTime":"2024-07-02T16:40:00","status":1,"category":"电脑"},{"equipmentId":19,"equipmentName":"设备十九","equipmentImage":"","extra":"性能稳定","deptId":"2","createTime":"2024-07-03T17:45:00","status":0,"category":"投影仪"},{"equipmentId":20,"equipmentName":"设备二十","equipmentImage":"","extra":"老旧设备","deptId":"1","createTime":"2024-07-04T18:50:00","status":2,"category":"电脑"},{"equipmentId":21,"equipmentName":"设备二十一","equipmentImage":"","extra":"待报废","deptId":"3","createTime":"2024-07-05T19:15:00","status":1,"category":"打印机"},{"equipmentId":22,"equipmentName":"设备二十二","equipmentImage":"","extra":"需更换零件","deptId":"2","createTime":"2024-07-06T20:30:00","status":0,"category":"扫描仪"},{"equipmentId":23,"equipmentName":"设备二十三","equipmentImage":"","extra":"维护良好","deptId":"4","createTime":"2024-07-07T21:45:00","status":2,"category":"电脑"},{"equipmentId":24,"equipmentName":"设备二十四","equipmentImage":"","extra":"待更新软件","deptId":"1","createTime":"2024-07-08T22:00:00","status":0,"category":"投影仪"},{"equipmentId":25,"equipmentName":"设备二十五","equipmentImage":"","extra":"需更换墨盒","deptId":"3","createTime":"2024-07-09T23:10:00","status":1,"category":"打印机"},{"equipmentId":26,"equipmentName":"设备二十六","equipmentImage":"","extra":"待维修","deptId":"2","createTime":"2024-07-10T08:30:00","status":2,"category":"扫描仪"},{"equipmentId":27,"equipmentName":"设备二十七","equipmentImage":"","extra":"性能优秀","deptId":"1","createTime":"2024-07-11T09:45:00","status":0,"category":"电脑"},{"equipmentId":28,"equipmentName":"设备二十八","equipmentImage":"","extra":"设备新购","deptId":"4","createTime":"2024-07-12T10:55:00","status":1,"category":"投影仪"},{"equipmentId":29,"equipmentName":"设备二十九","equipmentImage":"","extra":"待更换部件","deptId":"2","createTime":"2024-07-13T12:00:00","status":0,"category":"电脑"},{"equipmentId":30,"equipmentName":"设备三十","equipmentImage":"","extra":"设备维修中","deptId":"3","createTime":"2024-07-14T13:15:00","status":2,"category":"打印机"}]
      this.calculateStatistics();


      getAllEquipment(this.token).then(res => {
                        if (res.data.code === 1) {
                          this.tableData = res.data.data;
                          this.calculateStatistics();  // 计算统计数据
                        } else if(res.data.msg == "NOT_LOGIN") {
                            this.$message.error("未登录");
                            this.$router.push('/firstpage');
                        }else{
                          this.$message.error("获取数据失败");
                        }
                    }) */
        getEquipmentByDept(this.token).then(res => {
            if (res.data.code === 1) {
                this.tableData = res.data.data;
                this.calculateStatistics();  // 计算统计数据
                this.$forceUpdate();
            } else if(res.data.msg == "NOT_LOGIN") {
                this.$message.error("未登录");
                this.$router.push('/firstpage');
            }
            else{
                this.$message.error("获取数据失败");
            }
        })

    //   axios.get("https://yapi.pro/mock/433129/equipment/dept/search").then(result => {
    //     this.tableData = result.data;
    //     this.calculateStatistics();  // 计算统计数据
    // })
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