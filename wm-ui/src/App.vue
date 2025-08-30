<script>
import Header from "./components/Header.vue";
import Footer from "./components/Footer.vue";

export default {
  components: {
    Header,
    Footer
  }
};
</script>

<template>
  <header>
    <Header />
  </header>
  <main class="container">
    <RouterView />
  </main>
  <footer class="text-center p-4 container border-top gy-10">
    <Footer />
  </footer>
</template>
