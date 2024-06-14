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
            <el-col v-if="actList.length>0" :lg="8" :xs="24" :md="8" :key="index" v-for="(item,index) in actList">
                <el-card shadow="hover" style="margin: 10px;padding:1px;">
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
                        <div class="bottom">
                            <span>活动状态:</span>
                            <span>{{item.status}}</span>
                        </div>
                        <div style="margin: 25px 0px 0px 0px;text-align: center">
                            <el-button type="primary" size="small" @click="showDetail(item)">查看详情</el-button>
                            <el-button type="success" size="small" @click="openShare(item)">分享活动</el-button>
                        </div>
                    </div>
                </el-card>
            </el-col>
            <div v-else>
                暂无活动!
            </div>
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
                <el-button type="success" @click="toEvaluate(activity)" v-if='joined=="Y"'>评 价</el-button>
                <el-button type="warning"  @click.prevent="openChat()"  v-if='joined=="Y"'>进入聊天室</el-button>
                <!--@click="toChat(activity)"-->
                <el-button type="primary" @click="joinActivity(activity)" v-if='joined=="N"'>加 入</el-button>
                <el-button @click="cancel">关 闭</el-button>
            </div>
        </el-dialog>

        <el-dialog title="分享活动" :visible.sync="shareFlg" width="420px" append-to-body @close="shareFlg=false">
            <el-card>
                <img :src="shareImg" style="width: 100%"/>
            </el-card>
        </el-dialog>

        <el-dialog title="评价" :visible.sync="showEvaluateFlg" width="1000px" append-to-body>
                <h2>{{ activity.activityName }}</h2>
                <p>{{ activity.description }}</p>
                <ul>
                    <li v-for="comment in comments" :key="comment.id">
                        <p>{{ comment.content }}</p>
                        <button @click="showReplyForm(comment.id)">回复</button>
                        <form v-if="replyFormOpen[comment.id]" @submit.prevent="submitReply(comment.id)">
                            <input type="text" v-model="replyContent">
                            <button type="submit">提交回复</button>
                        </form>
                        <ul>
                            <li v-for="reply in comment.replies" :key="reply.id">
                                <p>{{ reply.content }}</p>
                            </li>
                        </ul>
                    </li>
                </ul>
            <el-row>
                <el-form ref="form" label-width="110px">
                    <el-row>
                        <el-col>
                            <el-input type="textarea" rows="5" v-model="evaluate" placeholder="欢迎评论"/>
                        </el-col>
                    </el-row>
                </el-form>

            </el-row>

            <div slot="footer" class="dialog-footer">
                <el-button type="success" @click="submitEvaluate(activity)" v-if='joined=="Y"'>提交</el-button>
                <el-button @click="closeEvaluate">关 闭</el-button>
            </div>

        </el-dialog>

        <div>
            <beautiful-chat
                    :participants="participants"
                    :agentProfile="agentProfile"
                    :onMessageWasSent="onMessageWasSent"
                    :messageList="messageList"
                    :newMessagesCount="newMessagesCount"
                    :isOpen="isChatOpen"
                    :colors="colors"
                    :close="closeChat"
                    :open="openChat"
                    :showEmoji="true"
                    :showFile="true"
                    :showEdition="true"
                    :showTypingIndicator="showTypingIndicator"
                    :alwaysScrollToBottom="false"
                    :disableUserListToggle="false"
            />

        </div>
    </div>
</template>

<script>
    import {listActivities, applyActivities, generateQRForSharing, joined} from '@/api/system/activities';

    export default {
        name: "Index",
        data() {
            return {
                shareImg: "",
                showDetailFlg: false,
                shareFlg: false,
                activity:{},
                actList: [],
                searchForm: {
                    pageNum: 1,
                    pageSize: 10,
                    type: 0,
                    name: '',
                },
                joined: 'N',
                showEvaluateFlg: false,
                showChatFlg: false,
                evaluate: '',

                comments: [
                    {
                        id: 1,
                        content: '我对这个话题很感兴趣！可以详细聊一聊吗？',
                        replies: []
                    },
                    {
                        id: 2,
                        content: '关于你的活动我有一些想法和建议',
                        replies: [
                            {
                                id: 1,
                                content: '支持积极提出意见'
                            }
                        ]
                    }
                ],
                commentContent: '',
                replyContent: '',
                replyFormOpen: {},


                participants: [
                    {
                        id: 'user1',
                        name: 'zhangzhenghao',
                        imageUrl: 'https://avatars3.githubusercontent.com/u/37018832?s=200&v=4'
                    },
                    {
                        id: 'user2',
                        name: 'wzq',
                        imageUrl: 'https://avatars3.githubusercontent.com/u/37018832?s=200&v=4'
                    },
                    {
                    id: 'user3',
                    name: 'zhangsan',
                    imageUrl: 'https://avatars3.githubusercontent.com/u/37018832?s=200&v=4'
                    },
                    {
                    id: 'user4',
                    name: 'Tony',
                    imageUrl: 'https://avatars3.githubusercontent.com/u/37018832?s=200&v=4'
                    },
                ], // 对话的所有参与者的列表。' name '是用户名，' id '用于建立消息的作者，' imageUrl '应该是用户头像。
                agentProfile: {
                    teamName: '游戏课',
                    imageUrl: 'https://a.slack-edge.com/66f9/img/avatars-teams/ava_0001-34.png'
                },
                messageList: [],
                newMessagesCount: 0,
                isChatOpen: false,
                showTypingIndicator: '',
                colors: {
                    header: {
                        bg: '#4e8cff',
                        text: '#ffffff'
                    },
                    launcher: {
                        bg: '#4e8cff'
                    },
                    messageList: {
                        bg: '#ffffff'
                    },
                    sentMessage: {
                        bg: '#4e8cff',
                        text: '#ffffff'
                    },
                    receivedMessage: {
                        bg: '#eaeaea',
                        text: '#222222'
                    },
                    userInput: {
                        bg: '#f4f7f9',
                        text: '#565867'
                    }
                },
            }
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
            toEvaluate(){
                this.showDetailFlg = false;
                this.showEvaluateFlg = true;
            },
            closeEvaluate(){
                this.showEvaluateFlg = false;
            },
            submitEvaluate(){
                this.$modal.msgSuccess("评价成功");
                this.showEvaluateFlg = false;
            },
            toChat(){
                this.showDetailFlg = false;
                this.showChatFlg = true;

            },
            showDetail(item){
                console.log('item', item);
                this.joined = "N";
                this.showDetailFlg = true;
                this.activity = item;
                joined(this.activity.activityId).then(res => {
                    if (res.code == 200) {
                        this.joined = res.data;
                    }
                });

            },
            openShare(item){
                this.shareFlg = true;
                generateQRForSharing(item.activityId).then(res => {
                    this.shareImg= "data:image/jpeg;base64,"+res;
                });

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

            //chat start
            sendMessage (msg) {
                if (text.length > 0) {
                    this.newMessagesCount = this.isChatOpen ? this.newMessagesCount : this.newMessagesCount + 1
                    this.messageList.push(msg)
                }
            },
            onMessageWasSent (msg) {
                this.messageList.push(msg)
            },
            openChat () {
                this.isChatOpen = true
                this.newMessagesCount = 0
            },
            closeChat () {
                this.isChatOpen = false
            }


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


