package Magic;
import GameCharacters.*;
import GameCharacters.Character;
import Jobs.Healer;
import Races.Ogre;

public class Playground {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HealingSpell h = new HealingSpell("x", 25);
		
		Character c1 = new Character("v", new Ogre(), new Healer(), true );
		
		System.out.println(h);

	}

}
