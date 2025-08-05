<script>
import api from '@/services/api';

export default {
    props: {
        details: null
    }, 
    inject: ['selectedMap'],
    data() {
        return {
            tileData: {
                description: 'description placeholder',
                pointsOfInterest: [{
                    name: 'poiName',
                    description: 'poiDesc',
                    events: [{
                        name: 'eventName',
                        description: 'eventDesc'
                    }] 
                }]
            },
            validation: {
                uniqueEventNames: true,
                eventNameLength: true,
                uniquePoiNames: true,
                poiNameLength: true,
                emptyField: true,
                isValid() {
                    return this.uniqueEventNames && 
                    this.eventNameLength && 
                    this.uniquePoiNames &&
                    this.poiNameLength &&
                    this.emptyField;
                },
                errors() {
                    const ret = [];
                    if(!this.uniqueEventNames) { ret.push('uniqueEventNames') }
                    if(!this.eventNameLength) { ret.push('eventNameLength') }
                    if(!this.uniquePoiNames) { ret.push('uniquePoiNames') }
                    if(!this.poiNameLength) { ret.push('poiNameLength') }
                    if(!this.emptyField) { ret.push('emptyField') }
                    return ret;
                },
                resetBools() {
                    this.uniqueEventNames = true;
                    this.eventNameLength = true;
                    this.uniquePoiNames = true;
                    this.poiNameLength = true;
                    this.emptyField = true;
                }
            }
        }
    },
    mounted() {
        const editModal = document.getElementById('tileDetailsEditModal')
        editModal.addEventListener('show.bs.modal', () => {
            api.get('/tile/', {
                params: { 
                    email: localStorage.getItem('user'),
                    map: this.selectedMap.name,
                    x: this.details.x,
                    y: this.details.y
                } 
            })
            .then(response => {
                console.log(response.data)
                this.tileData = response.data;
            })
            .catch(error => console.error(error));
        })
    },
    methods: {
        addPoi() {
            this.tileData.pointsOfInterest.push({
                name: '',
                description: '',
                events: []
            })
        },
        addEvent(poiIndex) {
            this.tileData.pointsOfInterest[poiIndex].events.push({
                name: '',
                description: ''
            })
        },
        removePoi(poiIndex) {
            this.tileData.pointsOfInterest.splice(poiIndex, 1);
        },
        removeEvent(poiIndex, eventIndex) {
            this.tileData.pointsOfInterest[poiIndex].events.splice(eventIndex, 1);
        },
        submit() {
            if(this.validate()) {
                console.log(JSON.stringify(this.tileData));
                api.patch('/tile/', {
                    email: localStorage.getItem('user'),
                    map: this.selectedMap.name,
                    x: this.details.x,
                    y: this.details.y,
                    details: this.tileData
                })
                .then(response => {
                    if(response.status == 204) {
                        console.log('data updated')
                        editModal.hide();
                    }
                })
                .catch(error => console.error(error));
            }
        },
        validate() {
            this.validation.resetBools();
            if(this.tileData.pointsOfInterest.length > 0) {
                if(!this.ArrayCheckDeepDistinct(this.tileData.pointsOfInterest, 'name')) {
                    this.validation.uniquePoiNames = false;
                }
                for(const poi of this.tileData.pointsOfInterest) {
                    if(!poi.name) {
                        this.validation.emptyField = false;
                    }
                    if(!this.ArrayCheckDeepDistinct(poi.events, 'name')) {
                        this.validation.uniqueEventNames = false;
                    }
                    if(poi.name.length > 100) {
                        this.validation.poiNameLength = false;
                    }
                    if(poi.events.length > 0) {
                        for(const poiEvent of poi.events) {
                            if(!poiEvent.name) {
                                this.validation.emptyField = false;
                            }
                            if(poiEvent.name.length > 100) {
                                this.validation.eventNameLength = false;
                            }
                        }
                    }
                }
            }
            return this.validation.isValid();
        },
        ArrayCheckDeepDistinct(array, fieldName) {
            const fieldArray = [];
            for(const element of array) {
                fieldArray.push(element[fieldName]);
            }
            const checkSet = new Set(fieldArray);
            return checkSet.size === fieldArray.length;  
        }
    }
}
</script>

<template>
    <div class="modal modal-lg fade" id="tileDetailsEditModal" tabindex="-1" aria-labelledby="tileDetailsEditModalTitleLabel" 
        aria-hidden="true" data-bs-backdrop="static" data-bs-keyboard="false">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title" id="tileDetailsEditModalTitleLabel">{{$t('editTileModal.modalTitle')}}</h1>
                </div>
                <div class="modal-body">
                    <div v-for="error in this.validation.errors()" :key="error">
                        <p class="rule" style="color: red;">{{ error }}</p>
                    </div>
                    <form action="submit">
                        <div class="mb-3" id="tileDetailsMain">
                            <h2>{{$t('editTileModal.tileDetailsTitle')}}</h2>
                            <h3>{{$t('editTileModal.coordinates')}}</h3>
                            <p class="math" :aria-label="$t('tile.aria.coordinates')">{{'x: '+ this.details.x +', y: '+ this.details.y}}</p>
                            <h3 for="floatingTextarea">{{$t('editTileModal.description')}}</h3>
                            <textarea class="form-control" :aria-label="$t('tile.aria.description')" id="descriptionTextarea" v-model="this.tileData.description"></textarea>
                        </div>
                        <div class="mb-3" id="tileDetailsPointsOfInterests">
                            <h2>{{$t('editTileModal.poiSubtitle')}}</h2>
                            <div class="card mb-3" v-for="(poi, poiIndex) in this.tileData.pointsOfInterest" :key="poi.name">
                                <div class="card-body">
                                    <div class="mb-3">
                                        <div class="row">
                                            <div class="col-sm-11">
                                                <input type="text" class="card-title form-control form-control-lg" :aria-label="$t('tile.aria.poiNameInput')" :placeholder="$t('tile.poiNameInputPlaceholder')" v-model="this.tileData.pointsOfInterest[poiIndex].name"/>
                                            </div>
                                            <div class="col-sm-1">
                                                <button type="button" class="btn btn-outline-secondary btn-lg" @click="this.removePoi(poiIndex)" :aria-label="$t('tile.aria.poiDeleteButton')">x</button>
                                            </div>
                                        </div>
                                        <textarea class="form-control" :aria-label="$t('tile.aria.poiDescTextarea')" :placeholder="$t('tile.poiDescTextareaPlaceholder')" v-model="this.tileData.pointsOfInterest[poiIndex].description"></textarea>
                                    </div>
                                    <div class="card mb-3" v-for="(event, eventIndex) in poi.events" :key="event.name">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input type="text" class="card-title form-control form-control-lg col-sm-10" :aria-label="$t('tile.aria.eventNameInput')" :placeholder="$t('tile.eventNameInputPlaceholder')" v-model="this.tileData.pointsOfInterest[poiIndex].events[eventIndex].name"/>
                                                </div>
                                                <div class="col-sm-1">
                                                    <button type="button" class="btn btn-outline-secondary btn-lg" @click="this.removeEvent(poiIndex, eventIndex)">x</button>
                                                </div>
                                            </div>
                                            <textarea class="form-control" :aria-label="$t('tile.aria.eventDescTextarea')" :placeholder="$t('tile.eventDescTextareaPlaceholder')" v-model="this.tileData.pointsOfInterest[poiIndex].events[eventIndex].description"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="card text-center">
                                    <div class="card-body">
                                        <button type="button" class="btn btn-outline-secondary" @click="this.addEvent(poiIndex)">{{$t('editTileModal.addEvent')}}</button>
                                    </div>
                                </div>
                            </div>
                            <div class="text-center">
                                <button type="button" class="btn btn-outline-secondary" @click="this.addPoi()">{{$t('editTileModal.addPOI')}}</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">{{$t('editTileModal.cancel')}}</button>
                    <button type="submit" @click="this.submit()" class="btn btn-primary">{{$t('editTileModal.save')}}</button>
                </div>
            </div>
        </div>
    </div>
</template>