package GameCharacters;

import Jobs.Healer;
import Jobs.Knight;
import Races.Elf;
import Races.Ogre;

public class Playground {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Player p = new Player ("jasmyn", new Ogre(), new Knight(), true);
		Player p1 = new Player ("Michael", new Elf(), new Healer(), true);
		
		System.out.println(p.getRemainingHealth());
		
		System.out.println(p1.getRemainingHealth());
		
		p.dealDamageDependingOnYourSwordSkillAndStrength(100, p1);
		
		System.out.println(p.getRemainingHealth());
		
		System.out.println(p1.getRemainingHealth());


	}

}
