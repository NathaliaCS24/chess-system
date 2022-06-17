package boardgame;

public class Piece {
	
	//Essa não é uma posição do tabuleiro, é a posição da matriz, por isso ela será protegida para não ficar visível na outra camada.
	protected Position position;
	private Board board;	//criando uma conexão com o tabuleiro(Board)
	
	public Piece(Board board) {
		this.board = board;
		position = null;	//é nula porque quando a peça é criada ela não está no tabuleiro ainda.
		
	}

	protected Board getBoard() {	//o tabuleiro não pode ser acessivel pela camada de xadrez.
		return board;
	}

}
