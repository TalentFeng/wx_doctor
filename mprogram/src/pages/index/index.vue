<template>
  <div class="page">
    <div class="weui-panel weui-panel_access">
      <div class="weui-panel__hd"><button @click="navigateTo('question/question')">提问</button></div>
      <div class="weui-panel__hd">
        问题列表
      </div>
      <div class="weui-panel__bd" v-for="question in list" :key="question.id" @click="showQuestionDetail(question.id)">
        <div class="weui-media-box weui-media-box_text">
          <h4 class="weui-media-box__title"><a>{{question.title}}</a></h4>
          <p class="weui-media-box__desc">{{question.content}}</p>
          <ul class="weui-media-box__info">
            <li class="weui-media-box__info__meta">提问者: {{question.name}}</li>
            <li class="weui-media-box__info__meta">时间:{{question.createdTime}}</li>
            <li class="weui-media-box__info__meta weui-media-box__info__meta_extra"></li>
          </ul>
        </div>
      </div>
      <div class="weui-panel__ft" v-if="hasMore">
        <a href="javascript:void(0);" class="weui-cell weui-cell_access weui-cell_link">
          <div class="weui-cell__bd">查看更多</div>
          <span class="weui-cell__ft"></span>
        </a>
      </div>
    </div>
  </div>
</template>

<script>
  import card from '@/components/card'

  import {list} from '@/api/question'

  export default {
    data () {
      return {
        list: [],
        hasMore: false
      }
    },

    components: {
      card
    },

    methods: {
      navigateTo (url) {
        wx.navigateTo({url: `/pages/${url}`})
      },
      showMore () {
        let vm = this
        list({start: vm.list.length}).then(res => {
          vm.list = vm.list.concat(res.data)
          if (res.data.length < 20) {
            vm.hasMore = false
          } else {
            vm.hasMore = true
          }
        })
      },
      showQuestionDetail (id) {
        this.navigateTo('questionDetail/questionDetail?id=' + id)
      }
    },
    onShow () {
      this.showMore()
    }
  }
</script>

<style scoped>
</style>
