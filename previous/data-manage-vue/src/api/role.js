import request from '@/utils/request'

export function getRoleListByConditionAndPage(query) {
  return request({
    url: '/role/rolelistbyconditionandpage',
    method: 'get',
    params: query
  })
}

export function getRoleListByCondition(query) {
  return request({
    url: '/role/rolelistbycondition',
    method: 'get',
    params: query
  })
}

export function getRoleListByEmployeeId(employeeId) {
  return request({
    url: '/role/rolelistbyemployeeid',
    method: 'get',
    params: {employeeId}
  })
}

export function editRole(data) {
  return request({
    url: '/role/editrole',
    method: 'post',
    data:data
  })
}

export function editRoleResource(data) {
  return request({
    url: '/role/editroleresource',
    method: 'post',
    data:data
  })
}

export function deleteRoleById(id) {
  return request({
    url: '/role/deleterolebyid',
    method: 'post',
    params:{id}
  })
}
