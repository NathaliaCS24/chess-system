package chess;

import boardgame.Board;

public class ChessMatch { 	//� nessa classe que teremos as regras de xadrez
	
	private Board board;		//uma partida tem q ter um tabuleiro

	public ChessMatch() {
		board = new Board(8, 8);		//instanciando um tabuleiro 8x8
	}

	public ChessPiece[][] getPieces(){ 		//retorna uma matriz de pe�as de xadrez corresponde a essa partida.
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i=0; i<board.getRows(); i++) {
			for (int j=0; j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
}
