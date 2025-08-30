<script>
import auth from "@/services/authService";

export default {
  name: "AppHeader",
  computed: {
    isLoggedIn() {
      return auth.isAuthenticated.value;
    },
    currentUser() {
      return auth.email.value;
    },
  },
  methods: {
    logout() {
      auth.logout();
      this.$router.push({ name: "home" });
    },
  },
};
</script>

<template>
  <div class="container head text-center">
    <div class="row">
      <div class="col-8 offset-md-2">
        <RouterLink to="/" class="ms-3" style="color: inherit; text-decoration: none;">
          <h1>WestMarches</h1>
        </RouterLink>
      </div>

      <div class="col above"  v-if="isLoggedIn">
        <button class="dropdown-item" @click="logout">Logout</button>
      </div>
    </div>
  </div>

  <Toaster />
</template>



<style scoped>
.above {
  position: relative;
  z-index: 1000;
}
</style>