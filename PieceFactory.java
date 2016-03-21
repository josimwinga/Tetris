package Tetris;
/**
 * This is the PieceFactory.
 * it uses switch statements 
 * to randomly create a 
 * basic piece 
 *
 *@author <jsimiwing>
 */

public class PieceFactory {
	public PieceFactory(){
		
	}
	public BasicPiece getPiece(){
		int rand = (int) (Math.random() * 7);
		BasicPiece piece = null;
		switch (rand){
		case 0: 
			piece = new RedPiece();
			break;
		case 1:
			piece = new PinkPiece();
			break;
		case 2:
			piece = new BluePiece();
			break;
		case 3: 
			piece = new GrayPiece();
			break;
		case 4:
			piece = new OrangePiece();
			break;
		case 5:
			piece = new GreenPiece();
			break;
		case 6:
			piece = new CyanPiece();
			break;
		}
		return piece;
	}

}
