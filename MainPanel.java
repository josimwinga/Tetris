package Tetris;
/**
 * This is the MainPanel. It holds the Board 
 * and buttonPanel. It also has an inner class
 * click listener for the "quit" button
 *
 * @author <jsimwing>
 * Did you discuss your design with another student?
 * If so, list their login here:
 *
 */

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel{

	private Board _board;
	private JLabel _gameOver;
	
	public MainPanel(){
		_board = new Board();
		JPanel bottomPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		this.setPreferredSize(Constants.MPANEL_DIM);
		bottomPanel.setPreferredSize(Constants.TPANEL_DIM);
		this.setBackground(Color.WHITE);
		
		//button
		JButton quit = new JButton("Quit");
		QuitListener qListener = new QuitListener(this);
		quit.addMouseListener(qListener);

		//layout
		buttonPanel.add(quit);
		this.setLayout(new BorderLayout());
		bottomPanel.add(buttonPanel, BorderLayout.NORTH);
		this.add(_board, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		_board.paint(g);	
	}
	
	//Quit Listener inner class
	private class QuitListener implements MouseListener{
		private QuitListener(JPanel panel){
		}
		@Override
		public void mouseClicked(MouseEvent e){}

		@Override
		public void mousePressed(MouseEvent e){
			System.exit(0);
		}
		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e){}

		@Override
		public void mouseExited(MouseEvent e){}
	}//end QuitListener inner class
}

