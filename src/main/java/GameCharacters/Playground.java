package GameCharacters;

import Jobs.Healer;
import Jobs.Knight;
import Map.MapGenerator;
import Races.Elf;
import Races.Ogre;
import Map.*;

public class Playground {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MapGenerator testMg = new MapGenerator(4, 4);
		Map testMap = testMg.generate(1);
		
		Player p = new Player ("jasmyn", new Ogre(), new Knight(), true, testMap);
		Player p1 = new Player ("Michael", new Elf(), new Healer(), true, testMap);
		
		System.out.println(p.getRemainingHealth());
		
		System.out.println(p1.getRemainingHealth());
		
		p.dealDamageDependingOnYourSwordSkillAndStrength(100, p1);
		
		System.out.println(p.getRemainingHealth());
		
		System.out.println(p1.getRemainingHealth());


	}

}
