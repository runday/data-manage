<template>
  <div class="app-container">
    <el-row class="app-query">
      <el-select clearable class="filter-item" v-model="listQuery.customerNo"  style="width: 150px;" placeholder="所属客户">
        <el-option v-for="item in customerOption" :key="item.value" :label="item.label" :value="item.value"></el-option>
      </el-select>
      <el-select clearable class="filter-item" v-model="listQuery.status"  style="width: 150px;" placeholder="是否销售">
        <el-option v-for="item in isOrNotArray" :key="item.value" :label="item.label" :value="item.value"></el-option>
      </el-select>
      <el-select clearable class="filter-item" v-model="listQuery.runStatus"  style="width: 150px;" placeholder="是否运行">
        <el-option v-for="item in isOrNotArray" :key="item.value" :label="item.label" :value="item.value"></el-option>
      </el-select>
      <el-input clearable v-model="listQuery.deviceSuffix" placeholder="设备编号"  style="width: 150px;"></el-input>
      <el-date-picker v-model="listQuery.saleDatetime" type="date" value-format="yyyy-MM-dd hh:mm:ss" placeholder="销售时间" style="width: 150px;"></el-date-picker>
      <el-button  type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
      <el-button  type="primary" icon="el-icon-circle-plus-outline" @click="handleGenerateQRCode">生成二维码</el-button>
    </el-row>

    <el-table :data="list" v-loading="listLoading" element-loading-text="给我一点时间" border fit highlight-current-row style="width: 120%" @row-contextmenu="openTableMenu">
      <el-table-column align="left" :show-overflow-tooltip="true" label="设备编号">
        <template slot-scope="scope">
          <span>{{scope.row.deviceSuffix}}</span>
        </template>
      </el-table-column>
      <el-table-column align="left" :show-overflow-tooltip="true"  label="所属客户">
        <template slot-scope="scope">
          <span>{{scope.row.customerName}}</span>
        </template>
      </el-table-column>
      <el-table-column align="left" :show-overflow-tooltip="true" label="设备类型">
        <template slot-scope="scope">
          <span>{{scope.row.deviceType}}</span>
        </template>
      </el-table-column>
      <el-table-column align="left" :show-overflow-tooltip="true" label="燃料">
        <template slot-scope="scope">
          <span v-if="scope.row.power!=null">
            <span v-for="item in fuelArray"  v-if="scope.row.power===item.value">{{item.label}}</span>
          </span>
          <span v-else>无</span>
        </template>
      </el-table-column>
      <el-table-column align="left" :show-overflow-tooltip="true" label="介质">
        <template slot-scope="scope">
          <span v-if="scope.row.media!=null">
            <span v-for="item in mediumArray"  v-if="scope.row.media===item.value">{{item.label}}</span>
          </span>
          <span v-else>无</span>
        </template>
      </el-table-column>
      <el-table-column align="left" :show-overflow-tooltip="true"  label="是否销售">
        <template slot-scope="scope">
          <span>{{scope.row.status | statusFilterLabel(isOrNotArray)[0].label}}</span>
        </template>
      </el-table-column>
      <el-table-column align="left" :show-overflow-tooltip="true"  label="是否运行">
        <template slot-scope="scope">
          {{scope.row.runStatus | statusFilterLabel(isOrNotArray)[0].label}}
        </template>
      </el-table-column>
      <el-table-column align="left" :show-overflow-tooltip="true"  label="销售时间">
        <template slot-scope="scope">
          <span>{{scope.row.saleDatetime}}</span>
        </template>
      </el-table-column>
      <el-table-column align="left" :show-overflow-tooltip="true"  label="导入时间">
        <template slot-scope="scope">
          <span>{{scope.row.importDatetime}}</span>
        </template>
      </el-table-column>
    </el-table>
    <menu-context ref="menuContext">
      <menu-context-item @click="handleUpdate">编辑</menu-context-item>
      <menu-context-item @click="handleDelete">删除</menu-context-item>
    </menu-context>
    <div class="pagination-container">
      <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="listQuery.pageNum" :page-sizes="[5,10,15,20]" :page-size="listQuery.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="listQuery.total">
      </el-pagination>
    </div>
    <div class="el-dialog-device">
      <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="40%">
        <el-form ref="deviceFormData" :model="deviceFormData" label-position="right" label-width="80px" style='width: 90%; margin-left:15px;'>
          <el-row>
            <el-col :span="12">
              <el-form-item label="燃料">
                <el-select clearable class="filter-item" v-model="deviceFormData.power"  style="width: 100%">
                  <el-option v-for="item in fuelArray" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="介质">
                <el-select clearable class="filter-item" v-model="deviceFormData.media"  style="width: 100%">
                  <el-option v-for="item in mediumArray" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="设备类型">
                <el-select class="filter-item" v-model="deviceFormData.deviceType"  style="width: 100%">
                  <el-option v-for="item in deviceTypeOption" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" v-if="deviceFormData.id">
              <el-form-item label="是否销售">
                <el-select class="filter-item" v-model="deviceFormData.status"  style="width: 100%">
                  <el-option v-for="item in isOrNotArray" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="是否运行">
                <el-select class="filter-item" v-model="deviceFormData.runStatus"  style="width: 100%">
                  <el-option v-for="item in isOrNotArray" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" v-if="deviceFormData.id">
              <el-form-item label="售出时间">
                <el-date-picker type="datetime" v-model="deviceFormData.saleDatetime" style="width: 100%;" default-time="00:00:00"></el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="editData">确认</el-button>
        </div>
      </el-dialog>
      <el-dialog title="生成二维码" :visible.sync="dialogQRCodeFormVisible" width="30%">
        <el-form :rules="qRCodeRules" ref="qRCodeForm" :model="qRCodeFormData" label-position="right" label-width="80px" style='width: 80%; margin-left:30px;'>
          <el-form-item label="开始编号" prop="startSuffix">
            <el-input v-model="qRCodeFormData.startSuffix"></el-input>
          </el-form-item>
          <el-form-item label="结束编号" prop="endSuffix">
            <el-input v-model="qRCodeFormData.endSuffix"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogQRCodeFormVisible = false">取消</el-button>
          <el-button type="primary" @click="generateQRCode">生成</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import {fuelArray,mediumArray} from '@/utils/common'
  import {formatDateTime} from '@/utils/date'
  import {getDeviceListByEnterpriseIdAndPage,editDevice,deleteDeviceById} from '@/api/device'
  import {getCustomerListByEnterpriseId} from '@/api/token-dict'
  import {getDeviceTypeList} from '@/api/device-type'
  export default {
    data() {
      return {
        list: null,
        listQuery: {
          total:50,
          pageNum:1,
          pageSize:5,
          deviceSuffix:null,
          customerNo:null,
          enterpriseId:this.$store.state.user.orgId,
          status:null,
          runStatus:null,
          saleDatetime:null
        },
        customerOption:[],
        isOrNotArray:[
          {value:1,label:'是'},
          {value:0,label:'否'}
        ],
        deviceTypeOption:[],
        textMap: {
          update: '编辑',
          create: '新增'
        },
        fuelArray:fuelArray,
        mediumArray:mediumArray,
        dialogStatus: '',
        dialogFormVisible: false,
        deviceFormData: {
          id:'',
          media:'',
          power:'',
          deviceType:'',
          status:1,
          runStatus:1,
          saleDatetime:''
        },
        dialogQRCodeFormVisible:false,
        qRCodeFormData:{
          startSuffix:'',
          endSuffix:''
        },
        qRCodeRules: {
          startSuffix: [
            { required: true, message: '开始编号不能为空', trigger: 'blur' }
          ],
          endSuffix: [
            { required: true, message: '结束编号不能为空', trigger: 'blur' }
          ]
        },
        listLoading:false
      }
    },
    filters: {
      statusFilterLabel(status,dictaryList){
        return dictaryList.filter(item => {
          return item.value==status
        })
      }
    },
    created() {
      this.getList()
      this.initCustomerList()
      this.initDeviceTypeList()
    },
    methods: {
      initCustomerList(){
        let customerOption=[]
        getCustomerListByEnterpriseId(this.listQuery.enterpriseId).then(data=>{
          data.data.data.forEach(item=>{
            customerOption.push({value:item.code+"",label:item.name})
          })
          this.customerOption=customerOption
        })
      },
      initDeviceTypeList(){
        let deviceTypeOption=[]
        getDeviceTypeList().then(data=>{
          data.data.data.forEach(item=>{
            deviceTypeOption.push({value:item.deviceType,label:item.deviceType})
          })
          this.deviceTypeOption=deviceTypeOption
        })
      },
      openTableMenu(row, event) {
        this.$refs.menuContext.openTableMenu(row,event);
      },
      handleFilter() {
        this.listQuery.pageNum = 1
        this.getList()
      },
      getList() {
        this.listLoading = true
        getDeviceListByEnterpriseIdAndPage(this.listQuery).then(response => {
          const data=response.data.data
          this.list=data.list
          this.listQuery.total=data.total
          this.listQuery.pageNum=data.pageNum
          this.listQuery.pageSize=data.pageSize
          this.listLoading = false
        })
      },
      resetTemp() {
        this.deviceFormData = {
          id:'',
          media:'',
          power:'',
          deviceType:'',
          status:1,
          runStatus:1,
          saleDatetime:''
        }
      },
      handleCreate() {
        this.resetTemp()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['deviceFormData'].clearValidate()
        })
      },
      handleGenerateQRCode(){
        this.qRCodeFormData.startSuffix=''
        this.qRCodeFormData.endSuffix=''
        this.dialogQRCodeFormVisible = true
        this.$nextTick(() => {
          this.$refs['qRCodeForm'].clearValidate()
        })
      },
      handleUpdate(row) {
        this.deviceFormData = Object.assign({}, row) // copy obj
        if(this.deviceFormData.saleDatetime){
          this.deviceFormData.saleDatetime=new Date(this.deviceFormData.saleDatetime)
        }else{
          this.deviceFormData.saleDatetime=new Date()
        }
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['deviceFormData'].clearValidate()
        })
      },
      generateQRCode(){
        this.$refs.qRCodeForm.validate(valid => {
          if (valid) {
            this.dialogQRCodeFormVisible = false
            window.open(process.env.BASE_API+'/device/generateqrcode?startSuffix='+this.qRCodeFormData.startSuffix+'&endSuffix='+this.qRCodeFormData.endSuffix, "_blank");
          } else {
            return false
          }
        })
      },
      editData(){
        this.$refs.deviceFormData.validate(valid => {
          if (valid) {
            this.deviceFormData.saleDatetime=formatDateTime(this.deviceFormData.saleDatetime,"yyyy-MM-dd hh:mm:ss")
            editDevice(this.deviceFormData).then(data=>{
              this.dialogFormVisible = false
              this.$message({
                message: "成功",
                type: 'success'
              });
              this.getList()
            })
          } else {
            return false
          }
        })
      },
      handleDelete(row) {
        this.$confirm('确认删除?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteDeviceById(row.id).then(data=>{
            this.$message({
              message: '删除成功',
              type: 'success'
            });
            this.list.splice(this.list.indexOf(row), 1)
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      handleSizeChange(val) {
        this.listQuery.pageSize = val
        this.getList()
      },
      handleCurrentChange(val) {
        this.listQuery.pageNum = val
        this.getList()
      }
    }
  }
</script>
