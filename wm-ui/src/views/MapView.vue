<script>
import HexMap from "@/components/map/HexMap.vue";
import MouseFunctionPicker from "@/components/map/MouseFunctionPicker.vue";
import MapPickerTab from "@/components/map/MapPickerTab.vue";
import { computed } from "vue";

export default {
  data() {
    return {
      maps: null,
      selectedMap: null,
      tiles: null,
    };
  },
  provide() {
    return {
      selectedMap: computed(() => this.selectedMap),
    };
  },
  watch: {
    selectedMap(newSelectedMap) {
      this.$api
        .get("/tile/map", {
          params: {
            email: localStorage.getItem("user"),
            map: newSelectedMap.name,
          },
        })
        .then((response) => {
          this.tiles = response.data;
        })
        .catch(() => { 
          /* already handled by interceptor */
        });
    },
  },
  components: {
    HexMap,
    MouseFunctionPicker,
    MapPickerTab,
  },
  created() {
    const user = localStorage.getItem("user");
    const roles = localStorage.getItem("roles");
    const token = localStorage.getItem("token");
    this.$api
      .get("/map/user", {
        params: { email: user },
      })
      .then((response) => {
        this.maps = response.data;
        this.selectedMap = response.data[0];
      })
      .catch(() => { 
        /* already handled by interceptor */
      });
  },
};
</script>
<template>
  <div class="head">
    <h1>{{ $t("map.title") }}</h1>
    <MapPickerTab
      v-if="this.selectedMap && this.maps"
      :maps="maps"
      v-model:selectedMap="selectedMap"
    ></MapPickerTab>
  </div>
  <div class="content">
    <HexMap
      v-if="this.selectedMap && this.tiles"
      :mapData="this.selectedMap"
      :key="this.selectedMap.name"
      :tiles="this.tiles"
    ></HexMap>
  </div>
</template>
