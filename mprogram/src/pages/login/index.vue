<template>
  <div class="page">
    <div class="userinfo">
      <img class="userinfo-avatar"
           :src="userInfo.avatarUrl? userInfo.avatarUrl : defaultImg"
           background-size="cover" />
      <div class="userinfo-nickname">
        <card :text="userInfo.nickName"></card>
      </div>
    </div>
    <div class="weui-cells weui-cells_after-title">
      <div class="weui-cell weui-cell_input">
        <div class="weui-cell__hd"><div class="weui-label">病历号</div></div>
        <div class="weui-cell__bd">
          <input class="weui-input" type="number" pattern="[0-9]*" :disabled="isBind" v-model="userInfo.blh" placeholder="请输入病历号"/>
        </div>
      </div>
      <div class="weui-cell weui-cell_input">
        <div class="weui-cell__hd"><div class="weui-label">姓名</div></div>
        <div class="weui-cell__bd">
          <input class="weui-input" type="text" v-model="userInfo.name" :disabled="isBind" placeholder="请输入姓名"/>
        </div>
      </div>
    </div>
    <button type="warn" v-if="isBind" class="weui-btn" @click="unbind" >解除绑定</button>
    <button type="primary" v-else class="weui-btn" @click="bind">绑定</button>
  </div>
</template>

<script>
  import card from '@/components/card'
  import {getUserInfo, saveUserInfo, login, bindBlh} from '@/api/user'

  export default {
    data () {
      return {
        defaultImg: 'data:image/jpg;base64,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',
        userInfo: {},
        isBind: false
      }
    },

    components: {
      card
    },

    methods: {
      bind () {
        let vm = this
        bindBlh(this.userInfo.name, this.userInfo.blh).then(res => {
          if (res.data === false) {
            wx.showToast({
              title: '绑定失败',
              icon: 'loading',
              duration: 2000
            })
          } else {
            vm.login()
          }
        })
      },
      unbind () {
        this.isBind = false
      },
      login () {
        let vm = this
        login().then(({data}) => {
          saveUserInfo(vm.userInfo)
          if (data.blh !== undefined && data.name !== undefined) {
            vm.isBind = true
            vm.userInfo.name = data.name
            vm.userInfo.blh = data.blh
          }
        })
      }
    },

    mounted () {
      let vm = this
//      wx.chooseImage({
//        count: 1, // 默认9
//        sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
//        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
//        success: function (res) {
//          // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
//          wx.uploadFile({
//            url: 'http://localhost:8080/user/upload',
//            filePath: res.tempFilePaths[0],
//            name: 'file'
//          })
//        }
//      })
      vm.isBind = false
      // 调用应用实例的方法获取全局数据
      getUserInfo().then((res) => {
        vm.userInfo = Object.assign(res.userInfo, vm.userInfo)
      }).finally(() => {
        vm.login()
      })
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
