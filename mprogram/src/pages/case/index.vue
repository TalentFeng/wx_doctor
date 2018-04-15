<template>
  <div class="page">
    <div class="weui-panel weui-panel_access">
      <div class="weui-panel__hd">
        病历详情
      </div>
      <div class="weui-panel__bd" v-for="item in cases" :key="item.id">
        <div class="weui-media-box weui-media-box_text">
          <h4 class="weui-media-box__title"><a>{{item.inspection_item}}</a></h4>
          <p class="weui-media-box__desc">{{item.result}}</p>
          <ul class="weui-media-box__info">
            <li class="weui-media-box__info__meta">报告医生: {{item.report_doctor}}</li>
            <li class="weui-media-box__info__meta">报告时间:{{item.rep_date}}</li>
            <li class="weui-media-box__info__meta weui-media-box__info__meta_extra"></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import card from '@/components/card'

  import {getCases} from '@/api/user'

  export default {
    data () {
      return {
        id: 0,
        cases: []
      }
    },

    components: {
      card
    },

    methods: {
      load () {
        let vm = this
        getCases({id: this.id}).then(res => {
          vm.cases = res.data
        })
      }
    },

    onLoad () {
      this.id = this.$mp.query.id
      this.load()
    }
  }
</script>

<style scoped>
</style>
