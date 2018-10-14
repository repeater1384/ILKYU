package Dodge_Poop;

import javax.swing.JFrame;

public class Main {
	
	public static final int SCREEN_WIDTH = 400;
	public static final int SCREEN_HEIGHT = 600;
	
		
	public static void main(String[] args) {

		Dodge_Poop dodge_Poop = new Dodge_Poop();
		dodge_Poop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Thread thread = new Thread(dodge_Poop);
		thread.start();

	}

}
