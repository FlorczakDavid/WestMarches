<script>
  import HexMap from '@/components/HexMap.vue';
  import MouseFunctionPicker from '@/components/MouseFunctionPicker.vue';
  import MapPickerTab from "@/components/MapPickerTab.vue"
  import api from '@/services/api';
  import { computed } from 'vue';

  export default {
    data() {
      return {
        maps: null,
        selectedMap: null,
        tiles: null
      }
    },
    provide() {
      return {
        selectedMap: computed(() => this.selectedMap)
      }
    },
    watch: {
      selectedMap(newSelectedMap) {
        api.get('/tile/map', {
        params: { 
          email: localStorage.getItem('user'),
          map: newSelectedMap.name
        } 
      })
      .then(response => {
        this.tiles = response.data;
      })
      .catch(error => console.error(error));
      }
    },
    components: {
      HexMap,
      MouseFunctionPicker,
      MapPickerTab
    },
    created() {
      const user = localStorage.getItem('user')
      const roles = localStorage.getItem('roles')
      const token = localStorage.getItem('token')
      console.log('user: '+user, 'roles: '+roles, 'token:'+ token)
      console.log(localStorage)
      api.get('/map/user', {
        params: { email: user } 
      })
      .then(response => {
        this.maps = response.data;
        this.selectedMap = response.data[0];
      })
      .catch(error => console.error(error));
    },
    methods: {
      logout() {
        localStorage.clear();
        this.$router.push({name: 'home'})
      }
    }
  }
</script>
<template>
  <div>
    <h1>Maps</h1>
    <button v-on:click="logout">Logout</button>
    <MapPickerTab v-if="this.selectedMap && this.maps" :maps="maps" v-model:selectedMap="selectedMap"></MapPickerTab>
    <MouseFunctionPicker></MouseFunctionPicker>
    <HexMap v-if="this.selectedMap && this.tiles" :mapData="this.selectedMap" :key="this.selectedMap.name" :tiles="this.tiles"></HexMap>
  </div>
</template>