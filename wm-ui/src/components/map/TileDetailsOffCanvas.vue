<script>
import PointOfInterestDetails from "./PointOfInterestDetails.vue";

export default {
  props: {
    details: null,
  },
  inject: ["selectedMap"],
  emits: ["edit"],
  components: {
    PointOfInterestDetails,
  },
  data() {
    return {
      pointsOfInterest: null,
      selectedTile: {},
    };
  },
  watch: {
    details: function (newDetails) {
      this.$api
        .get("/poi/tile", {
          params: {
            email: localStorage.getItem("user"),
            map: this.selectedMap.name,
            x: newDetails.x,
            y: newDetails.y,
          },
        })
        .then((response) => {
          console.log(response.data.poiName);
          this.pointsOfInterest = response.data;
        })
        .catch(() => { 
          /* already handled by interceptor */
        });
    },
  },
  methods: {
    requestEdit() {
      this.$emit("edit");
    },
  },
};
</script>

<template>
  <div
    class="offcanvas offcanvas-start"
    data-bs-scroll="true"
    tabindex="-1"
    id="offcanvasScrolling"
    aria-labelledby="offcanvasScrollingLabel"
  >
    <div class="offcanvas-header container head">
      <h1 class="offcanvas-title col-9" id="offcanvasScrollingLabel">
        {{ `x: ${this.details.x}, y:${this.details.y}` }}
      </h1>
      <button
        type="button"
        class="btn btn-outline-secondary col"
        v-on:click="requestEdit()"
        data-bs-dismiss="offcanvas"
        aria-label="Edit"
      >
        {{ $t("tileDetails.editButton") }}
      </button>
      <button
        type="button"
        class="btn-close col"
        data-bs-dismiss="offcanvas"
        :aria-label="$t('common.close')"
      ></button>
    </div>
    <div class="offcanvas-body content">
      <h1>{{ this.details.terrain }}</h1>
      <p>{{ this.details.description }}</p>
      <h2>{{ $t("tileDetails.poiSubtitle") }}</h2>
      <div class="accordion accordion-flush" id="poi-accordion">
        <div v-for="(pointOfInterest, index) in this.pointsOfInterest" :key="`x: ${this.details.x}, y:${this.details.y}`">
          <PointOfInterestDetails
            :context="this.details"
            :details="pointOfInterest"
            :poiIndex="index"
          ></PointOfInterestDetails>
        </div>
      </div>
    </div>
  </div>
</template>
