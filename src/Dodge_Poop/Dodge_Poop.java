package Dodge_Poop;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Dodge_Poop extends JFrame implements Runnable {

	/*
	 * 추가해야될것 : 버틴 시간, 충돌했을때 게임오버 메세지/재시작버튼, , (아이템), 똥바닥에 떨어졌을때 이미지추가, 배경음악, etc...
	 */
	private static final long serialVersionUID = 1L; // ? 모름;

	private Image human; // 여기에 right/left 설정할것임.
	private Image right = new ImageIcon("C:\\temp\\images\\RIGHT.png").getImage(); // 오른쪾
	private Image left = new ImageIcon("C:\\temp\\images\\LEFT.png").getImage(); // 왼쪽

	private boolean buf[] = new boolean[300];
	private boolean prebuf[] = new boolean[300];

	JPanel jPanel = new MyPanel(); // 키입력 받을 판넬
	Random random = new Random(); // 똥 X좌표 결정할 랜덤

	ArrayList<Poop> poops; // 똥들 담을 배열
	Poop p; //

	private int tick = 0; // 똥생성해줄 틱
	private int playerY = Main.SCREEN_HEIGHT - 55; // 이상함..ㅠ
	private double playerX = Main.SCREEN_WIDTH / 2; // 초깃값은 정중앙
	private double speed = 0.999; // 요놈이 가속도
	private double friction = 0.95; // 마찰력
	private double vecX = 0; // 벡터값
	private int poopTimer = 50; // poopTimer/60 초에 하나씩 생성
	private int gameTick = 0; // 똥 개수 늘려줄 틱

	public Dodge_Poop() { // 생성자

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
//					System.out.println("때짐");
				}

				public void keyPressed(KeyEvent e) {
					buf[e.getKeyCode()] = true;
//					System.out.println("눌림");
				}
			});

			this.requestFocus(); // 포커싱
			setFocusable(true);
		}

		public void paint(Graphics g) {

			g.clearRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 화면 초기화

			g.drawImage(human, (int) playerX, Main.SCREEN_HEIGHT - 55, this); // 사람 그리기

			drawPoop(g);
		}

	}

	private void setPoopTimer() { // 1초에 6번 똥생성 ////시간 지나면 자주나오도록<<추가하기

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

	private void windowSetting() { // 화면설정

		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		jPanel.setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		this.setBackground(Color.white);

		add(jPanel, BorderLayout.CENTER);

		setVisible(true);

		setLocationRelativeTo(null);
		setTitle("Dodge_Poop");

	}

	private void update() { // run() 안에서 불러줌

		if (checkFailed())
			System.out.println("똥맞음"); // 똥맞으면 재시작해줘야되는데 모름.
		if (buf[KeyEvent.VK_A]) {
			if (isKeyPressed(KeyEvent.VK_A))
				human = left; // 이미지 바꿈
			vecX -= speed;

		} else if (buf[KeyEvent.VK_D]) {
			if (isKeyPressed(KeyEvent.VK_D))
				human = right; // 이미지 바꿈
			vecX += speed;
		}

		playerX += vecX;
		vecX *= friction;

		if (playerX <= 0) // 화면 밖으로 나가는것 방지.
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

	private void drawPoop(Graphics g) { // 똥그리기
		for (int i = 0; i < poops.size(); i++) { // 배열 돌면서
			poops.get(i).setPoopY(poops.get(i).getPoopY() + 5); // 모든 똥 y좌표 5씩 내리기
			poops.get(i).draw(g); // 모든 똥 그리기
			if (poops.get(i).getPoopY() >= Main.SCREEN_HEIGHT) /// 화면 밖으로 나간 똥 지우기,지우는게 그리는 것보다 위에있으면 오류발생
				poops.remove(i);
		}

	}

	private boolean checkFailed() { // 충돌 감지
		for (int i = 0; i < poops.size(); i++) {

			if ((int) playerX <= poops.get(i).getPoopX() && poops.get(i).getPoopX() <= (int) playerX + 32 // 똥맞는 범위
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
