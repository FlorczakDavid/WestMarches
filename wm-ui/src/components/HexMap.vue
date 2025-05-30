<script>
import { SVG } from '@svgdotjs/svg.js'
import { defineHex, Grid, rectangle } from 'honeycomb-grid'
import TileDetailsOffCanvas from './TileDetailsOffCanvas.vue'
import TileEditModal from './TileEditModal.vue'
import { Offcanvas } from 'bootstrap'
import { Modal } from 'bootstrap';

export default {
    props: [
        'mapData',
        'tiles'
        ],
    components: {
        TileDetailsOffCanvas,
        TileEditModal
    },
    data() {
        return {
            primaryColor: '#b865cd',
            previousColor: '#b865cd',
            tileDetails: {},
            offcanvas: null
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
                .stroke({ width: 3, color: '#999' })
                .mouseover(function(e) {
                    // if(!e.target.attributes.class.value || e.target.attributes.class.value !== 'map-tile') { return }
                    e.target.instance.stroke('#f06'); 
                })
                .mouseout(function(e) {
                    // if(!e.target.attributes.class.value || e.target.attributes.class.value !== 'map-tile') { return }
                    e.target.instance.stroke('#999')
                })
                .click((e) => { 
                    // TODO - if Display checked, do something
                    // TODO - if Edit checked, do something
                    // TODO - if Draw checked, do something
                    this.offcanvas = new Offcanvas('#offcanvasScrolling')
                    const coordinates = e.target.instance.data('coordinates').value;
                    this.tileDetails = this.tiles.find((tile) => tile.x === coordinates.x && tile.y === coordinates.y);
                    this.offcanvas.show();
                })

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
        },
        showTileEditModal() {
            this.offcanvas.hide();
            const tileEditModal = new Modal('#tileDetailsEditModal')
            // const coordinates = e.target.instance.data('coordinates').value;
            // this.tileDetails = this.tiles.find((tile) => tile.x === coordinates.x && tile.y === coordinates.y);
            tileEditModal.show();
        }
    }
}
</script>

<template>
    <div class="debug">
        <h1>HEXMAP HERE</h1>
        <h2> {{ this.mapData }}</h2>
    </div>
    <article ref="mapContainer" class="map-container"></article>
    <TileDetailsOffCanvas v-if="this.tileDetails" :details="this.tileDetails" @edit="showTileEditModal"></TileDetailsOffCanvas>
    <TileEditModal v-if="this.tileDetails" :details="this.tileDetails" ></TileEditModal>
</template>