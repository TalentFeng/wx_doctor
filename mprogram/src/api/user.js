import {authRequest} from '../utils/auth'

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
  return new Promise(() => {
    authRequest('/user/save', {data: userInfo})
  })
}
