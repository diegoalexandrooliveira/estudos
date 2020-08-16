import './index.css';

import React from 'react';
import ReactDOM from 'react-dom';

function Square(props) {
    return (
        <button className="square"
            style={{
                'color': props.highlight ? 'red' : 'black'
            }}

            onClick={props.onClick}>
            {props.value}
        </button>
    );
}

class Board extends React.Component {

    renderSquare(i) {
        let highlight;
        if (this.props.winner) {
            highlight = this.props.winner.find((linha) => linha === i) !== undefined;
        }
        return <Square value={this.props.squares[i]}
            onClick={() => this.props.onClick(i)}
            highlight={highlight}
        />;
    }

    render() {
        return (
            <div>
                <div className="board-row">
                    {this.renderSquare(0)}
                    {this.renderSquare(1)}
                    {this.renderSquare(2)}
                </div>
                <div className="board-row">
                    {this.renderSquare(3)}
                    {this.renderSquare(4)}
                    {this.renderSquare(5)}
                </div>
                <div className="board-row">
                    {this.renderSquare(6)}
                    {this.renderSquare(7)}
                    {this.renderSquare(8)}
                </div>
            </div>
        );
    }
}

class Game extends React.Component {


    constructor(props) {
        super(props);
        this.state = {
            history: [{
                squares: Array(9).fill(null),
                ultimaJogada: ''
            }],
            XEhOProximo: true,
            numeroDaJogada: 0
        };
    }

    handleClick(i) {
        const history = this.state.history.slice(0, this.state.numeroDaJogada + 1);
        const current = history[history.length - 1];
        const squares = current.squares.slice();
        if (calculateWinner(squares) || squares[i]) {
            return;
        }
        squares[i] = this.state.XEhOProximo ? 'X' : 'O';
        const jogada = squares[i] + ' ' + this.calcularPosicao(i);
        this.setState({
            history: history.concat([{
                squares: squares,
                ultimaJogada: jogada
            }]),
            XEhOProximo: !this.state.XEhOProximo,
            numeroDaJogada: history.length
        });

    }

    jumpTo(step) {
        this.setState({
            numeroDaJogada: step,
            XEhOProximo: (step % 2) === 0
        });
    }

    render() {
        const history = this.state.history;
        const current = history[this.state.numeroDaJogada];
        const winner = calculateWinner(current.squares);


        const moves = history.map((step, move) => {
            const descricao = move ?
                'Go to move #' + move + ' ' + step.ultimaJogada :
                'Go to game start';
            return (
                <li key={move}>
                    <button onClick={() => this.jumpTo(move)}>{descricao}</button>
                </li>
            );
        });
        let status;
        if (winner) {
            status = 'Winner: ' + winner.ganhador;
        } else {
            status = 'Next player: ' + (this.state.XEhOProximo ? 'X' : 'O');
        }

        return (
            <div className="game">
                <div className="game-board">
                    <Board
                        squares={current.squares}
                        winner={winner?.linhas}
                        onClick={(i) => this.handleClick(i)}
                    />
                </div>
                <div className="game-info">
                    <div>{status}</div>
                    <ol>{moves}</ol>
                </div>
            </div>
        );
    }

    calcularPosicao(i) {
        let posicao;
        if (i <= 2) {
            posicao = '(' + (i + 1) + ',1)';
        } else if (i > 2 && i <= 5) {
            posicao = '(' + (i - 2) + ',2)';
        } else {
            posicao = '(' + (i - 5) + ',3)';
        }
        return posicao;
    }
}

// ========================================

ReactDOM.render(
    <Game />,
    document.getElementById('root')
);

function calculateWinner(squares) {
    const lines = [
        [0, 1, 2],
        [3, 4, 5],
        [6, 7, 8],
        [0, 3, 6],
        [1, 4, 7],
        [2, 5, 8],
        [0, 4, 8],
        [2, 4, 6],
    ];
    for (let i = 0; i < lines.length; i++) {
        const [a, b, c] = lines[i];
        if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]) {
            return {
                linhas: lines[i],
                ganhador: squares[a]
            };
        }
    }
    return null;
}