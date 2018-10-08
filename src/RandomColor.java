
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class RandomColor extends JFrame{
	public RandomColor() {
		setTitle("Random Color");
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		makeUI();
	}
	private void makeUI() {
		JButton button = new JButton();
		add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				button.setBackground(new Color(getRandomInt(), getRandomInt(), getRandomInt()));
				
			}
		});
	}
	private int getRandomInt() {
		int randomInt = (int)(Math.random()*256);
		return randomInt;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RandomColor();

	}

}
