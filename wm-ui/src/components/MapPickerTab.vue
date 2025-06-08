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
            <button class="nav-link" :class="selectedMap === map ? 'active' : ''" v-on:click="selectMap(map)"><h2>{{ map.name }}</h2></button>
        </li>
        <li class="nav-item">
            <button class="btn" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight"><h2> + </h2></button>
        </li>
    </ul>
</template>