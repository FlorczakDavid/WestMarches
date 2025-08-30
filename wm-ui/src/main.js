import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.bundle";
import "./assets/main.css";
import "./assets/terrains.css"
import "@/assets/customBootstrap.scss";
import "@/assets/fonts.scss";

import { createApp } from "vue";
import App from "./App.vue";
import plugins from "./plugins";
import router from "./router";

const app = createApp(App);

app.use(router);
plugins(app);

app.config.errorHandler = (err, comp) => {
    const messagePath = 'errors.unhandledError';
    comp.$toaster.error(comp.$t(messagePath));
};

app.mount("#app");