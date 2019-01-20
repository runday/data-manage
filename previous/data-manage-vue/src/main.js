import Vue from 'vue'
import App from './App'
import 'normalize.css/normalize.css'
import 'element-ui/lib/theme-chalk/index.css'
import '@/styles/index.scss'
import Element from 'element-ui'
import router from './router'
import './permission'
import store from './store'
import menuContext from '@/components/menuContext'
import menuContextItem from '@/components/menuContext/menuContextItem'

Vue.use(menuContext)
Vue.use(menuContextItem)
Vue.component('menu-context', menuContext)
Vue.component('menu-context-item', menuContextItem)
Vue.use(Element)
Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
