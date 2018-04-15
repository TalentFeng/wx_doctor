import {authRequest} from '../utils/auth'

export function list (param) {
  return authRequest('/answer/list', {data: param})
}

export function add (param) {
  return authRequest('/answer/add', {data: param})
}
