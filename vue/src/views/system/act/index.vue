<template>
    <div class="app-container">
        <el-row :gutter="20">
            <!--用户数据-->
            <el-col :span="24" :xs="24">
                <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
                    <el-form-item label="活动名称" prop="activityName">
                        <el-input
                                v-model="queryParams.activityName"
                                placeholder="请输入活动名称"
                                clearable
                                style="width: 140px"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>

                    <el-form-item label="状态" prop="status">
                        <el-select
                                v-model="queryParams.status"
                                placeholder="活动状态"
                                clearable
                                style="width: 140px"
                        >
                            <el-option
                                    v-for="dict in dict.type.sys_normal_disable"
                                    :key="dict.value"
                                    :label="dict.label"
                                    :value="dict.value"
                            />
                        </el-select>
                    </el-form-item>
                    <el-form-item label="活动日期">
                        <el-date-picker
                                v-model="dateRange"
                                style="width: 380px"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                type="datetimerange"
                                range-separator="-"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期"
                        ></el-date-picker>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
                    </el-form-item>
                </el-form>
                <el-row style="padding-bottom: 10px">
                    <el-button type="primary" icon="el-icon-new" size="mini" @click="createActivity">新增活动</el-button>
                </el-row>

                <el-table v-loading="loading" :data="actList" @selection-change="handleSelectionChange">
                    <!--<el-table-column type="selection" width="50" align="center" />-->
                    <el-table-column label="活动编号" align="center" key="activityId" prop="activityId"/>
                    <el-table-column label="活动名称" align="left" key="activityName" prop="activityName"/>
                    <el-table-column label="活动地址" align="left" key="address" prop="address"/>
                    <el-table-column label="活动时间" align="left" >
                        <template slot-scope="scope">
                           {{scope.row.startTime}}<br/>{{scope.row.endTime}}
                        </template>
                    </el-table-column>
                    <!--<el-table-column label="状态" align="center" key="status">
                        <template slot-scope="scope">
                            <el-switch
                                    v-model="scope.row.status"
                                    active-value="0"
                                    inactive-value="1"
                                    @change="handleStatusChange(scope.row)"
                            ></el-switch>
                        </template>
                    </el-table-column>-->
                    <el-table-column label="创建时间" align="center" prop="createdTime" width="160">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.createdTime) }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="活动状态" align="center" key="status" prop="status"/>

                    <el-table-column
                            label="操作"
                            align="center"
                            width="230"
                            class-name="small-padding fixed-width"
                    >
                        <template slot-scope="scope" v-if="scope.row.userId !== 1">
                            <el-button
                                    size="mini"
                                    type="text"
                                    icon="el-icon-plus"
                                    @click="handleDetail(scope.row)"
                            >详情
                            </el-button>
                            <el-button
                                    size="mini"
                                    type="text"
                                    icon="el-icon-edit"
                                    @click="handleUpdate(scope.row)"
                            >修改
                            </el-button>
                            <el-button v-if="sysUser.roles.includes('superadmin')"
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
        <el-dialog title="添加活动" :visible.sync="open" width="620px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="110px">
                <el-row>
                    <el-col>
                        <el-form-item label="请选择活动模板" prop="templateId">
                            <el-select v-model="form.templateId" placeholder="请选择活动模板">
                                <el-option
                                        v-for="item in templateList"
                                        :key="item.activityId"
                                        :label='item.activityId +","+item.placePlanToUse+","+item.activitySize'
                                        :value="item.activityId">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="活动名称" prop="activityName">
                            <el-input v-model="form.activityName" placeholder="请输入活动名称"/>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="地点" prop="safetyOfficerName">
                            <el-input v-model="form.address" placeholder="请输入地点" maxlength="50"/>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-form-item label="活动日期">
                        <el-date-picker
                                v-model="dateRange"
                                style="width: 380px"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                type="datetimerange"
                                range-separator="-"
                                start-placeholder="开始时间"
                                end-placeholder="结束时间"
                        ></el-date-picker>
                    </el-form-item>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="活动简介">
                            <el-input v-model="form.description" type="textarea" rows="5"/>
                        </el-form-item>
                    </el-col>
                </el-row>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitForm">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
            </div>
        </el-dialog>

        <el-dialog title="修改活动" :visible.sync="mdfOpen" width="620px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="110px">
                <el-row>
                    <el-col>
                        <el-form-item label="请选择活动模板" prop="templateId">
                            <el-select v-model="form.templateId" placeholder="请选择活动模板">
                                <el-option
                                        v-for="item in templateList"
                                        :key="item.activityId"
                                        :label='item.activityId +","+item.placePlanToUse+","+item.activitySize'
                                        :value="item.activityId">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="活动名称" prop="activityName">
                            <el-input v-model="form.activityName" placeholder="请输入活动名称"/>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="地点" prop="safetyOfficerName">
                            <el-input v-model="form.address" placeholder="请输入地点" maxlength="50"/>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-form-item label="活动日期">
                        <el-date-picker
                                v-model="dateRange"
                                style="width: 380px"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                type="datetimerange"
                                range-separator="-"
                                start-placeholder="开始时间"
                                end-placeholder="结束时间"
                        ></el-date-picker>
                    </el-form-item>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="状态" prop="status">
                                <el-select
                                        v-model="form.status"
                                        placeholder="活动状态"
                                        clearable
                                        style="width: 140px"
                                >
                                    <el-option
                                            v-for="dict in dict.type.sys_normal_disable"
                                            :key="dict.value"
                                            :label="dict.label"
                                            :value="dict.value"
                                    />
                                </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="活动简介">
                            <el-input v-model="form.description" type="textarea" rows="5"/>
                        </el-form-item>
                    </el-col>
                </el-row>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitForm">确 定</el-button>
                <el-button @click="mdfOpen=false">取 消</el-button>
            </div>
        </el-dialog>

        <!--申请加入列表与管理审核-->
        <el-dialog title="活动详情" :visible.sync="detailOpen" width="620px" append-to-body>
            <el-form label-width="110px">
                <el-row>
                    <el-col>
                        <el-form-item label="活动名称">
                            <el-input v-model="form.activityName" disabled/>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="地点" prop="safetyOfficerName">
                            <el-input v-model="form.address" disabled/>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col>
                        <el-form-item label="活动简介">
                            <el-input v-model="form.description" type="textarea" rows="5" disabled/>
                        </el-form-item>
                    </el-col>
                </el-row>
                <div v-if="participanList && participanList.length>0">
                    <el-row>
                        <el-col><h2>申请加入活动列表</h2></el-col>
                    </el-row>
                    <el-row>
                        <el-table v-loading="loading" :data="participanList">
                            <el-table-column label="ID" align="center" key="applyId" prop="applyId"/>
                            <el-table-column label="申请人" align="center" key="userId" prop="userId"/>
                            <el-table-column label="申请人姓名" align="center" key="username" prop="username"/>
                            <el-table-column label="状态" align="center" key="state" prop="state"/>
                            <el-table-column label="操作" align="center" key="status">
                                <template slot-scope="scope" v-if="scope.row.state == '申请中'">
                                    <el-button
                                            size="mini"
                                            type="text"
                                            icon="el-icon-check"
                                            @click="handleApply(form.activityId,scope.row)"
                                    >接收
                                    </el-button>
                                    <el-button
                                            size="mini"
                                            type="text"
                                            icon="el-icon-close"
                                            @click="handleReject(form.activityId,scope.row)"
                                    >拒绝
                                    </el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                    </el-row>
                </div>
                <div v-else>
                    <h2>暂时没有人申请加入活动</h2>
                </div>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="detailCancel">关 闭</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import {getUser, listUser} from "@/api/system/user";
    import {
        addActivities,
        listTemplate,
        listActivities,
        updateaActivities,
        delActivities,
        listParticipant,
        applyParticipant,
        rejectParticipant
    } from "@/api/system/activities";
    import {mapState} from "vuex";


    export default {
        name: "User",
        // dicts: ['sys_normal_disable', 'sys_user_sex'],
        components: {},
        data() {
            return {
                dict: {
                    type: {//筹备中','进行中','已完成
                        sys_normal_disable: [{label: '筹备中', value: '筹备中'}, {label: '进行中', value: '进行中'}, {label: '已完成', value: '已完成'}],
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
                // 活动表格数据
                actList: null,
                templateList: [],
                //活动申请表格数据
                participanList: null,
                participanTotal: 0,
                // 弹出层标题
                title: "",
                // 部门树选项
                deptOptions: undefined,
                // 是否显示弹出层
                open: false,
                detailOpen: false,
                //修改窗口
                mdfOpen: false,
                // 部门名称
                deptName: undefined,
                // 默认密码
                initPassword: undefined,
                // 日期范围
                dateRange: [],
                // 岗位选项
                postOptions: [],
                // 角色选项
                roleOptions: [],
                // 表单参数
                form: {},
                defaultProps: {
                    children: "children",
                    label: "label"
                },
                // 用户导入参数
                upload: {
                    // 是否显示弹出层（用户导入）
                    open: false,
                    // 弹出层标题（用户导入）
                    title: "",
                    // 是否禁用上传
                    isUploading: false,
                    // 是否更新已经存在的用户数据
                    updateSupport: 0,
                },
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                    activityName: undefined,
                    status: undefined,
                    type: 0
                },
                participantQueryParams: {
                    pageNum: 1,
                    pageSize: 10,
                    activityId: ''
                },
                // 表单校验
                rules: {
                    activityName: [
                        {required: true, message: "用户名称不能为空", trigger: "blur"},
                        {min: 2, max: 20, message: '用户名称长度必须介于 2 和 20 之间', trigger: 'blur'}
                    ],
                    nickName: [
                        {required: true, message: "用户昵称不能为空", trigger: "blur"}
                    ],
                    password: [
                        {required: true, message: "用户密码不能为空", trigger: "blur"},
                        {min: 5, max: 20, message: '用户密码长度必须介于 5 和 20 之间', trigger: 'blur'},
                        {pattern: /^[^<>"'|\\]+$/, message: "不能包含非法字符：< > \" ' \\\ |", trigger: "blur"}
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
        computed:{
            ...mapState({
                sysUser: state => state.user
            }),
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
            createActivity() {
                this.reset();
                this.open = true;
                this.getTemplateList();
            },
            setRoles() {

            },
            /** 查询用户列表 */
            getList() {
                this.loading = true;
                if ( this.sysUser.roles.includes('user')){
                    this.queryParams.type = 1;
                }
                listActivities(this.addDateRange(this.queryParams, this.dateRange)).then(res => {
                        this.actList = res.data.records;
                        this.total = res.data.total;
                        this.loading = false;
                    }
                );
            },
            getTemplateList(){
                listTemplate(this.queryParams).then(response => {
                        this.templateList = response.data.records;
                    }
                );
            },
            handleDetail(row){
                this.form = row;
                this.participantQueryParams.activityId = row.activityId;
                this.detailOpen = true;
                this.getParticipanList();
            },
            getParticipanList() {
                listParticipant(this.participantQueryParams).then(res => {
                        this.participanList = res.data.records;
                        this.participanTotal = res.data.total;
                        this.loading = false;
                    }
                );
            },
            // 详情关闭
            detailCancel() {
                this.detailOpen = false;
                this.form = {};
                // this.reset();
            },
            //通过
            handleApply(activityId,item) {
                applyParticipant({applyId:item.applyId, activityId: activityId}).then(res => {
                    this.$message.success("审核成功!");
                    this.getParticipanList();
                });
            },
            //拒绝
            handleReject(activityId,item) {
                rejectParticipant({applyId:item.applyId, activityId: activityId}).then(res => {
                    this.$message.success("拒绝成功!");
                    this.getParticipanList();
                });
            },
            // 用户状态修改
            handleStatusChange(row) {
                let text = row.status === "0" ? "启用" : "停用";
                this.$modal.confirm('确认要"' + text + '""' + row.activityName + '"用户吗？').then(function () {
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
                    activityId: undefined,
                    activityName: undefined,
                    address: undefined,
                    templateId: undefined,
                    description: undefined,
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
                // this.reset();
                this.form=row;
                this.mdfOpen = true;
                // this.dateRange[0]=row.startTime;
                // this.dateRange[1]=row.startTime;
                this.getTemplateList();
            },
            /** 重置密码按钮操作 */
            handleResetPwd(row) {
                this.$prompt('请输入"' + row.activityName + '"的新密码', "提示", {
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
                        if (this.form.activityId != undefined) {
                            this.form.startTime=this.dateRange[0];
                            this.form.endTime=this.dateRange[1];
                            updateaActivities(this.form).then(res => {
                                this.$modal.msgSuccess("修改成功");
                                this.mdfOpen = false;
                                this.getList();
                            });
                        } else {
                            console.log('this.dateRange', this.dateRange);
                            this.form.startTime=this.dateRange[0];
                            this.form.endTime=this.dateRange[1];
                            addActivities(this.form).then(res => {
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
                const actName = row.activityName;
                const id = row.activityId;
                this.$modal.confirm('是否确认删除活动:"' + actName + '"？').then(function () {
                    delActivities(id).then(res => {
                        this.$modal.msgSuccess("删除成功");
                        this.getList();
                    });
                }).then(() => {

                }).catch(() => {
                });
            },
        }
    };
</script>
