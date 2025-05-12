<script>
import api from '../services/api.js'
import permissionService from '@/services/permissionService.js'

// import variables from './variables.module.scss'
import { SVG } from '@svgdotjs/svg.js'
import { defineHex, Grid, rectangle } from 'honeycomb-grid'
import TileDetailsOffCanvas from './TileDetailsOffCanvas.vue'
import { Offcanvas } from 'bootstrap'
// import tippy from 'tippy.js';
// import 'tippy.js/dist/tippy.css';
// import tippyCard from '@/components/tippyCard.js' 

export default {
    props: [
        'mapData',
        'tiles'
        ],
    components: {
        TileDetailsOffCanvas
    },
    data() {
        return {
            primaryColor: '#b865cd',
            previousColor: '#b865cd',
            tileDetails: {},
        }
    },
    watch: {
        tiles(newTiles) {
            if(newTiles.length && this.mapData) {
                this.initGrid();
            }
        }
    },
    mounted() {
        this.initGrid();
    },
    methods: {
        initGrid() {
            const Hex = defineHex({ dimensions: 50, origin: 'topLeft' })
            const grid = new Grid(Hex, rectangle({ width: this.mapData.width, height: this.mapData.height })) //chult = 72, 85
            const container = this.$refs.mapContainer
            container.innerHTML = '';
            const draw = SVG().addTo(container).size(grid.pixelWidth+4, grid.pixelHeight+4).group()
                .fill('none')
                .translate(2, 2)
                .stroke({ width: 2, color: '#999' })
                .mouseover(function(e) {
                    if(!e.target.attributes.class.value || e.target.attributes.class.value !== 'map-tile') { return }
                    this.previousColor = e.target.attributes.fill.value;
                    e.target.instance.fill({ color: '#f06' }); 
                    e.target.instance.opacity('30%')
                })
                .mouseout(function(e) {
                    if(!e.target.attributes.class.value || e.target.attributes.class.value !== 'map-tile') { return }
                    e.target.instance.fill({ color: this.previousColor })
                    e.target.instance.opacity('100%')
                })
                .click((e) => { 
                    // TODO - if Display checked, do something
                    // TODO - if Edit checked, do something
                    // TODO - if Draw checked, do something
                    const bsOffcanvas = new Offcanvas('#offcanvasScrolling')
                    const coordinates = e.target.instance.data('coordinates').value;
                    this.tileDetails = this.tiles.find((tile) => tile.x === coordinates.x && tile.y === coordinates.y);
                    bsOffcanvas.show();
                })

            // const tippyViews = tippy('.map-tile', {
            //     content(e) { 
            //         const coordinates = e.instance.data('coordinates');
            //         return tippyCard.cardHTML(coordinates);
            //     },
            //     theme: 'material',
            //     trigger: 'manual',
            //     allowHTML: true,
            //     interactive: true,
            //     appendTo: document.body,
            // });
            // console.log(tippyViews);

            grid.forEach(hex => { this.renderSVG(hex, draw); })
        },
        renderSVG(hex, draw) {
            // let className = (hex.col === 5 && hex.row === 5) ? 'desert' : 'map-tile';
            let fill = (hex.col === 5 && hex.row === 5) ? '/biomes/desert.svg' : ''
            const polygon = draw
                .polygon(hex.corners.map(({ x, y }) => `${x},${y}`))
                .addClass('map-tile')
                .attr('data-cy', 'cell')
                .data('coordinates' , { value : { x: hex.col, y: hex.row } })
                // .fill({ color: colors[Math.round(hex.col)], opacity: '30%'})
                // .fill(draw.image('/biomes/desert.svg', function() {
                //     this.size(hex.width, hex.height)
                //     // this.center(hex.x, hex.y)
                //     this.x(hex.center.x)
                //     this.y(hex.center.y)
                //     this.addClass('desert')
                //     console.log('hex', hex, 'this', this.center)
                // }))


            // Add a text element at the center of the hex
            // if(hex.col === 5 && hex.row === 5) {
            //     draw.foreignObject(polygon.width, polygon.height)
            //         .add(SVG(<img src="/biomes/desert.svg" alt="desert" />, true))
            // } else {
            if(this.tiles.find((e) => e.x === hex.col && e.y === hex.row)) {
            polygon.addClass(this.tiles.find((e) => e.x === hex.col && e.y === hex.row).terrain);
            draw
                // .text(`${Math.round(hex.col)}, ${Math.round(hex.row)}`)
                .text(this.tiles.find((e) => e.x === hex.col && e.y === hex.row).terrain)
                .font({ size: 20, anchor: 'middle', leading: '1.2em' })
                // .addClass('map-tile-coordininates')
                .center(hex.x, hex.y)
                .stroke({ width: 0, color: '#999' })
                .fill('#999');
            } else {
                draw
                .text(`${Math.round(hex.col)}, ${Math.round(hex.row)}`)
                .font({ size: 20, anchor: 'middle', leading: '1.2em' })
                // .addClass('map-tile-coordininates')
                .center(hex.x, hex.y)
                .stroke({ width: 0, color: '#999' })
                .fill('#999');
            }
            // }
        return draw.add(polygon)
        }
    }
}
</script>

<template>
    <div>
        <h1>HEXMAP HERE</h1>
        <h2> {{ this.mapData }}</h2>
    </div>
    <article ref="mapContainer" class="map-container"></article>
    <p v-for="tile in this.tiles"> {{ tile }} </p>
    <TileDetailsOffCanvas v-if="this.tileDetails" :details="this.tileDetails"></TileDetailsOffCanvas>
</template>