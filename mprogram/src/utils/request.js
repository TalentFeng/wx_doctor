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
        'Cookie': wx.getStorageSync('cookie')[0]
      },
      success: (res) => {
        if (res.header['Set-Cookie']) { wx.setStorageSync('cookie', res.header['Set-Cookie']) }
        resolve(res)
      },
      fail: (err) => reject(err),
      complete: () => {
        wx.hideLoading()
      }
    })
  })
}

