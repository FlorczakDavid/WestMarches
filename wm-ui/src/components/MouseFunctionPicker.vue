<script>
    import terrains from '@/assets/terrains';
    
    export default {
        emits: [

        ],
        data() {
            return {
                terrains,
                currentTerrain: 'Draw',
                display: false,
                draw: false,
                edit: false
            }
        },
        methods: {
            chooseTerrain(terrain) {
                this.currentTerrain.value = terrain
            }
        }
    }
</script>

<template>
    <div class="btn-group" role="group" aria-label="toggle button group">
        <input type="checkbox" class="btn-check" id="display" v-model="display" autocomplete="off" checked/>
        <label class="btn btn-outline" for="display">{{$t('mouseFunctionPicker.displayLabel')}}</label>

        <input type="checkbox" class="btn-check" id="edit" v-model="edit" autocomplete="off"/>
        <label class="btn btn-outline" for="edit">{{$t('mouseFunctionPicker.editLabel')}}</label>
    
        <div class="btn-group">
            <input type="checkbox" class="btn-check" id="draw" v-model="draw" autocomplete="off"/>
            <label class="btn btn-outline" for="draw">{{ currentTerrain }}</label>
            <button type="button" class="btn dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown" aria-expanded="false">
                <span class="visually-hidden">Toggle Dropdown</span>
            </button>
            <ul class="dropdown-menu">
                <li v-for="terrain in this.terrains" :key="terrain">
                    <button class="dropdown-item" @click="chooseTerrain(terrain)">
                        {{ terrain }}
                    </button>
                </li>
            </ul>
        </div>
    </div>
</template>