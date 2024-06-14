<template>
    <div class="app-container">
        <el-row :gutter="20">
            <!--用户数据-->
            <el-col :span="24" :xs="24">
                <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
                    <el-form-item label="用户名称" prop="username">
                        <el-input
                                v-model="queryParams.username"
                                placeholder="请输入用户名称"
                                clearable
                                style="width: 240px"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                    <el-form-item label="email" prop="email">
                        <el-input
                                v-model="queryParams.email"
                                placeholder="请输入email"
                                clearable
                                style="width: 240px"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
                    </el-form-item>
                </el-form>

                <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
                    <el-table-column label="用户编号" align="center" key="id" prop="id"/>
                    <el-table-column label="用户名称" align="center" key="username" prop="username" :show-overflow-tooltip="true"/>
                    <el-table-column label="email" align="center" key="email" prop="email" :show-overflow-tooltip="true"/>
                    <el-table-column label="创建时间" align="center" key="createdAt" prop="createdAt" :show-overflow-tooltip="true">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.createdTime) }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            label="操作"
                            align="center"
                            width="160"
                            class-name="small-padding fixed-width"
                    >
                        <template slot-scope="scope">
                            <el-button
                                    size="mini"
                                    type="text"
                                    icon="el-icon-edit"
                                    @click="handleUpdate(scope.row)"
                            >修改
                            </el-button>
                            <el-button v-if="1>2 && sysUser.roles.includes('superadmin')"
                                    size="mini"
                                    type="text"
                                    icon="el-icon-delete"
                                    @click="handleDelete(scope.row)"
                            >删除
                            </el-button>
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
        <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                <el-row>
                    <el-col>
                        <el-form-item label="用户名称" prop="username">
                            <el-input v-model="form.username" placeholder="请输入用户名称" maxlength="30" disabled/>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col>
                        <el-form-item label="email">
                            <el-input v-model="form.email" type="text" placeholder="请输入email"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row v-if="sysUser.roles.includes('superadmin')">
                    <el-col>
                        <el-form-item label="角色"  prop="roleId">
                            <el-select v-model="form.role" placeholder="请选择角色">
                                <el-option
                                        v-for="item in roleOptions"
                                        :key="item.roleId"
                                        :label='item.roleName'
                                        :value="item.roleId">
                                </el-option>
                            </el-select>
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
    import {addUser, changeUserStatus, delUser, getUser, list, listUser, resetUserPwd, updateUser} from "@/api/system/user";
    import {getToken} from "@/utils/auth";
    import {mapState} from "vuex";

    export default {
        name: "User",
        // dicts: ['sys_normal_disable', 'sys_user_sex'],
        data() {
            return {
                dict: {
                    type: {
                        sys_normal_disable: [{label: '正常', value: 0}, {label: '停用', value: 1}],
                        sys_user_sex: [{label: '男', value: 0}, {label: '女', value: 1}],
                    }
                },
                // 遮罩层
                loading: true,
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
                // 日期范围
                dateRange: [],
                // 岗位选项
                postOptions: [],
                // 角色选项 普通用户0 系统管理员1  超级管理员2
                roleOptions: [{"roleId": 0, "roleName": "user"}, {"roleId": 1, "roleName": "admin"}],
                // 表单参数
                form: {},
                defaultProps: {
                    children: "children",
                    label: "label"
                },
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                    username: undefined,
                    email: undefined,
                },
                // 列信息
                columns: [
                    {key: 0, label: `用户编号`, visible: true},
                    {key: 1, label: `用户名称`, visible: true},
                    {key: 2, label: `用户昵称`, visible: true},
                    {key: 3, label: `部门`, visible: true},
                    {key: 4, label: `手机号码`, visible: true},
                    {key: 5, label: `状态`, visible: true},
                    {key: 6, label: `创建时间`, visible: true}
                ],
                // 表单校验
                rules: {
                    email: [
                        {
                            type: "email",
                            message: "请输入正确的邮箱地址",
                            trigger: ["blur", "change"]
                        }
                    ],
                }
            };
        },
        computed:{
          ...mapState({
              sysUser: state => state.user
          }),
        },

        created() {
            this.getList();
            // this.getDeptTree();
            // this.getConfigKey("sys.user.initPassword").then(response => {
            //   this.initPassword = response.msg;
            // });
        },
        methods: {
            setRoles() {

            },
            /** 查询用户列表 */
            getList() {
                this.loading = true;
                //this.addDateRange(this.queryParams, this.dateRange)
                list(this.queryParams).then(res => {
                        console.log('userlist', res);
                        this.userList = res.data.records;
                        this.total = res.data.total;
                        this.loading = false;
                    }
                );
            },
            // 用户状态修改
            handleStatusChange(row) {
                let text = row.status === "0" ? "启用" : "停用";
                this.$modal.confirm('确认要"' + text + '""' + row.username + '"用户吗？').then(function () {
                    return changeUserStatus(row.userId, row.status);
                }).then(() => {
                    this.$modal.msgSuccess(text + "成功");
                }).catch(function () {
                    row.status = row.status === "0" ? "1" : "0";
                });
            },
            // 取消按钮
            cancel() {
                this.open = false;
                this.reset();
            },
            // 表单重置
            reset() {
                this.form = {
                    userId: undefined,
                    username: undefined,
                    nickName: undefined,
                    password: undefined,
                    email: undefined,
                    sex: undefined,
                    status: "0",
                    remark: undefined,
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
                this.handleQuery();
            },
            // 多选框选中数据
            handleSelectionChange(selection) {
                this.ids = selection.map(item => item.userId);
                this.single = selection.length != 1;
                this.multiple = !selection.length;
            },
            // 更多操作触发
            handleCommand(command, row) {
                switch (command) {
                    case "handleResetPwd":
                        this.handleResetPwd(row);
                        break;
                    case "handleAuthRole":
                        this.handleAuthRole(row);
                        break;
                    default:
                        break;
                }
            },
            /** 新增按钮操作 */
            handleAdd() {
                this.reset();
                getUser().then(response => {
                    this.postOptions = response.posts;
                    this.roleOptions = response.roles;
                    this.open = true;
                    this.title = "添加用户";
                    this.form.password = this.initPassword;
                });
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.open = true;
                this.reset();
                this.form = row;
                this.form.userId = row.id;
                console.log('row is',row, 'form is', this.form);
                /* const userId = row.userId || this.ids;
                 getUser(userId).then(response => {
                   this.form = response.data;
                   this.postOptions = response.posts;
                   this.roleOptions = response.roles;
                   this.$set(this.form, "postIds", response.postIds);
                   this.$set(this.form, "roleIds", response.roleIds);
                   this.open = true;
                   this.title = "修改用户";
                   this.form.password = "";
                 });*/
            },
            /** 重置密码按钮操作 */
            handleResetPwd(row) {
                this.$prompt('请输入"' + row.username + '"的新密码', "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    closeOnClickModal: false,
                    inputPattern: /^.{5,20}$/,
                    inputErrorMessage: "用户密码长度必须介于 5 和 20 之间",
                    inputValidator: (value) => {
                        if (/<|>|"|'|\||\\/.test(value)) {
                            return "不能包含非法字符：< > \" ' \\\ |"
                        }
                    },
                }).then(({value}) => {
                    resetUserPwd(row.userId, value).then(response => {
                        this.$modal.msgSuccess("修改成功，新密码是：" + value);
                    });
                }).catch(() => {
                });
            },
            /** 分配角色操作 */
            handleAuthRole: function (row) {
                const userId = row.userId;
                this.$router.push("/system/user-auth/role/" + userId);
            },
            /** 提交按钮 */
            submitForm: function () {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.userId != undefined) {
                            updateUser(this.form).then(response => {
                                this.$modal.msgSuccess("修改成功");
                                this.open = false;
                                this.getList();
                            });
                        } else {
                            addUser(this.form).then(response => {
                                this.$modal.msgSuccess("新增成功");
                                this.open = false;
                                this.getList();
                            });
                        }
                    }
                });
            },
            /** 删除按钮操作 */
            handleDelete(row) {
                const userIds = row.userId || this.ids;
                this.$modal.confirm('是否确认删除用户编号为"' + userIds + '"的数据项？').then(function () {
                    return delUser(userIds);
                }).then(() => {
                    this.getList();
                    this.$modal.msgSuccess("删除成功");
                }).catch(() => {
                });
            },
            /** 导出按钮操作 */
            handleExport() {
                this.download('system/user/export', {
                    ...this.queryParams
                }, `user_${new Date().getTime()}.xlsx`)
            },
            /** 导入按钮操作 */
            handleImport() {
                this.upload.title = "用户导入";
                this.upload.open = true;
            },
            /** 下载模板操作 */
            importTemplate() {
                this.download('system/user/importTemplate', {}, `user_template_${new Date().getTime()}.xlsx`)
            },
            // 文件上传中处理
            handleFileUploadProgress(event, file, fileList) {
                this.upload.isUploading = true;
            },
            // 文件上传成功处理
            handleFileSuccess(response, file, fileList) {
                this.upload.open = false;
                this.upload.isUploading = false;
                this.$refs.upload.clearFiles();
                this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", {dangerouslyUseHTMLString: true});
                this.getList();
            },
            // 提交上传文件
            submitFileForm() {
                this.$refs.upload.submit();
            }
        }
    };
</script>
