<template>
  <div class="page">
    <div class="userinfo" @click="bindViewTap">
      <img class="userinfo-avatar" v-if="userInfo.avatarUrl" :src="userInfo.avatarUrl" background-size="cover" />
      <div class="userinfo-nickname">
        <card :text="userInfo.nickName"></card>
      </div>
    </div>
    <div class="weui-cells weui-cells_after-title">
      <div class="weui-cell weui-cell_input">
        <div class="weui-cell__hd"><div class="weui-label">病历号</div></div>
        <div class="weui-cell__bd">
          <input class="weui-input" type="number" pattern="[0-9]*" placeholder="请输入病历号"/>
        </div>
      </div>
      <div class="weui-cell weui-cell_input">
        <div class="weui-cell__hd"><div class="weui-label">姓名</div></div>
        <div class="weui-cell__bd">
          <input class="weui-input" type="text" placeholder="请输入姓名"/>
        </div>
      </div>
    </div>
    <button type="primary" class="weui-btn">登录</button>
    <button open-type="contact" >fff</button>
  </div>
</template>

<script>
import card from '@/components/card'

export default {
  data () {
    return {
      blh: '',
      name: '',
      userInfo: {}
    }
  },

  components: {
    card
  },

  methods: {
    getUserInfo () {
      // 调用登录接口
      wx.checkSession({
        success: () => {

        }
      })
      wx.login({
        success: (res) => {
          wx.request({
            url: 'http://localhost/wechat/login',
            data: {
              code: res.code
            },
            success: (res) => {
            }
          })
          wx.getUserInfo({
            withCredentials: true,
            success: (res) => {
              this.userInfo = res.userInfo
            }
          })
        }
      })
    }
  },

  created () {
    // 调用应用实例的方法获取全局数据
    this.getUserInfo()
  }
}
</script>

<style scoped>
.userinfo {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.userinfo-avatar {
  width: 128rpx;
  height: 128rpx;
  margin: 20rpx;
  border-radius: 50%;
}

.userinfo-nickname {
  color: #aaa;
}

/*.form-control {*/
  /*display: block;*/
  /*padding: 0 12px;*/
  /*margin-bottom: 5px;*/
  /*border: 1px solid #ccc;*/
/*}*/

/*.counter {*/
  /*display: inline-block;*/
  /*margin: 10px auto;*/
  /*padding: 5px 10px;*/
  /*color: blue;*/
  /*border: 1px solid blue;*/
/*}*/
</style>
