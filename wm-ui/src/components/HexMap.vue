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
  const tile = this.tiles.find((e) => e.x === hex.col && e.y === hex.row);
  const polygon = draw
    .polygon(hex.corners.map(({ x, y }) => `${x},${y}`))
    .addClass('map-tile')
    .data('coordinates', { value: { x: hex.col, y: hex.row } });

  if (tile) {
    const terrain = tile.terrain;
    polygon.addClass(terrain);

    const icon = draw.image(`/src/assets/svgs/${terrain}.svg`);
    icon.size(60, 60).center(hex.x, hex.y);
  } else {
    draw.text(`${hex.col},${hex.row}`)
      .font({ size: 20, anchor: 'middle', leading: '1.2em' })
      .center(hex.x, hex.y)
      .stroke({ width: 0, color: '#999' })
      .fill('#999');
  }

  return draw.add(polygon);
}
,
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
    <article ref="mapContainer" class="map-container text-center"></article>
    <TileDetailsOffCanvas v-if="this.tileDetails" :details="this.tileDetails" @edit="showTileEditModal"></TileDetailsOffCanvas>
    <TileEditModal v-if="this.tileDetails" :details="this.tileDetails" ></TileEditModal>
    <div class="maquette">
        <div class="offcanvas offcanvas-end" data-bs-scroll="true" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
            <div class="offcanvas-header container head">
                <h1 class="offcanvas-title col-9" id="offcanvasRightLabel"> Player List </h1>
                <button type="button" class="btn-close col" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body content">
                <div class="input-group mb-3">
                    <span class="input-group-text material-symbols-outlined" id="basic-addon1">search</span>
                    <input type="text" class="form-control" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1">
                </div>
                <ul class="list-group">
                    <li class="list-group-item odd" aria-current="true">QweUser</li>
                    <li class="list-group-item active even">PlayerFive</li>
                    <li class="list-group-item odd">مستخدم</li>
                    <li class="list-group-item even">Alex</li>
                    <li class="list-group-item odd">Bertrand</li>
                    <li class="list-group-item even">Claude</li>
                    <li class="list-group-item odd">Daniel</li>
                    <li class="list-group-item even">Emmanuelle</li>
                    <li class="list-group-item odd">Félix</li>
                    <li class="list-group-item even">Gérard</li>
                    <li class="list-group-item odd">Hubert</li>
                </ul>
            </div>
        </div>
    </div>
</template>