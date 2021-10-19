package Item;

import GameCharacters.Character;
import GameCharacters.Player;
import Jobs.Job;
import Jobs.Knight;
import Races.Elf;
import Races.Human;
import Races.Ogre;
import Races.Race;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EggTest {
	
	public static final Egg DEFAULT_EGG = new Egg();
	public static final String DEFAULT_NAME = "Mr Default";
	public static final Race HUMAN = new Human();
	public static final Race OGRE = new Ogre();
	public static final Race ELF = new Elf();
	public static final Job KNIGHT = new Knight();
	public static final Job MAGICIAN = new Knight();
	public static final Job HEALER = new Knight();
	public static final boolean DEFAULT_LIFE_STATUS = true;
	public static final Character[] DEFAULT_PLAYERS = {
			new Player(DEFAULT_NAME, HUMAN, KNIGHT, DEFAULT_LIFE_STATUS),
			new Player(DEFAULT_NAME, OGRE, KNIGHT, DEFAULT_LIFE_STATUS),
			new Player(DEFAULT_NAME, ELF, KNIGHT, DEFAULT_LIFE_STATUS),
			new Player(DEFAULT_NAME, HUMAN, MAGICIAN, DEFAULT_LIFE_STATUS),
			new Player(DEFAULT_NAME, OGRE, MAGICIAN, DEFAULT_LIFE_STATUS),
			new Player(DEFAULT_NAME, ELF, MAGICIAN, DEFAULT_LIFE_STATUS),
			new Player(DEFAULT_NAME, HUMAN, HEALER, DEFAULT_LIFE_STATUS),
			new Player(DEFAULT_NAME, OGRE, HEALER, DEFAULT_LIFE_STATUS),
			new Player(DEFAULT_NAME, ELF, HEALER, DEFAULT_LIFE_STATUS),
	};
	
	@Test
	void R19_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS[0]));
	}
	
	@Test
	void R20_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS[1]));
	}
	
	@Test
	void R21_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS[2]));
	}
	
	@Test
	void R22_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS[3]));
	}
	
	@Test
	void R23_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS[4]));
	}
	
	@Test
	void R24_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS[5]));
	}
	
	@Test
	void R25_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS[6]));
	}
	
	@Test
	void R26_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS[7]));
	}
	
	@Test
	void R27_canUse() {
		assertTrue(DEFAULT_EGG.canBeUsedBy(DEFAULT_PLAYERS[8]));
	}
	
}