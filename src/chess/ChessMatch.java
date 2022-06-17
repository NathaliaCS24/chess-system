package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch { 	//� nessa classe que teremos as regras de xadrez
	
	private Board board;		//uma partida tem q ter um tabuleiro

	public ChessMatch() {
		board = new Board(8, 8);		//instanciando um tabuleiro 8x8
		initialSetup(); 				//chama as pe�as criadas
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
	private void initialSetup(){//responsavel por iniciar a partida de xadrez colocando as pe�as no tabuleiro
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
		board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
		board.placePiece(new King(board, Color.WHITE), new Position(7, 4));


	}
}
