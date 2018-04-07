<template>
  <div class="page">
    <div class="weui-cells__title">提问</div>
    <div class="weui-cells weui-cells_form">
      <div class="weui-cell">
        <div class="weui-cell__hd"></div>
        <div class="weui-cell__bd">
          <input class="weui-input" type="text" v-model.lazy="form.question.title" placeholder="请输入标题"/>
        </div>
      </div>
      <div class="weui-cell">
        <div class="weui-cell__hd">
        </div>
        <div class="weui-cell__bd">
          <textarea class="weui-textarea" v-model.lazy="form.question.content" type="text" placeholder="请输入问题">
          </textarea>
        </div>
      </div>
      <div class="weui-cell">
        <div class="weui-cell__hd">
        </div>
        <div class="weui-cell__bd">
          <button type="primary" @click="save">
            提交
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import {add} from '@/api/question'
  export default {
    data () {
      return {
        form: {
          question: {
            title: '',
            content: ''
          }
        }
      }
    },

    methods: {
      save () {
        add(this.form.question).then(_ => {
          wx.switchTab({url: '/pages/index/index'})
        })
      }
    }

  }
</script>

<style scoped>
</style>
