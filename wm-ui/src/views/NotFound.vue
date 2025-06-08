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
  <div class="head">
    <h1>404</h1>
    <button v-on:click="goBack">{{$t('errors.404.goBackButton')}}</button>
  </div>
</template>