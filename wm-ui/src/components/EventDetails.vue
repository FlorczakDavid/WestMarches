<script>
export default {
  props: {
    details: {},
    index: null,
  },
};
</script>

<template>
  <div class="accordion-item">
    <h2 class="accordion-header">
      <button
        class="accordion-button collapsed"
        type="button"
        data-bs-toggle="collapse"
        :data-bs-target="'#event-accordion-' + index"
        aria-expanded="false"
        :aria-controls="'event-accordion-' + index"
      >
        {{ this.details.name }}
      </button>
    </h2>
    <div
      :id="'event-accordion-' + index"
      class="accordion-collapse collapse"
      data-bs-parent="#event-accordion"
    >
      <div class="accordion-body">
        {{ this.details.description }}
      </div>
    </div>
  </div>
</template>
