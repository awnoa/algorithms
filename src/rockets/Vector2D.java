package rockets;

import java.util.Random;

public class Vector2D {

	double x,y;
	public static final Vector2D zero = new Vector2D(0, 0);

	public Vector2D(double x, double y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}
	
	public static Vector2D fromAngle(int degrees){
		double radians = (Math.PI / 180) * degrees;
		double x = Math.cos(radians);
		double y = Math.sin(radians);
		
		return new Vector2D(x, y);
	}

	public Vector2D mult(double delta) {
		double x = this.x*delta;
		double y = this.y*delta;
		return new Vector2D(x, y);
	}

	public Vector2D add(Vector2D mult) {
		double x = this.x + mult.x;
		double y = this.y + mult.y;
		
		return new Vector2D(x, y);
		
	}

	public Vector2D mult(Vector2D direction) {
		// TODO Auto-generated method stub
		double x = this.x * direction.x;
		double y = this.y * direction.y;
		return new Vector2D(x, y);
		
	}
	
	public static Vector2D randomDirection(){
		Random r = new Random(System.nanoTime());
		int rAngle = r.nextInt(361);
		return Vector2D.fromAngle(rAngle);
	}
	
	public double dist(Vector2D d){
		return Math.sqrt(Math.pow((x-d.x),2) + Math.pow((y-d.y),2));
	}
}
