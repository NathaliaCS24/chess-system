package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch { 	//� nessa classe que teremos as regras de xadrez
	
	private int turn;
	private Color currentPlayer;
	private Board board;		//uma partida tem q ter um tabuleiro

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

	public ChessPiece[][] getPieces(){ 		//retorna uma matriz de pe�as de xadrez corresponde a essa partida.
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
		return capturedPiece;
	}
	
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE; 	
	}
	
	//Opera��o de colocar pe�a passando as coordenadas do xadrez, recebe as coordenadas do xadrez e depois a pe�a
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
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
