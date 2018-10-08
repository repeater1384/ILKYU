package HangMan;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class test {
	static class MyFrame extends JFrame {
		MyFrame() {
			setTitle("¤¾¤·");
			setSize(300, 200);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			add(new Mypanel(), BorderLayout.CENTER);
			setVisible(true);
		}
	}

	static class Mypanel extends JPanel implements ActionListener {
		private WorldPanel wp;
		private JButton button;
		private String s = "programming";
		private int cnt;

		Mypanel() {
			setLayout(new GridLayout(2, 1));
			add(wp = new WorldPanel());
			wp.setWord(s);
			JPanel panel = new JPanel();
			panel.add(button = new JButton("OK"));
			add(panel);
			button.addActionListener(this);
			if (wp.getWordLength() != s.length())
				button.setEnabled(false);
			System.out.println(wp.getWordLength());
		}

		public void actionPerformed(ActionEvent e) {
			if (wp.isAllMatched()) {
				wp.setWord(s);
				cnt = 0;
				
			}else if(!wp.math(s.charAt(cnt++)))
				button.setEnabled(false);
			
		
			

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyFrame();
	}

}
