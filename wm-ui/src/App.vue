<script>
  import { RouterLink, RouterView } from 'vue-router'

  export default {
    methods: {
      logout() {
        localStorage.clear();
        this.$router.push({name: 'home'})
      }
    }
  }
</script>

<template>
  <header>
      <nav>
        <RouterLink to="/">Login</RouterLink>
        <RouterLink to="/register">Register</RouterLink>
        <RouterLink to="/map">Map</RouterLink>
      </nav>
      <button v-on:click="logout">Logout</button>
  </header>
  <main class="container">
    <RouterView />
  </main>
  <footer class="text-center p-4 border-top mt-auto">
    <p>© westmarches</p>
    <RouterLink to="/gcu">CGU</RouterLink>
    <RouterLink to="/privacy">Privacy Policy</RouterLink>
    <small>Project developped as part of school assignment</small>
  </footer>
</template>
