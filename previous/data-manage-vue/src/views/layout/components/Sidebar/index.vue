<template>
  <el-scrollbar wrap-class="scrollbar-wrapper">
    <el-menu
      :show-timeout="200"
      :default-active="$route.path"
      :collapse="isCollapse"
      mode="vertical"
      background-color="#e3e7ec"
      text-color="#000000"
      active-text-color="#2a579a"
    >
      <sidebar-item v-for="route in permission_routers" :key="route.path+getUniqueString()" :item="route" :base-path="route.path"/>
    </el-menu>
  </el-scrollbar>
</template>

<script>
  import getUniqueString from '@/utils/createUniqueString'
  import { mapGetters } from 'vuex'
  import SidebarItem from './SidebarItem'

  export default {
    components: { SidebarItem },
    computed: {
      ...mapGetters([
        'permission_routers',
        'sidebar'
      ]),
      isCollapse() {
        return !this.sidebar.opened
      }
    },
    methods:{
      getUniqueString
    }
  }
</script>
