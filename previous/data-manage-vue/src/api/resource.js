import request from '@/utils/request'

export function getResourceListByCondition(query) {
  return request({
    url: '/resource/resourcelistbycondition',
    method: 'get',
    params: query
  })
}
export function getResourceIdListByRoleId(roleId) {
  return request({
    url: '/resource/resourceidlistbyroleid',
    method: 'get',
    params: {roleId}
  })
}
export function editResource(data) {
  return request({
    url: '/resource/editresource',
    method: 'post',
    data:data
  })
}
export function deleteResourceById(id) {
  return request({
    url: '/resource/deleteresourcebyid',
    method: 'post',
    params:{id}
  })
}
