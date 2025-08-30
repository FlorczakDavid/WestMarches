import { createI18n } from "vue-i18n";
import en from "@/assets/locales/en.json"

const i18n = createI18n({
  locale: "en",
  fallbackLocale: 'en',
  messages: { en },
  silentTranslationWarn: true,
  silentFallbackWarn: true, 
  datetimeFormats: {
    en: { short: { year: 'numeric', month: 'long', day: 'numeric' }},
    fr: { short: { year: 'numeric', month: 'long', day: 'numeric' }},
    ar: { short: { year: 'numeric', month: 'long', day: 'numeric' }},
    ja: { short: { year: 'numeric', month: 'long', day: 'numeric' }}
  }
});

export async function loadLocaleMessages(locale) {
  if (i18n.global.availableLocales.includes(locale)) {
    return i18n.global.getLocaleMessage(locale)
  }

  try {
    const module = await import(`@/assets/locales/${locale}.json`)
    i18n.global.setLocaleMessage(locale, module.default)
    return module.default
  } catch (e) {
    return i18n.global.getLocaleMessage("en")
  }
}

export function initI18n() {
  const rtlLocales = ["ar", "az", "dv", "he", "ku", "fa", "ur"];
  const userLocale = (navigator.language?.split("-")[0]) || "en";

  if (rtlLocales.includes(userLocale)) {
    document.querySelector("body")?.classList?.add("rtl");
  }

  loadLocaleMessages(userLocale).then(() => {
    i18n.global.locale = userLocale;
  });
}

export default i18n;
