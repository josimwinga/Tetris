package Tetris;
/**
 * This is the Timelistener
 * it tells the board to react ever
 * time the timer ticks
 *
 *@author <jsimiwing>
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener{
	private Board _board;
	public TimerListener(Board board, PieceProxy proxy){
		_board = board;
		
	}
	@Override
	public void actionPerformed(ActionEvent e){
		_board.react();
	}

}
