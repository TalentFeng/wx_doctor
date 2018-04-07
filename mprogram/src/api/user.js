import {authRequest} from '../utils/auth'
import {wxRequest} from '../utils/request'

export function getUserInfo () {
  return new Promise((resolve, reject) => {
    wx.getUserInfo({
      withCredentials: true,
      success: (res) => {
        resolve(res)
      },
      fail: (err) => {
        reject(err)
      }
    })
  })
}

export function saveUserInfo (userInfo) {
  return authRequest('/user/save', {data: userInfo})
}

export function login () {
  return new Promise((resolve, reject) => {
    wx.login({
      success: (res) => {
        wxRequest(process.env.BASE_API + '/wechat/login', {
          data: {
            code: res.code
          },
          method: 'GET'
        }).then((res) => {
          wx.setStorageSync('userinfo', res)
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      },
      fail: (err) => {
        reject(err)
      }
    }
  )
  })
}
