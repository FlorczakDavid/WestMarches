import axios from "axios";
import i18n from "./i18n";

const api = axios.create({
  baseURL: `${import.meta.env.VITE_API_URL}`,
  headers: { "Content-type": "application/json" },
});

let toaster;
export default {
  install: (app) => {
    toaster = app.config.globalProperties.$toaster;
    app.config.globalProperties.$api = api;

    app.config.globalProperties.$toastSuccess = (key, params) =>
      toaster?.success(t(key, params));
    app.config.globalProperties.$toastInfo = (key, params) =>
      toaster?.info(t(key, params));
  }
};

const t = (key) => i18n.global.t(key);

function toastFromStatus(code) {
  const i18nKey = `errors.http.${code}`;
  const exists = i18n.global.te?.(i18nKey) || i18n.global.tm?.(i18nKey);
  const msg = exists ? t(i18nKey) : t("errors.http.default", { code: code });
  toaster?.error(msg);
}

function handleError(error) {
  if (error?.response?.status) {
    toastFromStatus(error.response.status);
  } else if (error?.request) {
    toaster?.error(t("errors.network"));
  } else {
    toaster?.error(t("errors.setup"));
  }
  // Keep rejection so callers can still .catch if they need control flow
  return Promise.reject(error);
}

api.interceptors.response.use(
  (response) => response,
  (error) => handleError(error)
);

api.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  const roles = localStorage.getItem("roles");
  if (token) {
    config.headers["Authorization"] = `bearer ${token}`;
  }
  if (roles) {
    config.headers["Roles"] = roles;
  }
  return config;
});