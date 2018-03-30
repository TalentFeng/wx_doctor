import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false
App.mpType = 'app'
import '../static/weui/weui.css'

const app = new Vue(App)
app.$mount()

export default {
  // 这个字段走 app.json
  config: {
    // 页面前带有 ^ 符号的，会被编译成首页，其他页面可以选填，我们会自动把 webpack entry 里面的入口页面加进去
    pages: [
      '^pages/login/login',
      'pages/counter/counter'
    ],
    window: {
      backgroundTextStyle: 'light',
      navigationBarBackgroundColor: '#fff',
      navigationBarTitleText: '线上微医',
      navigationBarTextStyle: 'black'
    },
    tabBar: {
      'selectedColor': '#4ddb6f',
      'list': [{
        'pagePath': 'pages/index/index',
        'text': '首页'
      }, {
      //   'pagePath': 'pages/counter/counter',
      //   'text': '我的问题'
      // }, {
        'pagePath': 'pages/login/login',
        'text': '用户中心'
      }]
    }

  }
}
