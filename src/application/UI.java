package application;

import chess.ChessPiece;

public class UI {		//recebe a matriz de pe�as da partida
	
	public static void printBoard(ChessPiece[][] pieces) { 		//imprime o tabuleiro com as pe�as
		for (int i=0; i < pieces.length; i++) {
			System.out.print((8-i) + " ");
			for (int j=0; j<pieces.length; j++) {
				printPiece(pieces[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	private static void printPiece(ChessPiece piece) {		//imprime uma pe�a
		if (piece == null) {
			System.out.print("-");
		}
		else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}

}
