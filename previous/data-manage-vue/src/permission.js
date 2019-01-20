import router from './router'
import store from './store'
import { Message } from 'element-ui'
import { getToken } from '@/utils/auth'

const whiteList = ['/login']

router.beforeEach((to, from, next) => {
  if (getToken()){
    if (to.path === '/login') {
      next({ path: '/home/index' })
    }else{
      if(store.getters.addRouters.length === 0){
        store.dispatch('GetEmployeeInfo').then(data => { // 拉取user_info
          store.dispatch('GenerateRoutes',{ data }).then(() => { // 根据roles权限生成可访问的路由表
            router.addRoutes(store.getters.addRouters) // 动态添加可访问路由表
            next({ ...to, replace: true })
          })
        }).catch((err) => {
          store.dispatch('FedLogOut').then(() => {
            Message.error(err || 'err')
            next({ path: '/login' })
          })
        })
      }else{
        next()
      }
    }
  }else{
    if (whiteList.indexOf(to.path) !== -1) { // 在免登录白名单，直接进入
      next()
    } else {
      next({ path: '/login' })
    }
  }
})

