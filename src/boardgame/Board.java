package boardgame;

public class Board {		//n�o retorna uma matriz inteira, somente uma pe�a por vez.
	
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		if (rows <0 || columns <0) {
			throw new BoardException("Error creating board: there must be at 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Piece piece(int row, int column) {	//retorna a matriz na linha e coluna informada
		if (!positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {	//retorna a pe�a pela posi��o(classe Position)
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {		//coloca essa pe�a nessa posi��o
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position" + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece; 		//essa matriz de pe�as � a que est� declarada aqui no tabuleiro e ja foi instanciada no construtor.
		piece.position = position;
	}
	
	public Piece removePiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		if (piece(position) == null) {
			return null;
		}
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}
	
	private boolean positionExists(int row, int column) {		//testa se a posi��o existe pela linha e coluna
		return row >=0 && row < rows && column >=0 && column < columns;
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	//testa se tem uma pe�a nessa posi��o
	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;
	}
	
	
}