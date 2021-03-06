package boardgame;

public class Position { //camada de tabuleiro
	
	private int row;
	private int column;
	
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	//atualiza os valores de uma posi??o
	public void setValues(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	//imprimir a posi??o na tela
	@Override
	public String toString() {
		return row + ", " + column;
	}
}
