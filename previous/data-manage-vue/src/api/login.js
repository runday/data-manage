import request from '@/utils/request'
export function login(account, passWord) {
  return request({
    url: '/login',
    method: 'post',
    params:{ 'account': account, 'passWord': passWord}
  })
}
