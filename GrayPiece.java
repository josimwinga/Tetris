package Tetris;
/**
 * This is the GrayPiece. It is a BasicPiece
 * made up of 4 tetris squares
 * and it's location is set based on 1 squares 
 * x and y (2 integers), the other 3 squares
 * x,y are based upon a formula in the 
 * basic piece. 
 *
 *@author <jsimiwing>
 */
import java.awt.Color;

public class GrayPiece extends BasicPiece{

	public GrayPiece(){
		super();
		this.setColor(Color.DARK_GRAY);
		this.setBorderColor(Color.BLACK);
		this.setBorderWidth(1);
	}
	@Override
	public void setLocation(double x, double y){
		this.setAllLocation(x, y,
				Constants.SQUARE_SIZE, 0.0,
				0.0, Constants.SQUARE_SIZE,
				0.0, (Constants.SQUARE_SIZE * 2));
	}	
}
