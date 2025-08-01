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
  <footer class="text-center p-4 container border-top gy-10">
    <p class="ms-3">© westmarches</p>
    <RouterLink to="/gcu" class="ms-3">{{ $t('footer.gcu') }}</RouterLink>
    <RouterLink to="/privacy" class="ms-3">{{ $t('footer.privacyPolicy') }}</RouterLink>
    <small class="ms-3">{{ $t('footer.disclamer') }}</small>
  </footer>
</template>
