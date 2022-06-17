package boardgame;

public class Piece {
	
	//Essa n�o � uma posi��o do tabuleiro, � a posi��o da matriz, por isso ela ser� protegida para n�o ficar vis�vel na outra camada.
	protected Position position;
	private Board board;	//criando uma conex�o com o tabuleiro(Board)
	
	public Piece(Board board) {
		this.board = board;
		position = null;	//� nula porque quando a pe�a � criada ela n�o est� no tabuleiro ainda.
		
	}

	protected Board getBoard() {	//o tabuleiro n�o pode ser acessivel pela camada de xadrez.
		return board;
	}

}
