import {constantRouterMap} from '@/router'
import Layout from '@/views/layout/Layout'
import getUniqueString from '@/utils/createUniqueString'

const permission = {
  state: {
    routers: [],
    addRouters: []
  },
  mutations: {
    SET_ROUTERS: (state,routers) => {
      state.addRouters = routers
      state.routers = constantRouterMap.concat(routers)
    },
    REMOVE_ROUTERS: (state) => {
      state.routers = []
      state.addRouters = []
    }
  },
  actions: {
    GenerateRoutes({ commit }, data) {
      return new Promise(resolve => {
        const allMenus = data.data;
        const routers=[]
        if(allMenus.length>0){
          const firstLevelMenus=filterFirstLevelMenus(allMenus, 0);
          firstLevelMenus.forEach(item=>{
            const routerItem=generateRoute(item)
            routerItem.children=generateRoutesFromMenu(item.id,allMenus)
            routers.push(routerItem)
          })
        }
        commit('SET_ROUTERS', routers)
        resolve(routers)
      })
    },
    removeRouters({ commit, state }) {
      commit('REMOVE_ROUTERS')
    },
  }
}

/**
 * 过滤各个级别的菜单
 * @param routeMap
 * @param menu_level
 * @returns {*}
 */
function filterFirstLevelMenus(menus, menu_level) {
  const accessedRouters = menus.filter(item => {
    return item.parentId==menu_level;
  })
  return accessedRouters;
}

/**
 * 生成路由列表
 * @param id
 * @param menus
 * @returns {Array}
 */
function generateRoutesFromMenu (id, menus = []) {
  let childMenus=[]
  let childRouters=[]
  menus.forEach(item=>{
    if(id==item.parentId){
      childMenus.push(item)
    }
  })
  childMenus.forEach(item=>{
    childRouters.push(generateRoute(item))
    childRouters.children=generateRoutesFromMenu(item.id,menus)
  })
  return childRouters
}

/**
 * 路由各个参数的匹配
 * @param routerMap
 */
function generateRoute(routerMap) {
  let router={};
  router.path=routerMap.routeUrl==null?'':routerMap.routeUrl;
  if(routerMap.parentId==0){
    router.component=Layout
  }else{
    if(routerMap.bindPageUrl){
      router.component=() => import('@/views'+routerMap.bindPageUrl)
    }else{
      router.component=() => import('@/views/'+routerMap.routeUrl+'/index')
    }
    if(routerMap.hidden){
      router.hidden=routerMap.hidden==1?true:false
    }
  }
  router.name=routerMap.routeUrl||""+getUniqueString();
  router.meta={title:routerMap.name,noCache: true }
  return router
}

export default permission
