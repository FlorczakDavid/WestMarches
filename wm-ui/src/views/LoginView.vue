<script>
import { useVuelidate } from "@vuelidate/core";
import { required } from "@vuelidate/validators";
import permissionService from "@/services/permissionService.js";

export default {
  setup() {
    return { v$: useVuelidate() };
  },
  data() {
    return {
      inputs: { email: "", password: "" },
    };
  },
  validations() {
    return {
      inputs: {
        email: { required },
        password: { required },
      },
    };
  },
  methods: {
    submit() {
      this.$api
        .post("/user/login", {
          email: this.inputs.email,
          password: this.inputs.password,
        })
        .then((response) => {
          permissionService.setUser(response.data, this.inputs.email);
          this.$toastSuccess("toasts.loginSuccess");
          this.$router.push({ name: "map" });
        })
        .catch(() => {
          /* already handled by interceptor */
        });
    },
  },
};
</script>

<template>
  <div className="container min-vw-80 content">
    <h1>{{ $t("auth.loginTitle") }}</h1>
    <form @submit.prevent="submit" novalidate>
      <div className="mb-3">
        <label for="email" class="form-label">
          {{ $t("auth.emailLabel") }}
        </label>
        <input v-model="inputs.email" type="email" name="email" className="form-control" id="email" />
        <div class="text-danger" v-if="v$.inputs.email.$error">
          {{ $t("validation.auth.emailError") }}
        </div>
      </div>

      <div className="mb-3">
        <label for="password" class="form-label">
          {{ $t("auth.passwordLabel") }}
        </label>
        <input v-model="inputs.password" type="password" name="password" className="form-control" id="password" />
        <div class="text-danger" v-if="v$.inputs.password.$error">
          {{ $t("validation.auth.passwordError") }}
        </div>
      </div>

      <button type="submit" className="btn btn-primary">
        {{ $t("auth.submitButton") }}
      </button>
    </form>

    <nav>
      <RouterLink to="/register">{{ $t("auth.registerLink") }}</RouterLink>
    </nav>
  </div>
</template>

<style scoped>
label.form-label {
  display: block;
  font-family: "gin";
  color: #5d0000;
  font-size: 2rem;
  line-height: 1.2;
  margin-bottom: .5rem;
}
</style>
