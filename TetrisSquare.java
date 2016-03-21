package Tetris;
/**
 * This is the Tetris Square. It is a
 * Rectangle Shape that is used to
 * make BasicPieces and the board.
 * It has one unique method.
 *
 * @author <jsimwing>
 * Did you discuss your design with another student?
 * If so, list their login here:
 *
 */
import java.awt.Color;

import cs015.prj.Shape.*;
public class TetrisSquare extends RectangleShape{
	private Color _color;

	public TetrisSquare(){
		super();
		this.setSize(Constants.SQUARE_SIZE, Constants.SQUARE_SIZE);
		this.setBorderColor(Color.black);
		this.setBorderWidth(1);
		setVisible(true);
	}
	public void setTSColor(Color color){
		this.setColor(color);
		_color = color;
	}
	public Color getColor(){
		return _color;
	}
}