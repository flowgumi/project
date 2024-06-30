<template>
    <el-container style="height: 300px; border: 1px solid #eee">
      <!-- <el-header style="display: flex; justify-content: space-between;">
          <span style="font-size: 40px;">智能设备管理平台</span>
          <span type="text" style="margin-right: 15px;font-size: 20px">
              欢迎{{userId}}
          </span>
          <template>
              <el-button type="text" @click="open">退出登录</el-button>
          </template>
      </el-header> -->

        <el-header style="display: flex; justify-content: space-between;">
            <nav class="fixed navbar w-full z-10">
                <div class="py-6 px-4 sm:px-6 lg:px-8">
                <div class="flex items-center justify-between">
                    <div class="hidden md:block" style="width: 100%;">
                    <div class="flex items-baseline justify-between">

                        <div>
                        <span class="artistic-font1">智能设备管理平台 </span>
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
                          <router-link class="link" to="" >设备管理</router-link>
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
              <div style="display: flex;">
                <span style="width:150px ;font-size: 20px;">查询部门设备</span>
                <el-input style="width:200px;margin-left:10px;" v-model="queryEquipment.category" placeholder="请输入设备种类" clearable></el-input>
                <el-input style="width:200px;margin-left:10px;" v-model="queryEquipment.equipmentId" placeholder="请输入设备编号" clearable></el-input>
                <el-select style="margin-left:10px" v-model="queryEquipment.status" placeholder="请选择设备状态" clearable>
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
                <el-button style="margin-left: 10px;  background-color: #2d9aff;" type="primary" @click="query" >查询</el-button>
                <el-button style="margin-left: 20px; background-color: #2d9aff;" type="primary" @click="dialogFormVisible = true">添加设备</el-button>
              </div>
              <el-dialog title="审批设备" :visible.sync="outDataFormVisible">
                <el-table :data="outData" :key="itemKey" border style="width: 100%">
                  <el-table-column  prop="equipmentName" label="设备名称" width="100"></el-table-column>
                  <el-table-column  prop="equipmentId" label="设备编号" width="100"></el-table-column>
                  <el-table-column prop="userId" label="申请人ID" width="100"></el-table-column>
                  <el-table-column  label="申请部门" width="100">
                    <template slot-scope="scope">
                         {{dept[Number(scope.row.deptId)-1]}}
                      </template>
                  </el-table-column>
                  <el-table-column label="操作" width="200">
                    <template slot-scope="scope">
                        <el-button type="primary" @click="outEquipment(scope.row.equipmentId)" size="mini" style="background-color: #2d9aff;">同意</el-button>
                        <el-button type="danger" size="mini" style="background-color: #ff6f6f; border-color: #ff0000;">拒绝</el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </el-dialog>

                <!-- <el-dialog title="设备信息" :visible.sync="dialogFormVisible" >
                  <el-form :model="newEquipment">
                    <el-form-item label="设备名称" :label-width="formLabelWidth">
                      <el-input v-model="newEquipment.equipmentName" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="设备编号" :label-width="formLabelWidth">
                      <el-input v-model="newEquipment.equipmentId" autocomplete="off"></el-input>
                    </el-form-item>
                  <el-form-item label="设备图片" :label-width="formLabelWidth">    
                    <el-upload
                        class="upload-demo"
                        :action="''"             可以留空因为我们将在事件处理中调用API
                        :before-upload="beforeUpload"
                        :on-success="handleSuccess"
                        :on-error="handleError"
                        :http-request="uploadImage"  使用http-request来覆盖默认的上传行为
                      >
                        <el-button type="primary" style="margin-left: 10px;  background-color: #2d9aff;">点击上传图片</el-button>
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
                  </el-form>
                  <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="addEquipment()" style="margin-left: 10px;  background-color: #2d9aff;">确 定</el-button>
                  </div>
                </el-dialog> -->


                <transition name="fade">
                  <el-dialog
                    title="设备信息"
                    :visible.sync="dialogFormVisible"
                    width="45%"   height="80%"
                    :before-close="handleClose"  
                    custom-class="custom-dialog"  
                  >
                  <template #title>
                    <div class="custom-dialog-title">设备信息</div>  <!-- 自定义标题样式 -->
                  </template>
                    <el-form :model="newEquipment" label-position="right" label-width="120px">
                          <el-form-item class="custom-form-item" label="设备名称">
                            <el-input v-model="newEquipment.equipmentName" autocomplete="off"></el-input>
                          </el-form-item>
                          <el-form-item class="custom-form-item" label="设备编号">
                            <el-input v-model="newEquipment.equipmentId" autocomplete="off"></el-input>
                          </el-form-item>
                          <el-form-item class="custom-form-item" label="设备图片">
                            <el-upload
                                class="upload-demo"
                                :action="''"
                                :before-upload="beforeUpload"
                                :on-success="handleSuccess"
                                :on-error="handleError"
                                :http-request="uploadImage"
                                :file-list="fileList"
                              >
                          <el-button slot="trigger" type="primary" size="small" class="upload-button" style="background-color: #2d9aff;">点击上传图片</el-button>
                          <div slot="tip" class="el-upload__tip upload-tip">支持格式:jpg, png等</div>


                        </el-upload>
                      </el-form-item>
                      <el-form-item class="custom-form-item" label="所属类别">
                          <el-input v-model="newEquipment.category" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item class="custom-form-item" label="购置日期">
                          <el-date-picker v-model="newEquipment.createTime" type="date" placeholder="选择日期" ></el-date-picker>
                        </el-form-item>
                        <el-form-item class="custom-form-item" label="设备描述">
                          <el-input type="textarea" v-model="newEquipment.extra" autocomplete="off"></el-input>
                        </el-form-item>
                      </el-form>
                      <div slot="footer" class="dialog-footer">
                      <el-button @click="dialogFormVisible = false">取消</el-button>
                      <el-button type="primary" @click="addEquipment" style="margin-left: 10px;  background-color: #2d9aff;">确定</el-button>
                    </div>
                  </el-dialog>
                </transition>


                <br>
                
                <!-- <el-table ref="tableInstance" :data="tableData" border style="width: 100%" > -->
                  <el-table :data="pagedTableData" :key="itemKey" border style="width: 100%">
                    <el-table-column prop="equipmentName" label="设备名称" width="130">
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
                          <!-- <img :src="scope.row.equipmentImage" alt="设备图片" height="50px"> -->
                          <img :src="scope.row.equipmentImage" alt="设备图片" style="width: 100px; height: 100px; object-fit: cover;">
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
                    <el-table-column prop="status" label="状态" width="100">
                      <template slot-scope="scope">
                         {{scope.row.status == 0 ?'正常':(scope.row.status == 1 ?'报废':(scope.row.status == 2 ?'待审批':'借出'))}}
                      </template>
                    </el-table-column>
                    <!-- <el-table-column  label="管理" width="150">
                      <template slot-scope="scope">
                        <el-button type="danger" @click="scapEquipment(scope.row.equipmentId)" style="background-color: #ff6f6f; border-color: #ff0000;">报废设备</el-button>
                      </template>
                    </el-table-column> -->

                    <el-table-column label="管理" width="150">
                        <template slot-scope="scope">               
                          <el-button 
                            type="danger" 
                            @click="scapEquipment(scope.row.equipmentId)" 
                            style="background-color: #ff6f6f; border-color: #ff0000;" 
                            v-if="scope.row.status === 0">
                            报废设备
                          </el-button>
                        </template>
                    </el-table-column>

                </el-table>
                <br>
                <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page="currentPage"
                        :page-sizes="[5, 10, 20, 50]"
                        :page-size="pageSize"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="tableData.length">
                    </el-pagination> 

            </el-main>
          </el-container>
        </el-container>
    </el-container>
</template>
<script>

import {uploadEquipmentImage, addEquipmentHttp,getEquipmentByAllSelf,scrapEquipmentHttp,getEquipmentByDept,getOutEquipment,outEquipmentHttp } from '@/api' //
export default {
    data() {
        return {
          options: [{
              value: 7,
              label: '正常'
            }, {
              value: 1,
              label: '报废'
            }, {
              value: 2,
              label: '待审批'
            }, {
              value: 3,
              label: '借出'
            }],
          dept: ['实验室1','实验室2','实验室3','实验室4','实验室5'],
          itemKey:Math.random(),
          queryId: null,
          fileList: [],
          token: localStorage.getItem('token'),
          userId:localStorage.getItem('userId'),
          queryEquipment:{
            equipmentId:"",
            category:'' ,
            status:''

          },
          tableData: [],
          outData: [],
          dialogFormVisible: false,
          outDataFormVisible: true,
          newEquipment: {
            equipmentName: '',
            equipmentId: '',
            equipmentImage: '',
            category: '',
            createTime: null,
            extra: '',
            /* status: null, */
        },
        formLabelWidth: '120px',
        
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
        getEquipmentByAllSelf(this.queryEquipment,this.token).then(res => {
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
      outEquipment(outId){
        outEquipmentHttp(outId,this.token).then(res => {
                        if (res.data.code === 1) {
                          this.$message.success('已同意');
                        } else if(res.data.msg == "NOT_LOGIN") {
                            this.$message.error("未登录");
                            this.$router.push('/firstpage');
                        }
                        else{
                          this.$message.error("操作失败");
                        }
                    })
      },
      scapEquipment(scrapId){
        this.$confirm('确认报废该设备吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          scrapEquipmentHttp(scrapId,this.token).then(res => {
                        if (res.data.code === 1) {
                          this.$message.success('报废成功');
                        } else if(res.data.msg == "NOT_LOGIN") {
                            this.$message.error("未登录");
                            this.$router.push('/firstpage');
                        }
                        else{
                          this.$message.error("报废失败");
                        }
                    })
          }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          });          
        });
        
      },
      // handleUpload(event) {
      //   const file = event.detail.files[0];
      //   const reader = new FileReader();
      //   reader.onload = (e) => {
      //     this.newEquipment.equipmentImage = e.target.result;
      //   };
      //   reader.readAsDataURL(file);
      // },

      uploadImage(action) {
        const { file } = action;
        uploadEquipmentImage(file, this.token)
          .then((response) => {
            this.$message.success('图片上传成功');
            // 这里处理更多逻辑，例如更新图片URL等
             this.newEquipment.equipmentImage = response.data.data;  // 假设服务器响应中包含文件的 URL
          })
          .catch(() => {
            this.$message.error('图片上传失败');
          });
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
      },

     handleSizeChange(newSize) {
        this.pageSize = newSize;
    },
    handleCurrentChange(newPage) {
        this.currentPage = newPage;
    },
    

    },
    mounted() {

      //this.tableData = [{"equipmentId":1,"equipmentName":"设备一","equipmentImage":"","extra":"详情描述","deptId":"1","createTime":"2024-06-15T12:00:00","status":0,"category":"电脑"},{"equipmentId":2,"equipmentName":"设备二","equipmentImage":"","extra":"需检修","deptId":"3","createTime":"2024-06-16T08:30:00","status":1,"category":"打印机"},{"equipmentId":3,"equipmentName":"设备三","equipmentImage":"","extra":"良好","deptId":"2","createTime":"2024-06-17T09:45:00","status":2,"category":"扫描仪"},{"equipmentId":4,"equipmentName":"设备四","equipmentImage":"","extra":"近乎全新","deptId":"4","createTime":"2024-06-18T10:15:00","status":0,"category":"电脑"},{"equipmentId":5,"equipmentName":"设备五","equipmentImage":"","extra":"老旧","deptId":"1","createTime":"2024-06-19T11:20:00","status":1,"category":"打印机"},{"equipmentId":6,"equipmentName":"设备六","equipmentImage":"","extra":"使用频繁","deptId":"3","createTime":"2024-06-20T12:00:00","status":0,"category":"扫描仪"},{"equipmentId":7,"equipmentName":"设备七","equipmentImage":"","extra":"需维护","deptId":"2","createTime":"2024-06-21T14:35:00","status":2,"category":"投影仪"},{"equipmentId":8,"equipmentName":"设备八","equipmentImage":"","extra":"准备报废","deptId":"4","createTime":"2024-06-22T16:45:00","status":1,"category":"电脑"},{"equipmentId":9,"equipmentName":"设备九","equipmentImage":"","extra":"性能优越","deptId":"1","createTime":"2024-06-23T17:00:00","status":0,"category":"投影仪"},{"equipmentId":10,"equipmentName":"设备十","equipmentImage":"","extra":"需更新软件","deptId":"3","createTime":"2024-06-24T18:10:00","status":2,"category":"电脑"},{"equipmentId":11,"equipmentName":"设备十一","equipmentImage":"","extra":"正常运行","deptId":"2","createTime":"2024-06-25T09:30:00","status":0,"category":"电脑"},{"equipmentId":12,"equipmentName":"设备十二","equipmentImage":"","extra":"待检修","deptId":"1","createTime":"2024-06-26T08:45:00","status":1,"category":"打印机"},{"equipmentId":13,"equipmentName":"设备十三","equipmentImage":"","extra":"维护中","deptId":"3","createTime":"2024-06-27T10:00:00","status":2,"category":"扫描仪"},{"equipmentId":14,"equipmentName":"设备十四","equipmentImage":"","extra":"新设备","deptId":"4","createTime":"2024-06-28T11:15:00","status":0,"category":"显微镜"},{"equipmentId":15,"equipmentName":"设备十五","equipmentImage":"","extra":"使用寿命长","deptId":"2","createTime":"2024-06-29T12:30:00","status":1,"category":"投影仪"},{"equipmentId":16,"equipmentName":"设备十六","equipmentImage":"","extra":"需要更新驱动","deptId":"1","createTime":"2024-06-30T14:00:00","status":0,"category":"打印机"},{"equipmentId":17,"equipmentName":"设备十七","equipmentImage":"","extra":"维修中","deptId":"3","createTime":"2024-07-01T15:20:00","status":2,"category":"扫描仪"},{"equipmentId":18,"equipmentName":"设备十八","equipmentImage":"","extra":"近乎报废","deptId":"4","createTime":"2024-07-02T16:40:00","status":1,"category":"电脑"},{"equipmentId":19,"equipmentName":"设备十九","equipmentImage":"","extra":"性能稳定","deptId":"2","createTime":"2024-07-03T17:45:00","status":0,"category":"投影仪"},{"equipmentId":20,"equipmentName":"设备二十","equipmentImage":"","extra":"老旧设备","deptId":"1","createTime":"2024-07-04T18:50:00","status":2,"category":"电脑"},{"equipmentId":21,"equipmentName":"设备二十一","equipmentImage":"","extra":"待报废","deptId":"3","createTime":"2024-07-05T19:15:00","status":1,"category":"打印机"},{"equipmentId":22,"equipmentName":"设备二十二","equipmentImage":"","extra":"需更换零件","deptId":"2","createTime":"2024-07-06T20:30:00","status":0,"category":"扫描仪"},{"equipmentId":23,"equipmentName":"设备二十三","equipmentImage":"","extra":"维护良好","deptId":"4","createTime":"2024-07-07T21:45:00","status":2,"category":"电脑"},{"equipmentId":24,"equipmentName":"设备二十四","equipmentImage":"","extra":"待更新软件","deptId":"1","createTime":"2024-07-08T22:00:00","status":0,"category":"投影仪"},{"equipmentId":25,"equipmentName":"设备二十五","equipmentImage":"","extra":"需更换墨盒","deptId":"3","createTime":"2024-07-09T23:10:00","status":1,"category":"打印机"},{"equipmentId":26,"equipmentName":"设备二十六","equipmentImage":"","extra":"待维修","deptId":"2","createTime":"2024-07-10T08:30:00","status":2,"category":"扫描仪"},{"equipmentId":27,"equipmentName":"设备二十七","equipmentImage":"","extra":"性能优秀","deptId":"1","createTime":"2024-07-11T09:45:00","status":0,"category":"电脑"},{"equipmentId":28,"equipmentName":"设备二十八","equipmentImage":"","extra":"设备新购","deptId":"4","createTime":"2024-07-12T10:55:00","status":1,"category":"投影仪"},{"equipmentId":29,"equipmentName":"设备二十九","equipmentImage":"","extra":"待更换部件","deptId":"2","createTime":"2024-07-13T12:00:00","status":0,"category":"电脑"},{"equipmentId":30,"equipmentName":"设备三十","equipmentImage":"","extra":"设备维修中","deptId":"3","createTime":"2024-07-14T13:15:00","status":2,"category":"打印机"}]
      getOutEquipment(this.token).then(res => {
                        if (res.data.code === 1) {
                          this.outData = res.data.data;
                          if(this.outData == []){
                            this.outDataFormVisible = false;
                          }
                          this.$forceUpdate();
                        } else if(res.data.msg == "NOT_LOGIN") {
                            this.$message.error("未登录");
                            this.$router.push('/firstpage');
                        }
                        else{
                          this.outDataFormVisible = false;
                          this.$message.error("获取数据失败");
                        }
                    })
      getEquipmentByDept(this.token).then(res => {
                        if (res.data.code === 1) {
                          this.tableData = res.data.data;
                          this.$forceUpdate();
                        } else if(res.data.msg == "NOT_LOGIN") {
                            this.$message.error("未登录");
                            this.$router.push('/firstpage');
                        }
                        else{
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
  margin-top: -25px; 
}

@import url('https://fonts.googleapis.com/css2?family=Pacifico&display=swap');

.artistic-font1 {
    font-family: '', cursive;
    font-size: 40px;
    color: #41ff5a; /* Customize the color */
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


.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}
.dialog-footer {
  text-align: right; /* 让按钮对齐到右边 */
}
.upload-demo .el-upload__tip {
  font-size: 12px; /* 调整字体大小 */
  color: #999; /* 调整字体颜色 */
}

.custom-dialog {
  background-color: rgb(240, 254, 245);  /* 对话框背景颜色 */
}

.custom-dialog-title {
  text-align: center;  /* 标题居中 */
  font-size: 30px;  /* 放大字体 */
}

.custom-form-item {
  margin-bottom: 6px;  /* 减少表单项的间距 */
}

.upload-button {
  margin-bottom: 10px; /* 减小按钮与提示信息之间的间距 */
}

/* 调整提示信息的样式 */
.upload-tip {
  font-size: 12px; /* 可以调整提示文字的字体大小 */
  margin-top: -10px; /* 将提示信息向上移，减少间隔 */
}

</style>