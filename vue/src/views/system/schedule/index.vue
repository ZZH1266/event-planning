<template>
    <div class="app-container home">


        <el-row type="flex" justify="start" style="flex-wrap: wrap;max-width: 100%;margin: 0 auto;">

            <el-col v-if="actList.length>0" :lg="8" :xs="24" :md="8">
                <el-timeline>
                    <el-timeline-item :timestamp="item.startTime" placement="top" :key="index" v-for="(item,index) in actList">
                        <el-card>
                            <h4>{{item.activityName}}</h4>
                            <p>{{item.description}}</p>
                        </el-card>
                    </el-timeline-item>
                </el-timeline>
            </el-col>
            <div v-else>
                暂无活动!
            </div>
        </el-row>

    </div>
</template>

<script>
    import {listActivities, applyActivities, generateQRForSharing, joined} from '@/api/system/activities';

    export default {
        name: "Index",
        data() {
            return {
                activity:{},
                actList: [],
                searchForm: {
                    pageNum: 1,
                    pageSize: 10,
                    type: 2,
                    name: '',
                },
                joined: 'N',
                showEvaluateFlg: false,
                showChatFlg: false,
            }
        },
        created() {
            this.getList();
        },
        methods: {
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


