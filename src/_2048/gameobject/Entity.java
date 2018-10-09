package _2048.gameobject;

import _2048.util.Vector2d;

public class Entity extends GameObject{
	
	public double speed=5;
	public Vector2d velocity=new Vector2d();
	
	public Entity(double x,double y,double width,double height) {
		super(x,y,width,height);
	}
	
	public void update() {
		
		pos.addLocal(velocity);
		
	}
	
	public void move(double rad) {
		velocity.x+=Math.cos(rad)*speed;
		velocity.y+=Math.sin(rad)*speed;
	}
}
