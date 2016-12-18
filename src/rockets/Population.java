package rockets;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.print.attribute.standard.Fidelity;

import app.DrawingCanvas;

public class Population {

	ArrayList<Rocket> rockets;
	int frozens;

	public Population(int size, DrawingCanvas d) {
		rockets = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			rockets.add ( 
					new Rocket(
							new Vector2D(320, 480),
							new Vector2D(10, 10),
							Vector2D.fromAngle(14),
							d ,
							this
							)
					);

		}
		frozens = 0;

	}

	public Population(Population population) {
		Random r = new Random(System.currentTimeMillis());
		int indexA;
		int indexB;
		Rocket parentA;
		Rocket parentB;
		ArrayList<Rocket> meetingPool = new ArrayList<>();
		ArrayList<Rocket> childrens = new ArrayList<>() ;

		population.rockets.sort(Rocket.comparator);
		for (int i = 0; i<population.rockets.size() ;i++) {
			double chances = population.rockets.get(i).fitness * 100+1 ;
			for (int j = 0; j < chances; j++) {
				meetingPool.add(population.rockets.get(i));
			}
		}
		//creation des enfants
		for (Rocket rocket : population.rockets) {
			//System.out.println(meetingPool.size());
			indexA = r.nextInt(meetingPool.size());
			indexB = r.nextInt(meetingPool.size());
			
			parentA = meetingPool.get(indexA);
			parentB = meetingPool.get(indexB);
			
			childrens.add(createChildren(parentA,parentB));
		}
		rockets = childrens;
		frozens = 0;
	}

	public Rocket createChildren(Rocket a, Rocket b){
		return new Rocket(a,b);		 
	}
	public void draw(Graphics2D g){
		try {
			for (Rocket rocket : rockets) {
				rocket.draw(g);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public void update(double delta){
		for (Rocket rocket : rockets) {
			rocket.update(delta);
		}
	}
	public void incrementFrozen(){
		frozens++;
	}
	public boolean finished(){
		return frozens == rockets.size();		
	}

	public void setPopulation() {
		for (Rocket rocket : rockets) {
			rocket.setPopulation(this);
		}
	}
	public void setPosition(Vector2D p){
		for (Rocket rocket : rockets) {
			rocket.setPosition(p);
		}
	}
}
