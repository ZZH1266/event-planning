<!--管理通知-->
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

                    <el-form-item label="消息状态" prop="status">
                        <el-select
                                v-model="queryParams.status"
                                placeholder="消息状态"
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

                    <el-form-item>
                        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
                        <el-button type="primary" icon="el-icon-message" size="mini" @click="addNotification">发送通知</el-button>
                    </el-form-item>
                </el-form>

                <el-table v-loading="loading" :data="actList" @selection-change="handleSelectionChange">
                    <!--<el-table-column type="selection" width="50" align="center" />-->
                    <el-table-column label="模板编号" align="center" key="templateId" prop="templateId"/>
                    <el-table-column label="活动编号" align="center" key="activityId" prop="activityId"/>
                    <el-table-column label="发送人" align="left" key="sentBy" prop="sentBy"/>
                    <el-table-column label="接收人" align="left" key="sentTo" prop="sentTo"/>
                    <el-table-column label="发送状态" align="left" prop="status"/>

                    <el-table-column label="发送时间" align="center" width="160">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.sendAt) }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            label="操作"
                            align="center"
                            width="230"
                            class-name="small-padding fixed-width"
                    >
                        <template slot-scope="scope">
                            <el-button
                                    size="mini"
                                    type="text"
                                    icon="el-icon-edit"
                                    @click="handleUpdate22(scope.row)"
                            >修改
                            </el-button>
                            <el-button
                                    size="mini"
                                    type="text"
                                    icon="el-icon-delete"
                                    @click="handleDelete22(scope.row)"
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
        <el-dialog title="发送通知" :visible.sync="open" width="620px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="110px">
                <el-row>
                    <el-col>
                        <el-form-item label="请选择模板" prop="templateId">
                            <el-select v-model="form.templateId" placeholder="请选择模板">
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
                        <el-form-item label="请选择活动" prop="activityId">
                            <el-select v-model="form.activityId" placeholder="请选择活动">
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
                        <el-form-item label="请选择接收者" prop="sentTo">
                            <el-select v-model="form.activityId" placeholder="请选择接收者">
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
                        <el-form-item label="通知内容">
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
    import {getall} from "@/api/system/notices";
    import {addActivities,listTemplate, listActivities,delActivities,listParticipant,applyParticipant,rejectParticipant} from "@/api/system/activities";


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
                actList: [{"templateId":"123456","activityId":"654321","sentBy":"111","sentTo":"222","status":"已发送","sendAt":"2024-06-06 23:23:14"}],
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
                },
                participantQueryParams: {
                    pageNum: 1,
                    pageSize: 10,
                    activityId: ''
                },
                // 表单校验
                rules: {
                    activityId: [
                        {required: true,trigger: "blur"},
                    ],
                    sentTo: [
                        {required: true, message: "用户不能为空", trigger: "blur"}
                    ],
                    templateId: [
                        {required: true,trigger: "blur"},
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
            addNotification() {
                this.reset();
                this.open = true;
                // this.getTemplateList();
            },
            setRoles() {

            },
            /** 查询用户列表 */
            getList() {
                this.loading = false;
                /*this.loading = true;
                getall(0).then(res => {
                        this.actList = res.data.records;
                        this.total = res.data.total;
                        this.loading = false;
                    }
                ).catch(()=>{
                    this.loading = false;
                });*/
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
                this.reset();
                const activityId = row.activityId || this.ids;
                getActivities(activityId).then(response => {
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
                            this.form.startTime=this.dateRange.beginTime;
                            this.form.endTime=this.dateRange.endTime;
                            updateaActivities(this.form).then(res => {
                                this.$modal.msgSuccess("修改成功");
                                this.open = false;
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
                const userIds = row.activityId;
                this.$modal.confirm('是否确认删除"' + userIds + '"的数据项？').then(function () {
                    return delActivities(userIds);
                }).then(() => {
                    this.getList();
                    this.$modal.msgSuccess("删除成功");
                }).catch(() => {
                });
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
