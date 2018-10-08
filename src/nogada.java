import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class nogada extends JFrame {
	private int score = 0;
	private int press = 100;

	public nogada() {
		setSize(512, 150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		makeUI();
	}

	private void makeUI() {
		JProgressBar bar = new JProgressBar();
		bar.setStringPainted(true);
		bar.setForeground(Color.blue);
		bar.setMaximum(press);
		this.add(bar);

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				bar.setValue(bar.getValue() + 1);
				score += 1;
				setTitle("노가다 하세요 " + " score : " + score + "/" + bar.getMaximum());

				if (bar.getValue() % (press / 10) == 0) {
					bar.setForeground(new Color(getRandomInt(), getRandomInt(), getRandomInt()));//색변환
					if (bar.getValue() == press) {
						press += 100;
						bar.setMaximum(press);
						bar.setValue(0);
						score= 0;
						
					}
				}
			}
		});
	}

	private int getRandomInt() {
		int randomInt = (int) (Math.random() * 256);
		return randomInt;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new nogada();
	}

}
