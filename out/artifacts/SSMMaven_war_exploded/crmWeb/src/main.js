// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import iView from 'iview'
import Axios from '../src/servers/index'
import 'iview/dist/styles/iview.css'
import store from '../src/servers/store'
Vue.use(Element);
Vue.use(iView);
Vue.use(Axios);
Vue.config.productionTip = false;
// Axios.defaults.withCredentials = true;
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render:c =>c(App),
  store
});
