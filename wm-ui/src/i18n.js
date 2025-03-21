import { createI18n } from 'vue-i18n';

const i18n = createI18n({
  locale: 'en',
  messages: {} 
});

export function loadLocaleMessages(locale) {
  if (
    !i18n.global.getLocaleMessage(locale) ||
    Object.keys(i18n.global.getLocaleMessage(locale)).length === 0
  ) {
    return import(
      /* webpackChunkName: "locale-[request]" */
      `@/assets/locales/${locale}.json`
    ).then((module) => {
      i18n.global.setLocaleMessage(locale, module.default);
      return module.default;
    });
  }
  return Promise.resolve(i18n.global.getLocaleMessage(locale));
}

export default i18n;