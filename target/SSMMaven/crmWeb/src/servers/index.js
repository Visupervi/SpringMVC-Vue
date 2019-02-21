/**
 * Created by visupervi on 2018-07-14.
 */
import axios from 'axios'
import qs from 'qs'
import {Message,Loading } from 'element-ui'
import router from '../router'
axios.defaults.withCredentials = true;
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
    "Content-type":"application/json;charset=utf-8"//x-www-form-urlencoded
  }

});
Axios.interceptors.request.use(config =>{

  if(
    config.method == 'post' ||
    config.method == 'put' ||
    config.method == 'delete' ||
    config.method == 'axios'
  ){
    loadingInstance = Loading.service(opts);
    if(config.method == "put"){
      config.data = qs.stringify(config.data);
    }
  }
  if(localStorage.token){
    config.headers.Authorization = localStorage.token;
    // console.log(config.headers.Authorization);
    // loadingInstance = Loading.service(opts);
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
    setTimeout(function () {
      loadingInstance && loadingInstance.close();
    }, 2000);
    if(res.data && res.data.success){
      Message({
        showClose:true,
        message:res.data.error.message.message
        ? res.data.error.message.message
          : res.data.error.message,
        type:'error'
      });
      return Promise.reject(res.data.error.message);
    }
    // loadingInstance.close();
    return res;
  },
  error => {
    if (!window.localStorage.getItem("userInfo")) {
      router.push({
        path: "/"
      });
    } else {
      let lifeTime =
        JSON.parse(window.localStorage.getItem("userInfo")).lifeTime *
        1000;
      let nowTime = new Date().getTime(); // 当前时间的时间戳
      if (nowTime > lifeTime) {
        Message({
          showClose: true,
          message: "登录状态信息过期,请重新登录",
          type: "error"
        });
        router.push({
          path: "/"
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
    // let errorInfo =  error.data.error ? error.data.error.message : error.data;
    let errorInfo = error.data && error.data.error && error.data.error.message || error.data;
    return Promise.reject(errorInfo);
  }
);



export default {

  //作为插件使用，可以直接使用Vue.use方法使用
  install: function(Vue, Option) {
    Object.defineProperty(Vue.prototype, "$http", { value: Axios });
  },

};
