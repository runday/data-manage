<template>
  <li v-on:click='handleClick' ><slot></slot></li>
</template>
<script>
  export default {
    name: 'menuContextItem',
    data() {
      return{
        row:{}
      }
    },
    created:function(){
      this.$root.$on("table-row",row=>{
        this.row=row
      })
    },
    methods: {
      handleClick() {
        this.$emit('click',this.row)
      }
    }
  }
</script>
