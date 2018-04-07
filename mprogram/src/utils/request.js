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
        'content-type': contentType,
        'Cookie': wx.getStorageSync('cookie')
      },
      success: (res) => {
        if (res.header['Set-Cookie']) {
          wx.setStorageSync('cookie', res.header['Set-Cookie'])
          wx.reLaunch({
            url: '/pages/login/login'
          })
        }
        resolve(res)
      },
      fail: (err) => {
        wx.showToast({
          title: '请求失败',
          icon: 'loading',
          duration: 2000
        })
        reject(err)
      },
      complete: () => {
        wx.hideLoading()
      }
    })
  })
}

