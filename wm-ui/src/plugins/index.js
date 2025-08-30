import toaster from "./toaster";
import api from "./api";
import i18n, { initI18n } from "./i18n";
import Toaster from "@/components/Toaster.vue";

const plugins = (app) => {
  app.use(i18n);
  initI18n();

  app.component("Toaster", Toaster)

  app.use(toaster);
  app.use(api);
};

export default plugins;
