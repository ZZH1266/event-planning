<template>
  <div class="app-container home">
    <el-row type="flex" justify="start" style="flex-wrap: wrap;max-width: 100%;margin: 0 auto;">
      <el-col :lg="8" :xs="24" :md="8" :key="index" v-for="(item,index) in actList">
        <el-card  shadow="hover" style="margin: 10px;padding:5px;">
            <img :src="item.img" class="image">
            <div style="padding: 14px;">
              <span>{{item.activityName}}</span>
              <div class="bottom clearfix">
                <time class="time">{{ item.startTime }}~{{item.endTime}}</time>
                <el-button type="primary" class="button">分享</el-button>
              </div>
            </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {listActivities} from '@/api/system/activities';

  export default {
  name: "Index",
  data() {
    return {
      actList: [
              {"activityId":"1122","activityName":"北京马拉松分赛","description":"活动描述","startTime":"开始时间","endTime":"结束时间","img":"https://img0.baidu.com/it/u=660283424,3951643735&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"},
              {"activityId":"334353","activityName":"活动名re称1","description":"活动描述","startTime":"开始时间","endTime":"结束时间","img":"https://img1.baidu.com/it/u=526391263,3715006622&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"},
              {"activityId":"3452","activityName":"活动名称1","description":"活动描述","startTime":"开始时间","endTime":"结束时间","img":"https://img1.baidu.com/it/u=3413131606,2893606853&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"},
              {"activityId":"5466464","activityName":"活动rer名称1","description":"活动描述","startTime":"开始时间","endTime":"结束时间","img":"https://img2.baidu.com/it/u=3529267642,2320719589&fm=253&fmt=auto&app=120&f=JPEG?w=640&h=356"},
      ],
      // 版本号
      version: "3.8.7"
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      listActivities().then(res => {
        if (res.data && res.data.length > 0) {
          this.actList = res.data;
        }
      });
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


