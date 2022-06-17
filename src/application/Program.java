package application;

import java.awt.Point;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;

public class Program {

	public static void main(String[] args) {

//teste
		ChessMatch chess = new ChessMatch();
		UI.printBoard(chess.getPieces());		//imprime o tabuleiro com as peças(se houver)
	}

}
