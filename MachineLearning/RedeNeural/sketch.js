function setup(){

    createCanvas(500,500);
    background(0);

    let rede = new RedeNeural(2, 2, 1);
    rede.feedforward([1,2]);
}

function draw(){

}