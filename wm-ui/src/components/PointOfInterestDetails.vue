<script>
import EventDetails from "./EventDetails.vue";
import api from "@/services/api";

export default {
  props: {
    details: null,
    context: null,
    index: null,
  },
  inject: ["selectedMap"],
  components: {
    EventDetails,
  },
  data() {
    return {
      events: null,
    };
  },
  watch: {
    details: {
      immediate: true,
      deep: true,
      handler(newDetails) {
      console.log("newDetails: ");
      console.log(newDetails);
      api
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
        .catch((error) => console.error(error));
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
        :data-bs-target="'#poi-accordion-' + index"
        aria-expanded="false"
        :aria-controls="'poi-accordion-' + index"
      >
        {{ this.details.name }}
      </button>
    </h2>
    <div
      :id="'poi-accordion-' + index"
      class="accordion-collapse collapse"
      data-bs-parent="#poi-accordion"
    >
      <div class="accordion-body">
        <p>{{ this.details.description }}</p>
        <h3>{{ $t("tileDetails.eventSubtitle") }}</h3>
        <div class="accordion accordion-flush" id="event-accordion" v-if="this.events">
          <div v-for="(event, index) in this.events" :key="event.name">
            <EventDetails :details="event" :index="index"></EventDetails>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
