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
      token: token
    }
  },
  mutations:{
    setToken(state,token){
      state.token = token;
      localStorage.setItem('token',JSON.stringify(token));
    },
    delToken(state){
      state.token = null;
      localStorage.removeItem('token');
    }
  }
});
export default store;