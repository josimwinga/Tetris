package Tetris;

import javax.swing.JFrame;

/**
 * It's time for Tetris! This is the  main class to get things started.
 * The main method of this application calls the App constructor. You 
 * will need to fill in the constructor to instantiate your Tetris game.
 *
 * Class comments here...
 *
 * @author <jsimiwing>
 * Did you discuss your design with another student?
 * If so, list their login here:
 *
 */

public class App {

	public App() {
		// Constructor code goes here.
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainPanel mainPanel = new MainPanel();
		frame.add(mainPanel);
		frame.pack();
		frame.setVisible(true);
	}

	/*Here's the mainline!*/
	public static void main(String[] argv) {
		new App();
	}

}
