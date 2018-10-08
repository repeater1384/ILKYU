import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class addspeed extends JFrame {
	public addspeed() {
		setTitle("가속도/똥피하기 준비");
		setBounds(100, 100, 224, 378);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		makeUI();
	}

	private ImageIcon human = new ImageIcon("C:\\temp\\images\\humanRight");
	private JButton main = new JButton(human);
	
	private void makeUI() {
		add(main);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new addspeed();

	}

}
