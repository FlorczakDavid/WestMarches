<script>
export default {
  data() {
    return {
      effectiveDate: new Date("2025-09-01")
    }
  }
}
</script>

<template>
  <div class="row">
    <article id="privacy" class="content col">
      <header>
        <h1>{{ $t("privacyPolicy.title") }}</h1>
        <p>{{ $t("privacyPolicy.effectiveDate") }} <i18n-d tag="p" :value="this.effectiveDate"></i18n-d></p>
        <p>{{ $t("privacyPolicy.intro") }}</p>
      </header>
      <section>
        <h2>{{ $t("privacyPolicy.dataCollected.title") }}</h2>
        <p>{{ $t("privacyPolicy.dataCollected.text") }}</p>
      </section>
      <section>
        <h2>{{ $t("privacyPolicy.dataUse.title") }}</h2>
        <p>{{ $t("privacyPolicy.dataUse.text") }}</p>
      </section>
      <section>
        <h2>{{ $t("privacyPolicy.dataSharing.title") }}</h2>
        <p>{{ $t("privacyPolicy.dataSharing.text") }}</p>
      </section>
      <section>
        <h2>{{ $t("privacyPolicy.dataRetention.title") }}</h2>
        <p>{{ $t("privacyPolicy.dataRetention.text") }}</p>
      </section>
      <section>
        <h2>{{ $t("privacyPolicy.security.title") }}</h2>
        <p>{{ $t("privacyPolicy.security.text") }}</p>
      </section>
      <section>
        <h2>{{ $t("privacyPolicy.rights.title") }}</h2>
        <p>{{ $t("privacyPolicy.rights.text") }}</p>
      </section>
      <section>
        <h2>{{ $t("privacyPolicy.changes.title") }}</h2>
        <p>{{ $t("privacyPolicy.changes.text") }}</p>
      </section>
    </article>
    <article id="gcu" class="content col">
      <header>
        <h1>{{ $t("gcu.title") }}</h1>
        <p>{{ $t("gcu.effectiveDate") }} <i18n-d tag="p" :value="this.effectiveDate"></i18n-d></p>
        <p>{{ $t("gcu.purpose") }}</p>
      </header>
      <section>
        <h2>{{ $t("gcu.userContent.title") }}</h2>
        <p>{{ $t("gcu.userContent.text") }}</p>
      </section>
      <section>
        <h2>{{ $t("gcu.intellectualProperty.title") }}</h2>
        <p>{{ $t("gcu.intellectualProperty.text") }}</p>
        <p>{{ $t("gcu.intellectualProperty.repoLink") }} <a href="https://github.com/FlorczakDavid/WestMarches">https://github.com/FlorczakDavid/WestMarches</a></p>
      </section>
      <section>
        <h2>{{ $t("gcu.acceptableUse.title") }}</h2>
        <p>{{ $t("gcu.acceptableUse.text") }}</p>
      </section>
      <section>
        <h2>{{ $t("gcu.liability.title") }}</h2>
        <p>{{ $t("gcu.liability.text") }}</p>
      </section>
      <section>
        <h2>{{ $t("gcu.termination.title") }}</h2>
        <p>{{ $t("gcu.termination.text") }}</p>
      </section>
      <section>
        <h2>{{ $t("gcu.changes.title") }}</h2>
        <p>{{ $t("gcu.changes.text") }}</p>
      </section>
    </article>
  </div>
</template>