import axios from "axios";

const api = axios.create({
  baseURL: `${import.meta.env.VITE_API_URL}`,
  headers: {
    "Content-type": "application/json",
  },
  successMessage: "welcome ", //i18N
});

const handleError = (error) => {
  /*
   * If request was made, but the status code
   * of the server response falls outside
   * the 2xx range.
   */
  if (error.response) {
    // A lookup table of different error messages
    const messages = {
      404: this.$t("error.404"),
      500: this.$t("error.500"),
    };

    const errorMessage =
      messages[error.response.status] ||
      `Unexpected error: ${error.response.status}`;

    alert(errorMessage, { id: "api-error" });
    console.error("Full error:", error);
    return;
  }

  // If request was made but no response received
  if (error.request) {
    alert("No response from server. Check your network connection.", {
      id: "api-error",
    });
    console.error("Full error:", error);
    return;
  }

  // If error was triggered by something else
  alert("Error setting up the request", { id: "api-error" });
  console.error("Full error:", error);
};

api.interceptors.response.use(
  (response) => {
    // const successMessage = response.config.successMessage +
    //   JSON.parse(response.config.data).email.split('@')[0];
    // alert(successMessage, {
    //   id: 'api-success',
    // });

    return response;
  },
  (error) => {
    handleError(error);
    return Promise.reject(error);
  },
);

api.interceptors.request.use(
  (config) => {
    console.log("config" + JSON.stringify(config));
    const token = localStorage.getItem("token");
    const roles = localStorage.getItem("roles");

    if (token) {
      config.headers["Authorization"] = `bearer ${token}`;
    }

    if (roles) {
      config.headers["Roles"] = roles;
    }
    console.log(config.headers);
    return config;
  },
  (error) => Promise.reject(error),
);

export default api;
