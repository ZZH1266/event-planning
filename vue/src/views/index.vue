<template>
    <div class="app-container home">
        <el-row>
            <el-col>
                <div style="padding-bottom: 5px;">
                    <el-input type="text" v-model="searchForm.name" placeholder="请输入搜索内容" style="width: 50%" size="small" :clearable="true" @change="getList"></el-input>
                    <el-button style="margin-left: 10px;" type="primary" size="small" @click="seachList">搜索</el-button>

                    <el-radio-group style="margin-left: 10px;" size="small" v-model="searchForm.type" type="button" @change="seachList">
                        <el-radio-button label="2">我加入的</el-radio-button>
                        <el-radio-button label="1">我创建的</el-radio-button>
                        <el-radio-button label="0">所有活动</el-radio-button>
                    </el-radio-group>
                </div>
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
                        <h2 align="center">{{item.activityName}}</h2>
                        <div class="bottom clearfix">
                            <span>开始时间:</span>
                            <span><time class="time">{{ item.startTime }}</time></span>
                        </div>
                        <div class="bottom">
                            <span>结束时间:</span>
                            <span><time class="time">{{item.endTime}}</time></span>
                        </div>
                        <div class="bottom">
                            <span>活动地点:</span>
                            <span>{{item.address}}</span>
                        </div>
                        <div style="padding: 5px 0px;text-align: right">
                            <el-button type="primary" size="small" @click="showDetail(item)">查看详情</el-button>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>


        <el-dialog title="活动详情" :visible.sync="showDetailFlg" width="620px" append-to-body>
            <el-form ref="form" label-width="110px">
                <el-row>
                    <el-col>
                        <el-form-item label="活动名称">
                            <el-input v-model="activity.activityName" disabled/>
                        </el-form-item>
                    </el-col>
                    <el-form-item label="活动时间">
                        <el-col :span="11">
                            <el-input v-model="activity.startTime" style="width: 100%;" disabled/>
                        </el-col>
                        <el-col class="line" :span="2">-</el-col>
                        <el-col :span="11">
                            <el-input v-model="activity.endTime" style="width: 100%;" disabled/>
                        </el-col>
                    </el-form-item>
                    <el-col>
                        <el-form-item label="活动地址">
                            <el-input v-model="activity.address" disabled/>
                        </el-form-item>
                    </el-col>
                    <el-col>
                        <el-form-item label="活动简介">
                            <el-input type="textarea" rows="5" v-model="activity.description" disabled/>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="joinActivity(activity)">加 入</el-button>
                <el-button @click="cancel">关 闭</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import {listActivities,applyActivities } from '@/api/system/activities';

    export default {
        name: "Index",
        data() {
            return {
                showDetailFlg: false,
                activity:{},
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
                this.activity = item;
            },
            seachList() {
                this.searchForm.pageNum = 1;
                this.getList();
            },
            getList() {
                listActivities(this.searchForm).then(res => {
                    if (res.data && res.data.records.length > 0) {
                        this.actList = res.data.records;
                    } else {
                        this.actList = [];
                    }
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


