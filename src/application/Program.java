package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

//teste
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();  //instancia uma nova partida
		List<ChessPiece> captured = new ArrayList<>();
		
		while(true) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);		//imprime o tabuleiro com as peças(se houver)
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMovies = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMovies);		
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
						
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
			
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
			}
			catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}
}
