package _2048.gameobject;

public class Player extends Entity{
	
	int jumpCounter;
	
	public Player(double x,double y,double width,double height) {
		super(x,y,width,height);
	}
	
	public void jump(double amount) {
		velocity.y=-amount;
	}
	
}
