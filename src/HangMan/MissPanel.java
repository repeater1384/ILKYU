package HangMan;

import javax.swing.JLabel;
import javax.swing.JPanel;

class MissPanel extends JPanel {

	private JLabel misses;
	private boolean[] flag;
	static final int alphaNum = 26;

	public MissPanel() {
		add(new JLabel("Misses : "));
		add(misses = new JLabel());
		flag = new boolean[alphaNum];
	}

	void add(char c) {
		flag[c - 'a'] = true;
		misses.setText(scan());
	}

	void reset() {
		misses.setText("");
		for (int i = 0; i < alphaNum; i++)
			flag[i] = false;
	}

	private String scan() {

		int i, j = 0, len = 0;

		for (i = 0; i < alphaNum; i++) {
			if (flag[i] == true)
				len++;
		}

		char[] buf = new char[len * 2];

		for (i = 0; i < alphaNum; i++) {
			if (flag[i] == true) {
				buf[j++] = (char) (i + 'a');
				buf[j++] = ' ';
			}
		}

		return new String(buf);
	}
}
