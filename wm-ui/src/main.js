import './assets/main.css';

import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import i18n, { loadLocaleMessages } from './i18n';

const app = createApp(App);

app.use(router);
app.use(i18n);

const userLocale = navigator.language.split('-')[0] || 'en';
if (userLocale === 'ar') {
  document.querySelector('body').classList.toggle('rtl');
}

loadLocaleMessages(userLocale).then(() => {
  i18n.global.locale = userLocale;
  app.mount('#app');
})
