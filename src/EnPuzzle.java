import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EnPuzzle extends JFrame {

	public EnPuzzle() {
		setTitle("은규님을 위한 세레나데");
		setSize(800, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		makeUI();

	}

	private JButton button[];
	static final int setSize = 16;

	private void makeUI() {

		setLayout(new BorderLayout(10, 30));
		JPanel panel = new JPanel();
		JPanel up = new JPanel();
		add(panel, BorderLayout.CENTER);
		add(up, BorderLayout.NORTH);

		JButton bt1 = new JButton("누르세요");
		JButton bt2 = new JButton("초기화");
		JButton bt3 = new JButton("섞기");

		up.add(bt1);
		up.add(bt2);
		up.add(bt3);

		panel.setLayout(new GridLayout(4, 4));
		button = new JButton[setSize];

		for (int i = 0; i < setSize; i++) {
			panel.add(button[i] = new JButton(String.valueOf(i + 1)));
		}

		button[setSize-1].setText("");
		button[setSize-1].setEnabled(false);

		bt1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					changeButton_up();
					System.out.println("up");
					break;
				case KeyEvent.VK_DOWN:
					changeButton_down();
					System.out.println("down");
					break;
				case KeyEvent.VK_LEFT:
					changeButton_left();
					System.out.println("left");
					break;
				case KeyEvent.VK_RIGHT:
					changeButton_right();
					System.out.println("right");
					break;
				default:
					System.out.println("ㅗ");

				}
			}
		});
		
		bt2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				for (int i = 0; i < setSize; i++) {
					button[i].setText(String.valueOf(i+1));
					button[i].setEnabled(true);
				}

				button[setSize-1].setText("");
				button[setSize-1].setEnabled(false);
			}
		});
		
		bt3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				shuffle();
				
			}
		});
	}

	private void shuffle() {
		for (int r = (int) (Math.random() * 1000); r >= 0; r--) {
			if(r%4==0) {
				changeButton_up();
				changeButton_up();
				changeButton_left();
			}else if(r%4 == 1) {
				changeButton_right();
				changeButton_right();
				changeButton_up();
			}else if(r%4 == 2) {
				changeButton_left();
				changeButton_down();
				changeButton_right();
			}else if(r%4 == 3) {
				changeButton_left();
				changeButton_down();
				changeButton_down();
			}
		}
	}

	private int findFalseIndex() {
		int i;

		for (i = 0; i < setSize; i++) {
			if (button[i].getText() == "")
				break;
		}
		return i;

	}

	private void changeButton_up() {
		if (findFalseIndex() > 3) {
			JButton nowFalse, nextFalse;

			nowFalse = button[findFalseIndex()];
			nextFalse = button[findFalseIndex() - 4];

			nowFalse.setText(nextFalse.getText());
			nextFalse.setText("");
			nowFalse.setEnabled(true);
			nextFalse.setEnabled(false);

		}
	}

	private void changeButton_down() {
		if (findFalseIndex() < 12) {
			JButton nowFalse, nextFalse;

			nowFalse = button[findFalseIndex()];
			nextFalse = button[findFalseIndex() + 4];

			nowFalse.setText(nextFalse.getText());
			nextFalse.setText("");
			nowFalse.setEnabled(true);
			nextFalse.setEnabled(false);

		}
	}

	private void changeButton_left() {
		if (findFalseIndex() % 4 != 0) {
			JButton nowFalse, nextFalse;

			nowFalse = button[findFalseIndex()];
			nextFalse = button[findFalseIndex() - 1];

			nowFalse.setText(nextFalse.getText());
			nextFalse.setText("");
			nowFalse.setEnabled(true);
			nextFalse.setEnabled(false);

		}
	}

	private void changeButton_right() {
		if (findFalseIndex() % 4 != 3) {
			JButton nowFalse, nextFalse;

			nowFalse = button[findFalseIndex()];
			nextFalse = button[findFalseIndex() + 1];

			nowFalse.setText(nextFalse.getText());
			nextFalse.setText("");
			nowFalse.setEnabled(true);
			nextFalse.setEnabled(false);

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new EnPuzzle();
	}

}
