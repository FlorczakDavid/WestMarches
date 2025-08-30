<script>
export default {
  name: 'AppFooter'
}
</script>

<template>
    <p class="ms-3">© westmarches</p>
    <RouterLink to="/general-conditions-of-use-and-privay-policy" class="ms-3">{{ $t("footer.gcu") }}</RouterLink>
    <RouterLink to="/general-conditions-of-use-and-privay-policy" class="ms-3">{{
      $t("footer.privacyPolicy")
    }}</RouterLink>
    <small class="ms-3">{{ $t("footer.disclamer") }}</small>
</template>