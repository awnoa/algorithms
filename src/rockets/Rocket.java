package rockets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.util.Comparator;

import javax.swing.text.Position;

import app.DrawingCanvas;

public class Rocket implements Comparable<Rocket>{

	Vector2D speed;
	Vector2D acceleration; 
	DrawingCanvas d;
	double width;
	double height;
	Vector2D direction;
	Vector2D position;
	BasicStroke stroke ;
	boolean freeze;
	int age;
	int lifeSpan = 500;
	DNA dna;
	double fitness;
	double distanceMax;
	double distanceMin;
	int ageopti ;
	Population population;

	public Rocket(Vector2D position, Vector2D size, Vector2D direction, DrawingCanvas d, Population p) {
		this.position = position;
		this.d = d;
		speed = new Vector2D(1,1);
		acceleration = new Vector2D(0,0);
		this.width = size.x;
		this.height = size.y;
		this.direction = direction;
		stroke = new BasicStroke(20);
		freeze = false;
		dna =  new DNA(lifeSpan);
		age = 0;
		distanceMax = new Vector2D(0, 0).dist(new Vector2D(640, 480));
		distanceMin = distanceMax;
		population = p;
	}

	public Rocket(Rocket a, Rocket b){
		d = a.d;
		position = new Vector2D(320, 480);
		speed = new Vector2D(1,1);
		acceleration = new Vector2D(0,0);
		this.width = a.width;
		this.height = a.height;
		this.direction = a.direction;
		stroke = new BasicStroke(20);
		freeze = false;
		dna =  new DNA(a.dna,b.dna);
		age = 0;
		distanceMax = new Vector2D(0, 0).dist(new Vector2D(320, 480));		
		distanceMin = distanceMax;
	}

	public void setPosition(Vector2D position){
		this.position = position;
	}
	public void setPopulation(Population p) {
		population = p;

	}
	public void update(double delta){
		direction = dna.getGene(age).getDirection();
		//System.out.println(dna.getGene(age).getDirection().x + ", "+ dna.getGene(age).getDirection().y);
		speed = speed.add(acceleration);

		Vector2D newPos = position.add(speed.mult(delta).mult(direction));
		//System.out.println(speed.x + "," + speed.y);
		//		System.out.println(speed.add(acceleration).x+ "," + speed.add(acceleration).y);
		//		try {
		//			Thread.sleep(100000);
		//		} catch (InterruptedException e) {
		//			e.printStackTrace();
		//		}
		checkCollision(newPos);
		if(age<lifeSpan-1)
			age++;
		else{
			freeze();
		}
	}
	private void checkCollision(Vector2D newPos) {

		if(newPos.x > d.getWidth()+1){
			freeze();

		}
		else if(newPos.x<-1){
			freeze();

		}

		//y
		if(newPos.y > d.getHeight()+1){
			freeze();
		}
		else if(newPos.y<-1){
			freeze();
		}
		if(!freeze){
			position.x = newPos.x;
			position.y = newPos.y;
			if(position.dist(new Vector2D(0, 0)) < distanceMin){
				distanceMin = position.dist(new Vector2D(0, 0));
				ageopti = age;
			}
		}

	}

	public void draw(Graphics2D g){
		Stroke s = g.getStroke();
		Color c = g.getColor();

		g.setStroke(stroke);
		g.setColor(new Color(100, 100, 100,100));
		g.draw(
				new java.awt.geom.Ellipse2D.Double(
						position.x, position.y, width, height));
		g.setStroke(s);
	}

	private void freeze(){
		if(!freeze){
			population.incrementFrozen();
			fitness = distanceMin / distanceMax;
			fitness = 1/fitness -1;
			if(fitness>0.8)
				fitness = 1;
			else if(fitness<0.3)
				fitness = 0;
			//System.out.println(fitness);

		}
		freeze = true;
	}

	@Override
	public int compareTo(Rocket o) {
		if(this.fitness>o.fitness)
			return 1;
		else if(this.fitness<o.fitness)
			return -1;
		else
			return 0;
	}

	public static Comparator<Rocket> comparator
	= new Comparator<Rocket>() {

		public int compare(Rocket a, Rocket b) {
			//ascending order
			return b.compareTo(a);

			//descending order
			//return fruitName2.compareTo(fruitName1);
		}

	};
}
