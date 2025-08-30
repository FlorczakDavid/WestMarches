<script>
import { useVuelidate } from "@vuelidate/core";
import { helpers, maxLength, minLength, required } from "@vuelidate/validators";

const passwordRegex = helpers.regex(
  /^(?=.*[A-Za-z])(?=.*\d)(?=.*[;:*\-!]).{8,}$/,
);
const emailRegex = helpers.regex(
  /^(?=.{1,64}@)\w+([.-]?\w+)*@(?=.{4,252}$)\w+([.-]?\w+)*(\.\w{2,4})+$/,
);

export default {
  setup() {
    return {
      v$: useVuelidate(),
    };
  },
  data() {
    return {
      inputs: {
        email: "",
        password: "",
      },
    };
  },
  validations() {
    return {
      inputs: {
        email: {
          required,
          maxLength: maxLength(255),
          emailRegex,
        },
        password: {
          required,
          minLength: minLength(8),
          passwordRegex,
        },
      },
    };
  },
  methods: {
    async submit() {
      const isFormCorrect = await this.v$.$validate();
      if (!isFormCorrect) return;

      this.$api
        .post("/user", {
          email: this.inputs.email,
          password: this.inputs.password,
          username: this.inputs.email.split("@")[0],
        })
        .then(() => {
          this.$toastSuccess("toasts.registerSuccess");
          this.$router.push({ name: "login" });
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
    <h1>{{ $t("auth.registerTitle") }}</h1>
    <form @submit.prevent="submit" novalidate>
      <div className="mb-3">
        <label for="email" class="form-label">
          {{ $t("auth.emailLabel") }}
        </label>
        <input
          v-model="inputs.email"
          type="email"
          name="email"
          className="form-control"
          id="email"
        />
        <div class="text-danger" v-if="v$.inputs.email.$error">
          {{ $t("auth.emailError") }}
        </div>
      </div>
      <div className="mb-3">
        <label for="password" class="form-label">
          {{ $t("auth.passwordLabel") }}
        </label>
        <input
          v-model="inputs.password"
          type="password"
          name="password"
          className="form-control"
          id="password"
        />
        <div class="text-danger" v-if="v$.inputs.password.$error">
          {{ $t("auth.passwordError") }}
        </div>
      </div>
      <button type="submit" className="btn btn-primary">
        {{ $t("auth.submitButton") }}
      </button>
    </form>
    <nav>
      <RouterLink to="/login">{{ $t("auth.loginLink") }}</RouterLink>
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