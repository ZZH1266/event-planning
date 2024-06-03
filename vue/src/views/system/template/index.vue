<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--用户数据-->
      <el-col :span="24" :xs="24">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="活动类型" prop="templateId">
            <el-select
              v-model="queryParams.templateId"
              placeholder="活动类型"
              :clearable="true"
              style="width: 240px"
            >
              <el-option
                v-for="dict in dict.type.sys_normal_disable"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            <el-button type="primary" icon="el-icon-new" size="mini" @click="createTemplate">新增模板</el-button>
          </el-form-item>
        </el-form>

        <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="活动类型" align="center" key="activityId" prop="activityId"  />
          <el-table-column label="活动规模" align="center" key="activitySize" prop="activitySize"  />
          <el-table-column label="使用场馆类型" align="center" key="placePlanToUse" prop="placePlanToUse"  />
          <el-table-column label="运动类型" align="center" key="sportsKind" prop="sportsKind"   />
          <el-table-column label="安全员姓名" align="center" key="safetyOfficerName" prop="safetyOfficerName" width="120" />
          <el-table-column label="室内运动类型" align="center" key="indoor_kind" prop="phonenumber" width="120" />
          <el-table-column label="主持人姓名" align="center" key="hostName" prop="hostName" width="120" />
          <el-table-column label="授课教师姓名" align="center" key="teacherName" prop="teacherName" width="120" />

          <el-table-column label="创建时间" align="center" prop="createTime" v-if="columns[6].visible" width="160">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.createTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="操作"
            align="center"
            width="160"
            class-name="small-padding fixed-width"
          >
            <template slot-scope="scope" v-if="scope.row.userId !== 1">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />
      </el-col>
    </el-row>

    <!-- 添加或修改用户配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="620px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="使用场馆类型" prop="placePlanToUse">
              <el-input v-model="form.placePlanToUse" placeholder="请输入使用的场馆类型"/>
            </el-form-item>
          </el-col>
            <el-col :span="12">
              <el-form-item label="活动规模">
                <el-input v-model="form.activitySize"/>
              </el-form-item>
            </el-col>
        </el-row>

        <el-row>
          <el-col :span="24">
            <el-form-item label="活动类型">
              <el-radio-group v-model="form.templateId">
                <el-radio
                        v-for="dict in dict.type.sys_normal_disable"
                        :key="dict.value"
                        :label="dict.value"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row v-show="form.templateId==1">
          <el-col :span="24">
            <el-form-item label="室内运动类型" prop="indoorKind">
              <el-input v-model="form.indoorKind" placeholder="请输入室内运动类型" maxlength="11" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row v-show="form.templateId==1">
          <el-col :span="24">
            <el-form-item label="主持人姓名" prop="hostName">
              <el-input v-model="form.hostName" placeholder="主持人姓名" maxlength="30" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row v-show="form.templateId==1">
          <el-col :span="24">
            <el-form-item label="授课教师姓名">
              <el-input v-model="form.teacherName"  placeholder="请输入teacherName"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row v-show="form.templateId==2">
          <el-col :span="24">
            <el-form-item label="户外运动类型" prop="sportsKind">
              <el-input v-model="form.sportsKind" placeholder="请输入户外运动类型" maxlength="30" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row v-show="form.templateId==2">
          <el-col :span="24">
            <el-form-item label="安全员姓名" prop="safetyOfficerName">
              <el-input v-model="form.safetyOfficerName" placeholder="请输入安全员姓名" maxlength="50" />
            </el-form-item>
          </el-col>

        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listUser, getUser } from "@/api/system/user";
import { AddTemplate,listTemplate } from "@/api/system/activities";

export default {
  name: "Template",
  // dicts: ['sys_normal_disable', 'sys_user_sex'],
  components: {  },
  data() {
    return {
      dict:{
        type:{
          sys_normal_disable:[{label:'室内',value:1},{label:'户外',value: 2}],
        }
      },
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用户表格数据
      userList: null,
      // 弹出层标题
      title: "",
      // 部门树选项
      deptOptions: undefined,
      // 是否显示弹出层
      open: false,
      // 部门名称
      deptName: undefined,
      // 默认密码
      initPassword: undefined,
      // 表单参数
      form: {
        templateId: 1,
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        templateId: undefined,
      },
      // 列信息
      columns: [
        { key: 0, label: `用户编号`, visible: true },
        { key: 1, label: `用户名称`, visible: true },
        { key: 2, label: `用户昵称`, visible: true },
        { key: 3, label: `部门`, visible: true },
        { key: 4, label: `手机号码`, visible: true },
        { key: 5, label: `状态`, visible: true },
        { key: 6, label: `创建时间`, visible: true }
      ],
      // 表单校验
      rules: {
        userName: [
          { required: true, message: "用户名称不能为空", trigger: "blur" },
          { min: 2, max: 20, message: '用户名称长度必须介于 2 和 20 之间', trigger: 'blur' }
        ],
        nickName: [
          { required: true, message: "用户昵称不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "用户密码不能为空", trigger: "blur" },
          { min: 5, max: 20, message: '用户密码长度必须介于 5 和 20 之间', trigger: 'blur' },
          { pattern: /^[^<>"'|\\]+$/, message: "不能包含非法字符：< > \" ' \\\ |", trigger: "blur" }
        ],
        email: [
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"]
          }
        ],
        phonenumber: [
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur"
          }
        ]
      }
    };
  },
  watch: {
    // 根据名称筛选部门树
    deptName(val) {
      this.$refs.tree.filter(val);
    }
  },
  created() {
    this.getList();
    // this.getConfigKey("sys.user.initPassword").then(response => {
    //   this.initPassword = response.msg;
    // });
  },
  methods: {
    setRoles(){

    },
    createTemplate(){
      this.open = true;
    },
    /** 查询用户列表 */
    getList() {
      this.loading = true;
      listTemplate(this.queryParams).then(response => {
          this.userList = response.data.records;
          this.total = response.data.total;
          this.loading = false;
        }
      );
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        activityId: undefined,
        hostName: undefined,
        indoorKind: undefined,
        safetyOfficerName: undefined,
        sportsKind: undefined,
        placePlanToUse: undefined,
        activitySize: 0,
        templateId: 1,
        teacherName: undefined,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
      this.queryParams.deptId = undefined;
      this.$refs.tree.setCurrentKey(null);
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.userId);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    /** 修改按钮操作 */
    handleUpdate2(row) {
      this.reset();
      const userId = row.userId || this.ids;
      getUser(userId).then(response => {
        this.form = response.data;
        this.postOptions = response.posts;
        this.roleOptions = response.roles;
        this.$set(this.form, "postIds", response.postIds);
        this.$set(this.form, "roleIds", response.roleIds);
        this.open = true;
        this.title = "修改用户";
        this.form.password = "";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.activityId != undefined) {
            updateTemplate(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            AddTemplate(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete2(row) {
      const userIds = row.userId || this.ids;
      this.$modal.confirm('是否确认删除编号为"' + userIds + '"的数据项？').then(function() {
        return delUser(userIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
  }
};
</script>
