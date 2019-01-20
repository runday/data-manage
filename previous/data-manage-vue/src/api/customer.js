import request from '@/utils/request'

export function getCustomerListByConditionAndPage(query) {
  return request({
    url: '/customer/customerlistbyconditionandpage',
    method: 'get',
    params: query
  })
}

export function getCustomerListByCondition(query) {
    return request({
        url: '/customer/customerlistbycondition',
        method: 'get',
        params: query
    })
}

export function editCustomer(data) {
    return request({
        url: '/customer/editcustomer',
        method: 'post',
        data:data
    })
}
export function deleteCustomerById(id) {
    return request({
        url: '/customer/deletecustomerbyid',
        method: 'post',
        params:{id}
    })
}
