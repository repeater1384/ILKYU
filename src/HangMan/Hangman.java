package HangMan;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Hangman extends JFrame implements ActionListener {

	static final int count = 6;
	private WorldPanel wp;
	private CountPanel cp;
	private JTextField tf;
	private MissPanel mp;
	private DrawPanel dp;
	private Word word;

	Hangman() {
		setSize(400, 170);
		setTitle("Hangman");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		makeUI();
		setVisible(true);
	}

	private void makeUI() {
		word = new Word("C:\\temp\\fruit.txt");
		wp = new WorldPanel();
		wp.setWord(word.getWord());
		cp = new CountPanel();
		cp.setCount(count);

		JPanel gp = new JPanel();
		gp.add(new JLabel("Guess: "));
		gp.add(tf = new JTextField(1));
		tf.addActionListener(this);

		JPanel right = new JPanel();
		right.setLayout(new GridLayout(4, 1));
		right.add(wp);
		right.add(gp);
		right.add(mp = new MissPanel());
		right.add(cp);

		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		add(dp = new DrawPanel());
		add(right);

	}

	private void reset() {
		wp.setWord(word.getWord());
		cp.setCount(count);
		dp.reset();
		mp.reset();
	}

	public void actionPerformed(ActionEvent e) {
		char c = tf.getText().charAt(0);
		tf.setText("");
		if (!wp.math(c)) {
			mp.add(c);
			dp.drawNext();
			if (cp.decrement() == 0) {
				JOptionPane.showMessageDialog(this, "Mission Failed\n" + wp.getWord());
				reset();
			}
		} else if (wp.isAllMatched()) {
			JOptionPane.showMessageDialog(this, "Mission Success\n" + wp.getWord());
			reset();
		}
	}

	public static void main(String[] args) {
		new Hangman();
	}
}
