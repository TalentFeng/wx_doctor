<template>
  <div class="page">
    <div class="weui-panel weui-panel_access">
      <div class="weui-panel__hd">
        病历详情
      </div>
      <div class="weui-panel__bd" v-for="item in cases" :key="item.id">
        <div class="weui-media-box weui-media-box_text">
          <h4 class="weui-media-box__title" v-for="(val, name) in item" :key="name" v-if="name != 'id'">{{name}}: {{val}}</h4>
          <ul class="weui-media-box__info">
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
