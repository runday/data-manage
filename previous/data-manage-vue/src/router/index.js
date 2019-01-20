import Vue from 'vue'
import Router from 'vue-router'
import Layout from '../views/layout/Layout'
Vue.use(Router)

export const constantRouterMap = [
  {
    path: '/',
    redirect: '/login',
    hidden: true
  },
  {path: '/login',component: () => import('../views/login/index'),hidden: true},
  {
    path:'/home',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('../views/home/index'),
        name: 'home',
        meta: { title: '首页', noCache: true}
      }
    ]
  }
]
export default new Router({
  mode: 'history',
  base:__dirname,
  routes:constantRouterMap
})
