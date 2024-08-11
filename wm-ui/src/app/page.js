import 'bootstrap/dist/css/bootstrap.min.css';

export default async function Page() {

    const data = await fetch('http://localhost:8080/campaigns/list').then((res) =>
        res.json()
    )
    const listItems = data.map((campaign, index) =>
        <li class={`${index % 2 === 0 ? "list-group-item" : "list-group-item list-group-item-secondary"}`}>
            <div class="d-flex justify-content-between">
            <h5>{campaign.campaignName}:</h5>
            <img
                //src={getImageUrl(campaign.campaignImage)}
                alt={campaign.campaignImage}
            />
            </div>
            <p> {' ' + campaign.campaignDesc} </p>
        </li>
    );

    return (
        <main>
            <ul class="list-group">
                {listItems}
            </ul>
        </main>
    );
}

