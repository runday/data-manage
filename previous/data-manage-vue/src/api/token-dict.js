import request from '@/utils/request'

export function getCustomerListByEnterpriseId(enterpriseId) {
  return request({
    url: '/tokendict/getcustomerlistbyenterpriseid',
    method: 'get',
    params:{enterpriseId}
  })
}
export function getCustomerOrEnterpriseList(organizationType) {
  return request({
    url: '/tokendict/getcustomerorenterpriselist',
    method: 'get',
    params: {organizationType:organizationType}
  })
}
