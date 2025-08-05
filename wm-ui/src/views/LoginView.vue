<script>
import { useVuelidate } from "@vuelidate/core";
import { required } from "@vuelidate/validators";
import api from "../services/api.js";
import permissionService from "@/services/permissionService.js";

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
        email: { required },
        password: { required },
      },
    };
  },
  methods: {
    submit() {
      // const isFormCorrect = await this.v$.$validate();
      // if(!isFormCorrect) return;
      api
        .post("/user/login", {
          email: this.inputs.email,
          password: this.inputs.password,
        })
        .then((response) => {
          localStorage.setItem("user", this.inputs.email);
          permissionService.setUser(response.data);
          this.$router.push({ name: "map" });
          // console.log(response.data); //{token: string, roles: string[]}
          // console.log(response.status); //201
          // console.log(response.statusText);
          // console.log(response.headers); //Axios header?
          // console.log(response.config); // constains sent data
        })
        .catch(function (error) {
          if (error.response) {
            // The request was made and the server responded with a status code
            // that falls out of the range of 2xx
            console.log("error response" + error.response.data); // ex.getMessage()
            console.log("error response" + error.response.status); // 401
            console.log("error response" + error.response.headers); //Axios header?
          } else if (error.request) {
            // The request was made but no response was received
            // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
            // http.ClientRequest in node.js
            console.log("error request" + error.request);
          } else {
            // Something happened in setting up the request that triggered an Error
            console.log("Error else", error.message);
          }
          console.log("last " + error.config);
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
        <h2 htmlFor="email" className="form-label">
          {{ $t("auth.emailLabel") }}
        </h2>
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
        <h2 htmlFor="password" className="form-label">
          {{ $t("auth.passwordLabel") }}
        </h2>
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
  </div>
</template>
