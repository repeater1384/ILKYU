import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class move3 extends JFrame {
	public move3() {
		setTitle("move3");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new GridLayout(2,1));
		makeUI();
	}

	private void makeUI() {
		JTextField label = new JTextField();
		label.setBounds(50, 50, 50, 50);
		add(label);

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					label.setLocation(label.getX(), label.getY() - 10);
					System.out.println("up");
					break;
				case KeyEvent.VK_DOWN:
					label.setLocation(label.getX(), label.getY() + 10);
					break;
				case KeyEvent.VK_LEFT:
					label.setLocation(label.getX() - 10, label.getY());
					break;
				case KeyEvent.VK_RIGHT:
					label.setLocation(label.getX() + 10, label.getY());
					break;
				default:
					System.out.println("คว");

				}
			}
		});

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new move3();
	}

}
