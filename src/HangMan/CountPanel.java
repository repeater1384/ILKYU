package HangMan;

import javax.swing.JLabel;
import javax.swing.JPanel;

class CountPanel extends JPanel {
	private JLabel lb_cnt;
	private int count;

	public CountPanel() {
		add(new JLabel("Count Down: "));
		add(lb_cnt = new JLabel());
	}

	void setCount(int count) {
		lb_cnt.setText(String.valueOf(this.count = count));
	}
	int decrement() {
		lb_cnt.setText(String.valueOf(--count));
		return count;
	}
}