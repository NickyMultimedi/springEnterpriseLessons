window.addEventListener("load", init);

function init() {
    const getBeerButton = document.getElementById("getBeerBy");
    const getOrderButton = document.getElementById("getOrderBy");
    const getPlaceOrderButton = document.getElementById("placeOrder");

    getBeerButton.addEventListener("click", getBeer);
    getOrderButton.addEventListener("click", getOrder);
    getPlaceOrderButton.addEventListener("click", placeOrder);
}

function getBeer() {
    const beerId = document.getElementById("beerId").value;
    const httpRequest = new XMLHttpRequest();
    httpRequest.onload = onBeerLoad;
    httpRequest.open("GET", "beers/" + beerId, true);
    httpRequest.setRequestHeader("Accept", "application/json");
    httpRequest.send(null);
}

function getOrder() {
    const orderId = document.getElementById("orderId").value;
    const httpRequest = new XMLHttpRequest();
    httpRequest.onload = onOrderLoad;
    httpRequest.open("GET", "orders/" + orderId, true);
    httpRequest.setRequestHeader("Accept", "application/json");
    httpRequest.setRequestHeader("Authorization", "Basic " + btoa("Nick:NickyPw"));
    httpRequest.send(null);
}

function placeOrder() {
    const orderName = document.getElementById("orderName").value;
    const orderBeerId = document.getElementById("orderedBeer").value;
    const orderBeerAmount = document.getElementById("orderedAmount").value;
    let orderItem = {name: orderName, beerId: orderBeerId, beerAmount: orderBeerAmount};

    const httpRequest = new XMLHttpRequest();
    httpRequest.onload = onPlaceOrderLoad;
    httpRequest.open("POST", "orders", true);
    httpRequest.setRequestHeader("Accept", "*/*");
    httpRequest.setRequestHeader("Content-Type", "application/json");
    httpRequest.setRequestHeader("Authorization", "Basic " + btoa("Nick:NickyPw"));
    // let orderItem = '{"name" : "' + orderName + '", "beerId" : "' + orderBeerId +'", "beerAmount" : "' + orderBeerAmount +'" }';
    console.log(JSON.stringify(orderItem));
    httpRequest.send(JSON.stringify(orderItem));
}

function onBeerLoad() {
    const field = document.getElementById("contentField");
    let beerText = "";
    switch (this.status) {
        case 200:
            const beer = JSON.parse(this.responseText);
            beerText = beer.name + "\n" +
                beer.price + " euro per bottle\n" +
                beer.alcohol + "% alcohol percentage\n" +
                beer.stock + " bottles in stock\n" +
                beer.brewer.name + " brewer name\n" +
                beer.category.name + " category";
            break;
        case 404:
            beerText = "Not found";
            break;
        default:
            beerText = "Not working";
    }
    field.innerText = beerText;
}

function onOrderLoad() {
    const field = document.getElementById("contentField");
    let orderText = "";
    switch (this.status) {
        case 200:
            const order = JSON.parse(this.responseText);
            orderText =
                order.name + "\n";
            let i;
            for (i in order.items) {
                orderText += order.items[i].beer.name + " -> " + order.items[i].number + "\n";
            }
            break;
        case 404:
            orderText = "Not found";
            break;
        default:
            orderText = "Not working";
    }
    field.innerText = orderText;
}

function onPlaceOrderLoad() {
    const field = document.getElementById("contentField");
    let orderPlacedText = "";
    switch (this.status) {
        case 200:
            orderPlacedText = "Order is placed";
            break;
        case 404:
            orderPlacedText = "Order wasn't found";
            break;
        default:
            orderPlacedText = "Oeps, something went wrong";
    }
    field.innerText = orderPlacedText;
}
