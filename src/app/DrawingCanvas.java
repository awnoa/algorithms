package app;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JPanel;

import rockets.Population;
import rockets.Rocket;
import rockets.Vector2D;

public class DrawingCanvas extends JPanel{

	private int fps;
	private long lastTimeMillis ;
	private long frameTime;
	private Population population;
	private int generation;

	public DrawingCanvas(LayoutManager l) {
		super(l);
		initAttribut();
	}
	public DrawingCanvas() { 
		super();
		initAttribut();
	}

	void initAttribut(){
		
		population = new Population(100, this);
		lastTimeMillis = System.currentTimeMillis();
		fps = 100;
		frameTime = 1000/fps;
		generation = 0;
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		setBackground(Color.lightGray);
		population.draw(g2);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 10)); 
		g.setColor(Color.white);
		g2.drawString("Generation : "+ generation, 50, 480);
	}



	public void gameUpdate(){

		//System.out.println(timeElapsed);

		while(true){
			long currentMillis = System.currentTimeMillis();
			long timeElapsed = currentMillis - lastTimeMillis;

			if(timeElapsed>=frameTime && generation%5 == 0){

				lastTimeMillis = currentMillis;
				repaint();
				doGameUpdates(5);
				//System.out.println(timeElapsed);

			}
			else if(generation%5 != 0){
				doGameUpdates(5);
			}
			//System.out.println(timeElapsed);
		}
	}


	private void doGameUpdates(double delta)
	{
		if(!population.finished()){
			population.update(delta);
		}
		else {
			population = new Population(population);
			population.setPopulation();
			generation ++;
			//population.setPosition(new Vector2D(320, 480));
			//System.out.println("blabl");

		}
	}

}
