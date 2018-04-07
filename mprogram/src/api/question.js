import {authRequest} from '../utils/auth'

export function add (question) {
  return authRequest('/question/add', {data: question})
}

export function list (param) {
  return authRequest('/question/list', {data: param})
}
