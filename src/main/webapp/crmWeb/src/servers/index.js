/**
 * Created by visupervi on 2018-07-14.
 */
import axios from 'axios'
import qs from 'qs'
import {Message,Loading } from 'element-ui'
let loadingInstance;
const opts = {
  lock: true,
  text: 'Loading',
  background: 'rgba(255, 255, 255, 0.3)',
  customClass: '请耐心等待'
}
const Axios = axios.create({
  baseUrl:'',//查一下自己的地址
  timeout:3000,
  responseType:'json',
  withCredentials:true,
  headers:{
    "Content-type":"application/x-www-form-urlencoded;charset=utf-8"
  }
});
axios.interceptors.request.use(config =>{

  if(
    config.method == 'post' ||
    config.method == 'put' ||
    config.method == 'delete'
  ){
    loadingInstance = Loading.service(opts);
    config.data = qs.stringify(config.data);
  }
  if(localStorage.token){
    config.headers.Authorization = localStorage.token;
  }
  return config;
},error =>{
  Message({
    showClose:true,
    message:error,
    type:'error.data.error.message'
  });
  return Promise.reject(error.data.error.message)
});

Axios.interceptors.response.use(
  res => {

    if(res.data && res.data.success){
      setTimeout(function () {
        loadingInstance.close()
      }, 2000);
      Message({
        showClose:true,
        message:res.data.error.message.message
        ? res.data.error.message.message
          : res.data.error.message,
        type:'error'
      });
      return Promise.reflect(res.data.error.message);
    }
    return res;
  },
  error => {
    if (!window.localStorage.getItem("loginUserBaseInfo")) {
      router.push({
        path: "/login"
      });
    } else {
      let lifeTime =
        JSON.parse(window.localStorage.getItem("loginUserBaseInfo")).lifeTime *
        1000;
      let nowTime = new Date().getTime(); // 当前时间的时间戳
      console.log(nowTime, lifeTime);
      console.log(nowTime > lifeTime);
      if (nowTime > lifeTime) {
        Message({
          showClose: true,
          message: "登录状态信息过期,请重新登录",
          type: "error"
        });
        router.push({
          path: "/login"
        });
      } else {
        if (error.response.status === 403) {
          router.push({
            path: "/error/403"
          });
        }
        if (error.response.status === 500) {
          router.push({
            path: "/error/500"
          });
        }
        if (error.response.status === 502) {
          router.push({
            path: "/error/502"
          });
        }
        if (error.response.status === 404) {
          router.push({
            path: "/error/404"
          });
        }
      }
    }
    let errorInfo =  error.data.error ? error.data.error.message : error.data;
    return Promise.reject(errorInfo);
  }
);

export default {
  install: function(Vue, Option) {
    Object.defineProperty(Vue.prototype, "$http", { value: Axios });
  }
};