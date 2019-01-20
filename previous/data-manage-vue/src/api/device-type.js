import request from '@/utils/request'

export function getDeviceTypeList() {
  return request({
    url: '/devicetype/getdevicetypelist',
    method: 'get'
  })
}
