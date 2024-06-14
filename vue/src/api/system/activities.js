import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/cms";


// 查询列表
export function listActivities(query) {
  return request({
    url: '/api/activities/list',
    method: 'get',
    params: query
  })
}

/**
 * 新增活动
 * @param data
 */
export function addActivities(data) {
  return request({
    url: '/api/activities/add',
    method: 'post',
    data: data
  })
}

export function updateaActivities(data) {
  return request({
    url: '/api/activities/update',
    method: 'put',
    data: data
  })
}


export function applyActivities(id) {
  return request({
    url: '/api/activities/apply/'+id,
    method: 'get',
  })
}

export function delActivities(id) {
  return request({
    url: '/api/activities/del/'+id,
    method: 'get',
  })
}

export function generateQRForSharing(id) {
  return request({
    url: '/generateQRForSharing?activityId='+id,
    method: 'get',
  })
}
/**
 * 新增模板
 * @param data
 */
export function AddTemplate(data) {
  return request({
    url: '/api/template/add',
    method: 'post',
    data: data
  })
}

/**
 *模板列表
 * @param query
 */
export function listTemplate(query) {
  return request({
    url: '/api/template/list',
    method: 'get',
    params: query
  })
}

/**
 * 活动申请查询列表
 **/
export function listParticipant(query) {
  return request({
    url: '/activity/participant/handle',
    method: 'get',
    params: query
  })
}

/**
 * 活动通过
 * @param query
 */
export function applyParticipant(data) {
  return request({
    url: '/activity/participant/handle/apply',
    method: 'post',
    data: data
  })
}

/**
 * 拒绝通过
 * @param query
 */
export function rejectParticipant(data) {
  return request({
    url: '/activity/participant/handle/reject',
    method: 'post',
    data: data
  })
}


/**
 * 判断是否有加入此活动
 * @param id
 */
export function joined(activityId) {
  return request({
    url: '/api/activities/joined/' + activityId,
    method: 'get',
  })
}
