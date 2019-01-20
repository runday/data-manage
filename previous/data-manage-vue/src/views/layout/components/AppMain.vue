<template>
  <section class="app-main">
    <keep-alive :include="cachedViews">
      <router-view :key="key"/>
    </keep-alive>
  </section>
</template>

<script>
export default {
  name: 'AppMain',
  computed: {
    cachedViews() {
      return this.$store.state.tagsView.cachedViews
    },
    key() {
      return this.$route.fullPath
    }
  }
}
</script>

<style scoped>
.app-main {
  width: 100%;
  position: relative;
  overflow: hidden;
}
</style>

