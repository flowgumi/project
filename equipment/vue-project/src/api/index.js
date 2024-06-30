/* import { Header } from "element-ui";
 */
import http from "../utils/request";
/* import axios from 'axios';
 */

//login,register
export const userRegister = (data) => {
    return http.post('/register', data);
}
export const userLogin = (data) => {
    return http.post('/login', data);
}

// Homepage
export const getEquipmentByDept = (token) => {
    return http.get('/equipment/dept',{
        headers:{
          token: token
        }});
}
export const getOutEquipment = (token) => {
  return http.get('/borrow/dept/list',{
      headers:{
        token: token
      }});
}
export const addEquipmentHttp = (data,token) => {
    return http.post('/equipment', data,{
        headers:{
          token: token
        }});
}
export const outEquipmentHttp = (outId,token) => {
  return http.get('/borrow/dept/'+outId,{
      headers:{
        token: token
      }});
}
export const getEquipmentByAllSelf = (data,token) => {
    return http.post('/equipment/dept/search',data,{
        headers:{
          token: token
        }});
}

export const scrapEquipmentHttp = (scrapId,token) => {
    return http.delete('/equipment/'+scrapId,{
        headers:{
          token: token
        }});
}

export const uploadEquipmentImage = (file, token) => {
  let formData = new FormData();
  formData.append("image", file);  // 'image' 是你后端期望的文件字段名

  return http.post('/equipment/upload', formData, {
    headers: {
      'Content-Type': 'multipart/form-data',  // 确保设置了正确的内容类型
      token: token
    }
  });
}


//querypage
export const getEquipmentById = (equipmentId,token) => {
    return http.get('/equipment/'+equipmentId,{
        headers:{
          token: token
        }});
}

export const getEquipmentByAll = (data,token) => {
    return http.post('/equipment/search',data,{
        headers:{
          token: token
        }});
}

export const getAllEquipment = (token) => {
    return http.get('/equipment',{
        headers:{
          token: token
        }});
}

//BorrowPage
export const getAllOtherEquipment = (token) => {
    return http.get('/borrow/list',{
        headers:{
          token: token
        }});
}

export const borrowEquipmentHttp = (borrowId,token) => {
    return http.get('/borrow/'+borrowId,{
        headers:{
          token: token
        }});
}

//ReturnPage
export const getBorrowedEquipment = (token) => {
  return http.get('/borrow/dept',{
      headers:{
        token: token
      }});
}

export const returnEquipmentHttp = (returnId,token) => {
  return http.get('/borrow/' + returnId + '/back',{
      headers:{
        token: token
      }});
}