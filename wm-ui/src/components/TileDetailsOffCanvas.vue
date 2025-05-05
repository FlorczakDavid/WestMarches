<script>
import PointOfInterestDetails from './PointOfInterestDetails.vue';
import api from '@/services/api';

export default {
    props: {
        details: null
    }, 
    inject: ['selectedMap'],
    components: {
        PointOfInterestDetails
    },
    data() {
        return {
            pointsOfInterest: null,
            selectedTile: {},
        }
    },
    watch: {
        details: function(newDetails) { 
            api.get('/poi/tile', {
                params: { 
                    email: localStorage.getItem('user'),
                    map: this.selectedMap.name,
                    x: newDetails.x,
                    y: newDetails.y
                } 
            })
            .then(response => {
                console.log(response.data.poiName)
                this.pointsOfInterest = response.data;
            })
            .catch(error => console.error(error));
        }
    }
}
</script>

<template>
    <div class="offcanvas offcanvas-start" data-bs-scroll="true" tabindex="-1" id="offcanvasScrolling" aria-labelledby="offcanvasScrollingLabel">
        <div class="offcanvas-header container">
            <h2 class="offcanvas-title col-9" id="offcanvasScrollingLabel">{{ `x: ${this.details.x}, y:${this.details.y}` }}</h2>
            <button type="button" class="btn btn-outline-secondary col" aria-label="Edit">edit</button>
            <button type="button" class="btn-close col" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body">
            <h3> {{ this.details.terrain }} </h3>
            <p> {{ this.details.description }} </p>
            <h4> Points of interest: </h4>
            <div class="accordion accordion-flush" id="poi-accordion">
                <div v-for="(pointOfInterest, index) in this.pointsOfInterest" v-if="this.pointsOfInterest">
                    <PointOfInterestDetails :context="this.details" :details="pointOfInterest" :index="index"></PointOfInterestDetails>
                </div>
            </div>
        </div>
    </div>
</template>