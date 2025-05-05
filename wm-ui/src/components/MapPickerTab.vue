<script>
export default {
    props: [
        'maps',
        'selectedMap'
    ],
    methods: {
        selectMap(map) {
            this.$emit('update:selectedMap', map)
        }
    }
}
</script>

<template>
    <ul class="nav nav-tabs">
        <li v-for="(map) in maps" :key="map.name" class="nav-item">
            <button class="nav-link" :class="selectedMap === map ? 'active' : ''" v-on:click="selectMap(map)">{{ map.name }}</button>
        </li>
        <li class="nav-item">
            <button class="nav-link"> + </button>
        </li>
    </ul>
</template>