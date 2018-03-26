import {wxRequest} from './request'

export function authRequest (url = '', config = {}) {
  return new Promise((resolve, reject) => {
    if (url === '') {
      wx.login({
        success: (res) => {
          wxRequest(process.env.BASE_API + '/wechat/login', {
            data: {
              code: res.code
            },
            method: 'GET'
          }).then((res) => {
            resolve()
          })
        },
        fail: (err) => {
          reject(err)
        }
      })
    } else {
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
                wxRequest(process.env.BASE_API + url, config).then(res => resolve(res))
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
        }
      }
      )
    }
  })
}

