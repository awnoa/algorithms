package rockets;

public class Gene {

	Vector2D direction;

	public Gene() {
		direction = Vector2D.randomDirection();
	}
	
	public Vector2D getDirection(){
		return direction;
	}
}
