<script>
import { SVG } from "@svgdotjs/svg.js";
import { defineHex, Grid, rectangle } from "honeycomb-grid";
import TileDetailsOffCanvas from "./TileDetailsOffCanvas.vue";
import TileEditModal from "./TileEditModal.vue";
import { Offcanvas } from "bootstrap";
import { Modal } from "bootstrap";

export default {
  props: ["mapData", "tiles"],
  components: {
    TileDetailsOffCanvas,
    TileEditModal,
  },
  data() {
    return {
      primaryColor: "#b865cd",
      previousColor: "#b865cd",
      tileDetails: {},
      offcanvas: null,
    };
  },
  watch: {
    tiles(newTiles) {
      if (newTiles.length && this.mapData) {
        this.initGrid();
      }
    },
  },
  mounted() {
    this.initGrid();
  },
  methods: {
    initGrid() {
      const Hex = defineHex({ dimensions: 50, origin: "topLeft" });
      const grid = new Grid(
        Hex,
        rectangle({ width: this.mapData.width, height: this.mapData.height }),
      ); //chult = 72, 85
      const container = this.$refs.mapContainer;
      container.innerHTML = "";
      const draw = SVG()
        .addTo(container)
        .size(grid.pixelWidth + 4, grid.pixelHeight + 4)
        .group()
        .fill("none")
        .attr({
          role: "tilemap",
          tabindex: "0",
          "aria-label": `${this.mapData.name}`,
        })
        .translate(2, 2)
        .stroke({ width: 3, color: "#999" })
        .mouseover(function (e) {
          e.target.instance.stroke("#f06");
        })
        .mouseout(function (e) {
          e.target.instance.stroke("#999");
        })
        .click((e) => {
          this.offcanvas = new Offcanvas("#offcanvasScrolling");
          const coordinates = e.target.instance.data("coordinates").value;
          this.tileDetails = this.tiles.find(
            (tile) => tile.x === coordinates.x && tile.y === coordinates.y,
          );
          this.offcanvas.show();
        });

      grid.forEach((hex) => {
        this.renderSVG(hex, draw);
      });
    },
    renderSVG(hex, draw) {
      const tile = this.tiles.find((e) => e.x === hex.col && e.y === hex.row);
      const polygon = draw
        .polygon(hex.corners.map(({ x, y }) => `${x},${y}`))
        .attr({
          tabindex: "0",
          role: "button",
          "aria-label": this.$t("map.tileAriaLabel", {
            x: hex.col,
            y: hex.row,
            terrain: tile
              ? this.$t(`terrain.${tile.terrain}`)
              : this.$t("terrain.empty"),
          }),
        })
        .addClass("map-tile")
        .data("coordinates", { value: { x: hex.col, y: hex.row } });

      if (tile) {
        const terrain = tile.terrain;
        polygon.addClass(terrain);

        const iconUrl = new URL(
          `../../assets/svgs/${terrain}.svg`,
          import.meta.url,
        ).href;
        const icon = draw.image(iconUrl);
        icon.size(60, 60).center(hex.x, hex.y);
      } else {
        draw
          .text(`${hex.col},${hex.row}`)
          .font({ size: 20, anchor: "middle", leading: "1.2em" })
          .center(hex.x, hex.y)
          .stroke({ width: 0, color: "#999" })
          .fill("#999");
      }

      return draw.add(polygon);
    },
    showTileEditModal() {
      this.offcanvas.hide();
      const tileEditModal = new Modal("#tileDetailsEditModal");
      tileEditModal.show();
    },
  },
};
</script>

<template>
  <div ref="mapContainer" class="map-container text-center"></div>
  <TileDetailsOffCanvas
    v-if="this.tileDetails"
    :details="this.tileDetails"
    @edit="showTileEditModal"
  ></TileDetailsOffCanvas>
  <TileEditModal
    v-if="this.tileDetails"
    :details="this.tileDetails"
  ></TileEditModal>
</template>
