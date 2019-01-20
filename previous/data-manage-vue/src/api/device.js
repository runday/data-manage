import request from '@/utils/request'

export function getDeviceListByConditionAndPage(query) {
  return request({
    url: '/device/devicelistbyconditionandpage',
    method: 'get',
    params: query
  })
}
export function getDeviceListByEnterpriseIdAndPage(query) {
  return request({
    url: '/device/getdevicelistbyenterpriseidandpage',
    method: 'get',
    params: query
  })
}
export function insertManyDevice(data) {
  return request({
    url: '/device/insertmanydevice',
    method: 'post',
    data:data
  })
}

export function updateManyDeviceNo(data) {
  return request({
    url: '/device/updatemanydeviceno',
    method: 'post',
    data:data
  })
}

export function editDevice(data) {
  return request({
    url: '/device/editdevice',
    method: 'post',
    data:data
  })
}
export function deleteDeviceById(id) {
  return request({
    url: '/device/deletedevicebyid',
    method: 'post',
    params:{id}
  })
}
