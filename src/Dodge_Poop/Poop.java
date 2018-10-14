package Dodge_Poop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;

import javax.swing.ImageIcon;

public class Poop {

	private int poopX;		
	private int poopY;
	public Image poop = new ImageIcon("C:\\temp\\images\\DDong.png").getImage();

	public Poop(int poopX, int poopY) {
		this.poopX = poopX;
		this.poopY = poopY;
	}

	public int getPoopX() {
		return poopX;
	}

	public void setPoopX(int poopX) {
		this.poopX = poopX;
	}

	public int getPoopY() {
		return poopY;
	}

	public void setPoopY(int poopY) {
		this.poopY = poopY;
	}

	public void draw(Graphics g) {
		g.drawImage(poop, poopX, poopY, null);		//null << this로 하면 오류발생.
	}

}
