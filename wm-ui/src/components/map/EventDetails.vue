<script>
export default {
  props: {
    details: Object,
    poiId: String,
    index: Number,
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
        :data-bs-target="'#event-' + poiId + '-' + index"
        aria-expanded="false"
        :aria-controls="'event-' + poiId + '-' + index"
      >
        {{ this.details.name }}
      </button>
    </h2>
    <div
      :id="'event-' + poiId + '-' + index"
      class="accordion-collapse collapse"
      :data-bs-parent="'#event-accordion-' + poiId"
    >
      <div class="accordion-body">
        {{ this.details.description }}
      </div>
    </div>
  </div>
</template>
