package boardgame;

public class Board {		//não retorna uma matriz inteira, somente uma peça por vez.
	
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public Piece piece(int row, int column) {	//retorna a matriz na linha e coluna informada
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {	//retorna a peça pela posição(classe Position)
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {		//coloca essa peça nessa posição
		pieces[position.getRow()][position.getColumn()] = piece; 		//essa matriz de peças é a que está declarada aqui no tabuleiro e ja foi instanciada no construtor.
		piece.position = position;
	}
}
