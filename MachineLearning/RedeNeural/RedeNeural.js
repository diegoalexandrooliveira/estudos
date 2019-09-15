class RedeNeural {
    constructor(input_nodes, hidden_nodes, output_nodes){
        this.input_nodes = input_nodes;
        this.hidden_nodes = hidden_nodes;
        this.output_nodes = output_nodes;

        this.bias_input_hidden = new Matrix(this.hidden_nodes, 1);
        this.bias_input_hidden.randomize();
        this.bias_hidden_output = new Matrix(this.output_nodes, 1);
        this.bias_hidden_output.randomize();

        this.weigths_input_hidden = new Matrix(this.hidden_nodes, this.input_nodes);
        this.weigths_input_hidden.randomize();
        this.weigths_hidden_output = new Matrix(this.output_nodes, this.hidden_nodes);
        this.weigths_hidden_output.randomize();


    }

    feedforward(input){
        let input_matrix = Matrix.arrayToMatrix(input);
        let hidden = Matrix.multiply(this.weigths_input_hidden, input_matrix);
        hidden = Matrix.add(hidden,this.bias_input_hidden);
        hidden.map(sigmoid);

        let output = Matrix.multiply(this.weigths_hidden_output, hidden);
        output = Matrix.add(output,this.bias_hidden_output);
        output.map(sigmoid);

        output.print();
    }

    
}

function sigmoid(x){
    return 1/(1+Math.exp(-x));
}