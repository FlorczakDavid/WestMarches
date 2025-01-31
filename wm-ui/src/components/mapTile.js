import { Polygon, SVG } from '@svgdotjs/svg.js'

export default class MapTile {
    static defaultTile(draw, hex) {
        return draw
        .polygon(hex.corners.map(({ x, y }) => `${x},${y}`))
        .fill('none')
        .stroke({ width: 1, color: '#999' })
        .mouseover(function() {
            this.fill({ color: '#f06' })
        })
        .mouseout(function() {
            this.fill({ color: 'none' })
        })
    }
}