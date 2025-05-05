<script>
  export default {
    methods: {
      goBack() {
        this.$router.push({name: 'home'})
      }
    }
  }
</script>
<template>
  <div>
    <h1>404</h1>
    <button v-on:click="goBack">Go back</button>
  </div>
</template>