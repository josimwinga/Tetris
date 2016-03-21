package Tetris;
/**
 * This is the pieceProxy. It is
 * a proxy that gets a new
 * piece from the factory and
 * then gives the piece to the
 * board
 *
 *@author <jsimiwing>
 */
public class PieceProxy {
	private BasicPiece _piece;
	public PieceProxy(){}
	
	public BasicPiece setPiece(){
		PieceFactory factory = new PieceFactory();
		return _piece = factory.getPiece();
	}
	public void setLocation(double x, double y){
		_piece.setLocation(x, y);
	}
	public void moveRight(Board board){
		_piece.moveRight(board);
	}
	public void moveLeft(Board board){
		_piece.moveLeft(board);
	}
	public void drop(Board board){
		for (int i = 0; i < Constants.NUM_ROWS; i++){
			_piece.moveDown(board);
		}
	}
	public void moveDown(Board board){
		_piece.moveDown(board);
	}
	public void rotate(Board board){
		_piece.rotate(board);
	}
}
