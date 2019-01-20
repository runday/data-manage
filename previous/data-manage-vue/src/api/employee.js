import request from '@/utils/request'

export function getEmployeeListByConditionAndPage(query) {
  return request({
    url: '/employee/employeelistbyconditionandpage',
    method: 'get',
    params: query
  })
}
export function getEmployeeInfo(id) {
  return request({
    url: '/employee/getemployeeinfo',
    method: 'post',
    params:{ 'id':id}
  })
}
export function editEmployee(data) {
  return request({
    url: '/employee/editemployee',
    method: 'post',
    data:data
  })
}

export function editEmployeeRole(data) {
  return request({
    url: '/employee/editemployeerole',
    method: 'post',
    data:data
  })
}

export function deleteEmployeeById(id) {
  return request({
    url: '/employee/deleteemployeebyid',
    method: 'post',
    params:{id}
  })
}
export function editEmployeePass(data) {
  return request({
    url: '/employee/editemployeepass',
    method: 'post',
    data:data
  })
}
