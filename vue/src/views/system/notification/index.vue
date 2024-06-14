<!--查看通知-->
<template>
    <div class="app-container home">
        <el-row>
            <el-col>
                <h2>我的通知</h2>
                <!--<div style="padding-bottom: 5px;">
                    <el-input type="text" v-model="searchForm.name" placeholder="请输入搜索内容" style="width: 50%" size="small" :clearable="true" @change="getList"></el-input>
                    <el-button style="margin-left: 10px;" type="primary" size="small" @click="seachList">搜索</el-button>
                </div>-->
            </el-col>
        </el-row>
        <el-row>
            <el-col>

            </el-col>
        </el-row>
        <el-row type="flex" justify="start" style="flex-wrap: wrap;max-width: 100%;margin: 0 auto;">
            <el-col :lg="8" :xs="24" :md="8" :key="index" v-for="(item,index) in actList">
                <el-card shadow="hover" style="margin: 10px;padding:5px;">
                    <div style="padding: 14px;display: flex;justify-content: center;flex-direction: column">

                        <div class="bottom">
                            <span>活动ID:</span>
                            <span><time class="time">{{ item.activityId }}</time></span>
                        </div>

                        <div class="bottom">
                            <span>通知时间:</span>
                            <span><time class="time">{{ item.sentAt }}</time></span>
                        </div>
                        <div class="bottom">
                            <span>状态:</span>
                            <span><time class="time">{{item.status}}</time></span>
                        </div>

                        <div style="padding: 5px 0px;text-align: right">
                            <el-button type="primary" size="small" @click="showDetail(item)">查看详情</el-button>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>


        <el-dialog title="查看通知" :visible.sync="showDetailFlg" width="620px" append-to-body>
            <el-form ref="form" label-width="110px">
                <el-row>
                    <el-col>
                        <el-form-item label="活动ID">
                            <el-input v-model="notice.activityId" disabled/>
                        </el-form-item>
                    </el-col>
                    <el-form-item label="发送时间">
                            <el-input v-model="notice.sentAt" style="width: 100%;" disabled/>
                    </el-form-item>
                    <el-col>
                        <el-form-item label="状态">
                            <el-input v-model="notice.status" disabled/>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer" align="center">
                <el-button type="primary" @click="joinActivity(notice)">接 受</el-button>
                <el-button @click="cancel">取 消</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import {getall,viewnotice} from '@/api/system/notices';

    export default {
        name: "Index",
        data() {
            return {
                showDetailFlg: false,
                notice:{},
                actList: [],
                searchForm: {
                    pageNum: 1,
                    pageSize: 10,
                    type: 0,
                    name: '',
                },
            };
        },
        created() {
            this.getList();
        },
        methods: {
            joinActivity(item) {
                console.log('join activity...', item.activityId);
                applyActivities(item.activityId).then(res => {
                    console.log('res is ', res);
                    if (res.data) {
                        this.$message.success("加入成功!");
                        this.showDetailFlg = false;
                        this.getList();
                    }
                });

            },
            cancel() {
                this.showDetailFlg = false;
            },
            showDetail(item){
                console.log('item', item);
                this.showDetailFlg = true;
                this.notice = item;
            },
            seachList() {
                this.searchForm.pageNum = 1;
                this.getList();
            },
            getList() {
                getall().then(res => {
                    console.log('res', res);
                    if (res.data && res.data.length > 0) {
                        this.actList = res.data;
                    } else {
                        this.actList = [];
                    }
                }).catch(err=>{
                    console.log(err);
                });
            },

        }
    };
</script>

<style scoped lang="scss">
    .time {
        font-size: 13px;
        color: #999;
    }

    .bottom {
        margin-top: 13px;
        line-height: 20px;
    }

    .button {
        padding: 5px;
        float: right;
    }

    .image {
        max-width: 100%;
        max-height: 400px;
        display: block;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }

    .clearfix:after {
        clear: both
    }
</style>


