package HangMan;

import java.util.Random;



import java.io.*;

public class Word {
	private Random random;
	private String[] words;
	private final int MAX = 500;
	private int Length;

	String getWord() {
		return words[random.nextInt(Length)];
	}

	public Word(String fname) {
		random = new Random();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fname));
			words = new String[MAX];
			int i;
			for (i = 0; i < MAX; i++) {
				if ((words[i] = br.readLine()) == null)
					break;
			}
			Length = i;
			br.close();

		} catch (Exception e) {
			System.out.println("can't read");
			System.exit(0);
		}
	}
}
