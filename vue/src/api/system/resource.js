import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/cms";


// 查询列表
export function listResource(query) {
  return request({
    url: '/api/resource/list',
    method: 'get',
    params: query
  })
}

/**
 * 新增活动
 * @param data
 */
export function addResource(data) {
  return request({
    url: '/api/resource/add',
    method: 'post',
    data: data
  })
}

export function updateaResource(data) {
  return request({
    url: '/api/resource/update',
    method: 'put',
    data: data
  })
}


export function delResource(id) {
  return request({
    url: '/api/resource/del/'+id,
    method: 'get',
  })
}
