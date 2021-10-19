package GameCharacters;

import Jobs.Healer;
import Jobs.Knight;
import Races.Elf;
import Races.Ogre;

public class playground {

	public static void main(String[] args) {
		
		Player p = new Player("j", new Elf(), new Knight(), true);
		Player p1 = new Player("j", new Elf(), new Knight(), true);

		
		
		Elf e = new Elf();
		
		Knight k = new Knight();
		
		
		System.out.println(p1.getRemainingHealth());

		System.out.println(p1.getStrength());
		System.out.println(p1.getIntelligence());
		
		System.out.println(p1.getIfCanFly());
		System.out.println(p1.getIfCanSwim());
		System.out.println(p1.getIfCanWalkThroughTerraign());

		System.out.println(p1.getHealingSkill());

		System.out.println(p1.getSwordSkill());
		System.out.println(p1.getMagicSkill());
		System.out.println(p1.getRemainingMana());
		
		






		
		
		

		
		
		
	
		

		
		// TODO Auto-generated method stub

	}

}
