import Vue from 'vue'
import Router from 'vue-router'
import {Message} from 'element-ui'
import userLoginVue from '../components/userLoginVue/userLogin'
import listVue from '../components/listVue/listVue'
import bodyVue from '../components/bodyVue/bodyVue'

Vue.use(Router);

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'userLogin',
      component: userLoginVue,
      meta: {
        requireLogin: false
      },
    },
    {
      path:'/listVue',
      name:'listVue',
      component:listVue,
      meta: {
        requireLogin: true
      },
    }
  ]
});

router.beforeEach((to, from, next) => {
  if (to.matched.some(res => res.meta.requireLogin)) {
    // 判断是否需要登录权限
    // console.log(this.$store.getters.getToken);
    let userInfo = window.localStorage.getItem("userInfo");
    // console.log(JSON.parse(userInfo).lifeTime);
    if (userInfo!=null) {
      // 判断是否登录
      let lifeTime =
        JSON.parse(userInfo).lifeTime * 1000;
      // console.log(lifeTime)
      let nowTime = (new Date()).getTime(); // 当前时间的时间戳
      console.log(nowTime);
      if (nowTime < lifeTime) {
        next();
      } else {
        Message({
          showClose: true,
          message: "登录状态信息过期,请重新登录",
          type: "error"
        });
        next({
          path: "/"
        });
      }
    } else {
      // 没登录则跳转到登录界面
      next({
        path: "/"
      });
    }
  } else {
    next();
  }
});

export default router;
