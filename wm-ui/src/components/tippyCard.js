// import { Polygon, SVG } from '@svgdotjs/svg.js'

export default class TippyCard {
    static cardHTML(data) {
        const coordinates = data;
        let isEditing = true;

        const handleEditClick = () => { this.isEditing = true };

        return /* html */`
        <div class="card d-flex">
            <div class="card-body">
                <h1 class="card-title"><nav class="navbar bg-body-tertiary">
                    <div class="container-fluid">
                    ${isEditing ? 
                        '<input type="text" value="Terrain" onChange={handleInputChange}>' 
                            : 
                        '<a class="navbar-brand">Terrain</a>'
                    }
                        <button class="btn btn-primary d-flex" type="button" onClick={handleEditClick}>
                            edit
                        </button>
                    </div>
                </h1>
                <h2 class="card-subtitle mb-2 text-body-secondary">${coordinates.value.x}, ${coordinates.value.y}</h2>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content: details</p>
            </div>
        </div>
    `
    }
}