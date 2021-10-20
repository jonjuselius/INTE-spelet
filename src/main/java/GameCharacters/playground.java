package GameCharacters;

import Jobs.Knight;
import Races.Elf;

public final class playground {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Player p = new Player("jasmyn", new Elf(), new Knight(), true);
		p.increaseIntelligenceFromWinningASpell();
		p.elfLevelsUp();
		System.out.println(p.getLevel()<6);
		
		System.out.println(p.getRace().toString().contains("Elf"));
		System.out.println(p.getIntelligence());
		System.out.println(p.getLevel());
		
	}

}
