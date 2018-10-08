package dynamic_beat;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {
	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fileInputStream;
	private BufferedInputStream bufferedInputStream;

	public Music(String name, boolean isLoop) {
		try {

			this.isLoop = isLoop;
			file = new File(("C:\\temp\\music\\" + name));
			fileInputStream = new FileInputStream(file);
			bufferedInputStream = new BufferedInputStream(fileInputStream);
			player = new Player(bufferedInputStream);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getTime() {
		if (player == null)
			return 0;
		return player.getPosition();
	}

	public void close() {
		isLoop = false;
		player.close();
		this.interrupt();
	}

	public void run() {
		try {
			do {
				player.play();
				fileInputStream = new FileInputStream(file);
				bufferedInputStream = new BufferedInputStream(fileInputStream);
				player = new Player(bufferedInputStream);
			} while (isLoop);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
