package rockets;

import java.util.ArrayList;
import java.util.Random;

import javax.lang.model.element.Element;

public class DNA {

	ArrayList<Gene> genes;

	public DNA(int lifeSpan) {
		genes = new ArrayList<Gene>();
		for (int i = 0; i < lifeSpan; i++) {
			genes.add(new Gene());
		}
	}

	public DNA(DNA a, DNA b) {
		genes = new ArrayList<Gene>();
		Random r = new Random(System.currentTimeMillis());
		for (int i = 0; i < a.genes.size(); i++) {
			if(Math.random()<0.5){
				genes.add(a.getGene(i));
				//System.out.print("|"+a.getGene(i).direction.x+"|");
			}
			else {
				genes.add(b.getGene(i));
				//System.out.print("|"+b.getGene(i).direction.x+"|");

			}			
		}
		if(Math.random()<0.001)
			genes.get(r.nextInt(genes.size())).direction = new Gene().direction;
		System.out.println();
	}

	public Gene getGene(int index){
		return genes.get(index);
	}
}
