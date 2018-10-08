package HangMan;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WorldPanel extends JPanel {
	private JTextField tField;
	private String word;
	private int len;
	private char[] buf;

	public WorldPanel() {
		add(new JLabel("Word : "));
		add(tField = new JTextField(12));
		tField.setEditable(false);
		tField.setHorizontalAlignment(JTextField.CENTER);
	}

	void setWord(String word) {
		this.word = word;
		this.len = word.length();
		buf = new char[len];
		for (int i = 0; i < len; i++)
			buf[i] = '_';
		tField.setText(new String(buf));
	}

	String getWord() {
		return word;
	}

	int getWordLength() {
		return len;
	}

	boolean math(char c) {
	      boolean found = false;
	      for (int i = 0; i < len; i++) 
	         if (word.charAt(i) == c) {
	            buf[i] = c;
	            tField.setText(new String(buf));
	            found = true;
	         }
	         return found;
		
		

	}

	boolean isAllMatched() {
		for(int i = 0 ; i <len;i++)
			if(buf[i]=='_')
				return false;
		return true ;
	}

}
