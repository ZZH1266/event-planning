import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/cms";


// 当前登陆者所发送的消息列表
export function getall(query) {
  return request({
    url: '/notices/getall',
    method: 'get',
    params: query
  })
}

export function viewnotice(query) {
  return request({
    url: '/notices/viewnotice',
    method: 'get',
    params: query
  })
}
