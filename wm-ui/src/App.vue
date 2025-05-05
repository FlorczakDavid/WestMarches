<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
      <nav>
        <RouterLink to="/">Login</RouterLink>
        <RouterLink to="/register">Register</RouterLink>
        <RouterLink to="/map">Map</RouterLink>
      </nav>
  </header>
  <main class="container">
    <RouterView />
  </main>
</template>
