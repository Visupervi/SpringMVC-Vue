/**
 * Created by visupervi on 2018-07-17.
 */
import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex);
const token = JSON.parse(localStorage.getItem('token') || null);

const store = new Vuex.Store({
  state(){
    return {
      userInfo:{
        token: token,
        userName:"",
        lifeTime:null
      }
    }
  },
  mutations:{
    setToken(state,token,name){
      state.userInfo.token = token;
      state.userInfo.lifeTime = (new Date()).getTime();
      state.userInfo.userName = name;
      localStorage.setItem('userInfo',JSON.stringify(state.userInfo));
    },
    delToken(state){
      state.token = null;
      localStorage.removeItem('userInfo');
    }
  },
  getters:{
    getToken(){
      if(!localStorage.getItem("userInfo")){
        localStorage.setItem("userInfo",{})
      }
      return localStorage.getItem("userInfo");
    }
  }
});
export default store;
