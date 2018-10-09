package _2048.util;

import java.awt.Image;

import javax.swing.ImageIcon;

import _2048._main;

public class ImageLoader {

	private static ImageLoader instance;

	public static ImageLoader getInstance() {
		if (instance == null)
			instance = new ImageLoader();
		return instance;
	}

	public Image bird = new ImageIcon(_main.class.getResource("../../image/Yongho.png")).getImage();

	public ImageLoader() {
	}
}
