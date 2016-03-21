package Tetris;
/**
 * This is the Board. It is a JPanel
 * that holds most of the game action.
 * It has a 2d array of Tetris Squares
 * that make up the grid. It also has 
 * a proxy which tells the pieces to move.
 * this also contains a key listener
 * inner class. There is a StartBoard
 * method that initializes the
 * border of the array instead of 
 * so that restart capabilities
 * could be later added.
 * The labels "paused" and "gameOver"
 * are also in this panel so that they
 * can be called directly instead of
 * being set visible through a method in
 * the main panel
 * @author <jsimwing>
 * Did you discuss your design with another student?
 * If so, list their login here:
 *
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel{
	
	private TetrisSquare[][] _squares;
	private BasicPiece _currPiece;
	private PieceProxy _proxy;
	private Timer _timer;
	private int _p;//if i is even the game will not pause
	private JLabel _gameOver, _pause;
	private boolean _gameIsOver;
	
	public Board(){
		this.setPreferredSize(Constants.MPANEL_DIM);
		this.setBackground(Color.WHITE);
		_squares = new TetrisSquare[Constants.NUM_COL][Constants.NUM_ROWS];
		_proxy = new PieceProxy();
		_currPiece = _proxy.setPiece();
		_proxy.setLocation(Constants.SQUARE_SIZE*5, Constants.SQUARE_SIZE * 2);
		
		//buttons and labels
		_pause = new JLabel("Paused");
		_gameOver = new JLabel("Game Over");
		_gameOver.setVisible(false);
		_pause.setVisible(false);
		this.add(_pause);
		this.add(_gameOver);
		
		//timer
		TimerListener tListener = new TimerListener(this, _proxy);
		_timer = new Timer(400, tListener);
		_timer.start();
		
		//keyListener
		this.addKeyListener(new MyKeyListener(this));
		this.setFocusable(true);
		this.grabFocus();
		
		//set up board
		this.startBoard();
	}
	public void startBoard(){
		for(int col = 0; col<_squares.length; col++){
			for (int row = 0; row < _squares[1].length; row++){
				TetrisSquare tSquare = new TetrisSquare();
				tSquare.setBorderColor(Color.BLACK);
				tSquare.setBorderWidth(1);
				tSquare.setColor(Color.LIGHT_GRAY);
				if(row < 2 || row >= 22){
					tSquare.setBorderColor(Color.BLACK);
					tSquare.setBorderWidth(1);
					_squares[col][row] = tSquare; 
					tSquare.setLocation(col * Constants.SQUARE_SIZE,
							row * Constants.SQUARE_SIZE);
				}
				else if(col < 2 || col >= 12){
					tSquare.setBorderColor(Color.BLACK);
					tSquare.setBorderWidth(1);
					_squares[col][row] = tSquare;
					tSquare.setLocation(col * Constants.SQUARE_SIZE,
						row * Constants.SQUARE_SIZE);
				}
			}
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
			for (int col = 0; col < _squares.length; col++){
				for (int row = 0; row < _squares[1].length; row++) {
					if(_squares[col][row] != null){
						_squares[col][row].paint(g);	
				}
			}
		}
			if (_currPiece != null){
				_currPiece.paint(g);
			}
	}
	//method to clear row
	public void clearRow(int rowNum, int linesCleared){
		boolean rowCleared = false;
		_timer.stop();
		for (int columns = 2; columns <12; columns++){
			_squares[columns][rowNum] = null;
			rowCleared = true;
		}
		if (rowCleared){
			for (int row = rowNum; row > 2; row--){
				for(int col = 2; col < 12; col++){
					if(this.isFull(col, row)){ 
						Color color = _squares[col][row].getColor();
						int newRow = row+1;
						_squares[col][newRow] = _squares[col][row];
						_squares[col][newRow].setLocation(col * Constants.SQUARE_SIZE, newRow * Constants.SQUARE_SIZE);	
						_squares[col][newRow].setColor(color);
						_squares[col][newRow].setBorderColor(Color.BLACK);
					}
					int oldCol = col;
					int oldRow = row;
					_squares[oldCol][oldRow]= null;
					this.repaint();
				}
			}
		}
		_timer.start();
	}
	//checks if space is empty in array using x, y location
	public boolean isNull(int x, int y){
		if (_squares[(int)x/20][(int)y/20] == null){
			return true;
		}else{
			return false;
		}
	}
	//checks if space is full in array uring array location
	public boolean isFull(int x, int y){
		if (_squares[x][y] == null){
			return false;
		}else{
			return true;
		}
	}
	public void react(){
		int empty = 0;
		int full = 0;
		int linesCleared = 0;
		//line clearing
		for (int rowNum = 2; rowNum < 22;){
			for (int column = 2; column < 12; column++){
				if(_squares[column][rowNum] != null){
					full++;
				}
				if(full == 10){
					linesCleared++;
					this.clearRow(rowNum, linesCleared);
					this.repaint();
				}
				if(_squares[column][rowNum] == null){
						empty++;
					}
				if (empty + full == 10 || empty == 10 || full ==10){
						rowNum++;
						column = 2;
						full = 0;
						empty = 0;
				}
			}
		}
		//check to see if game is over
		for (int c = 2; c <= 10; c++){
			if(this.isFull(c, 2)){
				_gameIsOver = true;
				_timer.stop();
				_gameOver.setVisible(true);
			}
		}
		//check to see if piece can move down
		if(_currPiece.inBounds()& _currPiece.canMoveDown(this) & 
				_gameIsOver != true){
		_proxy.moveDown(this);
		this.repaint();
		}else{
			_timer.stop();
			//add piece to array
			int x1 = ((int)_currPiece.getX()/20);
			int y1 = ((int)_currPiece.getY()/20);
			int x2 = ((int)_currPiece.getX2()/20);
			int y2 = ((int)_currPiece.getY2()/20);
			int x3 = ((int)_currPiece.getX3()/20);
			int y3 = ((int)_currPiece.getY3()/20);
			int x4 = ((int)_currPiece.getX4()/20);
			int y4 = ((int)_currPiece.getY4()/20);
			for (int col = 0; col < _squares.length; col++){
				for (int row = 0; row < _squares[0].length; row++) {
					_squares[x1][y1]= _currPiece.getSquare1();
					_squares[x2][y2]= _currPiece.getSquare2();
					_squares[x3][y3]= _currPiece.getSquare3();
					_squares[x4][y4]= _currPiece.getSquare4();	
				}
			}//create new current piece
			if(_gameIsOver != true){
				_currPiece = _proxy.setPiece();
				_currPiece.setLocation(Constants.SQUARE_SIZE*5, Constants.SQUARE_SIZE * 2);
				_timer.start();
			}
		}
		this.repaint();	
	}
	//game can be paused if p is even
	//p is incremented every time the paused
	//button is clicked
	public boolean pauseGame(int p){
		_p = p;
		boolean isPaused = true;
		boolean notPaused = false;
		if (p % 2 !=0){
			_timer.stop();
			_pause.setVisible(true);
			return isPaused;
		}else{
			_timer.start();
			_pause.setVisible(false);
			return notPaused;
		}
	}
	//boolean to tell other methods if game is paused
	public boolean isPaused(){
		if (this.pauseGame(_p)){
			return true;
		}else{
			return false;
		}	
	}
	//MyKeyListener inner class
	private class MyKeyListener implements KeyListener{
		private Board _board;
		private int _i;
		private MyKeyListener(Board board){
			_board = board;
			_i = 0;
		}
			public void keyPressed(KeyEvent e){
				int keyPressed = e.getKeyCode();
				if(keyPressed == KeyEvent.VK_RIGHT){
					_proxy.moveRight(_board);
					_board.repaint();
				}
				if (keyPressed == KeyEvent.VK_LEFT){
					_proxy.moveLeft(_board);
					_board.repaint();
				}
				if (keyPressed == KeyEvent.VK_UP){
					_proxy.rotate(_board);
					_board.repaint();
				}
				if (keyPressed == KeyEvent.VK_DOWN){
					_proxy.moveDown(_board);
					_board.repaint();
				}
				if (keyPressed == KeyEvent.VK_P){
					_i++;
					_board.pauseGame(_i);
				}
				if (keyPressed == KeyEvent.VK_SPACE){
					_proxy.drop(_board);
					_board.repaint();
				}
			}
			public void keyReleased(KeyEvent e){}
			public void keyTyped(KeyEvent e){}
	}
	//end MyKeyListner inner class
}
