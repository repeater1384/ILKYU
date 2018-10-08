

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Puzzle extends JFrame implements ActionListener {
	public static void main(String[] args) {
		new Puzzle();

	}

	private JButton btn[];

	public Puzzle() {
		setTitle("Math Puzzle");
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		makeUI();
		setVisible(true);

	}
	private int getRandomInt() {
		int randomInt = (int)(Math.random()*100+156);
		return randomInt;
	}

	private int[] getRandomArr() {

		int arr[] = new int[16];
		int ran = 0;
		boolean check;
		Random random = new Random();

		for (int i = 0; i < arr.length; i++) {
			ran = random.nextInt(16) + 1;
			check = true;
			for (int j = 0; j < i; j++) {
				if (arr[j] == ran) {
					i--;
					check = false;
				}
			}
			if (check)
				arr[i] = ran;

		}
		return arr;
	}

	private void makeUI() {
		setLayout(new GridLayout(4, 4));
		btn = new JButton[16];
		int arr[] = getRandomArr();

		for (int i = 0; i < 16; i++) {
			add(btn[i] = new JButton(String.valueOf(arr[i])));
			btn[i].setEnabled(true);
			btn[i].addActionListener(this);
			btn[i].setBackground(new Color(getRandomInt(),getRandomInt(),getRandomInt()));
			btn[i].setFont(new Font("¸¼Àº°íµñ",Font.BOLD,30));
		}
		for (int i = 0; i < 16; i++) {
			if (Integer.parseInt(btn[i].getText()) == 16) {
				btn[i].setEnabled(false);
				btn[i].setText("");
				btn[i].setBackground(Color.black);

			}
		}

	}

	private int[] nb = new int[4];

	private void findNb(int id) {
		// nb[-1]<<< ÀÎÁ¢ÇÑ ¼ýÀÚX

		// up
		nb[0] = id - 4;

		// down
		nb[1] = id + 4;
		if (nb[1] >= 16)
			nb[1] = -1;

		// left
		nb[2] = id - 1;
		if (nb[2] < 0 || nb[2] % 4 == 3)
			nb[2] = -1;

		// right
		nb[3] = id + 1;
		if (nb[3] % 4 == 0)
			nb[3] = -1;
	}

	private boolean checkVictory() {
		for (int i = 0; i < 16; i++) {
			if (btn[i].getText() == "") {
				if (i != 15)
					return false;
			} else {
				if (Integer.parseInt(btn[i].getText()) != i + 1) {
					return false;
				}
			}
		}
		return true;
	}

	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		int id;
		for (id = 0; id < 16; id++)
			if (b == btn[id])
				break;

		findNb(id);

		for (int i = 0; i < 4; i++) {
			if (nb[i] >= 0 && !btn[nb[i]].isEnabled()) {
				JButton act, inact;
				act = btn[id];
				inact = btn[nb[i]];
				inact.setText(act.getText());
				act.setText("");
				inact.setEnabled(true);
				act.setEnabled(false);
				inact.setBackground(act.getBackground());
				act.setBackground(Color.BLACK);
				break;
			}

		}
		if(checkVictory()) {
			JOptionPane.showMessageDialog(null,"½Â¸®");
		}

	}

}
