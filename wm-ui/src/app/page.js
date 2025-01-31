'use client'
//#FFF899 -> jaune 0.3
//#
// import Image from "next/image";
// import styles from "./page.module.css";

import { SVG } from '@svgdotjs/svg.js'
import { defineHex, Grid, rectangle } from 'honeycomb-grid'
import { useEffect } from 'react' // useEffect -> 'beurk'
import tippy from 'tippy.js';
import 'tippy.js/dist/tippy.css';
import tippyCard from '@/components/tippyCard.js' 


export default function Home() {
  const colors = ['#cd6565', '#cda365', '#b8cd65', '#7acd65', "#65cd8f", 
                    "#65cdcd", "#658fcd", "#7a65cd", "#b865cd", "#cd65a3"];
  let previousColor = '#b865cd'

  useEffect(() => {
    const Hex = defineHex({ dimensions: 50, origin: 'topLeft' })
    const grid = new Grid(Hex, rectangle({ width: 10, height: 10 })) //chult = 72, 85
    
    const draw = SVG().addTo('.map-container').size('100%', '100%').group()
    .fill('none')
    .stroke({ width: 2, color: '#999' })
    .mouseover(function(e) {
        if(!e.target.attributes.class.value || e.target.attributes.class.value !== 'map-tile') { return }
        previousColor = e.target.attributes.fill.value;
        e.target.instance.fill({ color: '#f06' }); 
    })
    .mouseout(function(e) {
        if(!e.target.attributes.class.value || e.target.attributes.class.value !== 'map-tile') { return }
        e.target.instance.fill({ color: previousColor })
    })
    .click(function(e) { 
        if(document.querySelector('#pop-up').checked) {
            e.target.instance.node._tippy.show(); 
        }
    })
    
    grid.forEach(renderSVG)

    const tippyViews = tippy('.map-tile', {
      content(e) { 
        const coordinates = e.instance.data('coordinates');
        return tippyCard.cardHTML(coordinates);
      },
      theme: 'material',
      trigger: 'manual',
      allowHTML: true,
      interactive: true,
      appendTo: document.body,
    });
    console.log(tippyViews);
    
    function renderSVG(hex) {
      // let className = (hex.col === 5 && hex.row === 5) ? 'desert' : 'map-tile';
      let fill = (hex.col === 5 && hex.row === 5) ? '/biomes/desert.svg' : ''
        const polygon = draw
          .polygon(hex.corners.map(({ x, y }) => `${x},${y}`))
          .addClass('map-tile')
          .attr('data-cy', 'cell')
          .fill(draw.image('/biomes/desert.svg', function() {
            this.size(hex.width, hex.height)
            // this.center(hex.x, hex.y)
            this.x(hex.center.x)
            this.y(hex.center.y)
            this.addClass('desert')
            console.log('hex', hex, 'this', this.center)
          }))
          // .fill({ color: colors[Math.round(hex.col)], opacity: '30%'})
          .data('coordinates' , { value : { x: hex.col, y: hex.row} })


            // Add a text element at the center of the hex
            // if(hex.col === 5 && hex.row === 5) {
            //     draw.foreignObject(polygon.width, polygon.height)
            //         .add(SVG(`<img src="/biomes/desert.svg" alt="desert" />`, true))
            // } else {


            draw
                .text(`${Math.round(hex.col)}, ${Math.round(hex.row)}`)
                .font({ size: 20, anchor: 'middle', leading: '1.2em' })
                // .addClass('map-tile-coordininates')
                .center(hex.x, hex.y)
                .stroke({ width: 0, color: '#999' })
                .fill('#999');
            // }
        return draw.add(polygon)
    }
  })

  return (
      <div>
          <div>
              <input type="checkbox" id="pop-up" name="pop-up" data-cy="event-mode"/>
              <label htmlFor="pop-up">pop-up</label>
          </div>
          <article className="map-container"/>
      </div>
  );
}

