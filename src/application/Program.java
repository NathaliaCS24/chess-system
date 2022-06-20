package application;

import java.awt.Point;
import java.util.Scanner;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

//teste
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();  //instancia uma nova partida
		
		while(true) {
			UI.printBoard(chessMatch.getPieces());		//imprime o tabuleiro com as peças(se houver)
			System.out.println();
			System.out.print("Source: ");
			ChessPosition source = UI.readChessPosition(sc);
			
			System.out.println();
			System.out.print("Target: ");
			ChessPosition target = UI.readChessPosition(sc);
			
			ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
		}
	}

}
