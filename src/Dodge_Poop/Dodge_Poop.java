package Dodge_Poop;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Dodge_Poop extends JFrame implements Runnable {

	/*
	 * �߰��ؾߵɰ� : ��ƾ �ð�, �浹������ ���ӿ��� �޼���/����۹�ư, , (������), �˹ٴڿ� ���������� �̹����߰�, �������, etc...
	 */
	private static final long serialVersionUID = 1L; // ? ��;

	private Image human; // ���⿡ right/left �����Ұ���.
	private Image right = new ImageIcon("C:\\temp\\images\\RIGHT.png").getImage(); // �����U
	private Image left = new ImageIcon("C:\\temp\\images\\LEFT.png").getImage(); // ����

	private boolean buf[] = new boolean[300];
	private boolean prebuf[] = new boolean[300];

	JPanel jPanel = new MyPanel(); // Ű�Է� ���� �ǳ�
	Random random = new Random(); // �� X��ǥ ������ ����

	ArrayList<Poop> poops; // �˵� ���� �迭
	Poop p; //

	private int tick = 0; // �˻������� ƽ
	private int playerY = Main.SCREEN_HEIGHT - 55; // �̻���..��
	private double playerX = Main.SCREEN_WIDTH / 2; // �ʱ갪�� ���߾�
	private double speed = 0.999; // ����� ���ӵ�
	private double friction = 0.95; // ������
	private double vecX = 0; // ���Ͱ�
	private int poopTimer = 50; // poopTimer/60 �ʿ� �ϳ��� ����
	private int gameTick = 0; // �� ���� �÷��� ƽ

	public Dodge_Poop() { // ������

		windowSetting();
		poops = new ArrayList<>();
	}

	class MyPanel extends JPanel {

		public MyPanel() {
			addKeyListener(new KeyListener() {

				public void keyTyped(KeyEvent e) {
				}

				public void keyReleased(KeyEvent e) {
					buf[e.getKeyCode()] = false;
//					System.out.println("����");
				}

				public void keyPressed(KeyEvent e) {
					buf[e.getKeyCode()] = true;
//					System.out.println("����");
				}
			});

			this.requestFocus(); // ��Ŀ��
			setFocusable(true);
		}

		public void paint(Graphics g) {

			g.clearRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // ȭ�� �ʱ�ȭ

			g.drawImage(human, (int) playerX, Main.SCREEN_HEIGHT - 55, this); // ��� �׸���

			drawPoop(g);
		}

	}

	private void setPoopTimer() { // 1�ʿ� 6�� �˻��� ////�ð� ������ ���ֳ�������<<�߰��ϱ�

		tick++;

		if (tick % poopTimer == 0) {
			p = new Poop(random.nextInt(Main.SCREEN_WIDTH - 32), 0);
			poops.add(p);
			tick = 0;
			gameTick++;
		}
		if (gameTick % 20 == 0 && poopTimer > 5) 

			poopTimer--;
		

	}

	private void windowSetting() { // ȭ�鼳��

		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		jPanel.setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		this.setBackground(Color.white);

		add(jPanel, BorderLayout.CENTER);

		setVisible(true);

		setLocationRelativeTo(null);
		setTitle("Dodge_Poop");

	}

	private void update() { // run() �ȿ��� �ҷ���

		if (checkFailed())
			System.out.println("�˸���"); // �˸����� ���������ߵǴµ� ��.
		if (buf[KeyEvent.VK_A]) {
			if (isKeyPressed(KeyEvent.VK_A))
				human = left; // �̹��� �ٲ�
			vecX -= speed;

		} else if (buf[KeyEvent.VK_D]) {
			if (isKeyPressed(KeyEvent.VK_D))
				human = right; // �̹��� �ٲ�
			vecX += speed;
		}

		playerX += vecX;
		vecX *= friction;

		if (playerX <= 0) // ȭ�� ������ �����°� ����.
			playerX = 0;
		else if (playerX >= Main.SCREEN_WIDTH - 32)
			playerX = Main.SCREEN_WIDTH - 32;

		inputUpdate();

	}

	private void inputUpdate() {
		for (int i = 0; i < prebuf.length; i++)
			prebuf[i] = buf[i];
	}

	private boolean isKeyPressed(int key) {
		return !prebuf[key] && buf[key];
	}

	private void drawPoop(Graphics g) { // �˱׸���
		for (int i = 0; i < poops.size(); i++) { // �迭 ���鼭
			poops.get(i).setPoopY(poops.get(i).getPoopY() + 5); // ��� �� y��ǥ 5�� ������
			poops.get(i).draw(g); // ��� �� �׸���
			if (poops.get(i).getPoopY() >= Main.SCREEN_HEIGHT) /// ȭ�� ������ ���� �� �����,����°� �׸��� �ͺ��� ���������� �����߻�
				poops.remove(i);
		}

	}

	private boolean checkFailed() { // �浹 ����
		for (int i = 0; i < poops.size(); i++) {

			if ((int) playerX <= poops.get(i).getPoopX() && poops.get(i).getPoopX() <= (int) playerX + 32 // �˸´� ����
					&& playerY == poops.get(i).getPoopY())
				return true;

		}
		return false;
	}

	@Override
	public void run() {
		while (true) {
			setPoopTimer();
			update();
			repaint();
			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
