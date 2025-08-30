<script>
import EventDetails from "./EventDetails.vue";

export default {
  props: {
    details: Object,
    context: Object,
    poiIndex: Number,
  },
  inject: ["selectedMap"],
  components: {
    EventDetails,
  },
  data() {
    return {
      events: null,
      uniqueId: `${this.context.x}-${this.context.y}-${this.poiIndex}`
    };
  },
  watch: {
    details: {
      immediate: true,
      deep: true,
      handler(newDetails) {
      console.log("newDetails: ");
      console.log(newDetails);
      this.$api
        .get("/event/poi", {
          params: {
            email: localStorage.getItem("user"),
            map: this.selectedMap.name,
            x: this.context.x,
            y: this.context.y,
            poi: newDetails.name,
          },
        })
        .then((response) => {
          this.events = response.data;
        })
        .catch(() => { 
          /* already handled by interceptor */
        });
    },
    } 
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
        :data-bs-target="'#poi-' + uniqueId"
        aria-expanded="false"
        :aria-controls="'poi-' + uniqueId"
      >
        {{ this.details.name }}
      </button>
    </h2>
    <div
      :id="'poi-' + uniqueId"
      class="accordion-collapse collapse"
      data-bs-parent="#poi-accordion"
    >
      <div class="accordion-body">
        <p>{{ this.details.description }}</p>
        <h3>{{ $t("tileDetails.eventSubtitle") }}</h3>
        <div class="accordion accordion-flush" :id="'event-accordion-' + uniqueId" v-if="this.events">
          <div v-for="(event, eventIndex) in this.events" :key="eventIndex">
            <EventDetails :details="event" :poiId="uniqueId" :index="eventIndex"></EventDetails>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
