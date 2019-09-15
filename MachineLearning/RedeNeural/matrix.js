class Matrix {

    constructor(rows, cols){
        this.rows = rows;
        this.cols = cols;

        this.data = [];

        for (let index = 0; index < rows; index++) {
            let array = [];
            for (let j = 0; j < cols; j++) {
                
                array.push(0);
                
            }

            this.data.push(array);
            
        }
    }

    map(func){
        this.data = this.data.map((arr, i)=> {
            return arr.map((num, j)=> {
                   return func(num, i,j);          
            })
        });
        return this;
    }

    static map(A, func){
        let matrix = new Matrix(A.rows, A.cols);
        matrix.data = matrix.data.map((arr, i)=> {
            return arr.map((num, j)=> {
                   return func(num, i,j);          
            })
        });
        return matrix;
    }

    static add(A, B){
        let matrix = new Matrix(A.rows, A.cols);        
        matrix.map((elemento, i, j)=> {
            return A.data[i][j]+B.data[i][j];
        });
        return matrix;
    }

    static multiply(A, B){
        let matrix = new Matrix(A.rows, B.cols);
        matrix.map((elemento, i, j)=> {
            let soma = 0;
            for (let k = 0; k < A.cols; k++) {
                let elt1 = A.data[i][k];
                let elt2 = B.data[k][j];
                soma += elt1*elt2;
            }
            return soma;
        });
        return matrix;
    }

    randomize(){
        this.map((elm, i, j)=> {
            return Math.random()*2 - 1;
        });
    }

    print(){
        console.table(this.data);
    }

    static arrayToMatrix(array){
        let matrix = new Matrix(array.length, 1);
        matrix.map((elem, i, j)=> {
            return array[i];
        });
        return matrix;
    }

}