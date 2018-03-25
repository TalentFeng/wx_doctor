export function wxRequest (url, config) {
  return new Promise((resolve, reject) => {
    let {
      data = {},
      contentType = 'application/json',
      method = 'POST'
      // ...other
    } = {...config}
    wx.showLoading({
      title: '正在加载中'
    })
    wx.request({
      url: url,
      data: {...data},
      method: method,
      header: {
        'content-type': contentType
        // 'Cookie': app.globalData.cookie  // 全局变量中获取 cookie
      },
      success: (data) => resolve(data),
      fail: (err) => reject(err),
      complete: () => {
        wx.hideLoading()
      }
    })
  })
}

