package Tetris;
/**
 * This is the BasicPiece. It is
 * made up of 4 tetris squares.
 * It has many unique methods
 * and has move checking capabilites
 * that are utilited in the board.
 *
 *@author <jsimiwing>
 */

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

public class BasicPiece {
	private TetrisSquare _b1, _b2, _b3, _b4;
	private Color _color;
	private double _edgeX1, _edgeX2, _edgeY1, _edgeY2, 
					_rX2, _rX3, _rX4, _rY2, _rY3, _rY4;
	
	public BasicPiece(){
		_b1 = new TetrisSquare();
		_b2 = new TetrisSquare();
		_b3 = new TetrisSquare();
		_b4 = new TetrisSquare();	
		_b1.setBorderColor(Color.BLACK);
		_b2.setBorderColor(Color.BLACK);
		_b3.setBorderColor(Color.BLACK);
		_b4.setBorderColor(Color.BLACK);
		//edge check values
		_edgeX1 = (Constants.SQUARE_SIZE * 2);
		_edgeX2 = Constants.BOARD_W - (Constants.SQUARE_SIZE * 2);
		_edgeY1 = (Constants.SQUARE_SIZE * 2);
		_edgeY2 = Constants.BOARD_H - (Constants.SQUARE_SIZE * 2);
	}
	public void setBorderWidth(int width){
		_b1.setBorderWidth(width);
		_b2.setBorderWidth(width);
		_b3.setBorderWidth(width);
		_b4.setBorderWidth(width);
	}
	public void setBorderColor(Color color){
		_b1.setBorderColor(color);
		_b2.setBorderColor(color);
		_b3.setBorderColor(color);
		_b4.setBorderColor(color);
	}//sets the location of all 4 squares at once 
	//in reference to the first square
	public void setAllLocation (double x, double y, 
			double x2, double y2,
			double x3, double y3,
			double x4, double y4){
		_b1.setLocation(x, y);
		_b2.setLocation(x + x2, y + y2);
		_b3.setLocation(x + x3, y + y3);
		_b4.setLocation(x + x4, y + y4);	
		}
	public void setColor(Color color){
		_b1.setTSColor(color);
		_b2.setTSColor(color);
		_b3.setTSColor(color);
		_b4.setTSColor(color);
		_color = color;
	}
	public void setLocation(double x, double y){}
	public Color getColor(){
		return _color;
	}
	public void paint(Graphics g){
		_b1.paint(g);
		_b2.paint(g);
		_b3.paint(g);
		_b4.paint(g);;
	}//move right method and move checks for shape
	public void moveRight(Board board){
		double newX = _b1.getX() + Constants.SQUARE_SIZE;
		double newY = _b1.getY();
		double newX2 = _b2.getX() + Constants.SQUARE_SIZE;
		double newY2 = _b2.getY();
		double newX3 = _b3.getX() + Constants.SQUARE_SIZE;
		double newY3 = _b3.getY();
		double newX4 = _b4.getX() + Constants.SQUARE_SIZE;
		double newY4 = _b4.getY();
		if (board.isNull((int)newX, (int)newY)
			& board.isNull((int)newX2, (int)newY3)
			& board.isNull((int)newX3, (int)newY3)
			& board.isNull((int)newX4, (int)newY4)
			& board.isPaused() != true){
			_b1.setLocation(newX, newY);
			_b2.setLocation(newX2, newY2);
			_b3.setLocation(newX3, newY3);
			_b4.setLocation(newX4, newY4);
		}
	}//move left method and checks for shape
	public void moveLeft(Board board ){
		double newX = _b1.getX() + Constants.NSQUARE_SIZE;
		double newY = _b1.getY();
		double newX2 = _b2.getX() + Constants.NSQUARE_SIZE;
		double newY2 = _b2.getY();
		double newX3 = _b3.getX() + Constants.NSQUARE_SIZE;
		double newY3 = _b3.getY();
		double newX4 = _b4.getX() + Constants.NSQUARE_SIZE;
		double newY4 = _b4.getY();
		if (board.isNull((int)newX, (int)newY)&
			board.isNull((int)newX2, (int)newY3) &
			board.isNull((int)newX3, (int)newY3) &
			board.isNull((int)newX4, (int)newY4)
			& board.isPaused() != true){
			_b1.setLocation(newX, newY);
			_b2.setLocation(newX2, newY2);
			_b3.setLocation(newX3, newY3);
			_b4.setLocation(newX4, newY4);
		}
	}//boolean to tell if shape is allowed to move down
	public boolean canMoveDown(Board board){
		double newY = _b1.getY() + Constants.SQUARE_SIZE;
		double newX = _b1.getX();
		double newY2 = _b2.getY() + Constants.SQUARE_SIZE;
		double newX2 = _b2.getX();
		double newY3 = _b3.getY() + Constants.SQUARE_SIZE;
		double newX3 = _b3.getX();
		double newY4 = _b4.getY() + Constants.SQUARE_SIZE;
		double newX4 = _b4.getX();
		if (board.isNull((int)newX, (int)newY)&
				board.isNull((int)newX2, (int)newY2) &
				board.isNull((int)newX3, (int)newY3) &
				board.isNull((int)newX4, (int)newY4)){
			return true;
		}else{
			return false;
		}
	}//boolean to tell if shape is moving down
	public boolean moveDown(Board board){
		double newY = _b1.getY() + Constants.SQUARE_SIZE;
		double newX = _b1.getX();
		double newY2 = _b2.getY() + Constants.SQUARE_SIZE;
		double newX2 = _b2.getX();
		double newY3 = _b3.getY() + Constants.SQUARE_SIZE;
		double newX3 = _b3.getX();
		double newY4 = _b4.getY() + Constants.SQUARE_SIZE;
		double newX4 = _b4.getX();
		if (this.canMoveDown(board) & board.isPaused() != true){
			_b1.setLocation(newX, newY);
			_b2.setLocation(newX2, newY2);
			_b3.setLocation(newX3, newY3);
			_b4.setLocation(newX4, newY4);
		return true;
		}else{
			return false;
		}
	}//boolean to tell if location is in bounds	
	public boolean inBounds(){
		if (_b1.getX() >= _edgeX1 & _b1.getX() <= _edgeX2 &
			_b1.getY() >= _edgeY1 & _b1.getY() <= _edgeY2 &
			_b2.getX() >= _edgeX1 & _b2.getX() <= _edgeX2 &
			_b2.getY() >= _edgeY1 & _b2.getY() <= _edgeY2 &
			_b3.getX() >= _edgeX1 & _b3.getX() <= _edgeX2 &
			_b3.getY() >= _edgeY1 & _b3.getY() <= _edgeY2 &
			_b4.getX() >= _edgeX1 & _b4.getX() <= _edgeX2 &
			_b4.getY() >= _edgeY1 & _b4.getY() <= _edgeY2){
			return true;
		}else{
			return false;
		}
	}//get x and y methods for subclassed methods
	public double getX(){
		return _b1.getX();	
	}
	public double getX2(){
		return _b2.getX();	
	}
	public double getX3(){
		return _b3.getX();	
	}
	public double getX4(){
		return _b4.getX();	
	}
	public double getY(){
		return _b1.getY();
	}
	public double getY2(){
		return _b2.getY();
	}
	public double getY3(){
		return _b3.getY();
	}
	public double getY4(){
		return _b4.getY();
	}//check to see if rotation is legal
	public boolean canRotate(){
		Point cPoint = new Point();
		cPoint.x = (int)_b1.getX();
		cPoint.y = (int)_b1.getY();
		_rX2 = cPoint.x - cPoint.y + _b2.getY();
		_rY2 = cPoint.x + cPoint.y - _b2.getX();
		_rX3 = cPoint.x - cPoint.y + _b3.getY();
		_rY3 = cPoint.x + cPoint.y - _b3.getX();
		_rX4 = cPoint.x - cPoint.y + _b4.getY();
		_rY4 = cPoint.x + cPoint.y - _b4.getX();
		if (_rX2 >= _edgeX1 & _rX2 <= _edgeX2 &
			_rY2 >= _edgeY1 & _rY2 <= _edgeY2 &
			_rX3 >=_edgeX1 & _rX3 <= _edgeX2 &
			_rY3 >= _edgeY1 & _rY3 <= _edgeY2 &
			_rX4 >= _edgeX1 & _rX4 <= _edgeX2 &
			_rY4 >= _edgeY1 & _rY4 <= _edgeY2){
			return true;
		}else{
			return false;
		}
	}//rotate shape
	public void rotate(Board board){
		Point cPoint = new Point();
		cPoint.x = (int)_b1.getX();
		cPoint.y = (int)_b1.getY();
		_rX2 = cPoint.x - cPoint.y + _b2.getY();
		_rY2 = cPoint.x + cPoint.y - _b2.getX();
		_rX3 = cPoint.x - cPoint.y + _b3.getY();
		_rY3 = cPoint.x + cPoint.y - _b3.getX();
		_rX4 = cPoint.x - cPoint.y + _b4.getY();
		_rY4 = cPoint.x + cPoint.y - _b4.getX();
		//check to see if new location is legal
		if(this.inBounds() & this.canRotate()
				& board.isPaused() != true){
			_b2.setLocation(_rX2, _rY2);
			_b3.setLocation(_rX3, _rY3);
			_b4.setLocation(_rX4, _rY4);
		}
	}//get individual squares from subclassed shapes
	public TetrisSquare getSquare1(){
		return _b1;
	}
	public TetrisSquare getSquare2(){
		return _b2;
	}
	public TetrisSquare getSquare3(){
		return _b3;
	}
	public TetrisSquare getSquare4(){
		return _b4;
	}
}
