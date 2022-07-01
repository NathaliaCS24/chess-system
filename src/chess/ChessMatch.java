package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch { 	//� nessa classe que teremos as regras de xadrez
	
	private int turn;
	private Color currentPlayer;
	private Board board;		//uma partida tem q ter um tabuleiro
	private boolean check;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {
		board = new Board(8, 8);		//instanciando um tabuleiro 8x8
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup(); 				//chama as pe�as criadas
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}

	//retorna uma matriz de pe�as de xadrez corresponde a essa partida.
	public ChessPiece[][] getPieces(){ 		
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i=0; i<board.getRows(); i++) {
			for (int j=0; j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition(); 	//converte a posi��o de xadres p matriz
		validateSourcePosition(position); 
		return board.piece(position).possibleMoves();
	}
	
	//move a pe�a de uma posi��o para outra
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition(); //converte a posi��o de xadrez para poso��o de matriz
		Position target = targetPosition.toPosition();
		validateSourcePosition(source); //validar se a posi��o de origem existe
		validateTargetPosition(source, target); 	//valida posi��o de destino
		Piece capturedPiece = makeMove(source, target); //makeMove ser� respons�vel por realizar o movimento da pe�a
		
		if (testCheck(currentPlayer)){		//testa se o proprio jogador colocou o rei em check
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in check");
		}
		
		//testando se a partida esta em check utilizando operador ternario
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		nextTurn();
		return (ChessPiece) capturedPiece;
	}
	
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {	//verifica se h� uma pe�a na posi��o
			throw new ChessException("There is no piece on source position");
		}
		if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) { 	//verifica se h� movimentos para a pe�a
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position");
		}
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source); //remove a pe�a que est� na origem
		Piece capturedPiece = board.removePiece(target); //remove a poss�vel pe�a que esteja na posi��o de destino
		board.placePiece(p, target); //coloca a pe�a de origem na posi��o de destino
		
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		return capturedPiece;
	}
	
	//desfaz um movimento 
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		Piece p = board.removePiece(target); 	//remove a pe�a da posi��o de destino
		board.placePiece(p, source); 	//coloca a pe�a na posi��o inicial novamente
		
		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);	//coloca a pe�a capturada de volta na posi��o dela
			capturedPieces.remove(capturedPiece);	//remove da lista de capturadas
			piecesOnTheBoard.add(capturedPiece);	//coloca na lista de pe�as no tabuleiro
		}
	}
	
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE; 	
	}
	
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	// filtrando a lista de pe�as para achar o rei usando express�es lambda e stream
	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board");
	}
	
	//testando se o rei dessa cor est� em check
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();  	// pega a posi��o do rei em formato de matriz
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList()); //cria uma lista de pe�as do oponente
		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}
	
	//Opera��o de colocar pe�a passando as coordenadas do xadrez, recebe as coordenadas do xadrez e depois a pe�a
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition()); 	//coloca a pe�a no tabuleiro
		piecesOnTheBoard.add(piece); 	//coloca a pe�a na lista de pe�as no tabuleiro
	}
	
	private void initialSetup(){//responsavel por iniciar a partida de xadrez colocando as pe�as no tabuleiro
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}
