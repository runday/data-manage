<template>
  <img :src="homeImg" :width="width" :height="height">
</template>

<script>
  export default {
    data() {
      return {
        homeImg:'../static/common/home.jpg',
        width:document.body.clientWidth-50,
        height:document.body.clientHeight-98
      };
    },
    mounted (){
      let self=this
      window.onresize = function(){
        self.width=document.body.clientWidth-50
        self.height=document.body.clientHeight-98
      }
    }
  }
</script>
