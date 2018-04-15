<template>
  <div class="page">
    <div class="weui-panel weui-panel_access">
      <div class="weui-panel__hd">
        问题
      </div>
      <div class="weui-panel__bd">
        <div class="weui-media-box weui-media-box_text" @click="showCase(question.name)">
          <h4 class="weui-media-box__title"><a>{{question.title}}</a></h4>
          <p class="weui-media-box__desc">{{question.content}}</p>
          <ul class="weui-media-box__info">
            <li class="weui-media-box__info__meta">提问者: {{question.name}}</li>
            <li class="weui-media-box__info__meta">时间:{{question.createdTime}}</li>
            <li class="weui-media-box__info__meta weui-media-box__info__meta_extra"></li>
          </ul>
        </div>
      </div>
      <div class="weui-panel__hd">
        回复列表
      </div>
      <div class="weui-panel__bd">
        <div class="weui-media-box weui-media-box_text" v-for="item in answer" :key="id">
          <h4 class="weui-media-box__title"><a>{{item.content}}</a></h4>
          <ul class="weui-media-box__info">
            <li class="weui-media-box__info__meta">回复者: {{item.name}}</li>
            <li class="weui-media-box__info__meta">时间:{{item.createdTime}}</li>
            <li class="weui-media-box__info__meta weui-media-box__info__meta_extra"></li>
          </ul>
        </div>
      </div>
    </div>
    <div class="weui-cells__title">回复</div>
    <div class="weui-cells weui-cells_form">
      <div class="weui-cell">
        <div class="weui-cell__hd">
        </div>
        <div class="weui-cell__bd">
          <textarea class="weui-textarea" v-model.lazy="form.answer.content" type="text" placeholder="请输入回复内容">
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
  import {get} from '@/api/question'
  import {add} from '@/api/answer'
  export default {
    data () {
      return {
        id: 0,
        question: {
          title: '',
          content: '',
          name: '',
          createdTime: ''
        },
        answer: [],
        form: {
          answer: {
            content: '',
            qid: ''
          }
        }
      }
    },
    onLoad () {
      this.id = this.$mp.query.id
      this.load()
    },
    methods: {
      load () {
        let vm = this
        get({id: vm.id}).then(({data}) => {
          data = data.data
          vm.form.answer.qid = vm.id
          vm.question = data.question
          vm.answer = data.answer
        })
      },
      save () {
        let vm = this
        add(this.form.answer).then(_ => {
          vm.form.answer.content = ''
          vm.load()
        })
      },
      navigateTo (url) {
        wx.navigateTo({url: `/pages/${url}`})
      },
      showCase (id) {
        this.navigateTo('case/case?id=' + this.question.createdUser)
      }
    }

  }
</script>

<style scoped>
</style>
