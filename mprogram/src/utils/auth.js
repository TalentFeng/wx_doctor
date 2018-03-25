import {wxRequest} from './request'

export function authRequest (url = '', config = {}) {
  return new Promise((resolve, reject) => {
    wx.checkSession({
      fail: () => {
        wx.login({
          success: (res) => {
            wxRequest(process.env.BASE_API + '/wechat/login', {
              data: {
                code: res.code
              },
              method: 'GET'
            }).then((res) => {
              if (url === '') {
                wx.redirectTo({
                  url: res
                })
              } else {
                wxRequest(process.env.BASE_API + url, config).then(res => resolve(res))
              }
            }).catch(err => {
              reject(err)
            })
          },
          fail: (err) => {
            reject(err)
          }
        })
      },
      success: () => {
        wxRequest(process.env.BASE_API + url, config).then(res => resolve(res))
      }}
    )
  })
}

