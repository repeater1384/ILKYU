package _2048.util;

public class Vector2d {
	public double x;
	public double y;
	
	
	public Vector2d() {
		this(0,0);
	}
	
	public Vector2d(double x, double y) {
		super();
		set(x, y);
	}
	
	public void set(double x,double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2d addLocal(Vector2d v) {
		this.x+=v.x;
		this.y+=v.y;
		
		return this;
	}
	
	
}
