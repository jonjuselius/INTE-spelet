package GameCharacters;

import Jobs.Healer;
import Jobs.Knight;
import Map.Map;
import Map.MapGenerator;
import Races.Elf;
import Races.Human;
import Races.Ogre;

public class Playground {
	private static final MapGenerator MAP_GENERATOR = new MapGenerator(4, 4);
	public static final Map MAP = MAP_GENERATOR.generate(1);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Player p = new Player ("jasmyn", new Human(), new Knight(), true, MAP);
		Player p1 = new Player ("Michael", new Elf(), new Healer(), true, MAP);
		
		System.out.println(p1.getLevel());
		System.out.println(p1.getIntelligence());
		
		p1.levelsUp();
		
		System.out.println(p1.getLevel());
		System.out.println(p1.getStrength());
		System.out.println(p1.getIntelligence());


		
		

	}

}
