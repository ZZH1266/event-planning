import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/cms";


// 查询列表
export function listExpense(query) {
  return request({
    url: '/api/expense_claim/list',
    method: 'get',
    params: query
  })
}

// 查询列表
export function saveExpense(data) {
  return request({
    url: '/api/expense_claim/save',
    method: 'post',
    data: data
  })
}
